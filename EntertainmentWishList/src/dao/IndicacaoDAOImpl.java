package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entity.Indicacao;
import entity.Usuario;

public class IndicacaoDAOImpl {
	private EntityManagerFactory emf;
	
	public IndicacaoDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}
	
	
	public void adicionar(Indicacao indicacao) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(indicacao);
		em.getTransaction().commit();
		em.close();
	}
	
	public void alterar(Indicacao indicacao) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(indicacao);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Indicacao> buscarIndicacaoEnviada(Usuario user) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Indicacao> query = em.createQuery("select i from Indicacao i where usuarioIndicador.email = '" + user.getEmail()+"'", Indicacao.class);
		List<Indicacao> estudios = query.getResultList();
		em.close();
		return estudios;
	}
	
	public List<Indicacao> buscarIndicacaoRecebida(Usuario user) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Indicacao> query = em.createQuery("select i from Indicacao i where usuarioRecebido.email = '" + user.getEmail()+"'", Indicacao.class);
		List<Indicacao> estudios = query.getResultList();
		em.close();
		return estudios;
	}

}
