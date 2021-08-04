package com.fernandacoronado;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

public class CalcularEulerMejorado {

    private String ecuacion = "x";
    private float x0 = 0, y0 = 0, xf = 0, h = 0;
    private float x = 0, y = 0, u = 0;
    private float vReal = 0, errorAbs = 0, errorRel = 0;

    private ObservableList<TablaEulerMejorado> tabla = null;

    public CalcularEulerMejorado() {}

    public CalcularEulerMejorado(String ecuacion, float x0, float y0, float xf, float h) {
        setEcuacion(ecuacion);
        setX0(x0);
        setY0(y0);
        setXf(xf);
        setH(h);
    }

    public void calcular() {
        tabla = FXCollections.observableArrayList();
        tabla.add(new TablaEulerMejorado(x0, y0, y0, 0, 0));
        x = x0 + h;
        u = y0 + h * f(x0, y0);
        y = y0 + h * ((f(x0, y0) + f(x, u)) / 2);
        vReal = valorReal(x);
        errorAbs = errorAbsoluto(vReal, y);
        errorRel = pErrorRelativo(errorAbs, vReal);
        tabla.add(new TablaEulerMejorado(x, y, vReal, errorAbs, errorRel));
        for (; x <= xf;) {
            u = y + h * f(x, y);
            y = y + h * ((f(x, y) + f(x+h, u)) / 2);
            x += h;
            vReal = valorReal(x);
            errorAbs = errorAbsoluto(vReal, y);
            errorRel = pErrorRelativo(errorAbs, vReal);
            tabla.add(new TablaEulerMejorado(x, y, vReal, errorAbs, errorRel));
        }
        System.out.println("Listo!");
    }

    public ObservableList<TablaEulerMejorado> getTabla() {
        return this.tabla;
    }

    public float valorReal(float x) {
        return (float) Math.exp(-0.2 + (0.2 * Math.pow(x, 2)));
    }

    public float errorAbsoluto(float vReal, float y) {
        return vReal - y;
    }

    public float pErrorRelativo(float eAbsoluto, float vReal) {
        return (eAbsoluto / vReal) * 100;
    }

    /*public float f(String ecuacion, float x, float y) {
        System.out.println(exp);
        exp = new ExpressionBuilder(ecuacion).variables("x", "y").build().setVariable("x", x).setVariable("y", y);
        return (float) exp.evaluate();
    }*/

    public float f(float x, float y) {
        Argument varX = new Argument("x");
        Argument varY = new Argument("y");
        varX.setArgumentValue(x);
        varY.setArgumentValue(y);
        Expression expression = new Expression(this.ecuacion, varX, varY);
        return (float) expression.calculate();
    }

    public void setEcuacion(String ecuacion) {
        this.ecuacion = ecuacion;
    }

    public void setX0(float x0) {
        this.x0 = x0;
    }

    public void setY0(float y0) {
        this.y0 = y0;
    }

    public void setXf(float xf) {
        this.xf = xf;
    }

    public void setH(float h) {
        this.h = h;
    }

}