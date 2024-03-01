package controllers.Ticket;

import java.net.URL;
import java.util.ResourceBundle;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Paiement {

    public WebView webView;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField billingAddressField;

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField cvvField;

    @FXML
    private TextField expiryDateField;

    // Stripe API secret key
    private final String STRIPE_SECRET_KEY = "sk_test_51OouDsEilKEX8RIJTQYpiPOpvuf14RxYLxpxbai50AyMH3jcqbgsZW1ntLqJ8PQCuCb1bwN9wmFJ2L82XdAxYX4M00O8S2JiqU";

    public void processPayment(ActionEvent event) {
        String cardNumber = cardNumberField.getText();
        String expiryDate = expiryDateField.getText();
        String cvv = cvvField.getText();
        String billingAddress = billingAddressField.getText();

        // Initialize Stripe with your secret key
        Stripe.apiKey = STRIPE_SECRET_KEY;

        // Create a charge with the user's payment details
        try {
            // Set up the parameters for charge creation
            ChargeCreateParams params = ChargeCreateParams.builder()
                    .setAmount(1000L)  // Amount in cents (e.g., $10.00)
                    .setCurrency("usd")  // Currency
                    .setSource("tok_visa")  // Token representing the card
                    .setDescription("Example Charge")  // Description of the charge
                    .build();

            // Create the charge
            Charge charge = Charge.create(params);

            // Payment was successful
            System.out.println("Payment successful! Charge ID: " + charge.getId());
        } catch (StripeException e) {
            // Handle any errors from Stripe
            e.printStackTrace();
            System.err.println("Error processing payment: " + e.getMessage());
        }
    }


    @FXML
    void initialize() {
        WebEngine webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("/Ticket/payment_form.html").toExternalForm());

    }

}
