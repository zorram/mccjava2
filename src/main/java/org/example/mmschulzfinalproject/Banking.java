package org.example.mmschulzfinalproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

public class Banking extends Application {

    public List<Customer> customers;
    public int currentCustomerIndex = 0;
    private TextField customerIdField = new TextField();
    private TextField firstNameField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField phoneNumberField = new TextField();
    private TextField addressField = new TextField();
    private TextField accountIdField = new TextField();
    private TextField balanceField = new TextField();
    private TextField interestRateField = new TextField();
    private TextField withdrawDepositField = new TextField();
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

        //buttons
        Button searchCustomerButton = getButton("Search Customer");
        searchCustomerButton.setOnAction(event -> searchCustomerButtonClicked());

        this.previousCustomerButton = getButton("Previous Customer");
        previousCustomerButton.setDisable(true);
        previousCustomerButton.setOnAction(event -> previousCustomerButtonClicked());

        this.nextCustomerButton = getButton("Next Customer");
        nextCustomerButton.setDisable(true);
        nextCustomerButton.setOnAction(event -> nextCustomerButtonClicked());

        Button addCustomerButton = getButton("Add Customer");
        addCustomerButton.setOnAction(event -> addCustomerButtonClicked());

        Button updateCustomerButton = getButton("Update Customer");
        updateCustomerButton.setOnAction(event -> updateCustomerButtonClicked());

        Button openAccountButton = getButton("Open Account");
        openAccountButton.setDisable(true);
        openAccountButton.setOnAction(event -> openAccountButtonClicked());

        Button depositButton = getButton("Deposit");
        depositButton.setOnAction(event -> depositButtonClicked());

        Button withdrawButton = getButton("Withdraw");
        withdrawButton.setOnAction(event -> withdrawButtonClicked());

        Button calculatedInterestButton = getButton("Calculate Interest");
        calculatedInterestButton.setOnAction(event -> calculatedInterestButtonClicked());

        Button exitButton = getButton("Exit");
        exitButton.setOnAction(event -> exitButtonClicked());

        TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
        tileButtons.setPadding(new Insets(20, 10, 20, 0));
        tileButtons.setHgap(10.0);
        tileButtons.setVgap(8.0);
        tileButtons.getChildren().addAll(
                searchCustomerButton,
                previousCustomerButton,
                nextCustomerButton,
                addCustomerButton,
                updateCustomerButton,
                openAccountButton,
                depositButton,
                withdrawButton,
                calculatedInterestButton,
                exitButton
        );

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(12);

        addRow(grid, "Customer Id", customerIdField);
        addRow(grid, "First Name", firstNameField);
        addRow(grid, "Last Name", lastNameField);
        addRow(grid, "Phone Number", phoneNumberField);
        addRow(grid, "Address", addressField);

        addRow(grid, "Account Number", accountIdField);
        addRow(grid, "Balance", balanceField);
        addRow(grid, "Interest Rate", interestRateField);

        addRow(grid, "Withdraw/Deposit", withdrawDepositField);

        grid.add(new Label("Interest Month"), 3, 0);
        grid.add(interestMonthField, 4, 0);

        grid.add(new Label("Calculate Interest"), 3, 1);
        grid.add(calculatedInterestField, 4, 1);

        vBox.getChildren().add(grid);
        vBox.getChildren().add(tileButtons);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }

    private void addRow(GridPane grid, String label, TextField field) {
        int rowCount = grid.getRowCount();
        grid.add(new Label(label), 0, rowCount);
        grid.add(field, 1, rowCount);
    }

    private Button getButton(String label) {
        Button button = new Button(label);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return button;
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
        BankingDB.addAccountToCustomer(
                BankingDB.addCustomer(getCustomer()),
                AccountDB.addAccount(getAccount())
        );
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
        if (withdrawDepositField.getText().isEmpty()) {
            return;
        }
        Account account = getAccount();
        account.setBalance(
                account.getBalance().add(new BigDecimal(withdrawDepositField.getText()))
        );
        AccountDB.updateBalance(account);
        updateScreen(
                BankingDB.getCustomer(Integer.parseInt(customerIdField.getText()))
        );
    }

    private void withdrawButtonClicked() {
        System.out.println("Withdraw");
        if (withdrawDepositField.getText().isEmpty()) {
            return;
        }
        Account account = getAccount();
        account.setBalance(
                account.getBalance().subtract(new BigDecimal(withdrawDepositField.getText()))
        );
        AccountDB.updateBalance(account);
        updateScreen(
                BankingDB.getCustomer(Integer.parseInt(customerIdField.getText()))
        );
    }

    private void calculatedInterestButtonClicked() {
        System.out.println("Calculated Interest");
        int month = Integer.parseInt(interestMonthField.getText());
        BigDecimal interestRate = new BigDecimal(interestRateField.getText());
        BigDecimal balance = new BigDecimal(balanceField.getText());
        calculatedInterestField.setText(
                interestRate.divide(new BigDecimal(100))
                        .divide(new BigDecimal(month), 2)
                        .multiply(balance)
                        .toString()
        );
    }

    private void exitButtonClicked() {
        System.exit(0); //normal exit
    }

    private void updateScreen(Customer customer) {
        System.out.println("Update Screen");
        customerIdField.setText(String.valueOf(customer.getCustomerId()));
        firstNameField.setText(customer.getFirstName());
        lastNameField.setText(customer.getLastName());
        phoneNumberField.setText(customer.getPhoneNumber());
        addressField.setText(customer.getAddress());
        updateScreen(customer.getAccount());
    }

    private void updateScreen(Account account) {
        accountIdField.setText(String.valueOf(account.getId()));
        balanceField.setText(String.valueOf(account.getBalance()));
        interestRateField.setText(String.valueOf(account.getInterestRate()));
    }

    private Customer getCustomer() {
        Customer customer = new Customer();
        if (!customerIdField.getText().isEmpty()) {
            customer.setCustomerId(Integer.parseInt(customerIdField.getText()));
        }
        customer.setFirstName(firstNameField.getText());
        customer.setLastName(lastNameField.getText());
        customer.setPhoneNumber(phoneNumberField.getText());
        customer.setAddress(addressField.getText());
        return customer;
    }
    private Account getAccount() {
        Account account = new Account();

        if (!accountIdField.getText().isEmpty()) {
            account.setId(Integer.parseInt(accountIdField.getText()));
        }
        if (!balanceField.getText().isEmpty()) {
            account.setBalance(new BigDecimal(balanceField.getText()));
        }
        if (!interestRateField.getText().isEmpty()) {
            account.setInterestRate(new BigDecimal(interestRateField.getText()));
        }
        return account;
    }
}