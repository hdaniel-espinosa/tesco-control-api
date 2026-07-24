package mx.edu.tecnologicodecoacalco.tescocontrolapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@Table(name = "usuario_materia")
@IdClass(UsuarioMateriaId.class)
public class UsuarioMateria {

	@Id
	@Column(name = "id_usuario")
	private @Getter @Setter Integer idUsuario;

	@Id
	@Column(name = "id_materia")
	private @Getter @Setter Integer idMateria;
}
