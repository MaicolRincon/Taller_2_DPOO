
public class ProductoMenu {
	
	//Atributos
	
	private String nombre;
	private int precioBase=0;
	
	
	//Constructor
	
	public ProductoMenu(String nombre, int precioBase) {
		
		this.nombre = nombre;
		this.precioBase = precioBase;
	}


	public String getNombre() {
		return nombre;
	}


	public int getPrecioBase() {
		return precioBase;
	}
	

	
	

}
