package Pandemic;

import java.io.*;

public class ciudades_enfermedad {

    public static void main(String[] args) {
    	
    	String archivoCiudades = "ciudades.txt";//Ruta del fitxer
        String archivoEnfermedades = "CCP.bin";//Ruta del fitxer
        String archivoSalida = "ciudades-enfermedad.bin";//Ruta del fitxer
        
        int numeroEnfermedad = 0;//Variable int per saber quin num de enfermetat te cada ciutat del fitxer ciudades.txt
        String linea = "";//String per guardar les linies del fitxer ciudades.txt
        String explicacion = "";//String on guardarem la explicació que esta al fitxer CCP.bin
        String ciudad = "";//String on guardarem el nom de cada ciutat
        String enfermedad = "";//String on guardarem el nom de cada enfermetat
        

        try (DataInputStream entradaCCP = new DataInputStream(new BufferedInputStream(new FileInputStream(archivoEnfermedades)));
             BufferedReader brCiudades = new BufferedReader(new FileReader(archivoCiudades));
             DataOutputStream salida = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(archivoSalida)))) {

               //Llegim la primera linia del fitxer CCP.bin que conte la explicacio
               explicacion = entradaCCP.readUTF();
               System.out.println(explicacion);//Ho mostrem per pantalla
               System.out.println();//Canvi de linia

               while ((linea = brCiudades.readLine()) != null) {//Bucle per llegir cada linia del fitxer ciudades.txt
                   String[] partes = linea.split(";");//Creo una string que en cada posicio rebra tot el contingut que trobi fins arribar a ;
                   ciudad = partes[0];//Com que el primer que hi ha es el nom de la ciutat, ciudad rebra el valor de la string partes[0]
                   numeroEnfermedad = Integer.parseInt(partes[1]);//Com que el segon que trobarem es el num de la enfermetat, ho passem a int i
                   //assigno a la variable numeroEnfermedad el contingut de partes[1]  
                   enfermedad = obtenerEnfermedad(numeroEnfermedad);//Cridem a la funcio obtenerEnfermedad i li passem el num de la enfermetat i
                   //ens retorna el nom de la enfermetat en funcio del numero

                   //Escrivim cada ciutat i cada enfermetat al arxiu ciudades-enfermedad.bin
                   salida.writeUTF(ciudad);
                   salida.writeUTF(enfermedad);

                   //Mostrem per pantalla el que hem afegit al fitxer ciudades-enfermedad.bin
                   System.out.println("Ciudad: " + ciudad + ", Enfermedad principal: " + enfermedad);
               }
               
               System.out.println();//Canvi de linia

               System.out.println("Se han guardado las ciudades y sus enfermedades en el archivo '" + archivoSalida + "' correctamente.");
           } catch (IOException e) {
               System.err.println("Error: " + e.getMessage());
           }
       }
    

	   // ************************************************************************************
	   // ** Nombre de la función: obtenerEnfermedad
	   // ** Explicación del que hace la función: En funcio del valor de la variable numeroEnfermedad,
       // ** se li assigna una enfermetat o una altra a la ciutat corresponent
	   // ** Parámetros de entrada: Int (numeroEnfermedad)
	   // ** Parámetros de salida: String (enfermedad)
	   // ************************************************************************************
       private static String obtenerEnfermedad(int numeroEnfermedad) {
    	   
           if (numeroEnfermedad == 0) {//Si el valor es 0
               return "Alfa";//La enfermetat sera Alfa
               
           } else if (numeroEnfermedad == 1) {//Si el valor es 1
               return "Beta";//La enfermetat sera Beta
               
           } else if (numeroEnfermedad == 2) {//Si el valor es 2
               return "Gama";//La enfermetat sera Gama
               
           } else if (numeroEnfermedad == 3) {//Si el valor es 3
               return "Delta";//La enfermetat sera Delta
               
           } else {
               return "Enfermedad Desconocida";
           }
       }
   }