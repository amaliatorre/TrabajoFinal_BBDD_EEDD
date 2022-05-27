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
public class Descuentos {
<<<<<<< Updated upstream
	
	
=======
	private static final String DB_DES_ID="id_descuento";
	private static final String DB_DES_PORC="porcentaje";
	private static final String DB_DES_REB="tipo_rebaja";
	private static final String DB_DES ="descuento";
	private static final String DB_DES_SELECT="SELECT * FROM " + DB_DES;
>>>>>>> Stashed changes
	
	private int id_descuento;
	private double porcentaje;
	private String tipo_rebajas;
	
	public boolean validacionLoad;
	public boolean validacionConnect;
	public static Connection conexion = (Connection) DBManager.mandarConexion();
	private static final String DB_CLI_SP_DESC = "CALL sp_DescuentosPremium(?)";
	
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
	
<<<<<<< Updated upstream
	public void CrearDescuentos()
=======
	
	/*BLOQUE METODOS*/
	public static void CrearDescuentos() 
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
	public static ResultSet getTablaClientes(int resultSetType, int resultSetConcurrency) {
		String DB_DES ="descuento";
		String DB_CLI_SELECT="SELECT * FROM " + DB_DES;
=======
	
	
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
		
>>>>>>> Stashed changes

		try 
	       {
	        	
	        PreparedStatement stmt =(PreparedStatement) conexion.prepareStatement (DB_CLI_SELECT,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	        	
	        //Statement stmt = conn.createStatement(resultSetType, resultSetConcurrency);
	       /*CONSULTA SQL --> db_cli_select = SELECT* FROM clientes*/
	        	
	        ResultSet rs = stmt.executeQuery(DB_CLI_SELECT);
	          
	        return rs;
	       } 
	       catch (SQLException ex) 
	       {
	           ex.printStackTrace();
	           return null;
	       }  
	    }
	
<<<<<<< Updated upstream
	
=======
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
	                System.out.println("Tu descuento por ciudad es de: " + rsSp.getInt("Descuento"));                	
	            }
	           
	            return rsSp;

	        } catch (SQLException ex) {
	            System.err.println(ex);
	            return null;
	        }
	    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
>>>>>>> Stashed changes
}
