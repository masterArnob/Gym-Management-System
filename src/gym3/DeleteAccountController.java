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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DeleteAccountController implements Initializable {

    @FXML
    private StackPane deleteAccount;
    @FXML
    private Button gotosettings;
    @FXML
    private Button DeleteProf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        DeleteProf.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        DeleteProf.setOnMouseEntered(e -> DeleteProf.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        DeleteProf.setOnMouseExited(e -> DeleteProf.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));
        
        
        
         gotosettings.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        gotosettings.setOnMouseEntered(e -> gotosettings.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        gotosettings.setOnMouseExited(e -> gotosettings.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));
        
        // TODO
    }    

    @FXML
    private void gotosettings(ActionEvent event) throws IOException
    {
         Parent root = FXMLLoader.load(getClass().getResource("AfterLogin.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setTitle("Recovery Account");
            stage.setScene(scene);
            stage.show();
            deleteAccount.getScene().getWindow().hide();
    }

    
    
    Statement st;
    ResultSet rs;
    Connection con;

    String userName = "root";
    String pass = "";
    String dbMachine = "localhost";
    String dbName = "testBot2";
    String url = "jdbc:mysql://" + dbMachine + "/" + dbName;
    
    
    
    Alert alert;
    
    @FXML
    private void DeleteProf(ActionEvent event)
    {
          String user = data.useremail;
        

        String sql = "delete from user where email = '" + user + "'";

        try {

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you suer you want to delete user: '" + user + "'");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection(url, userName, pass);
                st = (Statement) con.createStatement();

                st.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Successfully Deleted");
                
                alert.showAndWait();
                  
                
           Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            deleteAccount.getScene().getWindow().hide();
                
                
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Delete Cenclled");
                alert.showAndWait();
            }

        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("" + e);
            alert.showAndWait();
        }

    }
    
}
