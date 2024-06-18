package oo2.aop.practico.ejercicio2;

import java.time.LocalDateTime;
import java.util.Objects;

public record RecordInvocacion(String nombre, String[] params, LocalDateTime fechaHora) {

	public RecordInvocacion {
		Objects.requireNonNull(nombre);
		Objects.requireNonNull(params);
		Objects.requireNonNull(fechaHora);
	}
}
