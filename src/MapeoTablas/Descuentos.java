package MapeoTablas;
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
	


}
