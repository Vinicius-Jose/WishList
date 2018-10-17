package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
			entretenimento.setNotaUsuario(query.getSingleResult());
		} catch (NonUniqueResultException e) {
			throw new NotEvaluatedException(entretenimento);
		} finally {
			em.close();
			return entretenimento;
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
}
