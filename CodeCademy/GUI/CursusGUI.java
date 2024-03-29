package CodeCademy.GUI;

import CodeCademy.Contents.Cursist;
import CodeCademy.Database.AppBehaviour;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CursusGUI extends SceneWrapper {
  // Variables for this window
  private TableView<Cursist> tableView = new TableView<>();
  private Button refresh = new Button("Refresh");
  private Button home = new Button("Home");
  private Button create = new Button("Create");
  private Button edit = new Button("Edit");
  private Button delete = new Button("Delete");

  TableColumn<Cursist, String> nameColumn = new TableColumn<>("Naam");
  TableColumn<Cursist, String> onderwerpColumn = new TableColumn<>("Onderwerp");
  TableColumn<Cursist, String> introductieColumn = new TableColumn<>("Introductie");
  TableColumn<Cursist, String> niveauColumn = new TableColumn<>("Niveau");

  public CursusGUI(Stage stage) {
    super(stage);
    stage.show();
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("naam"));
    onderwerpColumn.setCellValueFactory(new PropertyValueFactory<>("onderwerp"));
    introductieColumn.setCellValueFactory(new PropertyValueFactory<>("introductie"));
    niveauColumn.setCellValueFactory(new PropertyValueFactory<>("niveau"));
    ;

    tableView = new TableView<>();
    tableView.getColumns().add(nameColumn);
    tableView.getColumns().add(onderwerpColumn);
    tableView.getColumns().add(introductieColumn);
    tableView.getColumns().add(niveauColumn);

    HBox hbox = new HBox(home, refresh, create, edit, delete);
    hbox.setSpacing(10);
    hbox.setPadding(new Insets(10.0));
    VBox vBox1 = new VBox(hbox, tableView);
    HBox hBox2 = new HBox(vBox1);

    create.setOnAction(this::CreateCursist);
    home.setOnAction(this::GoHome);
    refresh.setOnAction(this::Refresh);

    onderwerpColumn.setPrefWidth(200);
    introductieColumn.setPrefWidth(250);
    niveauColumn.setPrefWidth(200);
    this.scene = new Scene(hBox2);
    tableView.prefHeightProperty().bind(scene.heightProperty());
    tableView.prefWidthProperty().bind(scene.widthProperty());

    populateTable(new Event(EventType.ROOT));
  }

  private void populateTable(Event e) {
    tableView.setItems(FXCollections.observableArrayList(AppBehaviour.getCursisten()));
  }

  private void CreateCursist(Event e) {
    GuiMain.SCENE_MANAGER.switchScene(SceneType.CURSISTCREATE);
  }

  private void GoHome(Event e) {
    GuiMain.SCENE_MANAGER.switchScene(SceneType.HOME);
  }

  private void Refresh(Event e) {
    populateTable(e);
  }

}
