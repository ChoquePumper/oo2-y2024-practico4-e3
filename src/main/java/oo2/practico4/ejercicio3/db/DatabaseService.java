package oo2.practico4.ejercicio3.db;

import oo2.practico4.ejercicio3.modelo.Concurso;
import oo2.practico4.ejercicio3.modelo.Concursos;
import oo2.practico4.ejercicio3.modelo.Inscripciones;
import oo2.practico4.ejercicio3.modelo.Inscripcion;

import java.util.List;

public class DatabaseService implements Concursos, Inscripciones {

	private ConcursoDAOJDBC concursos;
	private InscripcionDAOJDBC inscripciones;

	public DatabaseService(String subprotocolo, String subnombre, String user, String password) {
		this.concursos = new ConcursoDAOJDBC(subprotocolo, subnombre, user, password);
		this.inscripciones = new InscripcionDAOJDBC(subprotocolo, subnombre, user, password);
	}

	public DatabaseService(DBParams params) {
		this(params.subprotocolo(), params.subnombre(), params.user(), params.password());
	}

	@Override
	public List<Concurso> obtenerConcursos() {
		return concursos.findAll();
	}

	@Override
	public void registrarInscripcion(Inscripcion inscripcion) {
		inscripciones.create(inscripcion);
	}
}
