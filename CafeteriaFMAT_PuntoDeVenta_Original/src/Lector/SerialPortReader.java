/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lector;

import static Lector.SerialConector.serialPort;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author jhont
 */
public class SerialPortReader implements SerialPortEventListener {
    private int num;
    private String sbuffer=new String("");
    public void serialEvent(SerialPortEvent event) {
        if(event.isRXCHAR()){//If data is available
            try {
                String st = serialPort.readString(event.getEventValue());
                System.out.println("llego");
                System.out.println("Sbuffer:\n");
                System.out.println(st);
                
                System.out.println("Despuees de sbuffer:\n");
                this.num=convertidor(st);
                
            }
            catch(SerialPortException ex){
                System.out.println("error");
                System.out.println(ex);
            }
        }
        try {
            serialPort.closePort();
        } catch (SerialPortException ex) {
            Logger.getLogger(SerialPortReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Puerto Cerrado");
    }

    public int getNum() {
        return this.num;
    }        
        
    public int convertidor(String cadena){
        
        System.out.println("LLEGO CONVERTIDOR\n");
        System.out.println(cadena);
        int temp,a,c,p;
        temp=Integer.valueOf(cadena);
        p = temp%100;
        temp = temp/100;
        c = temp%100;
        temp = temp/100;
        a = temp%100;
        p=switchBinario(p);
        c=switchBinario(c);
        a=switchBinario(a);
        temp=(a*100)+(c*10)+p;
        System.out.println(temp);
        Sender send= new Sender(temp);
        return temp;
    }
        
    public int switchBinario(int v){
        switch(v){
            case 00:
                v=1;
                break;
            case 01:
                v=2;
                break;
            case 10:
                v=3;
                break;
            case 11:
                v=4;
                break;
            default:
                break;
        }
        return v;
    }
}
