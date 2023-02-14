import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

public class Restaurante {

	ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
	ArrayList<ProductoMenu> productosMenu = new ArrayList<ProductoMenu>();
	ArrayList<Combo> combos = new ArrayList<Combo>();
	private ArrayList<Bebida> bebidas = new ArrayList<Bebida>();

	/*
	 * Constructor
	 */
	public Restaurante() {

	}

	/*
	 * Retorna el menú disponible en un ArrayList de tipo Producto
	 */

	public ArrayList<ProductoMenu> getMenuBase() {
		return productosMenu;
	}

	/*
	 * Retorna un Arraylist con todos los ingredientes disponibles
	 */
	public ArrayList<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	/*
	 * Retorna un Arraylist con todos los combos disponibles
	 */
	public ArrayList<Combo> getCombos() {
		return combos;
	}

	/*
	 * Lee y carga toda la información del restuarante dado unos archivos de texto
	 */
	public void cargarInformaciónRestaurante(String archivoIngredientes, String archivoMenu, String archivoCombos,
			String archivoBebidas) throws IOException {

		cargarMenu(archivoMenu);
		cargarIngredientes(archivoIngredientes);
		cargarBebidas(archivoBebidas);

		cargarCombos(archivoCombos);

		
	}

	/*
	 * Carga la información de los ingredientes
	 */
	private void cargarIngredientes(String archivoIngredientes) throws IOException {

		FileReader file = new FileReader(archivoIngredientes);
		BufferedReader br = new BufferedReader(file);

		String linea = br.readLine();

		while (linea != null) {
			String[] partes = linea.split(";");

			String nombreIngrediente = partes[0];
			String precio = partes[1];
			int calorias = Integer.parseInt(partes[2]);

			int precio2 = Integer.parseInt(precio);
			

			Ingrediente ingrediente = new Ingrediente(nombreIngrediente, precio2,calorias);
			ingredientes.add(ingrediente);

			linea = br.readLine();

		}

		br.close();

	}

	/*
	 * Carga el menu
	 */
	private void cargarMenu(String archivoMenu) throws IOException {

		FileReader file = new FileReader(archivoMenu);
		BufferedReader br = new BufferedReader(file);

		String linea = br.readLine();

		while (linea != null) {
			String[] partes = linea.split(";");

			String nombreProducto = partes[0];
			String precio = partes[1];
			int calorias = Integer.parseInt(partes[2]);

			int precio2 = Integer.parseInt(precio);

			ProductoMenu prod = new ProductoMenu(nombreProducto, precio2, calorias);
			productosMenu.add(prod);

			linea = br.readLine();

		}

		br.close();

	}

	/*
	 * cargar Combos
	 */
	private void cargarCombos(String archivoCombos) throws IOException {

		FileReader file = new FileReader(archivoCombos);
		BufferedReader br = new BufferedReader(file);

		String linea = br.readLine();

		while (linea != null) {
			String[] partes = linea.split(";");

			String nombreCombo = partes[0];
			double descuento = Double.parseDouble(partes[1]);
			String hamburguesa = partes[2];
			String papas = partes[3];
			String bebida = partes[4];

			Combo combo = new Combo(descuento, nombreCombo);

			combo.agregarItemACombo(darProductoMenu(hamburguesa));
			combo.agregarItemACombo(darProductoMenu(papas));
			combo.agregarItemACombo(darProductoMenu(bebida));

			combos.add(combo);

			linea = br.readLine();

		}

		br.close();
	}

	private void cargarBebidas(String archivoBebidas) throws IOException {

		FileReader file = new FileReader(archivoBebidas);
		BufferedReader br = new BufferedReader(file);

		String linea = br.readLine();

		while (linea != null) {
			String[] partes = linea.split(";");

			String nombre = partes[0];
			int precio = Integer.parseInt(partes[1]);
			int calorias = Integer.parseInt(partes[2]);

			Bebida bebida = new Bebida(nombre, precio,calorias);

			bebidas.add(bebida);

			linea = br.readLine();

		}

		br.close();
	}

	private ProductoMenu darProductoMenu(String producto) {
		
		ProductoMenu pr = null;

		for (int i = 0; i < productosMenu.size(); i++) {

			if (productosMenu.get(i).getNombre().equals(producto)) {

				pr = productosMenu.get(i);
			}
		}

		for (int i = 0; i < bebidas.size(); i++) {
			
			if (bebidas.get(i).getNombre().equals(producto)) {
				
				
				
				pr= new ProductoMenu(bebidas.get(i).getNombre(),bebidas.get(i).getPrecioBase(),bebidas.get(i).getCalorias());
				
			}
		}

		return pr;

	}

	/*
	 * Retorna la factura de un pedido dado su id
	 */

	public String getFacturaId(String id) throws IOException {
		String message = "";

		try {

			FileReader file = new FileReader("./data/facturas/Factura_" + id + ".txt");
			BufferedReader br = new BufferedReader(file);

			String linea = br.readLine();

			while (linea != null) {

				message += linea;
				message += "\n";

				linea = br.readLine();

			}

			br.close();

		} catch (FileAlreadyExistsException e) {
			System.out.println("\nArchivo no encontrado");
		}

		return message;
	}

	public ArrayList<Bebida> getBebidas() {
		return bebidas;
	}

}
