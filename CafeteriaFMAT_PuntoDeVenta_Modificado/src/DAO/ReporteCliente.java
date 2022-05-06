/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author 52999
 */
public class ReporteCliente {
    private int matricula;
    private String dia;
    private String mes;
    private String anio;
    private String accion;
    private double actualizacion;
    private double saldo;

    public ReporteCliente(int matricula, String dia, String mes, String anio, String accion, double actualizacion,
            double saldo) {
        this.matricula = matricula;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.accion = accion;
        this.actualizacion = actualizacion;
        this.saldo = saldo;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

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
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public double getActualizacion() {
        return actualizacion;
    }

    public void setActualizacion(double actualizacion) {
        this.actualizacion = actualizacion;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
