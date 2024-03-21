package CodeCademy.GUI;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class SceneManager {
  private final SceneType splash = SceneType.HOME;

  private HashMap<SceneType, SceneWrapper> scenes;
  private SceneType currentScene = splash;
  private Stage mainStage;

  public SceneManager(Stage mainStage) {
    this.mainStage = mainStage;
    this.scenes = new HashMap<>(Map.ofEntries(
        Map.entry(SceneType.HOME, new MainHomeGui(mainStage)),
        Map.entry(SceneType.CURSIST, new CursistGUI(mainStage)),
        Map.entry(SceneType.CURSISTCREATE, new CreateCursistGUI(mainStage))));

    this.scenes.get(splash).show();
    mainStage.setMinHeight(768);
    mainStage.setMinWidth(1024);
    mainStage.setMaximized(true);
  }

  public void switchScene(SceneType scene) {
    System.out.println("Switching");
    this.scenes.get(scene).show();
    this.currentScene = scene;
  }

  public static void showErrorDialog(String message) {
    new Alert(Alert.AlertType.ERROR, message, ButtonType.CLOSE).show();
  }

  public SceneWrapper getCurrentScene() {
    return this.scenes.get(currentScene);
  }
}
