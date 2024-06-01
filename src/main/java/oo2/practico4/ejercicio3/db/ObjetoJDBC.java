package oo2.practico4.ejercicio3.db;

import java.sql.*;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public abstract class ObjetoJDBC {
	private final String subprotocolo;
	private final String subnombre;
	private String user = "", password = "";

	private final String lineaUrlJDBC;
	// Ejemplo de UrlJDBC: "jdbc:mysql://servidor:3306/schema"

	/**
	 * Arma la linea JDBC que luego se usará al invocar {@code getConnection}
	 *
	 * @param subprotocolo : Nombre de subprotocolo.
	 *                     Ej: {@code "odbc"}, {@code "mysql"}, {@code "db2"}, {@code "oracle"}, {@code "postgresql"}.
	 * @param subnombre    : Nombre de servidor y/o base de datos. Dependiendo del subprotocolo, puede ser:
	 *                     <ul>
	 *                       <li>"database"</li>
	 *                       <li>"//host/database"</li>
	 *                       <li>"//host:port/database"</li>
	 *                     </ul>
	 */
	public ObjetoJDBC(String subprotocolo, String subnombre) {
		this.subprotocolo = validarDato(subprotocolo, ObjetoJDBC::validarSubprotocolo);
		this.subnombre = validarDato(subnombre, ObjetoJDBC::validarString);
		this.lineaUrlJDBC = String.format("jdbc:%s:%s", this.subprotocolo, this.subnombre);
	}

	public ObjetoJDBC(String subprotocolo, String subnombre, String user, String password) {
		this(subprotocolo, subnombre);
		this.user = validarDato(user, ObjetoJDBC::validarString);
		this.password = validarDato(password, ObjetoJDBC::validarString);
	}

	private static boolean validarString(String s) {
		return !(Objects.requireNonNull(s).isBlank());
	}

	private static boolean validarSubprotocolo(String subprotocolo) {
		return validarString(subprotocolo) && !subprotocolo.contains(":");
	}

	private static <T> T validarDato(T obj, Predicate<T> validador) {
		if (!validador.test(obj))
			throw new IllegalArgumentException();
		return obj;
	}

	private static boolean esNombreValido(String nombre) {
		return Pattern.matches("[a-zA-Z0-9]", nombre);
	}

	private boolean conUserPassword() {
		return validarString(this.user) && validarString(this.password);
	}

	/**
	 * Llamar a {@code DriverManager.getConnection()}
	 */
	protected Connection getConnection() throws SQLException {
		if (conUserPassword())
			return getConnection(user, password);
		return DriverManager.getConnection(lineaUrlJDBC);
		//return getConnection("root", "");
	}

	protected Connection getConnection(String user, String password) throws SQLException {
		return DriverManager.getConnection(lineaUrlJDBC, user, password);
	}

	/**
	 * Invoca a los métodos set correspondientes de {@code PreparedStatement} según los tipos
	 */
	protected static PreparedStatement setearDatos(PreparedStatement statement, Object... objects) throws SQLException {
		int indice = 1;
		// Mapa de algunos tipos de datos para PreparedStatement. Podría agregar más.
		final Map<Class<?>, StatementSetData> tipos = Map.of(
				String.class, (i, o) -> statement.setString(i, (String) o),
				Boolean.class, (i, o) -> statement.setBoolean(i, (Boolean) o),
				Integer.class, (i, o) -> statement.setObject(i, (Integer) o, Types.INTEGER),
				Long.class, (i, o) -> statement.setObject(i, (Long) o, Types.INTEGER),
				Float.class, (i, o) -> statement.setObject(i, (Float) o, Types.FLOAT),
				Double.class, (i, o) -> statement.setObject(i, (Double) o, Types.DOUBLE),
				Date.class, (i, o) -> statement.setDate(i, (Date) o)
		);
		final StatementSetData setNull = (i, o) -> statement.setNull(i, Types.NULL);
		final StatementSetData setDefault = (i, o) -> statement.setObject(i, o);
		for (Object obj : objects) {
			StatementSetData metodo = setNull;
			if (obj != null)
				metodo = tipos.getOrDefault(obj.getClass(), setDefault);
			metodo.setData(indice++, obj);
		}
		return statement;
	}

	protected static Integer longAInteger(Long l) {
		if (l == null)
			return null;
		return l.intValue();
	}

	protected boolean esDuplicado(SQLIntegrityConstraintViolationException e) {
		return (e.getSQLState().startsWith(MARIADB_CLASSCODE_23)) &&
				(e.getErrorCode() == MARIADB_ER_DUP_ENTRY);
	}

	// Algunos códigos:
	static final String MARIADB_CLASSCODE_23 = "23";
	static final int MARIADB_ER_DUP_ENTRY = 1062;
}

@FunctionalInterface
interface StatementSetData {
	void setData(int i, Object o) throws SQLException;
}
