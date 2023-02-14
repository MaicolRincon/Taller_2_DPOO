
public class ProductoMenu {
	
	//Atributos
	
	private String nombre;
	private int precioBase=0;
	private int calorias;
	
	
	//Constructor
	
	public ProductoMenu(String nombre, int precioBase, int calorias) {
		
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.calorias=calorias;
	}


	public String getNombre() {
		return nombre;
	}


	public int getPrecioBase() {
		return precioBase;
	}
	
	public int getCalorias() {
		return calorias;
	}
	

	
	

}
