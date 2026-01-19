package Cine.vista;

import Cine.controlador.controlador;

public class Menu {

	
	public void Iniciar() {
		try {
			MenuPrincipal();
		}catch(Exception e) {
			System.out.println("Fatal error");
		}
	}

	private void MenuPrincipal() {
		
		controlador controlador = new controlador();
		controlador.MostrarNombreDePeliculas();
	}
}
