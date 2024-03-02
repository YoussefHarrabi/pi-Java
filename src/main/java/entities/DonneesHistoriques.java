package entities;

public class DonneesHistoriques {
    private int id;
    private int idCapteur;
    private String timestamp;
    private int niveauEmbouteillage;
    private String alerte;
    private String conditionsMeteo;

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCapteur() {
        return idCapteur;
    }

    public void setIdCapteur(int idCapteur) {
        this.idCapteur = idCapteur;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getNiveauEmbouteillage() {
        return niveauEmbouteillage;
    }

    public void setNiveauEmbouteillage(int niveauEmbouteillage) {
        this.niveauEmbouteillage = niveauEmbouteillage;
    }

    public String getAlerte() {
        return alerte;
    }

    public void setAlerte(String alerte) {
        this.alerte = alerte;
    }

    public String getConditionsMeteo() {
        return conditionsMeteo;
    }

    public void setConditionsMeteo(String conditionsMeteo) {
        this.conditionsMeteo = conditionsMeteo;
    }


    public String getHeureCapture() {
            // Supposons que le format de l'horodatage soit HH:mm:ss
            // Vous pouvez adapter cette méthode en fonction du format réel de votre horodatage
            return timestamp.substring(0, 8); // Récupère les 8 premiers caractères pour obtenir l'heure
        }

    }

