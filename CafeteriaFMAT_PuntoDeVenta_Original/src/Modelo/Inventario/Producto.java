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
public class Producto {
    private String nombre;
    private int codigo;
    private double precioDeCompra;
    private double precioDeVenta;
    private int existencia;
    private int codigoClasificacion;
    private int codigoArea;

    public Producto( int codigo, String nombre, double precioDeCompra, double precioDeVenta, int existencia, int codigoClasificacion,int codigoArea) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precioDeCompra = precioDeCompra;
        this.precioDeVenta = precioDeVenta;
        this.existencia = existencia;
        this.codigoClasificacion = codigoClasificacion;
        this.codigoArea = codigoArea;
        
    }

    public Producto() {
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

    public double getPrecioDeCompra() {
        return precioDeCompra;
    }

    public void setPrecioDeCompra(double precioDeCompra) {
        this.precioDeCompra = precioDeCompra;
    }

    public double getPrecioDeVenta() {
        return precioDeVenta;
    }

    public void setPrecioDeVenta(double precioDeVenta) {
        this.precioDeVenta = precioDeVenta;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public int getCodigoClasificacion() {
        return codigoClasificacion;
    }

    public void setCodigoClasificacion(int codigoClasificacion) {
        this.codigoClasificacion = codigoClasificacion;
    }

    public int getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(int codigoArea) {
        this.codigoArea = codigoArea;
    }
    
    
    
}
