package Cine.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import Cine.modelo.gestores.GestorSala;
import Cine.modelo.pojo.Sala;

public class GestorSalaTest {

	@BeforeClass
	public static void setUpBeforeClass() {

	}

	@Test
	public void testGetAllSala() {
		GestorSala gestorSala = new GestorSala();
		// Recuperamos todas las salas
		ArrayList<Sala> salas = gestorSala.getAllSala();
		assertNotNull(salas);
		assertFalse(salas.isEmpty());
	}

	

	@Test
	public void testGetId_salaById() {
		GestorSala gestorSala = new GestorSala();
		int idABuscar = 1; // ID de la sala que queremos buscar
		Sala actualSala = gestorSala.getId_salaById(idABuscar);

		assertNotNull(actualSala); // Verifica que no es nulo
		assertEquals(1, actualSala.getId_sala()); // Verifica que el ID es correcto
		assertEquals("Sala 1", actualSala.getNombre()); // Verifica que el nombre es correcto
	}
}
