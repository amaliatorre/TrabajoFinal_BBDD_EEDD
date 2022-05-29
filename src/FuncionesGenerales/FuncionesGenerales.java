package FuncionesGenerales;
import java.util.Scanner;
/**
 * @author  Amalia
 */
public class FuncionesGenerales 
{
	
	public static Scanner ent = new Scanner(System.in);
	public static Scanner in = new Scanner(System.in);
/**
 * constructor null
 */
	public FuncionesGenerales (){}
								/*****************/
								/*BLOQUE SCANNER*/
								/****************/
/**
* Bucle infinito hasta que se introduzca un valor valido entero
* @param mensaje String sobre el mensaje que mostrar al usuario
* @return un entero introducido por el usuario 
*/
public static int pideInt(String mensaje)
{
/*mientras (true) se utiliza para definir un bucle infinito
* Aqui sirve al tener el try en su interior para conseguir repetir solo esta script
* del mensaje y su espera de valor pro parte del usuario de la otra forma repetiria todo el menu*/
	while(true) 
	{
		try 
		{
			System.out.print(mensaje);
			
			int valor = in.nextInt();
			return valor;
		} 
		catch (Exception e) 
		{
			System.out.println("No has introducido un número entero. Vuelve a intentarlo.");
		}
	}
}

/**
* Bucle infinito hasta que se introduzca un valor valido de caracteres
* @param mensaje mensaje String sobre el mensaje que mostrar al usuario
* @return una cadena de carcteres String introducido por el usuario 
*/
public static String pideLinea(String mensaje)
{
	while(true) 
	{
		try 
		{
			System.out.print(mensaje);
			String linea = ent.nextLine();
			return linea;
		} 
		catch (Exception e) 
		{
			System.out.println("No has introducido una cadena de texto. Vuelve a intentarlo.");
		}
	}
}
	



	
	
	
}
