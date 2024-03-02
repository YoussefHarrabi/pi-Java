package entities;

import utils.MyConnection;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.sql.Connection;

public class SMSsender {

    Connection cnx;

    public SMSsender() {
        cnx = MyConnection.getInstance().getCnx();
    }

    // twilio.com/console
    public static final String ACCOUNT_SID = "AC071be9549f8729e64e1d0c767470d51b";
    public static final String AUTH_TOKEN = "034a82b0aa1dfe2723a6b61df781c283";

    public static void main(String[] args) {

    }

    public static void sendSMS(String clientPhoneNumber, String s) {

        String accountSid = "AC071be9549f8729e64e1d0c767470d51b";
        String authToken =   "034a82b0aa1dfe2723a6b61df781c283";

        try {
            Twilio.init(accountSid, authToken);
            Message message = Message.creator(
                    new PhoneNumber("+216" + clientPhoneNumber),
                    new PhoneNumber("+18573924757"),
                    "Vous avez un retard"
            ).create();

            System.out.println("SID du message : " + message.getSid());
        } catch (Exception ex) {
            System.out.println("Erreur : " + ex.getMessage());
        }
    }
}