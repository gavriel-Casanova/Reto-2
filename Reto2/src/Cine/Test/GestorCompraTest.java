package Cine.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import Cine.modelo.gestores.GestorCompra;

import Cine.modelo.pojo.Compra;


public class GestorCompraTest {
	private static GestorCompra compras = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		compras = new GestorCompra(); 
	}

	@Test
	public void testGetAllCompras() {
		ArrayList<Compra> resultado = compras.getAllCompras();
		assertNotNull("La lista de compras no deberia ser nula",resultado);
		

	}
	
	@Test
	public void testInsert() {
		Compra nuevoCompra = new Compra();
		
		 nuevoCompra.setId_compra(0);
		 nuevoCompra.setDNI("11111111E");
		 nuevoCompra.setFecha_hora_compra(null);
	     nuevoCompra.setPrecio_total(0);
	     nuevoCompra.setDescuento_total(0);

		
		compras.insert( nuevoCompra);
		ArrayList<Compra> listaCompras = compras.getAllCompras();
		assertNotNull("La lista de clientes no deberia ser nula", listaCompras);

		 
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
				
			
		
		Compra compraObtenida = compras.getCompraByDNIAndFecha(compraParaInsertar);   
		assertEquals("El DNI deberia coincidir",compraParaInsertar.getDNI(),compraObtenida.getDNI());
		}
	

}