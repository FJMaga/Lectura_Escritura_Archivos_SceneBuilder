package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TXTController {
	static String fechaString=null,nombreString=null,ciudadString=null,salarioString=null;
	@FXML
	TextArea areaVisual;
	
	@FXML
	TextField Fecha,Nombre,Ciudad,Salario;
	
	@FXML
	public void escribirTXT() throws IOException {
		// TODO Auto-generated method stub
		
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
	
			String ruta = "archivo.txt";
			File archivo = new File(ruta);
			BufferedWriter bw;
			
		    bw = new BufferedWriter(new FileWriter(archivo));
			bw.write("El Empleado "+nombreString+" se ha crerado con exito."+" ( DATOS [ "+"Fecha : "+fechaString+", "+"Ciudad : "+ciudadString+", "+"Salario : "+salarioString+" ] )");
			      
			
			
			bw.close();
			
			Fecha.setText("");
			Nombre.setText("");
			Ciudad.setText("");
			Salario.setText("");
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setHeaderText("Confirmacion");
		    alert.setTitle("Info");
		    alert.setContentText("El archivo ha sido creado con exito");
		    alert.showAndWait();
		}
	}
	
	public void leerTXT() throws IOException {
		
		File archivo = new File ("archivo.txt");
		FileReader fr = new FileReader (archivo);
		BufferedReader br = new BufferedReader(fr);
		
		String linea = br.readLine();
		
	    areaVisual.setText(linea);
	}
}
