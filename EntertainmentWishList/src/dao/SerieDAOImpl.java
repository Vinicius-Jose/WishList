package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entity.Serie;

public class SerieDAOImpl implements SerieDAO {
	private EntityManagerFactory emf;

	public SerieDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}

	/* (non-Javadoc)
	 * @see dao.SerieDAO#adicionar(entity.Serie)
	 */
	@Override
	public void adicionar(Serie serie) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(serie);
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see dao.SerieDAO#alterar(entity.Serie)
	 */
	@Override
	public void alterar(Serie serie) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(serie);
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see dao.SerieDAO#buscarSeries(java.lang.String)
	 */
	@Override
	public List<Serie> buscarSeries(String nome) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Serie> query = em.createQuery("select s from Serie s  where s.nomeOriginal like :nomeOriginal or s.nomePortugues like :nomePortugues", Serie.class);
		query.setParameter("nomeOriginal", "%" + nome+"%");
		query.setParameter("nomePortugues", "%" + nome+"%");
		List<Serie> serie = query.getResultList();
		em.close();
		return serie;
	}
	
	
	/* (non-Javadoc)
	 * @see dao.SerieDAO#buscarNomesSeries()
	 */
	@Override
	public List<String> buscarNomesSeries(){
		EntityManager em = emf.createEntityManager();
		TypedQuery<String> query = em.createQuery(
				"select s.nomePortugues from Serie s ",
				String.class);
		return query.getResultList();
		
	}

}
