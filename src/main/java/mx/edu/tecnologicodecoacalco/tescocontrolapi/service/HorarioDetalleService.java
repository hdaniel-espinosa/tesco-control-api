package mx.edu.tecnologicodecoacalco.tescocontrolapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Horario;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Laboratorio;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Materia;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.UsuarioMateria;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.LaboratorioDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.MateriaDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.UsuarioDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.UsuarioMateriaDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.rest.dto.HorarioDetalleDto;

/** Enriquece un Horario con los nombres de laboratorio, materia y maestro(s) que la imparten. */
@Service
@RequiredArgsConstructor
public class HorarioDetalleService {

	private final LaboratorioDao laboratorioDao;
	private final MateriaDao materiaDao;
	private final UsuarioMateriaDao usuarioMateriaDao;
	private final UsuarioDao usuarioDao;

	public HorarioDetalleDto aDetalle(Horario horario) {
		String nombreLaboratorio = laboratorioDao.findById(horario.getIdLaboratorio()).map(Laboratorio::getNombre)
				.orElse(null);
		Materia materia = materiaDao.findById(horario.getIdMateria()).orElse(null);
		String nombreMateria = materia != null ? materia.getNombre() + " (" + materia.getGrupo() + ")" : null;
		List<String> maestros = usuarioMateriaDao.findByIdMateria(horario.getIdMateria()).stream()
				.map(UsuarioMateria::getIdUsuario).map(usuarioDao::findById).filter(Optional::isPresent)
				.map(Optional::get).map(usuario -> usuario.getNombre() + " " + usuario.getApPaterno()).toList();

		return new HorarioDetalleDto(horario.getIdHorario(), horario.getDia(), horario.getHoraInicio(),
				horario.getHoraTermino(), horario.getIdLaboratorio(), nombreLaboratorio, horario.getIdMateria(),
				nombreMateria, maestros);
	}

	public List<HorarioDetalleDto> aDetalle(List<Horario> horarios) {
		return horarios.stream().map(this::aDetalle).toList();
	}
}
