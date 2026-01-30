package Cine.vista;

import java.util.ArrayList;
import java.util.Scanner;

import Cine.controlador.Controlador;
import Cine.modelo.pojo.Cliente;
import Cine.modelo.pojo.Compra;
import Cine.modelo.pojo.Pelicula;

public class Menu {

	public static Scanner sc = new Scanner(System.in);

	private Cliente cliente = null;
	private Controlador controlador = null;

	public Menu() {
		controlador = new Controlador();
	}

	public void iniciar() {
		try {
			do {
				menuPrincipal();
			} while (true);
		} catch (Exception e) {
			System.out.println("Fatal error");
		}
	}

	private void menuPrincipal() {

		System.out.println("		Bienvenido ");

		System.out.println("             Pulsa Enter para continuar...");
		sc.nextLine().trim();

		// --- COSA 1

		boolean seguirComprando = false;
		do {
			ArrayList<Pelicula> peliculas = controlador.mostrarPeliculasPorOrdenDeSesion();
			if (null != peliculas) {
				for (int i = 0; i < peliculas.size(); i++) {
					System.out.println(peliculas.get(i).getId_pelicula() + " ) " + peliculas.get(i).getNombre());
				}
				System.out.println("0 - salir");
			}
			
			// PASAR el peliculas
			Pelicula peliculaSeleccionada = controlador.seleccionDePelicula();

			if (null != peliculaSeleccionada) {
				controlador.mostrarSesionesDeUnaPelicula(peliculaSeleccionada);
				boolean seleccionado = controlador.seleccionarSesion(peliculaSeleccionada);
				if (!seleccionado) {
					seguirComprando = controlador.seguirComprando();
					controlador.enseñarCarrito();
				}
			}

		} while (seguirComprando == true);

		// --- COSA 2

		if (!controlador.carritoVacio()) {

			controlador.calcularDescuento();

			System.out.println("Esta de acuerdo con su compra?");
			boolean deAcuerdo = controlador.preguntarSiONo();

			if (deAcuerdo) {
				System.out.println("Se ecuentra registrado?");
				boolean registro = controlador.preguntarSiONo();
				if (registro) {
					cliente = login();
				} else {
					cliente = controlador.registrarUsuario();
				}

				// Consultar... ^__^
				// cliente = registro ? login(): controlador.registrarUsuario();

				Compra compra = controlador.generarCompra(cliente);
				controlador.generarEntradas(compra);

				System.out.println("-- Compra realizada con exito --");
				System.out.println(" Desea factura? ");
				boolean quiereFactura = controlador.preguntarSiONo();

				if (quiereFactura) {
					controlador.imprimirTicket(cliente);
					controlador.mostrarFactura(cliente);
				}
				System.out.println("gracias por su compra");
			}

		}
		controlador.vaciarCarrito();
		esperar();
	}

	/**
	 * hace el login del cliente
	 * 
	 * @param controlador -> se usa para comunicarse con la base de datos
	 * @return -> el cliente logueado
	 */
	private Cliente login() {
		Cliente ret = null;
		boolean bloqueo = false;
		while (bloqueo == false) {
			System.out.print("Ingrese su DNI: ");
			String DNI = sc.nextLine();
			System.out.print("Ingrese su contraseña: ");
			String pass = sc.nextLine();
			bloqueo = controlador.validarLogin(DNI, pass);
			if (bloqueo == true) {
				ret = controlador.getCliente(DNI);
			} else {
				System.out.println("DNI o contraseña incorrectos");
			}

		}

		return ret;
	}

	/**
	 * Deja el programa en espera durante 3 segundos
	 */
	private void esperar() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
