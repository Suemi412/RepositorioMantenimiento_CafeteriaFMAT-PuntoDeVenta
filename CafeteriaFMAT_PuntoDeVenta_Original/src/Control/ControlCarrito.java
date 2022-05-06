/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Caja.Carrito;

import Modelo.Inventario.Producto;
import Modelo.Usuario.Cliente;
import Vista.pantallaPrincipal;
import Vista.vistaAgregarProductosACarrito;
import Vista.vistaCarrito;
import Vista.vistaSeleccionarCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jssc.SerialPortException;

/**
 *
 * @author jhont
 */
public class ControlCarrito implements ActionListener{
    private Carrito modeloCarrito;
    private vistaCarrito vistaCarrito;
    private pantallaPrincipal v;
    
    public ControlCarrito(Carrito modeloCarrito, vistaCarrito vistaCarrito, pantallaPrincipal v){
        this.modeloCarrito=modeloCarrito;
        this.vistaCarrito=vistaCarrito;
        this.v=v;
        this.vistaCarrito.setAlwaysOnTop(true);
        this.vistaCarrito.requestFocus();
        
        this.vistaCarrito.getAgregarProducto().addActionListener(this);
        this.vistaCarrito.getQuitarProducto().addActionListener(this);
        this.vistaCarrito.getRealizarVenta().addActionListener(this);
        this.vistaCarrito.getLimiarCarrito().addActionListener(this);
        this.vistaCarrito.getCancelarCompra().addActionListener(this);
        this.vistaCarrito.gettxtTotal().setEditable(false);
        this.vistaCarrito.gettxtTotal().setText("0");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(vistaCarrito.getAgregarProducto()== e.getSource()){            
            this.vistaCarrito.setEnabled(false);
            Producto producto = new Producto(0,null,0.0,0.0,0,0,0);
            vistaAgregarProductosACarrito vAPC = new vistaAgregarProductosACarrito();
            vAPC.setVisible(true);
            try {            
                ControlAgregaCarro controlAddCar = new ControlAgregaCarro(producto, vAPC, this.vistaCarrito);
            } catch (Exception ex) {
                Logger.getLogger(ControlCarrito.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(vistaCarrito.getQuitarProducto()== e.getSource()){
            int fila=vistaCarrito.getTablaCarro().getSelectedRow();
            if(fila>=0){
                double total=Double.valueOf(vistaCarrito.gettxtTotal().getText())-Double.valueOf(String.valueOf(vistaCarrito.modelo.getValueAt(fila, 3)));
                vistaCarrito.gettxtTotal().setText(String.valueOf(total));
                vistaCarrito.modelo.removeRow(fila);
            }
            else{
                JOptionPane.showMessageDialog(this.vistaCarrito, "Favor de seleccionar fila", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(vistaCarrito.getRealizarVenta()== e.getSource()){
            if(vistaCarrito.getTablaCarro().getRowCount()>0){
                this.vistaCarrito.setEnabled(false);
                Cliente cliente= new Cliente(0, null, 0);
                vistaSeleccionarCliente vSC= new vistaSeleccionarCliente();
                vSC.setVisible(true);
                ControlSelectCliente controlSelectCliente= new ControlSelectCliente(cliente, vSC, this.vistaCarrito, this.v);
            }
            else{
                JOptionPane.showMessageDialog(this.vistaCarrito, "Carrito vacío", "Error", JOptionPane.ERROR_MESSAGE);
            }      
        }
        
        if(vistaCarrito.getLimiarCarrito()== e.getSource()){
            if(vistaCarrito.getTablaCarro().getRowCount()>0){
                int fila= vistaCarrito.getTablaCarro().getRowCount();
                for(int i=fila-1; i>=0; i--){
                    int total=Integer.parseInt(vistaCarrito.gettxtTotal().getText())-Integer.parseInt(String.valueOf(vistaCarrito.modelo.getValueAt(i, 3)));
                    vistaCarrito.gettxtTotal().setText(String.valueOf(total));
                    vistaCarrito.modelo.removeRow(i);
                }
            }
            else{
                JOptionPane.showMessageDialog(this.vistaCarrito, "Carrito vacío", "Error", JOptionPane.ERROR_MESSAGE);
            }   
        }
        
        if(vistaCarrito.getCancelarCompra()== e.getSource()){
            this.v.setEnabled(true);
            vistaCarrito.dispose();
        }
    }
    
}
