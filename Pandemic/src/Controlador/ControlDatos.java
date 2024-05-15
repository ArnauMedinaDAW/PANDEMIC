package Controlador;

import Model.*;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.math.BigDecimal;
import java.sql.*;

public class ControlDatos {

	// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
	// Remplazar URL -> jdbc:oracle:thin:@oracle.ilerna.com:1521:xe
	// jdbc:oracle:thin:@192.168.3.26:1521:xe
	private static final String URL = "jdbc:oracle:thin:@oracle.ilerna.com:1521:xe";
	private static final String user = "DW1_2324_OL_DAVID";
	private static final String password = "A47991504A";

	public static int numCiudadesInfectadasInicio;
	public static int numCuidadesInfectadasRonda;
	public static int numEnfermedadesActivasDerrota;
	public static int numBrotesDerrota;
	public static int pDesarrollo = 0;

	public static String dificulty = "Facil";
	public static String idioma = "Español";

	public static String user_partida;
	public static String ficheroTXT = "ciudades.txt";
	public static String ficheroBIN = "CCP.bin";
	public static String ficheroXML;

	public ControlDatos() {

		new DatosPartida();
		new ControlPartida();
	}

	public static Connection conectarBaseDatos() {
		Connection con = null;

		System.out.println("Intentando conectarse a la base de datos");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}

		System.out.println("Conectados a la base de datos");

		return con;
	}

	public static boolean iniciarSesion(String usuario, String contrasena) {
		Connection con = conectarBaseDatos();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean loginExitoso = false;

		if (con != null) {
			try {
				String query = "SELECT * FROM PANDEMIC_USUARIOS WHERE usuario = ? AND contrasena = ?";
				ps = con.prepareStatement(query);
				ps.setString(1, usuario);
				ps.setString(2, contrasena);
				rs = ps.executeQuery();

				if (rs.next()) {
					// Usuario encontrado, inicio de sesión exitoso
					loginExitoso = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al iniciar sesión: " + e.getMessage());
			} finally {
				cerrarConexion(con, ps, rs);
			}
		}

		return loginExitoso;
	}

	// Método en la clase ControlDatos para registrar un nuevo usuario
	public static boolean registrarUsuario(String usuario, String contrasena) {
		Connection con = conectarBaseDatos();
		PreparedStatement ps = null;
		boolean registroExitoso = false;

		if (con != null) {
			try {
				String query = "INSERT INTO PANDEMIC_USUARIOS (usuario, contrasena) VALUES (?, ?)";
				ps = con.prepareStatement(query);
				ps.setString(1, usuario);
				ps.setString(2, contrasena);

				int filasAfectadas = ps.executeUpdate();

				if (filasAfectadas > 0) {
					// Registro exitoso
					registroExitoso = true;
				}
			} catch (SQLException e) {
				System.out.println("Error al registrar usuario: " + e.getMessage());
			} finally {
				cerrarConexion(con, ps, null);
			}
		}

		return registroExitoso;
	}

	// Método para cerrar la conexión, PreparedStatement y ResultSet
	private static void cerrarConexion(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión: " + e.getMessage());
		}
	}

	/**
	 * Realiza una inserción en la base de datos.
	 *
	 * @param con Objeto Connection que representa la conexión a la base de datos.
	 * @param sql Sentencia SQL de inserción que hayais creado.
	 */

	public static void guardarJuego() {
		Connection con = conectarBaseDatos();

		// GUARDAR DATOS PARTIDA
		// Usuario para eliminar los registros
		String usuario = ControlDatos.user_partida;

		// Sentencia SQL de eliminación
		String sqlDelete = "DELETE FROM DATOS_PARTIDA WHERE usuario = '" + usuario + "'";

		// Ejecutar el DELETE
		BBDD.delete(con, sqlDelete);

		// Sentencia SQL de inserción
		String sqlInsert = "INSERT INTO DATOS_PARTIDA(usuario, rondas, brotes, dificultad) " + "VALUES ('" + usuario
				+ "','" + DatosPartida.rondas + "', '" + DatosPartida.brotes + "',  '" + ControlDatos.dificulty + "')";

		// Ejecutar el INSERT
		BBDD.insert(con, sqlInsert);

		// GUARDAR CIUDADES
		String queryDelete = "DELETE FROM CIUDADES_PARTIDA WHERE usuario = ?";
		String queryInsert = "INSERT INTO CIUDADES_PARTIDA (usuario, ciudad) VALUES (?, ?)";
		PreparedStatement psDelete = null;
		PreparedStatement psInsert = null;

		try {
			// Eliminar registros existentes para el usuario
			psDelete = con.prepareStatement(queryDelete);
			psDelete.setString(1, ControlDatos.user_partida);
			psDelete.executeUpdate();

			// Insertar nuevos registros
			psInsert = con.prepareStatement(queryInsert);
			for (int i = 0; i < DatosPartida.ciutats.size(); i++) {
				// Crear un objeto CIUDADES
				Struct struct = con.createStruct("CIUDADES", new Object[] { DatosPartida.ciutats.get(i).getNombre(),
						DatosPartida.ciutats.get(i).getInfeccion() });

				// Insertar el objeto CIUDADES y el usuario en la tabla CIUDADES_PARTIDA
				psInsert.setString(1, ControlDatos.user_partida);
				psInsert.setObject(2, struct);
				psInsert.addBatch(); // Agregar la inserción a un lote
			}
			psInsert.executeBatch(); // Ejecutar todas las inserciones en el lote

			System.out.println("Inserción de datos de ciudades realizada correctamente");
		} catch (SQLException e) {
			System.out.println("Ha habido un error en la inserción de datos de ciudades: " + e.getMessage());
		}

		// GUARDAR VACUNAS
		queryDelete = "DELETE FROM VACUNAS_PARTIDA WHERE usuario = ?";
		queryInsert = "INSERT INTO VACUNAS_PARTIDA (usuario, vacunas) VALUES (?, ?)";
		psDelete = null;
		psInsert = null;
		try {
			// Eliminar registros existentes para el usuario
			psDelete = con.prepareStatement(queryDelete);
			psDelete.setString(1, ControlDatos.user_partida);
			psDelete.executeUpdate();

			// Insertar nuevos registros
			psInsert = con.prepareStatement(queryInsert);
			for (int i = 0; i < DatosPartida.vacunas.size(); i++) {
				// Crear un objeto CIUDADES
				Struct struct = con.createStruct("VACUNAS", new Object[] { DatosPartida.vacunas.get(i).getNombre(),
						DatosPartida.vacunas.get(i).getPorcentaje() });

				// Insertar el objeto CIUDADES y el usuario en la tabla CIUDADES_PARTIDA
				psInsert.setString(1, ControlDatos.user_partida);
				psInsert.setObject(2, struct);
				psInsert.addBatch(); // Agregar la inserción a un lote
			}
			psInsert.executeBatch(); // Ejecutar todas las inserciones en el lote

			System.out.println("Inserción de datos de vacunas realizada correctamente");
		} catch (SQLException e) {
			System.out.println("Ha habido un error en la inserción de datos de vacunas: " + e.getMessage());
		}

	}

	public static void cargarJuego() {

		for (int i = 0; i < DatosPartida.ciutats.size(); i++) {

			DatosPartida.ciutats.get(i).infeccion = 0;
		}
		DatosPartida.numEnfermedadesActivas = 0;

		Connection con = conectarBaseDatos();

		// Usuario específico
		String usuarioEspecifico = user_partida;

		// CARGAR DATOS PARTIDA
		String sql = "SELECT rondas, brotes, dificultad FROM DATOS_PARTIDA WHERE usuario = ?";
		String[] elementosSeleccionados = { "rondas", "brotes", "dificultad" };
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, usuarioEspecifico);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				for (String elemento : elementosSeleccionados) {
					// Asignar valores específicos a cada columna
					if (elemento.equals("rondas")) {
						DatosPartida.rondas = rs.getInt(elemento);
						// System.out.println(DatosPartida.rondas);
					} else if (elemento.equals("brotes")) {
						DatosPartida.brotes = rs.getInt(elemento);
						// System.out.println(DatosPartida.brotes);
					} else if (elemento.equals("dificultad")) {
						dificulty = rs.getString(elemento);
						// System.out.println(ControlDatos.dificulty);
						cargarParametrosInicioCiudades();// Cargar los valores segun la dificultad
						 //System.out.println(ControlDatos.numCuidadesInfectadasRonda);
					}
				}
			} else {
				System.out.println("No se encontraron datos de partida para el usuario " + usuarioEspecifico);
			}
		} catch (SQLException ex) {
			System.out.println("Ha ocurrido un error al cargar los datos de partida: " + ex.getMessage());
		}

		// CARGAR LAS CIUDADES
		String querySelect = "SELECT usuario, TREAT(ciudad AS CIUDADES).nom AS nom_ciudad, "
				+ "TREAT(ciudad AS CIUDADES).nivell_infeccio AS nivell_infeccio " + "FROM CIUDADES_PARTIDA "
				+ "WHERE usuario = ?";

		PreparedStatement psSelect = null;
		ResultSet rs = null;
		int i = 0;
		try {
			psSelect = con.prepareStatement(querySelect);
			psSelect.setString(1, usuarioEspecifico);
			rs = psSelect.executeQuery();

			// Recorrer el resultado y cargar los datos de las ciudades
			while (rs.next()) {
				// Obtener los datos de la ciudad
				String nombreCiudad = rs.getString("nom_ciudad");
				int nivelInfeccion = rs.getInt("nivell_infeccio");

				// Actualizar los datos cargados
				DatosPartida.ciutats.get(i).infeccion = nivelInfeccion;
				
				i++;
			}
			contarInfecciones();

			System.out.println("Carga de datos de ciudades realizada correctamente");
		} catch (SQLException e) {
			System.out.println("Ha habido un error al cargar los datos de las ciudades: " + e.getMessage());
		}

		// CARGAR LAS VACUNAS
		querySelect = "SELECT usuario, TREAT(vacunas AS VACUNAS).nom_vacuna AS nom_vacuna, "
				+ "TREAT(vacunas AS VACUNAS).nivell_vacuna AS nivell_vacuna " + "FROM VACUNAS_PARTIDA "
				+ "WHERE usuario = ?";

		psSelect = null;
		rs = null;
		i = 0;
		try {
			psSelect = con.prepareStatement(querySelect);
			psSelect.setString(1, usuarioEspecifico);
			rs = psSelect.executeQuery();

			// Recorrer el resultado y cargar los datos de las ciudades
			while (rs.next()) {
				// Obtener los datos de la ciudad
				String nombreVacuna = rs.getString("nom_vacuna");
				int nivelInfeccion = rs.getInt("nivell_vacuna");

				// Actualizar los datos cargados
				DatosPartida.vacunas.get(i).setPorcentaje(nivelInfeccion);
				//System.out.println(DatosPartida.vacunas.get(i).getPorcentaje());
				i++;
			}
			

			System.out.println("Carga de datos de vacunas realizada correctamente");
		} catch (SQLException e) {
			System.out.println("Ha habido un error al cargar los datos de las vacunas: " + e.getMessage());
		}		finally {
			cerrarConexion(con, psSelect, rs);
		}
	}

	public void cargarVacunas(ArrayList<Vacunas> vacunas) {

	}

	public void cargarVirus(ArrayList<Virus> virus) {

	}

	public static void guardarRecord() {
		Connection con = conectarBaseDatos();

//		String sqlDelete = "DELETE FROM PANDEMIC_RECORDS WHERE usuario = '" + ControlDatos.user_partida + "'";
//
//		// Ejecutar el DELETE
//		BBDD.delete(con, sqlDelete);

		String sqlInsert = "INSERT INTO PANDEMIC_RECORDS (usuario, rondas, fecha, resultado, dificultad) VALUES ('"
				+ ControlDatos.user_partida + "','" + DatosPartida.rondas + "', SYSDATE, 'Victoria','"
				+ ControlDatos.dificulty + "')";

		// Ejecutar el INSERT
		BBDD.insert(con, sqlInsert);

	}

	public static void cargarParametrosInicioCiudades() {

		if (dificulty.equals("Facil")) {
			ficheroXML = "parametrosFacil.xml";
		}
		if (dificulty.equals("Normal")) {
			ficheroXML = "parametrosNormal.xml";
		}
		if (dificulty.equals("Dificil")) {
			ficheroXML = "parametrosDificil.xml";
		}
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(ficheroXML);

			// Normalitzar el document per manejar espais en blanc
			doc.getDocumentElement().normalize();
			// Obtindre la llista de nodes del document
			NodeList nodeList = doc.getDocumentElement().getChildNodes();
			// Recòrrer els nodes i atribuir el valor als atributs de DatosPartidas
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					String nodeName = node.getNodeName();
					int nodeValue = Integer.parseInt(node.getTextContent().trim());
					switch (nodeName) {
					case "numCiudadesInfectadasInicio":
						numCiudadesInfectadasInicio = nodeValue;
						break;
					case "numCuidadesInfectadasRonda":
						numCuidadesInfectadasRonda = nodeValue;
						break;
					case "numEnfermedadesActivasDerrota":
						numEnfermedadesActivasDerrota = nodeValue;
						break;
					case "numBrotesDerrota":
						numBrotesDerrota = nodeValue;
						break;
					case "vacunas":
						pDesarrollo = nodeValue;
						break;
					default:
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int getsetnumCiudadesInfectadasInicio() {
		return numCiudadesInfectadasInicio;
	}

	public void setnumCiudadesInfectadasInicio(int x) {
		numCiudadesInfectadasInicio = x;
	}

	public static float getpDesarrollo() {
		return pDesarrollo;
	}

	public void setpDesarrollo(int x) {
		pDesarrollo = x;
	}

	public static void contarInfecciones() {

		for (int i = 0; i < DatosPartida.ciutats.size(); i++) {

			if (DatosPartida.ciutats.get(i).infeccion != 0) {
				DatosPartida.numEnfermedadesActivas++;
			}

		}

	}

}
