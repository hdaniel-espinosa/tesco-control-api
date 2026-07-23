package mx.edu.tecnologicodecoacalco.tescocontrolapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "materia")
public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_materia")
	private @Getter @Setter Integer idMateria;

	@Column(name = "nombre", length = 50)
	private @Getter @Setter String nombre;

	@Column(name = "grupo", length = 10)
	private @Getter @Setter String grupo;
}
