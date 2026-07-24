package mx.edu.tecnologicodecoacalco.tescocontrolapi.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Nombre del día de la semana en español, con la misma capitalización que
 * se guarda en la columna "dia" de horario (p. ej. "Lunes"), para poder
 * comparar contra la hora actual del servidor.
 */
public final class DiaSemanaUtil {

	private DiaSemanaUtil() {
	}

	public static String nombreDia(LocalDateTime fechaHora) {
		DayOfWeek diaSemana = fechaHora.getDayOfWeek();
		String nombre = diaSemana.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es"));
		return nombre.substring(0, 1).toUpperCase(Locale.forLanguageTag("es")) + nombre.substring(1);
	}
}
