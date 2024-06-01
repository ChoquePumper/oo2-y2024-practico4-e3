package oo2.practico4.ejercicio3.db;

import oo2.practico4.ejercicio3.modelo.Concurso;
import oo2.practico4.ejercicio3.modelo.Concursos;

import java.util.List;

public class ConcursosDBService implements Concursos {

	private ConcursoDAOJDBC daojdbc;

	public ConcursosDBService(String subprotocolo, String subnombre, String user, String password) {
		this.daojdbc = new ConcursoDAOJDBC(subprotocolo, subnombre, user, password);
	}

	public ConcursosDBService(DBParams params) {
		this(params.subprotocolo(), params.subnombre(), params.user(), params.password());
	}

	@Override
	public List<Concurso> obtenerConcursos() {
		return daojdbc.findAll();
	}
}
