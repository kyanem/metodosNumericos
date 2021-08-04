package com.fernandacoronado;

import java.text.DecimalFormat;

public class TablaEulerMejorado {

    private String x;
    private String y;
    private String valorReal;
    private String errorAbsoluto;
    private String pErrorRelativo;

    public TablaEulerMejorado() {}

    public TablaEulerMejorado(float x, float y, float valorReal, float errorAbsoluto, float pErrorRelativo) {
        setX(format(x));
        setY(format(y));
        setValorReal(format(valorReal));
        setErrorAbsoluto(format(errorAbsoluto));
        setpErrorRelativo(format(pErrorRelativo));
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }

    public void setValorReal(String valorReal) {
        this.valorReal = valorReal;
    }

    public void setErrorAbsoluto(String errorAbsoluto) {
        this.errorAbsoluto = errorAbsoluto;
    }

    public void setpErrorRelativo(String pErrorRelativo) {
        this.pErrorRelativo = pErrorRelativo + "%";
    }

    public String format(float exp) {
        return new DecimalFormat("#.#####").format(exp);
    }

}