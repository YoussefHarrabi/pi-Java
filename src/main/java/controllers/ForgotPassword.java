package controllers;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import com.sendgrid.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ForgotPassword {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField emailField;

    @FXML
    private TextField newPasswordField;

    @FXML
    private TextField verificationCodeField;

    @FXML
    void resetPassword(ActionEvent event) {

    }

    @FXML
    void sendVerificationCode(ActionEvent event) {
        String email = emailField.getText();

        // Check if the email is empty
        if (email.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter your email address.");
            return;
        }

        // Check if the email is valid
        if (!isValidEmail(email)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a valid email address.");
            return;
        }

        // Send the verification code via email using SendGrid API
        sendVerificationCodeViaSendGrid(email);
    }

    private void sendVerificationCodeViaSendGrid(String email) {
        Email from = new Email("Youssefharrabi99@gmail.com");
        String subject = "Verification Code";
        Email to = new Email(email);
        String verificationCode = generateVerificationCode(); // You need to implement this method
        String bodyContent = "Your verification code is: " + verificationCode;
        Content content = new Content("text/plain", bodyContent);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Verification code sent to your email.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to send verification code. Please try again later.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to send verification code. Please try again later.");
        }
    }
    private String generateVerificationCode() {
        // Define the length of the verification code
        int length = 6;

        // Define the characters allowed in the verification code
        String characters = "0123456789";

        // Create a StringBuilder to store the verification code
        StringBuilder verificationCode = new StringBuilder();

        // Create a random object
        Random random = new Random();

        // Generate the verification code
        for (int i = 0; i < length; i++) {
            // Generate a random index within the range of the characters string
            int index = random.nextInt(characters.length());

            // Append the character at the random index to the verification code
            verificationCode.append(characters.charAt(index));
        }

        // Convert the StringBuilder to a String and return the verification code
        return verificationCode.toString();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private boolean isValidEmail(String email) {
        // Your email validation logic here
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }



    @FXML
    void initialize() {

    }

}
