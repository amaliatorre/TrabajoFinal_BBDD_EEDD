package Software;
import static org.junit.Assert.*;

import org.junit.Test;
/**
*
* @author Amalia
*/
public class ProjectTest {

	/**
	 * ejemplo de como funciona JUnit
	 */
	public void pideLineaTest() {
		GestionClientes gCliente = new GestionClientes();
		
		String result = gCliente.pideLinea("Balbi y Amalia");
		assertEquals("Balbi y Amalia", result);				
	}

}
