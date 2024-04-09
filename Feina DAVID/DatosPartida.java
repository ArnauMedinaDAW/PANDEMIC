package Pandemic;

import java.util.*;

public class DatosPartida {

	
	private ArrayList<Ciudad> ciudades;
	private ArrayList<Virus> virus;
	private ArrayList<Vacunas> vacunas;
	
	private int brotes;
	private int rondas;
	private float pDesarrollo;
	private int acciones;
	
	
	public void setCiudades(ArrayList<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}
	
	public ArrayList<Ciudad> getCiudades() {
		return this.ciudades;
	}
	
	
	public void setVirus(ArrayList<Virus> virus) {
		this.virus = virus;
	}
	
	public ArrayList<Virus> getVirus() {
		return this.virus;
	}	
	
	
	public void setVacunas(ArrayList<Vacunas> vacunas) {
		this.vacunas = vacunas;
	}
	
	public ArrayList<Vacunas> getVacunas() {
		return this.vacunas;
	}
	
	
	public void setBrotes(int brotes) {
		this.brotes = brotes;
	}
	
	public int getBrotes() {
		return this.brotes;
	}
	
	
	public void setRondas(int rondas) {
		this.rondas = rondas;
	}
	
	public int getRondas() {
		return this.rondas;
	}
	
	
	public void setPDesarrollo(float pDesarrollo) {
		this.pDesarrollo = pDesarrollo;
	}
	
	public float getPDesarrollo() {
		return this.pDesarrollo;
	}
	
	
	public void setAcciones(int acciones) {
		this.acciones = acciones;
	}
	
	public int getAcciones() {
		return this.acciones;
	}
	
	
	
	public DatosPartida(ArrayList<Ciudad> ciudades, ArrayList<Virus> virus, ArrayList<Vacunas> vacunas, int brotes, int rondas, float pDesarrollo, int acciones) {
		this.ciudades = ciudades;
		this.virus = virus;
		this.vacunas = vacunas;
		this.brotes = brotes;
		this.rondas = rondas;
		this.pDesarrollo = pDesarrollo;
		this.acciones = acciones;
	}
	
	
	
	public void modificarCiudad(String nCiudad, int modificacion) {
		
	}
	
	
	public void modificarVacuna(String nVacuna, float modificacion) {
		
	}
	
	
	public void cargarDatos() {
		
	}
	
}
