package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint protegido que devuelve quién es el usuario autenticado. El
 * frontend lo usa para validar las credenciales al iniciar sesión y para
 * confirmar que la sesión guardada localmente todavía es válida.
 */
@RestController
@RequestMapping(value = "/tesco-control-api/auth")
public class AuthRestController {

	@GetMapping("/me")
	public Map<String, String> me(Authentication authentication) {
		return Map.of("username", authentication.getName());
	}
}
