package CodeCademy.GUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class GuiMain extends Application {
  public static SceneManager SCENE_MANAGER;

  @Override
  public void start(Stage stage) {
    stage.setTitle("CodeCademy Application");
    SCENE_MANAGER = new SceneManager(stage);
    SCENE_MANAGER.switchScene(SceneType.HOME);
    stage.show();
  }
}
