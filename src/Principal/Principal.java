package Principal;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import DBManager.DBManager;
import Ficheros.Ficheros;
import FuncionesGenerales.FuncionesGenerales;
import MapeoTablas.Descuentos;
import MapeoTablasNombre.ClienteNombreColumnas;
import MapeoTablasNombre.DescuentoNombreColumnas;
import RelacionBBDD.GestionClientes;
import RelacionBBDD.GestionDescuentos;
import SQLGeneral.SQLGeneral;

/**
 * @author  Amalia
 */
public class Principal 
{

	/*******/
	/*MAIN*/
	/*******/
public static void main(String[] args) throws IOException
{	
	boolean validacionLoad, validacionConnect;
	validacionLoad= DBManager.loadDriver();
	validacionConnect =DBManager.connect();
	boolean salir = false;
	do 
	{
		salir = menuPrincipal();
	} 
		while (!salir);
		DBManager.close();
}
							/************/
							/*FUNCIONES*/
							/************/
						/************/
						/*BLOQUE MENU*/
						/************/
/**
* Menú prinicpal 
* @return true para salir del menu y false para continuar en el 
* @throws IOException excepciones
*/
	public static boolean menuPrincipal() throws IOException 
	{
		Scanner in = new Scanner(System.in);
		boolean validacion=false;
		String busquedaDireccion;
		File fichero;
		String urlC = "./InformacionBBDD/ClientesTodaInformacion.txt";
		String urlD = "./InformacionBBDD/DescuentoTodaInformacion.txt";
		
		System.out.println("");
		System.out.println("MENU PRINCIPAL");
		System.out.println("0. Exit");
		System.out.println("1. Listar clientes");
		System.out.println("2. Nuevo cliente");
		System.out.println("3. Modificar cliente");
		System.out.println("4. Eliminar cliente");
		System.out.println("5. Guardar información de lista clientes en el fichero");
		System.out.println("6. Guardar información de lista Descuentos en el fichero");
		System.out.println("7. Crear Descuentos ");
		System.out.println("8. Descubrir que descuento tiene su ciudad!!!");
		System.out.println("9. Borrar Descuentos");
		System.out.println("10. Crear tabla virgen");
		
		
		int opcion = FuncionesGenerales.pideInt("Elige una opción: ");
		
		switch (opcion) 
		{
			case 0:
			validacion=true;
			return validacion;
			
			case 1:
					GestionClientes.opcionMostrarClientes();
				break;
			
			case 2:
					GestionClientes.opcionNuevoCliente();
				break;
			case 3:
					GestionClientes.opcionModificarCliente();
				break;
			case 4:
					GestionClientes.opcionEliminarCliente();
				break;
			case 5: Ficheros.crearDirectorio();
					fichero=Ficheros.crearFichero(urlC);
					ClienteNombreColumnas NombTablaClientes = new ClienteNombreColumnas();
					Ficheros.guardarEnFichero(fichero, NombTablaClientes);
				break;
			case 6: 
					Ficheros.crearDirectorio();
					fichero=Ficheros.crearFichero(urlD);
					DescuentoNombreColumnas NombTablaDescuento = new DescuentoNombreColumnas();
					Ficheros.guardarEnFichero(fichero, NombTablaDescuento);
				break;	
			case 7: GestionDescuentos.CrearDescuentos();
				break;
			
			case 8: String ciudad = FuncionesGenerales.pideLinea("Diga su ciudad:");
					ciudad= ciudad.toUpperCase();
					GestionDescuentos.getDescuento(ciudad);
				break;
			case 9:
					String nombreDesc=FuncionesGenerales.pideLinea("Diga el descuento que quiere eliminar:");
					nombreDesc=nombreDesc.toUpperCase();
					GestionDescuentos.deleteDescuento(nombreDesc);
				break;
			case 10: 
					String nombre = FuncionesGenerales.pideLinea("Diga el nombre de la tabla:");
					String id = FuncionesGenerales.pideLinea("Diga el nombre del id la tabla:");            	
					SQLGeneral.crearTablaVirgen(nombre, id);
				break;
			default:
					System.out.println("Opción elegida incorrecta");
					return false;
				
		}
		return validacion;
	
	}
}
