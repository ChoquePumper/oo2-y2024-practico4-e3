package oo2.practico4.ejercicio3.db;

import oo2.practico4.ejercicio3.modelo.Concurso;
import oo2.practico4.ejercicio3.modelo.Concursos;
import oo2.practico4.ejercicio3.modelo.Inscripciones;
import oo2.practico4.ejercicio3.modelo.Participante;

import java.util.List;

public class DatabaseService implements Concursos, Inscripciones {

	private ConcursoDAOJDBC daojdbc;

	public DatabaseService(String subprotocolo, String subnombre, String user, String password) {
		this.daojdbc = new ConcursoDAOJDBC(subprotocolo, subnombre, user, password);
	}

	public DatabaseService(DBParams params) {
		this(params.subprotocolo(), params.subnombre(), params.user(), params.password());
	}

	@Override
	public List<Concurso> obtenerConcursos() {
		return daojdbc.findAll();
	}

	@Override
	public void guardarParticipante(Participante participante, String idConcurso) {
		// TODO: implementar
	}
}
