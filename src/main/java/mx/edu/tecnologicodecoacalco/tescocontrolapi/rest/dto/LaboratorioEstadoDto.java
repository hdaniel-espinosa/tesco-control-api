package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Estado de ocupación de un laboratorio "ahora mismo", según el horario
 * programado (con el mismo margen de 10 minutos que usa AccesoService),
 * más el próximo horario del día si el laboratorio está libre.
 */
@AllArgsConstructor
public class LaboratorioEstadoDto {

	private @Getter @Setter Integer idLaboratorio;

	private @Getter @Setter String nombreLaboratorio;

	private @Getter @Setter String edificio;

	private @Getter @Setter Integer nLugares;

	private @Getter @Setter boolean ocupado;

	private @Getter @Setter HorarioDetalleDto horarioActual;

	private @Getter @Setter HorarioDetalleDto proximoHorario;

	private @Getter @Setter Long minutosParaProximo;
}
