package oo2.aop.practico.ejercicio2;

import oo2.aop.practico.ejercicio2.extern.ArchivoCSV;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
public class AspectRegistro {

	private Registro registro;

	AspectRegistro() {
		try {
			this.registro = new ArchivoCSV(new File("loginvocaciones.csv"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Before("execution(@oo2.practico4.ejercicio3.annotations.Log * *(..))")
	public void logGuardarInscripcion(JoinPoint jp) {
		LocalDateTime fechaHora = LocalDateTime.now();

		RecordInvocacion r = new RecordInvocacion(
				jp.getSignature().getName(),
				Arrays.stream(jp.getArgs()).toArray(String[]::new),
				fechaHora
		);

		registro.guardar(r);
	}
}
