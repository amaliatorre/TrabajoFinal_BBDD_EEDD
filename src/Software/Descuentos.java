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
	
	public void CrearDescuentos()
	{
		
		 
		
		
	}
	public static ResultSet getTablaClientes(int resultSetType, int resultSetConcurrency) {
		String DB_DES ="descuento";
		String DB_CLI_SELECT="SELECT * FROM " + DB_DES;

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
	
	
}
