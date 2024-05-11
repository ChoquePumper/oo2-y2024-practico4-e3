package oo2.practico4.ejercicio3.modelo;

import java.util.Objects;

public class Telefono {
	private final String tel;

	public Telefono(String telefono) {
		// Cambiar null por un string vacío o recortar espacios.
		telefono = Objects.requireNonNullElse(telefono, "").trim();

		// Tirar excepción si el string está en blanco.
		if (telefono.isBlank())
			throw new IllegalArgumentException("Debe cargar un telefono");

		// Validar formato de telefono
		if (!esValido(telefono))
			throw new IllegalArgumentException("El teléfono debe ingresarse de la siguiente forma: NNNN-NNNNNN");

		this.tel = telefono;
	}

	private static boolean esValido(String telefono) {
		final String regex = "\\d{4}-\\d{6}"; // NNNN-NNNNNN
		return telefono.matches(regex);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Telefono telefono)) return false;
		return Objects.equals(tel, telefono.tel);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tel);
	}

	@Override
	public String toString() {
		return tel;
	}
}