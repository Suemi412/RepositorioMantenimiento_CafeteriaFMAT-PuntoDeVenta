/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;
import DAO.DAOArea;
import DAO.DAOClasificacion;
import DAO.DAOProducto;
import Modelo.Inventario.*;
import Vista.pantallaPrincipal;
import Vista.vistaActualizarExistencias;
import Vista.vistaActualizarPrecioDeCompra;
import Vista.vistaActualizarPrecioDeVenta;
import Vista.vistaBuscadorInventario;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author jhont
 */
public class ControlBuscarProducto implements ActionListener {
    private Producto modeloProducto;
    private vistaBuscadorInventario vistaBuscadorInventario;
    private pantallaPrincipal v;
    private vistaActualizarPrecioDeVenta vistaActualizarPrecioDeVenta;
    public ControlBuscarProducto(Producto modeloProducto,pantallaPrincipal v,vistaBuscadorInventario vistaActual){
        this.v=v;
        this.vistaBuscadorInventario = vistaActual;
        this.vistaBuscadorInventario.requestFocus();
        this.vistaBuscadorInventario.setAlwaysOnTop(true);
        this.modeloProducto=modeloProducto;
        this.vistaBuscadorInventario.getBotonActualizarPrecioDeLaCompra().addActionListener(this);
        this.vistaBuscadorInventario.getBotonActualizarPrecioDeVenta().addActionListener(this);
        this.vistaBuscadorInventario.getBotonActualizarExistencias().addActionListener(this);//Flataron 2 botones
        this.vistaBuscadorInventario.getBotonBuscar().addActionListener(this);
        this.vistaBuscadorInventario.getBotonEliminarProductoDelInventario().addActionListener(this);
        this.vistaBuscadorInventario.getBotonRegresarAPantallaPrincipal().addActionListener(this);
	this.vistaBuscadorInventario.getTextoArea().setEditable(false);
        this.vistaBuscadorInventario.getTextoClasificacion().setEditable(false);
        this.vistaBuscadorInventario.getTextoCodigoArea().setEditable(false);
        this.vistaBuscadorInventario.getTextoCodigoClasificacion().setEditable(false);
        this.vistaBuscadorInventario.getTextoCodigoProducto().setEditable(false);
        this.vistaBuscadorInventario.getTextoCompra().setEditable(false);
        this.vistaBuscadorInventario.getTextoExistencias().setEditable(false);
        this.vistaBuscadorInventario.getTextoVenta().setEditable(false);
        Area modeloArea=new Area();
        Clasificacion modeloClasificacion=new Clasificacion();
        
        this.vistaBuscadorInventario.getTextoCodigoProducto().setText(String.valueOf(modeloProducto.getCodigo()));
        this.vistaBuscadorInventario.getTextoArea().setText(modeloArea.getNombre());
        this.vistaBuscadorInventario.getTextoClasificacion().setText(modeloClasificacion.getNombre());
        this.vistaBuscadorInventario.getTextoCodigoArea().setText(String.valueOf(modeloArea.getCodigo()));
        this.vistaBuscadorInventario.getTextoCodigoClasificacion().setText(String.valueOf(modeloClasificacion.getCodigo()));
        this.vistaBuscadorInventario.getTextoCompra().setText(String.valueOf(modeloProducto.getPrecioDeCompra()));
        this.vistaBuscadorInventario.getTextoVenta().setText(String.valueOf(modeloProducto.getPrecioDeVenta()));
        this.vistaBuscadorInventario.getTextoExistencias().setText(String.valueOf(modeloProducto.getExistencia()));

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vistaBuscadorInventario.getBotonRegresarAPantallaPrincipal()==evento.getSource()){
            this.v.setEnabled(true);
            vistaBuscadorInventario.dispose();
        }
        
        if(vistaBuscadorInventario.getBotonEliminarProductoDelInventario()==evento.getSource()){
            int opcion=JOptionPane.showConfirmDialog(this.vistaBuscadorInventario, "El producto será eliminado del inventario y no podrá ser recuperado", "Advertencia", JOptionPane.OK_CANCEL_OPTION);
            if(opcion==0){
                //Manejo de base de datos
                String condicion;
                 condicion = " codigo = " + String.valueOf(this.vistaBuscadorInventario.getTextoCodigo().getText());
                 DAOProducto daoProducto = new DAOProducto();
                 try{
                  daoProducto.eliminar(condicion);
                }catch(SQLException e){
                        e.printStackTrace();
                }
            }
            else{
                JOptionPane.showMessageDialog(this.vistaBuscadorInventario, "Operacion cancelada");
            }
        }
        
        if(vistaBuscadorInventario.getBotonBuscar()==evento.getSource()){
            try{
                int temp = Integer.valueOf(vistaBuscadorInventario.getTextoCodigo().getText());
                //Manejo de base de datos
                String condicion;
                 condicion = " codigo = " + temp;
                 DAOProducto daoProducto = new DAOProducto();
                 ArrayList<Producto> listaProductos = new ArrayList<Producto>();
                 DAOArea daoArea = new DAOArea();
                 ArrayList<Area> listaAreas = new ArrayList<Area>();
                 DAOClasificacion daoClasificacion = new DAOClasificacion();
                 ArrayList<Clasificacion> listaClasificaciones = new ArrayList<Clasificacion>();
                 try{
                  listaProductos = daoProducto.consultar(condicion);
                  Producto producto = new Producto();
                  producto = listaProductos.get(0);
                  int codigoA = producto.getCodigoArea();
                  int codigoC = producto.getCodigoClasificacion();
                  
                  condicion = "codigo ="+ codigoA;
                  listaAreas =daoArea.consultar(condicion);
                  Area a = new Area();
                  a = listaAreas.get(0);
                  
                  condicion="codigo ="+codigoC;
                  listaClasificaciones= daoClasificacion.consultar(condicion);
                  Clasificacion c = new Clasificacion();
                  c=listaClasificaciones.get(0);
                  
                  this.vistaBuscadorInventario.getTextoProducto().setText(producto.getNombre());
                  this.vistaBuscadorInventario.getTextoCompra().setText(String.valueOf(producto.getPrecioDeCompra()));
                  this.vistaBuscadorInventario.getTextoVenta().setText(String.valueOf(producto.getPrecioDeVenta()));
                  this.vistaBuscadorInventario.getTextoExistencias().setText(String.valueOf(producto.getExistencia()));
                  this.vistaBuscadorInventario.getTextoCodigoArea().setText(String.valueOf(codigoA));
                  this.vistaBuscadorInventario.getTextoCodigoClasificacion().setText(String.valueOf(codigoC));
                  this.vistaBuscadorInventario.getTextoArea().setText(a.getNombre());
                  this.vistaBuscadorInventario.getTextoClasificacion().setText(c.getNombre());
                  this.vistaBuscadorInventario.getTextoCodigoProducto().setText(String.valueOf(producto.getCodigo()));
                }catch(SQLException e){
                        e.printStackTrace();
                }
                 
                
            }
            catch(NumberFormatException error){
                JOptionPane.showMessageDialog(this.vistaBuscadorInventario, "Valor de codigo invalido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(vistaBuscadorInventario.getBotonActualizarPrecioDeLaCompra()==evento.getSource()){
            int temp = Integer.valueOf(vistaBuscadorInventario.getTextoCodigo().getText());
                //Manejo de base de datos
                String condicion;
                 condicion = " codigo = " + temp;
                 DAOProducto daoProducto = new DAOProducto();
                 ArrayList<Producto> listaProductos = new ArrayList<Producto>();
                 DAOArea daoArea = new DAOArea();
                 ArrayList<Area> listaAreas = new ArrayList<Area>();
                 DAOClasificacion daoClasificacion = new DAOClasificacion();
                 ArrayList<Clasificacion> listaClasificaciones = new ArrayList<Clasificacion>();
                 try{
                    listaProductos = daoProducto.consultar(condicion);
                    Producto producto = new Producto();
                    modeloProducto = listaProductos.get(0);
                    this.vistaBuscadorInventario.setEnabled(false);
                    vistaActualizarPrecioDeCompra vAPDC = new vistaActualizarPrecioDeCompra();
                    ControlActualizarPrecioDeCompra cAPDC=new ControlActualizarPrecioDeCompra(modeloProducto,vAPDC,vistaBuscadorInventario);
                    vAPDC.setVisible(true);
                 }catch(Exception e){
                     JOptionPane.showMessageDialog(this.vistaBuscadorInventario, "Favor de ingresar un codigo Valido");
                 }
            
        }
        
        if(vistaBuscadorInventario.getBotonActualizarPrecioDeVenta()==evento.getSource()){
            int temp = Integer.valueOf(vistaBuscadorInventario.getTextoCodigo().getText());
                //Manejo de base de datos
                String condicion;
                 condicion = " codigo = " + temp;
                 DAOProducto daoProducto = new DAOProducto();
                 ArrayList<Producto> listaProductos = new ArrayList<Producto>();
                 DAOArea daoArea = new DAOArea();
                 ArrayList<Area> listaAreas = new ArrayList<Area>();
                 DAOClasificacion daoClasificacion = new DAOClasificacion();
                 ArrayList<Clasificacion> listaClasificaciones = new ArrayList<Clasificacion>();
                 try{
                    listaProductos = daoProducto.consultar(condicion);
                    Producto producto = new Producto();
                    modeloProducto = listaProductos.get(0);
            this.vistaBuscadorInventario.setEnabled(false);
            vistaActualizarPrecioDeVenta vAPDV = new vistaActualizarPrecioDeVenta();
            ControlActualizarPrecioDeVenta cAPDV=new ControlActualizarPrecioDeVenta(modeloProducto,vAPDV,vistaBuscadorInventario);
            vAPDV.setVisible(true);
             }catch(Exception e){
                     JOptionPane.showMessageDialog(this.vistaBuscadorInventario, "Favor de ingresar un codigo Valido");
                 }
        }
        
        if(vistaBuscadorInventario.getBotonActualizarExistencias()==evento.getSource()){
            int temp = Integer.valueOf(vistaBuscadorInventario.getTextoCodigo().getText());
                //Manejo de base de datos
                String condicion;
                 condicion = " codigo = " + temp;
                 DAOProducto daoProducto = new DAOProducto();
                 ArrayList<Producto> listaProductos = new ArrayList<Producto>();
                 DAOArea daoArea = new DAOArea();
                 ArrayList<Area> listaAreas = new ArrayList<Area>();
                 DAOClasificacion daoClasificacion = new DAOClasificacion();
                 ArrayList<Clasificacion> listaClasificaciones = new ArrayList<Clasificacion>();
                 try{
                    listaProductos = daoProducto.consultar(condicion);
                    Producto producto = new Producto();
                    modeloProducto = listaProductos.get(0);
            this.vistaBuscadorInventario.setEnabled(false);
            vistaActualizarExistencias vAE = new vistaActualizarExistencias();
            ControlActualizarExistencias cAPDV=new ControlActualizarExistencias(modeloProducto,vAE,vistaBuscadorInventario);
            vAE.setVisible(true);
             }catch(Exception e){
                     JOptionPane.showMessageDialog(this.vistaBuscadorInventario, "Favor de ingresar un codigo Valido");
                 }
        }
    }
}
