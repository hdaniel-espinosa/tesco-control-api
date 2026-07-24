package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest.dto;

import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Horario enriquecido con los nombres de laboratorio, materia y maestro(s)
 * que la imparten, para no obligar al frontend a cruzar esa información a
 * partir de los ids.
 */
@AllArgsConstructor
public class HorarioDetalleDto {

	private @Getter @Setter Integer idHorario;

	private @Getter @Setter String dia;

	private @Getter @Setter LocalTime horaInicio;

	private @Getter @Setter LocalTime horaTermino;

	private @Getter @Setter Integer idLaboratorio;

	private @Getter @Setter String nombreLaboratorio;

	private @Getter @Setter Integer idMateria;

	private @Getter @Setter String nombreMateria;

	private @Getter @Setter List<String> maestros;
}
