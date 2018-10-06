package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entity.Estudio;

public class EstudioDAOImpl {
	private EntityManagerFactory emf;
	
	public EstudioDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}
	
	
	public void adicionar(Estudio estudio) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(estudio);
		em.getTransaction().commit();
		em.close();
	}
	
	public void alterar(Estudio estudio) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(estudio);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Estudio> buscarEstudios() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Estudio> query = em.createQuery("select e from Estudio e ", Estudio.class);
		List<Estudio> estudios = query.getResultList();
		em.close();
		return estudios;
	}

}
