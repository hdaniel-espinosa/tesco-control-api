package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** Registro de bitácora con el nombre del maestro dueño de la tarjeta, si tiene una asignada. */
@AllArgsConstructor
public class RegistroDto {

	private @Getter @Setter Integer idRegistro;

	private @Getter @Setter String idTarjeta;

	private @Getter @Setter Integer idLaboratorio;

	private @Getter @Setter LocalDateTime fechaHora;

	private @Getter @Setter boolean abrio;

	private @Getter @Setter String nombreMaestro;
}
