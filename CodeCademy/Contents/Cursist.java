package CodeCademy.Contents;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cursist {
  private StringProperty name;
  private StringProperty email;
  private ObjectProperty<LocalDate> dateOfBirth;
  private StringProperty gender;
  private StringProperty address;
  private StringProperty hometown;
  private StringProperty country;

  public Cursist(String name, String email, LocalDate dateOfBirth,
      String gender, String address, String hometown, String country) {
    this.name = new SimpleStringProperty(name);
    this.email = new SimpleStringProperty(email);
    this.dateOfBirth = new SimpleObjectProperty<>(dateOfBirth);
    this.gender = new SimpleStringProperty(gender);
    this.address = new SimpleStringProperty(address);
    this.hometown = new SimpleStringProperty(hometown);
    this.country = new SimpleStringProperty(country);
  }

  public StringProperty nameProperty() {
    return name;
  }

  public String getName() {
    return name.get();
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public StringProperty emailProperty() {
    return email;
  }

  public String getEmail() {
    return email.get();
  }

  public void setEmail(String email) {
    this.email.set(email);
  }

  public ObjectProperty<LocalDate> dateOfBirthProperty() {
    return dateOfBirth;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth.get();
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth.set(dateOfBirth);
  }

  public StringProperty genderProperty() {
    return gender;
  }

  public String getGender() {
    return gender.get();
  }

  public void setGender(String gender) {
    this.gender.set(gender);
  }

  public StringProperty addressProperty() {
    return address;
  }

  public String getAddress() {
    return address.get();
  }

  public void setAddress(String address) {
    this.address.set(address);
  }

  public StringProperty hometownProperty() {
    return hometown;
  }

  public String getHometown() {
    return hometown.get();
  }

  public void setHometown(String hometown) {
    this.hometown.set(hometown);
  }

  public StringProperty countryProperty() {
    return country;
  }

  public String getCountry() {
    return country.get();
  }

  public void setCountry(String country) {
    this.country.set(country);
  }

}