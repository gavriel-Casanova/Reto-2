package Cine.modelo.pojo;

import java.util.Objects;

public class Entrada {
	private int id_entrada = 0;
	private int id_sesion = 0;
	private int num_personas = 0;
	private double descuento = 0;
	private double precio = 0;
	private int id_compra = 0;
	
	public Entrada() {
	}
	public Entrada(int id_entrada, int id_sesion, int num_personas, double descuento, double precio, int id_compra) {
		super();
		this.id_entrada = id_entrada;
		this.id_sesion = id_sesion;
		this.num_personas = num_personas;
		this.descuento = descuento;
		this.precio = precio;
		this.id_compra = id_compra;
	}
	public int getId_entrada() {
		return id_entrada;
	}
	public void setId_entrada(int id_entrada) {
		this.id_entrada = id_entrada;
	}
	public int getId_sesion() {
		return id_sesion;
	}
	public void setId_sesion(int id_sesion) {
		this.id_sesion = id_sesion;
	}
	public int getNum_personas() {
		return num_personas;
	}
	public void setNum_personas(int num_personas) {
		this.num_personas = num_personas;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getId_compra() {
		return id_compra;
	}
	public void setId_compra(int id_compra) {
		this.id_compra = id_compra;
	}
	@Override
	public String toString() {
		return "Entrada [id_entrada=" + id_entrada + ", id_sesion=" + id_sesion + ", num_personas=" + num_personas
				+ ", descuento=" + descuento + ", precio=" + precio + ", id_compra=" + id_compra + "]";
	}

}
