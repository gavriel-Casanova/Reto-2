
package Cine.modelo.pojo;

import java.util.Date;
import java.util.Objects;

public class Sesion {
	private int id_sesion = 0;		
	private int id_sala = 0;	
    private Date fecha_hora_inicio = null;	
	private	Date fecha_hora_fin	=null;
	private	double precio = 0 ;	
	private int	num_espectadores = 0;
	private	int id_pelicula = 0;
	
	
	
	public Sesion(int id_sesion, int id_sala, Date fecha_hora_inicio, Date fecha_hora_fin, double precio,
			int num_espectadores, int id_pelicula) {
		super();
		this.id_sesion = id_sesion;
		this.id_sala = id_sala;
		this.fecha_hora_inicio = fecha_hora_inicio;
		this.fecha_hora_fin = fecha_hora_fin;
		this.precio = precio;
		this.num_espectadores = num_espectadores;
		this.id_pelicula = id_pelicula;
	}
	public int getId_sesion() {
		return id_sesion;
	}
	public void setId_sesion(int id_sesion) {
		this.id_sesion = id_sesion;
	}
	public int getId_sala() {
		return id_sala;
	}
	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}
	public Date getFecha_hora_inicio() {
		return fecha_hora_inicio;
	}
	public void setFecha_hora_inicio(Date fecha_hora_inicio) {
		this.fecha_hora_inicio = fecha_hora_inicio;
	}
	public Date getFecha_hora_fin() {
		return fecha_hora_fin;
	}
	public void setFecha_hora_fin(Date fecha_hora_fin) {
		this.fecha_hora_fin = fecha_hora_fin;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getNum_espectadores() {
		return num_espectadores;
	}
	public void setNum_espectadores(int num_espectadores) {
		this.num_espectadores = num_espectadores;
	}
	public int getId_pelicula() {
		return id_pelicula;
	}
	public void setId_pelicula(int id_pelicula) {
		this.id_pelicula = id_pelicula;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(fecha_hora_fin, fecha_hora_inicio, id_pelicula, id_sala, id_sesion, num_espectadores,
				precio);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sesion other = (Sesion) obj;
		return Objects.equals(fecha_hora_fin, other.fecha_hora_fin)
				&& Objects.equals(fecha_hora_inicio, other.fecha_hora_inicio) && id_pelicula == other.id_pelicula
				&& id_sala == other.id_sala && id_sesion == other.id_sesion
				&& num_espectadores == other.num_espectadores
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio);
	}
	
	@Override
	public String toString() {
		return "Sesion [id_sesion=" + id_sesion + ", id_sala=" + id_sala + ", fecha_hora_inicio=" + fecha_hora_inicio
				+ ", fecha_hora_fin=" + fecha_hora_fin + ", precio=" + precio + ", num_espectadores=" + num_espectadores
				+ ", id_pelicula=" + id_pelicula + "]";
	}
}

