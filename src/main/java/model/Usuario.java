package model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;

@Entity
@Table(name = "tb_usuarios")
@Data
public class Usuario {
	@Id
	@Column(name = "cod_usua")
	private int codigo;
	@Column(name = "nom_usua", length = 15)
	private String nombre;
	@Column(name = "ape_usua", length = 25)
	private String apellido;
	@Column(name = "usr_usua", length = 45, unique = true)
	private String usuario;
	@Column(name = "cla_usua", length = 100)
	private String clave;
	@Column(name = "fna_usua")
	private String fecha;
	@Column(name = "idtipo")
	private int tipo;
	@Column(name = "est_usua")
	private int estado;
	
	@ManyToOne
	@JoinColumn(name = "idtipo", insertable = false, updatable = false)
	private Tipo objTipo;
	
}
