package CodeCademy.Contents;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cursus {
  private StringProperty onderwerp;
  private StringProperty introductietekst;
  private StringProperty niveau;
  private StringProperty cursusnaam;

  public Cursus(String onderwerp, String introductietekst, String niveau, String cursusnaam) {
    this.onderwerp = new SimpleStringProperty(onderwerp);
    this.introductietekst = new SimpleStringProperty(introductietekst);
    this.niveau = new SimpleStringProperty(niveau);
    this.cursusnaam = new SimpleStringProperty(cursusnaam);
  }

  public StringProperty getOnderwerp() {
    return onderwerp;
  }

  public void setOnderwerp(StringProperty onderwerp) {
    this.onderwerp = onderwerp;
  }

  public StringProperty getIntroductietekst() {
    return introductietekst;
  }

  public void setIntroductietekst(StringProperty introductietekst) {
    this.introductietekst = introductietekst;
  }

  public StringProperty getNiveau() {
    return niveau;
  }

  public void setNiveau(StringProperty niveau) {
    this.niveau = niveau;
  }

  public StringProperty getCursusnaam() {
    return cursusnaam;
  }

  public void setCursusnaam(StringProperty cursusnaam) {
    this.cursusnaam = cursusnaam;
  }

}
