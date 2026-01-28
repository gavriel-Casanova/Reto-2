package Cine.modelo.gestores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Cine.modelo.pojo.Compra;
import Cine.modelo.utils.DBUtils;

public class GestorCompra {

	public ArrayList<Compra> getAllCompras() {
		ArrayList<Compra> ret = null;

		String sql = "select * from compra";

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
					ret = new ArrayList<Compra>();

				Compra compra = new Compra();

				int id_compra = resultSet.getInt("id_compra");
				Date fecha_hora_compra = resultSet.getDate("fecha_hora_compra");
				double descuento_total = resultSet.getDouble("descuento_total");
				double precio_total = resultSet.getDouble("precio_total");
				String DNI = resultSet.getString("dni");

				compra.setId_compra(id_compra);
				compra.setFecha_hora_compra(fecha_hora_compra);
				compra.setDescuento_total(descuento_total);
				compra.setPrecio_total(precio_total);
				compra.setDNI(DNI);

				ret.add(compra);
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

	public void insert(Compra log) {

		Connection connection = null;

		Statement statement = null;

		try {

			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();

			String sql = "INSERT INTO compra (descuento_total, precio_total, dni) VALUES ( ?, ?, ? )";
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setDouble(1, log.getDescuento_total());
			ps.setDouble(2, log.getPrecio_total());
			ps.setString(3, log.getDNI());

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

	public Compra getCompraByDNIAndFecha(Compra compra) {
		Compra ret = null;

		String sql = "SELECT * FROM `compra` WHERE `dni` = '" + compra.getDNI()
				+ "' order by fecha_hora_compra DESC limit 1";

		Connection connection = null;

		Statement statement = null;
		ResultSet resultSet = null;

		try {

			Class.forName(DBUtils.DRIVER);

			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {

				ret = new Compra();

				int id_compra = resultSet.getInt("id_compra");
				Date fecha_hora_compra = resultSet.getDate("fecha_hora_compra");
				double descuento_total = resultSet.getDouble("descuento_total");
				double precio_total = resultSet.getDouble("precio_total");
				String DNI = resultSet.getString("dni");

				ret.setId_compra(id_compra);
				ret.setFecha_hora_compra(fecha_hora_compra);
				ret.setDescuento_total(descuento_total);
				ret.setPrecio_total(precio_total);
				ret.setDNI(DNI);

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

