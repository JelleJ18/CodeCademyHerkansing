package CodeCademy.GUI;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SceneManager {
  private final SceneType splash = SceneType.HOME;

  private HashMap<SceneType, SceneWrapper> scenes;
  private SceneType currentScene = splash;
  private Stage mainStage;

  public SceneManager(Stage mainStage) {
    this.scenes = new HashMap<>(Map.ofEntries(
        Map.entry(SceneType.HOME, new MainHomeGui(mainStage)),
        Map.entry(SceneType.CURSIST, new CursistGUI(mainStage)),
        Map.entry(SceneType.CURSISTCREATE, new CreateCursistGUI(mainStage))));

    this.scenes.get(splash).show();
    this.mainStage = mainStage;
  }

  public void switchScene(SceneType scene) {
    System.out.println("Switching");
    this.scenes.get(scene).show();
    this.currentScene = scene;
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    mainStage.setX(primaryScreenBounds.getMinX());
    mainStage.setY(primaryScreenBounds.getMinY());
    mainStage.setWidth(primaryScreenBounds.getWidth());
    mainStage.setHeight(primaryScreenBounds.getHeight());

    // Eerst het maximizen op false zetten, omdat er anders een fout zit met de UI
    // te scalen. (ik vond geen andere oplossing hiervoor).
    mainStage.setMaximized(false);
    mainStage.setMaximized(true);
    mainStage.show();
  }

  public static void showErrorDialog(String message) {
    new Alert(Alert.AlertType.ERROR, message, ButtonType.CLOSE).show();
  }

  public SceneWrapper getCurrentScene() {
    return this.scenes.get(currentScene);
  }
}
