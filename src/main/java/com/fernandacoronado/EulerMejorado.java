package com.fernandacoronado;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import javax.swing.*;
import java.util.ResourceBundle;

public class EulerMejorado implements Initializable {

    @FXML
    private TextField fdY;

    @FXML
    private TextField fdX0;

    @FXML
    private TextField fdY0;

    @FXML
    private TextField fdXF;

    @FXML
    private TextField fdH;

    @FXML
    private TableView<TablaEulerMejorado> tabla = new TableView<>();

    @FXML
    private TableColumn<TablaEulerMejorado, String> tcX;

    @FXML
    private TableColumn<TablaEulerMejorado, String> tcY;

    @FXML
    private TableColumn<TablaEulerMejorado, String> tcVR;

    @FXML
    private TableColumn<TablaEulerMejorado, String> tcEA;

    @FXML
    private TableColumn<TablaEulerMejorado, String> tcER;

    private String ONLY_NUMBERS = "[+-]?\\d*(\\.\\d+)?";

    private float x0 = 0, y0 = 0, xf = 0, h = 0;
    private String ecuacion = "";

    private CalcularEulerMejorado calcular = null;

    @FXML
    public void calcular() {
        this.ecuacion = fdY.getText();
        asignarValores();
        calcular = new CalcularEulerMejorado(ecuacion, x0, y0, xf, h);
        calcular.calcular();
    }

    private void asignarValores() {
        if (isNumber(fdX0.getText()))
            this.x0 = Float.parseFloat(fdX0.getText());
        else
            JOptionPane.showMessageDialog(null, "Valor inválido para x0", "Ha ocurrido un error...", JOptionPane.ERROR_MESSAGE);
        if (isNumber(fdY0.getText()))
            this.y0 = Float.parseFloat(fdY0.getText());
        else
            JOptionPane.showMessageDialog(null, "Valor inválido para y0", "Ha ocurrido un error...", JOptionPane.ERROR_MESSAGE);
        if (isNumber(fdXF.getText()))
            this.xf = Float.parseFloat(fdXF.getText());
        else
            JOptionPane.showMessageDialog(null, "Valor inválido para xf", "Ha ocurrido un error...", JOptionPane.ERROR_MESSAGE);
        if (isNumber(fdH.getText()))
            this.h = Float.parseFloat(fdH.getText());
        else
            JOptionPane.showMessageDialog(null, "Valor inválido para h", "Ha ocurrido un error...", JOptionPane.ERROR_MESSAGE);
    }

    private boolean isNumber(String ecuacion) {
        return ecuacion.matches(ONLY_NUMBERS);
    }

    @FXML
    public void regresar() throws Exception {
        App.setRoot("Menu");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<TablaEulerMejorado> tablaValues = FXCollections.observableArrayList();
        //calcular = new CalcularEulerMejorado(ecuacion, x0, y0, xf, h);
        //calcular.calcular();

        tablaValues = calcular.getTabla();

        tabla.setItems(tablaValues);

        tcX.setCellValueFactory(new PropertyValueFactory<TablaEulerMejorado, String>("x"));
        tcY.setCellValueFactory(new PropertyValueFactory<TablaEulerMejorado, String>("y"));
        tcVR.setCellValueFactory(new PropertyValueFactory<TablaEulerMejorado, String>("Valor real"));
        tcEA.setCellValueFactory(new PropertyValueFactory<TablaEulerMejorado, String>("Error absoluto"));
        tcER.setCellValueFactory(new PropertyValueFactory<TablaEulerMejorado, String>("% error relativo"));
    }

}