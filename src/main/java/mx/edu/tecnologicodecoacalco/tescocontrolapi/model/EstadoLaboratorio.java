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
@Table(name = "estado_laboratorio")
public class EstadoLaboratorio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estado")
	private @Getter @Setter Integer idEstado;

	@Column(name = "id_laboratorio")
	private @Getter @Setter Integer idLaboratorio;

	@Column(name = "fecha_hora")
	private @Getter @Setter LocalDateTime fechaHora;

	@Column(name = "temperatura")
	private @Getter @Setter Integer temperatura;

	@Column(name = "humedad")
	private @Getter @Setter Integer humedad;
}
