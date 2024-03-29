package CodeCademy.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CursistEditGUI extends Application {

  private TextField cursistIdField;
  private TextField naamField;
  private TextField emailField;
  private DatePicker dateOfBirthPicker;
  private ComboBox<String> genderComboBox;
  private TextArea addressArea;
  private TextField hometownField;
  private TextField countryField;

  private Connection connection; // Jouw databaseverbinding

  @Override
  public void start(Stage stage) {
    stage.setTitle("Cursist Bewerken");

    Label cursistIdLabel = new Label("Cursist ID:");
    cursistIdField = new TextField();

    Label nameLabel = new Label("Naam:");
    naamField = new TextField();

    Label emailLabel = new Label("E-mail:");
    emailField = new TextField();

    Label dobLabel = new Label("Geboortedatum:");
    dateOfBirthPicker = new DatePicker();

    Label genderLabel = new Label("Geslacht:");
    genderComboBox = new ComboBox<>();
    genderComboBox.getItems().addAll("Man", "Vrouw", "Anders");

    Label addressLabel = new Label("Adres:");
    addressArea = new TextArea();

    Label hometownLabel = new Label("Woonplaats:");
    hometownField = new TextField();

    Label countryLabel = new Label("Land:");
    countryField = new TextField();

    Button fetchButton = new Button("Ophalen");
    fetchButton.setOnAction(e -> fetchCursistData());

    Button saveButton = new Button("Opslaan");
    saveButton.setOnAction(e -> saveCursistData());

    VBox layout = new VBox(10, cursistIdLabel, cursistIdField, fetchButton,
        nameLabel, naamField, emailLabel, emailField,
        dobLabel, dateOfBirthPicker, genderLabel, genderComboBox,
        addressLabel, addressArea, hometownLabel, hometownField, countryLabel, countryField,
        saveButton);

    Scene scene = new Scene(layout, 400, 500);
    stage.setScene(scene);
    stage.show();
  }

  private void fetchCursistData() {
    try {
      // Vervang 'jouw_query' door de daadwerkelijke query om cursistgegevens op te
      // halen
      String query = "SELECT * FROM cursist WHERE cursist_id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, Integer.parseInt(cursistIdField.getText()));

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        naamField.setText(resultSet.getString("naam"));
        emailField.setText(resultSet.getString("email"));
        // Andere velden invullen op basis van je databasekolommen
      } else {
        // Cursist niet gevonden
        clearFields();
        System.out.println("Cursist niet gevonden.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void saveCursistData() {
    // Implementeer logica om bewerkte gegevens op te slaan (vervang dit met je
    // eigen logica)
    String naam = naamField.getText();
    String email = emailField.getText();
    // ... haal andere gegevens op

    // Voorbeeld: toon opgeslagen gegevens in de console
    System.out.println("Naam: " + naam);
    System.out.println("E-mail: " + email);
    // ... toon andere gegevens
  }

  private void clearFields() {
    naamField.clear();
    emailField.clear();
    dateOfBirthPicker.getEditor().clear();
    genderComboBox.getSelectionModel().clearSelection();
    addressArea.clear();
    hometownField.clear();
    countryField.clear();
  }

  public static void main(String[] args) {
    launch(args);
  }
}