package Cine.modelo.pojo;

import java.util.Objects;

public class Pelicula {

	private int id_pelicula = 0;
	private String nombre = null;
	private int duracion = 0;
	private String genero = null;
	private double precio = 0;
	
	public Pelicula() {
	}
	public Pelicula(int id_pelicula, String nombre, int duracion, String genero, double precio) {
		super();
		this.id_pelicula = id_pelicula;
		this.nombre = nombre;
		this.duracion = duracion;
		this.genero = genero;
		this.precio = precio;
	}
	public int getId_pelicula() {
		return id_pelicula;
	}
	public void setId_pelicula(int id_pelicula) {
		this.id_pelicula = id_pelicula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Pelicula [id_pelicula=" + id_pelicula + ", nombre=" + nombre + ", duracion=" + duracion + ", genero="
				+ genero + ", precio=" + precio + "]";
	}
	
}
