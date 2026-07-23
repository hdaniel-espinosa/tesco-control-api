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
@Table(name = "laboratorio")
public class Laboratorio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_laboratorio")
	private @Getter @Setter Integer idLaboratorio;

	@Column(name = "nombre", length = 50)
	private @Getter @Setter String nombre;

	@Column(name = "edificio", length = 10)
	private @Getter @Setter String edificio;

	@Column(name = "n_lugares")
	private @Getter @Setter Integer nLugares;
}
