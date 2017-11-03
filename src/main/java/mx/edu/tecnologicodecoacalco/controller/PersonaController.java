package mx.edu.tecnologicodecoacalco.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.tecnologicodecoacalco.dto.Persona;

@RestController
public class PersonaController {

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