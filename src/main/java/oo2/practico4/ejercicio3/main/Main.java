package oo2.practico4.ejercicio3.main;

import oo2.practico4.ejercicio3.externo.ArchivoConcursos;
import oo2.practico4.ejercicio3.externo.ArchivoInscripciones;
import oo2.practico4.ejercicio3.modelo.Concursos;
import oo2.practico4.ejercicio3.modelo.DefaultSistema;
import oo2.practico4.ejercicio3.modelo.Inscripciones;
import oo2.practico4.ejercicio3.modelo.Sistema;
import oo2.practico4.ejercicio3.ui.VentanaRadioCompetition;

import javax.swing.*;
import java.net.URISyntaxException;

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

	private void start() throws URISyntaxException {
		Concursos concursos = new ArchivoConcursos(Thread.currentThread().getContextClassLoader().getResource("concursos.txt").toURI());
		Inscripciones inscripciones = new ArchivoInscripciones("inscriptos.txt");
		Sistema sistema = new DefaultSistema(concursos, inscripciones);
		new VentanaRadioCompetition(sistema);
	}
}
