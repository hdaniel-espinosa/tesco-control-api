package mx.edu.tecnologicodecoacalco.tescocontrolapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="usuario_sistema")
public class UsuarioSistema {
	@Id
	@Column(name="id_usuario_sistema", unique = true)
	private @Getter @Setter Integer idUsuarioSistema;
	
	@Column(name ="nombre")
	private @Getter @Setter String nombre;
	
	@Column(name="ap_paterno")
	private @Getter @Setter String apPaterno;
	
	@Column(name="ap_materno")
	private @Getter @Setter String apMaterno;
	
	@Column(name ="contraseña")
	private @Getter @Setter String contrasena;
	
	@Column(name="activo")
	private @Getter @Setter Byte activo;
	
	@Column(name="tipo")
	private @Getter @Setter String tipo;
	
	
}
