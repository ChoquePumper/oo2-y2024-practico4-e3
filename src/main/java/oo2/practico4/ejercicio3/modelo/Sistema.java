package oo2.practico4.ejercicio3.modelo;

import java.util.List;

public interface Sistema {
	void guardarInscripcion(String nombre, String apellido, String dni, String email, String telefono, String idConcurso);

	List<Concurso> todosLosConcursos();
}
