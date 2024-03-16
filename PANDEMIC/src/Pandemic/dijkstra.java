package Pandemic;

import java.io.*;
import java.util.ArrayList;

public class dijkstra {

	static final int V = 48;

	int minDistance(int dist[], Boolean sptSet[]) {
		int min = Integer.MAX_VALUE, min_index = -1;

		for (int v = 0; v < V; v++)
			if (sptSet[v] == false && dist[v] <= min) {
				min = dist[v];
				min_index = v;
			}

		return min_index;
	}

	void printSolution(int dist[]) {
		String a = "";
		ArrayList<String> ciutats = new ArrayList<String>(); // Conté les ciutats colindants per cada ciutat
		String nciutat = ""; // Nom de la ciutat principal
		int[] numeros = new int[3]; // Cordenades de la ciutat

		// Format de taula
		System.out.printf("%-20s %s%n", "Vertex", "Distance from Source");
		// %-20s especifica que la primera columna debe tener un ancho de campo de 20
		// caracteres y que se alineará a la izquierda.
		// %d especifica que la segunda columna contiene un entero.
		//%n se utiliza para una nueva línea, es equivalente a \n.

		try {
			FileReader fr = new FileReader("ciudades.txt");
			BufferedReader br = new BufferedReader(fr);

			for (int i = 0; i < V; i++) {
				a = br.readLine();
				nciutat = SepararDatosCiutats(a, ciutats, nciutat, numeros);
				System.out.printf("%-20s %d%n", nciutat, dist[i]);
				nciutat = "";
			}

		} catch (IOException e) {
			System.out.println("Error E/S: " + e);
		}
	}

	void dijkstra(int graph[][], int src) {
		int dist[] = new int[V];
		Boolean sptSet[] = new Boolean[V];

		for (int i = 0; i < V; i++) {
			dist[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		}

		dist[src] = 0;

		for (int count = 0; count < V - 1; count++) {
			int u = minDistance(dist, sptSet);
			sptSet[u] = true;

			for (int v = 0; v < V; v++)
				if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
					dist[v] = dist[u] + graph[u][v];
		}

		printSolution(dist);
	}

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
}