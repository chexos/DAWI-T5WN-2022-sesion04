package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_productos")
@Data
public class Producto {
	@Id
	@Column(name = "id_prod", length = 5)
	private String idprod;
	@Column(name = "des_prod", length = 45)
	private String descripcion;
	@Column(name = "stk_prod")
	private int stock;
	@Column(name = "pre_prod")
	private double precio;
	@Column(name = "idcategoria")
	private int idcategoria;
	@Column(name = "est_prod")
	private boolean estado;
	@Column(name = "idproveedor")
	private int idproveedor;
	
	@ManyToOne
	@JoinColumn(name = "idcategoria", insertable = false, updatable = false)
	private Categoria objCategoria;
	
	@ManyToOne
	@JoinColumn(name = "idproveedor", insertable = false, updatable = false)
	private Proveedor objProveedor;
}
