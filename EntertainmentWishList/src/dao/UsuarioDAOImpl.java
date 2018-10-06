package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entity.Usuario;
import excecoes.UserException;

public class UsuarioDAOImpl {
	private EntityManagerFactory emf;

	public UsuarioDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}

	public void adicionar(Usuario usuario) throws UserException {
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new UserException(usuario.getEmail());
		} finally {
			em.close();
		}
	}

	public void alterar(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();
		em.close();
	}

	public Usuario validarUsuario(String email, String senha) throws UserException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Usuario> query = em.createQuery(
				"select u from Usuario u where email = '" + email + "' and senha = '" + senha + "'", Usuario.class);
		Usuario usuario = null;
		try {
			usuario = query.getSingleResult();
			em.close();
		} catch (NoResultException no) {
			throw new UserException();
		} finally {
			return usuario;
		}
	}

	public List<Usuario> buscarUsuarios(String nome) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where email like '%" + nome
				+ "%' or nickname like '" + nome + "' or primeiroNome like '%" + nome + "%'", Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		em.close();
		return usuarios;
	}

}
