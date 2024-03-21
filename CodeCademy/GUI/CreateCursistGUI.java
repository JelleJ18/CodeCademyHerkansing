package CodeCademy.GUI;

import CodeCademy.Contents.Cursist;
import CodeCademy.Database.AppBehaviour;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateCursistGUI extends SceneWrapper {
  Button button;
  private Button homeBtn = new Button("Home");
  private Button backBtn = new Button("Back");

  // Inputs
  private TextField nameField;
  private TextField emailField;
  private DatePicker dobPicker;
  private ComboBox<String> genderComboBox;
  private TextArea addressArea;
  private TextField cityField;
  private TextField countryField;

  public CreateCursistGUI(Stage stage) {
    super(stage);

    Label nameLabel = new Label("Name: ");
    nameField = new TextField();
    nameField.setMaxWidth(500);

    Label emailLabel = new Label("E-mail:");
    emailField = new TextField();
    emailField.setMaxWidth(500);

    Label dobLabel = new Label("Geboortedatum:");
    dobPicker = new DatePicker();
    dobPicker.setMaxWidth(200);

    Label genderLabel = new Label("Geslacht:");
    genderComboBox = new ComboBox<>();
    genderComboBox.getItems().addAll("Man", "Vrouw", "Anders");
    genderComboBox.setMaxWidth(200);

    Label addressLabel = new Label("Adres:");
    addressArea = new TextArea();
    addressArea.setMaxWidth(500);

    Label cityLabel = new Label("Woonplaats:");
    cityField = new TextField();
    cityField.setMaxWidth(500);

    Label countryLabel = new Label("Land:");
    countryField = new TextField();
    countryField.setMaxWidth(500);

    Button saveButton = new Button("Opslaan");
    saveButton.setOnAction(this::submitData);
    homeBtn.setOnAction(this::goHome);
    backBtn.setOnAction(this::goBack);

    HBox hBox = new HBox(homeBtn, backBtn);
    hBox.setSpacing(10);
    hBox.setAlignment(javafx.geometry.Pos.CENTER);

    VBox layout = new VBox(hBox, nameLabel, nameField, emailLabel, emailField,
        dobLabel, dobPicker, genderLabel, genderComboBox,
        addressLabel, addressArea, cityLabel, cityField, countryLabel, countryField, saveButton);

    layout.setPadding(new Insets(10));
    layout.setSpacing(5);
    layout.setAlignment(javafx.geometry.Pos.TOP_CENTER);

    this.scene = new Scene(layout);
  }

  private void submitData(Event e) {
    String selectedGender = genderComboBox.getSelectionModel().getSelectedItem();
    try {
      AppBehaviour.createCursist(
          new Cursist(
              nameField.getText(),
              emailField.getText(),
              dobPicker.getValue(),
              selectedGender,
              addressArea.getText(),
              cityField.getText(),
              countryField.getText()));
    } catch (NumberFormatException error) {
      showErrorMessage("Not all values are assigned!");
      return;
    }

    GuiMain.SCENE_MANAGER.switchScene(SceneType.CURSIST);
  }

  private void goHome(Event e) {
    GuiMain.SCENE_MANAGER.switchScene(SceneType.HOME);
  }

  private void goBack(Event e) {
    GuiMain.SCENE_MANAGER.switchScene(SceneType.CURSIST);
  }

  private void showErrorMessage(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
