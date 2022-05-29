package Ficheros;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;

import MapeoTablas.Descuentos;
import MapeoTablasNombre.Tabla;
import RelacionBBDD.GestionClientes;
import RelacionBBDD.GestionDescuentos;


/**
 * @author  Amalia
 */
public class Ficheros 
{

	
    /****************/
	/*BLOQUE FICHERO*/
	/****************/

/*******************/
/*CREAR DIRECTORIO*/
/******************/
 /* @throws IOException */
/**Me aseguro que la información se almacene en un directorio conocido y el fichero teng el nombre que quiero para
* poder ordenar mejor al infromacion y como seguridad por si se olvida de la localizacion y almacenar todo lo referente
* en ese directorio
* @throws excepciones*/
public static void crearDirectorio() throws IOException 
{

String directorioRuta = "./InformacionBBDD";
Path path = Paths.get(directorioRuta);
try
{
	if  (!Files.exists(path))
	{
		Files.createDirectory(path);
    	System.out.println(path.getFileName());
    	System.out.println("No existe, hemos creado un directorio para almaceanr toda la información relacionada");
    	System.out.println("Directorio creado !!   "+ directorioRuta);
	}	
}
catch (IOException e)
{
	e.getStackTrace();
}

}
/****************/
/*CREAR FICHERO*/
/***************/
/**
* 
* @param url String ruta del fichero a crear
* @return Fichero creado
* @throws IOException excepcion 
*/
public static File crearFichero(String url) throws IOException 
{
String ficheroRuta = url;
File fichero = new File(ficheroRuta);
try
{
	if(!fichero.exists())
	{
		fichero.createNewFile();
		System.out.println("Se ha creado el fichero dentro del directorio 'InformacionBBDD'");
	}
	else
	{
		System.out.println("Ya tienes el fichero creado de antes...");
	}
	
}
catch(NullPointerException eNull)
{
	eNull.getStackTrace();
	System.out.println("Existe un error al instanciar file");
}
catch(SecurityException eSecurity)
{
	eSecurity.getStackTrace();
	System.out.println("Existe un error en la existencia del fichero o al crearlo");
}
return fichero;	
}
/*********************************/
/*GUARDAR INFORMACION EN FICHERO*/
/********************************/
/**
* Con fichero y el objeto tabla guardo la infromacion en este
* @param ficheroCreado fichero donde se guardara la info
*  @param x objeto tipo tabla con los atributos de nombre de las columnas necesarias
* @throws IOException excepciones 
*/
public static void guardarEnFichero(File ficheroCreado, Tabla x) throws IOException 
{

try 
{
	String ruta=ficheroCreado.getAbsolutePath();
	FileWriter fw = new FileWriter(ruta, false);
	BufferedWriter bw = new BufferedWriter(fw);
	
	if( x.getSimpleName()=="ClienteNombreColumnas")
	{
        System.out.println("Listado de Clientes:");
        ResultSet informacionClientes= GestionClientes.getTablaClientes();

        while (informacionClientes.next()) 
        {
            int id = informacionClientes.getInt(x.getID());
            String n = informacionClientes.getString(x.getNOMBRE());
            String c = informacionClientes.getString(x.getCIUDAD());
            String lineaUsuario=id + ", " + n + ", " + c;
            fw.write(lineaUsuario + "\n");
            
            System.out.println(id + "\t" + n + "\t" + c);
        }
	        fw.close();
	        informacionClientes.close();
    	}
	
	else if (x.getSimpleName()=="DescuentoNombreColumnas")
	{
		
		 System.out.println("Listado de Descuentos:");
	     ResultSet informacionDescuentos= GestionDescuentos.getTablaDescuentos();
    
	        while (informacionDescuentos.next()) 
	        {
	            int id =  informacionDescuentos.getInt(x.getID_DESC());
	            String n =  informacionDescuentos.getString(x.getTIPOREBAJA());
	            String c = String.valueOf(informacionDescuentos.getDouble(x.getPORCENTAJE()));
	            String f = (informacionDescuentos.getDate(x.getFECHACREACION())).toString();
	            String lineaUsuario=id + ", " + n + ", " + c + ", " + f;
	            fw.write(lineaUsuario + "\n");
	            
	            System.out.println(id + "\t" + n + "\t" + c + "\t" + f );
	        }
		        fw.close();
		        informacionDescuentos.close();
	 }
} 
catch (SQLException ex) 
{
	ex.printStackTrace();
}

}
	
	
	
	
	
}
