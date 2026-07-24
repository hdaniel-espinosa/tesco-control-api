package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.rest.dto.HorarioProximoDto;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.rest.dto.LaboratorioEstadoDto;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.service.DashboardService;

/** Datos agregados para el dashboard: ocupación actual de laboratorios y horarios por comenzar. */
@RestController
@RequestMapping(value = "/tesco-control-api/dashboard")
@RequiredArgsConstructor
public class DashboardRestController {

	private final DashboardService dashboardService;

	@GetMapping("/laboratorios")
	public List<LaboratorioEstadoDto> recuperarEstadoLaboratorios() {
		return dashboardService.obtenerEstadoLaboratorios();
	}

	@GetMapping("/horarios-proximos")
	public List<HorarioProximoDto> recuperarHorariosProximos(
			@RequestParam(name = "limite", defaultValue = "5") int limite) {
		return dashboardService.obtenerHorariosProximos(limite);
	}
}
