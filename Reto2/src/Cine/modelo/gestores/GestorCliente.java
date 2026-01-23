package Cine.modelo.gestores;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import Cine.modelo.pojo.Cliente;
import Cine.modelo.pojo.Compra;
import Cine.modelo.utils.DBUtils;

public class GestorCliente {

	public ArrayList<Cliente> getAllClientes() {
		ArrayList<Cliente> ret = null;

		String sql = "select * from cliente";

		
		Connection connection = null;

		
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);

			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			// Vamos a lanzar la sentencia...
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			// Recorremos resultSet, que tiene las filas de la tabla
			while (resultSet.next()) {

				// Hay al menos una fila en el cursos, inicializamos el ArrayList
				if (null == ret)
					ret = new ArrayList<Cliente>();

				// El Alumno
				Cliente cliente = new Cliente();

				// Sacamos las columnas del resultSet
				String DNI = resultSet.getString("dni");
				String nombre = resultSet.getString("nombre");
				String apellido = resultSet.getString("apellido");
				String correo_electronico = resultSet.getString("correo_electronico");
				String contrasenia = resultSet.getString("contrasenia");

				cliente.setDNI(DNI);
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setCorreo_electronico(correo_electronico);
				cliente.setContrasenia(contrasenia);
				
				// Lo guardamos en la lista
				ret.add(cliente);
			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
		}
		return ret;
	}
	
	public void insert(Cliente log) {
		// La conexion con BBDD
		Connection connection = null;

		// Vamos a lanzar una sentencia SQL contra la BBDD
		Statement statement = null;

		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);

			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			// Vamos a lanzar la sentencia...
			statement = connection.createStatement();

			// Montamos la SQL. Esta es una forma simple de hacerlo, hay otra mejor...
			String sql = "INSERT INTO cliente ( dni ,  nombre ,  apellido , correo_electronico ,  contrasenia ) VALUES (?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql); 
			
			
			ps.setString(1, log.getDNI());
			ps.setString(2, log.getNombre());
			ps.setString(3, log.getApellido());
			ps.setString(4, log.getCorreo_electronico());
			ps.setString(5, log.getContrasenia());
			
			// La ejecutamos...
			ps.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
		}
	}
}
