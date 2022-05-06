/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.DAOProducto;
import Modelo.Inventario.Producto;
import java.awt.event.ActionListener;
import Vista.vistaActualizarExistencias;
import Vista.vistaBuscadorInventario;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author jhont
 */
public class ControlActualizarExistencias implements ActionListener {
    private Producto modeloProducto;
    private vistaActualizarExistencias vistaActualizarExistencias;
    private vistaBuscadorInventario v;
    public ControlActualizarExistencias(Producto modeloProducto,vistaActualizarExistencias vistaActual,vistaBuscadorInventario v){
        this.v=v;
        this.vistaActualizarExistencias = vistaActual;
        this.vistaActualizarExistencias.requestFocus();
        this.vistaActualizarExistencias.setAlwaysOnTop(true);
        this.modeloProducto=modeloProducto;
        this.vistaActualizarExistencias.getBotonAgregar().addActionListener(this);
        this.vistaActualizarExistencias.getBotonRegresar().addActionListener(this);
        this.vistaActualizarExistencias.getBotonRetirar().addActionListener(this);
        this.vistaActualizarExistencias.getTxtExistenciasA().setEditable(false);
        this.vistaActualizarExistencias.getTxtExistenciasA().setText(String.valueOf(modeloProducto.getExistencia()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(vistaActualizarExistencias.getBotonRegresar()==e.getSource()){
             this.v.setEnabled(true);
             vistaActualizarExistencias.dispose();
        }
        if(vistaActualizarExistencias.getBotonAgregar()==e.getSource()){
            try{
                String condicion;
                condicion = " codigo = " + modeloProducto.getCodigo();
                DAOProducto daoProducto = new DAOProducto();
                ArrayList<Producto> listaProductos = new ArrayList<Producto>();
                listaProductos = daoProducto.consultar(condicion);
                modeloProducto= listaProductos.get(0);
                modeloProducto.setExistencia(Integer.valueOf(this.vistaActualizarExistencias.getTxtExistenciasA().getText())+Integer.valueOf(this.vistaActualizarExistencias.getTxtModificacion().getText()));
                
                daoProducto.modificar(modeloProducto, condicion);
                this.v.setEnabled(true);
                modeloProducto.setExistencia(Integer.valueOf(this.vistaActualizarExistencias.getTxtModificacion().getText()));
                this.vistaActualizarExistencias.dispose();
            }
            catch(NumberFormatException error){
                
                JOptionPane.showMessageDialog(this.vistaActualizarExistencias, "Valor invalido", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this.vistaActualizarExistencias, "Error en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(vistaActualizarExistencias.getBotonRetirar()==e.getSource()){
            try{
                String condicion;
                condicion = " codigo = " + modeloProducto.getCodigo();
                DAOProducto daoProducto = new DAOProducto();
                ArrayList<Producto> listaProductos = new ArrayList<Producto>();
                listaProductos = daoProducto.consultar(condicion);
                modeloProducto= listaProductos.get(0);
                modeloProducto.setExistencia(Integer.valueOf(this.vistaActualizarExistencias.getTxtExistenciasA().getText())-Integer.valueOf(this.vistaActualizarExistencias.getTxtModificacion().getText()));
                
                daoProducto.modificar(modeloProducto, condicion);
                this.v.setEnabled(true);
                modeloProducto.setExistencia(Integer.valueOf(this.vistaActualizarExistencias.getTxtModificacion().getText()));
                this.vistaActualizarExistencias.dispose();
            }
            catch(NumberFormatException error){
                
                JOptionPane.showMessageDialog(this.vistaActualizarExistencias, "Valor invalido", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(this.vistaActualizarExistencias, "Error en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
}

