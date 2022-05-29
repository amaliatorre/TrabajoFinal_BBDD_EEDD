package Test;
import static org.junit.Assert.*;

import org.junit.Test;

import FuncionesGenerales.FuncionesGenerales;
/**
*
* @author Amalia
*/
public class ProjectTest {

	FuncionesGenerales gCliente = new FuncionesGenerales();
	/**
	 * ejemplo de como funciona JUnit
	 */
	public void pideLineaTest() {
		
		String result = gCliente.pideLinea("message");
		assertEquals("message", result);				
	}
	
	public void pideIntTest() {
		int result = gCliente.pideInt("message");
		assertEquals(0, result);
	}

}
