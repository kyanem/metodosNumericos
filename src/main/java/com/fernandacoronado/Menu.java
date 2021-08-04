package com.fernandacoronado;

import javafx.fxml.FXML;

public class Menu {

    @FXML
    public void abrirEulerMejorado() throws Exception {
        App.setRoot("EulerMejorado");
    }

    @FXML
    public void abrirInterpolacion() throws Exception {
        App.setRoot("Interpolacion");
    }

}