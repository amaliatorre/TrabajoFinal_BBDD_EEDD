package Software;
/**
 * 
 * @author amalia
 *
 */
public class Cliente  
{
	/**
	 * parametro int del id de la clase cliente
	 */
	private int id;
	/**
	 * parametro String de nombre de la clase cliente
	 */
	private String nombre;
	/**
	 * parametro String de la ciudad de la clase cliente
	 */
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
