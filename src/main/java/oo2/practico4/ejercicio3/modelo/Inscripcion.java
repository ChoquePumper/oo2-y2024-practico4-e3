package oo2.practico4.ejercicio3.modelo;

import java.util.Objects;
import java.util.function.Predicate;

public class Inscripcion {
	private final String nombre, apellido;
	private final String dni;
	private final Email email;
	private final Telefono telefono;
	private final Concurso concurso;

	Inscripcion(String nombre, String apellido, String dni, Email email, Telefono telefono, Concurso concurso) {
		this.nombre = validarNoVacio(nombre, "nombre");
		this.apellido = validarNoVacio(apellido, "apellido");
		this.dni = validarNoVacio(dni, "dni");
		this.email = validarNoNulo(email, "email");
		this.telefono = validarNoNulo(telefono, "telefono");
		this.concurso = validarNoNulo(concurso, "concurso");
	}

	private static boolean validarString(String s) {
		return !s.trim().isEmpty();
	}

	private static String validarNoVacio(String s, String nombreCampo) {
		return validar(validarNoNulo(s, nombreCampo),
				String.format("'%s' no puede estar vacío.", nombreCampo),
				Inscripcion::validarString)
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

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDni() {
		return dni;
	}

	public Email getEmail() {
		return email;
	}

	public Telefono getTelefono() {
		return telefono;
	}

	public Concurso getConcurso() {
		return concurso;
	}
}
