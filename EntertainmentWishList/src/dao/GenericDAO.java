package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDAO {
	private static EntityManagerFactory emf;
	
	private GenericDAO() {
		emf = Persistence.createEntityManagerFactory("WISHLIST");
	}
	public static EntityManagerFactory getGenericDAO() {
		if(emf == null || !emf.isOpen()) {
			new GenericDAO();
		}

		return emf;
	}
	
	

	

}
