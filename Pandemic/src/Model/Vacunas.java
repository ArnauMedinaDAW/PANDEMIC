package Model;

public class Vacunas {

	public  String nombre;
	private  String color;
	public  int porcentaje=0;
	
	
	public  Vacunas() {
		
	}

	public Vacunas(String nombre, String color, int p) {
		
		this.nombre = nombre;
		this.color = color;
		this.porcentaje = p;
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
		
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	public int getPorcentaje() {
		return this.porcentaje;
	}

	public  void desarrollarVacuna(int porcentaje) {
		
		this.porcentaje +=porcentaje;
		
		
	}
	
}
