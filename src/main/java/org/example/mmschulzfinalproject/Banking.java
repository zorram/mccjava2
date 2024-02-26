package org.example.mmschulzfinalproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.ParseException;
import java.util.List;


import static javafx.application.Application.launch;

public class Banking extends Application {

//Customer First Name
//Customer Last Name
//Address
//Phone Number


    private TextField firstNameField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField phoneNumberField = new TextField();
    private TextField addressField = new TextField();
    private TextField interestMonthField = new TextField();
    private TextField calculatedInterestField = new TextField();

    @Override
    public void start(Stage stage) throws ParseException {
        stage.setTitle("Banking");
        stage.setResizable(false);
        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(25, 25, 25, 25));

        //first name
        vBox.getChildren().add(getField(firstNameField, "First Name"));
        vBox.getChildren().add(getField(lastNameField, "Last Name"));
        vBox.getChildren().add(getField(phoneNumberField, "Phone Number"));
        vBox.getChildren().add(getField(addressField, "Address"));

        //Interest Month
        HBox interestMonthBox = new HBox(10);
        interestMonthBox.setAlignment(Pos.BASELINE_RIGHT);
        Label interestMonthLabel = new Label("Interest Month");
        interestMonthField.setMinWidth(10);
        interestMonthBox.getChildren().add(interestMonthLabel);
        interestMonthField.setMinWidth(10);
        interestMonthBox.getChildren().add(interestMonthField);
        vBox.getChildren().add(interestMonthBox);

        //Calculated Interest
        HBox calculatedInterestBox = new HBox(10);
        calculatedInterestBox.setAlignment(Pos.BASELINE_RIGHT);
        Label calculatedInterestLabel = new Label("Calculated Interest");
        calculatedInterestField.setMinWidth(10);
        calculatedInterestBox.getChildren().add(calculatedInterestLabel);
        calculatedInterestField.setMinWidth(10);
        calculatedInterestBox.getChildren().add(calculatedInterestField);
        vBox.getChildren().add(calculatedInterestBox);

        //buttons
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.BASELINE_RIGHT);

        Button searchCustomerButton = new Button("Search Customer");
        searchCustomerButton.setOnAction(event -> searchCustomerButtonClicked());
        buttonBox.getChildren().add(searchCustomerButton);

        Button previousCustomerButton = new Button("Previous Customer");
        previousCustomerButton.setOnAction(event -> previousCustomerButtonClicked());
        buttonBox.getChildren().add(previousCustomerButton);

        Button nextCustomerButton = new Button("Next Customer");
        nextCustomerButton.setOnAction(event -> nextCustomerButtonClicked());
        buttonBox.getChildren().add(nextCustomerButton);

        Button addCustomerButton = new Button("Add Customer");
        addCustomerButton.setOnAction(event -> addCustomerButtonClicked());
        buttonBox.getChildren().add(addCustomerButton);

        Button updateCustomerButton = new Button("Update Customer");
        updateCustomerButton.setOnAction(event -> updateCustomerButtonClicked());
        buttonBox.getChildren().add(updateCustomerButton);

        Button openAccountButton = new Button("Open Account");
        openAccountButton.setOnAction(event -> openAccountButtonClicked());
        buttonBox.getChildren().add(openAccountButton);

        Button depositButton = new Button("Deposit");
        depositButton.setOnAction(event -> depositButtonClicked());
        buttonBox.getChildren().add(depositButton);

        Button withdrawButton = new Button("Withdraw");
        withdrawButton.setOnAction(event -> withdrawButtonClicked());
        buttonBox.getChildren().add(withdrawButton);

        Button calculateInterestButton = new Button("Calculate Interest");
        calculateInterestButton.setOnAction(event -> calculateInterestButtonClicked());
        buttonBox.getChildren().add(calculateInterestButton);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> exitButtonClicked());
        buttonBox.getChildren().add(exitButton);



        vBox.getChildren().add(buttonBox);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }

    private HBox getField(TextField field, String label) {
        HBox box = new HBox(10);
        box.setAlignment(Pos.BASELINE_LEFT);
        field.setMinWidth(50);
        box.getChildren().add(new Label(label));
        field.setMinWidth(200);
        box.getChildren().add(field);
        return box;
    }

    private void searchCustomerButtonClicked() {
        System.out.println("Search Customer");
    }
    private void previousCustomerButtonClicked() {
        System.out.println("Previous Customer");
    }
    private void nextCustomerButtonClicked() {
        System.out.println("Next Customer");
    }
    private void addCustomerButtonClicked() {
        System.out.println("Add Customer");
    }
    private void updateCustomerButtonClicked() {
        System.out.println("Update Customer");
    }
    private void openAccountButtonClicked() {
        System.out.println("Open Account");
    }
    private void depositButtonClicked() {
        System.out.println("Deposit");
    }
    private void withdrawButtonClicked() {
        System.out.println("Withdraw");
    }
    private void calculateInterestButtonClicked() {
        System.out.println("Calculate Interest");
    }
    private void exitButtonClicked()
    {
        System.exit(0); //normal exit
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}