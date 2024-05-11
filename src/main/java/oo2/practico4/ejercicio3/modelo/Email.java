package oo2.practico4.ejercicio3.modelo;

import java.util.Objects;

public class Email {
	private final String email;

	public Email(String emailString) {
		// Cambiar null por un string vacío o recortar espacios.
		emailString = Objects.requireNonNullElse(emailString, "").trim();

		// Tirar excepción si el string está en blanco.
		if (emailString.isBlank())
			throw new IllegalArgumentException("No se ingresó un email.");

		// Validar formato de email
		if (esValido(emailString))
			throw new IllegalArgumentException("Formato de email incorrecto");

		this.email = emailString;
	}

	private static boolean esValido(String emailString) {
		final String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return !emailString.matches(regex);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Email email1)) return false;
		return Objects.equals(email, email1.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public String toString() {
		return this.email;
	}
}
