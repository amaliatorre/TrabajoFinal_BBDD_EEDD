package Faceta_Grafica;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
public class Main extends Application {
	 
	    @Override
	    public void start(Stage primaryStage) throws Exception {
	        //Cargamos la escena (el formulario/ventana)
	        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
	        //Establecemos el título de la ventana
	        primaryStage.setTitle("ProyectoA - Mi primera aplicación JavaFX");
	        //Icono para el formulario
	        //primaryStage.getIcons().add(new Image("proyectoa_logotipo_corto_letra.png"));
	        //Establecemos el ancho y el alto
	        primaryStage.setScene(new Scene(root, 400, 200));
	        //Mostramos la ventana
	        primaryStage.show();
	    }
	 
	 
	    public static void main(String[] args) {
	        launch(args);
	    }
	}


