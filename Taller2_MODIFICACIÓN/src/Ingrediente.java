
public class Ingrediente {

	private String nombre;
	private int costoAdicional;
	private int calorias;
	
	/*
	 * Constructor
	 */
	public Ingrediente(String nombre, int costoAdicional, int calorias) {
		this.costoAdicional=costoAdicional;
		this.nombre=nombre;
		this.calorias=calorias;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCostoAdicional() {
		return costoAdicional;
	}
	
	public int getCalorias() {
		return calorias;
	}
	
	
}
