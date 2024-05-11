package oo2.practico4.ejercicio3.modelo;

import java.util.List;
import java.util.Objects;

public class DefaultSistema implements Sistema {
	private final Concursos concursos;
	private final Inscripciones participantes;

	private List<Concurso> listaConcursos;

	public DefaultSistema(Concursos concursos, Inscripciones inscripciones) {
		this.concursos = Objects.requireNonNull(concursos);
		this.participantes = Objects.requireNonNull(inscripciones);
	}

	private boolean existeConcursoConId(String s) {
		return todosLosConcursos().stream()
				.anyMatch(concurso -> concurso.getIdConcurso().equals(s));
	}

	@Override
	public void guardarInscripcion(String nombre, String apellido, String dni, String email, String telefono, String idConcurso) {
		Participante p = new Participante(nombre, apellido, dni, new Email(email), new Telefono(telefono));

		// Verificar si el concurso con tal id existe
		idConcurso = Objects.requireNonNullElse(idConcurso, "").trim();
		if (idConcurso.isEmpty())
			throw new RuntimeException("Debe elegir un concurso");
		if (!existeConcursoConId(idConcurso))
			throw new RuntimeException("No existe el concurso");

		// Guardar
		participantes.guardarParticipante(p, idConcurso);
	}

	@Override
	public List<Concurso> todosLosConcursos() {
		if (listaConcursos == null)
			listaConcursos = concursos.obtenerConcursos();
		return listaConcursos;
	}
}
