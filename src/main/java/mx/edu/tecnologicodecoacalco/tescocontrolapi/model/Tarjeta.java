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
@Table(name = "tarjeta")
public class Tarjeta {

	@Id
	@Column(name = "id_tarjeta", length = 15)
	private @Getter @Setter String idTarjeta;

	@Column(name = "tipo", length = 50)
	private @Getter @Setter String tipo;

	@Column(name = "activa")
	private @Getter @Setter Boolean activa;
}
