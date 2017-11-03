package mx.edu.tecnologicodecoacalco.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.support.GenericDto;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario_sistema")
public class UsuarioSistemaDto extends GenericDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4123586855990394097L;

	@Id
	@Column(name = "id_usuario_sistema", unique = true, nullable = false)
	private @Getter @Setter Integer idUsuarioSistema;

	@Column(name = "nombre", length = 50)
	private @Getter @Setter String nombre;

	@Column(name = "ap_paterno", length = 45)
	private @Getter @Setter String apPaterno;

	@Column(name = "ap_materno", length = 45)
	private @Getter @Setter String apMaterno;

	@Column(name = "contraseña", length = 45)
	private @Getter @Setter String contrasena;

	@Column(name = "activo")
	private @Getter @Setter Byte activo;

	@Column(name = "tipo", length = 45)
	private @Getter @Setter String tipo;
}
