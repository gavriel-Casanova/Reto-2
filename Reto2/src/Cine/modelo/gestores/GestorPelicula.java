package Cine.modelo.gestores;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import Cine.modelo.pojo.Pelicula;
import Cine.modelo.utils.DBUtils;

public class GestorPelicula {

	public ArrayList<Pelicula> getAllPelicula() {
		ArrayList<Pelicula> ret = null;

		String sql = "select * from Pelicula";

		
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
					ret = new ArrayList<Pelicula>();

				// El Alumno
				Pelicula pelicula = new Pelicula();

				// Sacamos las columnas del resultSet
				int id_pelicula = resultSet.getInt("id_pelicula");
				String nombre = resultSet.getString("nombre");
				int duracion = resultSet.getInt("duracion");
				String genero = resultSet.getString("genero");
				

				pelicula.setId_pelicula(id_pelicula);
				pelicula.setNombre(nombre);
				pelicula.setDuracion(duracion);
				pelicula.setGenero(genero);
				
				
				// Lo guardamos en la lista
				ret.add(pelicula);
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
