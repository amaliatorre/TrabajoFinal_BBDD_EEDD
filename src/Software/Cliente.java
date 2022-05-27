package Software;
/**
 * 
 * @author amali
 *
 */
public class Cliente 
{
	private int id;
	private String nombre;
	private String ciudad;
	
	/**
	 * Constructor basic limpio
	 */
	public Cliente () {};
	
	/**
	 * Constructor con parametros
	 * @param nombre nombre
	 * @param ciudad ciudad
	 */
	public Cliente (String nombre, String ciudad) 
	{
		this.nombre = nombre;
		this.ciudad =ciudad;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	
	
	
}
