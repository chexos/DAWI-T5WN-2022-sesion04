package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_proveedor")
@Data
public class Proveedor {
	@Id
	@Column(name = "idproveedor")
	private int idproveedor;
	@Column(name = "nombre_rs", length = 45)
	private String nombre;
	@Column(name = "telefono", length = 10)
	private String telefono;
	@Column(name = "email", length = 45)
	private String email;
}
