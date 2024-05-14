package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Ciudad {

	public  String nombre;
	public int[] coordenadas;
	public  String enfermedad;
	public int infeccion=0;
	public String[] ciudadesColindantes;
	
	public Ciudad() {
		
	}
	
	public Ciudad(String nombre, int[] coordenadas, String enfermedad, int infeccion, String[] ciudadesColindantes) {
		this.nombre = nombre;
		this.coordenadas = coordenadas;
		this.enfermedad = enfermedad;
		this.infeccion = infeccion;
		this.ciudadesColindantes = ciudadesColindantes;
	}
	
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}  
	
	
	public void setCoordenadas(int[] coordenadas) {
		this.coordenadas = coordenadas;
	}
	
	public int[] getCoordenadas() {
		return this.coordenadas;
	}
	
	
	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}
	
	public String getEnfermedad() {
		return this.enfermedad;
	}
	
	
	public void setInfeccion(int infeccion) {
		this.infeccion = infeccion;
	}
	
	public int getInfeccion() {
		return this.infeccion;
	}
	
	
	public void setCiudadesColindantes(String[] ciudadesColindantes) {
		this.ciudadesColindantes = ciudadesColindantes;
	}
	
	public String[] getCiudadesColindantes() {
		return this.ciudadesColindantes;
	}
	
	

	
	public void aumentarInfeccion() {
		this.infeccion++;
	}
	
	
	public void disminuirInfeccion() {
		this.infeccion--;
	}
	
	
	public void propagarInfeccion(ArrayList<Ciudad> x) {
		
		if(this.infeccion==3) {
			for(int i =0; i<this.ciudadesColindantes.length;i++) {
				
				for(int j =0; i<48;j++) {
								
				if(this.ciudadesColindantes[i].equals(x.get(j).nombre)) {
					x.get(j).setEnfermedad(this.enfermedad);
					x.get(j).aumentarInfeccion();
				}
					
				}
				
				
			}
		}

	}


	@Override
	public String toString() {
		return "Ciudad [nombre=" + nombre + ", coordenadas=" + Arrays.toString(coordenadas) + ", enfermedad="
				+ enfermedad + ", infeccion=" + infeccion + ", ciudadesColindantes="
				+ Arrays.toString(ciudadesColindantes) + "]";
	}	
	
}
