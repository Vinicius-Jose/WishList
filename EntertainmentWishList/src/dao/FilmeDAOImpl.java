package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entity.Filme;

public class FilmeDAOImpl {
	private EntityManagerFactory emf;

	public FilmeDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}

	public void adicionar(Filme filme) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(filme);
		em.getTransaction().commit();
		em.close();
	}

	public void alterar(Filme filme) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(filme);
		em.getTransaction().commit();
		em.close();
	}

	public List<Filme> buscarFilmes(String nome) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Filme> query = em.createQuery("select f from Filme f   where f.nomeOriginal like '%" + nome
				+ "%' or f.nomePortugues like '%" + nome + "%'", Filme.class);
		List<Filme> filme = query.getResultList();
		em.close();
		return filme;
	}

}
