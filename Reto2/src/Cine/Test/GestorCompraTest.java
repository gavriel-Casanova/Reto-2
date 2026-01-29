package Cine.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import Cine.modelo.gestores.GestorCompra;

import Cine.modelo.pojo.Compra;


public class GestorCompraTest {
	private static GestorCompra compras = null;// Declara una variable estática para almacenar la lista de cliente.
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		compras = new GestorCompra(); // Inicializa la instancia de GestorCompra antes de ejecutar las pruebas.
	}

	@Test
	public void testGetAllCompras() {
		ArrayList<Compra> resultado = compras.getAllCompras();//Llama al metodo para obtener todas las compras
		assertNotNull("La lista de compras no deberia ser nula",resultado);// Verifica que el resultado no sea nulo
		

	}
	
	@Test
	public void testInsert() {
		Compra nuevoCompra = new Compra();
		
		 nuevoCompra.setId_compra(0);
		 nuevoCompra.setDNI("11111111E");
		 nuevoCompra.setFecha_hora_compra(null);
	     nuevoCompra.setPrecio_total(0);
	     nuevoCompra.setDescuento_total(0);

		// verifica que el compras se haya añadido correstamente
		compras.insert( nuevoCompra);// inserta el nuevo cliente
		ArrayList<Compra> listaCompras = compras.getAllCompras();
		assertNotNull("La lista de clientes no deberia ser nula", listaCompras);

		 // Verifica que la compra insertada esté en la lista.
        boolean compraEncontrada = false;
        for (Compra compra : listaCompras) {
            if (compra.getDNI().equals(nuevoCompra.getDNI()) && 
                compra.getPrecio_total() == nuevoCompra.getPrecio_total()) {
                compraEncontrada = true;
                break;
		
			}
		}
		assertTrue("La compra insertado deberia estar en la lista", compraEncontrada);
	}



	@Test
	public void testGetCompraByDNIAndFecha() {
		Compra  compraParaInsertar = new Compra();
		
		compraParaInsertar.setDescuento_total(0);
		compraParaInsertar.setPrecio_total(0);
		compraParaInsertar.setId_compra(0);
		compraParaInsertar.setFecha_hora_compra(null);
		compraParaInsertar.setDNI("11111111A");
				
			
		
		Compra compraObtenida = compras.getCompraByDNIAndFecha(compraParaInsertar);   // Ahora, intenta obtener la compra por DNI.
		//assertNotNull("La compra obtenida no deberia ser null", compraObtenida); // Verifica que la compra obtenida no sea nula.
		
		
        // Verifica que los datos de la compra obtenida sean correctos.
		assertEquals("El DNI deberia coincidir",compraParaInsertar.getDNI(),compraObtenida.getDNI());
		//assertEquals("El precio total deberia coincidir",compraParaInsertar.getPrecio_total(),compraObtenida.getPrecio_total(), 0);
		}
	

}