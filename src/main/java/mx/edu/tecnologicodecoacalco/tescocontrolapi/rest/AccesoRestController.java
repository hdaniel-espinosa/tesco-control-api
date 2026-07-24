package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.rest.dto.AccesoRequest;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.rest.dto.AccesoResponse;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.service.AccesoService;

/**
 * Endpoint que consulta el lector NFC del laboratorio para decidir si abre la
 * puerta. No requiere autenticación interactiva: lo invoca el propio lector.
 */
@RestController
@RequestMapping(value = "/tesco-control-api/acceso")
@RequiredArgsConstructor
public class AccesoRestController {

	private final AccesoService accesoService;

	@PostMapping("/validar")
	public ResponseEntity<AccesoResponse> validarAcceso(@RequestBody AccesoRequest request) {
		AccesoResponse respuesta = accesoService.validarAcceso(request.getIdTarjeta(), request.getIdLaboratorio());
		HttpStatus status = respuesta.isAcceso() ? HttpStatus.OK : HttpStatus.FORBIDDEN;
		return ResponseEntity.status(status).body(respuesta);
	}
}
