package oo2.practico4.ejercicio3.externo;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import oo2.practico4.ejercicio3.modelo.Concurso;
import oo2.practico4.ejercicio3.modelo.Concursos;

import java.io.*;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class ArchivoConcursos implements Concursos {
	private static final String[] campos = {"idconcurso", "nombre", "fechaInicioInscripcion", "fechaFinInscripcion"};
	private final File archivo;

	public ArchivoConcursos(String nombreArchivo) {
		this.archivo = new File(nombreArchivo);
		//if (!this.archivo.canRead())
		//	throw new RuntimeException("No se puede leer el archivo");
	}

	public ArchivoConcursos(URI uri) {
		this.archivo = new File(uri);
	}

	private static LocalDate fecha(String s) {
		return LocalDate.parse(s, DateTimeFormatter.ofPattern("uuuu/MM/dd"));
	}

	@Override
	public List<Concurso> obtenerConcursos() {
		try (var reader = new CSVReader(new FileReader(archivo))) {
			// Descartar encabezado
			reader.readNext();
			return reader.readAll().stream()
					// Recortar espacios de cada campo
					.peek(linea -> {
						Arrays.setAll(linea, i -> linea[i].trim());
					})
					// Crear objetos Concurso
					.map((linea) -> {
						return new Concurso(linea[0], linea[1], fecha(linea[2]), fecha(linea[3]));
					}).toList();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("No se encuentra el archivo", e);
		} catch (IOException e) {
			throw new RuntimeException("Fallo al leer el archivo", e);
		}
	}
}
