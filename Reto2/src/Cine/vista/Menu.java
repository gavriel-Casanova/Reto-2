package Cine.vista;

import java.util.Scanner;

import Cine.controlador.controlador;
import Cine.modelo.pojo.Cliente;
import Cine.modelo.pojo.Compra;
import Cine.modelo.pojo.Pelicula;

public class Menu {

	public static Scanner sc = new Scanner(System.in);
	private Cliente cliente = null;

	public void Iniciar() {
		try {
			do {
				MenuPrincipal();
			} while (true);
		} catch (Exception e) {
			System.out.println("Fatal error");
		}
	}

	private void MenuPrincipal() {

		boolean seguirCompra = false;
		controlador controlador = new controlador();

		System.out.println("		Bienvenido ");

		System.out.println("             Pulsa Enter para continuar...");
		sc.nextLine().trim();

		do {
			controlador.MostrarPeliculasPorOrdenDeSesion();
			Pelicula peliculaSeleccionada = controlador.seleccionDePelicula();
			if (peliculaSeleccionada != null) {
				controlador.MostrarSesionesDeUnaPelicula(peliculaSeleccionada);
				seguirCompra = controlador.SeleccionarSesion(peliculaSeleccionada);
				if (!seguirCompra) {
					seguirCompra = controlador.seguirComprando();
					controlador.enseñarCarrito();
				}

			} else {
				seguirCompra = false;
			}
		} while (seguirCompra == true);

		if (controlador.programStatus() == false) {
			controlador.calcularDescuento();

			System.out.println("Esta de acuerdo con su compra?");
			boolean deAcuerdo = controlador.PreguntarSiONo();

			if (deAcuerdo) {
				System.out.println("Se ecuentra registrado?");
				boolean registro = controlador.PreguntarSiONo();
				if (registro == true) {
					cliente = login(controlador);
				} else {
					cliente = controlador.registrarUsuario();
				}

				Compra compra = controlador.generarCompra(cliente);
				controlador.generarEntradas(compra);

				System.out.println("-- Compra realizada con exito --");
				System.out.println(" Desea factura? ");
				boolean quiereFactura = controlador.PreguntarSiONo();

				if (quiereFactura == true) {
					controlador.mostrarFactura(cliente);
					controlador.imprimirTicket(cliente);
				}
				System.out.println("gracias por su compra");
			}

		}
		controlador.reiniciarPrograma();
		espera3s();
	}

	/**
	 * hace el login del cliente
	 * 
	 * @param controlador -> se usa para comunicarse con la base de datos
	 * @return -> el cliente logueado
	 */
	private Cliente login(controlador controlador) {
		Cliente ret = null;
		boolean bloqueo = false;
		int intentos = 0;
		while (bloqueo == false) {
			System.out.print("Ingrese su DNI: ");
			String DNI = sc.nextLine();
			System.out.print("Ingrese su contraseña: ");
			String pass = sc.nextLine();
			bloqueo = controlador.ValidarLogin(DNI, pass);
			if (bloqueo == true) {
				ret = controlador.getCliente(DNI);
			}
		}

		return ret;
	}

	/**
	 * Deja el programa en espera durante 3 segundos
	 */
	private void espera3s() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
