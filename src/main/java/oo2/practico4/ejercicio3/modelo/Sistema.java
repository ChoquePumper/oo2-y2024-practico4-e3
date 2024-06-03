package oo2.practico4.ejercicio3.modelo;

import java.util.List;

public interface Sistema {
	void guardarInscripcion(String nombre, String apellido, String dni, String email, String telefono, String idConcurso);

	/**
	 * @return Lista de los concursos en los que aún se pueden inscribir.
	 */
	List<Concurso> verConcursosDisponibles();

	/**
	 * @return Lista de todos los concursos.
	 */
	List<Concurso> todosLosConcursos();
}
