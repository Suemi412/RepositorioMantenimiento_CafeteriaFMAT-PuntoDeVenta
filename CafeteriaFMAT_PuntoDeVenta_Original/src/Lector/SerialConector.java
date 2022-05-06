/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lector;

import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort; 
import jssc.SerialPortEvent; 
import jssc.SerialPortEventListener; 
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author jhont
 */
public class SerialConector {
    static SerialPort serialPort;
    public int num;
    
    
    public void lector() throws SerialPortException{
        String puerto="";
        SerialPortReader SPR=new SerialPortReader();
        serialPort = new SerialPort("COM4"); 
        try {
            serialPort.openPort();//Open port
            serialPort.setParams(9600, 8, 1, 0);//Set params
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
            serialPort.setEventsMask(mask);//Set mask
            serialPort.addEventListener(SPR);//Add SerialPortEventListener
            System.out.println("Puerto abierto");
        }
        catch (SerialPortException ex) {
            System.out.println("ERROR1");
            System.out.println(ex);
        }
        //serialPort.closePort();
        //System.out.println("Puerto cerrado");
        
    } 
}
