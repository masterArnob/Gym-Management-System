/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym3;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LoginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button doLogin;

    /**
     * Initializes the controller class.
     */
    Statement st;
    ResultSet rs;
    Connection con;

    String userName = "root";
    String pass = "";
    String dbMachine = "localhost";
    String dbName = "testBot2";
    String url = "jdbc:mysql://" + dbMachine + "/" + dbName;
    @FXML
    private Label status;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        doLogin.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        doLogin.setOnMouseEntered(e -> doLogin.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));
        doLogin.setOnMouseExited(e -> doLogin.setStyle("-fx-background-color: black; -fx-text-fill: white;"));
        
    }

    @FXML
    private void doLogin(ActionEvent event) {

        Alert alert;

        try {

            InetAddress address = InetAddress.getByName("8.8.8.8");
            boolean reachable = address.isReachable(5000);
            if (reachable) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Internet Connected Successfully. Please start XAMPP");
                alert.showAndWait();

                String Email = email.getText();
                String Password = password.getText();

                String emailCheck = "^[A-Z0-9._%+-]+@[A-Z0-9._]+\\.[A-Z]{2,6}$";

                Pattern emailPattern = Pattern.compile(emailCheck, Pattern.CASE_INSENSITIVE);

                Matcher matcher = emailPattern.matcher(Email);

                String sql = "select * from user where email = '" + Email + "' and password = '" + Password + "'";

                try {

                    if (Email.isEmpty() || Password.isEmpty()) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Please Fill the Information");
                        alert.showAndWait();
                    } else if (emailPattern.matcher(Email).matches()) {

                        status.setText("");

                        Class.forName("com.mysql.jdbc.Driver");
                        con = (Connection) DriverManager.getConnection(url, userName, pass);
                        st = (Statement) con.createStatement();

                        rs = st.executeQuery(sql);

                        if (rs.next()) {

                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Welcome");
                            alert.showAndWait();
                            data.useremail = email.getText();
                            
                            Parent root = FXMLLoader.load(getClass().getResource("AfterLogin.fxml"));

                            Stage stage = new Stage();
                            Scene scene = new Scene(root);

                            stage.setTitle("");
                            stage.setScene(scene);

                            stage.show();

                            doLogin.getScene().getWindow().hide();

                        } else {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Incorrect Information");
                            alert.showAndWait();
                        }

                    } else {
                        status.setText("*Email is not valid");
                    }

                } catch (Exception e) {

                    System.out.println("" + e);
                }

            } else {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Internet Connection Failed");
                alert.showAndWait();
            }
        } catch (SocketTimeoutException e) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Internet Connection Failed");
            alert.showAndWait();
        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please start the XAMPP for database");
            alert.showAndWait();
        }

    }

    @FXML
    private void gotosignup(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        doLogin.getScene().getWindow().hide();
    }

    @FXML
    private void gotoforgetpassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("forgetPassword.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        doLogin.getScene().getWindow().hide();
    }

}
