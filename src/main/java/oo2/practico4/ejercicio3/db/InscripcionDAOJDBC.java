package oo2.practico4.ejercicio3.db;

import oo2.practico4.ejercicio3.modelo.Inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InscripcionDAOJDBC extends ObjetoJDBC implements ObjetoDAO<Inscripcion> {

	public InscripcionDAOJDBC(String subprotocolo, String subnombre) {
		super(subprotocolo, subnombre);
		//TODO Auto-generated constructor stub
	}

	public InscripcionDAOJDBC(String subprotocolo, String subnombre, String user, String password) {
		super(subprotocolo, subnombre, user, password);
	}

	@Override
	public void create(Inscripcion obj) {
		final String sql = "INSERT INTO Inscriptos (apellido, nombre, telefono, email, idconcurso) values (?,?,?,?,?);";
		Object[] valores = {obj.getApellido(), obj.getNombre(), obj.getTelefono().toString(), obj.getEmail().toString(), obj.getConcurso().getIdConcurso()};

		try (Connection conn = getConnection();
		     PreparedStatement sent = conn.prepareStatement(sql)) {
			setearDatos(sent, valores);
			sent.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Inscripcion obj) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'update'");
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'remove'");
	}

	@Override
	public void remove(Inscripcion obj) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'remove'");
	}

	@Override
	public Inscripcion find(String id) {
		throw new UnsupportedOperationException("Unimplemented method 'find'");
	}

	@Override
	public List<Inscripcion> findAll() {
		throw new UnsupportedOperationException("Unimplemented method 'findAll'");
	}
}
