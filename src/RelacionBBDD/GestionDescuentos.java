package RelacionBBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;

import DBManager.DBManager;
import MapeoTablas.Descuentos;

/**
*
* @author Amalia
*/
public class GestionDescuentos 
{
	private static Connection conn = DBManager.mandarConexion();
	 /**
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
		 * constante de la query del procedure descuento 
		 */
		private static final String DB_CLI_SP_DESC = "CALL sp_DescuentosPremium(?)";
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
		if (existDescuento(nombreDescuento))
		{
			System.out.println("El nombre del descuento ya existe, debe de seleccionar otro nombre");
			printTablaDescuentos();
		}
		else
		{
			System.out.println("Diga el % del nuevo descuento");
			porcentaje=ent.nextDouble();
			insertDescuentos(nombreDescuento,porcentaje);
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
	        	
	        PreparedStatement stmt =(PreparedStatement) conn.prepareStatement (DB_DES_SELECT,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); 	
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
				
	        	PreparedStatement stmtDescuento = (PreparedStatement)conn.prepareStatement ( DB_DES_SELECT,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
	            nombreDescuento=nombreDescuento.toUpperCase();
	            // Obtenemos el cliente
	            ResultSet rs = getDescuentoNombre(nombreDescuento);

	            // Si no existe el Resultset
	            if (rs == null) {
	                System.err.println("ERROR no existe este nombre de descuento");
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
