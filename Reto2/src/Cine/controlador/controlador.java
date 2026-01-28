package Cine.controlador;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Cine.modelo.gestores.GestorCliente;
import Cine.modelo.gestores.GestorCompra;
import Cine.modelo.gestores.GestorEntrada;
import Cine.modelo.gestores.GestorPelicula;
import Cine.modelo.gestores.GestorSesion;
import Cine.modelo.pojo.Carrito;
import Cine.modelo.pojo.Cliente;
import Cine.modelo.pojo.Compra;
import Cine.modelo.pojo.Entrada;
import Cine.modelo.pojo.Pelicula;
import Cine.modelo.pojo.Sesion;

public class controlador {

	public static Scanner sc = new Scanner(System.in);
	private ArrayList<Carrito> carritoTotal = null;

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
	 * 
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
			System.out.println(peliculas.get(i).getId_pelicula()+" ) "+peliculas.get(i).getNombre());
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

		if(idPelicula != 0) {
			for (int i = 0; i < peliculas.size(); i++) {
				if (peliculas.get(i).getId_pelicula() == idPelicula) {
					ret = peliculas.get(i);
				}
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

		System.out.println("Sesiones para la pelicula "+pelicula.getNombre());
		for (int i = 0; i < sesiones.size(); i++) {
			System.out.println((i + 1) + ")  Fecha y hora - " + sesiones.get(i).getFecha_hora_inicio().toGMTString()+" - Precio - "+sesiones.get(i).getPrecio() 
					+" - Nº de Sala - "+sesiones.get(i).getId_sala());
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
		int idSesion = pedirNumeroEnteroRango(sesiones.size()) - 1;

		if (idSesion != -1) {
			System.out.print("Indique el numero de personas asistentes a la sesion: ");
			int cantidadPersonas = pedirNumeroEntero();

			Carrito carrito = new Carrito(sesiones.get(idSesion), cantidadPersonas);
			if (carritoTotal == null) {
				carritoTotal = new ArrayList<Carrito>();
				carritoTotal.add(carrito);
			} else {
				carritoTotal.add(carrito);
			}

		} else {
			ret = true;
		}

		return ret;
	}

	/**
	 * Se encarga de preguntar si quieres seguir comprando o no
	 * 
	 * @return -> true = si  
	 * 			false = no
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

	/**
	 * Muestra el descuento aplicado a cada compra
	 */
	public void calcularDescuento() {

		double precioTotal = 0;
		double descuento20 = 0.20;
		double descuento30 = 0.30;

		for (int i = 0; i < carritoTotal.size(); i++) {
			precioTotal = carritoTotal.get(i).getSesion().getPrecio() + precioTotal;
		}

		if (carritoTotal.size() == 1) {

		} else if (carritoTotal.size() == 2) {
			// dos sesiones 20%
			double primerDescuento = precioTotal * descuento20;
			precioTotal = precioTotal - primerDescuento;
			System.out.println("TOTAL 20% DESCUENTO: " + (primerDescuento));

		} else {
			// tres o mas sesiones 30%
			double segundoDescuento = precioTotal * descuento30;
			precioTotal = precioTotal - segundoDescuento;
			System.out.println("TOTAL 30% DESCUENTO: " + (segundoDescuento));
		}

		System.out.println("Precio total: " + precioTotal);

	}

	/**
	 * Genera las entradas que se hicieron en las compra y la indexa en la base de datos
	 * @param compra -> objeto compra para extraer los datos necesarios
	 */
	public void generarEntradas(Compra compra) {
		for (int i = 0; i < carritoTotal.size(); i++) {
			Entrada entrada = new Entrada();
			double precioTotal = carritoTotal.get(i).getSesion().getPrecio();
			double descuento20 = 0.20;
			double descuento30 = 0.30;
			if (carritoTotal.size() == 1) {

			} else if (carritoTotal.size() == 2) {
				// dos sesiones 20%
				double primerDescuento = precioTotal * descuento20;
				entrada.setDescuento(primerDescuento);
				precioTotal = precioTotal - primerDescuento;
			} else {
				// tres o mas sesiones 30%
				double segundoDescuento = precioTotal * descuento30;
				entrada.setDescuento(descuento30);
				precioTotal = precioTotal - segundoDescuento;
			}
			entrada.setPrecio(precioTotal);
			entrada.setId_sesion(carritoTotal.get(i).getSesion().getId_sesion());
			entrada.setNum_personas(carritoTotal.get(i).getNum_personas());
			entrada.setId_compra(compra.getId_compra());

			GestorEntrada gestorEntrada = new GestorEntrada();
			gestorEntrada.insert(entrada);
		}

	}

	/**
	 * Genera la compra y la agrega a la base de datos
	 * 
	 * @param cliente -> necesario para rellenar los datos y asignar la compra
	 * @return -> la compra generada
	 */
	public Compra generarCompra(Cliente cliente) {
		Compra ret = new Compra();
		double precioTotal = 0;
		double descuento20 = 0.20;
		double descuento30 = 0.30;

		for (int i = 0; i < carritoTotal.size(); i++) {
			precioTotal = carritoTotal.get(i).getSesion().getPrecio() + precioTotal;
		}

		if (carritoTotal.size() == 1) {

		} else if (carritoTotal.size() == 2) {
			// dos sesiones 20%
			double primerDescuento = precioTotal * descuento20;

			ret.setDescuento_total(primerDescuento);
			precioTotal = precioTotal - primerDescuento;
		} else {
			// tres o mas sesiones 30%
			double segundoDescuento = precioTotal * descuento30;
			ret.setDescuento_total(segundoDescuento);
			precioTotal = precioTotal - segundoDescuento;

		}
		ret.setPrecio_total(precioTotal);
		ret.setDNI(cliente.getDNI());

		GestorCompra gestorCompra = new GestorCompra();
		gestorCompra.insert(ret);

		ret = gestorCompra.getCompraByDNIAndFecha(ret);

		return ret;
	}

	/**
	 * Registra un usuario desde 0
	 * @return -> el nuevo cliente registrado
	 */
	public Cliente registrarUsuario() {

		Cliente ret = new Cliente();

		System.out.print("Ingrese su nombre: ");
		ret.setNombre(sc.nextLine());
		System.out.print("Ingrese su apellido: ");
		ret.setApellido(sc.nextLine());
		System.out.print("Ingrese su DNI: ");
		ret.setDNI(comprobarDni());
		System.out.print("Ingrese su correo electronico: ");
		ret.setCorreo_electronico(comprobarCorreo());
		ret.setContrasenia(comprobarContraseña());
		
		GestorCliente gestorCliente = new GestorCliente();
		gestorCliente.insert(ret);
		System.out.println("-- Registro realizado con exito --");
		return ret;

	}

	/**
	 * Comprueba si el correo que se quiere ingresar es valido
	 * @return -> un correo valido
	 */
	public String comprobarCorreo() {
		String ret = null;
		boolean valido = false;

		do {

			ret = sc.nextLine();
			if (ret.contains("@")) {
				valido = true;
			} else {
				System.out.print("Correo electronico no valido, vuelva a intentar: ");
			}

		} while (!valido);

		return ret;
	}
	
	/**
	 * Comprueba que el usuario agregue una contraseña sin errores: Usa una comprobacion con una confirmacion 
	 * @return -> la contraseña deseada
	 */
	public String comprobarContraseña() {
		String ret = null;
		boolean valido = false;

		do {
			System.out.print("Ingrese su contraseña: ");
			String pass1 = sc.nextLine();
			System.out.print("Confirme su contraseña: ");
			String pass2 = sc.nextLine();
			
			if(pass1.equals(pass2)) {
				ret = pass2;
				valido = true;
			} else {
				System.out.println("Las contraseñas no coinciden, vuelva a intentarlo");
			}
			
		} while (!valido);
		return ret;
	}

	/**
	 * Comprueba que el dni contenga una letra valida
	 * @return -> el dni correcto
	 */
	public String comprobarDni() {
		String ret = null;
		boolean valido = false;

		do {

			ret = sc.nextLine();
			if (ret.contains("T") || ret.contains("R") || ret.contains("W") || ret.contains("A") || ret.contains("G")
					|| ret.contains("M") || ret.contains("Y") || ret.contains("F") || ret.contains("P")
					|| ret.contains("D") || ret.contains("X") || ret.contains("B") || ret.contains("N")
					|| ret.contains("J") || ret.contains("Z") || ret.contains("S") || ret.contains("Q")
					|| ret.contains("V") || ret.contains("H") || ret.contains("L") || ret.contains("C")
					|| ret.contains("K") || ret.contains("E")) {
				valido = true;
			} else {
				System.out.print("DNI no valido, vuelva a intentar: ");
			}

		} while (!valido);

		return ret;
	}
	
	/**
	 * muestra el contenido del carrito
	 */
	public void enseñarCarrito() {
		System.out.println("_________________________________________");
		System.out.println("--- Carrito ----");
		System.out.println("_________________________________________");
		for(int i =0;i<carritoTotal.size();i++) {
			System.out.print("La sesion : ");
			System.out.println(carritoTotal.get(i).getSesion().getFecha_hora_inicio().toGMTString()+"");
			System.out.print("La pelicula: ");
			GestorPelicula gestorPelicula = new GestorPelicula();
			Pelicula pelicula = gestorPelicula.getPeliculaById(carritoTotal.get(i).getSesion().getId_pelicula());
			System.out.print(pelicula.getNombre());
			System.out.println(" para "+carritoTotal.get(i).getNum_personas()+" Personas");
			
			System.out.println("_________________________________________");
		}
	}
	
	/**
	 * Muestra por consola la factura que se genera en la compra
	 * @param cliente -> requiere un cliente para mostrar la informacion necesaria de la factura
	 */
	public void mostrarFactura(Cliente cliente) {
		System.out.println("------------------------");
		System.out.println("		 Factura		");
		System.out.println("------------------------");
		System.out.println(" DNI: "+cliente.getDNI());
		System.out.println(" Cliente: "+cliente.getNombre()+" "+cliente.getApellido());
		System.out.println("----------------------------------");
		for(int i =0;i<carritoTotal.size();i++) {
			System.out.print("La pelicula: ");
			GestorPelicula gestorPelicula = new GestorPelicula();
			Pelicula pelicula = gestorPelicula.getPeliculaById(carritoTotal.get(i).getSesion().getId_pelicula());
			System.out.print(pelicula.getNombre());
			System.out.print(" para "+carritoTotal.get(i).getNum_personas()+" personas en la");
			System.out.print(" sesion ");
			System.out.println(carritoTotal.get(i).getSesion().getFecha_hora_inicio().toGMTString()+"");
			System.out.println("-----------------------------------");
		}
		
		GestorCompra gestorCompra = new GestorCompra();
		Compra compra = new Compra();
		compra.setDNI(cliente.getDNI());
		compra = gestorCompra.getCompraByDNIAndFecha(compra);
		
		System.out.println("Descuento: "+compra.getDescuento_total());
		System.out.println("Precio total: "+compra.getPrecio_total());
		reiniciarPrograma();
	}
	
	public void imprimirTicket() {
		
	}

	/**
	 * Reinicia las variables de clase para que sean usadas nuevamente
	 */
	public void reiniciarPrograma() {
		carritoTotal = null;
	}
	
	/**
	 * Indica si el programa es apto para el uso
	 * @return -> true = es apto 
	 * 			false = no es apto
	 */
	public boolean programStatus() {
		boolean ret = false;
		
		if (carritoTotal == null) {
			ret = true;
		}
		
		return ret;
	}
	
	/**
	 * solicita un numero entero y lo devuelve
	 */
	public int pedirNumeroEntero() {

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

	/**
	 * pide un numero entero dentro de un rango entre 0 y n y devuelve el numero correcto 
	 * @param maximo -> el numero maximo al q puede llegar
	 * @return -> el numero dentro del rango
	 */
	public int pedirNumeroEnteroRango(int maximo) {
		boolean numeroValido = false;
		int numero = 0;
		do {
			try {
				numero = sc.nextInt();
				if(numero >= 0 && numero <= maximo) {
					numeroValido = true;
				} else {
					System.out.print("Numero fuera del menu, vuelva a intentarlo: ");
				}
				
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Lo siento, se esperaba un numero");
			}
		} while (!numeroValido || numero < 0 || numero > maximo);
		return numero;
	}

	/**
	 * recibe un si o un no por teclado y lo transoforma en booleano
	 * 
	 * @return -> true = si o false = no
	 */
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
