package Cine.modelo.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Cine.modelo.pojo.Entrada;
import Cine.modelo.utils.DBUtils;


public class GestorEntrada {

	/**
	 * retorna una lista con todas las entradas
	 * @return lista de entrada
	 */
	public ArrayList<Entrada> getAllEntradas() {
		ArrayList<Entrada> ret = null;

		String sql = "select * from Entrada";

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
					ret = new ArrayList<Entrada>();

				Entrada entrada = new Entrada();

				int id_entrada = resultSet.getInt("id_entrada ");
				int id_sesion = resultSet.getInt("id_sesion");
				int num_personas = resultSet.getInt("num_personas");
				double descuento = resultSet.getDouble("descuento");
				double precio = resultSet.getDouble("precio");
				int id_compra = resultSet.getInt("id_compra");

				entrada.setId_entrada(id_entrada);
				entrada.setId_sesion(id_sesion);
				entrada.setNum_personas(num_personas);
				entrada.setDescuento(descuento);
				entrada.setPrecio(precio);
				entrada.setId_compra(id_compra);

				ret.add(entrada);
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
	 * inserta una entrada a la base de datos 
	 * @param log un objeto entrada
	 */
	public void insert(Entrada log) {

		Connection connection = null;

		Statement statement = null;

		try {

			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();

			String sql = "INSERT INTO entrada ( id_sesion ,  num_personas ,  descuento ,  precio ,  id_compra ) VALUES (?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setInt(1, log.getId_sesion());
			ps.setInt(2, log.getNum_personas());
			ps.setDouble(3, log.getDescuento());
			ps.setDouble(4, log.getPrecio());
			ps.setInt(5, log.getId_compra());

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
