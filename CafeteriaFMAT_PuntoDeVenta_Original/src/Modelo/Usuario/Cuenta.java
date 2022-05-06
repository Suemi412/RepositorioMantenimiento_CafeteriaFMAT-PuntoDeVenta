/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Usuario;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jhont
 */
public class Cuenta {
    private double saldo;

    public Cuenta(double saldo) {
        this.saldo = saldo;
    }

    public Cuenta() {
        
    }
    

    public void agregarSaldo(double deposito){
        saldo = saldo + deposito;
    }
    
    public void restarSaldo(double venta){
        if(saldo< venta){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Saldo insuficiente", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            saldo= saldo-venta;
        }
    }
    
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    
}
