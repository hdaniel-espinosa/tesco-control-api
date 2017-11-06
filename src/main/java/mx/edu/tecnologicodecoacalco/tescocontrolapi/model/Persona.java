package mx.edu.tecnologicodecoacalco.tescocontrolapi.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2175866737290937335L;

	private @Getter @Setter String nombre;

	private @Getter @Setter String apPaterno;

	private @Getter @Setter String apMaterno;

	private @Getter @Setter String edad;
}
