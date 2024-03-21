package CodeCademy.GUI;

import javafx.event.ActionEvent;
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
    stage.setMaximized(true);
    stage.setTitle("CodeCademy Home");

    HBox hbox = new HBox(cursistBtn, cursusBtn);
    VBox vbox = new VBox(hbox);

    this.scene = new Scene(vbox);

    cursistBtn.setStyle("-fx-font-size: 14;");
    cursusBtn.setStyle("-fx-font-size: 14;");

    cursistBtn.setOnAction(this::OpenCursist);
  }

  private void OpenCursist(ActionEvent event) {
    GuiMain.SCENE_MANAGER.switchScene(SceneType.CURSIST);
    stage.setTitle("Cursist");
    stage.setMaximized(true);
  }
}