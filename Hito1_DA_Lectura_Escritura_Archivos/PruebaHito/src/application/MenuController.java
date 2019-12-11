package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuController {
	
		@FXML
		private Button botonOpcion;
		@FXML
		private RadioButton opcionXml;
		@FXML
		private RadioButton opcionJson;
		@FXML
		private RadioButton opcionTxt;
		
		Stage primaryStage = new Stage();
		
		public void menu() {
			
			if(opcionXml.isSelected() && opcionJson.isSelected() || opcionXml.isSelected() && opcionTxt.isSelected() || opcionTxt.isSelected() && opcionJson.isSelected()) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
			    alert.setHeaderText(null);
			    alert.setTitle("Info");
			    alert.setContentText("No puede haber 2 opciones seleccionadas");
			    alert.showAndWait();
			}else {
			
				if(opcionXml.isSelected() && !opcionJson.isSelected() && !opcionTxt.isSelected()) {
					
					try {
						
						BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("GestionXML.fxml"));
						Scene scene = new Scene(root,700,550);
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						primaryStage.setScene(scene);
						primaryStage.setTitle("XML");
						primaryStage.show();
						
					} catch(Exception e) {
						e.printStackTrace();
					}
		
				}
				
				if(opcionJson.isSelected() && !opcionXml.isSelected() && !opcionTxt.isSelected()) {
					try {
						BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("GestionJSON.fxml"));
						Scene scene = new Scene(root,700,550);
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						primaryStage.setScene(scene);
						primaryStage.setTitle("JSON");
						primaryStage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				
				if(opcionTxt.isSelected() && !opcionJson.isSelected() && !opcionXml.isSelected()) {
					try {
						BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("GestionTXT.fxml"));
						Scene scene = new Scene(root,700,550);
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						primaryStage.setScene(scene);
						primaryStage.setTitle("TXT");
						primaryStage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			
			}
		}		
}
