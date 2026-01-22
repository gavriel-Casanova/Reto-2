package Cine.vista;

import java.util.Scanner;

import Cine.controlador.controlador;
import Cine.modelo.pojo.Cliente;
import Cine.modelo.pojo.Compra;
import Cine.modelo.pojo.Pelicula;

public class Menu {

	public static Scanner sc = new Scanner(System.in);
	private Cliente CLIENTE = null;

	public void Iniciar() {
		try {
			MenuPrincipal();
		} catch (Exception e) {
			System.out.println("Fatal error");
		}
	}

	private void MenuPrincipal() {

		boolean seguirCompra = false;
		controlador controlador = new controlador();
		
		do {
			controlador.MostrarPeliculasPorOrdenDeSesion();
			Pelicula peliculaSeleccionada = controlador.seleccionDePelicula();
			if (peliculaSeleccionada != null) {
				controlador.MostrarSesionesDeUnaPelicula(peliculaSeleccionada);
				seguirCompra = controlador.SeleccionarSesion(peliculaSeleccionada);
				if (!seguirCompra) {
					seguirCompra = controlador.seguirComprando();
				}

			}
		} while (seguirCompra == true);

		
		controlador.calcularDescuento();
		
		System.out.println("Se ecuentra registrado?");
		boolean registro = controlador.PreguntarSiONo();
		if (registro == true) {
			CLIENTE = login(controlador);
		} else {
			
		}
		
		Compra compra = controlador.generarCompra(CLIENTE);
		controlador.generarEntradas(compra);
		
		
	}

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
			intentos = intentos + 1;
			if (intentos == 3) {
				bloqueo = true;
			}
			if (bloqueo == true) {
				ret = controlador.getCliente(DNI);
			}
		}

		
		
		return ret;
	}
}
