package oo2.practico4.ejercicio3.main;

import oo2.practico4.ejercicio3.db.ConcursosDBService;
import oo2.practico4.ejercicio3.db.DBParams;
import oo2.practico4.ejercicio3.externo.ArchivoConcursos;
import oo2.practico4.ejercicio3.externo.ArchivoInscripciones;
import oo2.practico4.ejercicio3.modelo.Concursos;
import oo2.practico4.ejercicio3.modelo.DefaultSistema;
import oo2.practico4.ejercicio3.modelo.Inscripciones;
import oo2.practico4.ejercicio3.modelo.Sistema;
import oo2.practico4.ejercicio3.ui.VentanaRadioCompetition;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Main().start();
				} catch (Exception e) {
					// log exception...
					System.out.println(e);
				}
			}
		});
	}

	private void start() throws URISyntaxException, IOException {
		// Carga propiedades desde un archivo
		Properties prop = new Properties();
		prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));

		//Concursos concursos = concursosPorArchivo();
		Concursos concursos = concursosPorBaseDeDatos(prop);
		Inscripciones inscripciones = inscripcionesPorArchivo();
		Sistema sistema = new DefaultSistema(concursos, inscripciones);
		new VentanaRadioCompetition(sistema);
	}

	private Concursos concursosPorArchivo() throws URISyntaxException {
		return new ArchivoConcursos(Thread.currentThread().getContextClassLoader().getResource("concursos.txt").toURI());
	}

	private Concursos concursosPorBaseDeDatos(Properties prop) {
		return new ConcursosDBService(parametrosDB(prop));
	}

	private Inscripciones inscripcionesPorArchivo() {
		return new ArchivoInscripciones("inscriptos.txt");
	}

	private DBParams parametrosDB(Properties prop) {
		String subprotocolo = prop.getProperty("jdbc.subprotocol"), subnombre = prop.getProperty("jdbc.subname.tp4e3");
		String user = prop.getProperty("jdbc.user"), password = prop.getProperty("jdbc.password");
		return new DBParams(subprotocolo, subnombre, user, password);
	}
}
