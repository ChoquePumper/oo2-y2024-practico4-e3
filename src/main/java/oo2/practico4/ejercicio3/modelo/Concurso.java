package oo2.practico4.ejercicio3.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Predicate;

public class Concurso {
	private final String idConcurso;
	private final String nombre;
	private final LocalDate fechaInicioInscripcion;
	private final LocalDate fechaFinInscripcion;

	public Concurso(String idConcurso, String nombre, LocalDate fechaInicioInscripcion, LocalDate fechaFinInscripcion) {
		this.idConcurso = validarNoVacio(idConcurso, "idConcurso");
		this.nombre = validarNoVacio(nombre, "nombre");
		this.fechaInicioInscripcion = validarNoNulo(fechaInicioInscripcion, "fechaInicioInscripcion");
		this.fechaFinInscripcion = validarNoNulo(fechaFinInscripcion, "fechaFinInscripcion");
	}

	private static boolean validarString(String s) {
		return !s.trim().isEmpty();
	}

	private static String validarNoVacio(String s, String nombreCampo) {
		return validar(validarNoNulo(s, nombreCampo),
				String.format("'%s' no puede estar vacío.", nombreCampo),
				Concurso::validarString)
				.trim();
	}

	private static <T> T validarNoNulo(T obj, String nombreCampo) {
		return validar(obj,
				String.format("'%s' no puede ser nulo.", nombreCampo),
				Objects::nonNull);
	}

	private static <T> T validar(T obj, String mensajeDeError, Predicate<T> predicate) {
		Objects.requireNonNull(mensajeDeError);
		Objects.requireNonNull(predicate);
		if (!predicate.test(obj))
			throw new RuntimeException(mensajeDeError);
		return obj;
	}

	public String getIdConcurso() {
		return idConcurso;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean enPeriodoDeInscripcion(LocalDateTime ahora) {
		return fechaInicioInscripcion.atTime(0, 0).isBefore(ahora) && ahora.isBefore(fechaFinInscripcion.atTime(0, 0));
	}
}
