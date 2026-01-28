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
			
			Class.forName(DBUtils.DRIVER);

		
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			
			while (resultSet.next()) {

				
				if (null == ret)
					ret = new ArrayList<Pelicula>();

				
				Pelicula pelicula = new Pelicula();

				
				int id_pelicula = resultSet.getInt("id_pelicula");
				String nombre = resultSet.getString("nombre");
				int duracion = resultSet.getInt("duracion");
				String genero = resultSet.getString("genero");
				

				pelicula.setId_pelicula(id_pelicula);
				pelicula.setNombre(nombre);
				pelicula.setDuracion(duracion);
				pelicula.setGenero(genero);
				
				
				
				ret.add(pelicula);
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
	
	public ArrayList<Pelicula> getAllPeliculasOrdenadasPorSesion() {
		ArrayList<Pelicula> ret = null;

		String sql = "SELECT DISTINCT p.* FROM sesion s join pelicula p on s.id_pelicula = p.id_pelicula where current_timestamp < s.fecha_hora_inicio order by s.fecha_hora_inicio";

		
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
					ret = new ArrayList<Pelicula>();

				
				Pelicula pelicula = new Pelicula();

			
				int id_pelicula = resultSet.getInt("id_pelicula");
				String nombre = resultSet.getString("nombre");
				int duracion = resultSet.getInt("duracion");
				String genero = resultSet.getString("genero");
				

				pelicula.setId_pelicula(id_pelicula);
				pelicula.setNombre(nombre);
				pelicula.setDuracion(duracion);
				pelicula.setGenero(genero);
				
				
			
				ret.add(pelicula);
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
	
	public Pelicula getPeliculaById(int id) {
		Pelicula ret = null;

		String sql = "select * from Pelicula where id_pelicula = "+id;

		
		Connection connection = null;

		
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			
			Class.forName(DBUtils.DRIVER);

			
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			// Vamos a lanzar la sentencia...
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			
			while (resultSet.next()) {

				
				if (null == ret)
					ret = new Pelicula();

			
				Pelicula pelicula = new Pelicula();

				
				int id_pelicula = resultSet.getInt("id_pelicula");
				String nombre = resultSet.getString("nombre");
				int duracion = resultSet.getInt("duracion");
				String genero = resultSet.getString("genero");
				

				pelicula.setId_pelicula(id_pelicula);
				pelicula.setNombre(nombre);
				pelicula.setDuracion(duracion);
				pelicula.setGenero(genero);
				
				
				
				ret = pelicula;
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
