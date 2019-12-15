/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ot.arithmetix.ui;

import com.mycompany.ot.arithmetix.dao.ExerciseDao;
import com.mycompany.ot.arithmetix.dao.UserDao;
import com.mycompany.ot.arithmetix.engine.Engine;
import com.mycompany.ot.arithmetix.engine.Exercise;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author vlappala
 */
public class Ui extends Application {
    
    private Scene gameScene;
    private Scene newUserScene;
    private Scene loginScene;
    private Scene menuScene;
    private Scene statsScene;
    
    private Stage testStage;
    
    private Engine gameEngine;
//    private String loggedInUserName;
    
    private boolean newUser;
    
//    private long startTime;
//    private long elapsedTime;
    
    private boolean admin;
    
    
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
        loginButton.setDefaultButton(true);
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
//                        this.loggedInUserName = this.gameEngine.getUser().getName();
                        this.newUser = true;
                        
//                        kommentoitu 15:25
                        createMenuScene();
//                        createStatsScene(this.gameEngine.getUser().getName());
                        createStatsScene();
                        
                        if (usernameInput.getText().equals("admin")) {
                            this.admin = true;
                        }
                        
                        usernameInput.clear();
                        this.testStage.setScene(menuScene);
                        this.testStage.show();
                    }
                    
                } else {
//                    this.loggedInUserName = this.gameEngine.getUser().getName();
                    usernameInput.clear();
                    
//                    kommentoitu 15:25
                    createMenuScene();
//                    createStatsScene(this.gameEngine.getUser().getName());
                    createStatsScene();
                    
                    if (usernameInput.getText().equals("admin")) {
                        this.admin = true;
                    }
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
        
        String welcomeMessageNewUser = "Kirjattiin uusi käyttäjä "+this.gameEngine.getUser().getName()+"! Tervetuloa!";
        String welcomeMessageExistingUser = "Tervetuloa takaisin, "+this.gameEngine.getUser().getName()+"!";
        
        String greetingTemplate = "";
        
        if (this.newUser) {
            greetingTemplate += welcomeMessageNewUser;
            this.newUser = false;
        }
        else {
            greetingTemplate += welcomeMessageExistingUser;
        }
        
        
        Label loginMessage = new Label();
        
        
        loginMessage.setText(greetingTemplate+"\n\n"+"MENU!");
        
        Button gameButton = new Button("Aloita pelaaminen!");
        gameButton.setDefaultButton(true);
//        Button createButton = new Button("create new user");

        gameButton.setOnAction(e->{
            
//            System.out.println("JAHAS!");
            createGameScene();
            
            this.testStage.setScene(gameScene);
//            this.gameEngine.newExercise();
            this.testStage.show();
        });
        
        Button statsButton = new Button("Katso tilastosi!");
        


        statsButton.setOnAction(e->{
            
            

            createStatsScene();
            
            this.testStage.setScene(statsScene);

            this.testStage.show();
        });
        
        Button logoutButton = new Button("Kirjaudu ulos");
        logoutButton.setCancelButton(true);
//        Button createButton = new Button("create new user");

        logoutButton.setOnAction(e->{
            
            this.gameEngine.logoutUser();
//            createLoginScene();

            this.admin = false;
            
            this.testStage.setScene(loginScene);
            this.testStage.show();
        });
        

        
        inputPane.getChildren().addAll(gameButton, statsButton, logoutButton);
        loginPane.getChildren().addAll(loginMessage, inputPane);
        
        
        menuScene = new Scene(loginPane, 600, 250);
    }
    
    private void createStatsScene() {
        
//        System.out.println("Päästään createStatsScene-metodiin");
        
        // Kopsattua käliä:
        
        TableView tableView = new TableView();
        
        ArrayList<Exercise> exerciseList = this.gameEngine.getExerciseList();

        TableColumn<String, Exercise> column1 = new TableColumn<>("X");
        column1.setCellValueFactory(new PropertyValueFactory<>("X"));
        
        TableColumn<String, Exercise> column2 = new TableColumn<>("op");
        column2.setCellValueFactory(new PropertyValueFactory<>("OperationOut"));
        
        TableColumn<String, Exercise> column3 = new TableColumn<>("Y");
        column3.setCellValueFactory(new PropertyValueFactory<>("Y"));
        
        TableColumn<String, Exercise> column4 = new TableColumn<>("Oikein");
        column4.setCellValueFactory(new PropertyValueFactory<>("AnswerCorrectOut"));
        
        TableColumn<String, Exercise> column5 = new TableColumn<>("Suoritusaika sekunteina");
        column5.setCellValueFactory(new PropertyValueFactory<>("Time"));
        
        TableColumn<String, Exercise> column6 = new TableColumn<>("Päivämäärä");
        column6.setCellValueFactory(new PropertyValueFactory<>("Date"));


//        TableColumn<String, Exercise> column2 = new TableColumn<>("Last Name");
//        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        



        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);
        tableView.getColumns().add(column6);
//        tableView.getColumns().add(column2);

//        System.out.println(exerciseList.size());
//
        for (Exercise e : exerciseList) {
            tableView.getItems().add(e);
        }
//        tableView.getItems().add(new Person("Jane", "Deer"));
        
        tableView.setPlaceholder(new Label("Ei näytettäviä harjoituksia"));
        
        Button exitButton = new Button("Takaisin valikkoon");
        
        exitButton.setCancelButton(true);
        exitButton.requestFocus();
        
        exitButton.setOnAction(e->{
            this.testStage.setScene(menuScene);
            this.testStage.show();
        });

        VBox vbox = new VBox(tableView,exitButton);
        vbox.setPadding(new Insets(10));

        statsScene = new Scene(vbox, 600, 250);
//
//        primaryStage.setScene(scene);
//
//        primaryStage.show();
//        
//        // ...........................................
//        
//        VBox loginPane = new VBox(10);
//        HBox inputPane = new HBox(10);
//        loginPane.setPadding(new Insets(10));
//        Label loginLabel = new Label("Käyttäjätunnus: ");
//        TextField usernameInput = new TextField();
//        
//        inputPane.getChildren().addAll(loginLabel, usernameInput);
//        Label loginMessage = new Label();
//        
//        String loginMessageText = "Tervetuloa matematiikkapeliin! \n"
//                            +   "Syötä olemassaoleva käyttäjätunnus tai anna toivomasi tunnus uudelle käyttäjälle!";
//        
//        loginMessage.setText(loginMessageText);
//         String loginErrorMessage = "Käyttäjätunnuksen pitää olla vähintään 3 ja enintään 8 merkkiä pitkä";
//        
//        
//        Button loginButton = new Button("Kirjaudu sisään tai luo uusi käyttäjä");
//        loginButton.setDefaultButton(true);
////        Button createButton = new Button("create new user");
//
//        loginButton.setOnAction(e->{
//            
//            if (usernameInput.getText().length() < 3 || usernameInput.getText().length() > 8) {
//                loginMessage.setText(loginMessageText+"\n"+loginErrorMessage);
//            } else {
//                
//                try {
//
//                    this.gameEngine.loginUser(usernameInput.getText());
//                }
//                catch (Exception f) {
//                    loginMessage.setText(loginMessageText+"\n"+loginErrorMessage+"\n"+"SQL-virhe!");
//                }
//                
//                if (this.gameEngine.hasUser() == false) {
//                    
//                    try {
//                        this.gameEngine.createUser(usernameInput.getText());
//                    }
//                    catch (Exception f) {
//                        loginMessage.setText(loginMessageText+"\n"+loginErrorMessage+"\n"+"SQL-virhe, käyttäjää ei voitu luoda!");
//                    }
//                    
//                    if (this.gameEngine.hasUser()) {
////                        this.loggedInUserName = this.gameEngine.getUser().getName();
//                        this.newUser = true;
//                        
////                        kommentoitu 15:25
//                        createMenuScene();
//                        
//                        if (usernameInput.getText().equals("admin")) {
//                            this.admin = true;
//                        }
//                        
//                        usernameInput.clear();
//                        this.testStage.setScene(menuScene);
//                        this.testStage.show();
//                    }
//                    
//                } else {
////                    this.loggedInUserName = this.gameEngine.getUser().getName();
//                    usernameInput.clear();
//                    
////                    kommentoitu 15:25
//                    createMenuScene();
//                    
//                    if (usernameInput.getText().equals("admin")) {
//                        this.admin = true;
//                    }
//                    this.testStage.setScene(menuScene);
//                    this.testStage.show();
//                }
//                
//                
//                
//            }
//            
//           
//        });
//        
//        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton);
//        
//        
//        loginScene = new Scene(loginPane, 600, 250);
    }
    
    private void createGameScene() {
        
        // Kopsattua käliä:
        
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        
//        this.gameEngine.newExercise();
        
        Label questionLabel = new Label();
        
        if (this.gameEngine.getExercise() != null) {
            questionLabel.setText(""+this.gameEngine.getExercise().getX()
                +" "+this.gameEngine.getExercise().getOperation()
                +" "+this.gameEngine.getExercise().getY()+"?");
        }
        
        


        Label answerLabel = new Label("Vastaus: ");
        TextField answerInput = new TextField();
        
        inputPane.getChildren().addAll(questionLabel, answerLabel, answerInput);
        
//        String greetingTemplate = "";
        
//        if (this.newUser) {
//            greetingTemplate += welcomeMessageNewUser;
//            this.newUser = false;
//        }
//        else {
//            greetingTemplate += welcomeMessageExistingUser;
//        }
        
        
        Label gameMessage = new Label();
        
        
        gameMessage.setText("PELI!");
        
        Button menuButton = new Button("Takaisin valikkoon");
        menuButton.setCancelButton(true);
//        Button createButton = new Button("create new user");

        menuButton.setOnAction(e->{
            
//            this.gameEngine.logoutUser();
//            createLoginScene();
            this.gameEngine.clearExercise();
            
            this.testStage.setScene(menuScene);
            this.testStage.show();
        });
        
        Button answerButton = new Button();
        
        if (this.gameEngine.getExercise() != null) {
            answerButton.setText("Vastaa!");
        } else {
            answerButton.setText("Aloita peli!");
        }
        
        answerButton.setDefaultButton(true);
        
        
//        Button createButton = new Button("create new user");

//        answerInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
//     
//        @Override
//        public void handle(KeyEvent event) {
//            if(event.getCode().equals(KeyCode.ENTER)) {
//                 answerButton.
//            }
//        }
//        });


        answerButton.setOnAction(e->{
            
            if (this.gameEngine.getExercise() == null) {
                this.gameEngine.newExercise();
                
                questionLabel.setText(""+this.gameEngine.getExercise().getX()
                +" "+this.gameEngine.getExercise().getOperation()
                +" "+this.gameEngine.getExercise().getY()+"?");
                
                answerButton.setText("Vastaa!");
                
                answerInput.requestFocus();
                
//                startTime = System.currentTimeMillis();
            } else {
                
                
            
            
                if (!this.gameEngine.answerInGoodFormat(answerInput.getText())) {
                    gameMessage.setText("Vastaus on muotoiltu huonosti, yritä uudelleen!");
                    answerInput.requestFocus();
                } else if (Integer.parseInt(answerInput.getText()) == this.gameEngine.getExercise().getAnswer()) {
                    
//                    elapsedTime = System.currentTimeMillis()-startTime;
//                    System.out.println((double)elapsedTime/1000);
//                    double d = (double)elapsedTime;

                    // ... oikea vastaus

                    // AJASTUSFUNKTIO ENGINEEN!!! KUITENKIN KÄLIIN?

                    // TIETOKANTAYHTEYS KONDIKSEEN!

                    

                    

                    gameMessage.setText("Oikea vastaus, huraa!");
                    
                    this.gameEngine.processAnswer(true);

                    this.gameEngine.newExercise();

                    questionLabel.setText(""+this.gameEngine.getExercise().getX()
                    +" "+this.gameEngine.getExercise().getOperation()
                    +" "+this.gameEngine.getExercise().getY()+"?");

                    answerInput.clear();
                    answerInput.requestFocus();
//                    startTime = System.currentTimeMillis();
                } else {
                    // Väärä vastaus

                    gameMessage.setText("Aijai, väärä vastaus!");
//                    elapsedTime = System.currentTimeMillis()-startTime;
//                    double d = (double)elapsedTime;
//                    System.out.println(d/1000);

    //                createGameScene();
    
                    this.gameEngine.processAnswer(false);
                    
                    

                    this.gameEngine.newExercise();

                    questionLabel.setText(""+this.gameEngine.getExercise().getX()
                    +" "+this.gameEngine.getExercise().getOperation()
                    +" "+this.gameEngine.getExercise().getY()+"?");

                    answerInput.clear();
                    answerInput.requestFocus();
//                    startTime = System.currentTimeMillis();
                }
            }
            
//            this.gameEngine.logoutUser();
//            createLoginScene();
//            
//            this.testStage.setScene(loginScene);
//            this.testStage.show();
        });
        
        inputPane.getChildren().addAll(answerButton, menuButton);
        loginPane.getChildren().addAll(gameMessage, inputPane);
        
        
        gameScene = new Scene(loginPane, 600, 250);
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
        
//        this.loggedInUserName = "";
        
        
//        daoToEngine.createTablesIfNotExist();
        this.gameEngine = new Engine(taotao, daodao);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        // kopsattua käliä:
        
        this.testStage = primaryStage;
        
        createLoginScene();
//        createMenuScene();
        
        this.testStage.setScene(loginScene);
        this.testStage.setTitle("Arithmetix 1.0!");
        this.testStage.show();
        
        
        // Alapuolella originaalia JavaFX:ää
        
        
        
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
        
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
