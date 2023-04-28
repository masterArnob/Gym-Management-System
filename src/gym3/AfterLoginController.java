/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym3;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfterLoginController implements Initializable {

    @FXML
    private Button doLogout;
    @FXML
    private Button dashboard;
    @FXML
    private Button coaches;
    @FXML
    private Button members;
    @FXML
    private Button payment;
    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private AnchorPane coach_form;
    @FXML
    private AnchorPane member_form;
    @FXML
    private AnchorPane payment_form;
    @FXML
    private Label DC;
    @FXML
    private Label DT;
    @FXML
    private Label DM;
    @FXML
    private AreaChart<?, ?> dashboardChart;
    @FXML
    private Label name;
    @FXML
    private Button coaches_delete;
    @FXML
    private Button coaches_clear;
    @FXML
    private Button coaches_add;
    @FXML
    private Button coaches_update;
    @FXML
    private TextField coaches_coachID;
    @FXML
    private TextField coaches_name;
    @FXML
    private TextField coaches_phone;
    @FXML
    private ComboBox<String> coaches_gender;
    @FXML
    private ComboBox<String> coaches_status;
    @FXML
    private TextArea coaches_address;
    @FXML
    private Button coachesListDownload;
    @FXML
    private TableView<coachData> coaches_table;
    @FXML
    private TableColumn<coachData, String> coaches_col_coachID;
    @FXML
    private TableColumn<coachData, String> coaches_col_name;
    @FXML
    private TableColumn<coachData, String> coaches_col_address;
    @FXML
    private TableColumn<coachData, String> coaches_col_gender;
    @FXML
    private TableColumn<coachData, String> coaches_col_phone;
    @FXML
    private TableColumn<coachData, String> coaches_col_status;
    @FXML
    private TableView<memberData> members_table;
    @FXML
    private TableColumn<memberData, String> member_col_email;
    @FXML
    private TableColumn<memberData, String> member_col_memberid;
    @FXML
    private TableColumn<memberData, String> member_col_name;
    @FXML
    private TableColumn<memberData, String> member_col_address;
    @FXML
    private TableColumn<memberData, String> member_col_phone;
    @FXML
    private TableColumn<memberData, String> member_col_gender;
    @FXML
    private TableColumn<memberData, String> member_col_schedule;
    @FXML
    private TableColumn<memberData, String> member_col_startDate;
    @FXML
    private TableColumn<memberData, String> member_col_endDate;
    @FXML
    private TableColumn<memberData, String> member_col_price;
    @FXML
    private TableColumn<memberData, String> member_col_status;
    @FXML
    private Button members_update;
    @FXML
    private Button members_delete;
    @FXML
    private Button members_clear;
    @FXML
    private Button members_add;
    @FXML
    private TextField members_email;
    @FXML
    private TextField members_memberid;
    @FXML
    private TextField members_name;
    @FXML
    private TextField members_phone;
    @FXML
    private TextArea members_address;
    @FXML
    private ComboBox<String> members_gender;
    @FXML
    private ComboBox<String> members_status;
    @FXML
    private ComboBox<String> members_schedule;
    @FXML
    private DatePicker members_startDate;
    @FXML
    private DatePicker members_endDate;
    @FXML
    private Button memberListDownload;
    @FXML
    private TableView<memberData> payment_table;
    @FXML
    private TableColumn<memberData, String> payment_col_email;
    @FXML
    private TableColumn<memberData, String> payment_col_memberid;
    @FXML
    private TableColumn<memberData, String> payment_col_name;
    @FXML
    private TableColumn<memberData, String> payment_col_startDate;
    @FXML
    private TableColumn<memberData, String> payment_col_endDate;
    @FXML
    private TableColumn<memberData, String> payment_col_status;
    @FXML
    private Button payFull;
    @FXML
    private ComboBox<String> payment_memberid;
    @FXML
    private ComboBox<String> payment_name;
    @FXML
    private TextField payment_amount;
    @FXML
    private Label payment_total;
    @FXML
    private Label payment_change;
    @FXML
    private Button Settings;
    @FXML
    private AnchorPane settings_form;
    @FXML
    private Label accountName;
    @FXML
    private Button myProf;
    private Button scenter;
    @FXML
    private Button changePass;
    @FXML
    private Button recoveryAccount;
    @FXML
    private Button deleteAccount;
    @FXML
    private Label showTIme;
    @FXML
    private Label m;
    @FXML
    private Label c;
    @FXML
    private Label a;
    @FXML
    private Label d;
    @FXML
    private ImageView coach_image_view;
    @FXML
    private Button InsertImageCoach;
    @FXML
    private AnchorPane left_main;
    @FXML
    private Label file_path;
    @FXML
    private Button InsertImageMember;
    @FXML
    private ImageView member_image_view;
    @FXML
    private AnchorPane left_main2;
    @FXML
    private ImageView payment_image_view;
    @FXML
    private TextField payment_Search;
    @FXML
    private TextField Gym_member_search;
    @FXML
    private TextField Gym_Coaches_Search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        LocalTime time = LocalTime.now();
        int hour = time.getHour();

        if (hour >= 5 && hour < 12) {
            showTIme.setText("Good Morning");
        } else if (hour >= 12 && hour < 18) {
            showTIme.setText("Good Afternoon");
        } else if (hour >= 18 && hour < 24) {
            showTIme.setText("Good Evening");
        }

        DC();
        DM();
        DT();

        dahsboardChart();

        displayUserName();

        coachesGenderList();
        coachesStatusList();
        showCoachData();

        memberGenderList();
        memberStatus();
        memberSchedule();
        showMemberData();

        payment_memberid();
        payment_name();
        showPaymentData();

        dashboard.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        dashboard.setOnMouseEntered(e -> dashboard.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        dashboard.setOnMouseExited(e -> dashboard.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        coaches.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        coaches.setOnMouseEntered(e -> coaches.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        coaches.setOnMouseExited(e -> coaches.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        members.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        members.setOnMouseEntered(e -> members.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        members.setOnMouseExited(e -> members.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        payment.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        payment.setOnMouseEntered(e -> payment.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        payment.setOnMouseExited(e -> payment.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        doLogout.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        doLogout.setOnMouseEntered(e -> doLogout.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        doLogout.setOnMouseExited(e -> doLogout.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        coaches_add.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        coaches_add.setOnMouseEntered(e -> coaches_add.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        coaches_add.setOnMouseExited(e -> coaches_add.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        coaches_delete.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        coaches_delete.setOnMouseEntered(e -> coaches_delete.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        coaches_delete.setOnMouseExited(e -> coaches_delete.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        coaches_update.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        coaches_update.setOnMouseEntered(e -> coaches_update.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        coaches_update.setOnMouseExited(e -> coaches_update.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        coaches_clear.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        coaches_clear.setOnMouseEntered(e -> coaches_clear.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        coaches_clear.setOnMouseExited(e -> coaches_clear.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        members_add.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        members_add.setOnMouseEntered(e -> members_add.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        members_add.setOnMouseExited(e -> members_add.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        members_update.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        members_update.setOnMouseEntered(e -> members_update.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        members_update.setOnMouseExited(e -> members_update.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        members_delete.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        members_delete.setOnMouseEntered(e -> members_delete.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        members_delete.setOnMouseExited(e -> members_delete.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        members_clear.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        members_clear.setOnMouseEntered(e -> members_clear.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        members_clear.setOnMouseExited(e -> members_clear.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        coachesListDownload.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        coachesListDownload.setOnMouseEntered(e -> coachesListDownload.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        coachesListDownload.setOnMouseExited(e -> coachesListDownload.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        Settings.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        Settings.setOnMouseEntered(e -> Settings.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        Settings.setOnMouseExited(e -> Settings.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        payFull.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        payFull.setOnMouseEntered(e -> payFull.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        payFull.setOnMouseExited(e -> payFull.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        memberListDownload.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        memberListDownload.setOnMouseEntered(e -> memberListDownload.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        memberListDownload.setOnMouseExited(e -> memberListDownload.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        myProf.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        myProf.setOnMouseEntered(e -> myProf.setStyle("-fx-background-color:  #1cb0f6; -fx-text-fill: #1c1c1c;"));
        myProf.setOnMouseExited(e -> myProf.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        changePass.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        changePass.setOnMouseEntered(e -> changePass.setStyle("-fx-background-color:  #ffde00; -fx-text-fill: #1c1c1c;"));
        changePass.setOnMouseExited(e -> changePass.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        deleteAccount.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        deleteAccount.setOnMouseEntered(e -> deleteAccount.setStyle("-fx-background-color:  #ff4b4b; -fx-text-fill: #1c1c1c;"));
        deleteAccount.setOnMouseExited(e -> deleteAccount.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

        recoveryAccount.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;");
        recoveryAccount.setOnMouseEntered(e -> recoveryAccount.setStyle("-fx-background-color:  #43c000; -fx-text-fill: #1c1c1c;"));
        recoveryAccount.setOnMouseExited(e -> recoveryAccount.setStyle("-fx-background-color: #1c1c1c; -fx-text-fill: white;"));

    }

    Alert alert;

    @FXML
    private void doLogout(ActionEvent event) throws IOException {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to log out?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get().equals(ButtonType.OK)) {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            doLogout.getScene().getWindow().hide();
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("canclled");
            alert.showAndWait();
        }
    }

    @FXML
    private void switch_form(ActionEvent event) {
        if (event.getSource() == dashboard) {
            dashboard_form.setVisible(true);
            coach_form.setVisible(false);
            member_form.setVisible(false);
            payment_form.setVisible(false);
            settings_form.setVisible(false);

            DC();
            DM();
            DT();
            dahsboardChart();

        } else if (event.getSource() == coaches) {
            dashboard_form.setVisible(false);
            coach_form.setVisible(true);
            member_form.setVisible(false);
            payment_form.setVisible(false);
            settings_form.setVisible(false);

            coachesGenderList();
            coachesStatusList();
            showCoachData();
            CoachSearch();

        } else if (event.getSource() == members) {
            dashboard_form.setVisible(false);
            coach_form.setVisible(false);
            member_form.setVisible(true);
            payment_form.setVisible(false);
            settings_form.setVisible(false);

            memberGenderList();
            memberStatus();
            memberSchedule();
            showMemberData();
            MemberSearch();

        } else if (event.getSource() == payment) {
            dashboard_form.setVisible(false);
            coach_form.setVisible(false);
            member_form.setVisible(false);
            payment_form.setVisible(true);
            settings_form.setVisible(false);

            payment_memberid();
            payment_name();
            showPaymentData();
            PaymentSearch();

        } else if (event.getSource() == Settings) {
            dashboard_form.setVisible(false);
            coach_form.setVisible(false);
            member_form.setVisible(false);
            payment_form.setVisible(false);
            settings_form.setVisible(true);

            String userName = "root";
            String pass = "";
            String dbMachine = "localhost";
            String dbName = "testBot2";
            String url = "jdbc:mysql://" + dbMachine + "/" + dbName;

            String user = data.useremail;

            String sql = "select username from user where email = '" + user + "'";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection(url, userName, pass);
                st = (Statement) con.createStatement();

                rs = st.executeQuery(sql);

                if (rs.next()) {
                    String username = rs.getString("username").toUpperCase();
                    accountName.setText(username);
                } else {
                    System.out.println("No results found");
                }

            } catch (Exception e) {
                System.out.println("" + e);
            }

        }

    }

    public void displayUserName() {
        String userName = "root";
        String pass = "";
        String dbMachine = "localhost";
        String dbName = "testBot2";
        String url = "jdbc:mysql://" + dbMachine + "/" + dbName;

        String user = data.useremail;

        String sql = "select username from user where email = '" + user + "'";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, userName, pass);
            st = (Statement) con.createStatement();

            rs = st.executeQuery(sql);

            if (rs.next()) {
                String username = rs.getString("username").toUpperCase();
                name.setText(username);
            } else {
                System.out.println("No results found");
            }

        } catch (Exception e) {
            System.out.println("" + e);
        }

    }

    public String gender[] = {"Male", "Female", "Others"};

    public void coachesGenderList() {
        List<String> genderList = new ArrayList<>();
        for (String data : gender) {
            genderList.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(genderList);
        coaches_gender.setItems(listData);
    }

    private String status[] = {"Active", "Inactive"};

    public void coachesStatusList() {
        List<String> statusList = new ArrayList<>();
        for (String data : status) {
            statusList.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(statusList);
        coaches_status.setItems(listData);
    }

    Statement st;
    ResultSet rs;
    Connection con;

    String userName = "root";
    String pass = "";
    String dbMachine = "localhost";
    String dbName = "testBot2";
    String url = "jdbc:mysql://" + dbMachine + "/" + dbName;

    public ObservableList<coachData> coachDataList() {

        ObservableList<coachData> listData = FXCollections.observableArrayList();
        String sql = "select * from coach";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, userName, pass);
            st = (Statement) con.createStatement();

            rs = st.executeQuery(sql);

            coachData cd;

            while (rs.next()) {
                cd = new coachData(rs.getString("coachid"), rs.getString("name"), rs.getString("address"), rs.getString("gender"), rs.getString("phone"), rs.getString("status"), rs.getString("picture"));
                listData.add(cd);
            }

        } catch (Exception e) {

        }

        return listData;
    }

    private ObservableList<coachData> coachListData;

    public void showCoachData() {
        coachListData = coachDataList();

        coaches_col_coachID.setCellValueFactory(new PropertyValueFactory<>("coachid"));
        coaches_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        coaches_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        coaches_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        coaches_col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        coaches_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        coaches_table.setItems(coachListData);
    }

    @FXML
    private void coaches_add(ActionEvent event) {

        String coachid = coaches_coachID.getText();
        String name = coaches_name.getText();
        String address = coaches_address.getText();
        String gender = coaches_gender.getSelectionModel().getSelectedItem().toString();
        String phone = coaches_phone.getText();
        String status = coaches_status.getSelectionModel().getSelectedItem().toString();
        String Picture = file_path.getText();

        String sql = "INSERT INTO coach (coachid, name, address, gender, phone, status, picture) VALUES ('" + coachid + "', '" + name + "', '" + address + "', '" + gender + "', '" + phone + "', '" + status + "','" + Picture + "')";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, userName, pass);
            st = (Statement) con.createStatement();

            st.executeUpdate(sql);

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfully Added");
            showCoachData();
            alert.showAndWait();

        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("" + e);
            alert.showAndWait();
        }
    }

    @FXML
    private void coaches_update(ActionEvent event) {
        String coachid = coaches_coachID.getText();
        String name = coaches_name.getText();
        String address = coaches_address.getText();
        String gender = coaches_gender.getSelectionModel().getSelectedItem().toString();
        String phone = coaches_phone.getText();
        String status = coaches_status.getSelectionModel().getSelectedItem().toString();
        String Picture = file_path.getText();

        Picture = Picture.replace("\\", "\\\\");

        String sql = "update coach set name = '" + name + "', address = '" + address + "', gender = '" + gender + "', phone = '" + phone + "', status = '" + status + "', picture = '" + Picture + "' where coachid = '" + coachid + "'";

        try {

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you suer you want to update Coach ID : '" + coaches_coachID.getText() + "'");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection(url, userName, pass);
                st = (Statement) con.createStatement();

                st.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Successfully updated");
                showCoachData();
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("update Cenclled");
                alert.showAndWait();
            }

        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("" + e);
            alert.showAndWait();
        }
    }

    @FXML
    private void coaches_delete(ActionEvent event) {
        String coachid = coaches_coachID.getText();

        String sql = "delete from coach where coachid = '" + coachid + "'";

        try {

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you suer you want to delete Coach ID : '" + coaches_coachID.getText() + "'");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection(url, userName, pass);
                st = (Statement) con.createStatement();

                st.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Successfully Deleted");
                showCoachData();
                alert.showAndWait();
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

    @FXML
    private void coaches_clear(ActionEvent event) {
        coaches_coachID.setText("");
        coaches_name.setText("");
        coaches_address.setText("");
        coaches_gender.getSelectionModel().clearSelection();
        coaches_phone.setText("");
        coaches_status.getSelectionModel().clearSelection();
        file_path.setText("");
        coach_image_view.setImage(null);
    }

    @FXML
    public void coachesSelect() {
        coachData cd = (coachData) coaches_table.getSelectionModel().getSelectedItem();
        int num = coaches_table.getSelectionModel().getSelectedIndex();
        if ((num = -1) < -1) {
            return;
        }
        coaches_coachID.setText(cd.getCoachid());
        coaches_name.setText(cd.getName());
        coaches_address.setText(cd.getAddress());
        coaches_phone.setText(String.valueOf(cd.getPhone()));
        coaches_gender.setValue(cd.getGender());
        coaches_status.setValue(cd.getStatus());

        String picture = "file:" + cd.getPicture();

        Image image = new Image(picture);

        coach_image_view.setImage(image);

        String path = cd.getPicture();

        file_path.setText(path);

    }

    @FXML
    private void docoachesListDownload(ActionEvent event) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, userName, pass);

            JasperDesign jdesign = JRXmlLoader.load("E:\\Java\\Projects\\GYM3\\src\\gym3\\coachList.jrxml");

            String sql = "select * from coach";
            JRDesignQuery updateQuery = new JRDesignQuery();

            updateQuery.setText(sql);

            jdesign.setQuery(updateQuery);

            JasperReport jreport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jreport, null, con);

            JasperViewer.viewReport(jprint, false);

        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

    public String gender2[] = {"Male", "Female", "Others"};

    public void memberGenderList() {
        List<String> genderList = new ArrayList<>();
        for (String data : gender2) {
            genderList.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(genderList);
        members_gender.setItems(listData);
    }

    private String schedule[] = {"8am - 10am", "10am - 12pm", "12pm - 2pm", "2pm - 4pm", "4pm - 6pm", "6pm - 8pm", "8pm - 10pm"};

    public void memberSchedule() {
        List<String> scheduleList = new ArrayList<>();
        for (String data : schedule) {
            scheduleList.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(scheduleList);
        members_schedule.setItems(listData);

    }

    private ObservableList<memberData> memberDataList() {
        ObservableList<memberData> listData = FXCollections.observableArrayList();

        String sql = "select * from member";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, userName, pass);
            st = (Statement) con.createStatement();

            rs = st.executeQuery(sql);

            memberData md;
            while (rs.next()) {
                md = new memberData(rs.getString("email"),
                        rs.getInt("id"),
                        rs.getString("memberid"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getString("schedule"),
                        rs.getString("startDate"),
                        rs.getString("endDate"),
                        rs.getString("price"),
                        rs.getString("status"),
                        rs.getString("picture"));

                listData.add(md);
            }

        } catch (Exception e) {

        }
        return listData;
    }

    private ObservableList<memberData> memberListData;

    public void showMemberData() {
        memberListData = memberDataList();
        member_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        member_col_memberid.setCellValueFactory(new PropertyValueFactory<>("memberid"));
        member_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        member_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        member_col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        member_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        member_col_schedule.setCellValueFactory(new PropertyValueFactory<>("schedule"));

        member_col_startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        member_col_endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        member_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        member_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        members_table.setItems(memberListData);

    }

    @FXML
    public void memberSelect() {
        memberData md = members_table.getSelectionModel().getSelectedItem();
        int num = members_table.getSelectionModel().getSelectedIndex();
        if ((num = -1) < -1) {
            return;
        }
        members_email.setText(md.getEmail());
        members_memberid.setText(md.getMemberid());
        members_name.setText(md.getName());
        members_address.setText(md.getAddress());
        members_phone.setText(String.valueOf(md.getPhone()));

        members_startDate.setValue(LocalDate.parse(String.valueOf(md.getStartDate())));
        members_endDate.setValue(LocalDate.parse(String.valueOf(md.getEndDate())));

        String picture = "file:" + md.getPicture();

        Image image = new Image(picture);

        member_image_view.setImage(image);

        String path = md.getPicture();

        file_path.setText(path);

        members_gender.setValue(md.getGender());
        members_status.setValue(md.getStatus());
        members_schedule.setValue(md.getSchedule());

    }

    private int totalDays;
    private double price;

    private String status2[] = {"Paid", "Due"};

    public void memberStatus() {
        List<String> memberStatus = new ArrayList<>();
        for (String data : status2) {
            memberStatus.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(memberStatus);
        members_status.setItems(listData);
    }

    @FXML
    private void members_add(ActionEvent event) {
        String email = members_email.getText();
        String memberid = members_memberid.getText();
        String name = members_name.getText();
        String address = members_address.getText();
        String phone = members_phone.getText();
        String gender = members_gender.getSelectionModel().getSelectedItem().toString();
        String schedule = members_schedule.getSelectionModel().getSelectedItem().toString();

        String startDate = String.valueOf(members_startDate.getValue());
        String endDate = String.valueOf(members_endDate.getValue());

        totalDays = (int) ChronoUnit.DAYS.between(members_startDate.getValue(), members_endDate.getValue());
        price = (totalDays * 50);

        String status = members_status.getSelectionModel().getSelectedItem().toString();

        String Picture = file_path.getText();

        String sql = "INSERT INTO member (email, memberid, name, address, phone, gender, schedule, startDate, endDate, price, status, picture) VALUES ('" + email + "','" + memberid + "','" + name + "','" + address + "','" + phone + "','" + gender + "','" + schedule + "','" + startDate + "','" + endDate + "','" + price + "','" + status + "','" + Picture + "')";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, userName, pass);
            st = (Statement) con.createStatement();

            st.executeUpdate(sql);

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfully Added");
            showMemberData();
            alert.showAndWait();

        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("" + e);
            alert.showAndWait();
        }
    }

    @FXML
    private void members_update(ActionEvent event) {
        String email = members_email.getText();
        String memberid = members_memberid.getText();
        String name = members_name.getText();
        String address = members_address.getText();
        String phone = members_phone.getText();
        String gender = members_gender.getSelectionModel().getSelectedItem().toString();
        String schedule = members_schedule.getSelectionModel().getSelectedItem().toString();

        String startDate = String.valueOf(members_startDate.getValue());
        String endDate = String.valueOf(members_endDate.getValue());
        String status = members_status.getSelectionModel().getSelectedItem().toString();

        totalDays = (int) ChronoUnit.DAYS.between(members_startDate.getValue(), members_endDate.getValue());
        price = (totalDays * 50);
        double y;
        y = price;

        String Picture = file_path.getText();

        Picture = Picture.replace("\\", "\\\\");

        String sql = "update member set email = '" + email + "', name = '" + name + "', address = '" + address + "', phone = '" + phone + "', gender ='" + gender + "', schedule = '" + schedule + "', startDate = '" + startDate + "', endDate = '" + endDate + "' , price = '" + y + "', status = '" + status + "' ,picture = '" + Picture + "' where memberid = '" + memberid + "'";

        try {

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you suer you want to update member ID : '" + members_memberid.getText() + "'");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection(url, userName, pass);
                st = (Statement) con.createStatement();

                st.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Successfully updated");
                showMemberData();
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("update Cenclled");
                alert.showAndWait();
            }

        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("" + e);
            alert.showAndWait();
        }
    }

    @FXML
    private void members_delete(ActionEvent event) {
        String memberid = members_memberid.getText();

        String sql = "delete from member where memberid = '" + memberid + "'";

        try {

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you suer you want to delete member ID : '" + members_memberid.getText() + "'");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection(url, userName, pass);
                st = (Statement) con.createStatement();

                st.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Successfully deleted");
                showMemberData();
                alert.showAndWait();
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

    @FXML
    private void members_clear(ActionEvent event) {
        members_email.setText("");
        members_memberid.setText("");
        members_name.setText("");
        members_address.setText("");
        members_phone.setText("");

        members_endDate.setValue(null);
        members_startDate.setValue(null);
        members_status.getSelectionModel().clearSelection();
        members_gender.getSelectionModel().clearSelection();
        members_schedule.getSelectionModel().clearSelection();
        member_image_view.setImage(null);
        file_path.setText("");
    }

    @FXML
    private void domemberListDownload(ActionEvent event) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, userName, pass);

            JasperDesign jdesign = JRXmlLoader.load("E:\\Java\\Projects\\GYM3\\src\\gym3\\memberList.jrxml");

            String sql = "select * from member";
            JRDesignQuery updateQuery = new JRDesignQuery();

            updateQuery.setText(sql);

            jdesign.setQuery(updateQuery);

            JasperReport jreport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jreport, null, con);

            JasperViewer.viewReport(jprint, false);

        } catch (Exception e) {
            System.out.println("" + e);
        }

    }

    public ObservableList<memberData> paymentListData() {

        ObservableList<memberData> listData = FXCollections.observableArrayList();
        String sql = "select * from member";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, userName, pass);
            st = (Statement) con.createStatement();

            rs = st.executeQuery(sql);

            memberData md;
            while (rs.next()) {
                md = new memberData(rs.getString("email"),
                        rs.getInt("id"),
                        rs.getString("memberid"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getString("schedule"),
                        rs.getString("startDate"),
                        rs.getString("endDate"),
                        rs.getString("price"),
                        rs.getString("status"),
                        rs.getString("picture"));

                listData.add(md);

            }

        } catch (Exception e) {

        }
        return listData;
    }

    private ObservableList<memberData> paymentDataList;

    public void showPaymentData() {
        paymentDataList = paymentListData();

        payment_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        payment_col_memberid.setCellValueFactory(new PropertyValueFactory<>("memberid"));
        payment_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        payment_col_startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        payment_col_endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        payment_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        payment_table.setItems(paymentDataList);
    }

    private double totalPrice;

    public void displayTotal() {
        String sql = "select price from member where name  = '" + payment_name.getSelectionModel().getSelectedItem() + "' and memberid = '" + payment_memberid.getSelectionModel().getSelectedItem() + "'";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, userName, pass);
            st = (Statement) con.createStatement();

            rs = st.executeQuery(sql);
            if (rs.next()) {
                totalPrice = rs.getDouble("price");
            }

        } catch (Exception e) {

        }

    }

    public void paymentDisplayTotal() {
        displayTotal();
        payment_total.setText("" + totalPrice);
    }

    private double amount, change;

    public void paymentClear() {
        payment_name.getSelectionModel().clearSelection();
        payment_memberid.getSelectionModel().clearSelection();;
        payment_total.setText("$0.0");
        payment_amount.setText("$0.0");
        payment_change.setText("$0.0");
    }

    @FXML
    public void payment_memberid() {

        String sql = "select memberid from member where status = 'Due'";

        try {
            ObservableList listData = FXCollections.observableArrayList();
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, userName, pass);
            st = (Statement) con.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                listData.add(rs.getString("memberid"));

            }

            payment_name();
            payment_memberid.setItems(listData);

           

        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

    @FXML
    public void payment_name() {
        String sql = "select name from member where memberid = '" + payment_memberid.getSelectionModel().getSelectedItem() + "'";

        try {
            ObservableList listData = FXCollections.observableArrayList();
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, userName, pass);
            st = (Statement) con.createStatement();

            rs = st.executeQuery(sql);

            if (rs.next()) {
                listData.add(rs.getString("name"));
            }
            paymentDisplayTotal();
            payment_name.setItems(listData);

            

        } catch (Exception e) {
            System.out.println("" + e);
        }

    }

    @FXML
    public void payment_amount() {

        paymentDisplayTotal();
        amount = Double.parseDouble(payment_amount.getText());

        if (amount >= totalPrice) {
            change = amount - totalPrice;
            payment_change.setText("" + change);
        }

    }

    @FXML
    private void payFull(ActionEvent event) {
        if (payment_memberid.getSelectionModel().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Select member");
            alert.showAndWait();
        } else {
            String memberid = (String) payment_memberid.getSelectionModel().getSelectedItem();
            String sql = "update member set status = 'Paid' where memberid = '" + memberid + "'";
            String name = (String) payment_name.getSelectionModel().getSelectedItem();

            try {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Are you sure you want to payment member ID : '" + memberid + "'");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = (Connection) DriverManager.getConnection(url, userName, pass);
                    st = (Statement) con.createStatement();

                    st.executeUpdate(sql);

                    String gmail = null;
                    String getEmailSql = "select email from member where memberid = '" + memberid + "'";
                    ResultSet rs = st.executeQuery(getEmailSql);
                    if (rs.next()) {
                        gmail = rs.getString("email");
                    }

                    String subject = "Payment received";
                    String body = "Dear " + name + ",\n\nYour payment has been received. Thank you for your membership.\n\nRegards,\nThe Fitness Club";
                    sendEmail(gmail, subject, body);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Updated");
                    alert.showAndWait();
                    showPaymentData();

                    paymentClear();

                }
            } catch (Exception e) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("" + e);
                alert.showAndWait();
            }

            String sql2 = "select * from member where memberid = '" + memberid + "'";

            try {

                Class.forName("com.mysql.jdbc.Driver");
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection(url, userName, pass);

                JasperDesign jdesign = JRXmlLoader.load("E:\\Java\\Projects\\GYM3\\src\\gym3\\payment.jrxml");

                JRDesignQuery updateQuery = new JRDesignQuery();

                updateQuery.setText(sql2);

                jdesign.setQuery(updateQuery);

                JasperReport jreport = JasperCompileManager.compileReport(jdesign);
                JasperPrint jprint = JasperFillManager.fillReport(jreport, null, con);

                JasperViewer.viewReport(jprint, false);

            } catch (Exception e) {
                System.out.println("" + e);
            }

        }

    }

    private void sendEmail(String name, String subject, String body) {
        String from = "javatesbot1@gmail.com";
        String password = "zfgcaacakmiguvox";
        String host = "smtp.gmail.com";
        String port = "587";

        String attachmentPath = "E:\\Java\\Projects\\GYM3\\src\\gym3\\payment.jrxml";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(name));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Message sent successfully.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void DC() {
        int tc = 0;
        String sql = "select count(*) from coach where status = 'Active'";
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, userName, pass);
            st = (Statement) con.createStatement();

            rs = st.executeQuery(sql);
            if (rs.next()) {
                tc = rs.getInt("count(*)");
            }
            DC.setText("" + tc);

        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("" + e);
            alert.showAndWait();
        }

    }

    public void DM() {
        String sql = "select count(*) from member where status = 'Paid'";

        int tm = 0;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, userName, pass);
            st = (Statement) con.createStatement();

            rs = st.executeQuery(sql);

            if (rs.next()) {
                tm = rs.getInt("count(*)");
            }

            DM.setText("" + tm);

        } catch (Exception e) {

        }

    }

    public void DT() {
        String sql = "select sum(price) from member where status = 'Paid'";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, userName, pass);
            st = (Statement) con.createStatement();

            rs = st.executeQuery(sql);

            double dt = 0;

            if (rs.next()) {
                dt = rs.getDouble("sum(price)");
            }
            DT.setText("$ " + dt);
        } catch (Exception e) {

        }
    }

    public void dahsboardChart() {

        dashboardChart.getData().clear();
        String sql = "select startDate, sum(price) from member where status = 'Paid' group by startDate order by timestamp(startDate) asc limit 10";

        XYChart.Series chart = new XYChart.Series();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, userName, pass);
            st = (Statement) con.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                chart.getData().add(new XYChart.Data(rs.getString(1), rs.getDouble(2)));

            }

            dashboardChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void myProf(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("MyProf.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setTitle("My Profile");
        stage.setScene(scene);
        stage.show();
        Settings.getScene().getWindow().hide();

    }

    @FXML
    private void changePass(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("changePassword.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setTitle("Change Password");
        stage.setScene(scene);
        stage.show();
        Settings.getScene().getWindow().hide();
    }

    @FXML
    private void recoveryAccount(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("recoveryAccount.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setTitle("Recovery Account");
        stage.setScene(scene);
        stage.show();
        Settings.getScene().getWindow().hide();
    }

    @FXML
    private void deleteAccount(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("deleteAccount.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setTitle("Delete Account");
        stage.setScene(scene);
        stage.show();
        Settings.getScene().getWindow().hide();
    }

    @FXML
    private void InsertImageCoach(ActionEvent event) {
        FileChooser open = new FileChooser();

        Stage stage = (Stage) left_main.getScene().getWindow();

        File file = open.showOpenDialog(stage);

        if (file != null) {

            String path = file.getAbsolutePath();

            path = path.replace("\\", "\\\\");

            file_path.setText(path);

            Image image = new Image(file.toURI().toString());

            coach_image_view.setImage(image);

        } else {

            System.out.println("NO DATA EXIST!");

        }
    }

    @FXML
    private void InsertImageMember(ActionEvent event) {
        FileChooser open = new FileChooser();

        Stage stage = (Stage) left_main2.getScene().getWindow();

        File file = open.showOpenDialog(stage);

        if (file != null) {

            String path = file.getAbsolutePath();

            path = path.replace("\\", "\\\\");

            file_path.setText(path);

            Image image = new Image(file.toURI().toString());

            member_image_view.setImage(image);

        } else {

            System.out.println("NO DATA EXIST!");

        }
    }

    @FXML
    public void paymentSelect() {
        memberData md = payment_table.getSelectionModel().getSelectedItem();
        int num = payment_table.getSelectionModel().getSelectedIndex();
        if ((num = -1) < -1) {
            return;
        }

        String picture = "file:" + md.getPicture();

        Image image = new Image(picture);

        payment_image_view.setImage(image);
        payment_memberid.setValue(md.getMemberid());
        payment_name.setValue(md.getName());
        payment_total.setText(String.valueOf(md.getPrice()));
        
        
        
        

    }

    

    @FXML
    public void PaymentSearch() {
        FilteredList<memberData> filter = new FilteredList<>(memberListData, e -> true);
        payment_Search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateMemberData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateMemberData.getEmail().toString().contains(searchKey)) {
                    return true;
                } else if (predicateMemberData.getId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateMemberData.getName().toString().contains(searchKey)) {
                    return true;
                } else if (predicateMemberData.getStartDate().toString().contains(searchKey)) {
                    return true;
                } else if (predicateMemberData.getEndDate().toString().contains(searchKey)) {
                    return true;
                } else if (predicateMemberData.getStatus().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<memberData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(payment_table.comparatorProperty());

        payment_table.setItems(sortList);
    }

    @FXML
    public void MemberSearch() {
        FilteredList<memberData> filter = new FilteredList<>(memberListData, e -> true);
        Gym_member_search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateMemberData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateMemberData.getEmail().toString().contains(searchKey)) {
                    return true;
                } else if (predicateMemberData.getId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateMemberData.getName().toString().contains(searchKey)) {
                    return true;
                } else if (predicateMemberData.getAddress().toString().contains(searchKey)) {
                    return true;
                } else if (predicateMemberData.getPhone().toString().contains(searchKey)) {
                    return true;
                } else if (predicateMemberData.getGender().toString().contains(searchKey)) {
                    return true;
                } else if (predicateMemberData.getSchedule().toString().contains(searchKey)) {
                    return true;
                } //ssssss
                else if (predicateMemberData.getStartDate().toString().contains(searchKey)) {
                    return true;
                } else if (predicateMemberData.getEndDate().toString().contains(searchKey)) {
                    return true;
                } else if (predicateMemberData.getStatus().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<memberData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(members_table.comparatorProperty());

        members_table.setItems(sortList);
    }

    @FXML
    public void CoachSearch() {
        FilteredList<coachData> filter = new FilteredList<>(coachListData, e -> true);
        Gym_Coaches_Search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateCoachData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateCoachData.getCoachid().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCoachData.getName().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCoachData.getAddress().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCoachData.getGender().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCoachData.getPhone().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCoachData.getStatus().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<coachData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(coaches_table.comparatorProperty());

        coaches_table.setItems(sortList);
    }

}



