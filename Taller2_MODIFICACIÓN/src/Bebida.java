
public class Bebida {
	
	private String nombre;
	private int precio=0;
	private int calorias;
	
	
	Bebida(String nombre, int precio, int calorias){
		this.nombre=nombre;
		this.precio=precio;
		this.calorias=calorias;
	}


	public String getNombre() {
		return nombre;
	}


	public int getPrecioBase() {
		return precio;
	}
	
	public int getCalorias() {
		return calorias;
	}
	
	

}
