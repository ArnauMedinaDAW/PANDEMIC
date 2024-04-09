package Pandemic;

public class Ciudad extends DatosPartida {

	
	private String nombre;
	private int[] coordenadas;
	private String enfermedad;
	private int infeccion;
	private String[] ciudadesColindantes;
	
	
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
	
	
	
	public Ciudad(String nombre, int[] coordenadas, String enfermedad, int infeccion, String[] ciudadesColindantes) {
		this.nombre = nombre;
		this.coordenadas = coordenadas;
		this.enfermedad = enfermedad;
		this.infeccion = infeccion;
		this.ciudadesColindantes = ciudadesColindantes;
	}
	
	
	
	public void aumentarInfeccion() {
		
	}
	
	
	public void disminuirInfeccion() {
		
	}
	
	
	public void propagarInfeccion() {
		
	}	
	
}
