package Software;
import java.util.Scanner;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.PreparedStatement;
import DBManager.DBManager;

import java.sql.ResultSet;
/**
*
* @author Amalia
*/
public class Descuentos {
/**
 * cte nombre de la columna id_descuento
 */
	private static final String DB_DES_ID="id_descuento";
	/**
	 * cte nombre de la columna porcentaje
	 */	
	private static final String DB_DES_PORC="porcentaje";
	/**
	 * cte nombre de la columna tipo_rebaja
	 */	
	private static final String DB_DES_REB="tipo_rebaja";
	/**
	 * cte nombre de la columna descuento
	 */	
	private static final String DB_DES ="descuento";
	/**
	 * cte query para visualizar toda la tabla descuentos
	 */	
	private static final String DB_DES_SELECT="SELECT * FROM " + DB_DES;

	/**
	 * clave primaria de la tabal descuento 
	 */
	private int id_descuento;
	/**
	 * double numero de porcentaje del descuento 
	 */
	private double porcentaje;
	/**
	 * String nombre de la rebaja
	 */
	private String tipo_rebajas;
	/**
	 * Boolean para validar la carga del driver
	 */
	public boolean validacionLoad;
	/**
	 * Boolean para validar la carga la conexion con la based de datos
	 */
	public boolean validacionConnect;
	/**
	 * constante de la query del procedure desceunto 
	 */
	private static final String DB_CLI_SP_DESC = "CALL sp_DescuentosPremium(?)";
	/**
	 * obtiene el objeto connection del DBManager
	 */
	public static Connection conexion = (Connection) DBManager.mandarConexion();
	
	/**
	 * Constructor null
	 */
	public Descuentos() {}
	
	/**
	 * 
	 * @param porcentaje double cantidad a rebajar %
	 * @param tipo_rebajas String nombre descuento 
	 */
	public Descuentos(double porcentaje, String tipo_rebajas) 
	{
		this.porcentaje=porcentaje;
		this.tipo_rebajas=tipo_rebajas;	
	}

	/**
	 * @return the id_descuento
	 */
	public int getId_descuento() {
		return id_descuento;
	}

	/**
	 * @param id_descuento the id_descuento to set
	 */
	public void setId_descuento(int id_descuento) {
		this.id_descuento = id_descuento;
	}

	/**
	 * @return the porcentaje
	 */
	public double getPorcentaje() {
		return porcentaje;
	}

	/**
	 * @param porcentaje the porcentaje to set
	 */
	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	/**
	 * @return the tipo_rebajas
	 */
	public String getTipo_rebajas() {
		return tipo_rebajas;
	}

	/**
	 * @param tipo_rebajas the tipo_rebajas to set
	 */
	public void setTipo_rebajas(String tipo_rebajas) {
		this.tipo_rebajas = tipo_rebajas;
	}
	

	
	/*BLOQUE METODOS*/
/**
 * Crea nuevos descuentos en la tabla descuentos DDBB  
 */
	public static void CrearDescuentos() 
	{
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
			System.out.println("Diga el % del nuevo descuento");
			porcentaje=ent.nextDouble();
			Descuentos.insertDescuentos(nombreDescuento,porcentaje);
		}
	}
/**
 * Ver la tabla exiatente de descuentos
 */
	 public static void printTablaDescuentos() {
	        try {
	        	System.out.println("Tabla descuentos");
	            ResultSet rs = getTablaDescuentos(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
	            while (rs.next()) {
	                int id = rs.getInt(DB_DES_ID);
	                double porc = rs.getDouble(DB_DES_PORC);
	                String reb = rs.getString(DB_DES_REB);
	                System.out.println("ID" + id + "\t" + porc +"%" + "\t" + "Status " + reb);
	            }
	            rs.close();
	        } catch (SQLException ex) {
	        	System.out.println("Ha habido un error al imprimir la tabal de descuentos");
	        }
	    }

	/**
	 * Obtiene toda la informacion de BBDD de la tabla descuentos
	 * @param resultSetType int 
	 * @param resultSetConcurrency int 
	 * @return ResultSet con la informacion de tabla descuentos 
	 */
	public static ResultSet getTablaDescuentos(int resultSetType, int resultSetConcurrency) 
	{
		try 
	       {
	        	
	        PreparedStatement stmt =(PreparedStatement) conexion.prepareStatement (DB_DES_SELECT,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); 	
	        ResultSet rs = stmt.executeQuery(DB_DES_SELECT);
	          
	        return rs;
	       } 
	       catch (SQLException ex) 
	       {
	           ex.printStackTrace();
	           return null;
	       }  
	    }

	/**
	 * Acceso generico a la BBDD de la tabla descuentos 
	 * @return ResultSet toda la informacion filas de la tabla descuentos
	 * */
	 public static ResultSet getTablaDescuentos() {
	      ResultSet tabla = getTablaDescuentos(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		 return tabla;
	    }
	
	/*CRUD*/
	/**
	 * Inserta un descuento nuevo 
	 * @param nombreDescuento String 
	 * @param porcentaje double 
	 * @return true si se ha podidio insertar en BBDD y false si no 
	 */
	 public static boolean insertDescuentos(String nombreDescuento, double porcentaje) {
    	 	boolean validacion=true;
	        try {
	          
	            System.out.print("Insertando nuevo de descuento " + nombreDescuento + "...");
	            nombreDescuento.toUpperCase();
	            ResultSet rs = getTablaDescuentos(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
	     
	            int filaQueApunta=rs. getRow();
	            System.out.println(filaQueApunta);
	            // Insertamos el nuevo registro
	            rs.moveToInsertRow();
	            rs.updateString(DB_DES_REB, nombreDescuento);
	            rs.updateDouble(DB_DES_PORC, porcentaje);
	            rs.insertRow();
	            rs.moveToCurrentRow();

	            // Todo bien, cerramos ResultSet y devolvemos true
	            rs.close();
	            System.out.println("OK!");
	            return true;

	        } catch (SQLException ex) {
	        	System.out.print("Existe un problema insertar el descuento");
	            return false;
	        }
	    }
/**
 * Comprobacion de nombre de descuento si existe en  BBDD
 * @param nombreDescuento String nombre de decuento 
 * @return true si ya existe y false si no existe
 */
	 public static boolean existDescuento(String nombreDescuento) 
	 {
		 boolean resultado=false;
		 try 
		 {
			 ResultSet rs = getTablaDescuentos(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			 while (rs.next()) 
			 {
	              String reb = rs.getString(DB_DES_REB);
	              if(reb.equalsIgnoreCase(nombreDescuento))
	              {
	            	  System.out.println("El nombre del descuento ya existe");
	            	  resultado=true;
	              }
	         }
	            rs.close();
	         
		 }
		 catch(SQLException e)
		 {
			 resultado =false;
		 }
		
		 return resultado;
 
	 }
	 /**
	  * 
	  * @param nombreDesc String con el nombre del descuento 
	  * @return Resultset con la fila del descuento 
	  */
	 public static ResultSet getDescuentoNombre(String nombreDesc) 
		{
			try 
			{			        	
				
	        	PreparedStatement stmtDescuento = (PreparedStatement)conexion.prepareStatement ( DB_DES_SELECT,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	        	String query = DB_DES_SELECT + " WHERE tipo_rebaja = '" + nombreDesc + "'";
	        	
	        	try 
	        	{
	        		ResultSet rs = stmtDescuento.executeQuery(query);
	        		rs.first();
	        		String desc = rs.getString(DB_DES_REB);
	        		System.out.println("SUCCESS - Se ha encontrado el descuento " + nombreDesc );	
	        		return rs;
	        	}
	        	catch(Exception ex) 
	        	{
	        		System.out.println("No existe este nombre de descuento en nuestra base de datos...");
	        	}		
			} 
			catch (SQLException ex) 
			{
				ex.printStackTrace();
				System.out.println("Existe un problema al realizar la conexión con BBDD");			
			}
			
			return null;
		}
	 /**
	  * Borra descuento de la tabla de BBDD descuento   
	  * @param nombreDescuento String 
	  * @return true si se ha podido realizar y false si no 
	  */
	 public static boolean deleteDescuento(String nombreDescuento) {
	        try {
	            System.out.print("Eliminando descuento " + nombreDescuento + "... ");
	            nombreDescuento=nombreDescuento.toLowerCase();
	            // Obtenemos el cliente
	            ResultSet rs = getDescuentoNombre(nombreDescuento);

	            // Si no existe el Resultset
	            if (rs == null) {
	                System.err.println("ERROR  en descuento");
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
	                System.err.println("ERROR. Resultado vacio descuento.");
	                return false;
	            }

	        } catch (SQLException ex) {
	            System.err.println("ERROR No se pudo eliminar el descuento");
	            return false;
	        }
	    }
	 
	 /**
	  * Obtinee la tabla de descuentos BBDD por el filtro de ciudad
	  * @param ciudad String 
	  * @return ResultSet con las filas coincidentendes con ciudad
	  */
	 public static ResultSet getDescuento(String ciudad) 
	 {
		java.sql.Connection  conn = DBManager.mandarConexion();
	        try 
	        {
	            String SQL = DB_CLI_SP_DESC;

	            CallableStatement cstmt = (CallableStatement) conn.prepareCall(SQL);
	            cstmt.setString(1, ciudad);
	            ResultSet rsSp = cstmt.executeQuery();
	            if(rsSp.first()) 
	            {
	                System.out.println("Tu descuento por ciudad es de: " + rsSp.getInt("Descuento") + "%");                	
	            }
	           
	            return rsSp;

	        } 
	        catch (SQLException ex) 
	        {
	            System.err.println(ex);
	            return null;
	        }
	    }
}
