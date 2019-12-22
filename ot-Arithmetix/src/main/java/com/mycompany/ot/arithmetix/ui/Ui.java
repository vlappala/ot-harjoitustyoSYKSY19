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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author vlappala
 */
public class Ui extends Application {
    
    private Scene gameScene;

    private Scene loginScene;
    private Scene menuScene;
    private Scene statsScene;
    
    private Stage testStage;
    
    private Engine gameEngine;
    
    private boolean newUser;
    
    private boolean admin;
    
    
    private void createLoginScene() {
        
        // Kopsattua käliä:
        
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        HBox greetingPane = new HBox(1);
        HBox loginMessagePane = new HBox(1);
        HBox errorPane = new HBox(1);
        
        loginPane.setPadding(new Insets(10));
        loginPane.setSpacing(25.0);
        
        Label loginLabel = new Label("Käyttäjätunnus: ");
        TextField usernameInput = new TextField();
        
        Label errorLabel = new Label("");
        
        errorPane.getChildren().add(errorLabel);
        errorPane.setAlignment(Pos.BOTTOM_CENTER);
        
        
        
        
        
        Label loginMessage = new Label();
        loginMessage.setTextFill(Color.web("#FFFFFF"));
        loginMessage.setTextAlignment(TextAlignment.CENTER);
        loginMessage.setFont(Font.font("Verdana", 12));
        
        
        Text greetingText = new Text("Tervetuloa matematiikkapeliin!");
        greetingText.setFont(Font.font("Edwardian Script ITC", 50));
        greetingText.setTextAlignment(TextAlignment.CENTER);
        greetingText.setFill(Color.web("#FFFFFF"));

        
        greetingPane.getChildren().add(greetingText);
        greetingPane.setAlignment(Pos.CENTER);
        
        
        
        String loginMessageText = "Syötä olemassaoleva käyttäjätunnus \n tai \n anna toivomasi tunnus uudelle käyttäjälle!";
        
        loginMessage.setText(loginMessageText);
        
        loginMessagePane.getChildren().add(loginMessage);
        loginMessagePane.setAlignment(Pos.CENTER);
        
        
        
        
        

        
        
        loginPane.setBackground(new Background(new BackgroundFill(Color.web("#FF5722"), CornerRadii.EMPTY, Insets.EMPTY)));

        
        String loginErrorMessage = "Käyttäjätunnuksen pitää olla vähintään 3 ja enintään 8 merkkiä pitkä";
        
        
        Button loginButton = new Button("Kirjaudu sisään tai luo uusi käyttäjä");
        loginButton.setDefaultButton(true);

        loginButton.setOnAction(e->{
            
            if (usernameInput.getText().length() < 3 || usernameInput.getText().length() > 8) {
                errorLabel.setText(loginErrorMessage);
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

                        this.newUser = true;

                        createMenuScene();

                        createStatsScene();
                        
                        if (usernameInput.getText().equals("admin")) {
                            this.admin = true;
                        }
                        
                        usernameInput.clear();
                        this.testStage.setScene(menuScene);
                        this.testStage.show();
                    }
                    
                } else {

                    usernameInput.clear();

                    createMenuScene();

                    createStatsScene();
                    
                    if (usernameInput.getText().equals("admin")) {
                        this.admin = true;
                    }
                    this.testStage.setScene(menuScene);
                    this.testStage.show();
                }
                
                
                
            }
            
           
        });
        
        inputPane.getChildren().addAll(loginLabel, usernameInput, loginButton);
        
        inputPane.setAlignment(Pos.CENTER);
        
        loginPane.getChildren().addAll(greetingPane, loginMessagePane, inputPane, errorPane);
        
        
        
        loginScene = new Scene(loginPane, 600, 250);
    }
    
    private void createMenuScene() {
        
        // Kopsattua käliä:
        
        VBox verticalPane = new VBox(10);
        HBox inputPane = new HBox(10);
        HBox titlePane = new HBox(1);
        HBox greetingPane = new HBox(10);
        HBox menuPane = new HBox(1);
        
        Text titleText = new Text("Arithmetix!");
        titleText.setFill(Color.web("#FFFFFF"));
        titleText.setFont(Font.font("Edwardian Script ITC", 50));
        titleText.setStroke(Color.web("#FFFFFF"));
        titleText.setStrokeWidth(1.5);
        
        titlePane.getChildren().add(titleText);
        titlePane.setAlignment(Pos.CENTER);
        titlePane.setBackground(new Background(new BackgroundFill(Color.web("#FF5722"), CornerRadii.EMPTY, Insets.EMPTY)));
        
        Text greetingText = new Text("");
        greetingText.setFont(Font.font("Verdana", 20));
        // greetingText.setFill(Color.web("#FFFFFF"));
        
        Text newUserGreetingText = new Text("Kirjattiin uusi käyttäjä, ");
        newUserGreetingText.setFill(Color.web("#FFFFFF"));
        
        Text userNameText = new Text("");
        userNameText.setFont(Font.font("Edwardian Script ITC", 50));
        userNameText.setFill(Color.web("#FFFFFF"));
        
        Text welcomeText = new Text("Tervetuloa!");
        welcomeText.setFill(Color.web("#FFFFFF"));
        
        Text welcomeTextExistingUser = new Text("Tervetuloa takaisin, ");
        welcomeTextExistingUser.setFill(Color.web("#FFFFFF"));
        welcomeTextExistingUser.setFont(Font.font("Verdana", 20));
        
        Text menuText = new Text("Valikko: ");
        menuText.setFont(Font.font("Verdana", 20));
        menuText.setFill(Color.web("#FFFFFF"));
        
        menuPane.getChildren().add(menuText);
        menuPane.setAlignment(Pos.CENTER);
        
        greetingPane.getChildren().add(greetingText);
        greetingPane.setAlignment(Pos.CENTER);

        
        verticalPane.setBackground(new Background(new BackgroundFill(Color.web("#FF5722"), CornerRadii.EMPTY, Insets.EMPTY)));
        

        
        if (this.newUser) {
            userNameText.setText(this.gameEngine.getUser().getName()+"!");

            greetingPane.getChildren().clear();
            greetingPane.getChildren().addAll(newUserGreetingText, userNameText, welcomeText);
            this.newUser = false;
        }
        else {
            userNameText.setText(this.gameEngine.getUser().getName()+"!");
            
            greetingPane.getChildren().clear();
            greetingPane.getChildren().addAll(welcomeTextExistingUser, userNameText);

        }
        
        

        
        Button gameButton = new Button("Aloita pelaaminen!");
        gameButton.setDefaultButton(true);

        gameButton.setOnAction(e->{
            
            createGameScene();
            
            this.testStage.setScene(gameScene);

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

        logoutButton.setOnAction(e->{
            
            this.gameEngine.logoutUser();

            this.admin = false;
            
            createLoginScene();
            this.testStage.setScene(loginScene);
            this.testStage.show();
        });
        

        
        inputPane.getChildren().addAll(gameButton, statsButton, logoutButton);
        inputPane.setAlignment(Pos.CENTER);
        verticalPane.getChildren().addAll(titlePane, greetingPane, menuPane, inputPane);
        

        
        menuScene = new Scene(verticalPane, 600, 250);

    }
    
    private void createStatsScene() {
        
        // Kopsattua käliä:
        
        TableView tableView = new TableView();
        
        ArrayList<Exercise> exerciseList = this.gameEngine.getExerciseList();

        TableColumn<String, Exercise> column1 = new TableColumn<>("X");
        column1.setCellValueFactory(new PropertyValueFactory<>("X"));
        
        TableColumn<String, Exercise> column2 = new TableColumn<>("op");
        column2.setCellValueFactory(new PropertyValueFactory<>("OperationOut"));
        
        TableColumn<String, Exercise> column3 = new TableColumn<>("Y");
        column3.setCellValueFactory(new PropertyValueFactory<>("Y"));
        
        TableColumn<String, Exercise> column4 = new TableColumn<>("Oikein/Väärin");
        column4.setCellValueFactory(new PropertyValueFactory<>("AnswerCorrectOut"));
        
        TableColumn<String, Exercise> column5 = new TableColumn<>("Suoritusaika sekunteina");
        column5.setCellValueFactory(new PropertyValueFactory<>("Time"));
        
        TableColumn<String, Exercise> column6 = new TableColumn<>("Päivämäärä");
        column6.setCellValueFactory(new PropertyValueFactory<>("Date"));


        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);
        tableView.getColumns().add(column6);

        for (Exercise e : exerciseList) {
            tableView.getItems().add(e);
        }

        
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
        vbox.setBackground(new Background(new BackgroundFill(Color.web("#FF5722"), CornerRadii.EMPTY, Insets.EMPTY)));

        statsScene = new Scene(vbox, 600, 250);

    }
    
    private void createGameScene() {
        
        
        VBox verticalPane = new VBox(5);
//         verticalPane.setPadding(new Insets(10));
        verticalPane.setSpacing(20);
        
        HBox inputPane = new HBox(10);
        HBox titlePane = new HBox(1);
        HBox questionPane = new HBox(1);
        HBox messagePane = new HBox(1);

        
        Text titleText = new Text("Arithmetix!");
        titleText.setFill(Color.web("#FFFFFF"));
        titleText.setFont(Font.font("Edwardian Script ITC", 50));
        titleText.setStroke(Color.web("#FFFFFF"));
        titleText.setStrokeWidth(1.5);
        
        titlePane.getChildren().add(titleText);
        titlePane.setAlignment(Pos.CENTER);
        
        verticalPane.setBackground(new Background(new BackgroundFill(Color.web("#FF5722"), CornerRadii.EMPTY, Insets.EMPTY)));
        

        Text questionText = new Text("");
        questionText.setFont(Font.font("Verdana", 20));
        questionText.setFill(Color.web("#FFFFFF"));
        
        questionPane.getChildren().add(questionText);
        questionPane.setAlignment(Pos.CENTER);
        
        Text messageText = new Text("");
        messagePane.getChildren().add(messageText);
        messagePane.setAlignment(Pos.BOTTOM_CENTER);
        
        //Label questionLabel = new Label();
        
        if (this.gameEngine.getExercise() != null) {
            //questionLabel.setText(""+this.gameEngine.getQuestion());
            questionText.setText(""+this.gameEngine.getQuestion());
//            messageText.setText("Voit vastata myös painamalla enteriä.");
            
        } else {
            messageText.setText("Aloita peli painamalla enter tai Aloita peli-nappia");
        }
        
        


        Label answerLabel = new Label("Vastaus: ");
        TextField answerInput = new TextField();
        
        inputPane.getChildren().addAll(answerLabel, answerInput);
        inputPane.setAlignment(Pos.CENTER);
        

//        Label gameMessage = new Label();
//        
//        
//        gameMessage.setText("PELI!");
        
        Button menuButton = new Button("Takaisin valikkoon");
        menuButton.setCancelButton(true);


        menuButton.setOnAction(e->{
            
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
        
        answerButton.setOnAction(e->{
            
            if (this.gameEngine.getExercise() == null) {
                this.gameEngine.newExercise();
                
//                questionLabel.setText(""+this.gameEngine.getExercise().getX()
//                +" "+this.gameEngine.getExercise().getOperation()
//                +" "+this.gameEngine.getExercise().getY()+"?");
                
                questionText.setText(this.gameEngine.getQuestion());
                messageText.setText("Voit vastata myös painamalla enter-nappia!");
                
                answerButton.setText("Vastaa!");
                
                answerInput.requestFocus();
                

            } else {
                
                
            
            
                if (!this.gameEngine.answerInGoodFormat(answerInput.getText())) {
                    messageText.setText("Vastaus on muotoiltu huonosti, yritä uudelleen!");
                    answerInput.requestFocus();
                } else if (this.gameEngine.processAnswer(answerInput.getText())) {
                    
                    messageText.setText("Oikea vastaus, huraa! \n"
                            + "Aikasi oli: "+this.gameEngine.getLastClockedTime());
                    
                    
//                    questionLabel.setText(this.gameEngine.getQuestion());
                    questionText.setText(this.gameEngine.getQuestion());

                    answerInput.clear();
                    answerInput.requestFocus();

                } else {
                    // Väärä vastaus

                    messageText.setText("Aijai, väärä vastaus! \n"
                            + "Aikasi oli: "+this.gameEngine.getLastClockedTime());


//                    questionLabel.setText(this.gameEngine.getQuestion());
                    questionText.setText(this.gameEngine.getQuestion());

                    answerInput.clear();
                    answerInput.requestFocus();

                }
            }
            
        });
        
        inputPane.getChildren().addAll(answerButton, menuButton);
        verticalPane.getChildren().addAll(titlePane, questionPane, inputPane, messagePane);
        
        
        gameScene = new Scene(verticalPane, 600, 250);
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
        
        this.gameEngine = new Engine(taotao, daodao);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        // kopsattua käliä:
        
        this.testStage = primaryStage;
        
        createLoginScene();
        
        this.testStage.setScene(loginScene);
        this.testStage.setTitle("Arithmetix 1.0!");
        this.testStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
