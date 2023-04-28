/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym3;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private AnchorPane sendotp;
    @FXML
    private AnchorPane sendOtp;
    @FXML
    private TextField email;
    @FXML
    private TextField otpp;
    @FXML
    private Label status;
    @FXML
    private AnchorPane otpps;

    private int num;
    @FXML
    private Button backtoLogin;
    @FXML
    private Button doSendOtp;
    @FXML
    private Button conformOTP;
    @FXML
    private Button goBacktoLogin;
    @FXML
    private Label status2;
    @FXML
    private AnchorPane newPassSet;
    @FXML
    private Button newPassSave;
    @FXML
    private TextField pass2;

    private String email2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        doSendOtp.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        doSendOtp.setOnMouseEntered(e -> doSendOtp.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        doSendOtp.setOnMouseExited(e -> doSendOtp.setStyle("-fx-background-color: black; -fx-text-fill: white;"));

        backtoLogin.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        backtoLogin.setOnMouseEntered(e -> backtoLogin.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        backtoLogin.setOnMouseExited(e -> backtoLogin.setStyle("-fx-background-color: black; -fx-text-fill: white;"));

        conformOTP.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        conformOTP.setOnMouseEntered(e -> conformOTP.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        conformOTP.setOnMouseExited(e -> conformOTP.setStyle("-fx-background-color: black; -fx-text-fill: white;"));

        goBacktoLogin.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        goBacktoLogin.setOnMouseEntered(e -> goBacktoLogin.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        goBacktoLogin.setOnMouseExited(e -> goBacktoLogin.setStyle("-fx-background-color: black; -fx-text-fill: white;"));

        newPassSave.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        newPassSave.setOnMouseEntered(e -> newPassSave.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        newPassSave.setOnMouseExited(e -> newPassSave.setStyle("-fx-background-color: black; -fx-text-fill: white;"));

        // TODO
    }

    @FXML
    private void doSendOtp(ActionEvent event) {

        otpps.setVisible(false);

        Random rand = new Random();
        num = (int) (Math.random() * 9000) + 1000; // assign the random number to num

        String Email = email.getText();

        email2 = Email;
        String emailCheck = "^[A-Z0-9._%+-]+@[A-Z0-9._]+\\.[A-Z]{2,6}$";

        Pattern emailPattern = Pattern.compile(emailCheck, Pattern.CASE_INSENSITIVE);

        Matcher matcher = emailPattern.matcher(Email);

        Alert alert;

        final String senderEmail = "javatesbot1@gmail.com";
        final String senderPassword = "zfgcaacakmiguvox";

        String recipientEmail = email.getText();

        String subject = "Welcome";
        // Email message

        String message = "your varification code is " + num;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {

            if (Email.isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please Fill the Information");
                alert.showAndWait();
            } else if (emailPattern.matcher(Email).matches()) {
                status2.setText("");
                Message msg = new MimeMessage(session);
                // Set the sender, recipient, subject, and message text
                msg.setFrom(new InternetAddress(senderEmail));
                msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
                msg.setSubject(subject);
                msg.setText(message);
                // Send the message
                javax.mail.Transport.send(msg);
                System.out.println("Email sent successfully.");

                otpps.setVisible(true);

                sendOtp.setVisible(false);
                //newPass.setVisible(false);
            } else {
                status2.setText("Email is not valid");
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }

    }

    Alert alert;

    @FXML
    private void conformOTP(ActionEvent event) {
        if (otpp.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter your OTP here");
            alert.showAndWait();

        } else if (num == Integer.parseInt(otpp.getText())) { // use num to compare with the entered OTP
            //status.setText("valid");
            otpps.setVisible(false);
            newPassSet.setVisible(true);
        } else {
            status.setText("Invalid");
        }
    }

    @FXML
    private void goBacktoLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("forgetPassword.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        sendotp.getScene().getWindow().hide();
    }

    @FXML
    private void backtoLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        sendOtp.getScene().getWindow().hide();
    }

    Statement st;
    ResultSet rs;
    Connection con;

    String userName = "root";
    String pass = "";
    String dbMachine = "localhost";
    String dbName = "testBot2";
    String url = "jdbc:mysql://" + dbMachine + "/" + dbName;

    @FXML
    public void newPassSave(ActionEvent event) {

        UserName();

    }

    public void UserName() {
        String userName = "root";
        String pass = "";
        String dbMachine = "localhost";
        String dbName = "testBot2";
        String url = "jdbc:mysql://" + dbMachine + "/" + dbName;

        //user = user.substring(0,1).toUpperCase()+user.substring(1);
        String sql = "select username from user where email = '" + email2 + "'";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, userName, pass);
            st = (Statement) con.createStatement();

            rs = st.executeQuery(sql);

            if (rs.next()) {
                String username = rs.getString("username").toUpperCase();
                String Pass2 = pass2.getText();

                final String senderEmail = "javatesbot1@gmail.com";
                final String senderPassword = "zfgcaacakmiguvox";

                String recipientEmail = email2;

                String subject = "Password Successfully Updated";
                // Email message

                String message = "Dear " + username + ",\n"
                        + "\n"
                        + "I am writing to confirm that your password has been successfully updated for your account.\n"
                        + "\n"
                        + "If you did not make this change, please contact our customer support team immediately at javabot1@gmail.com to report any suspicious activity.\n"
                        + "\n"
                        + "If you have any further questions or concerns, please do not hesitate to reach out to us. Thank you\n"
                        + "\n"
                        + "Best regards,\n"
                        + "javabot1";

                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, senderPassword);
                    }
                });

                if (Pass2.isEmpty()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Password is empty");
                    alert.showAndWait();
                } else {

                    String sql2 = "update user set password = '" + Pass2 + "' where email = '" + email2 + "'";

                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        String password;
                        con = (Connection) DriverManager.getConnection(url, userName, pass);
                        st = (Statement) con.createStatement();

                        st.executeUpdate(sql2);

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("New password is saved");
                        alert.showAndWait();

                        Message msg = new MimeMessage(session);
                        // Set the sender, recipient, subject, and message text
                        msg.setFrom(new InternetAddress(senderEmail));
                        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
                        msg.setSubject(subject);
                        msg.setText(message);
                        // Send the message
                        javax.mail.Transport.send(msg);

                        System.out.println("New Password is Saved");

                        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

                        Stage stage = new Stage();
                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();
                        sendotp.getScene().getWindow().hide();

                    } catch (Exception e) {
                        System.out.println("" + e);
                    }

                }

            } else {
                System.out.println("No results found");
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }

    }

}
