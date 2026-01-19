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
		
		boolean seguirCompra = false;
		
		controlador controlador = new controlador();
		do {
			controlador.MostrarPeliculasPorOrdenDeSesion();
			Pelicula peliculaSeleccionada = controlador.seleccionDePelicula();
			if(peliculaSeleccionada != null) {
				controlador.MostrarSesionesDeUnaPelicula(peliculaSeleccionada);
				seguirCompra = controlador.SeleccionarSesion(peliculaSeleccionada);
				if(!seguirCompra) {
					seguirCompra=controlador.seguirComprando();
				}
				
			}
		}while(seguirCompra == true);
		
		
		
		
	}
}
