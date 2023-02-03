
public class Ingrediente {

	private String nombre;
	private int costoAdicional;
	
	/*
	 * Constructor
	 */
	public Ingrediente(String nombre, int costoAdicional) {
		this.costoAdicional=costoAdicional;
		this.nombre=nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCostoAdicional() {
		return costoAdicional;
	}
	
	
}
