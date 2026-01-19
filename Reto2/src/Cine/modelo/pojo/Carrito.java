package Cine.modelo.pojo;

import java.util.Objects;

public class Carrito {

	private Sesion  sesion= null;
	private int num_personas =0;
	@Override
	public String toString() {
		return "Carrito [sesion=" + sesion + ", num_personas=" + num_personas + "]";
	}
	public Sesion getSesion() {
		return sesion;
	}
	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}
	public int getNum_personas() {
		return num_personas;
	}
	public void setNum_personas(int num_personas) {
		this.num_personas = num_personas;
	}
	@Override
	public int hashCode() {
		return Objects.hash(num_personas, sesion);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrito other = (Carrito) obj;
		return num_personas == other.num_personas && Objects.equals(sesion, other.sesion);
	}
	public Carrito(Sesion sesion, int num_personas) {
		super();
		this.sesion = sesion;
		this.num_personas = num_personas;
	}
	
	
}
