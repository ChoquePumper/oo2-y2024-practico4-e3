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
	private final CSVWriter csvwriter;

	public ArchivoCSV(File archivo) throws IOException {
		this.archivo = archivo;
		// El archivo se mantendrá abierto.
		this.csvwriter = new CSVWriter(new FileWriter(archivo, true));
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
		try {
			csvwriter.writeNext(linea);
			// Debido a que el archivo se mantiene abierto:
			// para asegurar de que se escriba en el archivo, llamo a flush().
			csvwriter.flush();
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
