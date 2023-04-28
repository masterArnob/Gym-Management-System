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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MyProfController implements Initializable {

    @FXML
    private Button gotosettings;
    @FXML
    private AnchorPane MyProf;
    @FXML
    private Label userx;
    @FXML
    private Label usery;
    @FXML
    private Label userz;

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
   
    displayUserName();
    displayUseremail();
    displayUserPassword();
    
    
    
    }    

    @FXML
    private void gotosettings(ActionEvent event) throws IOException 
    {
       

       
            Parent root = FXMLLoader.load(getClass().getResource("AfterLogin.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            MyProf.getScene().getWindow().hide();
       
    }
    
       Statement st;
    ResultSet rs;
    Connection con;
    
    
     public void displayUserName() {
        String userName = "root";
        String pass = "";
        String dbMachine = "localhost";
        String dbName = "testBot2";
        String url = "jdbc:mysql://" + dbMachine + "/" + dbName;

        String user = data.useremail;
        //user = user.substring(0,1).toUpperCase()+user.substring(1);
        String sql = "select username from user where email = '" + user + "'";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, userName, pass);
            st = (Statement) con.createStatement();

            rs = st.executeQuery(sql);

            if (rs.next()) {
                String username = rs.getString("username").toUpperCase();
                userx.setText(username);
                
            } else {
                System.out.println("No results found");
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }

    }
     
  
     public void displayUseremail() 
     {
        String user = data.useremail;
        usery.setText(user);
    }
     
     
     
     public void displayUserPassword() {
        String userName = "root";
        String pass = "";
        String dbMachine = "localhost";
        String dbName = "testBot2";
        String url = "jdbc:mysql://" + dbMachine + "/" + dbName;

        String user = data.useremail;
        //user = user.substring(0,1).toUpperCase()+user.substring(1);
        String sql = "select password from user where email = '" + user + "'";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, userName, pass);
            st = (Statement) con.createStatement();

            rs = st.executeQuery(sql);

            if (rs.next()) {
                String password = rs.getString("password");
                userz.setText(password);
                
            } else {
                System.out.println("No results found");
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }

    }
     
     
     
     
}
