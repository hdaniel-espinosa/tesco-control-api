package mx.edu.tecnologicodecoacalco.tescocontrolapi.model;

import java.time.LocalDateTime;

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
@Table(name = "registro")
public class Registro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_registro")
	private @Getter @Setter Integer idRegistro;

	@Column(name = "id_tarjeta", length = 15)
	private @Getter @Setter String idTarjeta;

	@Column(name = "id_laboratorio")
	private @Getter @Setter Integer idLaboratorio;

	@Column(name = "fecha_hora")
	private @Getter @Setter LocalDateTime fechaHora;

	@Column(name = "abrio")
	private @Getter @Setter Boolean abrio;
}
