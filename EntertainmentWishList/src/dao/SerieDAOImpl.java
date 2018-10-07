package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entity.Serie;

public class SerieDAOImpl {
	private EntityManagerFactory emf;

	public SerieDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}

	public void adicionar(Serie serie) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(serie);
		em.getTransaction().commit();
		em.close();
	}

	public void alterar(Serie serie) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(serie);
		em.getTransaction().commit();
		em.close();
	}

	public List<Serie> buscarSeries(String nome) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Serie> query = em.createQuery("select s from Serie s  where s.nomeOriginal like '%" + nome
				+ "%' or s.nomePortugues like '%" + nome + "%'", Serie.class);
		List<Serie> serie = query.getResultList();
		em.close();
		return serie;
	}
	
	
	public List<String> buscarNomesSeries(String nome){
		EntityManager em = emf.createEntityManager();
		TypedQuery<String> query = em.createQuery(
				"select s.nomePortugues from Serie s where s.nomePortugues like'%" + nome+"%'",
				String.class);
		return query.getResultList();
		
	}

}
