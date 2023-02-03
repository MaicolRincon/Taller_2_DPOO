import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Aplicacion {

	static Restaurante restaurante = new Restaurante();
	static ArrayList<Combo> combos;
	static ArrayList<Ingrediente> ingredientes;
	static ArrayList<ProductoMenu> productos;
	static boolean continuar = true;

	static String nombreCliente = "";
	static String direccionCliente = "";
	
	static Pedido pedido;
	

	/*
	 * Método Main donde inicia la aplicación
	 */

	public static void main(String[] args) throws IOException {

		String archivoIngredientes = "./data/ingredientes.txt";
		String archivoProductoMenu = "./data/menu.txt";
		String archivoCombos = "./data/combos.txt";

		restaurante.cargarInformaciónRestaurante(archivoIngredientes, archivoProductoMenu, archivoCombos);
		combos = restaurante.getCombos();
		ingredientes = restaurante.getIngredientes();
		productos = restaurante.getMenuBase();
		
		

		mostrarMenu();

	}

	/*
	 * Muestra el menu por consola
	 */

	public static void mostrarMenu() throws IOException {

		while (continuar) {

			System.out.println("\n ----------- Bienvenido a nuestra aplicación --------\n");
			System.out.println("1. Mostrar el menú");
			System.out.println("2. Iniciar un nuevo pedido");
			System.out.println("3. Agregar un elemento a un pedido");
			System.out.println("4. Cerrar un pedido y guardar la factura");
			System.out.println("5. Consultar la información de un pedido dada su ID");
			System.out.println("6. Salir de la aplicación");

			int opcionSeleccionada = Integer.parseInt(input("\nPor favor seleccione una opción"));

			ejecutarOpcion(opcionSeleccionada);

		}

	}

	/*
	 * Ejecuta la opcion seleccionada
	 */

	public static void ejecutarOpcion(int opcionSeleccionada) throws IOException {

		if (opcionSeleccionada == 1) {
			mostrarMenuProductos();
		} else if (opcionSeleccionada == 2) {
			datosCliente();
			iniciarPedido();
		} else if (opcionSeleccionada == 3) {
			iniciarPedido();
		}else if(opcionSeleccionada==4) {
			pedido.guardarFactura();
			
		}else if(opcionSeleccionada==5) {
			consultarFactura();
		}else {
			continuar=false;
		}

	}
	
	public static void consultarFactura() throws IOException {
		String id = input("\nIngrese el número de la factura que desea consultar");
		String factura = restaurante.getFacturaId(id);
		
		System.out.println(factura);
		
	}

	/*
	 * Muestra el menu al usuario por consola
	 */

	public static void mostrarMenuProductos() {

		System.out.println("\n --------- COMBOS ---------\n");

		for (int i = 0; i < combos.size(); i++) {

			System.out.println(i + 1 + ". " + combos.get(i).getNombre());
		}

		System.out.println("\n --------- Menú ---------\n");

		for (int i = 0; i < productos.size(); i++) {

			System.out.println(i + 1 + ". " + productos.get(i).getNombre());
		}

		System.out.println("\n --------- Ingredientes Adicionales ---------\n");

		for (int i = 0; i < ingredientes.size(); i++) {

			System.out.println(i + 1 + ". " + ingredientes.get(i).getNombre());
		}
	}

	/*
	 * Método para iniciar un nuevo pedido
	 */

	public static void iniciarPedido() {



			System.out.println("\n1. Combos");
			System.out.println("2. Menú");
			System.out.println("3. Ingredientes Adicionales");

			int option = Integer.parseInt(input("\nSeleccione una opcion (Digite 0 para terminar el pedido)"));

			if (option == 1) {

				System.out.println("\n --------- COMBOS ---------\n");

				for (int i = 0; i < combos.size(); i++) {

					System.out.println(i + 1 + ". " + combos.get(i).getNombre());
				}

				int optionCombo = Integer.parseInt(input("\nDigite el número del combo "));
				if (optionCombo <= combos.size() && optionCombo > 0) {
					
					pedido.agregarCombo(combos.get(optionCombo-1));

					System.out.println("\n" + combos.get(optionCombo - 1).getNombre() + " Agregado correctamente");

				} }
			else if (option == 2) {

					System.out.println("\n --------- Menú ---------\n");

					for (int i = 0; i < productos.size(); i++) {

						System.out.println(i + 1 + ". " + productos.get(i).getNombre());
					}
				

				int optionProducto = Integer.parseInt(input("\nDigite el número del producto "));

				if (optionProducto <= productos.size() && optionProducto > 0) {
					
					pedido.agregarProducto(productos.get(optionProducto-1));

					System.out.println("\n" + productos.get(optionProducto - 1).getNombre() + " Agregado correctamente");

				}

			} else if (option == 3) {

				System.out.println("\n --------- Ingredientes ---------\n");

				for (int i = 0; i < ingredientes.size(); i++) {

					System.out.println(i + 1 + ". " + ingredientes.get(i).getNombre());
				}

				int optionIngre = Integer.parseInt(input("\nDigite el número del ingrediente "));

				if (optionIngre <= ingredientes.size() && optionIngre > 0) {
					
					pedido.agregarIngrediente(ingredientes.get(optionIngre-1));

					System.out
							.println("\n" + ingredientes.get(optionIngre - 1).getNombre() + " Agregado correctamente");

				}
			}
		}
	

	/*
	 * Limpia todos los campos de los productos seleccionados en dado caso de que no
	 * se termine alggún pedido con el fin de iniciar uno nuevo desde 0
	 */

	

	public static void datosCliente() {
		nombreCliente = input("\nDigite su nombre");
		direccionCliente = input("Digite su direccion");
		pedido = new Pedido(nombreCliente,direccionCliente);
	}

	/*
	 * Método para ingresar datos por el usuario (Lo obtuve del taller 1)
	 */

	public static String input(String mensaje) {
		try {
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		} catch (IOException e) {
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
}
