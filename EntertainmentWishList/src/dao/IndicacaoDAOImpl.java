package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entity.Indicacao;
import entity.Usuario;

public class IndicacaoDAOImpl implements IndicacaoDAO {
	private EntityManagerFactory emf;
	
	public IndicacaoDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}
	
	
	/* (non-Javadoc)
	 * @see dao.IndicacaoDAO#adicionar(entity.Indicacao)
	 */
	@Override
	public void adicionar(Indicacao indicacao) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(indicacao);
		em.getTransaction().commit();
		em.close();
	}
	
	/* (non-Javadoc)
	 * @see dao.IndicacaoDAO#alterar(entity.Indicacao)
	 */
	@Override
	public void alterar(Indicacao indicacao) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(indicacao);
		em.getTransaction().commit();
		em.close();
	}
	
	/* (non-Javadoc)
	 * @see dao.IndicacaoDAO#buscarIndicacaoEnviada(entity.Usuario)
	 */
	@Override
	public List<Indicacao> buscarIndicacaoEnviada(Usuario user) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Indicacao> query = em.createQuery("select i from Indicacao i where usuarioIndicador.email = :email", Indicacao.class);
		query.setParameter("email", user.getEmail());
		List<Indicacao> estudios = query.getResultList();
		em.close();
		return estudios;
	}
	
	/* (non-Javadoc)
	 * @see dao.IndicacaoDAO#buscarIndicacaoRecebida(entity.Usuario)
	 */
	@Override
	public List<Indicacao> buscarIndicacaoRecebida(Usuario user) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Indicacao> query = em.createQuery("select i from Indicacao i where usuarioRecebido.email = :email", Indicacao.class);
		query.setParameter("email", user.getEmail());
		List<Indicacao> estudios = query.getResultList();
		em.close();
		return estudios;
	}

}
