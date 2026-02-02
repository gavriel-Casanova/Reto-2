package Cine.modelo.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Cine.modelo.pojo.Cliente;
import Cine.modelo.utils.DBUtils;

public class GestorCliente {

	/**
	 * obtiene todos los clientes de la base de datos
	 * @return una lista de clientes
	 */
	public ArrayList<Cliente> getAllClientes() {
		ArrayList<Cliente> ret = null;

		String sql = "select * from cliente";

		Connection connection = null;

		Statement statement = null;
		ResultSet resultSet = null;

		try {

			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				if (null == ret)
					ret = new ArrayList<Cliente>();

				Cliente cliente = new Cliente();

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

				ret.add(cliente);
			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {

			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {

			}
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {

			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {

			}
		}
		return ret;
	}

	/**
	 * inserta un cliente en la base de datos
	 * @param log un objeto cliente
	 */
	public void insert(Cliente log) { //

		Connection connection = null;

		Statement statement = null;

		try {

			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();

			String sql = "INSERT INTO cliente ( dni ,  nombre ,  apellido , correo_electronico ,  contrasenia ) VALUES (?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, log.getDNI());
			ps.setString(2, log.getNombre());
			ps.setString(3, log.getApellido());
			ps.setString(4, log.getCorreo_electronico());
			ps.setString(5, log.getContrasenia());

			ps.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {

			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {

			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {

			}
		}
	}
}
