package Pandemic;

import java.io.*;
import java.util.*;

public class Buscando_las_rutas_optimas {

	public static void main(String[] args) {

		String NomFit = "ciudades.txt";// Ruta del fitxer.
		String a = "";// Linia de la ciutat actual.
		String b = "";// Linia de la ciutat colindant.
		ArrayList<String> ciutats = new ArrayList<String>();// Conté les ciutats colindants per cada ciutat.
		String nciutat = "";// Nom de la ciutat principal.
		int[] numeros = new int[3];// Cordenades de la ciutat.
		int cont_f = 0;// Contador de la fila per la matriu.
		int cont_c = 0;// Contador de la columna de la matriu.

		int matriu[][] = new int[48][48];// Matriu adjacent de totes les ciutats.
		String[] ciutatsx = new String[48];// Array amb el nom de totes les ciutats.
		int cont = 0;// Contador utilizat com a index per donar valor al Array ciutatsx.

		Scanner sc = new Scanner(System.in);// Objecte scanner.
		String origen = "";// Ciutat d'origen donada per l'usuari.
		int numOrigen = 0;// Numero indexat a la ciutat d'origen.

		// Es vol guardar les mateixes dades de cada ciutat colindant, per poder
		// calcular la distància entre una ciutat i les seves ciutats colindants.
		ArrayList<String> ciutats2 = new ArrayList<String>();// Ciutats colindants per cada ciutat colindant.
		String nciutat2 = "";// Nom de la ciutat colindant.
		int[] numeros2 = new int[3];// Cordeanades de la ciutat colindant.
		int distancia = 0;// Distancia entre les ciutats

		
		
		
		try {
			// Obrir el fitxer indicat en la variable NomFit
			FileReader fr = new FileReader(NomFit);
			BufferedReader br = new BufferedReader(fr);

			while ((a = br.readLine()) != null) {
				// Separar les dades de cada linia (ciutat)
				nciutat = SepararDatosCiutats(a, ciutats, nciutat, numeros);

				// Per cada linia (ciutat) del txt.
				for (int i = 0; i < ciutats.size(); i++) {
					FileReader fr2 = new FileReader(NomFit);
					BufferedReader br2 = new BufferedReader(fr2);
					b = br2.readLine();
					cont_c = 0;// Reiniciem les columnes per cada nova fila.
					while (b != null && !b.startsWith(ciutats.get(i))) {
						b = br2.readLine();// Busquem la linia del txt per cada ciutat adjacent.
						cont_c++;
					}
					nciutat2 = SepararDatosCiutats(b, ciutats2, nciutat2, numeros2);// Obtenim els valors de la ciutat
																					// adjacent.
					// Calculem la distància
					distancia = CalcularDistancia(numeros, numeros2);
					matriu[cont_f][cont_c] = distancia;// Li donem el valor a la posició de la matriu x(ciutat) y(ciutat
					br2.close();								// colindant). En cas de no ser colindant es guarda un 0
				}
				// Reiniciem Strings i ArrayList per cada nova linia del txt
				nciutat = "";
				ciutats.clear();
				nciutat2 = "";
				ciutats2.clear();
				cont_f++;
			}
			fr.close();
			br.close();
		} catch (IOException e) {
			System.out.println("Error E/S: " + e);
		}

		
//		for (int f = 0; f < 48; f++) { //Mostrar la matriu adjacent per pantalla.
//			for (int c = 0; c < 48; c++) {
//				System.out.print(matriu[f][c] + "|");
//			}
//			System.out.println();
//		}

		try {// Guardar en un array els noms de les ciutats
			FileReader fr = new FileReader(NomFit);
			BufferedReader br = new BufferedReader(fr);

			while ((a = br.readLine()) != null) {
				nciutat = SepararDatosCiutats(a, ciutats, nciutat, numeros);
				ciutatsx[cont] = nciutat;
				cont++;
				nciutat = "";
				ciutats.clear();
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Error E/S: " + e);
		}

		System.out.println("Introdueïx una ciutat d'origen");
		origen = sc.nextLine();

		while (!CiutatValida(origen, ciutatsx)) {
			System.out.println("Introdueïx una ciutat d'origen");
			origen = sc.next();

		}
		for (int i = 0; i < ciutatsx.length; i++) {

			if (origen.equals(ciutatsx[i])) {
				numOrigen = i;
			}
		}
		dijkstra d = new dijkstra();// Crida la clase dijkstra
		d.dijkstra(matriu, numOrigen);// Funció per buscar la ruta mes curta a la resta de ciutats.

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

	public static boolean CiutatValida(String origen, String[] ciutatsx) {

		for (int i = 0; i < ciutatsx.length; i++) {

			if (origen.equals(ciutatsx[i])) {

				return true;
			}
		}
		System.out.println("La ciutat " + origen + " no existeix al joc.");
		return false;
	}
}
