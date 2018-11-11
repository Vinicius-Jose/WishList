package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entity.Codigo;
import excecoes.CodeNotFoundException;

public class CodigoDAOImpl implements CodigoDAO {
	private EntityManagerFactory emf;

	public CodigoDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see dao.AmigoDAO#buscarSolicitacao(entity.Usuario)
	 */
	

	
	/* (non-Javadoc)
	 * @see dao.AmigoDAO#adicionarAmigo(entity.Usuario, entity.Amigo)
	 */
	@Override
	public void adicionarCodigo(Codigo c) {
		EntityManager em = emf.createEntityManager();
		try {
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		em.close();
		}catch(Exception e) {
			removerLinha(c);
			adicionarCodigo(c);
		}
			
	}
	
	
	
	/* (non-Javadoc)
	 * @see dao.CodigoDAO#remover(entity.Codigo)
	 */
	@Override
	public void remover(Codigo c) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
		em.close();
	}
	
	/* (non-Javadoc)
	 * @see dao.CodigoDAO#buscarCodigo(entity.Codigo)
	 */
	@Override
	public boolean buscarCodigo(Codigo c) throws CodeNotFoundException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Codigo> qry = em.createQuery("select c from Codigo c where usuario = :user and codigo = :cod",Codigo.class);
		qry.setParameter("user", c.getUsuario());
		qry.setParameter("cod", c.getCodigo());
		try {
			Codigo code = qry.getSingleResult();
			boolean resposta = true;
			removerLinha(code);
			return resposta;
		}catch (Exception e) {
			e.printStackTrace();
			throw new CodeNotFoundException();
		}

	}
	
	
	private void removerLinha(Codigo c) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Codigo> qry = em.createQuery("select c from Codigo c where usuario = :email",Codigo.class);
		qry.setParameter("email", c.getUsuario());
		c = qry.getSingleResult();
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
		em.close();
	}
	
	
	
}
