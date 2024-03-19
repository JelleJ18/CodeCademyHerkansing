package CodeCademy.GUI;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneWrapper {
  protected Stage stage;
  protected Scene scene;

  public SceneWrapper(Stage stage) {
    this.stage = stage;
  }

  public void show() {
    this.stage.setScene(this.scene);
  }
}