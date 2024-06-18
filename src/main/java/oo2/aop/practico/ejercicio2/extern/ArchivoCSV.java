package oo2.aop.practico.ejercicio2.extern;

import com.opencsv.CSVWriter;
import oo2.aop.practico.ejercicio2.RecordInvocacion;
import oo2.aop.practico.ejercicio2.Registro;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
				String.join("|", registro.params()),
				registro.fechaHora().toString(),
		};

		// Escribir en archivo
		try (var writer = new CSVWriter(fwriter)) {
			writer.writeNext(linea);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
