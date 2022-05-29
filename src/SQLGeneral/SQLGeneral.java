package SQLGeneral;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DBManager.DBManager;

public class SQLGeneral 
{
	private static Connection conn = DBManager.mandarConexion();
	
	public static void crearTablaVirgen(String nombre, String id)
	{	
		String query ="CREATE table " + nombre + "( `" + id + "` int NOT NULL AUTO_INCREMENT, PRIMARY KEY ("+ id+"));";
		try 
		{
			
			Statement stmt =conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); 
			stmt.execute(query); 
			stmt.close();	           
		} 
		catch (SQLException sqle) 
		{ 
			System.out.println("Error en la ejecución: " + sqle.getErrorCode() + ", " + sqle.getMessage());    
		}

	}
}
