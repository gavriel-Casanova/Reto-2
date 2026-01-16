package Cine.modelo.pojo;

import java.util.Objects;

public class Pelicula {

	private int IdPelicula = 0;
	private String Nombre = null;
	private int Duracion = 0;
	private String Genero = null;
	private double Precio = 0;

	public Pelicula() {
		
	}

	public Pelicula(int idPelicula, String nombre, int duracion, String genero, double precio) {
		super();
		IdPelicula = idPelicula;
		Nombre = nombre;
		Duracion = duracion;
		Genero = genero;
		Precio = precio;
	}

	public int getIdPelicula() {
		return IdPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		IdPelicula = idPelicula;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public int getDuracion() {
		return Duracion;
	}

	public void setDuracion(int duracion) {
		Duracion = duracion;
	}

	public String getGenero() {
		return Genero;
	}

	public void setGenero(String genero) {
		Genero = genero;
	}

	public double getPrecio() {
		return Precio;
	}

	public void setPrecio(double precio) {
		Precio = precio;
	}

}
