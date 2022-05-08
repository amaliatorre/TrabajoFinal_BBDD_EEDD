
import java.util.Scanner;

/**
 *
 * @author 
 */
public class GestionClientes {

	/*MAIN*/
    public static void main(String[] args) 
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
     */
    public static boolean menuPrincipal() 
    {
    	Scanner in = new Scanner(System.in);
    	
        System.out.println("");
        System.out.println("MENU PRINCIPAL");
        System.out.println("1. Listar clientes");
        System.out.println("2. Nuevo cliente");
        System.out.println("3. Modificar cliente");
        System.out.println("4. Eliminar cliente");
        System.out.println("5. Salir");
            
        int opcion = pideInt("Elige una opción: ");
        
        
        switch (opcion) {
            case 1:
                opcionMostrarClientes();
                return false;
            case 2:
                opcionNuevoCliente();
                return false;
            case 3:
                opcionModificarCliente();
                return false;
            case 4:
                opcionEliminarCliente();
                return false;
            case 5:
                return true;
            default:
                System.out.println("Opción elegida incorrecta");
                return false;
        }
        
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

        if (res) {
            System.out.println("Cliente registrado correctamente");
        } else {
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
