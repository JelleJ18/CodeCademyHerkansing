package CodeCademy;

import java.sql.DriverManager;

import java.sql.*;
import javafx.application.Application;

import CodeCademy.GUI.*;

public class MainConnection {
  public static Connection connection;

  public static void main(String[] args) {
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

      // link
      String connectionUrl = "jdbc:sqlserver://aei-sql2.avans.nl:1443;encrypt=true;databaseName=DatabaseNaam;user=usernaam;password=wachtwoord;trustServerCertificate=true";
      String username = "usernaam";
      String password = "wachtwoord";

      // zorgt voor een connectie
      connection = DriverManager.getConnection(connectionUrl, username, password);
      System.out.println("Connected!");
    } catch (Exception e) {
      System.out.println("Oops. error!");
      e.printStackTrace();
    }
    Application.launch(GuiMain.class);
  }
}
