package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Persona;

@RestController
public class PersonaRestController {

	@RequestMapping("/personas")
	public Persona recuperarPersona() {
		Persona persona = new Persona();
		persona.setNombre("Hector Daniel");
		persona.setApPaterno("Espinosa");
		persona.setApMaterno("Moreno");
		persona.setEdad("22");
		return persona;
	}
}