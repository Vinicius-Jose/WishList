package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entity.Filme;

public class FilmeDAOImpl implements FilmeDAO {
	private EntityManagerFactory emf;

	public FilmeDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}

	/* (non-Javadoc)
	 * @see dao.FilmeDAO#adicionar(entity.Filme)
	 */
	@Override
	public void adicionar(Filme filme) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(filme);
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see dao.FilmeDAO#alterar(entity.Filme)
	 */
	@Override
	public void alterar(Filme filme) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(filme);
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see dao.FilmeDAO#buscarFilmes(java.lang.String)
	 */
	@Override
	public List<Filme> buscarFilmes(String nome) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Filme> query = em.createQuery("select f from Filme f   where f.nomeOriginal like '%" + nome
				+ "%' or f.nomePortugues like '%" + nome + "%'", Filme.class);
		List<Filme> filme = query.getResultList();
		em.close();
		return filme;
	}
	
	/* (non-Javadoc)
	 * @see dao.FilmeDAO#buscarNomesFilmes(java.lang.String)
	 */
	@Override
	public List<String> buscarNomesFilmes(String nome){
		EntityManager em = emf.createEntityManager();
		TypedQuery<String> query = em.createQuery(
				"select f.nomePortugues from Filme f where f.nomePortugues like'%" + nome+"%'",
				String.class);
		return query.getResultList();
		
	}

}
