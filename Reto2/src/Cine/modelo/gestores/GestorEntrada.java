package Cine.modelo.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Cine.modelo.pojo.Entrada;
import Cine.modelo.utils.DBUtils;

public class GestorEntrada {

	public ArrayList<Entrada> getAllEntradas() {
		ArrayList<Entrada> ret = null;

		String sql = "select * from Entrada";

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
					ret = new ArrayList<Entrada>();

				// El Alumno
				Entrada entrada = new Entrada();

				// Sacamos las columnas del resultSet
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

				// Lo guardamos en la lista
				ret.add(entrada);
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
