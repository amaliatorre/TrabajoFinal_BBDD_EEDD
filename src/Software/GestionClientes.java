package Software;

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
 * @author 
 */
public class GestionClientes {
				/*******/
				/*MAIN*/
				/*******/
	
	@Test
	public void JunitMethod() {
		System.out.println("Test Inicializado....");
	}
	
	
	
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
     * @throws IOException 
     */
    public static boolean menuPrincipal() throws IOException 
    {
    	Scanner in = new Scanner(System.in);
    	
        System.out.println("");
        System.out.println("MENU PRINCIPAL");
        System.out.println("0. Listar clientes");
        System.out.println("1. Listar clientes");
        System.out.println("2. Nuevo cliente");
        System.out.println("3. Modificar cliente");
        System.out.println("4. Eliminar cliente");
        System.out.println("5. Guardar información de lista clientes en el fichero");
        System.out.println("6. Crear Descuentos nuevos");
        System.out.println("7. SALIR");
        
        boolean validacion=false;
        String busquedaDireccion;
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
            		File fichero=crearFichero();
            		guardarEnFichero(fichero);
            	break;
            case 6: 
           
            Scanner ent = new Scanner(System.in);
            Descuentos descuentos = new Descuentos ();
            String nombreDescuento;
            double porcentaje;
            System.out.println("Diga el nombre del nuevo descuento");
            nombreDescuento=ent.nextLine();
           if (Descuentos.existDescuento(nombreDescuento))
           {
        	   System.out.println("El nombre del descuento ya existe, debe de seleccionar otro nombre");
        	   Descuentos.printTablaDescuentos();
           }
           else
           {
        	   System.out.println("Diga el nombre del nuevo descuento");
               porcentaje=ent.nextDouble();
        	   Descuentos.insertDescuentos(nombreDescuento,porcentaje);
           }
            
            
         
            
            
            
            
            
            	break;
            case 7: 
            	
   
            default:
                System.out.println("Opción elegida incorrecta");
                return false;
        }
		return validacion;
        
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
        /*while (true) se utiliza para definir un bucle infinito
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
     * en ese directorio*/
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
    public static File crearFichero() throws IOException 
    {
    	String ficheroRuta = "./clientesInformacion/ClientesTodaInformacion.txt";
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

    public static void guardarEnFichero(File ficheroCreado) throws IOException 
    {
    	final String DB_CLI_ID = "id";
        final String DB_CLI_NOM = "nombre";
        final String DB_CLI_DIR = "direccion";
    	try 
    	{
	    	String ruta=ficheroCreado.getAbsolutePath();
	    	FileWriter fw = new FileWriter(ruta, false);
	    	BufferedWriter bw = new BufferedWriter(fw);
	    	
    	
	        System.out.println("Listado de Clientes:");
	        ResultSet informacionClientes= DBManager.getTablaClientes();
    
	        while (informacionClientes.next()) 
	        {
	            int id = informacionClientes.getInt(DB_CLI_ID);
	            String n = informacionClientes.getString(DB_CLI_NOM);
	            String d = informacionClientes.getString(DB_CLI_DIR);
	            String lineaUsuario=id + ", " + n + ", " + d;
	            fw.write(lineaUsuario + "\n");
	            
	            System.out.println(id + "\t" + n + "\t" + d);
	        }
	        fw.close();
	        informacionClientes.close();
        
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
     * 1 parte:
     * Pide datos de ID llamadno al metodo pideInt
     * llama la metodo existCliente de la clase DBManager
     * si es false muestra por pantalla un mensaje de error
     * si es true muestra los datos llamando a printCliente de la clase DBManager
     * 2parte:
     * llama al metodo updateClientes de la clase DBManager
     * si es true muestra correcto
     * si es false hay un error en la modificacion 
     * 
     */
    public static void opcionModificarCliente() {
        Scanner in = new Scanner(System.in);

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
