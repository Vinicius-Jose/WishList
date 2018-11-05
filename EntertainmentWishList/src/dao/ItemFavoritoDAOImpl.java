package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import entity.Entretenimento;
import entity.ItemFavoritos;
import entity.Usuario;

public class ItemFavoritoDAOImpl implements ItemFavoritoDAO {
	private EntityManagerFactory emf;

	public ItemFavoritoDAOImpl() {
		emf = GenericDAO.getGenericDAO();
	}

	/* (non-Javadoc)
	 * @see dao.ItemFavoritoDAO#adicionar(entity.ItemFavoritos)
	 */
	@Override
	public void adicionar(ItemFavoritos itemFavoritos) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(itemFavoritos);
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see dao.ItemFavoritoDAO#alterar(entity.ItemFavoritos)
	 */
	@Override
	public void alterar(ItemFavoritos itemFavoritos) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(itemFavoritos);
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see dao.ItemFavoritoDAO#buscarFavoritos(entity.Usuario)
	 */
	@Override
	public List<ItemFavoritos> buscarFavoritos(Usuario user) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<ItemFavoritos> query = em.createQuery("select i from ItemFavoritos i  where i.usuario.email = :email", ItemFavoritos.class);
		query.setParameter("email", user.getEmail());
		List<ItemFavoritos> favoritos = query.getResultList();
		em.close();
		return favoritos;
	}
	
	/* (non-Javadoc)
	 * @see dao.ItemFavoritoDAO#remover(entity.ItemFavoritos)
	 */
	@Override
	public void remover(ItemFavoritos itemFavoritos) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(itemFavoritos);
		em.getTransaction().commit();
		em.close();
	}
	
	
	@Override
	public List<ItemFavoritos> buscarCriticas(Entretenimento entretenimento){
		EntityManager em = emf.createEntityManager();
		TypedQuery<ItemFavoritos> query = em.createQuery("select i from ItemFavoritos i  where i.nota >0 and i.critica is not null and i.entretenimento.id = :id", ItemFavoritos.class);
		query.setParameter("id", entretenimento.getId());
		List<ItemFavoritos> criticas = query.getResultList();
		em.close();
		return criticas;
	}

}
