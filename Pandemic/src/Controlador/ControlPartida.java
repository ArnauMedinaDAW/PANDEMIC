package Controlador;


import Model.*;
import Swing.*;

public class ControlPartida {

	public static boolean seguirJuego = true;
	public static boolean victoria = false;
	public static boolean newGame;

	public ControlPartida() {

		// Cargar datos partida inicio
		ControlDatos.cargarParametrosInicioCiudades();
		DatosPartida.numEnfermedadesActivas = ControlDatos.numCiudadesInfectadasInicio;
		DatosPartida.randomitzarCiutatsIniciInfectades();

	}

	public static void gestionarTurno() {

		DatosPartida.acciones = 4;
		Juego.lblaccionesRestantes.setText(String.valueOf(DatosPartida.acciones));
		
		gestionarNuevasInfecciones();// Per cada turn,s'agreguen infecions
		gestionarInfeccionesColindantes();
		Juego.Infectadas.setText(String.valueOf(DatosPartida.numEnfermedadesActivas));
		gestionarBrotes();		
		gestionarProgressBar();
		gestionarFrases();// Frases generades per turn
		ciudadesVisibles();
		gestionarFinPartida();

	}

	public static void gestionarNuevasInfecciones() {

		// RANDOMITZAR LES CIUTATS INFECTADES PER TORN

		int maxValue = DatosPartida.ciutats.size();
		int random = 0;
		int i = 0;
		while (i != ControlDatos.numCuidadesInfectadasRonda) {
			random = (int) (Math.random() * maxValue);

			if (DatosPartida.ciutats.get(random).infeccion != 3) {
				DatosPartida.ciutats.get(random).infeccion++;
				if (DatosPartida.ciutats.get(random).infeccion == 1) {
					DatosPartida.numEnfermedadesActivas++;
				}

			}
			i++;
		}

	}

	public static void gestionarInfeccionesColindantes() {
		

		// Recorrer totes les ciutats
		for (int i = 0; i < DatosPartida.ciutats.size(); i++) {
			// Buscar una ciutat amb brot
			if (DatosPartida.ciutats.get(i).infeccion == 3) {

				// Buscar els noms de les ciutats colindants de la ciutat amb brot
				for (int j = 0; j < DatosPartida.ciutats.get(i).ciudadesColindantes.length; j++) {
					// Per cada ciutat colindant recorrer totes les ciutats fins trobar l'index del
					// objecte respecte la ciutat colindant
					for (int x = 0; x < DatosPartida.ciutats.size(); x++) {
						// Index del objecte colindant
						if (DatosPartida.ciutats.get(x).nombre
								.equals(DatosPartida.ciutats.get(i).ciudadesColindantes[j])) {
							// Si la colindant no te 3 en infecció, aumenta
							if (DatosPartida.ciutats.get(x).infeccion != 3) {
								DatosPartida.ciutats.get(x).infeccion++;
								// Si no estava infectada, aumenta el num de infectades Actives
								if (DatosPartida.ciutats.get(x).infeccion == 1) {
									DatosPartida.numEnfermedadesActivas++;
								}

							}
						}
					}
				}
			}
		}
	}

	public static void gestionarBrotes() {
		
		int numBrotes=0;
		
		for(int i =0; i<DatosPartida.ciutats.size();i++) {
			
			if(DatosPartida.ciutats.get(i).infeccion==3) {
				numBrotes++;
				Juego.frases.add("!Nuevo Brote! - " + DatosPartida.ciutats.get(i).nombre);

			}
			
			DatosPartida.brotes=numBrotes;
			
		}
		Juego.Brotes.setText(String.valueOf(DatosPartida.brotes));
	}
	public static void gestionarFrases() {
		
		for(int i =0; i<DatosPartida.ciutats.size();i++) {
			if(DatosPartida.ciutats.get(i).getInfeccion()!=0) {
				Juego.frases.add(DatosPartida.ciutats.get(i).getNombre() + " nivel " + DatosPartida.ciutats.get(i).getInfeccion() );
			}
		}
		
		
	}
	
	public static void gestionarFinPartida() {

		if (DatosPartida.brotes >= ControlDatos.numBrotesDerrota) {

			seguirJuego = false;
			System.out.println("PIERDES POR BROTES");
		}
		if (DatosPartida.numEnfermedadesActivas >= ControlDatos.numEnfermedadesActivasDerrota) {
			seguirJuego = false;
			System.out.println("PIERDES POR INFECCIONES");
		}
		if(DatosPartida.vacunas.get(0).porcentaje==100 &&DatosPartida.vacunas.get(1).porcentaje==100 && DatosPartida.vacunas.get(2).porcentaje==100 && DatosPartida.vacunas.get(3).porcentaje==100 ) {
			
			victoria = true;
		}

	}


	public static void gestionarProgressBar() {

		Juego.progressBarAlfa.setValue(DatosPartida.vacunas.get(0).porcentaje);
		Juego.progressBarBeta.setValue(DatosPartida.vacunas.get(1).porcentaje);
		Juego.progressBarGama.setValue(DatosPartida.vacunas.get(2).porcentaje);
		Juego.progressBarDelta.setValue(DatosPartida.vacunas.get(3).porcentaje);
		
		
	}
	public static void ciudadesVisibles() {
		
		for(int i =0; i<DatosPartida.ciutats.size();i++) {
			
			if(DatosPartida.ciutats.get(i).infeccion!=0) {
				
				Juego.ciudades[i].setVisible(true);
				
			}
			else {
				Juego.ciudades[i].setVisible(false);
			}
			
			
		}
		
		
	}

}
