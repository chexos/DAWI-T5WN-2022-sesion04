package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo04 {
	
	public static void main(String[] args) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		em.getTransaction().begin();
		Usuario u = em.find(Usuario.class, 20);
		System.out.println("Código : " + u.getCodigo());
		System.out.println("Nombre : " + u.getNombre());
		System.out.println("Apellido : " + u.getApellido());
		System.out.println("Usuario :" + u.getUsuario());
		System.out.println("Clave : " + u.getClave());
		System.out.println("Fecha :" + u.getFecha());
		System.out.println("Tipo : " + u.getTipo());
		System.out.println("Estado " + u.getEstado());
		em.getTransaction().commit();
		em.close();
	}
	
}
