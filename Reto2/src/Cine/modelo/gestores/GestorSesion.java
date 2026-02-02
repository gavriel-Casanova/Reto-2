
package Cine.modelo.gestores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Cine.modelo.pojo.Sesion;
import Cine.modelo.utils.DBUtils;

public class GestorSesion {
	/**
	 * devuelve una lista de todas las sesiones de la base de datos
	 * @return una lista de sesiones
	 */
	public ArrayList<Sesion> getAllSesion() {
		ArrayList<Sesion> ret = null;

		String sql = "select * from sesion";

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
					ret = new ArrayList<Sesion>();

				Sesion sesion = new Sesion(0, 0, null, null, 0, 0, 0);

				int id_sesion = resultSet.getInt("id_sesion");
				int id_sala = resultSet.getInt("id_sala");
				Date fecha_hora_inicio = resultSet.getDate("fecha_hora_inicio");
				Date fecha_hora_fin = resultSet.getDate("fecha_hora_fin");
				double precio = resultSet.getDouble("precio");
				int num_espectadores = resultSet.getInt("num_espectadores");
				int id_pelicula = resultSet.getInt("id_pelicula");

				sesion.setId_sesion(id_sesion);
				sesion.setId_sala(id_sala);
				sesion.setFecha_hora_inicio(fecha_hora_inicio);
				sesion.setFecha_hora_fin(fecha_hora_fin);
				sesion.setPrecio(precio);
				sesion.setNum_espectadores(num_espectadores);
				sesion.setId_pelicula(id_pelicula);

				ret.add(sesion);
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
	 * devuelve una lista de las sesiones correspondientes a una pelicula seleccionada
	 * @param idPelicula id de la pelicula seleccionada
	 * @return una lista de sesiones
	 */
	public ArrayList<Sesion> getSesionDePelicula(int idPelicula) {
		ArrayList<Sesion> ret = null;

		String sql = "SELECT * FROM `sesion` WHERE id_pelicula = " + idPelicula
				+ " and current_timestamp < fecha_hora_inicio order by fecha_hora_inicio";

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
					ret = new ArrayList<Sesion>();

				Sesion sesion = new Sesion(0, 0, null, null, 0, 0, 0);

				int id_sesion = resultSet.getInt("id_sesion");
				int id_sala = resultSet.getInt("id_sala");
				Date fecha_hora_inicio = resultSet.getDate("fecha_hora_inicio");
				Date fecha_hora_fin = resultSet.getDate("fecha_hora_fin");
				double precio = resultSet.getDouble("precio");
				int num_espectadores = resultSet.getInt("num_espectadores");
				int id_pelicula = resultSet.getInt("id_pelicula");

				sesion.setId_sesion(id_sesion);
				sesion.setId_sala(id_sala);
				sesion.setFecha_hora_inicio(fecha_hora_inicio);
				sesion.setFecha_hora_fin(fecha_hora_fin);
				sesion.setPrecio(precio);
				sesion.setNum_espectadores(num_espectadores);
				sesion.setId_pelicula(id_pelicula);

				ret.add(sesion);
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
	 * retorna una lista de las sesiones disponibles
	 * @return una lista de sesiones
	 */
	public ArrayList<Sesion> getSesionesDisponibles() {
		ArrayList<Sesion> ret = null;

		String sql = "select * from sesion where current_timestamp < fecha_hora_inicio order by fecha_hora_inicio";

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
					ret = new ArrayList<Sesion>();

				Sesion sesion = new Sesion(0, 0, null, null, 0, 0, 0);

				int id_sesion = resultSet.getInt("id_sesion");
				int id_sala = resultSet.getInt("id_sala");
				Date fecha_hora_inicio = resultSet.getDate("fecha_hora_inicio");
				Date fecha_hora_fin = resultSet.getDate("fecha_hora_fin");
				double precio = resultSet.getDouble("precio");
				int num_espectadores = resultSet.getInt("num_espectadores");
				int id_pelicula = resultSet.getInt("id_pelicula");

				sesion.setId_sesion(id_sesion);
				sesion.setId_sala(id_sala);
				sesion.setFecha_hora_inicio(fecha_hora_inicio);
				sesion.setFecha_hora_fin(fecha_hora_fin);
				sesion.setPrecio(precio);
				sesion.setNum_espectadores(num_espectadores);
				sesion.setId_pelicula(id_pelicula);

				ret.add(sesion);
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
}
