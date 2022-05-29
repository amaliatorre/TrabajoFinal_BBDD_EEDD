package RelacionBBDD;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;	
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import org.junit.Test;

import com.mysql.jdbc.PreparedStatement;

import DBManager.DBManager;
import FuncionesGenerales.FuncionesGenerales;
/**
 * @author  Amalia
 */
public class GestionClientes 
{		

	private static Connection conn = DBManager.mandarConexion();
	 /**
     * ConfiguraciÛn de la tabla Clientes
     */
    /**
     * cte nombre de la tabla clientes
     */
    private static final String DB_CLI = "clientes";
    /**
     * query seleciona toda la tabla clientes
     */
    private static final String DB_CLI_SELECT = "SELECT * FROM " + DB_CLI;
    /**
     * cte nombre de la tabla clientes id
     */
    private static final String DB_CLI_ID = "id";
    /**
     * cte nombre de la tabla clientes nombre
     */
    private static final String DB_CLI_NOM = "nombre";
    /**
     * cte nombre de la tabla clientes ciudad
     */
    private static final String DB_CLI_CIU = "ciudad";
	
	
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
        printTablaClientes();
    }

    /***************/
	/*NUEVO CLIENTE*/
	/***************/
	/**
	 * Pide datos de nombre y direcciÛn y
	 * llama a la funciÛn insertCliente de la clase DBManager
	 * esta devuelve true si se ha podido insertar correctamente y false si no 
	 * Lo muestra por pantalla
	 */
    public static void opcionNuevoCliente() {
        Scanner in = new Scanner(System.in);

        System.out.println("Introduce los datos del nuevo cliente:");
       
        String nombre = FuncionesGenerales.pideLinea("Nombre: ");
        String ciudad = FuncionesGenerales.pideLinea("Ciudad: ");

        nombre=nombre.toUpperCase();
        ciudad = ciudad.toUpperCase();
        boolean res = insertCliente(nombre, ciudad);

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

        printTablaClientes();
        int id = FuncionesGenerales.pideInt("Indica el id del cliente a modificar: ");

        // Comprobamos si existe el cliente
        if (!existsCliente(id)) {
            System.out.println("El cliente " + id + " no existe.");
            return;
        }

        // Mostramos datos del cliente a modificar
        printCliente(id);

        // Solicitamos los nuevos datos
        String nombre = FuncionesGenerales.pideLinea("Nuevo nombre: ");
        String ciudad = FuncionesGenerales.pideLinea("Nueva ciudad: ");
        nombre=nombre.toUpperCase();
        ciudad=ciudad.toUpperCase();
        // Registramos los cambios
        boolean res =updateCliente(id, nombre, ciudad);

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

        printTablaClientes();
        int id = FuncionesGenerales.pideInt("Indica el id del cliente a eliminar: ");

        // Comprobamos si existe el cliente
        if (!existsCliente(id)) {
            System.out.println("El cliente " + id + " no existe.");
            return;
        }

        // Eliminamos el cliente
        boolean res =deleteCliente(id);

        if (res) {
            System.out.println("Cliente eliminado correctamente");
        } else {
            System.out.println("Error :(");
        }
    }
								    /**********************/
								    /*METODOS GENERAL GET */
								    /*********************/ 
										/***********/
										/*GET TABLA*/
										/**********/ 
// Devuelve 
// Los argumentos indican el tipo de ResultSet deseado
/**
* Obtiene toda la tabla clientes de la base de datos
* @param resultSetType Tipo de ResultSet
* @param resultSetConcurrency Concurrencia del ResultSet
* @return ResultSet (del tipo indicado) con la tabla, null en caso de error
*/
public static ResultSet getTablaClientes(int resultSetType, int resultSetConcurrency) {
	try 
	{
		PreparedStatement stmt =(PreparedStatement) conn.prepareStatement (DB_CLI_SELECT,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery(DB_CLI_SELECT);
		return rs;
	} 
	catch (SQLException ex) 
	{
		ex.printStackTrace();
		return null;
	}  
}
/**
* Obtiene toda la tabla clientes de la base de datos
*
* @return ResultSet (por defecto) con la tabla, null en caso de error
*/
public static ResultSet getTablaClientes() 
{
	return getTablaClientes(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
}
								
								/*************/
								/*GET CLIENTE*/
								/************/ 
/**
* Solicita a la BD el cliente con id indicado
* @param id id del cliente
* @return ResultSet con el resultado de la consulta, null en caso de error
*/
public static ResultSet getCliente(int id) 
{
	try 
	{
		Statement stmt =conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		String sql = DB_CLI_SELECT + " WHERE " + DB_CLI_ID + "='" + id + "';";
		ResultSet rs = stmt.executeQuery(sql);
		if (!rs.first()) 
		{
			return null;
		}
			return rs;
	} 
	catch (SQLException ex) 
	{
		ex.printStackTrace();
		return null;
	}
}
/********************************************/
/*BLOQUE USADOS DIRECTOS EN GESTION CLIENTES*/
/********************************************/
/****************************/
/*COMPROBACIONES EXISTENCIA*/
/***************************/
/**
* Comprueba si en la BD existe el cliente con id indicado
*
* @param id id del cliente
* @return verdadero si existe, false en caso contrario
*/
public static boolean existsCliente(int id) 
{
	try 
	{
		ResultSet rs = getCliente(id);
		if (rs == null) 
		{
			return false;
		}
		if (!rs.first()) 
		{
			rs.close();
			return false;
		}
		rs.close();
		return true;
	} 
	catch (SQLException ex) 
	{
		ex.printStackTrace();
		return false;
	}
}
							/****************/
							/*MODIFICACIONES*/
							/****************/
/**
* Solicita a la BD insertar un nuevo registro cliente
*
* @param nombre nombre del cliente
* @param ciudad direcci√≥n del cliente
* @return verdadero si pudo insertarlo, false en caso contrario
*/
public static boolean insertCliente(String nombre, String ciudad) 
{
	try 
	{
		nombre=nombre.toUpperCase();
		ciudad= ciudad.toUpperCase();
		// Obtenemos la tabla clientes
		System.out.print("Insertando cliente " + nombre + "...");
		ResultSet rs = getTablaClientes(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
		
		int filaQueApunta=rs. getRow();
		// Insertamos el nuevo registro
		rs.moveToInsertRow();
		rs.updateString(DB_CLI_NOM, nombre);
		rs.updateString(DB_CLI_CIU, ciudad);
		rs.insertRow();
		rs.moveToCurrentRow();
		
		// Todo bien, cerramos ResultSet y devolvemos true
		rs.close();
		System.out.println("OK!");
		return true;
	} 
	catch (SQLException ex) 
	{
		ex.printStackTrace();
		return false;
	}
}

/**
* Solicita a la BD modificar los datos de un cliente
*
* @param id id del cliente a modificar
* @param nuevoNombre nuevo nombre del cliente
* @param nuevaDireccion nueva direcci√≥n del cliente
* @return verdadero si pudo modificarlo, false en caso contrario
*/
public static boolean updateCliente(int id, String nuevoNombre, String nuevaDireccion) 
{
	try 
	{
		System.out.print("Actualizando cliente " + id + "... ");
		ResultSet rs = getCliente(id);
		if (rs == null) 
		{
			System.out.println("Error. ResultSet null.");
			return false;
		}
		if (rs.first()) 
		{
			rs.updateString(DB_CLI_NOM, nuevoNombre);
			rs.updateString(DB_CLI_CIU, nuevaDireccion);
			rs.updateRow();
			rs.close();
			System.out.println("OK!");
			return true;
		} 
		else 
		{
			System.out.println("ERROR. ResultSet vac√≠o.");
			return false;
		}
	} 
	catch (SQLException ex) 
	{
		ex.printStackTrace();
		return false;
	}
}

/**
* Solicita a la BD eliminar un cliente
*
* @param id id del cliente a eliminar
* @return verdadero si pudo eliminarlo, false en caso contrario
*/
public static boolean deleteCliente(int id) 
{
	try 
	{
		System.out.print("Eliminando cliente " + id + "... ");
		ResultSet rs = getCliente(id);
		if (rs == null) 
		{
			System.out.println("ERROR. ResultSet null.");
			return false;
		}
		if (rs.first()) 
		{
			rs.deleteRow();
			rs.close();
			System.out.println("OK!");
			return true;
		} 
		else 
		{
			System.out.println("ERROR. ResultSet vac√≠o.");
			return false;
		}
	} 
	catch (SQLException ex) 
	{
		ex.printStackTrace();
		return false;
	}
}				
							/*********************/
							/*MOSTRAR INFORMACION*/
							/*********************/
/**
* Imprime por pantalla el contenido de la tabla clientes
*/
public static void printTablaClientes() 
{
	try 
	{
		ResultSet rs = getTablaClientes(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		while (rs.next()) 
		{
			int id = rs.getInt(DB_CLI_ID);
			String n = rs.getString(DB_CLI_NOM);
			String d = rs.getString(DB_CLI_CIU);
			System.out.println(id + "\t" + n + "\t" + d);
		}
		rs.close();
	} 
	catch (SQLException ex) 
	{
		System.err.println(ex);
	}
}

/**
* Imprime los datos del cliente con id indicado
*
* @param id id del cliente
*/
public static void printCliente(int id) 
{
	try 
	{
		ResultSet rs = getCliente(id);
		if (rs == null || !rs.first()) 
		{
			System.out.println("Cliente " + id + " NO EXISTE");
			return;
		}
		int cid = rs.getInt(DB_CLI_ID);
		String nombre = rs.getString(DB_CLI_NOM);
		String direccion = rs.getString(DB_CLI_CIU);
		System.out.println("Cliente " + cid + "\t" + nombre + "\t" + direccion);

	} 
	catch (SQLException ex) 
	{
		System.out.println("Error al solicitar cliente " + id);
	}
}

}  
