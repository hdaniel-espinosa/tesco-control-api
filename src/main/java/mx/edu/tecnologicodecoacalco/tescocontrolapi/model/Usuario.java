package mx.edu.tecnologicodecoacalco.tescocontrolapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private @Getter @Setter Integer idUsuario;

	@Column(name = "nombre", length = 50)
	private @Getter @Setter String nombre;

	@Column(name = "ap_paterno", length = 50)
	private @Getter @Setter String apPaterno;

	@Column(name = "ap_materno", length = 50)
	private @Getter @Setter String apMaterno;

	@Column(name = "correo", length = 50)
	private @Getter @Setter String correo;

	@Column(name = "telefono", length = 15)
	private @Getter @Setter String telefono;

	@Column(name = "activo")
	private @Getter @Setter Boolean activo;

	@Column(name = "tipo_usuario", length = 50)
	private @Getter @Setter String tipoUsuario;
}
