package entities;

import utils.MyConnection;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.sql.Connection;

public class SMSsender {

    private Connection cnx;
    private static String messageBody; // Ajout du champ messageBody

    public SMSsender(String messageBody) {
        cnx = MyConnection.getInstance().getCnx();
        this.messageBody = messageBody; // Initialisation du champ messageBody
    }

    // twilio.com/console
    public static final String ACCOUNT_SID = "AC071be9549f8729e64e1d0c767470d51b";
    public static final String AUTH_TOKEN = "6a3eb58033746128f346356c06cca01b";

    // Reste du code...

    public static void sendSMS(String clientPhoneNumber) {
        String accountSid = "AC071be9549f8729e64e1d0c767470d51b";
        String authToken = "6a3eb58033746128f346356c06cca01b";

        try {
            Twilio.init(accountSid, authToken);
            Message message = Message.creator(
                    new PhoneNumber("+216" + clientPhoneNumber),
                    new PhoneNumber("+18573924757"),
                    messageBody // Utilisation du champ messageBody ici
            ).create();

            System.out.println("SID du message : " + message.getSid());
        } catch (Exception ex) {
            System.out.println("Erreur : " + ex.getMessage());
        }
    }
}
