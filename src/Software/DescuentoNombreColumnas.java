package Software;
/**
*
* @author Amalia
*/
public class DescuentoNombreColumnas extends Tabla
{
	/**
	 * cte nombre de la tabla decuento  
	 */
	private String NOMBRETABLA ="descuento";
	/**
	 * cte nombre de la columna decuento id  
	 */
	private String ID_DESC="id_descuento";
	/**
	 * cte nombre de la columna decuento nombre del descuento  
	 */
	private String TIPOREBAJA="tipo_rebaja";
	/**
	 * cte nombre de la columna decuento del porcentaje del descuento  
	 */
	private String PORCENTAJE="porcentaje";
	
	/**
	 * constructor null
	 */
	public DescuentoNombreColumnas () {}
	/**
	 * constructor de la tabla descuento 
	 * @param NOMBRETABLA String nombre de la tabla
	 * @param ID_DESC String nombre de la columna id
	 * @param TIPOREBAJA String nombre de la columna del nombre del descuento 
	 * @param PORCENTAJE String nombre de la columna porcentaje del descuento 
	 */
	public DescuentoNombreColumnas (String NOMBRETABLA, String ID_DESC,String TIPOREBAJA, String PORCENTAJE) 
	{
		this.NOMBRETABLA=NOMBRETABLA;
		this.ID_DESC=ID_DESC;
		this.TIPOREBAJA=TIPOREBAJA;
		this.PORCENTAJE=PORCENTAJE;	
	}

	/**
	 * @return the nOMBRETABLA
	 */
	public String getNOMBRETABLA() {
		return NOMBRETABLA;
	}

	/**
	 * @param nOMBRETABLA the nOMBRETABLA to set
	 */
	public void setNOMBRETABLA(String nOMBRETABLA) {
		NOMBRETABLA = nOMBRETABLA;
	}

	/**
	 * @return the iD_DESC
	 */
	public String getID_DESC() {
		return ID_DESC;
	}

	/**
	 * @param iD_DESC the iD_DESC to set
	 */
	public void setID_DESC(String iD_DESC) {
		ID_DESC = iD_DESC;
	}

	/**
	 * @return the tIPOREBAJA
	 */
	public String getTIPOREBAJA() {
		return TIPOREBAJA;
	}

	/**
	 * @param tIPOREBAJA the tIPOREBAJA to set
	 */
	public void setTIPOREBAJA(String tIPOREBAJA) {
		TIPOREBAJA = tIPOREBAJA;
	}

	/**
	 * @return the pORCENTAJE
	 */
	public String getPORCENTAJE() {
		return PORCENTAJE;
	}

	/**
	 * @param pORCENTAJE the pORCENTAJE to set
	 */
	public void setPORCENTAJE(String pORCENTAJE) {
		PORCENTAJE = pORCENTAJE;
	}
	
	
	/**
	 * Conseguir nombre de la clase
	 * @return nombre de la clase
	 */
	public String getSimpleName()
	{
		return "DescuentoNombreColumnas";
	}
	
	
	
	
}
