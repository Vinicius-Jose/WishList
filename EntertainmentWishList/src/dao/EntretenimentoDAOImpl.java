package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import entity.Entretenimento;
import excecoes.NotEvaluatedException;

public class EntretenimentoDAOImpl {
	private EntityManagerFactory emf;

	public EntretenimentoDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}

	public Entretenimento buscarMediaUsuarios(Entretenimento entretenimento) throws NotEvaluatedException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Double> query = em.createQuery(
				"select avg(i.nota) from ItemFavoritos i where i.entretenimento.id = " + entretenimento.getId(),
				Double.class);
		try {
			entretenimento.setNotaUsuario(query.getSingleResult());
		} catch (NonUniqueResultException e) {
			throw new NotEvaluatedException(entretenimento);
		} finally {
			return entretenimento;
		}
	}
	
	public List<String> buscarNomes(String nome){
		EntityManager em = emf.createEntityManager();
		TypedQuery<String> query = em.createQuery(
				"select e.nomeOriginal from Entretenimento e where e.nomeOriginal like'%" + nome+"%'",
				String.class);
		return query.getResultList();
		
	}
}
