package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** Horario de hoy que todavía no empieza, junto con cuánto falta para que inicie. */
@AllArgsConstructor
public class HorarioProximoDto {

	private @Getter @Setter HorarioDetalleDto horario;

	private @Getter @Setter long minutosParaInicio;
}
