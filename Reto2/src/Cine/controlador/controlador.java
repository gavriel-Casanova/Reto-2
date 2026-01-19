package Cine.controlador;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Cine.modelo.gestores.GestorCliente;
import Cine.modelo.gestores.GestorPelicula;
import Cine.modelo.gestores.GestorSesion;
import Cine.modelo.pojo.Cliente;
import Cine.modelo.pojo.Pelicula;
import Cine.modelo.pojo.Sesion;

public class controlador {

	public static Scanner sc = new Scanner(System.in);
	
	/**
	 * Esta clase valida que el DNI y la contraseña son los correctos
	 * @param uss = DNI
	 * @param pass = contraseña
	 * @return retorna true si es valido el login o false si es invalido
	 */
	public boolean ValidarLogin(String uss, String pass) {
		boolean ret = false;
		
		GestorCliente gestorCliente = new GestorCliente();
		ArrayList<Cliente> clientes = gestorCliente.getAllClientes();
		
		for (int i = 0; i < clientes.size(); i++) {
			if (uss.equalsIgnoreCase(clientes.get(i).getDNI())) {
				if (uss.equalsIgnoreCase(clientes.get(i).getDNI()) && pass.equalsIgnoreCase(clientes.get(i).getContrasenia())) {
					ret = true;
				}
			}
		}
		
		
		if (ret == false) {
			System.out.println("DNI o contraseña incorrectos");
		}
		
		return ret;
	}
	
	
	
	public void MostrarPeliculasPorOrdenDeSesion() {
		GestorPelicula gestorPelicula = new GestorPelicula();
		ArrayList<Pelicula> peliculas = gestorPelicula.getAllPeliculasOrdenadasPorSesion();
		
		for(int i =0; i < peliculas.size();i++) {
			System.out.println(peliculas.get(i).toString());
		}
	}
	
	public Pelicula seleccionDePelicula() {
		
		Pelicula ret = null;
		GestorPelicula gestorPelicula = new GestorPelicula();
		ArrayList<Pelicula> peliculas = gestorPelicula.getAllPeliculasOrdenadasPorSesion();
		
		System.out.print("Indique el numero de la pelicula seleccionada: ");
		int idPelicula = pedirNumeroEnteroRango(peliculas.size());
		
		for(int i =0; i < peliculas.size();i++) {
			if(peliculas.get(i).getId_pelicula() == idPelicula) {
				ret = peliculas.get(i);
			}
		}
		
		return ret;
	}
	
	public void MostrarSesionesDeUnaPelicula(Pelicula pelicula) {
		GestorSesion gestorSesion = new GestorSesion();
		ArrayList<Sesion> sesiones = gestorSesion.getSesionDePelicula(pelicula.getId_pelicula());
		
		for(int i=0;i< sesiones.size();i++) {
			System.out.println(sesiones.get(i).toString());
		}
		
	}
	
	
	
	public static int pedirNumeroEntero() {
		boolean numeroValido = false;
		int numero = 0;
		do {
			try {
				numero = sc.nextInt();
				numeroValido = true;
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Lo siento, se esperaba un numero");
				System.out.println("Ingrese nuevamente la opcion: ");
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
}
