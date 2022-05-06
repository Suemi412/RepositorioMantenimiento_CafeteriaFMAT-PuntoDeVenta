/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Inventario;

/**
 *
 * @author jhont
 */
public class Clasificacion {
    private String nombre;
    private int codigo;
    private int codigoArea;

    public Clasificacion(int codigo, String nombre, int codigoArea) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.codigoArea = codigoArea;
    }

    public Clasificacion() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(int codigoArea) {
        this.codigoArea = codigoArea;
    }

}
