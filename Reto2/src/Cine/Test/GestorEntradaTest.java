package Cine.Test;

import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import org.junit.Test;
import Cine.modelo.gestores.GestorEntrada;
import Cine.modelo.pojo.Entrada;

public class GestorEntradaTest {
	private GestorEntrada gestorEntrada;

	@Test
	public void testGetAllEntradas() {
		gestorEntrada = new GestorEntrada();
	}

	@Test
	public void testInsert() {
		// Insertamos algunas entradas para probar la recuperación
		gestorEntrada = new GestorEntrada();
		// Recuperamos todas las entrada
		ArrayList<Entrada> entradas = gestorEntrada.getAllEntradas();
		assertNotNull(entradas);

	}
}
