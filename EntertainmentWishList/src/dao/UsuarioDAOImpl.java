package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entity.Estudio;
import entity.Usuario;
import excecoes.UserNotFoundException;

public class UsuarioDAOImpl {
	private EntityManagerFactory emf;

	public UsuarioDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}

	public void adicionar(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
	}

	public void alterar(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();
		em.close();
	}

	public Usuario validarUsuario(String email, String senha) throws UserNotFoundException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Usuario> query = em.createQuery(
				"select u from Usuario u where email = '" + email + "' and senha = '" + senha + "'", Usuario.class);
		Usuario usuario;
		try {
			usuario = query.getSingleResult();
			em.close();
		} catch (NoResultException no) {
			throw new UserNotFoundException();
		}
		
		return usuario;
	}
	
	
	public List<Usuario> buscarUsuarios(String nome) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where email like '%" +nome+"%' or nickname like '"+nome+"' or primeiroNome like '%"+nome+"%'", Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		em.close();
		return usuarios;
	}

}
