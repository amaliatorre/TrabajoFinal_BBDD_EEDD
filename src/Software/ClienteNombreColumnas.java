package Software;
/**
*
* @author Amalia
*/
public class ClienteNombreColumnas extends Tabla

{
	/**
	 * cte nombre de la tabla clientes
	 */
	private String NOMBRETABLA ="clientes";
	/**
	 * cte nombre de la columna clientes id
	 */
	private String ID="id";
	
	/**
	 * cte nombre de la columna clientes nombre
	 */
	private String NOMBRE="nombre";
	/**
	 * cte nombre de la columna clientes ciudad
	 */
	private String CIUDAD="ciudad";
	/**
	 * constructor null ya que extiende de tabla 
	 */
	public ClienteNombreColumnas () {}

	/**
	 * constructor tabla cliente
	 * @param NOMBRETABLA String nombre de la tabla 
	 * @param ID String con el nombre de la columna id
	 * @param NOMBRE  String con el nombre de la columna nombre
	 * @param CIUDAD String con el nombre de la columna ciudad
	 */
		public ClienteNombreColumnas (String NOMBRETABLA, String ID, String NOMBRE, String CIUDAD  ) 
		{
			this.NOMBRETABLAC=NOMBRETABLA;
			this.ID=ID;
			this.NOMBRE=NOMBRE;
			this.CIUDAD=CIUDAD;
			
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
		this.NOMBRETABLA =nOMBRETABLA;
	}

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
	 * Conseguir nombre de la clase
	 * @return nombre de la clase
	 */
	public String getSimpleName()
	{
		return "ClienteNombreColumnas";
	}
	
	
	
}
