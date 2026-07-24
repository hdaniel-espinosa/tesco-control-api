package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

public class AccesoRequest {

	private @Getter @Setter String idTarjeta;

	private @Getter @Setter Integer idLaboratorio;

	/**
	 * Opcional: solo la usa el simulador de lector NFC para probar horarios
	 * sin depender de la hora real. El lector físico nunca la envía, por lo
	 * que el acceso normal siempre evalúa contra la hora actual del servidor.
	 */
	private @Getter @Setter LocalDateTime fechaHoraSimulada;
}
