package Entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Equipe {

    private int id;
    private SimpleStringProperty relationTo;
    private SimpleIntegerProperty nbrPersonne;
    private Work worke;

    public Equipe(int id, String relationTo, int nbrPersonne, Work worke) {
        this.id = id;
        this.relationTo = new SimpleStringProperty(relationTo);
        this.nbrPersonne = new SimpleIntegerProperty(nbrPersonne);
        this.worke = worke;
    }
    public Equipe(int id, String relationTo, int nbrPersonne) {
        this.id = id;
        this.relationTo = new SimpleStringProperty(relationTo);
        this.nbrPersonne = new SimpleIntegerProperty(nbrPersonne);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SimpleStringProperty relationToProperty() {
        return relationTo;
    }

    public String getRelationTo() {
        return relationTo.get();
    }

    public void setRelationTo(SimpleStringProperty relationTo) {
        this.relationTo = relationTo;
    }

    public SimpleIntegerProperty nbrPersonneProperty() {
        return nbrPersonne;
    }

    public int getNbrPersonne() {
        return nbrPersonne.get();
    }

    public void setNbrPersonne(SimpleIntegerProperty nbrPersonne) {
        this.nbrPersonne = nbrPersonne;
    }

    public Work getWorke() {
        return worke;
    }

    public void setWorke(Work worke) {
        this.worke = worke;
    }

    @Override
    public String toString() {
        String workDetails = (worke != null) ? worke.toString() : "No associated work";

        return "Equipe{" +
                "id=" + id +
                ", relationTo='" + relationTo + '\'' +
                ", nbrPersonne=" + nbrPersonne +
                ", worke=" + workDetails +
                '}';
    }
}
