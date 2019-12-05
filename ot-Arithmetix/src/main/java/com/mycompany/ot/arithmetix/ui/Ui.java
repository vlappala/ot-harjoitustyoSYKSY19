/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ot.arithmetix.ui;

import com.mycompany.ot.arithmetix.dao.ExerciseDao;
import com.mycompany.ot.arithmetix.dao.UserDao;
import com.mycompany.ot.arithmetix.engine.Engine;
import java.io.FileInputStream;
import java.util.Properties;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author vlappala
 */
public class Ui extends Application {
    
    private Scene todoScene;
    private Scene newUserScene;
    private Scene loginScene;
    private Scene menuScene;
    
    private Stage testStage;
    
    private Engine gameEngine;
    private String loggedInUserName;
    
    
    
    private void createLoginScene() {
        
        // Kopsattua käliä:
        
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("Käyttäjätunnus: ");
        TextField usernameInput = new TextField();
        
        inputPane.getChildren().addAll(loginLabel, usernameInput);
        Label loginMessage = new Label();
        
        String loginMessageText = "Tervetuloa matematiikkapeliin! \n"
                            +   "Syötä olemassaoleva käyttäjätunnus tai anna toivomasi tunnus uudelle käyttäjälle!";
        
        loginMessage.setText(loginMessageText);
         String loginErrorMessage = "Käyttäjätunnuksen pitää olla vähintään 3 ja enintään 8 merkkiä pitkä";
        
        
        Button loginButton = new Button("Kirjaudu sisään tai luo uusi käyttäjä");
//        Button createButton = new Button("create new user");

        loginButton.setOnAction(e->{
            
            if (usernameInput.getText().length() < 3 || usernameInput.getText().length() > 8) {
                loginMessage.setText(loginMessageText+"\n"+loginErrorMessage);
            } else {
                
                try {

                    this.gameEngine.loginUser(usernameInput.getText());
                }
                catch (Exception f) {
                    loginMessage.setText(loginMessageText+"\n"+loginErrorMessage+"\n"+"SQL-virhe!");
                }
                
                if (this.gameEngine.hasUser() == false) {
                    
                    try {
                        this.gameEngine.createUser(usernameInput.getText());
                    }
                    catch (Exception f) {
                        loginMessage.setText(loginMessageText+"\n"+loginErrorMessage+"\n"+"SQL-virhe, käyttäjää ei voitu luoda!");
                    }
                    
                    if (this.gameEngine.hasUser()) {
                        this.testStage.setScene(menuScene);
                        this.testStage.show();
                    }
                    
                } else {
                    this.testStage.setScene(menuScene);
                    this.testStage.show();
                }
                
                
                
            }
            
           
        });
        
        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton);
        
        
        loginScene = new Scene(loginPane, 600, 250);
    }
    
    private void createMenuScene() {
        
        // Kopsattua käliä:
        
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("Käyttäjätunnus: ");
        TextField usernameInput = new TextField();
        
        inputPane.getChildren().addAll(loginLabel, usernameInput);
        Label loginMessage = new Label();
        
        loginMessage.setText("MENU!");
        
        Button loginButton = new Button("Kirjaudu sisään tai luo uusi käyttäjä");
//        Button createButton = new Button("create new user");

        loginButton.setOnAction(e->{
            this.testStage.setScene(loginScene);
            this.testStage.show();
        });
        
        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton);
        
        
        menuScene = new Scene(loginPane, 600, 250);
    }
    
    @Override
    public void init() throws Exception {
        
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("config.properties"));
        }
        catch (Exception e) {
            System.out.println("Virhe konfiguraatiotiedoston lataamisessa: "+e.toString());
        }
    
        String dbAddress = properties.getProperty("dbFile");
        
        UserDao taotao = new UserDao(dbAddress);
        ExerciseDao daodao = new ExerciseDao(dbAddress);
        
        this.loggedInUserName = "";
        
        
//        daoToEngine.createTablesIfNotExist();
        this.gameEngine = new Engine(taotao, daodao);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        // kopsattua käliä:
        
        this.testStage = primaryStage;
        
        createLoginScene();
        createMenuScene();
        
        this.testStage.setScene(loginScene);
        this.testStage.setTitle("Arithmetix 1.0!");
        this.testStage.show();
        
        
        // Alapuolella originaalia JavaFX:ää
        
        
        
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
