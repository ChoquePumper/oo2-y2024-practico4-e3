package oo2.practico4.ejercicio3.externo;

import com.opencsv.CSVWriter;
import oo2.practico4.ejercicio3.modelo.Inscripciones;
import oo2.practico4.ejercicio3.modelo.Inscripcion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivoInscripciones implements Inscripciones {
	private static final String[] campos = {"apellido", "nombre", "teléfono", "email", "idconcurso"};
	private final File archivo;

	public ArchivoInscripciones(String nombreArchivo) {
		this.archivo = new File(nombreArchivo);
		try {
			crearArchivoSiNoExiste();
		} catch (IOException e) {
			throw new RuntimeException("No se puede crear el archivo", e);
		}
		if (!this.archivo.canRead())
			throw new RuntimeException("No se puede leer el archivo");
	}

	private void crearArchivoSiNoExiste() throws IOException {
		if (this.archivo.createNewFile()) {
			// Archivo nuevo creado.
			try (var writer = new CSVWriter(new FileWriter(archivo))) {
				// Escribe el encabezado
				writer.writeNext(campos);
			}
		}
	}

	@Override
	public void registrarInscripcion(Inscripcion inscripcion) {
		try (var writer = new CSVWriter(new FileWriter(archivo, true))) {
			String[] linea = {
					inscripcion.getApellido(),
					inscripcion.getNombre(),
					inscripcion.getTelefono().toString(),
					inscripcion.getEmail().toString(),
					inscripcion.getConcurso().getIdConcurso(),
			};
			writer.writeNext(linea);
		} catch (IOException e) {
			throw new RuntimeException("No se pudo guardar el participante", e);
		}
	}
}
