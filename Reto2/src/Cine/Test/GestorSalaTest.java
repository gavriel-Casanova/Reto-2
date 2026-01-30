package Cine.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import Cine.modelo.gestores.GestorSala;
import Cine.modelo.pojo.Sala;

public class GestorSalaTest {
	private GestorSala gestorSala;
	@BeforeClass
	public void setUpBeforeClass(){
		gestorSala = new GestorSala();
		  
	}

	@Test
	public void testGetAllSala() {
        ArrayList<Sala> salas = gestorSala.getAllSala();
        assertNotNull(salas);
        assertFalse(salas.isEmpty());
        assertEquals(salas.size(),2);

    }


	@Test
	public void testGetId_salaById() {
		 int idABuscar = 1; // ID de la sala que queremos buscar
	        Sala actualSala = gestorSala.getId_salaById(idABuscar);

	        assertNotNull(actualSala); 
	        assertEquals(1, actualSala.getId_sala()); 
	        assertEquals("Sala 1", actualSala.getNombre());
	    }
	}

	


