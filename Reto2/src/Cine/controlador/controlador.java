package Cine.controlador;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Cine.modelo.gestores.GestorCliente;
import Cine.modelo.gestores.GestorCompra;
import Cine.modelo.gestores.GestorPelicula;
import Cine.modelo.gestores.GestorSesion;
import Cine.modelo.pojo.Carrito;
import Cine.modelo.pojo.Cliente;
import Cine.modelo.pojo.Compra;
import Cine.modelo.pojo.Pelicula;
import Cine.modelo.pojo.Sesion;

public class controlador {

	public static Scanner sc = new Scanner(System.in);
	private ArrayList<Carrito> CARRITO = null;

	/**
	 * Esta clase valida que el DNI y la contraseña son los correctos
	 * 
	 * @param uss  = DNI
	 * @param pass = contraseña
	 * @return retorna true si es valido el login o false si es invalido
	 */
	public boolean ValidarLogin(String uss, String pass) {
		boolean ret = false;

		GestorCliente gestorCliente = new GestorCliente();
		ArrayList<Cliente> clientes = gestorCliente.getAllClientes();

		for (int i = 0; i < clientes.size(); i++) {
			if (uss.equalsIgnoreCase(clientes.get(i).getDNI())) {
				if (uss.equalsIgnoreCase(clientes.get(i).getDNI())
						&& pass.equalsIgnoreCase(clientes.get(i).getContrasenia())) {
					ret = true;
				}
			}
		}

		if (ret == false) {
			System.out.println("DNI o contraseña incorrectos");
		}

		return ret;
	}
	
	/**
	 * Obtiene un cliente por su DNI
	 * @param uss -> DNI del cliente
	 * @return -> el cliente con ese DNI
	 */
	public Cliente getCliente(String uss) {
		Cliente ret = new Cliente();

		GestorCliente gestorCliente = new GestorCliente();
		ArrayList<Cliente> clientes = gestorCliente.getAllClientes();

		for (int i = 0; i < clientes.size(); i++) {
			if (uss.equalsIgnoreCase(clientes.get(i).getDNI())) {
				ret = clientes.get(i);
			}
		}
		return ret;
	}

	/**
	 * Muestra las peliculas ordenadas segun el orden de las sesiones de mas cercana
	 * a mas lejana
	 */
	public void MostrarPeliculasPorOrdenDeSesion() {
		GestorPelicula gestorPelicula = new GestorPelicula();
		ArrayList<Pelicula> peliculas = gestorPelicula.getAllPeliculasOrdenadasPorSesion();

		for (int i = 0; i < peliculas.size(); i++) {
			System.out.println(peliculas.get(i).toString());
		}
		System.out.println("0 - salir");
	}

	/**
	 * Se encarga de que el usuario seleccione una pelicura
	 * 
	 * @return -> la pelicula seleccionada
	 */
	public Pelicula seleccionDePelicula() {
		Pelicula ret = null;
		GestorPelicula gestorPelicula = new GestorPelicula();
		ArrayList<Pelicula> peliculas = gestorPelicula.getAllPeliculasOrdenadasPorSesion();

		System.out.print("Indique el numero de la pelicula seleccionada: ");
		int idPelicula = pedirNumeroEnteroRango(peliculas.size());

		for (int i = 0; i < peliculas.size(); i++) {
			if (peliculas.get(i).getId_pelicula() == idPelicula) {
				ret = peliculas.get(i);
			}
		}

		return ret;
	}

	/**
	 * Muestra todas las sesiones de una pelicula seleccionada
	 * 
	 * @param pelicula -> necesita una pelicula para funcionar
	 */
	public void MostrarSesionesDeUnaPelicula(Pelicula pelicula) {
		GestorSesion gestorSesion = new GestorSesion();
		ArrayList<Sesion> sesiones = gestorSesion.getSesionDePelicula(pelicula.getId_pelicula());

		for (int i = 0; i < sesiones.size(); i++) {
			System.out.println((i+1)+") "+sesiones.get(i).toString());
		}
		System.out.println("0 - salir");
	}

	/**
	 * Se encarga de seleccionar y agrega al carrito la sesion indicada
	 * 
	 * @param pelicula -> pelicula seleccionada
	 * @return -> true si se cancelo o false se agrego la sesion
	 */
	public boolean SeleccionarSesion(Pelicula pelicula) {

		boolean ret = false;
		GestorSesion gestorSesion = new GestorSesion();
		ArrayList<Sesion> sesiones = gestorSesion.getSesionDePelicula(pelicula.getId_pelicula());

		System.out.print("Indique el numero de la sesion seleccionada: ");
		int idSesion = pedirNumeroEnteroRango(sesiones.size()+1) - 1;

		if (idSesion != -1) {
			System.out.print("Indique el numero de personas asistentes a la sesion: ");
			int cantidadPersonas = pedirNumeroEntero();

			for (int i = 0; i < sesiones.size(); i++) {
				if (sesiones.get(i).getId_sesion() == idSesion) {
					Carrito carrito = new Carrito(sesiones.get(i), cantidadPersonas);
					if (CARRITO == null) {
						CARRITO = new ArrayList<Carrito>();
						CARRITO.add(carrito);
					} else {
						CARRITO.add(carrito);
					}
				}
			}
		} else {
			ret = true;
		}

		return ret;
	}

	/**
	 * Se encarga de preguntar si quieres seguir comprando o no
	 * 
	 * @return -> true en caso que si o false en caso que no
	 */
	public boolean seguirComprando() {
		boolean ret = false;
		boolean valido = false;

		System.out.println("Desea seguir comprando? ");
		System.out.print("SI / NO : ");
		String seguirCompra = sc.nextLine();

		while (valido == false) {
			if (seguirCompra.equalsIgnoreCase("Si") || seguirCompra.equalsIgnoreCase("S")) {

				ret = true;
				valido = true;

			} else if (seguirCompra.equalsIgnoreCase("No") || seguirCompra.equalsIgnoreCase("N")) {
				valido = true;
			} else {
				System.out.println("Respuesta no valida, vuelva a intentar: ");
			}
		}

		return ret;
	}

	public void calcularDescuento() {

 		double precioTotal = 0;
		double descuento20 = 0.20;
		double descuento30 = 0.30;
	

		for (int i = 0; i < CARRITO.size(); i++) {
			precioTotal = CARRITO.get(i).getSesion().getPrecio() + precioTotal;
		}

		if (CARRITO.size() == 1) {

		} else if (CARRITO.size() == 2) {
			// dos sesiones 20%
			double primerDescuento = precioTotal * descuento20;
			precioTotal = precioTotal - primerDescuento;
			System.out.println("TOTAL 20%DESCUENTO:" + (primerDescuento));
			
		} else {
			// tres o mas sesiones 30%
			double segundoDescuento = precioTotal * descuento30;
			precioTotal = precioTotal - segundoDescuento;
			System.out.println("TOTAL 30% DESCUENTO:" + (segundoDescuento));
		}

		System.out.println("Precio total: " + precioTotal);

	}

	public void generarEntradas() {
		for(int i =0;i< CARRITO.size();i++) {
			
		}
	}
	
	public Compra generarCompra(Cliente cliente){
		Compra ret = new Compra();
		double precioTotal = 0;
		double descuento20 = 0.20;
		double descuento30 = 0.30;
	

		for (int i = 0; i < CARRITO.size(); i++) {
			precioTotal = CARRITO.get(i).getSesion().getPrecio() + precioTotal;
		}

		if (CARRITO.size() == 1) {

		} else if (CARRITO.size() == 2) {
			// dos sesiones 20%
			double primerDescuento = precioTotal * descuento20;
			
			ret.setDescuento_total(descuento20);
			precioTotal = precioTotal - primerDescuento;
		} else {
			// tres o mas sesiones 30%
			double segundoDescuento = precioTotal * descuento30;
			ret.setDescuento_total(descuento30);
			precioTotal = precioTotal - segundoDescuento;
		}

		
		ret.setPrecio_total(precioTotal);
		ret.setDNI(cliente.getDNI());
		
		GestorCompra gestorCompra = new GestorCompra();
		gestorCompra.insert(ret);
		
		ret = gestorCompra.getCompraByDNIAndFecha(ret);
		
		return ret;
	}
	
	public static int pedirNumeroEntero() {

		boolean numeroValido = false;
		int numero = 0;
		do {
			try {
				numero = sc.nextInt();
				numeroValido = true;
				sc.nextLine();
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Lo siento, se esperaba un numero");
				System.out.print("Intente nuevamente: ");
			}
		} while (!numeroValido);

		return numero;
	}
	
	

	public static int pedirNumeroEnteroRango(int maximo) {
		boolean numeroValido = false;
		int numero = 0;
		do {
			try {
				numero = sc.nextInt();
				numeroValido = true;
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Lo siento, se esperaba un numero");
			}
		} while (!numeroValido || numero < 0 || numero > maximo);
		return numero;
	}
	
	public boolean PreguntarSiONo() {
		boolean ret = false;
		boolean valido = false;
		System.out.print("SI / NO : ");
		String seguirCompra = sc.nextLine();

		while (valido == false) {
			if (seguirCompra.equalsIgnoreCase("Si") || seguirCompra.equalsIgnoreCase("S")) {

				ret = true;
				valido = true;

			} else if (seguirCompra.equalsIgnoreCase("No") || seguirCompra.equalsIgnoreCase("N")) {
				valido = true;
			} else {
				System.out.println("Respuesta no valida, vuelva a intentar: ");
			}
		}

		return ret;
	}
}
