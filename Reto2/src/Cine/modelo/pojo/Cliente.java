package Cine.modelo.pojo;

import java.util.Objects;

public class Cliente {

	private String DNI = null;
	private String nombre = null;
	private String apellido = null;
	private String correo_electronico = null;
	private String contrasenia = null;
	@Override
	public String toString() {
		return "Cliente [DNI=" + DNI + ", nombre=" + nombre + ", apellido=" + apellido + ", correo_electronico="
				+ correo_electronico + ", contrasenia=" + contrasenia + "]";
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo_electronico() {
		return correo_electronico;
	}
	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	@Override
	public int hashCode() {
		return Objects.hash(DNI, apellido, contrasenia, correo_electronico, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(DNI, other.DNI) && Objects.equals(apellido, other.apellido)
				&& Objects.equals(contrasenia, other.contrasenia)
				&& Objects.equals(correo_electronico, other.correo_electronico) && Objects.equals(nombre, other.nombre);
	}
	
	
	
}
