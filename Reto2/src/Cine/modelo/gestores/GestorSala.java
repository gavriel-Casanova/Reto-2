package Cine.modelo.gestores;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Cine.modelo.pojo.Sala;
import Cine.modelo.utils.DBUtils;

public class GestorSala {
	public ArrayList<Sala> getAllSala() {
		ArrayList<Sala> ret = null;

		// SQL que queremos lanzar
		String sql = "select * from sala";

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
			while (resultSet.next()) {

				// Hay al menos una fila en el cursos, inicializamos el ArrayList
				if (null == ret)
					ret = new ArrayList<Sala>();

				// El Sala
				Sala sala = new Sala();

				// Sacamos las columnas del resultSet
				int  id_sala = resultSet.getInt(" id_sala");
				String nombre = resultSet.getString("nombre");
				
				// Metemos los datos en Alumno
				sala.setId_sala(id_sala);
				sala.setNombre(nombre);
				
				// Lo guardamos en la lista
				ret.add(sala);
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

	public void insert(Sala sala) {
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
			String sql = "insert into sala (id_sala, nombre) VALUES ('" + sala.getId_sala() + "', '" + sala.getNombre() + "')";

			// La ejecutamos...
			statement.executeUpdate(sql);

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

	public Sala getId_salaById(int idABuscar) {
		Sala ret = null;

		// SQL que queremos lanzar
		String sql = "select * from sala where id = '" + idABuscar + "'";

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

				ret = new Sala();
				
				// Sacamos las columnas del resultSet
				int  id_sala = resultSet.getInt("id_sala");
				String nombre = resultSet.getString("nombre");
				

				// Metemos los datos en Alumno
				ret.setId_sala(id_sala);
				ret.setNombre(nombre);
				

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
