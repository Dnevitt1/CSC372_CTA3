package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.Random;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.time.LocalDate;


public class Main extends Application {
	
    @Override
    public void start(Stage stage) {
 
        // Create MenuBar
        MenuBar menuBar = new MenuBar();
        
        // Create menus
        Menu dateMenu = new Menu("Print");
        Menu textMenu = new Menu("Write");
        Menu colorMenu = new Menu("Change Color");
        Menu exitMenu = new Menu("Options");
        
        // Create MenuItems
        MenuItem dateItem = new MenuItem("Date/Time");
        MenuItem textItem = new MenuItem("Save To Text File");
        MenuItem colorItem = new MenuItem("Random Color");
        MenuItem exitItem = new MenuItem("Exit");
        
        // Add menuItems to the Menus
        dateMenu.getItems().addAll(dateItem);
        textMenu.getItems().addAll(textItem);
        colorMenu.getItems().addAll(colorItem);
        exitMenu.getItems().addAll(exitItem);
        
        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(dateMenu, textMenu, colorMenu, exitMenu);
        
       
        // When user clicks on Date/Time MenuItem
        dateMenu.setOnAction(new EventHandler<ActionEvent>()    {

            @Override
            public void handle(ActionEvent event) {
                LocalDate today = LocalDate.now();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Date Printer");
                alert.setHeaderText(null);
                alert.setContentText("Today's Date is: " + today);
                alert.showAndWait();
                            }
        });
        
        // When user clicks on Save to Text File MenuItem
        textMenu.setOnAction(new EventHandler<ActionEvent>()    {

            @Override
            public void handle(ActionEvent event) {
                LocalDate today = LocalDate.now();
                try {
                    
                    // Log message created
                    String content = "Button clicked on: " + today;

                    File file = new  File("C:\\Users\\Dallas\\Desktop\\Software Development\\Java\\Projects\\CSC372_CTA3\\log.txt");

                    // File already created on desktop
                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    FileWriter fw = new FileWriter(file.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(content);
                    bw.close();

                    System.out.println("Done");

                } catch (IOException e) {
                    e.printStackTrace();
                }
                            }
        });
        
        // When user clicks on Change Color MenuItem
        colorMenu.setOnAction(new EventHandler<ActionEvent>()   {

            @Override
            public void handle(ActionEvent event) {
            	Random random = new Random();
                int r = 255; // Red is fully saturated in orange hues
                int g = random.nextInt(156) + 100; // Green component (from 100 to 255)
                int b = random.nextInt(51); // Blue component (from 0 to 50)

                String color = String.format("rgb(%d, %d, %d)", r, g, b);
                menuBar.setStyle("-fx-background-color: " + color + ";");
                            }
        });
        
        // Set Accelerator for Exit MenuItem.
        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
         
        // When user clicks on the Exit MenuItem
        exitItem.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
         
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        Scene scene = new Scene(root, 400, 200);
        
        stage.setTitle("JavaFX Menu for CSC372");
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        Application.launch(args);
    }
 
}