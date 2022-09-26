package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo01 {
	
	public static void main(String[] args) {
		// obtener la conexión
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		// generar el manejador de entidades según la conexión
		EntityManager em = fabrica.createEntityManager();
		//proceso de registro de un nuevo usuario -> transacción (reg, act, elim)
		em.getTransaction().begin();
		// obj Usuario
		Usuario u = new Usuario();
		u.setCodigo(20);
		u.setNombre("Juan");
		u.setApellido("Perez");
		u.setUsuario("jperez");
		u.setClave("555555");
		u.setFecha("2020/10/05");
		u.setTipo(1);
		u.setEstado(1);
		em.persist(u);
		// confimar la transacción
		em.getTransaction().commit();
		// cerrar
		em.close();
	}
	
}
