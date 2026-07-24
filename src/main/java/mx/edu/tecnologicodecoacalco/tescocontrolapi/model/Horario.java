package mx.edu.tecnologicodecoacalco.tescocontrolapi.model;

import java.time.LocalTime;

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
@Table(name = "horario")
public class Horario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_horario")
	private @Getter @Setter Integer idHorario;

	@Column(name = "dia", length = 20)
	private @Getter @Setter String dia;

	@Column(name = "hora_inicio")
	private @Getter @Setter LocalTime horaInicio;

	@Column(name = "hora_termino")
	private @Getter @Setter LocalTime horaTermino;

	@Column(name = "id_laboratorio")
	private @Getter @Setter Integer idLaboratorio;

	@Column(name = "id_materia")
	private @Getter @Setter Integer idMateria;
}
