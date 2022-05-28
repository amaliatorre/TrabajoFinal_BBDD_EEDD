package Software;
/**
*
* @author Amalia
*/
public abstract class Tabla {
	/*BLOQUE CLIENTE*/
	/**
	 * cte nombre de la tabla clientes
	 */
	protected String NOMBRETABLAC ="clientes";
	/**
	 * cte nombre de la columna clientes id
	 */
	protected String ID="id";
	/**
	 * cte nombre de la columna clientes nombre
	 */
	protected String NOMBRE="nombre";
	/**
	 * cte nombre de la columna clientes ciudad
	 */
	protected String CIUDAD="ciudad";
	
	/*BLOQUE DESCUENTO*/
	/**
	 * cte nombre de la tabla decuento  
	 */
	protected String NOMBRETABLAD ="clientes";
	/**
	 * cte nombre de la columna decuento id  
	 */
	protected String ID_DESC="id_descuento";
	/**
	 * cte nombre de la columna descuento nombre del descuento  
	 */
	protected String TIPOREBAJA="tipo_rebaja";
	/**
	 * cte nombre de la columna decuento del porcentaje del descuento  
	 */
	protected String PORCENTAJE="porcentaje";
	/**
	 * cte nombre de la columna decuento de la fecha de creacion 
	 */
	protected String FECHACREACION="fecha_creacion";
/**
 * constructor null
 */
	public Tabla () {}


	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}


	/**
	 * @return the nOMBRE
	 */
	public String getNOMBRE() {
		return NOMBRE;
	}


	/**
	 * @param nOMBRE the nOMBRE to set
	 */
	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	/**
	 * @return the cIUDAD
	 */
	public String getCIUDAD() {
		return CIUDAD;
	}

	/**
	 * @param cIUDAD the cIUDAD to set
	 */
	public void setCIUDAD(String cIUDAD) {
		CIUDAD = cIUDAD;
	}

	/**
	 * @return the nOMBRETABLAC
	 */
	public String getNOMBRETABLAC() {
		return NOMBRETABLAC;
	}

	/**
	 * @param nOMBRETABLAC the nOMBRETABLAC to set
	 */
	public void setNOMBRETABLAC(String nOMBRETABLAC) {
		NOMBRETABLAC = nOMBRETABLAC;
	}

	
/*BLOQUE DESCEUNTOS*/	
	
	/**
	 * @return the nOMBRETABLAD
	 */
	public String getNOMBRETABLAD() {
		return NOMBRETABLAD;
	}

	/**
	 * @param nOMBRETABLAD the nOMBRETABLAD to set
	 */
	public void setNOMBRETABLAD(String nOMBRETABLAD) {
		NOMBRETABLAD = nOMBRETABLAD;
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
	 * @return the fECHACREACION
	 */
	public String getFECHACREACION() {
		return FECHACREACION;
	}

	/**
	 * @param fECHACREACION the fECHACREACION to set
	 */
	public void setFECHACREACION(String fECHACREACION) {
		FECHACREACION = fECHACREACION;
	}

	/**
	 * Conseguir nombre de la clase
	 * @return nombre de la clase
	 */
	protected abstract String getSimpleName();
	
	

}
