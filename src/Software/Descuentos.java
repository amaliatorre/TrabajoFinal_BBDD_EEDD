package Software;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import DBManager.DBManager;

import java.sql.ResultSet;
public class Descuentos {
	private static String DB_DES_ID="id_descuento";
	private static String DB_DES_PORC="porcentaje";
	private static String DB_DES_REB="tipo_rebaja";
	private static String DB_DES ="descuento";
	private static String DB_DES_SELECT="SELECT * FROM " + DB_DES;
	
	private int id_descuento;
	private double porcentaje;
	private String tipo_rebajas;
	
	public boolean validacionLoad;
	public boolean validacionConnect;
	public static Connection conexion = (Connection) DBManager.mandarConexion();

	public Descuentos() {}
	
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
	public void CrearDescuentos()
	{
		
		 
		
		
	}
	
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

	
	public static ResultSet getTablaDescuentos(int resultSetType, int resultSetConcurrency) {
		

		try 
	       {
	        	
	        PreparedStatement stmt =(PreparedStatement) conexion.prepareStatement (DB_DES_SELECT,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	        	
	        //Statement stmt = conn.createStatement(resultSetType, resultSetConcurrency);
	       /*CONSULTA SQL --> db_cli_select = SELECT* FROM clientes*/
	        	
	        ResultSet rs = stmt.executeQuery(DB_DES_SELECT);
	          
	        return rs;
	       } 
	       catch (SQLException ex) 
	       {
	           ex.printStackTrace();
	           return null;
	       }  
	    }
	
	/*CRUD*/
	 public static boolean insertDescuentos(String nombreDescuento, double porcentaje) {
    	 	boolean validacion=true;
	        try {
	          
	            System.out.print("Insertando nuevo de descuento " + nombreDescuento + "...");
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

	 public static boolean existDescuento(String nombreDescuento) 
	 {
		 boolean resultado=true;
		 try 
		 {
			 ResultSet rs = getTablaDescuentos(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			 while (rs.next()) 
			 {
	              String reb = rs.getString(DB_DES_REB);
	              if(reb==nombreDescuento)
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
	 public static ResultSet getDescuentoNombre(String busquedaDni) 
		{
			try 
			{			        	
				
	        	PreparedStatement stmtTablaCliente = (PreparedStatement)conexion.prepareStatement ( DB_DES_SELECT,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	        	
	        	String query = DB_DES_SELECT + " WHERE dni = '" + busquedaDni + "'";
	        	
	        	try 
	        	{
	        		ResultSet rs = stmtTablaCliente.executeQuery(query);
	        		rs.first();
	        		String clienteNombre = rs.getString(DB_DES_REB);
	        		System.out.println("SUCCESS - Se ha encontrado el cliente con DNI: " + busquedaDni + " " + clienteNombre);	
	        		return rs;
	        	}
	        	catch(Exception ex) 
	        	{
	        		System.out.println("No existe este uuario en nuestra base de datos...");
	        	}		
			} 
			catch (SQLException ex) 
			{
				ex.printStackTrace();
				System.out.println("Existe un problema al realizar la conexión con BBDD");			
			}
			
			return null;
		}
	  
	 public static boolean deleteDescuento(String nombreDescuento) {
	        try {
	            System.out.print("Eliminando cliente " + nombreDescuento + "... ");
	            nombreDescuento=nombreDescuento.toLowerCase();
	            // Obtenemos el cliente
	            ResultSet rs = getDescuentoNombre(nombreDescuento);

	            // Si no existe el Resultset
	            if (rs == null) {
	                System.out.println("ERROR. ResultSet null.");
	                return false;
	            }
	            if (rs.first()) {
	                rs.deleteRow();
	                rs.close();
	                System.out.println("OK!");
	                return true;
	            } else {
	                System.out.println("ERROR. ResultSet vacÃ­o.");
	                return false;
	            }

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return false;
	        }
	    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
