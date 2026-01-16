package Cine.controlador;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Cine.modelo.gestores.GestorCliente;
import Cine.modelo.pojo.Cliente;

public class controlador {

	public static Scanner sc = new Scanner(System.in);
	
	
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

	public static int pedirNumeroEnteroRango(int minimo, int maximo) {
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
		} while (!numeroValido || numero < minimo || numero > maximo);
		return numero;
	}
}
