/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class RecoveryAccountController implements Initializable {

    @FXML
    private AnchorPane recoveryAccount;
    @FXML
    private Button gotosettings;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
                  
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

            stage.setTitle("Gym Management System");
            stage.setScene(scene);
            stage.show();
            recoveryAccount.getScene().getWindow().hide();
    }

    
}
