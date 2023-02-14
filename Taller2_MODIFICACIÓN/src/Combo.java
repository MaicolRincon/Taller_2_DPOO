import java.util.ArrayList;

public class Combo {

	private double descuento;
	private String nombreCombo;
	private ArrayList<ProductoMenu> productosCombo = new ArrayList<ProductoMenu>();

	public Combo(double descuento, String nombreCombo) {

		this.descuento = descuento;
		this.nombreCombo = nombreCombo;
	}

	public void agregarItemACombo(ProductoMenu itemCombo) {
		productosCombo.add(itemCombo);
	}

	public double getPrecio() {

		double precio = 0;
		
		
		
		for(int a=0;a<productosCombo.size();a++) {
			
			precio += productosCombo.get(a).getPrecioBase();
		}
		
		double descuentoValor = precio*descuento/100;
		
		double precioFinal = precio-descuentoValor;
		
		return precioFinal;
	}
	
	public double getCalorias() {

		double calorias = 0;
		
		
		
		for(int a=0;a<productosCombo.size();a++) {
			
			calorias += productosCombo.get(a).getCalorias();
		}
		
		
		return calorias;
	}

	

	public String getNombre() {
		return nombreCombo;
	}

	public ArrayList<ProductoMenu> getProductos() {
		return productosCombo;
	}

}
