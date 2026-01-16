package Cine.modelo.pojo;

import java.util.Objects;

public class Sala {

	private int id_sala =0;
	private String nombre = null;
	public int getId_sala() {
		return id_sala;
	}
	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Sala [id_sala=" + id_sala + ", nombre=" + nombre + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id_sala, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		return id_sala == other.id_sala && Objects.equals(nombre, other.nombre);
	}
<<<<<<< HEAD
=======
	
>>>>>>> refs/remotes/origin/main
	
	
}
