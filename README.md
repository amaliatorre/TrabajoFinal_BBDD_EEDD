# TrabajoFinal_BBDD_EEDD

#RECORRIDO POR TRABAJO
1. CREACION DE GitHub y ISSUES
  1.A Label necesarios nuevos y existentes para clasificar 
  1.B Issue #1 --> primeras ideas de organizacion e Isuues 
  1.C Issues #7 --> Crear ramas
                * Version mejora: Mejor uso de ramas 
                * Version mejora: Interfaz grafica 
                
2. Buscar fallos del codigo Original
  2.A CONECTION BBDD 
    2.A1 Issues #21 y #22  -->  rs.moveToInsertRow()  soluc. AUTO_INCREMENT
    2.A2 Issue #17 Class.forName(new com.mysql.jdbc.Driver ();).newInstance(); (Obsoleto) --> soluc. Class.forName("com.mysql.jdbc.Driver"); +
                   codifiicacion caracteres --> solc. url + ?useUnicode=true&characterEncoding=UTF-8
    2 A3 Issue #20  class path y librerias  --> liberias de Jnuit / Driver MySql
  
 3. BBDD 
    3 A1 Issue #14 Configurar base de datos con usuario contraseña solo para proyecto en MySql  
    3 A2  Issue #11 --> Procedimiento almacenado y correlación con nueva función
   
 4. Proyecto
    4.A1  Issue #16 Corrección ortográfica
    4.A2  Issue #8 --> Dividir por paquetes
    4.A3  Issue #18 y #10 PrepareStatement
                  * Version mejora: Conexion a cualqueir BBDD y cualqueir tabla  #24
    4.A4 Correguir Menu Original 
                  
    4.B1  Issues #5 --> Nuevas funciones soluc. punto 7 y 8  
    4.B2 Issues #12 --> get por atributos ciudad por al tabla Clientes
    4.B3 Issue #13 Ficheros --> Crear directorio que contenga todos los ficheros y eleccion de la tabla a guardar mediante clases con nombres CTE de los campos 
                          * Version mejora: opcion borrar fichero completo
  
    4.B4 Issue #23 y #6 -->  * Version mejora: Clasificar funciones con clases diferenciadas y refactorizar y casos de regresión 
  
 5. Doumentación
    5.A1 Issues #2 Docuemtnacion Java Doc y m. técnica #26
 
 6. UML
    6.A1 Issues #25 y #4--> UML Busqueda de plugins de automatización Code a UMl --FALLIDO
                     * Version mejora: uml de la nueva clasificacion de clases
                     
 7.JUNIT
    7.A1 Issues #3 JUnit --> Instalado 
                       * Version mejora: que test hacer?
  
  
