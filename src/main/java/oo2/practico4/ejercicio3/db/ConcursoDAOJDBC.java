package oo2.practico4.ejercicio3.db;

import oo2.practico4.ejercicio3.modelo.Concurso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConcursoDAOJDBC extends ObjetoJDBC implements ObjetoDAO<Concurso> {

	public ConcursoDAOJDBC(String subprotocolo, String subnombre) {
		super(subprotocolo, subnombre);
		//TODO Auto-generated constructor stub
	}

	public ConcursoDAOJDBC(String subprotocolo, String subnombre, String user, String password) {
		super(subprotocolo, subnombre, user, password);
	}

	@Override
	public void create(Concurso obj) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'create'");
	}

	@Override
	public void update(Concurso obj) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'update'");
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'remove'");
	}

	@Override
	public void remove(Concurso obj) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'remove'");
	}

	@Override
	public Concurso find(String id) {
		try (Connection conn = getConnection();
		     PreparedStatement st = conn.prepareStatement("SELECT idConcurso, nombre, fechaInicioInscripcion, fechaFinInscripcion FROM Concursos WHERE idConcurso = ? ;");
		     ResultSet res = setearDatos(st, id).executeQuery()) {
			if (res.next())
				return deResultSet(res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Concurso> findAll() {
		List<Concurso> lista = new ArrayList<>();
		try (Connection conn = getConnection();
		     PreparedStatement st = conn.prepareStatement("SELECT idConcurso, nombre, fechaInicioInscripcion, fechaFinInscripcion FROM Concursos;");
		     ResultSet res = st.executeQuery()) {
			while (res.next())
				lista.add(deResultSet(res));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	// Auxiliares

	/**
	 * Crea el objeto Concurso con los datos del ResultSet
	 *
	 * @param res ResultSet
	 * @return Concurso
	 * @throws SQLException
	 * @implNote Aún no sé que objetos poner para persistencia y proveedor_fecha.
	 */
	private Concurso deResultSet(ResultSet res) throws SQLException {
		return new Concurso(
				res.getString("idconcurso"),
				res.getString("nombre"),
				res.getObject("fechaInicioInscripcion", LocalDate.class),
				res.getObject("fechaFinInscripcion", LocalDate.class)
		);
	}
}
