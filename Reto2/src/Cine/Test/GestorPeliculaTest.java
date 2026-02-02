package Cine.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import Cine.modelo.gestores.GestorPelicula;
import Cine.modelo.pojo.Pelicula;

public class GestorPeliculaTest {
	private static GestorPelicula gestorPelicula;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gestorPelicula = new GestorPelicula();
	}

	@Test
	public void testGetAllPelicula() {
		gestorPelicula = new GestorPelicula();
		ArrayList<Pelicula> peliculas = gestorPelicula.getAllPelicula();

		
		assertNotNull(peliculas);

	}

	@Test
	public void testGetAllPeliculasOrdenadasPorSesion() {
		gestorPelicula = new GestorPelicula();
		ArrayList<Pelicula> peliculas = gestorPelicula.getAllPeliculasOrdenadasPorSesion();
		assertNotNull(peliculas);
		assertFalse(peliculas.isEmpty());
	}

	@Test
	public void testGetPeliculaById() {
		
		Pelicula retrievedPelicula = gestorPelicula.getPeliculaById(2);

		
		assertNotNull(retrievedPelicula);

	}
}
