package Model;

import Controlador.*;
import Pandemic.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DatosPartida {

	public static ArrayList<Ciudad> ciutats = new ArrayList<>();
	public static ArrayList<Virus> virus = new ArrayList<>();
	public static ArrayList<Vacunas> vacunas = new ArrayList<>();

	public static int numEnfermedadesActivas;
	public static int brotes;
	public static int rondas = 1;
	public static int acciones = 4;

	// Constructor
	public DatosPartida() {

		// GENERAR TOTS ELS OJBECTES CIUTATS
		String a = "";// Linia de la ciutat actual
		String nciutat = "";// Nom de la ciutat principal
		String enfermedad = "";
		int[] numeros = new int[3];// Cordeanades de la ciutat
		ArrayList<String> colind = new ArrayList<String>();// Conté les ciutats colindants per cada ciutat

		try {
			// Obrir el fitxer indicat en la variable NomFit
			FileReader fr = new FileReader(ControlDatos.ficheroTXT);
			BufferedReader br = new BufferedReader(fr);

			while ((a = br.readLine()) != null) {

				// Separar les dades de cada linia (ciutat)
				nciutat = DatosYCiudades.SepararDatosCiutats(a, colind, nciutat, numeros);
				if (numeros[0] == 0) {
					enfermedad = "Alfa";
				} else if (numeros[0] == 1) {
					enfermedad = "Beta";
				} else if (numeros[0] == 2) {
					enfermedad = "Gama";
				} else if (numeros[0] == 3) {
					enfermedad = "Delta";
				}
				ciutats.add(new Ciudad(nciutat, new int[] { numeros[1], numeros[2] }, enfermedad, 0,
						colind.toArray(new String[colind.size()])));
				// Reiniciem Strings i ArrayListper cada nova linia del txt
				nciutat = "";
				colind.clear();
			}
			// tancar fluxes de lectura
			fr.close();
			br.close();

		} catch (IOException e) {
			System.out.println("Error E/S: " + e);
		}

		// GENERAR OBJECTES VIRUS
		virus.add(new Virus("0", "Alfa", "Azul"));
		virus.add(new Virus("1", "Beta", "Rojo"));
		virus.add(new Virus("2", "Gama", "Verde"));
		virus.add(new Virus("3", "Delta", "Amarillo"));

		// GENERAR OBJECTES VACUNA
		vacunas.add(new Vacunas("Alfa", "Azul", 0));
		vacunas.add(new Vacunas("Beta", "Rojo", 0));
		vacunas.add(new Vacunas("Gama", "Verde", 0));
		vacunas.add(new Vacunas("Delta", "Amarillo", 0));

	}

	public static void randomitzarCiutatsIniciInfectades() {

		// RANDOMITZAR LES CIUTATS INFECTADES D'UN INICI
		int maxValue = ciutats.size();
		int random = 0;
		int i = 0;
		while (i != ControlDatos.numCiudadesInfectadasInicio) {
			random = (int) (Math.random() * maxValue);

			if (ciutats.get(random).infeccion == 0) {
				ciutats.get(random).infeccion++;
				i++;
			}
			
		}

	}

	// Getters y Setters
	public ArrayList<Ciudad> getCiutats() {
		return ciutats;
	}

//    public void setCiutats(ArrayList<String> ciutats) {
//        this.ciutats = ciutats;
//    }

	public ArrayList<Virus> getVirus() {
		return virus;
	}

	public void setVirus(ArrayList<Virus> x) {
		virus = x;
	}

	public ArrayList<Vacunas> getVacunas() {
		return vacunas;
	}

	public void setVacunas(ArrayList<Vacunas> x) {
		vacunas = x;
	}

	public int getBrotes() {
		return brotes;
	}

	public void setBrotes(int x) {
		brotes = x;
	}

	public int getRondas() {
		return rondas;
	}

	public void setRondas(int x) {
		rondas = x;
	}

	public int getAcciones() {
		return acciones;
	}

	public void setAcciones(int x) {
		acciones = x;
	}

}
