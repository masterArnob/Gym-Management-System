/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym3;

import java.io.IOException;
import javax.mail.PasswordAuthentication;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class SignupController implements Initializable {

    @FXML
    private Button goBack;
    @FXML
    private Button gotologin;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private Label status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        goBack.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        goBack.setOnMouseEntered(e -> goBack.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        goBack.setOnMouseExited(e -> goBack.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        gotologin.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        gotologin.setOnMouseEntered(e -> gotologin.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        gotologin.setOnMouseExited(e -> gotologin.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        gotologin.getScene().getWindow().hide();
    }

    @FXML
    private void gotologin(ActionEvent event) {
        String Username = username.getText();
        String Email = email.getText();
        String Password = password.getText();

        String emailCheck = "^[A-Z0-9._%+-]+@[A-Z0-9._]+\\.[A-Z]{2,6}$";

        Pattern emailPattern = Pattern.compile(emailCheck, Pattern.CASE_INSENSITIVE);

        Matcher matcher = emailPattern.matcher(Email);

        Scanner input = new Scanner(System.in);
        // Sender's email address
        final String senderEmail = "javatesbot1@gmail.com";
        final String senderPassword = "zfgcaacakmiguvox";

        String recipientEmail = email.getText();

        String subject = "Welcome to Arnob's Project - TestBot2";

        String message = "Dear " + username.getText() + ",\n\nI would like to extend a warm welcome to Arnob's project on behalf of Testbot2. My name is TestBot2, and I am here to assist you in any way I can.\n\nThank you\n\nBest regards,\nTestBot2";

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

        Statement st;
        ResultSet rs;
        Connection con;

        String userName = "root";
        String pass = "";
        String dbMachine = "localhost";
        String dbName = "testBot2";
        String url = "jdbc:mysql://" + dbMachine + "/" + dbName;

        String sql = "insert into user values('" + Username + "','" + Email + "','" + Password + "')";

        Alert alert;

        try {

            if (Email.isEmpty() || Username.isEmpty() || Password.isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Pleasee Fill all the info");
                alert.showAndWait();
            } else if (emailPattern.matcher(Email).matches()) {

                String sql2 = "select * from user where email = '" + Email + "'";

                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection(url, userName, pass);
                    st = con.createStatement();

                    rs = st.executeQuery(sql2);
                    if (rs.next()) {
                        status.setText("Email already exist");
                    } else {
                        status.setText("");
                        Class.forName("com.mysql.jdbc.Driver");
                        con = DriverManager.getConnection(url, userName, pass);
                        st = con.createStatement();

                        st.executeUpdate(sql);

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Successfully Sign up");
                        alert.showAndWait();

                        Message msg = new MimeMessage(session);
                        // Set the sender, recipient, subject, and message text
                        msg.setFrom(new InternetAddress(senderEmail));
                        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
                        msg.setSubject(subject);
                        msg.setText(message);
                        // Send the message
                        javax.mail.Transport.send(msg);
                        System.out.println("Email sent successfully.");

                        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

                        Stage stage = new Stage();
                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();
                        gotologin.getScene().getWindow().hide();
                    }

                } catch (Exception e) {
                    System.out.println("" + e);
                }

            } else {
                status.setText("*Email is not valid");
            }

        } catch (Exception e) {

        }

    }

}
