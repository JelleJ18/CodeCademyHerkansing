package CodeCademy.GUI;

import CodeCademy.Contents.Cursist;
import CodeCademy.Database.AppBehaviour;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    Label emailLabel = new Label("E-mail:");
    emailField = new TextField();

    Label dobLabel = new Label("Geboortedatum:");
    dobPicker = new DatePicker();

    Label genderLabel = new Label("Geslacht:");
    genderComboBox = new ComboBox<>();
    genderComboBox.getItems().addAll("Man", "Vrouw", "Anders");

    Label addressLabel = new Label("Adres:");
    addressArea = new TextArea();

    Label cityLabel = new Label("Woonplaats:");
    cityField = new TextField();

    Label countryLabel = new Label("Land:");
    countryField = new TextField();

    Button saveButton = new Button("Opslaan");
    saveButton.setOnAction(this::submitData);
    homeBtn.setOnAction(this::goHome);
    backBtn.setOnAction(this::goBack);

    HBox hBox = new HBox(homeBtn, backBtn);

    VBox layout = new VBox(hBox, nameLabel, nameField, emailLabel, emailField,
        dobLabel, dobPicker, genderLabel, genderComboBox,
        addressLabel, addressArea, cityLabel, cityField, countryLabel, countryField, saveButton);

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
      System.out.println(error.getMessage());
      return;
    }

    GuiMain.SCENE_MANAGER.switchScene(SceneType.CURSIST);
  }

  private void goHome(Event e) {
    GuiMain.SCENE_MANAGER.switchScene(SceneType.HOME);
    stage.setMaximized(true);
  }

  private void goBack(Event e) {
    GuiMain.SCENE_MANAGER.switchScene(SceneType.CURSIST);
    stage.setMaximized(true);
  }
}
