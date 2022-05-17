package Software;
import static org.junit.Assert.*;

import org.junit.Test;

public class ProjectTest {

	@Test
	public void pideLineaTest() {
		GestionClientes gCliente = new GestionClientes();
		
		String result = gCliente.pideLinea("Balbi y Amalia");
		assertEquals("Balbi y Amalia", result);				
	}

}
