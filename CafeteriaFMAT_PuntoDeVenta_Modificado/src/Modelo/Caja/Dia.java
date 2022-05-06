/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Caja;

import java.util.Date;

/**
 *
 * @author jhont
 */
public class Dia {
    private static String VentaDelDia;
    private static String dia;
    private static String mes;
    private static String anio;

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        Dia.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        Dia.anio = anio;
    }

    public void abrirCaja(Date fecha) {

    }

    public void corteCaja() {

    }

    public String toStringDia() {
        String dia = null;
        return dia;
    }

    public void crearArchivoDia() {

    }

    public void cobrar() {

    }

    public void tiketCliente() {

    }
}
