package Pandemic;

public class Vacunas extends DatosPartida{

	private String nombre;
	private String color;
	private float porcentaje;
	
	
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
	
	
	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	public float getPorcentaje() {
		return this.porcentaje;
	}
	
	
	
	public Vacunas(String nombre, String color, float porcentaje) {
		this.nombre = nombre;
		this.color = color;
		this.porcentaje = porcentaje;
	}
	
	
	
	public void desarrolarVacuna(float porcentaje) {
		
	}
}
