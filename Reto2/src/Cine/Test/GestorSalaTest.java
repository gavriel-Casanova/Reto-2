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
		// Recuperamos todas las salas
        ArrayList<Sala> salas = gestorSala.getAllSala();
        assertNotNull(salas);
        assertFalse(salas.isEmpty());
        assertEquals(salas.size(),2); // Verifica que hay 2 salas

        // Verifica que las salas recuperadas son las correctas
        //assertEquals("Sala 1", salas.get(0).getNombre());
        //assertEquals("Sala 2", salas.get(1).getNombre());
    }

	

	@Test
	public void testInsert() {
		Sala nuevaSala = new Sala();
        nuevaSala.setId_sala(3);
        nuevaSala.setNombre("Sala 3");

        // Inserta la nueva sala
        gestorSala.insert(nuevaSala);

        // Recupera todas las salas para verificar la inserción
        ArrayList<Sala> salas = gestorSala.getAllSala();
        assertEquals(3, salas.size()); // Verifica que ahora hay 3 salas
        assertEquals("Sala 3", salas.get(2).getNombre()); // Verifica que la nueva sala se ha insertado correctamente
    }

	

	@Test
	public void testGetId_salaById() {
		 int idABuscar = 1; // ID de la sala que queremos buscar
	        Sala actualSala = gestorSala.getId_salaById(idABuscar);

	        assertNotNull(actualSala); // Verifica que no es nulo
	        assertEquals(1, actualSala.getId_sala()); // Verifica que el ID es correcto
	        assertEquals("Sala 1", actualSala.getNombre()); // Verifica que el nombre es correcto
	    }
	}

	


