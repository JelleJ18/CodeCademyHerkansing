package CodeCademy.GUI;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainHomeGui extends SceneWrapper {

  private Button cursistBtn = new Button("Cursist");
  private Button cursusBtn = new Button("Cursus");

  public MainHomeGui(Stage stage) {
    super(stage);
    stage.setTitle("CodeCademy Home");

    HBox hbox = new HBox(cursistBtn, cursusBtn);
    hbox.setSpacing(10);

    VBox vbox = new VBox(hbox);
    vbox.setPadding(new Insets(10));
    hbox.setAlignment(javafx.geometry.Pos.CENTER);

    this.scene = new Scene(vbox);

    cursistBtn.setStyle("-fx-font-size: 14;");
    cursusBtn.setStyle("-fx-font-size: 14;");

    cursistBtn.setOnAction(this::OpenCursist);
  }

  private void OpenCursist(ActionEvent event) {
    GuiMain.SCENE_MANAGER.switchScene(SceneType.CURSIST);
    stage.setTitle("Cursist");
  }
}