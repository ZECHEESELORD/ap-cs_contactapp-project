package sh.harold.contactsapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

public class ContactsApplication extends Application {

    private TableView<Contact> table;
    private ObservableList<Contact> data;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Contacts App");

        /* Create Table View */
        table = new TableView<>();
        data = FXCollections.observableArrayList();
        table.setItems(data);

        /* Define the table columns */
        TableColumn<Contact, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<Contact, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<Contact, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<Contact, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        TableColumn<Contact, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        TableColumn<Contact, String> cityCol = new TableColumn<>("City");
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        TableColumn<Contact, String> provinceCol = new TableColumn<>("Province");
        provinceCol.setCellValueFactory(new PropertyValueFactory<>("province"));

        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol, phoneCol, addressCol, cityCol, provinceCol);

        /* Load existing data from CSV */
        loadDataFromFile();

        // Add form to add/edit contacts
        HBox form = new HBox();
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone");
        TextField addressField = new TextField();
        addressField.setPromptText("Address");
        TextField cityField = new TextField();
        cityField.setPromptText("City");
        TextField provinceField = new TextField();
        provinceField.setPromptText("Province");


        // new button with text "Add"
        Button addButton = new Button("Add");
        // event handler for the button
        addButton.setOnAction(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();
            String city = cityField.getText();
            String province = provinceField.getText();


            // Check if all fields are not empty
            if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !address.isEmpty() && !city.isEmpty() && !province.isEmpty()) {
                // Create a new Contact object with the retrieved field values
                Contact newContact = new Contact(firstName, lastName, email, phone, address, city, province);
                // Add the new Contact to the data list
                data.add(newContact);
                // Clear the text fields for first name, last name, email, phone, address, city, and province
                firstNameField.setText("");
                lastNameField.setText("");
                emailField.setText("");
                phoneField.setText("");
                addressField.setText("");
                cityField.setText("");
                provinceField.setText("");
                // Save the data to a file
                saveDataToFile();
            }
        });

        // Add all the form fields to the form
        form.getChildren().addAll(firstNameField, lastNameField, emailField, phoneField, addressField, cityField, provinceField, addButton);

        // Add delete button
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            Contact selectedContact = table.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                data.remove(selectedContact);
                saveDataToFile();
            }
        });

        // Add layout
        VBox root = new VBox();
        root.getChildren().addAll(table, form, deleteButton);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadDataFromFile() {
        // Error handling is very pog
        try {
             /* BufferedReader initalized with a FileReader.
              * filename object passed represents the file to be read. */
            BufferedReader reader = new BufferedReader(new FileReader("contacts.csv"));

            /* The "line" declared to store each line of data read from the file.*/
            String line;

            /* This line reads a line of text from the BufferedReader object and assigns it to the line variable.
             * then line is checked if null, (indicates end of file has been reached).
             * If line is not null, the loop body is executed. */
            while ((line = reader.readLine()) != null) {

                /* splits line variable into an array of String obj (using comma as delim).
                 * The resulting array is assigned to the fields variable. */
                String[] fields = line.split(",");

                /* This line checks whether the fields array has a length of 6. (indicates valid contact record)
                 * If it does, it means that the line contains exactly 7 fields of data, so it's a valid contact record.*/
                if (fields.length == 7) {

                    /* new Contact object using the data from the fields array.
                     * Each element of the array corresponds to a field of Contact object. */
                    Contact contact = new Contact(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6]);

                    /* Add "contact" to Arraylist "data" */
                    data.add(contact);
                }
            }
            reader.close();
        /* Catches any IOExceptions */
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /* Saves the data to file. duh*/
    private void saveDataToFile() {
        try {
            /* declare FileWriter object. Used to write data to a file called "contacts.csv".*/
            FileWriter writer = new FileWriter(new File("contacts.csv"));

            /* *e n h a n c e d* for loop that will iterate over each Contact object in data (ArrayList). */
            for (Contact contact : data)
                writer.write(contact.getFirstName() + "," + contact.getLastName() + "," + contact.getEmail() + "," + contact.getPhone() + "," + contact.getAddress() + "," + contact.getCity() + "," + contact.getProvince() + "," + "\n");

            /* Release resources because *o p t i m i z a t i o n* */
            writer.close();

        /* Cool kids do error handling :)*/
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
