package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entity.Estudio;

public class EstudioDAOImpl implements EstudioDAO {
	private EntityManagerFactory emf;
	
	public EstudioDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}
	
	
	/* (non-Javadoc)
	 * @see dao.EstudioDAO#adicionar(entity.Estudio)
	 */
	@Override
	public void adicionar(Estudio estudio) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(estudio);
		em.getTransaction().commit();
		em.close();
	}
	
	/* (non-Javadoc)
	 * @see dao.EstudioDAO#alterar(entity.Estudio)
	 */
	@Override
	public void alterar(Estudio estudio) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(estudio);
		em.getTransaction().commit();
		em.close();
	}
	
	/* (non-Javadoc)
	 * @see dao.EstudioDAO#buscarEstudios()
	 */
	@Override
	public List<Estudio> buscarEstudios() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Estudio> query = em.createQuery("select e from Estudio e ", Estudio.class);
		List<Estudio> estudios = query.getResultList();
		em.close();
		return estudios;
	}

}
