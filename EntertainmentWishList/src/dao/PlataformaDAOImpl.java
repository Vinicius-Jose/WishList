package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entity.Game;
import entity.Plataforma;

public class PlataformaDAOImpl implements PlataformaDAO {
	private EntityManagerFactory emf;
	
	public PlataformaDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}
	
	
	/* (non-Javadoc)
	 * @see dao.PlataformaDAO#adicionar(entity.Plataforma)
	 */
	@Override
	public void adicionar(Plataforma plataforma) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(plataforma);
		em.getTransaction().commit();
		em.close();
	}
	
	/* (non-Javadoc)
	 * @see dao.PlataformaDAO#alterar(entity.Plataforma)
	 */
	@Override
	public void alterar(Plataforma plataforma) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(plataforma);
		em.getTransaction().commit();
		em.close();
	}
	
	/* (non-Javadoc)
	 * @see dao.PlataformaDAO#buscarPlataformas()
	 */
	@Override
	public List<Plataforma> buscarPlataformas() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Plataforma> query = em.createQuery("select p from Plataforma p ", Plataforma.class);
		List<Plataforma> plataformas = query.getResultList();
		em.close();
		return plataformas;
	}
	
	@Override
	public List<Plataforma> buscarPlataformas(Game g) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Plataforma> query = em.createQuery("select p from Plataforma p  game.plataformas.id= p.id and game.id =   :game ", Plataforma.class);
		query.setParameter("game",g.getId());
		List<Plataforma> plataformas = query.getResultList();
		em.close();
		return plataformas;
	}
	

}
