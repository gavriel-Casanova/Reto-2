package Cine.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import Cine.modelo.gestores.GestorSesion;
import Cine.modelo.pojo.Sesion;

public class GestorSesionTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testGetAllSesion() {
		GestorSesion gestorsesiones = new GestorSesion();
		ArrayList<Sesion> resultado = gestorsesiones.getAllSesion();
		assertNotNull(resultado);
	}

	@Test
	public void testGetSesionDePelicula() {
		GestorSesion gestorsesiones = new GestorSesion();
		ArrayList<Sesion> resultado = gestorsesiones.getSesionDePelicula(1);
		
		assertNotNull(resultado);
	}

	@Test
	public void testGetSesionesDisponibles() {
		GestorSesion gestorsesiones = new GestorSesion();
		ArrayList<Sesion> resultado = gestorsesiones.getSesionesDisponibles();
		assertNotNull(resultado);
	}
	
	
	@Test
	public void testGetSesionDePeliculaErroneo() {
		GestorSesion gestorsesiones = new GestorSesion();
		ArrayList<Sesion> resultado = gestorsesiones.getSesionDePelicula(0);
		assertNull(resultado);
	}
}
