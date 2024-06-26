package CodeCademy.Database;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import CodeCademy.MainConnection;
import CodeCademy.Contents.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AppBehaviour {
  private static Connection connection = MainConnection.connection;

  // Cursist methoden
  public static void createCursist(Cursist cursist) {
    try {
      if (cursist.getName().isEmpty() ||
          cursist.getEmail().isEmpty() ||
          cursist.getDateOfBirth() == null ||
          cursist.getGender().isEmpty() ||
          cursist.getAddress().isEmpty() ||
          cursist.getHometown().isEmpty() ||
          cursist.getCountry().isEmpty()) {
        throw new IllegalArgumentException("All fields are required");
      }

      PreparedStatement insertStatement = connection.prepareStatement(
          "INSERT INTO Cursist(naam, email, dateOfBirth, gender, address, hometown, country)\n" +
              "VALUES(?, ?, ?, ?, ?, ?, ?)");
      insertStatement.setString(1, cursist.getName());
      insertStatement.setString(2, cursist.getEmail());
      insertStatement.setDate(3, Date.valueOf(cursist.getDateOfBirth()));
      insertStatement.setString(4, cursist.getGender());
      insertStatement.setString(5, cursist.getAddress());
      insertStatement.setString(6, cursist.getHometown());
      insertStatement.setString(7, cursist.getCountry());

      insertStatement.executeUpdate();
    } catch (SQLException e) {
      handleSQLException(e);
    } catch (IllegalArgumentException e) {
      // Show an alert to the user indicating that the date of birth is required
      showErrorAlert("Error", "Not all the values have been assigned!");
    }
  }

  public static List<Cursist> getCursisten() {
    return getCursisten(0);
  }

  public static List<Cursist> getCursisten(int offset) {
    ArrayList<Cursist> list = new ArrayList<>();

    try {
      PreparedStatement selectCursist = connection.prepareStatement(
          "SELECT * FROM Cursist ORDER BY naam OFFSET ? ROWS FETCH NEXT 100 ROWS ONLY");

      selectCursist.setInt(1, offset);

      ResultSet rs = selectCursist.executeQuery();

      while (rs.next()) {
        list.add(new Cursist(
            rs.getString("naam"),
            rs.getString("email"),
            rs.getDate("dateOfBirth").toLocalDate(),
            rs.getString("gender"),
            rs.getString("address"),
            rs.getString("hometown"),
            rs.getString("country")));
      }

    } catch (SQLException e) {
      handleSQLException(e);
    }

    return list;
  }

  public static void editCursist(Cursist cursist) {
    try {
      PreparedStatement updateStatement = connection.prepareStatement(
          "UPDATE Cursist SET email = ?, dateOfBirth = ?, gender = ?, address = ?, hometown = ?, country = ?\n" +
              "WHERE naam = ?");

      updateStatement.setString(1, cursist.getEmail());
      updateStatement.setDate(2, Date.valueOf(cursist.getDateOfBirth()));
      updateStatement.setString(3, cursist.getGender());
      updateStatement.setString(4, cursist.getAddress());
      updateStatement.setString(5, cursist.getHometown());
      updateStatement.setString(6, cursist.getCountry());
      updateStatement.setString(7, cursist.getName());

      updateStatement.executeUpdate();
    } catch (SQLException e) {
      handleSQLException(e);
    }
  }

  public static List<Cursus> getCursussen() {
    return getCursussen(0);
  }

  public static List<Cursus> getCursussen(int offset) {
    ArrayList<Cursus> list = new ArrayList<>();

    try {
      PreparedStatement selectCursus = connection.prepareStatement(
          "SELECT * FROM Cursus ORDER BY cursusnaam OFFSET ? ROWS FETCH NEXT 100 ROWS ONLY");

      selectCursus.setInt(1, offset);

      ResultSet rs = selectCursus.executeQuery();

      while (rs.next()) {
        list.add(new Cursus(
            rs.getString("cursusnaam"),
            rs.getString("onderwerp"),
            rs.getString("introductietekst"),
            rs.getString("niveau")));
      }

    } catch (SQLException e) {
      handleSQLException(e);
    }

    return list;
  }

  public static void editCursus(Cursus cursus) {
    try {
      PreparedStatement updateStatement = connection.prepareStatement(
          "UPDATE Cursus SET onderwerp = ?, introductietekst = ?, niveau = ?\n" +
              "WHERE cursusnaam = ?");

      updateStatement.setString(1, cursus.getOnderwerp().get());
      updateStatement.setString(2, cursus.getIntroductietekst().get());
      updateStatement.setString(3, cursus.getNiveau().get());
      updateStatement.setString(4, cursus.getCursusnaam().get());

      updateStatement.executeUpdate();
    } catch (SQLException e) {
      handleSQLException(e);
    }
  }

  // Handles sqlerrors
  private static void handleSQLException(SQLException e) {
    e.printStackTrace();
  }

  // Show Alert for user, can be used anywhere
  private static void showErrorAlert(String title, String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
