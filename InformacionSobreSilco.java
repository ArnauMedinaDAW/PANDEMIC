package CAP_1;

import java.util.ArrayList;

public class InformacionSobreSilco {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String x = "San Francisco;0;235,315;Chicago,Los Angeles,Manila,Tokio;";

		SepararDatosCiutats(x);

	}

	// ************************************************************************************
	// ** Nombre de la función: SepararDatosCiutats
	// ** Explicación del que hace la función: Donada una entrada de text, guarda el
	// nom de la primera ciutat en un String, els 3 següents numeros en un array[]
	// int . A continuació guarda els noms de tantes ciutats com apareguin dins d'un
	// ArrayList.
	// ** Parámetros de entrada: String (entrada de text)
	// ** Parámetros de salida: Nul
	// ************************************************************************************
	public static void SepararDatosCiutats(String x) {

		ArrayList<String> palabras = new ArrayList<String>();
		String nciutat = "";
		int[] numeros = new int[3];
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
					palabras.add(conv);
					conv = "";
				}
			}
		}
		//es mostra per pantalla
		System.out.println(nciutat);
		System.out.println(numeros[0] + " " + numeros[1] + " " + numeros[2]);
		for (int i = 0; i < palabras.size(); i++) {
			System.out.println(palabras.get(i));
		}
	}
}
