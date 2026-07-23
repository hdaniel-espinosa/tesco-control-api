package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class AccesoResponse {

	private @Getter @Setter boolean acceso;

	private @Getter @Setter String mensaje;

	private @Getter @Setter String usuario;

	private @Getter @Setter LocalDateTime fechaHora;
}
