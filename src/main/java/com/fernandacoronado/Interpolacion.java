package com.fernandacoronado;

import javafx.fxml.FXML;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Interpolacion implements Initializable {

    @FXML
    public void regresar() throws Exception {
        App.setRoot("Menu");
    }


    @FXML
    private TextField txtX;

    @FXML
    private TextField txtY;


    @FXML
    private Button btnCalcular;

    private ArrayList<Double> equis = new ArrayList<>();

    private ArrayList<Double> yes = new ArrayList<>();

    @FXML
    private Button btnAgregaPunto;


    @FXML
    private ListView<String> listView;

    private ObservableList<String> puntos;

    @FXML
    void agregarPunto(){

        puntos.add("("+txtX.getText()+","+txtY.getText()+")");

        double x = Double.valueOf(txtX.getText());

        double y = Double.valueOf(txtY.getText());

        agregarValores(x, y);

        listView.setItems(puntos);

        txtX.clear();
        txtY.clear();

    }

    public void agregarValores(double xs, double ys){

        equis.add(xs);
        yes.add(ys);



    }


    @FXML
    private TableView tableViewResultados;


    private ArrayList<ResultadosInterpolacion> listaValores = new ArrayList();



    @FXML
    public void MetodoInterLagrange(){



        /*for(int i=0; i<equis.size(); i++){

            System.out.println("X"+1+"   "+equis.get(i));
            System.out.println("y"+1+"   "+yes.get(i));

        }*/



    }



    private double getLee() {
        return lee();
    }

    //para leer desde teclado
    public double lee(){
        double num;
        try{
            InputStreamReader isr = new InputStreamReader (System.in);
            BufferedReader br = new BufferedReader(isr);
            String sdato;
            sdato = br.readLine();
            num = Double.parseDouble(sdato);
        }
        catch(IOException ioe){
            num=0.0;
        }
        return num;
    }

    //para  leer un entero
    public int leeint(){
        int num;
        try{
            InputStreamReader isr = new InputStreamReader (System.in);
            BufferedReader br = new BufferedReader(isr);
            String sdato;
            sdato = br.readLine();
            num = Integer.parseInt(sdato);
        }
        catch(IOException ioe){
            num=0;
        }
        return num;
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        puntos = FXCollections.observableArrayList();
        listView.setItems(puntos);


        TableColumn<String, ResultadosInterpolacion> columnaX = new TableColumn<>("X");
        TableColumn<String, ResultadosInterpolacion> columnaY = new TableColumn<>("Y");
        TableColumn<String, ResultadosInterpolacion> columnaFDX = new TableColumn<>("F(x)");

        columnaX.setMinWidth(61);
        columnaY.setMinWidth(61);
        columnaFDX.setMinWidth(150);

        columnaX.setCellValueFactory(new PropertyValueFactory<>("x"));
        columnaY.setCellValueFactory(new PropertyValueFactory<>("y"));
        columnaFDX.setCellValueFactory(new PropertyValueFactory<>("f(x)"));


        tableViewResultados.getColumns().addAll(columnaX, columnaY, columnaFDX);




    }
}
