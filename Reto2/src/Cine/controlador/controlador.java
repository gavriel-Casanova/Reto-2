package Cine.controlador;

import java.util.InputMismatchException;
import java.util.Scanner;

public class controlador {

	public static Scanner sc = new Scanner(System.in);
	
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
