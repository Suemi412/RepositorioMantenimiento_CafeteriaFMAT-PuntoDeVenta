/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Usuario;

/**
 *
 * @author jhont
 */
public class Cliente {
    private int matricula;
    private String nombre;
    private String fechaIngreso;
    private Cuenta cuenta;

    public Cliente(int matricula,String nombre,double saldo) {
        this.nombre = nombre;
        this.matricula = matricula;
        cuenta = new Cuenta(saldo);
    }
    
    public Cliente(){
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
    public void vaciarCuenta(){
        
    }
    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    
}
