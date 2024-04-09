package Pandemic;

public class Virus extends DatosPartida{
	
	private String identificador;
	private String nombre;
	private String color;
	
	
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
	public String getidentificador() {
		return this.identificador;
	}  
	
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}  
	
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return this.color;
	}
	
	
	
	public Virus(String identificador, String nombre, String color) {
		this.identificador = identificador;
		this.nombre = nombre;
		this.color = color;
	}

}
