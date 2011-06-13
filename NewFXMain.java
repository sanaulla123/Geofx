/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geofx;


import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextBox;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

/**
 *
 * @author Mohamed Sanaulla
 */
public class NewFXMain extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(NewFXMain.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Geocoder");
        TabPane mainTabPane = new TabPane();
        Tab geoTab = new Tab("Geocoding");
        geoTab.setClosable(false);
        mainTabPane.getTabs().add(geoTab);
        
        final GridPane geoGrid = new GridPane();
        geoGrid.setHgap(10);
        geoGrid.setVgap(10);
        geoGrid.setPadding(new Insets(0, 20, 0, 10));
        
        Label mainGeoLabel = new Label("Geocoding");
        
        final TextBox geoAddressTextBox = new TextBox(15);
       
        Button geoCodeButton = new Button("Geocode"); 
        final TextBox latitudeValTextBox = new TextBox();
        latitudeValTextBox.setEditable(false);
        final TextBox longitudeValTextBox = new TextBox();
        longitudeValTextBox.setEditable(false);
        final TextBox locationTypeValTextBox = new TextBox();
        locationTypeValTextBox.setEditable(false);
        
        
        final StringProperty latitudeProperty = new StringProperty();
        latitudeProperty.addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                latitudeValTextBox.setText(newValue);
            }
        });
        
        final StringProperty longitudeProperty = new StringProperty();
        longitudeProperty.addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                longitudeValTextBox.setText(newValue);
            }
        });
        
        final StringProperty locationTypeProperty = new StringProperty();
        locationTypeProperty.addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                locationTypeValTextBox.setText(newValue);
            }
        });
        
        geoCodeButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                String address = geoAddressTextBox.getText();
                System.out.println("Address: "+address);
                if(address == null){
                    
                }else{
                    Geocode parsedCode = (Geocode)GeocodingParser.getGeocodeForAddress(address);
                    latitudeProperty.set(parsedCode.getLatitude());
                    longitudeProperty.set(parsedCode.getLongitude());
                    locationTypeProperty.set(parsedCode.getLocationType());
                }
            }
            
        });
        
        geoGrid.add(mainGeoLabel, 4, 1);
        geoGrid.add(new Label("Address"), 2, 3);
        geoGrid.add(geoAddressTextBox, 3, 3,3,1);
        geoGrid.add(new Label("Latitude"), 2,7);
        
        geoGrid.add(new Label("Longitude"),2,8);
        
        geoGrid.add(new Label("Location Type"),2,9);
        geoGrid.add(latitudeValTextBox,3,7,2,1);
        geoGrid.add(longitudeValTextBox,3,8,2,1);
        geoGrid.add(locationTypeValTextBox,3,9,2,1);
        geoGrid.add(geoCodeButton, 4, 5);
        geoTab.setContent(geoGrid);
        Scene scene = new Scene(mainTabPane);
        primaryStage.setScene(scene);
        primaryStage.setVisible(true);
        primaryStage.setResizable(false);
        
    }
}
