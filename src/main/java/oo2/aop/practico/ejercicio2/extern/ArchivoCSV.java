package oo2.aop.practico.ejercicio2.extern;

import com.opencsv.CSVWriter;
import oo2.aop.practico.ejercicio2.RecordInvocacion;
import oo2.aop.practico.ejercicio2.Registro;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ArchivoCSV implements Registro {

	private final File archivo;
	private FileWriter fwriter;

	public ArchivoCSV(File archivo) throws IOException {
		this.archivo = archivo;
		this.fwriter = new FileWriter(archivo, true);
	}

	@Override
	public void guardar(RecordInvocacion registro) {
		// Generar linea
		String[] linea = {
				registro.nombre(),
				generarTextoParametros(registro.params()),
				generarTextoFechaHora(registro.fechaHora()),
		};

		// Escribir en archivo
		try (var writer = new CSVWriter(fwriter)) {
			writer.writeNext(linea);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static String generarTextoParametros(String[] params) {
		Objects.requireNonNull(params);

		if (params.length == 0)
			return "sin parámetros";

		return String.join("|", params);
	}

	private static String generarTextoFechaHora(LocalDateTime fechaHora) {
		Objects.requireNonNull(fechaHora);
		final DateTimeFormatter dtFmt = DateTimeFormatter.ofPattern("uuuu/MM/dd hh:mm:ss");
		return fechaHora.format(dtFmt);
	}
}
