package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {
	
	public static void main(String[] args) {
		// obtener la conexión
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		// generar el manejador de entidades según la conexión
		EntityManager em = fabrica.createEntityManager();
		// proceso de actualizar de un nuevo usuario -> transacción (reg, act, elim)
		em.getTransaction().begin();
		// obj Usuario
		Usuario u = em.find(Usuario.class, 20);
		/*
		Usuario u = new Usuario();
		u.setCodigo(20);
		u.setNombre("Juan Carlos");
		u.setApellido("Perez Cruz");
		u.setUsuario("jperez");
		u.setClave("55555");
		u.setFecha("2020/10/05");
		u.setTipo(1);
		u.setEstado(1);
		*/
		// forma 1. el borrado físico -> elimina definitivamente
		em.remove(u); // necesita toda la información del usuario -> buscar y devolver un objeto usuario
		// forma 2. borrado lógico -> cambio de estado
		//u.setEstado(2); // es necesario toda la información del usuario
		//em.merge(u);
		// confimar la transacción
		em.getTransaction().commit();
		// cerrar
		em.close();
	}
	
}
