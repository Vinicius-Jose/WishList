package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import entity.Entretenimento;
import excecoes.NotEvaluatedException;

public class EntretenimentoDAOImpl implements EntretenimentoDAO {
	private EntityManagerFactory emf;

	public EntretenimentoDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}

	/* (non-Javadoc)
	 * @see dao.EntretenimentoDAO#buscarMediaUsuarios(entity.Entretenimento)
	 */
	@Override
	public Entretenimento buscarMediaUsuarios(Entretenimento entretenimento) throws NotEvaluatedException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Double> query = em.createQuery(
				"select avg(i.nota) from ItemFavoritos i where i.nota>0 and i.entretenimento.id = :id",
				Double.class);
		try {
			query.setParameter("id",  + entretenimento.getId());
			System.out.println(entretenimento.getNomeOriginal());
			entretenimento.setNotaUsuario(query.getSingleResult());
			em.close();
			return entretenimento;
		} catch (NonUniqueResultException |NullPointerException| NoResultException e) {
			throw new NotEvaluatedException(entretenimento);
		}
	}
	
	/* (non-Javadoc)
	 * @see dao.EntretenimentoDAO#buscarNomes(java.lang.String)
	 */
	@Override
	public List<String> buscarNomes(){
		EntityManager em = emf.createEntityManager();
		TypedQuery<String> query = em.createQuery(
				"select e.nomeOriginal from Entretenimento e ",
				String.class);
		List<String> nomes = query.getResultList();
		em.close();
		return nomes;
		
	}
	
	
	@Override
	public List<Entretenimento> maisBuscados(){
		EntityManager em = emf.createEntityManager();
		TypedQuery<Entretenimento> query = em.createQuery(
				"select  it.entretenimento from ItemFavoritos it group by it.entretenimento order by count(it.entretenimento) desc ",
				Entretenimento.class);

		List<Entretenimento> maisBuscados = query.getResultList();
		em.close();
		return maisBuscados;
	}
}
