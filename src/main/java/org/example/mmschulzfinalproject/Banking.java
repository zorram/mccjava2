package org.example.mmschulzfinalproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.ParseException;
import java.util.List;

public class Banking extends Application {

//Customer First Name
//Customer Last Name
//Address
//Phone Number

    public List<Customer> customers;
    public int currentCustomerIndex = 0;
    private TextField accountNumberField = new TextField();
    private TextField firstNameField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField phoneNumberField = new TextField();
    private TextField addressField = new TextField();
    private TextField interestMonthField = new TextField();
    private TextField calculatedInterestField = new TextField();

    private Button nextCustomerButton;
    private Button previousCustomerButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws ParseException {
        stage.setTitle("Banking");
        stage.setResizable(false);
        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(25, 25, 25, 25));

        //first name
        accountNumberField.setEditable(false);
        vBox.getChildren().add(getField(accountNumberField, "Account Number"));
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

        this.previousCustomerButton = new Button("Previous Customer");
        previousCustomerButton.setDisable(true);
        previousCustomerButton.setOnAction(event -> previousCustomerButtonClicked());
        buttonBox.getChildren().add(previousCustomerButton);

        this.nextCustomerButton = new Button("Next Customer");
        nextCustomerButton.setDisable(true);
        nextCustomerButton.setOnAction(event -> nextCustomerButtonClicked());
        buttonBox.getChildren().add(nextCustomerButton);

        Button addCustomerButton = new Button("Add Customer");
        addCustomerButton.setOnAction(event -> addCustomerButtonClicked());
        buttonBox.getChildren().add(addCustomerButton);

        Button updateCustomerButton = new Button("Update Customer");
        updateCustomerButton.setOnAction(event -> updateCustomerButtonClicked());
        buttonBox.getChildren().add(updateCustomerButton);

        Button openAccountButton = new Button("Open Account");
        openAccountButton.setDisable(true);
        openAccountButton.setOnAction(event -> openAccountButtonClicked());
        buttonBox.getChildren().add(openAccountButton);

        Button depositButton = new Button("Deposit");
        depositButton.setDisable(true);
        depositButton.setOnAction(event -> depositButtonClicked());
        buttonBox.getChildren().add(depositButton);

        Button withdrawButton = new Button("Withdraw");
        withdrawButton.setDisable(true);
        withdrawButton.setOnAction(event -> withdrawButtonClicked());
        buttonBox.getChildren().add(withdrawButton);

        Button calculateInterestButton = new Button("Calculate Interest");
        calculateInterestButton.setDisable(true);
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
        this.customers = BankingDB.searchCustomer(getCustomer());
        System.out.println("Customers: " + this.customers);
        if (this.customers != null && !this.customers.isEmpty()) {
            updateScreen(this.customers.getFirst());

            if (this.customers.size() == 1) {
                this.nextCustomerButton.setDisable(true);
            } else {
                this.nextCustomerButton.setDisable(false);
            }

        } {
            System.out.println("Unable to find customer");
        }

    }

    private void previousCustomerButtonClicked() {
        System.out.println("Previous Customer");
        currentCustomerIndex = currentCustomerIndex + -1;
        updateScreen(this.customers.get(currentCustomerIndex));

        if (currentCustomerIndex == 0) {
            this.previousCustomerButton.setDisable(true);
        }
        this.nextCustomerButton.setDisable(false);
    }

    private void nextCustomerButtonClicked() {
        System.out.println("Next Customer");
        currentCustomerIndex = currentCustomerIndex + 1;
        updateScreen(this.customers.get(currentCustomerIndex));
        this.previousCustomerButton.setDisable(false);

        if (this.customers.size() >= (currentCustomerIndex +1)) {
            this.nextCustomerButton.setDisable(true);
        }
    }

    private void addCustomerButtonClicked() {
        System.out.println("Add Customer Button Click");
        BankingDB.addCustomer(getCustomer());
    }

    private void updateCustomerButtonClicked() {
        System.out.println("Update Customer");
        BankingDB.updateCustomer(getCustomer());
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

    private void exitButtonClicked() {
        System.exit(0); //normal exit
    }

    private void updateScreen(Customer customer) {
        System.out.println("Update Screen");
        accountNumberField.setText(String.valueOf(customer.getAccountNumber()));
        firstNameField.setText(customer.getFirstName());
        lastNameField.setText(customer.getLastName());
        phoneNumberField.setText(customer.getPhoneNumber());
        addressField.setText(customer.getAddress());
    }

    private Customer getCustomer() {
        Customer customer = new Customer();
        if (!accountNumberField.getText().isEmpty()) {
            customer.setAccountNumber(Integer.parseInt(accountNumberField.getText()));
        }
        customer.setFirstName(firstNameField.getText());
        customer.setLastName(lastNameField.getText());
        customer.setPhoneNumber(phoneNumberField.getText());
        customer.setAddress(addressField.getText());
        return customer;
    }
}