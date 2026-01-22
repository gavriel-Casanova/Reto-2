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
					ret = new ArrayList<Compra>();

				// El Alumno
				Compra compra = new Compra();

				// Sacamos las columnas del resultSet
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
				
				
				// Lo guardamos en la lista
				ret.add(compra);
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
	
	public void insert(Compra log) {
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
			String sql = "INSERT INTO compra (descuento_total, precio_total, dni) VALUES ( ?, ?, ? )";
			PreparedStatement ps = connection.prepareStatement(sql); 
			
			
			ps.setDouble(1, log.getDescuento_total());
			ps.setDouble(2, log.getPrecio_total());
			ps.setString(3, log.getDNI());
			
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
	
	public Compra getCompraByDNIAndFecha(Compra compra) {
		Compra ret = null;

		// SQL que queremos lanzar
		String sql = "SELECT * FROM `compra` WHERE `dni` = '"+compra.getDNI()+"' order by fecha_hora_compra DESC limit 1";
		

		// La conexion con BBDD
		Connection connection = null;

		// Vamos a lanzar una sentencia SQL contra la BBDD
		// Result set va a contener todo lo que devuelve la BBDD
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
			if (resultSet.next()) {

				ret = new Compra();
				
				// Sacamos las columnas del resultSet
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
}
