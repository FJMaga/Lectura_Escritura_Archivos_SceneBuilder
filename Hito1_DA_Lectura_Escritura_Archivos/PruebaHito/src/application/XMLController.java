package application;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;


public class XMLController {

	public static Object superFecha;
	
	static ArrayList<StaXEscritura> archivosConfigurados = new ArrayList<StaXEscritura>();

	String configFile;
	static String fechaString=null,nombreString=null,ciudadString=null,salarioString=null;
	
	@FXML
	TextArea areaVisual;
	@FXML
	TextField Fecha,Nombre,Ciudad,Salario;
	
	@FXML
	public void escribirXML() throws Exception {
		
		//System.out.println(Fecha.getText());
		
		
		if(fechaString==null || nombreString==null || ciudadString==null || salarioString==null) {
			
			fechaString=Fecha.getText().toString();
			nombreString=Nombre.getText().toString();
			ciudadString=Ciudad.getText().toString();
			salarioString=Salario.getText().toString();
			
			Alert alert = new Alert(Alert.AlertType.WARNING);
		    alert.setHeaderText(null);
		    alert.setTitle("Info");
		    alert.setContentText("Los campos no pueden estra en blanco, en caso de tenerlos rellenos ignore el mensaje");
		    alert.showAndWait();
		}
		else {
			
			fechaString=Fecha.getText().toString();
			nombreString=Nombre.getText().toString();
			ciudadString=Ciudad.getText().toString();
			salarioString=Salario.getText().toString();
		
			// TODO Auto-generated method stub
			StaXEscritura configFile = new StaXEscritura();
	        configFile.setFile("config2.xml");
	        
	        Fecha.setText("");
			Nombre.setText("");
			Ciudad.setText("");
			Salario.setText("");
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setHeaderText("Confirmacion");
		    alert.setTitle("Info");
		    alert.setContentText("El archivo ha sido creado con exito");
		    alert.showAndWait();
			
	        try {
	            configFile.saveConfig();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
	}
				 
	
	@FXML
	public void leerXML() {
		
		StaXParser parsearLectura = new StaXParser();
		//parsearLectura.leer("config.xml"); //comprobacion de lectura
		//List<Item> solucion = parsearLectura.leer("config.xml");
		List<Item> solucionEscritura = parsearLectura.leer("config2.xml");
		//System.out.println(solucion);
		//System.out.println("######################################");
		/*
		for (Item item: solucion) {
			areaVisual.appendText(item.toString()+"\n");
        }
		
		for (Item itemEscritos: solucionEscritura) {
			areaVisual.appendText(itemEscritos.toString()+"\n");
		}
		*/
		@SuppressWarnings("rawtypes")
		ArrayList<List> listaCreada = new ArrayList<List>();
		listaCreada.add(solucionEscritura);
		areaVisual.setText(listaCreada.toString()+"\n");
		
	}
}
