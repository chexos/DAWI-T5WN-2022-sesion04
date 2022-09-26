package model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Table(name = "tb_tipos")
@Data
public class Tipo {
	@Id
	private int idtipo;
	private String descripcion;
	
}
