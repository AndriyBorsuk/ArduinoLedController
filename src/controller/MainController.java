package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;


import java.io.IOException;

public class MainController {

    @FXML
    private CheckBox chboxRedLed;

    @FXML
    private CheckBox chboxBlueLed;

    @FXML
    private CheckBox chboxGreenLed;

    @FXML
    private CheckBox chboxYellowLed;

    @FXML
    private CheckBox chboxOrangeLed;

    @FXML
    private CheckBox chboxWhiteLed;

    @FXML
    private ComboBox<String> cmbChoicePort;

    @FXML
    private Label txtPortStatus;

    @FXML
    public void initialize(){
        cmbChoicePort.setItems(showPorts());
    }

    private static SerialPort serialPort;
    private static char buf;

    public void closeMainWindow(ActionEvent actionEvent){
        showError(actionEvent);
    }

    private void showError(ActionEvent actionEvent){
        if (serialPort != null && serialPort.isOpened() ) {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../fxml/error.fxml"));
                stage.setTitle("Error");
                stage.setMinWidth(400);
                stage.setMinHeight(200);
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }


    public void openPort() throws SerialPortException {
        serialPort = new SerialPort(cmbChoicePort.getValue());
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);

            txtPortStatus.setText("Open");

        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    public void closePort() throws SerialPortException {
        if (serialPort != null && serialPort.isOpened()){
            try{
                serialPort.closePort();

                txtPortStatus.setText("Close");
            }
            catch (SerialPortException ex){
                System.out.println(ex);
            }
        }
    }

    public ObservableList<String> showPorts() {
        String[] portNames = SerialPortList.getPortNames();

        ObservableList<String> portList = FXCollections.observableArrayList();

        for (int i = 0; i < portNames.length; i++){
            portList.add(portNames[i]);
        }
        return portList;
    }

    public void ledOn() throws SerialPortException {
        if (serialPort != null && serialPort.isOpened()){
            if(chboxRedLed.isSelected()) {
                buf = 'R';
                serialPort.writeByte((byte) buf);
            }
            if (chboxBlueLed.isSelected()){
                buf = 'B';
                serialPort.writeByte((byte) buf);
            }
            if (chboxGreenLed.isSelected()){
                buf = 'G';
                serialPort.writeByte((byte) buf);
            }
            if (chboxOrangeLed.isSelected()){
                buf = 'O';
                serialPort.writeByte((byte) buf);
            }
            if (chboxYellowLed.isSelected()){
                buf = 'Y';
                serialPort.writeByte((byte) buf);
            }
            if (chboxWhiteLed.isSelected()){
                buf = 'W';
                serialPort.writeByte((byte) buf);
            }
        }
    }

    public void ledOf() throws SerialPortException {
        if (serialPort != null && serialPort.isOpened()){
            if (chboxRedLed.isSelected()) {
                buf = 'r';
                serialPort.writeByte((byte) buf);
            }
            if (chboxBlueLed.isSelected()){
                buf = 'b';
                serialPort.writeByte((byte) buf);
            }
            if (chboxGreenLed.isSelected()){
                buf = 'g';
                serialPort.writeByte((byte) buf);
            }
            if (chboxOrangeLed.isSelected()){
                buf = 'o';
                serialPort.writeByte((byte) buf);
            }
            if (chboxYellowLed.isSelected()){
                buf = 'y';
                serialPort.writeByte((byte) buf);
            }
            if (chboxWhiteLed.isSelected()){
                buf = 'w';
                serialPort.writeByte((byte) buf);
            }
        }
    }
}
