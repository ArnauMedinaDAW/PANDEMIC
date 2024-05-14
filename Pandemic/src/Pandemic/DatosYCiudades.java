package Pandemic;

import java.io.*;
import java.util.*;

public class DatosYCiudades {

	public static void main(String[] args) {

		String NomFit = "ciudades.txt";// Ruta del fitxer
		String a = "";// Linia de la ciutat actual
		String b = "";// Linia de la ciutat colindant
		ArrayList<String> ciutats = new ArrayList<String>();// Conté les ciutats colindants per cada ciutat
		String nciutat = "";// Nom de la ciutat principal
		int[] numeros = new int[3];// Cordeanades de la ciutat

		//int matriu[][] = new int[48][48];
		String nomFit = "ciudadesRedactadas.txt";

		// Es vol guardar les mateixes dades de cada ciutat colindant, per poder
		// calcular la distància entre una ciutat i les seves ciutats colindants.
		ArrayList<String> ciutats2 = new ArrayList<String>();// Ciutats colindants per cada ciutat colindant
		String nciutat2 = "";// Nom de la ciutat colindant
		int[] numeros2 = new int[3];// Cordeanades de la ciutat colindant
		int distancia = 0;// Distancia entre les ciutats

		try {
			// Obrir el fitxer indicat en la variable NomFit
			FileReader fr = new FileReader(NomFit);
			BufferedReader br = new BufferedReader(fr);

			FileWriter fw = new FileWriter(nomFit, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			while ((a = br.readLine()) != null) {

				// Separar les dades de cada linia (ciutat)
				nciutat = SepararDatosCiutats(a, ciutats, nciutat, numeros);
				System.out
						.println("La ciudad " + nciutat + " está en les coordenades " + numeros[1] + "," + numeros[2]);
				pw.println("La ciudad " + nciutat + " está en les coordenades " + numeros[1] + "," + numeros[2]);
				pw.println("Sus ciudades colindantes son:");
				System.out.println("Sus ciudades colindantes son:");
				// Per cada linia (ciutat) del .txt
				for (int i = 0; i < ciutats.size(); i++) {
					FileReader fr2 = new FileReader(NomFit);
					BufferedReader br2 = new BufferedReader(fr2);

					b = br2.readLine();// Linia de cada ciutat colindant

					// Busquem les linies de les ciutats colindants
					while (b != null && !b.startsWith(ciutats.get(i))) {
						b = br2.readLine();
					}
					// Un cop trobada separem les dades de la linia
					nciutat2 = SepararDatosCiutats(b, ciutats2, nciutat2, numeros2);
					// Calculem la distància
					distancia = CalcularDistancia(numeros, numeros2);
					pw.println(ciutats.get(i) + ", que está a una distància de " + distancia);
					System.out.println(ciutats.get(i) + ", que está a una distància de " + distancia);

					fr2.close();
					br2.close();
				}
				System.out.println();
				pw.println();
				// Reiniciem Strings i ArrayListper cada nova linia del txt
				nciutat = "";
				ciutats.clear();
				nciutat2 = "";
				ciutats2.clear();

			}
			//Tancar fluxes d'escritura
			pw.close();
			bw.close();
			fw.close();
			//tancar fluxes de lectura
			fr.close();
			br.close();
		} catch (IOException e) {
			System.out.println("Error E/S: " + e);
		}

	}

	// ************************************************************************************
	// ** Nombre de la función: SepararDatosCiutats
	// ** Explicación del que hace la función: Donada una linia de text del arxiu
	// ciudades.txt, guarda el
	// nom de la primera ciutat en un String, els 3 següents numeros en un array[]
	// int . A continuació guarda els noms de tantes ciutats com apareguin dins d'un
	// ArrayList.
	// ** Parámetros de entrada: String (entrada de text)
	// ** Parámetros de salida: String nomCiutat (nciutat)
	// ************************************************************************************
	public static String SepararDatosCiutats(String x, ArrayList<String> ciutats, String nciutat, int[] numeros) {

		String conv = "";
		int cont = 0;

		for (int i = 0; i < x.length(); i++) {// guardem la linia de text en variables

			if (cont == 0) {
				if (x.charAt(i) != ';') {
					nciutat = nciutat + x.charAt(i);// guardar nom ciutat 1
				} else {
					cont++;
					i++;
				}
			}
			if (cont == 1) {// guardar numero 1
				if (x.charAt(i) != ';') {

					conv = conv + x.charAt(i);

				} else {

					numeros[0] = Integer.parseInt(conv);
					conv = "";
					cont++;
					i++;

				}
			}
			if (cont == 2) {// guardar numero 2

				if (x.charAt(i) != ',') {

					conv = conv + x.charAt(i);

				} else {
					numeros[1] = Integer.parseInt(conv);
					conv = "";
					cont++;
					i++;
				}

			}

			if (cont == 3) {// guardar numero 3

				if (x.charAt(i) != ';') {
					conv = conv + x.charAt(i);

				} else {
					numeros[2] = Integer.parseInt(conv);
					conv = "";
					cont++;
					i++;
				}
			}
			if (cont == 4) {// guardar noms de les ciutats en arraylist
				if (x.charAt(i) != ';' && x.charAt(i) != ',') {
					conv = conv + x.charAt(i);
				} else {
					ciutats.add(conv);
					conv = "";
				}
			}
		}
		return nciutat;
	}

	// ************************************************************************************
	// ** Nombre de la función: CalcularDistancia
	// ** Explicación del que hace la función: Calcula la distància entre dos punts
	// amb la funció Math.
	// ** Parámetros de entrada: 2 int[] amb les coordenades de cada punt
	// ** Parámetros de salida: String nomCiutat (nciutat)
	// ************************************************************************************
	public static int CalcularDistancia(int[] cord1, int[] cord2) {

		double distancia = 0;

		distancia = Math.sqrt(Math.pow((cord2[1] - cord1[1]), 2) + Math.pow((cord2[2] - cord1[2]), 2));

		return (int) distancia; // Truncamos el double a int
	}

}
