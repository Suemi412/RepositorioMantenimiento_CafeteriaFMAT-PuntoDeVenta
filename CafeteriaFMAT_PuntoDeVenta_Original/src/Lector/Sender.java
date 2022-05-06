/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lector;

/**
 *
 * @author jhont
 */
public class Sender {
    private static int codigo;

    public Sender(){
        
    }
    
    public Sender(int num) {
        setCodigo(num);
        System.out.println(getCodigo());
    }

    public int getCodigo() {
        return codigo;
    }

    public static void setCodigo(int codigo) {
        Sender.codigo = codigo;
    }
    
    
}
