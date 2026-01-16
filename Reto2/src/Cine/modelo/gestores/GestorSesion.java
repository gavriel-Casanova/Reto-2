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
	public ArrayList<Sesion> getAllSesion() {
		ArrayList<Sesion> ret = null;

		String sql = "select * from sesion";

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
					ret = new ArrayList<Sesion>();

				// El Alumno
				Sesion sesion = new Sesion(0, 0, null, null, 0, 0, 0);

				// Sacamos las columnas del resultSet
				int id_sesion = resultSet.getInt("id_sesion");
				int id_sala = resultSet.getInt("dni");
				Date fecha_hora_inicio = resultSet.getDate("dni");
				Date fecha_hora_fin = resultSet.getDate("dni");
				double precio = resultSet.getDouble("dni");
				int num_espectadores = resultSet.getInt("dni");
				int id_pelicula = resultSet.getInt("dni");

				sesion.setId_sesion(id_sesion);
				sesion.setId_sala(id_sala);
				sesion.setFecha_hora_inicio(fecha_hora_inicio);
				sesion.setFecha_hora_fin(fecha_hora_fin);
				sesion.setPrecio(precio);
				sesion.setNum_espectadores(num_espectadores);
				sesion.setId_pelicula(id_pelicula);

				// Lo guardamos en la lista
				ret.add(sesion);
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