package Cine.vista;

import Cine.controlador.controlador;
import Cine.modelo.pojo.Pelicula;

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
		controlador.MostrarPeliculasPorOrdenDeSesion();
		Pelicula peliculaSeleccionada = controlador.seleccionDePelicula();
		controlador.MostrarSesionesDeUnaPelicula(peliculaSeleccionada);
		controlador.SeleccionarSesion(peliculaSeleccionada);
	}
}
