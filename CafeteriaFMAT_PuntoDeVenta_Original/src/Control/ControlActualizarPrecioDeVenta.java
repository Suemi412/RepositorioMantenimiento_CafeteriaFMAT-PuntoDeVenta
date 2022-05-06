/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
import DAO.DAOProducto;
import Modelo.Inventario.Producto;
import Vista.vistaActualizarPrecioDeVenta;
import Vista.vistaBuscadorInventario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author jhont
 */
public class ControlActualizarPrecioDeVenta implements ActionListener{
    private Producto modeloProducto;
    private vistaActualizarPrecioDeVenta vistaActualizarPrecioDeVenta;
    private vistaBuscadorInventario v;
    public ControlActualizarPrecioDeVenta(Producto modeloProducto,vistaActualizarPrecioDeVenta vistaActual,vistaBuscadorInventario v){
        this.v=v;
        this.vistaActualizarPrecioDeVenta = vistaActual;
        this.vistaActualizarPrecioDeVenta.requestFocus();
        this.vistaActualizarPrecioDeVenta.setAlwaysOnTop(true);
        this.modeloProducto=modeloProducto;
        this.vistaActualizarPrecioDeVenta.getBotonCancelar().addActionListener(this);
        this.vistaActualizarPrecioDeVenta.getBotonActualizar().addActionListener(this);
        this.vistaActualizarPrecioDeVenta.getTxtPrecioActual().setEditable(false);
        this.vistaActualizarPrecioDeVenta.getTxtPrecioActual().setText(String.valueOf(modeloProducto.getPrecioDeVenta()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(vistaActualizarPrecioDeVenta.getBotonCancelar()==e.getSource()){
             this.v.setEnabled(true);
             vistaActualizarPrecioDeVenta.dispose();
        }
        if(vistaActualizarPrecioDeVenta.getBotonActualizar()==e.getSource()){
            
            try{
                String condicion;
                condicion = " codigo = " + modeloProducto.getCodigo();
                DAOProducto daoProducto = new DAOProducto();
                ArrayList<Producto> listaProductos = new ArrayList<Producto>();
                listaProductos = daoProducto.consultar(condicion);
                modeloProducto= listaProductos.get(0);
                modeloProducto.setPrecioDeVenta(Double.valueOf(this.vistaActualizarPrecioDeVenta.getTxtModificacion().getText()));
                
                daoProducto.modificar(modeloProducto, condicion);
                this.v.setEnabled(true);
                modeloProducto.setPrecioDeVenta(Double.valueOf(this.vistaActualizarPrecioDeVenta.getTxtModificacion().getText()));
                vistaActualizarPrecioDeVenta.dispose();
            }
            catch(NumberFormatException error){
                
                JOptionPane.showMessageDialog(this.vistaActualizarPrecioDeVenta, "Valor invalido", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this.vistaActualizarPrecioDeVenta, "Error en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
           
        }
    }  
   
}
