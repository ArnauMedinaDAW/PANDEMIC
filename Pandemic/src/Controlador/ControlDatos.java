package Controlador;

import Model.*;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.*;

public class ControlDatos {

	// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
	//Remplazar URL -> jdbc:oracle:thin:@oracle.ilerna.com:1521:xe
	private static final String URL = "jdbc:oracle:thin:@192.168.3.26:1521:xe";
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
			}finally {
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
			}finally {
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

	public static void guardarEstadoCiudades() {
		Connection con = conectarBaseDatos();
		String queryDelete = "DELETE FROM CIUDADES_PARTIDA WHERE usuario = ?";
	    String queryInsert = "INSERT INTO CIUDADES_PARTIDA (usuario, ciudad) VALUES (?, ?)";
	    PreparedStatement psDelete = null;
	    PreparedStatement psInsert = null;

	    try {
	    	// Eliminar registros existentes para el usuario
	        psDelete = con.prepareStatement(queryDelete);
	        psDelete.setString(1, ControlDatos.user_partida);//ControlDatos.user_partida
	        psDelete.executeUpdate();

	        // Insertar nuevos registros
	        psInsert = con.prepareStatement(queryInsert);
	        for (int i = 0; i < DatosPartida.ciutats.size(); i++) {
	            Struct struct = con.createStruct("CIUDADES", new Object[] {
	                    DatosPartida.ciutats.get(i).getNombre(), DatosPartida.ciutats.get(i).getInfeccion() });
	            psInsert.setString(1, ControlDatos.user_partida);
	            psInsert.setObject(2, struct);
	            psInsert.addBatch(); // Agregar la inserción a un lote
	        }
	        psInsert.executeBatch(); // Ejecutar todas las inserciones en el lote

	        System.out.println("Inserción de datos de ciudades realizada correctamente");
	    } catch (SQLException e) {
	        System.out.println("Ha habido un error en la inserción de datos de ciudades: " + e.getMessage());
	    } finally {
			cerrarConexion(con, psInsert, null);
			cerrarConexion(con, psDelete, null);
	    }
	    
	}

	public static void cargarEstadoCiudades() {
	    Connection con = conectarBaseDatos();
		String querySelect = "SELECT ciudad.nom AS nom_ciudad, ciudad.nivell_infeccio AS nivell_infeccio FROM CIUDADES_PARTIDA WHERE usuario = ?";
	    PreparedStatement psSelect = null;
	    ResultSet rs = null;

	    try {
	        psSelect = con.prepareStatement(querySelect);
	        psSelect.setString(1, "1"); // Aquí debes colocar el usuario que corresponda
	        rs = psSelect.executeQuery();

	        // Recorrer el resultado y cargar los datos de las ciudades
	        while (rs.next()) {
	            String nombreCiudad = rs.getString("nom_ciudad");
	            System.out.println(rs);
	            int nivelInfeccion = rs.getInt("nivell_infeccio");
	            System.out.println(rs);
	        }

	        System.out.println("Carga de datos de ciudades realizada correctamente");
	    } catch (SQLException e) {
	        System.out.println("Ha habido un error al cargar los datos de las ciudades: " + e.getMessage());
	    } finally {
	        cerrarConexion(con, psSelect, rs);
	    }

	 
	}




	public void cargarVacunas(ArrayList<Vacunas> vacunas) {

	}

	public void cargarVirus(ArrayList<Virus> virus) {

	}

	public void guardarPartida(String usuario, int rondas, String estado, ArrayList<Ciudad> ciudades) {
		Connection con = conectarBaseDatos();
		PreparedStatement psPartida = null;
		PreparedStatement psCiudades = null;

		if (con != null) {
			try {
				// Insertar datos generales de la partida en la tabla PARTIDA
				String queryPartida = "INSERT INTO PARTIDA (usuario, rondas, estado) VALUES (?, ?, ?)";
				psPartida = con.prepareStatement(queryPartida, Statement.RETURN_GENERATED_KEYS);
				psPartida.setString(1, usuario);
				psPartida.setInt(2, rondas);
				psPartida.setString(3, estado);
				psPartida.executeUpdate();

				// Obtener el ID de la partida recién insertada
				ResultSet rs = psPartida.getGeneratedKeys();
				int idPartida = -1;
				if (rs.next()) {
					idPartida = rs.getInt(1);
				}

				// Insertar datos de las ciudades en la tabla CIUDADES_PARTIDA
				String queryCiudades = "INSERT INTO CIUDADES_PARTIDA (id_partida, nombre_ciudad, nivel_infeccion) VALUES (?, ?, ?)";
				psCiudades = con.prepareStatement(queryCiudades);
				for (Ciudad ciudad : ciudades) {
					psCiudades.setInt(1, idPartida);
					psCiudades.setString(2, ciudad.getNombre());
					psCiudades.setInt(3, ciudad.getInfeccion());
					psCiudades.executeUpdate();
				}

				System.out.println("Partida guardada correctamente.");
			} catch (SQLException e) {
				System.out.println("Error al guardar la partida: " + e.getMessage());
			} finally {
				cerrarConexion(con, psPartida, null);
				cerrarConexion(con, psCiudades, null);
			}
		}
	}

	public void guardarRecord(String usuario, int rondas, String resultado) {
		Connection con = conectarBaseDatos();
		PreparedStatement ps = null;

		try {
			// Consulta SQL para insertar un nuevo registro
			String insertQuery = "INSERT INTO PANDEMIC_RECORDS (usuario, rondas, fecha, resultado) VALUES (?, ?, SYSDATE, ?)";
			ps = con.prepareStatement(insertQuery);
			ps.setString(1, usuario);
			ps.setInt(2, rondas);
			ps.setString(3, resultado);
			ps.executeUpdate();
			System.out.println("Se ha registrado el récord de " + usuario + " con " + rondas + " rondas y resultado: "
					+ resultado);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Cerrar la conexión y los recursos
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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

}
