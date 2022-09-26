package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_categorias")
@Data
public class Categoria {
	@Id
	@Column(name = "idcategoria")
	private int idcategoria;
	@Column(name = "descripcion", length = 45)
	private String descripcion;
}
