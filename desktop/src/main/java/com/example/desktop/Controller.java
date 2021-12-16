package com.example.desktop;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.InvalidPropertiesFormatException;


public class Controller {
    @FXML
    private TableView<User> MainTable;
    @FXML
    private Button DeleteButton;
    @FXML
    private CheckBox adminCheck;

    @FXML
    private TextField minutesField;

    @FXML
    private Button dButton;

    @FXML
    private CheckBox empCheck;

    @FXML
    private Spinner<Integer> minutesId =new Spinner<Integer>();


    @FXML
    private Button pickButton;

    @FXML
    void onButtonClickRoles(ActionEvent event) throws IOException{
        Long id=MainTable.getSelectionModel().getSelectedItem().getId();
        Boolean emp=empCheck.isSelected();
        Boolean adm=adminCheck.isSelected();
        Integer check1=0;
        Integer check2=0;
        if (emp==true)
            check1=1;
        if (adm==true)
            check2=1;
        Json.RolesUser(id,check1,check2);
    }

    @FXML
    void onButtonClickMinutes(ActionEvent event) throws  IOException{
        Long id=MainTable.getSelectionModel().getSelectedItem().getId();
        String text=minutesField.getText();
        Long min=Long.parseLong(text);
        Json.MinutesUser(id,min);
    }
    @FXML
    void onButtonCLickDelete(ActionEvent event) throws IOException {
        Long id=MainTable.getSelectionModel().getSelectedItem().getId();
        Json.DeleteUser(id);
        System.out.println(id);

    }
    @FXML
    void onUpdateButtonClick(ActionEvent event) throws IOException, ParseException {

        final long initialValue=0;
        SpinnerValueFactory valueFactory=
        new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000, (int) initialValue);
        minutesId.setValueFactory(valueFactory);
        minutesField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                minutesField.setText(newValue.replaceAll("[^\\d]", "-"));
            }
        });
        String get= "http://localhost:8080/bpro/admin";
        URL url=Json.createUrl(get);
        String show=Json.parseUrl(url);
        ObservableList<User> users=Json.ShowUsers(show);
        MainTable.getColumns().clear();
        TableColumn<User,Long> idCol=new TableColumn<User,Long>("Id");

        TableColumn<User,String> usernameCol=new TableColumn<User,String>("Имя пользователя");

        TableColumn<User,Long> minutesCol=new TableColumn<User,Long>("Минуты");

        TableColumn<User,Long> accountCol=new TableColumn<User,Long>("Баланс");

        TableColumn<User,String> tariffCol=new TableColumn<User,String>("Название тарифа");
        TableColumn<User,Long> sumCol=new TableColumn<User,Long>("Сумма к оплате");
        TableColumn<User,String> contractCol=new TableColumn<User,String>("Тариф");

        idCol.setCellValueFactory(new PropertyValueFactory<User,Long>("id"));

        usernameCol.setCellValueFactory(new PropertyValueFactory<User,String>("username"));

        minutesCol.setCellValueFactory(new PropertyValueFactory<User,Long>("minutes"));

        accountCol.setCellValueFactory(new PropertyValueFactory<User,Long>("account"));


        tariffCol.setCellValueFactory(new PropertyValueFactory<User,String>("nametariff"));
        sumCol.setCellValueFactory(new PropertyValueFactory<User,Long>("sum"));
        contractCol.getColumns().addAll(tariffCol, sumCol);
        MainTable.getColumns().addAll(idCol,usernameCol,minutesCol,accountCol,contractCol);
        MainTable.setItems(users);

    }
}