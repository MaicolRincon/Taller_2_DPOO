
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Pedido {


	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<ProductoMenu> productosMenu;
	private ArrayList<Combo> combos;
	private ArrayList<Ingrediente> ingredientes;

	// Constructor

	public Pedido(String nombreCliente, String direccionCliente) {

		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.productosMenu = new ArrayList<ProductoMenu>();
		this.combos = new ArrayList<Combo>();
		this.ingredientes = new ArrayList<Ingrediente>();

	}

	public int getIdPedido() {
		return idPedido;
	}

	public void agregarProducto(ProductoMenu nuevoItem) {
		productosMenu.add(nuevoItem);
	}

	public void agregarCombo(Combo nuevoCombo) {
		combos.add(nuevoCombo);
	}

	public void agregarIngrediente(Ingrediente nuevoIngre) {
		ingredientes.add(nuevoIngre);
	}

	private double getPrecioNetoPedido() {

		double precioNeto = 0;

		if (combos.size() > 0) {

			for (int i = 0; i < combos.size(); i++) {
				precioNeto += combos.get(i).getPrecio();
				System.out.println("SI ENTRO");
			}
		}

		if (productosMenu.size() > 0) {

			for (int i = 0; i < productosMenu.size(); i++) {
				precioNeto += productosMenu.get(i).getPrecioBase();
			}
		}
		
		if (ingredientes.size() > 0) {

			for (int i = 0; i < ingredientes.size(); i++) {
				precioNeto += ingredientes.get(i).getCostoAdicional();
			}
		}
		
		return precioNeto;

	}

	private double getPrecioTotalPedido(double precioNeto, double IVA) {
		return precioNeto+IVA;

	}

	private double getPrecioIVAPedido(double precioNeto) {
		
		return precioNeto*0.19;

	}

	private String generarTextoFactura(int idFactura) {

		String todosLosProductos = "";
		double precioNeto=getPrecioNetoPedido();
		double IVA = getPrecioIVAPedido(precioNeto);
		double total = getPrecioTotalPedido(precioNeto,IVA);

		if (combos.size() > 0) {
			todosLosProductos += "\n --- Combos ----\n";

			for (int i = 0; i < combos.size(); i++) {
				todosLosProductos += combos.get(i).getNombre() + "\n";
			}
		}

		if (productosMenu.size() > 0) {
			todosLosProductos += "\n --- Productos ----\n";

			for (int i = 0; i < productosMenu.size(); i++) {
				todosLosProductos += productosMenu.get(i).getNombre() + "\n";
			}
		}

		if (ingredientes.size() > 0) {
			todosLosProductos += "\n --- Ingredientes Adicionales ----\n";

			for (int i = 0; i < ingredientes.size(); i++) {
				todosLosProductos += ingredientes.get(i).getNombre() + "\n";
			}
		}

		String texto = "\nFactura N° " + idFactura
				+ "\n________________________________________________________________\n" + "       Nombre Cliente: "
				+ nombreCliente + "\n       Dirección: " + direccionCliente
				+ "\n________________________________________________________________\n" + "Productos adquiridos:\n"
				+ todosLosProductos+
				"\n________________________________________________________________\n"+
				"\n                                      Precio Neto: " + precioNeto+
				"\n                                       Precio IVA: " + IVA+
				"\n----------------------------------------------------------------"+
				"\n                                     Precio Total: " + total;
		
		return texto;

	}

	public void guardarFactura() throws IOException {

		int idFactura = generarIdFactura();
		String texto= generarTextoFactura(idFactura);

		BufferedWriter factura = new BufferedWriter(new FileWriter("./data/facturas/Factura_" + idFactura + ".txt"));
		factura.write(texto);
		factura.close();
		
		System.out.println(texto);
		
		System.out.println("\n **Factura Guardada en el sistema**");

	}

	private int generarIdFactura() throws IOException {
		int id = 101;
		boolean idEncontrado = false;

		while (idEncontrado == false) {

			String idFactura = "Factura_" + id + ".txt";

			File file = new File("./data/facturas/" + idFactura);

			if (file.createNewFile()) {
				System.out.println("Factura N°" + id);
				idEncontrado = true;
			} else {
				id += 1;
			}
		}
		return id;
	}
	
	

}
