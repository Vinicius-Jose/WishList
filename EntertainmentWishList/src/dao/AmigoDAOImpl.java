package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entity.Amigo;
import entity.Usuario;
import enumeradas.StatusAmigo;
import excecoes.FriendException;

public class AmigoDAOImpl implements AmigoDAO {
	private EntityManagerFactory emf;

	public AmigoDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see dao.AmigoDAO#buscarSolicitacao(entity.Usuario)
	 */
	@Override
	public List<Amigo> buscarSolicitacaoRecebida(Usuario user){
		EntityManager em = emf.createEntityManager();
		TypedQuery<Amigo> query = em.createQuery("select a from Amigo a where usuarioEmail2 = :email and status = 2", Amigo.class);
		query.setParameter("email",user.getEmail());
		List<Amigo> usuarios = query.getResultList();
		em.close();
		return usuarios;
	}
	
	@Override
	public List<Amigo> buscarSolicitacaoEnviada(Usuario user){
		EntityManager em = emf.createEntityManager();
		TypedQuery<Amigo> query = em.createQuery("select a from Amigo a where usuarioEmail = :email and status = 2", Amigo.class);
		query.setParameter("email",user.getEmail());
		List<Amigo> usuarios = query.getResultList();
		em.close();
		return usuarios;
	}
	
	
	/* (non-Javadoc)
	 * @see dao.AmigoDAO#adicionarAmigo(entity.Usuario, entity.Amigo)
	 */
	@Override
	public void adicionarAmigo(Amigo amigo) throws FriendException {
		EntityManager em = emf.createEntityManager();
		try {
		em.getTransaction().begin();
		em.persist(amigo);
		em.getTransaction().commit();
		em.close();
		}catch(Exception e) {
//			throw new FriendException(amigo.getUsuario());
		}
			
	}
	
	
	/* (non-Javadoc)
	 * @see dao.AmigoDAO#atualizarAmizade(java.lang.String, java.lang.String, enumeradas.StatusAmigo)
	 */
	@Override
	public void atualizarAmizade(String usuarioEmail, String usuarioEmail2, StatusAmigo status) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("update Amigo set status = :status where usuarioEmail = :user and usuarioEmail2 = :user2");
		query.setParameter("user",  usuarioEmail);
		query.setParameter("user2", usuarioEmail2);
		query.setParameter("status", status);
		em.getTransaction().begin();
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	
	@Override
	public void remover(Amigo a) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Amigo> qry = em.createQuery("select a from Amigo a where usuarioEmail = :user and usuarioEmail2 = :user2",Amigo.class);
		qry.setParameter("user", a.getAmigoPk().getUsuarioEmail());
		qry.setParameter("user2", a.getAmigoPk().getUsuarioEmail2());
		a = qry.getSingleResult();
		em.getTransaction().begin();
		em.remove(a);
		em.getTransaction().commit();
		qry = em.createQuery("select a from Amigo a where usuarioEmail = :user2 and usuarioEmail2 = :user",Amigo.class);
		qry.setParameter("user", a.getAmigoPk().getUsuarioEmail());
		qry.setParameter("user2", a.getAmigoPk().getUsuarioEmail2());
		a = qry.getSingleResult();
		em.getTransaction().begin();
		em.remove(a);
		em.getTransaction().commit();
		em.close();
	}
	
	
	
}
