package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entity.Game;

public class GameDAOImpl implements GameDAO {
	private EntityManagerFactory emf;

	public GameDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}

	/* (non-Javadoc)
	 * @see dao.GameDAO#adicionar(entity.Game)
	 */
	@Override
	public void adicionar(Game game) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(game);
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see dao.GameDAO#alterar(entity.Game)
	 */
	@Override
	public void alterar(Game game) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(game);
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see dao.GameDAO#buscarGames(java.lang.String)
	 */
	@Override
	public List<Game> buscarGames(String nome) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Game> query = em.createQuery("select g from Game g   where g.nomeOriginal like '%" + nome + "%'",
				Game.class);
		List<Game> games = query.getResultList();
		em.close();
		return games;
	}

}
