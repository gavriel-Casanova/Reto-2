package Cine.modelo.pojo;

import java.util.Objects;

public class Entrada {
	private int IdEntrada = 0;
	private int IdSecion = 0;
	private int NumPersonas = 0;
	private double Descuento = 0;
	private double Precio = 0;
	private int IdCompra = 0;

	public Entrada() {
	}

	public Entrada(int idEntrada, int idSecion, int numPersonas, double descuento, double precio, int idCompra) {
		super();
		IdEntrada = idEntrada;
		IdSecion = idSecion;
		NumPersonas = numPersonas;
		Descuento = descuento;
		Precio = precio;
		IdCompra = idCompra;
	}

	public int getIdEntrada() {
		return IdEntrada;
	}

	public void setIdEntrada(int idEntrada) {
		IdEntrada = idEntrada;
	}

	public int getIdSecion() {
		return IdSecion;
	}

	public void setIdSecion(int idSecion) {
		IdSecion = idSecion;
	}

	public int getNumPersonas() {
		return NumPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		NumPersonas = numPersonas;
	}

	public double getDescuento() {
		return Descuento;
	}

	public void setDescuento(double descuento) {
		Descuento = descuento;
	}

	public double getPrecio() {
		return Precio;
	}

	public void setPrecio(double precio) {
		Precio = precio;
	}

	public int getIdCompra() {
		return IdCompra;
	}

	public void setIdCompra(int idCompra) {
		IdCompra = idCompra;
	}

	@Override
	public String toString() {
		return "Entrada [IdEntrada=" + IdEntrada + ", IdSecion=" + IdSecion + ", NumPersonas=" + NumPersonas
				+ ", Descuento=" + Descuento + ", Precio=" + Precio + ", IdCompra=" + IdCompra + "]";
	}

}
