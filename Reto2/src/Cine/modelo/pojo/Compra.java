package Cine.modelo.pojo;

import java.sql.Date;
import java.util.Objects;

public class Compra {

	private int id_compra =0;
	private Date fecha_hora_compra = null;
	private double descuento_total =0;
	private double precio_total =0;
	private String DNI = null;
	@Override
	public String toString() {
		return "Compra [id_compra=" + id_compra + ", fecha_hora_compra=" + fecha_hora_compra + ", descuento_total="
				+ descuento_total + ", precio_total=" + precio_total + ", DNI=" + DNI + "]";
	}
	public int getId_compra() {
		return id_compra;
	}
	public void setId_compra(int id_compra) {
		this.id_compra = id_compra;
	}
	public Date getFecha_hora_compra() {
		return fecha_hora_compra;
	}
	public void setFecha_hora_compra(Date fecha_hora_compra) {
		this.fecha_hora_compra = fecha_hora_compra;
	}
	public double getDescuento_total() {
		return descuento_total;
	}
	public void setDescuento_total(double descuento_total) {
		this.descuento_total = descuento_total;
	}
	public double getPrecio_total() {
		return precio_total;
	}
	public void setPrecio_total(double precio_total) {
		this.precio_total = precio_total;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	@Override
	public int hashCode() {
		return Objects.hash(DNI, descuento_total, fecha_hora_compra, id_compra, precio_total);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		return Objects.equals(DNI, other.DNI)
				&& Double.doubleToLongBits(descuento_total) == Double.doubleToLongBits(other.descuento_total)
				&& Objects.equals(fecha_hora_compra, other.fecha_hora_compra) && id_compra == other.id_compra
				&& Double.doubleToLongBits(precio_total) == Double.doubleToLongBits(other.precio_total);
	}
	
	
	
}
