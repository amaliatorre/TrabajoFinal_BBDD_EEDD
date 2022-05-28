package Software;
/**
*
* @author Amalia
*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;	
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import org.junit.Test;

import DBManager.DBManager;

/**
 *
 * @author  Amalia
 */
public class GestionClientes 
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
        String urlC = "./clientesInformacion/ClientesTodaInformacion.txt";
        String urlD = "./clientesInformacion/DescuentoTodaInformacion.txt";
    	
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
     

        int opcion = pideInt("Elige una opción: ");

        switch (opcion) 
        {
	        case 0:
	        	validacion=true;
            	return validacion;
	            
            case 1:
                opcionMostrarClientes();
                break;
                
            case 2:
                opcionNuevoCliente();
                break;
            case 3:
                opcionModificarCliente();
                break;
            case 4:
                opcionEliminarCliente();
                break;
            case 5: crearDirectorio();
            		fichero=crearFichero(urlC);
            		ClienteNombreColumnas NombTablaClientes = new ClienteNombreColumnas();
            		guardarEnFichero(fichero, NombTablaClientes);
            	break;
            case 6: 
	            	crearDirectorio();
	        		fichero=crearFichero(urlD);
	        		DescuentoNombreColumnas NombTablaDescuento = new DescuentoNombreColumnas();
            		guardarEnFichero(fichero, NombTablaDescuento);
            	break;	
            case 7: Descuentos.CrearDescuentos();
            	break;
        
            case 8: String ciudad = pideLinea("Diga su ciudad:");
            		ciudad= ciudad.toUpperCase();
            		Descuentos.getDescuento(ciudad);
            		break;

            default:
                System.out.println("Opción elegida incorrecta");
                return false;
        }
		return validacion;
        
    }
    
    @Test
    /**
     * Llamada preparada para crear una comunicacion con junit 
     */
	public void JunitMethod() {
		System.out.println("Test Inicializado....");
	}
	    	/********************/
	    	/*BLOQUE PEDIR DATOS*/
	    	/********************/
    
    /**
     * Bucle infinito hasta que se introduzca un valor valido entero
     * @param mensaje String sobre el mensaje que mostrar al usuario
     * @return un entero introducido por el usuario 
     */
    public static int pideInt(String mensaje)
    {
        /*mientras (true) se utiliza para definir un bucle infinito
         * Aqui sirve al tener el try en su interior para conseguir repetir solo esta script
         * del mensaje y su espera de valor pro parte del usuario de la otra forma repetiria todo el menu*/
        while(true) 
        {
            try 
            {
                System.out.print(mensaje);
                Scanner in = new Scanner(System.in);
                int valor = in.nextInt();
                //in.nextLine();
                return valor;
            } 
            catch (Exception e) 
            {
                System.out.println("No has introducido un número entero. Vuelve a intentarlo.");
            }
        }
    }
    
    /**
     * Bucle infinito hasta que se introduzca un valor valido de caracteres
     * @param mensaje mensaje String sobre el mensaje que mostrar al usuario
     * @return una cadena de carcteres String introducido por el usuario 
     */
    public static String pideLinea(String mensaje)
    {
        while(true) 
        {
            try 
            {
                System.out.print(mensaje);
                Scanner in = new Scanner(System.in);
                String linea = in.nextLine();
                return linea;
            } 
            catch (Exception e) 
            {
                System.out.println("No has introducido una cadena de texto. Vuelve a intentarlo.");
            }
        }
    }
		
		    /****************/
			/*BLOQUE FICHERO*/
			/****************/
    
		/*******************/
		/*CREAR DIRECTORIO*/
		/******************/
		 /* @throws IOException */
    /**Me aseguro que la información se almacene en un directorio conocido y el fichero teng el nombre que quiero para
     * poder ordenar mejor al infromacion y como seguridad por si se olvida de la localizacion y almacenar todo lo referente
     * en ese directorio
     * @throws excepciones*/
    public static void crearDirectorio() throws IOException 
    {

    	String directorioRuta = "./clientesInformacion";
    	Path path = Paths.get(directorioRuta);
    	try
    	{
    		if  (!Files.exists(path))
    		{
    			Files.createDirectory(path);
	        	System.out.println(path.getFileName());
	        	System.out.println("No existe, hemos creado un directorio para almaceanr toda la información relacionada");
	        	System.out.println("Directorio creado !!   "+ directorioRuta);
			}	
    	}
    	catch (IOException e)
    	{
    		e.getStackTrace();
    	}

    }
	    /****************/
		/*CREAR FICHERO*/
		/***************/
    /**
     * 
     * @param url String ruta del fichero a crear
     * @return Fichero creado
     * @throws IOException excepcion 
     */
    public static File crearFichero(String url) throws IOException 
    {
    	String ficheroRuta = url;
    	File fichero = new File(ficheroRuta);
    	try
    	{
	    	if(!fichero.exists())
	    	{
	    		fichero.createNewFile();
	    		System.out.println("Se ha creado el fichero dentro del directorio 'clientesInformacion'");
	    	}
	    	else
	    	{
	    		System.out.println("Ya tienes el fichero creado de antes...");
	    	}
	    	
    	}
    	catch(NullPointerException eNull)
    	{
    		eNull.getStackTrace();
    		System.out.println("Existe un error al instanciar file");
    	}
    	catch(SecurityException eSecurity)
    	{
    		eSecurity.getStackTrace();
    		System.out.println("Existe un error en la existencia del fichero o al crearlo");
    	}
		return fichero;	
    }
    /*********************************/
	/*GUARDAR INFORMACION EN FICHERO*/
	/********************************/
/**
 * Con fichero y el objeto tabla guardo la infromacion en este
 * @param ficheroCreado fichero donde se guardara la info
 *  @param x objeto tipo tabla con los atributos de nombre de las columnas necesarias
 * @throws IOException excepciones 
 */
    public static void guardarEnFichero(File ficheroCreado, Tabla x) throws IOException 
    {
    	
    	try 
    	{
	    	String ruta=ficheroCreado.getAbsolutePath();
	    	FileWriter fw = new FileWriter(ruta, false);
	    	BufferedWriter bw = new BufferedWriter(fw);
	    	
	    	if( x.getSimpleName()=="ClienteNombreColumnas")
	    	{
		        System.out.println("Listado de Clientes:");
		        ResultSet informacionClientes= DBManager.getTablaClientes();
	    
		        while (informacionClientes.next()) 
		        {
		            int id = informacionClientes.getInt(x.getID());
		            String n = informacionClientes.getString(x.getNOMBRE());
		            String c = informacionClientes.getString(x.getCIUDAD());
		            String lineaUsuario=id + ", " + n + ", " + c;
		            fw.write(lineaUsuario + "\n");
		            
		            System.out.println(id + "\t" + n + "\t" + c);
		        }
			        fw.close();
			        informacionClientes.close();
		    	}
	    	
	    	else if (x.getSimpleName()=="DescuentoNombreColumnas")
	    	{
	    		
	    		 System.out.println("Listado de Descuentos:");
			     ResultSet informacionDescuentos= Descuentos.getTablaDescuentos();
		    
			        while (informacionDescuentos.next()) 
			        {
			            int id =  informacionDescuentos.getInt(x.getID_DESC());
			            String n =  informacionDescuentos.getString(x.getTIPOREBAJA());
			            String c = String.valueOf(informacionDescuentos.getDouble(x.getPORCENTAJE()));
			            String f = (informacionDescuentos.getDate(x.getFECHACREACION())).toString();
			            String lineaUsuario=id + ", " + n + ", " + c + ", " + f;
			            fw.write(lineaUsuario + "\n");
			            
			            System.out.println(id + "\t" + n + "\t" + c + "\t" + f );
			        }
				        fw.close();
				        informacionDescuentos.close();
			 }
    	} 
    	catch (SQLException ex) 
    	{
    		ex.printStackTrace();
    	}
       
    }

			/****************/
			/*BLOQUE CLIENTE*/
			/****************/
    
    /*************/
	/*VER CLIENTE*/
	/*************/
    /**
     * Metodo que llama a mostrar tabla cliente de la clase DBManager
     */
    public static void opcionMostrarClientes() 
    {
        System.out.println("Listado de Clientes:");
        DBManager.printTablaClientes();
    }

    /***************/
	/*NUEVO CLIENTE*/
	/***************/
	/**
	 * Pide datos de nombre y dirección y
	 * llama a la función insertCliente de la clase DBManager
	 * esta devuelve true si se ha podido insertar correctamente y false si no 
	 * Lo muestra por pantalla
	 */
    public static void opcionNuevoCliente() {
        Scanner in = new Scanner(System.in);

        System.out.println("Introduce los datos del nuevo cliente:");
       
        String nombre = pideLinea("Nombre: ");
        String direccion = pideLinea("Dirección: ");

        boolean res = DBManager.insertCliente(nombre, direccion);

        if (res) 
        {
            System.out.println("Cliente registrado correctamente");
        } 
        else 
        {
            System.out.println("Error :(");
        }
    }

    /********************/
	/*MODIFICAR CLIENTE*/
	/*******************/
    /**
     * pide los datos necesarios para modificacion del cliente en BBDD
     * comprobando y mostrando para conocer id
     */
    public static void opcionModificarCliente() {
        Scanner in = new Scanner(System.in);

        DBManager.printTablaClientes();
        int id = pideInt("Indica el id del cliente a modificar: ");

        // Comprobamos si existe el cliente
        if (!DBManager.existsCliente(id)) {
            System.out.println("El cliente " + id + " no existe.");
            return;
        }

        // Mostramos datos del cliente a modificar
        DBManager.printCliente(id);

        // Solicitamos los nuevos datos
        String nombre = pideLinea("Nuevo nombre: ");
        String direccion = pideLinea("Nueva dirección: ");

        // Registramos los cambios
        boolean res = DBManager.updateCliente(id, nombre, direccion);

        if (res) {
            System.out.println("Cliente modificado correctamente");
        } else {
            System.out.println("Error :(");
        }
    }

    /********************/
   	/*ELIMINAR CLIENTE*/
   	/*******************/
    /**
     * Comprueba llamando al metodo existsCliente de la clase DBManager 
     * y al metodo deleteCliente de la clase DBManager
     */
    public static void opcionEliminarCliente() {
        Scanner in = new Scanner(System.in);

        int id = pideInt("Indica el id del cliente a eliminar: ");

        // Comprobamos si existe el cliente
        if (!DBManager.existsCliente(id)) {
            System.out.println("El cliente " + id + " no existe.");
            return;
        }

        // Eliminamos el cliente
        boolean res = DBManager.deleteCliente(id);

        if (res) {
            System.out.println("Cliente eliminado correctamente");
        } else {
            System.out.println("Error :(");
        }
    }
}
