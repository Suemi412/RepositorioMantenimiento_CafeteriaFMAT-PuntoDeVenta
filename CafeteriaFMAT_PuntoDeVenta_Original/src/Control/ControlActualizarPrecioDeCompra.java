/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
import DAO.DAOProducto;
import Modelo.Inventario.Producto;
import Vista.vistaActualizarPrecioDeCompra;
import Vista.vistaBuscadorInventario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/** 
 *
 * @author jhont
 */
public class ControlActualizarPrecioDeCompra implements ActionListener {
    private Producto modeloProducto;
    private vistaActualizarPrecioDeCompra vistaActualizarPrecioDeCompra;
    private vistaBuscadorInventario v;
    public ControlActualizarPrecioDeCompra (Producto modeloProducto,vistaActualizarPrecioDeCompra vistaActual,vistaBuscadorInventario v){
        this.v=v;
        this.vistaActualizarPrecioDeCompra = vistaActual;
        this.vistaActualizarPrecioDeCompra.requestFocus();
        this.vistaActualizarPrecioDeCompra.setAlwaysOnTop(true);
        this.modeloProducto=modeloProducto;
        this.vistaActualizarPrecioDeCompra.getBotonCancelar().addActionListener(this);
        this.vistaActualizarPrecioDeCompra.getBotonActualizar().addActionListener(this);
        this.vistaActualizarPrecioDeCompra.getTxtPrecioActual().setEditable(false);
        this.vistaActualizarPrecioDeCompra.getTxtPrecioActual().setText(String.valueOf(modeloProducto.getPrecioDeCompra()));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(vistaActualizarPrecioDeCompra.getBotonCancelar()==e.getSource()){
             this.v.setEnabled(true);
             vistaActualizarPrecioDeCompra.dispose();
        }
        if(vistaActualizarPrecioDeCompra.getBotonActualizar()==e.getSource()){
            try{
                String condicion;
                condicion = " codigo = " + this.v.getTextoCodigo().getText();
                DAOProducto daoProducto = new DAOProducto();
                ArrayList<Producto> listaProductos = new ArrayList<Producto>();
                listaProductos = daoProducto.consultar(condicion);
                modeloProducto= listaProductos.get(0);
                modeloProducto.setPrecioDeCompra(Double.valueOf(this.vistaActualizarPrecioDeCompra.getTxtModificacion().getText()));
                
                daoProducto.modificar(modeloProducto, condicion);
                this.v.setEnabled(true);
                vistaActualizarPrecioDeCompra.dispose();
            }catch(NumberFormatException error){
                JOptionPane.showMessageDialog(this.vistaActualizarPrecioDeCompra, "Valor invalido", "Error", JOptionPane.ERROR_MESSAGE);
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(this.vistaActualizarPrecioDeCompra, "Error en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
        
    }

}
