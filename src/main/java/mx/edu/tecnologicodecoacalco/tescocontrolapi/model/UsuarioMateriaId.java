package mx.edu.tecnologicodecoacalco.tescocontrolapi.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UsuarioMateriaId implements Serializable {

	private static final long serialVersionUID = 1L;

	private @Getter @Setter Integer idUsuario;

	private @Getter @Setter Integer idMateria;
}
