package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class JSONController {
	
	static String fechaString=null,nombreString=null,ciudadString=null,salarioString=null;
	@FXML
	TextArea areaVisual;
	
	@FXML
	TextField Fecha,Nombre,Ciudad,Salario;
	
	@FXML
	public void EscribirJSON() throws IOException {
		List<Object> Objetos = new ArrayList<Object>();
		JSONArray ListaObjetos = new JSONArray();
		
		//primer item
		JSONObject obj1 = new JSONObject();
		
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
		
			obj1.put("date", fechaString);
			obj1.put("Nombre", nombreString);
			obj1.put("Ciudad", ciudadString);
			obj1.put("Salario", salarioString);
			
			JSONObject item1 = new JSONObject();
			item1.put("Item", obj1);
			
			ListaObjetos.add(item1);
			
			Fecha.setText("");
			Nombre.setText("");
			Ciudad.setText("");
			Salario.setText("");
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setHeaderText("Confirmacion");
		    alert.setTitle("Info");
		    alert.setContentText("El archivo ha sido creado con exito");
		    alert.showAndWait();
		
			
	        try  {
	        	
	        	File file = new File("archivo.json");
	        	
	        	if(!file.exists()) {
					 file.createNewFile();
				}
	        	
	        	FileWriter fw = new FileWriter(file);
				
	            BufferedWriter bw = new BufferedWriter(fw);
	 
	            fw.write(ListaObjetos.toJSONString());
	            fw.flush();
	            
	            
	            
	            
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
			
		}	
			
	}
	
	 @FXML
	 public void parseEmployeeObject(JSONObject ite) {
	    	
	    	//cogemos los valores del listado de objetos
	    	JSONObject iteObject = (JSONObject) ite.get("Item");
	    	
	        //recogemos la fecha
	        String fecha = (String) iteObject.get("date"); 
	        areaVisual.appendText("{"+"\n"+"Fecha :"+fecha+"\n");
	        
	        //recogemos el nombre
	        String nombre = (String) iteObject.get("Nombre"); 
	        areaVisual.appendText("Nombre :"+nombre+"\n");
	         
	        //recogemos la ciudad
	        String ciudad = (String) iteObject.get("Ciudad"); 
	        areaVisual.appendText("Ciudad :"+ciudad+"\n");
	        	         
	        //recogemos el salario
	        String salario = (String) iteObject.get("Salario");
	        areaVisual.appendText("Salario :"+salario+"\n"+"}");	      
	    }
		
	 @FXML
	 public void LeerJSON( ) {
		// TODO Auto-generated method stub
			//JSON parser object to parse read file
	        JSONParser jsonParser = new JSONParser();
	         
	        try (FileReader reader = new FileReader("archivo.json"))
	        {
	            //Read JSON file
	            Object obj = jsonParser.parse(reader);
	 
	            JSONArray ListaObjetos = (JSONArray) obj;
	            System.out.println(ListaObjetos);
	             
	            //Iterate over items array
	            
	            ListaObjetos.forEach( ite -> parseEmployeeObject( (JSONObject) ite));
	            
	 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
    }
	
}