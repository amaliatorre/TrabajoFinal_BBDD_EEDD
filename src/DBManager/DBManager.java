package DBManager;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import MapeoTablas.Cliente;

import java.sql.ResultSet;

/**
 *
 * @author Amalia
 */
public class DBManager {

    /**
     * Conexión a la base de datos
     */
    public static Connection conn = null;

    
    //String url = "jdbc:mysql://localhost:3306/demodb?useUnicode=true&characterEncoding=UTF-8";
    /**
     * Configuración de la conexión a la base de datos
     */
    /**
     * cte nombre de host 
     */
    private static final String DB_HOST = "localhost";
    /**
     * cte nombre del puerto 
     */
    private static final String DB_PORT = "3306";
    /**
     * cte nombre de la DDBB 
     */
    private static final String DB_NAME = "tiendadb";
    /**
     * url con codigo del driver , host, puerto y configuracion de codificacion caracteres 
     */
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?useUnicode=true&characterEncoding=UTF-8"  ;
    /**
     * cte nombre de usuario 
     */
    private static final String DB_USER = "admin";
    /**
     * cte nombre de la constraseña
     */
    private static final String DB_PASS = "Admin1234";
    /**
     * cte mensaje conexion correcta
     */
    private static final String DB_MSQ_CONN_OK = "CONEXIÓN CORRECTA";
    /**
     * cte mensaje conexion fallida
     */
    private static final String DB_MSQ_CONN_NO = "ERROR EN LA CONEXIÓN";

   
    
				    /****************/
				    /*CONEXION BBDD */
				    /***************/ 
    
    /***************/
    /*CARGAR DRIVER*/
    /**************/ 
    /**
     * Intenta cargar el JDBC driver.
     * @return true si pudo cargar el driver, false en caso contrario
     */
	public static boolean loadDriver() {
        try {
            System.out.print("Cargando Driver...");
            Class.forName("com.mysql.jdbc.Driver"); 
           
            //System.out.println("OK!");
            return true;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
	
	/***************/
    /*CONEXION BBDD*/
    /**************/ 
    /**
     * Intenta conectar con la base de datos.
     *
     * @return true si pudo conectarse, false en caso contrario
     */
    public static boolean connect() {
        try {
            System.out.print("Conectando a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("OK!");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
    
    /***************************/
    /*COMPROBACION CONEXION BBDD*/
    /***************************/ 
    
    /**
     * Comprueba la conexión y muestra su estado por pantalla
     *
     * @return true si la conexión existe y es válida, false en caso contrario
     */
    public static boolean isConnected() {
        // Comprobamos estado de la conexión
        try {
            if (conn != null && conn.isValid(0)) {
                System.out.println(DB_MSQ_CONN_OK);
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(DB_MSQ_CONN_NO);
            ex.printStackTrace();
            return false;
        }
    }
    /*****************/
    /*CERRAR CONEXION*/
    /****************/ 
    
    /**
     * Cierra la conexión con la base de datos
     */
    public static void close() {
        try {
            System.out.print("Cerrando la conexión...");
            conn.close();
            System.out.println("OK!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /*************/
    /*MANDAR CONN*/
    /************/
    /**
     * crea el objeto conexion a traves del Drivermanager con el driver, url usser y passwd
     * @return connection 
     */ 
    public static Connection mandarConexion()
    {
    	Connection conexion = null;
    	loadDriver();
    	if(connect())
    	{
    		try
    		{
    			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    		}
    		catch(SQLException ex)
    		{
    			System.out.println("Hay un problema con la conexion a la base de datos");
    		}
    	}
    	return conexion=conn;
    }
 
}					