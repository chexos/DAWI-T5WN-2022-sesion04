package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05 {
	
	public static void main(String[] args) {

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		em.getTransaction().begin();
		Usuario u = em.find(Usuario.class, 20);
		if (u != null) {
			em.remove(u);
		} else {
			System.out.println("No existe el usuario");
		}
		em.getTransaction().commit();
		em.close();
	}
	
}
