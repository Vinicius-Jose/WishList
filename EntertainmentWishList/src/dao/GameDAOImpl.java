package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entity.Game;

public class GameDAOImpl {
	private EntityManagerFactory emf;

	public GameDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}

	public void adicionar(Game game) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(game);
		em.getTransaction().commit();
		em.close();
	}

	public void alterar(Game game) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(game);
		em.getTransaction().commit();
		em.close();
	}

	public List<Game> buscarGames(String nome) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Game> query = em.createQuery("select g from Game g   where g.nomeOriginal like '%" + nome + "%'",
				Game.class);
		List<Game> games = query.getResultList();
		em.close();
		return games;
	}

}
