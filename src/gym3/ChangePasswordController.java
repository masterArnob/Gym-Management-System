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
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class ChangePasswordController implements Initializable {

    @FXML
    private AnchorPane changePassword;
    @FXML
    private Button goToSettings;
    @FXML
    private TextField oldPass;
    @FXML
    private TextField newPass;
    @FXML
    private Button PassChange;
    @FXML
    private Label status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        goToSettings.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        goToSettings.setOnMouseEntered(e -> goToSettings.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        goToSettings.setOnMouseExited(e -> goToSettings.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        PassChange.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        PassChange.setOnMouseEntered(e -> PassChange.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        PassChange.setOnMouseExited(e -> PassChange.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));
        // TODO
    }

    @FXML
    private void goToSettings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AfterLogin.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setTitle("Gym Management Sytsem");
        stage.setScene(scene);
        stage.show();
        changePassword.getScene().getWindow().hide();
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
    private void PassChange(ActionEvent event) {
        String OldPass = oldPass.getText();
        String NewPass = newPass.getText();

        if (OldPass.isEmpty() || NewPass.isEmpty()) {
            status.setText("*Information required");
        } else {
            if (OldPass.equals(NewPass)) {
                status.setText("*Old password and new password can't be same");
            } else {

                String sql = "select password from user where email = '" + data.useremail + "'";

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = (Connection) DriverManager.getConnection(url, userName, pass);
                    st = (Statement) con.createStatement();

                    rs = st.executeQuery(sql);

                    if (rs.next()) {
                        String PassOl = rs.getString("password").toUpperCase();

                        if (PassOl.equals(OldPass)) {

                            String sql2 = "update user set password = '" + NewPass + "' where email = '" + data.useremail + "'";

                            try {

                                Class.forName("com.mysql.jdbc.Driver");
                                con = (Connection) DriverManager.getConnection(url, userName, pass);
                                st = (Statement) con.createStatement();

                                st.executeUpdate(sql2);

                                final String senderEmail = "javatesbot1@gmail.com";
                                final String senderPassword = "zfgcaacakmiguvox";

                                String recipientEmail = data.useremail;

                                String subject = "Password Successfully Updated";
                                // Email message

                                String user = data.useremail;
                                //user = user.substring(0,1).toUpperCase()+user.substring(1);
                                String sql3 = "select username from user where email = '" + user + "'";

                                try {
                                    Class.forName("com.mysql.jdbc.Driver");
                                    con = (Connection) DriverManager.getConnection(url, userName, pass);
                                    st = (Statement) con.createStatement();

                                    rs = st.executeQuery(sql3);

                                    if (rs.next()) {
                                        String username = rs.getString("username").toUpperCase();

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

                                        Message msg = new MimeMessage(session);
                                        // Set the sender, recipient, subject, and message text
                                        msg.setFrom(new InternetAddress(senderEmail));
                                        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
                                        msg.setSubject(subject);
                                        msg.setText(message);
                                        // Send the message
                                        javax.mail.Transport.send(msg);

                                        Alert alert;
                                        alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setContentText("Successfully updated");
                                        alert.showAndWait();

                                        Parent root = FXMLLoader.load(getClass().getResource("AfterLogin.fxml"));

                                        Stage stage = new Stage();
                                        Scene scene = new Scene(root);

                                        stage.setTitle("Gym Management Sytsem");
                                        stage.setScene(scene);
                                        stage.show();
                                        changePassword.getScene().getWindow().hide();
                                        status.setText("");

                                    } else {
                                        System.out.println("No results found");
                                    }

                                } catch (Exception e) {
                                    System.out.println("" + e);
                                }

                            } catch (Exception e) {
                                System.out.println("" + e);
                            }

                        }
                        else{
                            status.setText("Old Password does not match");
                        }
                        //name.setText(username);
                    } 
                    else {
                        status.setText("Old Password does not match");
                    }

                } catch (Exception e) {
                    System.out.println("" + e);
                }

            }

        }

    }

}
