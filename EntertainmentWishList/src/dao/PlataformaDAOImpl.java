package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entity.Plataforma;

public class PlataformaDAOImpl {
	private EntityManagerFactory emf;
	
	public PlataformaDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}
	
	
	public void adicionar(Plataforma plataforma) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(plataforma);
		em.getTransaction().commit();
		em.close();
	}
	
	public void alterar(Plataforma plataforma) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(plataforma);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Plataforma> buscarPlataformas() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Plataforma> query = em.createQuery("select p from Plataforma p ", Plataforma.class);
		List<Plataforma> plataformas = query.getResultList();
		em.close();
		return plataformas;
	}

}
