package entities;

import com.mysql.cj.conf.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public class Incident {
    int IncidentId ;
    String Type;
    String Place;
    Time Hour;
    String Description;
    Date Date;

    Button button;

    public Incident() {
    }

    public Incident(int incidentId, String type, String place, Time hour, String description) {
        IncidentId = incidentId;
        Type = type;
        Place = place;
        Hour = hour;
        Description = description;
        this.button = new Button("report");

    }

    public Incident(String type, String place, Time hour, String description) {
        Type = type;
        Place = place;
        Hour = hour;
        Description = description;
        this.button = new Button("report");

    }

    public int getIncidentId() {
        return IncidentId;
    }

    public void setIncidentId(int incidentId) {
        IncidentId = incidentId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public Time getHour() {
        return Hour;
    }

    public void setHour(Time hour) {
        Hour = hour;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "Incident{" +

                " Type='" + Type + '\'' +
                ", Place='" + Place + '\'' +
                ", Hour=" + Hour +
                ", Description='" + Description + '\'' +
                '}';
    }

    private static ObservableList<String> suggestions = FXCollections.observableArrayList(
            "Tunis", "Le Bardo", "Le Kram", "La Goulette", "Carthage", "Sidi Bou Said", "La Marsa",
            "Souk Lahad", "Ariana", "La Soukra", "Raoued", "Kalâat el-Andalous", "Sidi Thabet",
            "Ettadhamen-Mnihla", "Ben Arous", "El Mourouj", "Hammam Lif", "Hammam Chott",
            "Bou Mhel el-Bassatine", "Ezzahra", "Radès", "Mégrine", "Mohamedia-Fouchana",
            "Mornag", "Khalidia", "Manouba", "Den Den", "Douar Hicher", "Oued Ellil", "Mornaguia",
            "Borj El Amri", "Djedeida", "Tebourba", "El Battan", "Nabeul", "Dar Chaabane",
            "Béni Khiar", "El Maâmoura", "Somâa", "Korba", "Tazerka", "Menzel Temime",
            "Menzel Horr", "El Mida", "Kelibia", "Azmour", "Hammam Ghezèze", "Dar Allouch",
            "El Haouaria", "Takelsa", "Soliman", "Korbous", "Menzel Bouzelfa", "Béni Khalled",
            "Zaouiet Djedidi", "Grombalia", "Bou Argoub", "Hammamet", "Zaghouan", "Zriba",
            "Bir Mcherga", "Djebel Oust", "El Fahs", "Nadhour", "Bizerte", "Sejnane", "Mateur",
            "Menzel Bourguiba", "Tinja", "Ghar al Milh", "Aousja", "Menzel Jemil",
            "Menzel Abderrahmane", "El Alia", "Ras Jebel", "Metline", "Raf Raf", "Béja",
            "El Maâgoula", "Zahret Medien", "Nefza", "Téboursouk", "Testour", "Goubellat",
            "Majaz al Bab", "Jendouba", "Bou Salem", "Tabarka", "Aïn Draham", "Fernana",
            "Beni M'Tir", "Ghardimaou", "Oued Melliz", "El Kef", "Nebeur", "Touiref",
            "Sakiet Sidi Youssef", "Tajerouine", "Menzel Salem", "Kalaat es Senam",
            "Kalâat Khasba", "Jérissa", "El Ksour", "Dahmani", "Sers", "Siliana",
            "Bou Arada", "Gaâfour", "El Krib", "Sidi Bou Rouis", "Maktar", "Rouhia",
            "Kesra", "Bargou", "El Aroussa", "Sousse", "Ksibet Thrayet", "Ezzouhour",
            "Zaouiet Sousse", "Hammam Sousse", "Akouda", "Kalâa Kebira", "Sidi Bou Ali",
            "Hergla", "Enfidha", "Bouficha", "Sidi El Hani", "M'saken", "Kalâa Seghira",
            "Messaadine", "Kondar", "Monastir", "Khniss", "Ouerdanin", "Sahline Moôtmar",
            "Sidi Ameur", "Zéramdine", "Beni Hassen", "Ghenada", "Jemmal", "Menzel Kamel",
            "Zaouiet Kontoch", "Bembla-Mnara", "Menzel Ennour", "El Masdour", "Moknine",
            "Sidi Bennour", "Menzel Farsi", "Amiret El Fhoul", "Amiret Touazra",
            "Amiret El Hojjaj", "Cherahil", "Bekalta", "Téboulba", "Ksar Hellal",
            "Ksibet El Mediouni", "Benen Bodher", "Touza", "Sayada", "Lemta", "Bouhjar",
            "Menzel Hayet", "Mahdia", "Rejiche", "Bou Merdes", "Ouled Chamekh", "Chorbane",
            "Hebira", "Essouassi", "El Djem", "Kerker", "Chebba", "Melloulèche",
            "Sidi Alouane", "Ksour Essef", "El Bradâa", "Sfax", "Sakiet Ezzit", "Chihia",
            "Sakiet Eddaïer", "Gremda", "El Ain", "Thyna", "Agareb", "Jebiniana", "El Hencha",
            "Menzel Chaker", "Ghraïba", "Bir Ali Ben Khélifa", "Skhira", "Mahares", "Kerkennah",
            "Medenine", "Beni Khedache", "Ben Gardane", "Zarzis", "Houmt El Souk (Djerba)",
            "Midoun (Djerba)", "Ajim (Djerba)", "Tataouine", "Bir Lahmar", "Ghomrassen",
            "Dehiba", "Remada", "Gafsa", "El Ksar", "Moularès", "Redeyef", "Métlaoui",
            "Mdhila", "El Guettar", "Sened", "Tozeur", "Degache", "Hamet Jerid", "Nafta",
            "Tamerza", "Kebili", "Djemna", "Douz", "El Golâa", "Souk Lahad"
            // Add your place suggestions here
    );

    public static ObservableList<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(ObservableList<String> suggestions) {
        this.suggestions = suggestions;
    }
}
