package Cine.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import Cine.modelo.gestores.GestorCliente;
import Cine.modelo.pojo.Cliente;

public class GestorClienteTest {
	private static GestorCliente clientes = null;// Declara una variable estática para almacenar la lista de cliente.

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		clientes = new GestorCliente(); // Inicializa la instancia de GestorCliente antes de ejecutar las pruebas.

	}

	@Test
	public void testGetAllClientes() {
		ArrayList<Cliente> resultado = clientes.getAllClientes();
		assertNotNull("La lista de cliente no deberia ser nula" + resultado);

	}

	@Test
	public void testInsert() {
		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setDNI("11111111E");
		nuevoCliente.setNombre("Juan");
		nuevoCliente.setApellido("Pérez");
		nuevoCliente.setCorreo_electronico("juan@mail.com");
		nuevoCliente.setContrasenia("pass1");

		// verifica que el cliente se haya añadido correstamente
		clientes.insert(nuevoCliente);// inserta el nuevo cliente
		ArrayList<Cliente> listaClientes = clientes.getAllClientes();
		assertNotNull("La lista de clientes no deberia ser nula", listaClientes);

		// Verifica que el cliente insertadi este en la lista

		boolean clienteEncontrado = false;
		for (Cliente cliente : listaClientes) {
			if (cliente.getDNI().equals(nuevoCliente.getDNI())) {
				clienteEncontrado = true;
				break;
			}
		}
		assertTrue("El cliente insertado deberia estar en la lista", clienteEncontrado);
	}

}
