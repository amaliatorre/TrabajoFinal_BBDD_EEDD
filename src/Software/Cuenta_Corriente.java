package Software;
import java.util.Date;

public class Cuenta_Corriente 
{
	public Integer id_Cuenta;
	private double saldo;

	public String numeroCuenta;
	public String dni;	
	public Date fechaAlta_Cuenta;
	public Date fechaBaja_Cuenta;
	
	public Cuenta_Corriente (){}
	
	public Cuenta_Corriente (String numeroCuenta, String dni)
	{
		this.numeroCuenta=numeroCuenta;
		this.dni=dni;
	}
/*GET*/
	/**
	 * @return the saldo
	 */
	public double getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	/**
	 * @return the id_Cuenta
	 */
	public Integer getId_Cuenta() {
		return id_Cuenta;
	}

	/**
	 * @param id_Cuenta the id_Cuenta to set
	 */
	public void setId_Cuenta(Integer id_Cuenta) {
		this.id_Cuenta = id_Cuenta;
	}

	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the fechaAlta_Cuenta
	 */
	public Date getFechaAlta_Cuenta() {
		return fechaAlta_Cuenta;
	}

	/**
	 * @param fechaAlta_Cuenta the fechaAlta_Cuenta to set
	 */
	public void setFechaAlta_Cuenta(Date fechaAlta_Cuenta) {
		this.fechaAlta_Cuenta = fechaAlta_Cuenta;
	}

	/**
	 * @return the fechaBaja_Cuenta
	 */
	public Date getFechaBaja_Cuenta() {
		return fechaBaja_Cuenta;
	}

	/**
	 * @param fechaBaja_Cuenta the fechaBaja_Cuenta to set
	 */
	public void setFechaBaja_Cuenta(Date fechaBaja_Cuenta) {
		this.fechaBaja_Cuenta = fechaBaja_Cuenta;
	}

/*IMPRIMIR*/
	public void informacionCuentas ()
	{
		System.out.println("Nº Cuenta: " + getNumeroCuenta());
		System.out.println("DNI: " + getDni());
	}
	
	
	
	
}
