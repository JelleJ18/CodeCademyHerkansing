package CodeCademy.GUI;

import java.time.LocalDate;

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

public class CursistGUI extends SceneWrapper {
  // Variables for this window
  private TableView<Cursist> tableView = new TableView<>();
  private Button refresh = new Button("Refresh");
  private Button home = new Button("Home");
  private Button create = new Button("Create");
  private Button edit = new Button("Edit");
  private Button delete = new Button("Delete");

  TableColumn<Cursist, String> nameColumn = new TableColumn<>("Name");
  TableColumn<Cursist, String> emailColumn = new TableColumn<>("Email");
  TableColumn<Cursist, LocalDate> dateColumn = new TableColumn<>("Date Of Birth");
  TableColumn<Cursist, String> genderColumn = new TableColumn<>("Gender");
  TableColumn<Cursist, String> addressColumn = new TableColumn<>("Address");
  TableColumn<Cursist, String> hometownColumn = new TableColumn<>("Hometown");
  TableColumn<Cursist, String> countryColumn = new TableColumn<>("Country");

  public CursistGUI(Stage stage) {
    super(stage);
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
    genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
    addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    hometownColumn.setCellValueFactory(new PropertyValueFactory<>("hometown"));
    countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));

    tableView = new TableView<>();
    tableView.getColumns().add(nameColumn);
    tableView.getColumns().add(emailColumn);
    tableView.getColumns().add(dateColumn);
    tableView.getColumns().add(genderColumn);
    tableView.getColumns().add(addressColumn);
    tableView.getColumns().add(hometownColumn);
    tableView.getColumns().add(countryColumn);

    HBox hbox = new HBox(home, refresh, create, edit, delete);
    hbox.setSpacing(10);
    hbox.setPadding(new Insets(10.0));
    VBox vBox1 = new VBox(hbox, tableView);
    HBox hBox2 = new HBox(vBox1);

    create.setOnAction(this::CreateCursist);
    home.setOnAction(this::GoHome);
    refresh.setOnAction(this::Refresh);

    emailColumn.setPrefWidth(200);
    addressColumn.setPrefWidth(250);
    hometownColumn.setPrefWidth(200);
    countryColumn.setPrefWidth(150);
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
    stage.setMaximized(true);
  }

  private void GoHome(Event e) {
    GuiMain.SCENE_MANAGER.switchScene(SceneType.HOME);
    stage.setMaximized(true);
  }

  private void Refresh(Event e) {
    populateTable(e);
  }

}
