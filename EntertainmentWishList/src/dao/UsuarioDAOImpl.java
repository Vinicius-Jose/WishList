package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import entity.Amigo;
import entity.Usuario;
import excecoes.FriendException;
import excecoes.UserException;

public class UsuarioDAOImpl implements UsuarioDAO  {
	private EntityManagerFactory emf;

	public UsuarioDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}

	/* (non-Javadoc)
	 * @see dao.FilmeDAO#adicionar(entity.Usuario)
	 */
	/* (non-Javadoc)
	 * @see dao.UsuarioDAO#adicionar(entity.Usuario)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see dao.FilmeDAO#alterar(entity.Usuario)
	 */
	/* (non-Javadoc)
	 * @see dao.UsuarioDAO#alterar(entity.Usuario)
	 */
	@Override
	public void alterar(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see dao.FilmeDAO#validarUsuario(java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see dao.UsuarioDAO#validarUsuario(java.lang.String, java.lang.String)
	 */
	@Override
	public Usuario validarUsuario(String email, String senha) throws UserException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Usuario> query = em.createQuery(
				"select u from Usuario u where email = :email and senha = :senha", Usuario.class);
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		Usuario usuario = null;
		try {
			usuario = query.getSingleResult();
			em.close();
			return usuario;
		} catch (NoResultException no) {
			throw new UserException();
		}
	}

	/* (non-Javadoc)
	 * @see dao.FilmeDAO#buscarUsuarios(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see dao.UsuarioDAO#buscarUsuarios(java.lang.String)
	 */
	@Override
	public List<Usuario> buscarUsuarios(String nome) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where email like :email or nickname like :nick or primeiroNome like :primeiro", Usuario.class);
		query.setParameter("email", "%" + nome+"%");
		query.setParameter("nick", "%" + nome+"%");
		query.setParameter("primeiro", "%" + nome+"%");
		List<Usuario> usuarios = query.getResultList();
		em.close();
		return usuarios;
	}
	
	@Override
	public List<Amigo> buscarSolicitacao(Usuario user){
		EntityManager em = emf.createEntityManager();
		TypedQuery<Amigo> query = em.createQuery("select a from Amigo a where usuario = :email ", Amigo.class);
		query.setParameter("email",user.getEmail());
		List<Amigo> usuarios = query.getResultList();
		em.close();
		return usuarios;
	}
	
	
	@Override
	public void adicionarAmigo(Usuario user, Amigo amigo) throws FriendException {
		EntityManager em = emf.createEntityManager();
		try {
		em.getTransaction().begin();
		em.persist(amigo);
		em.getTransaction().commit();
		em.close();
		alterar(user);
		}catch(Exception e) {
			throw new FriendException(amigo.getUsuario());
		}
			
	}

}
