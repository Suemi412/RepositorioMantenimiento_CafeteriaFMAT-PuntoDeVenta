/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.DAOArea;
import DAO.DAOClasificacion;
import DAO.DAOProducto;
import Modelo.Inventario.Area;
import Modelo.Inventario.Clasificacion;
import Modelo.Inventario.Producto;
import Vista.pantallaPrincipal;
import Vista.vistaAgregarArea;
import Vista.vistaAgregarClasificacion;
import Vista.vistaAgregarProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



/**
 *
 * @author Igniter
 */
public class ControlAgregarProducto implements ActionListener{
  private Area modeloArea;
    private Clasificacion modeloClasificacion;
    private Producto modeloProducto;
    private pantallaPrincipal vP;
    private vistaAgregarProducto vistaAgregarProductos;
    private vistaAgregarClasificacion vC;
    private vistaAgregarArea vA;


public ControlAgregarProducto(Area modeloArea, Clasificacion modeloClasificacion,Producto modeloProducto,vistaAgregarArea vA,
       vistaAgregarClasificacion vC,pantallaPrincipal vP,vistaAgregarProducto vistaAgregarProductos ) {
        this.modeloArea = modeloArea;
        this.modeloClasificacion = modeloClasificacion;
        this.modeloProducto = modeloProducto;
        this.vistaAgregarProductos = vistaAgregarProductos;
        this.vistaAgregarProductos.requestFocus();
        this.vistaAgregarProductos.setAlwaysOnTop(true);
        this.vA = vA;
        this.vC = vC;
        this.vP = vP;
        
        
        this.vistaAgregarProductos.getBotonAgregarProducto().addActionListener(this);
        this.vistaAgregarProductos.getBotonCancelar().addActionListener(this);
        this.vistaAgregarProductos.getBotonLimpiarFromato().addActionListener(this);
        this.vistaAgregarProductos.getBotonRegresarInicio().addActionListener(this);
        this.vistaAgregarProductos.getTextoCodigoProducto().setEditable(false);
        this.vistaAgregarProductos.getComboArea().addActionListener(this);
        buscarAreas(this.vistaAgregarProductos);
        
    }

public ControlAgregarProducto(Area modeloArea, Clasificacion modeloClasificacion,Producto modeloProducto,
       vistaAgregarClasificacion vC,pantallaPrincipal vP,vistaAgregarProducto vistaAgregarProductos ) {
        this.modeloArea = modeloArea;
        this.modeloClasificacion = modeloClasificacion;
        this.modeloProducto = modeloProducto;
        this.vistaAgregarProductos = vistaAgregarProductos;
        this.vistaAgregarProductos.requestFocus();
        this.vistaAgregarProductos.setAlwaysOnTop(true);
        this.vA = null;
        this.vC = vC;
        this.vP = vP;
        
        
        this.vistaAgregarProductos.getBotonAgregarProducto().addActionListener(this);
        this.vistaAgregarProductos.getBotonCancelar().addActionListener(this);
        this.vistaAgregarProductos.getBotonLimpiarFromato().addActionListener(this);
        this.vistaAgregarProductos.getBotonRegresarInicio().addActionListener(this);
        this.vistaAgregarProductos.getTextoCodigoProducto().setEditable(false);
        this.vistaAgregarProductos.getComboArea().addActionListener(this);
        buscarAreas(this.vistaAgregarProductos);
        
    }

    public ControlAgregarProducto(Area modeloArea, Clasificacion modeloClasificacion, Producto modeloProducto,
           pantallaPrincipal vP, vistaAgregarProducto vistaAgregarProductos) {
        this.modeloArea = modeloArea;
        this.modeloClasificacion = modeloClasificacion;
        this.modeloProducto = modeloProducto;
        this.vA = null;
        this.vC = null;
        this.vP = vP;
        this.vistaAgregarProductos = vistaAgregarProductos;
        this.vistaAgregarProductos.requestFocus();
        this.vistaAgregarProductos.setAlwaysOnTop(true);
            
            
        this.vistaAgregarProductos.getBotonAgregarProducto().addActionListener(this);
        this.vistaAgregarProductos.getBotonCancelar().addActionListener(this);
        this.vistaAgregarProductos.getBotonLimpiarFromato().addActionListener(this);
        this.vistaAgregarProductos.getBotonRegresarInicio().addActionListener(this);
        
        this.vistaAgregarProductos.getTextoCodigoProducto().setEditable(false);
        
        this.vistaAgregarProductos.getComboArea().addActionListener(this);
        buscarAreas(this.vistaAgregarProductos);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vistaAgregarProductos.getComboArea() == evento.getSource()){
            if(vistaAgregarProductos.getComboArea().getSelectedIndex()> 0){
                this.vistaAgregarProductos.getComboClasificacion().removeAllItems();
                this.vistaAgregarProductos.getComboClasificacion().addItem("Seleccionar");
                buscarClasificaciones(vistaAgregarProductos);
            }
        }
        
        if(vistaAgregarProductos.getBotonAgregarProducto() == evento.getSource()){
            int codigoProducto = 0,codigoArea=0,codigoClasificacion = 0;
            String nombre;
            double precioCompra, precioVenta;
            int cantidadIngresada = 0;
            boolean validacion = true;
            try{
                codigoArea = toIntegerArea();
            }catch (Exception e){
                // area no valida
                JFrame frame = new JFrame("Status");
                JOptionPane.showMessageDialog(frame, "Favor de seleccionar un area.");
                validacion = false;
            }
            try{
                codigoClasificacion = toIntegerClasificacion();
            }catch (Exception e){
                // clasificacion no valida
                JFrame frame = new JFrame("Status");
                JOptionPane.showMessageDialog(frame, "Favor de seleccionar una clasificacion.");
                validacion = false;
            }
            try{
                cantidadIngresada = Integer.valueOf(vistaAgregarProductos.getTextoCantidadIngresada().getText());
            }catch (Exception e){
                JFrame frame = new JFrame("Status");
                JOptionPane.showMessageDialog(frame, "Favor de ingresar existencia valida" );
                validacion = false;
            }
            
            if(validacion){
                ArrayList<Producto> listaProductos = new ArrayList<Producto>();
                String condicion;
                DAOProducto daoProducto = new DAOProducto();
                condicion = " codigoArea = "+ codigoArea +" AND codigoClasificacion = "+ codigoClasificacion;

                try{
                  listaProductos = daoProducto.consultar(condicion);
                }catch(Exception e){
                        e.printStackTrace();
                }
                int i = 0;
                Producto producto = null;
                for(i=0; i<listaProductos.size(); i++){
                    producto = listaProductos.get(i);
                }
                
                if(i==0){
                    i++;
                }else{
                    i=(producto.getCodigo()%10)+1;
                }
                nombre = vistaAgregarProductos.getTextoNombreProducto().getText();
                precioCompra = Double.valueOf(vistaAgregarProductos.getTextoPrecioCompra().getText());
                precioVenta = Double.valueOf(vistaAgregarProductos.getTextoPrecioVenta().getText());
                codigoProducto = codigoArea*100 +codigoClasificacion*10 +i; 
                modeloProducto.setCodigo(codigoProducto);
                modeloProducto.setCodigoArea(codigoArea);
                modeloProducto.setCodigoClasificacion(codigoClasificacion);
                modeloProducto.setExistencia(cantidadIngresada);
                modeloProducto.setNombre(nombre);
                modeloProducto.setPrecioDeCompra(precioCompra);
                modeloProducto.setPrecioDeVenta(precioVenta);
                try{
                  daoProducto.agregar(modeloProducto);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            this.vistaAgregarProductos.getTextoCodigoProducto().setText(String.valueOf(modeloProducto.getCodigo()));
        }  
        if(vistaAgregarProductos.getBotonLimpiarFromato() == evento.getSource()){
            this.vistaAgregarProductos.getComboArea().setSelectedIndex(0);
            this.vistaAgregarProductos.getTextoCantidadIngresada().setText(null);
            this.vistaAgregarProductos.getTextoCodigoProducto().setText(null);
            this.vistaAgregarProductos.getTextoNombreProducto().setText(null);
            this.vistaAgregarProductos.getTextoPrecioCompra().setText(null);
            this.vistaAgregarProductos.getTextoPrecioVenta().setText("");
            this.vistaAgregarProductos.getTextoCodigoProducto().setText("");
            this.vistaAgregarProductos.getComboClasificacion().removeAllItems();
            this.vistaAgregarProductos.getComboClasificacion().addItem("Seleccionar");
        }
        
        if(vistaAgregarProductos.getBotonCancelar() == evento.getSource()){
            if(this.vC == null){
                this.vP.setEnabled(true);
                this.vistaAgregarProductos.dispose();
            }else{
                this.vC.setEnabled(true);
                this.vistaAgregarProductos.dispose();
            }
        }
        
        if(vistaAgregarProductos.getBotonRegresarInicio() == evento.getSource()){
            this.vP.setEnabled(true);
            if(this.vC == null){
                this.vistaAgregarProductos.dispose();
            }else{
                if(this.vA== null){
                    this.vC.dispose();
                    this.vistaAgregarProductos.dispose();
                }
                else{
                    this.vA.dispose();
                    this.vC.dispose();
                    this.vistaAgregarProductos.dispose();
                }
                
            }
        }
        
        
  
}
    private void buscarAreas(vistaAgregarProducto Vista) { 
        ArrayList<Area> listaAreas = new ArrayList<Area>();
        DAOArea daoCliente = new DAOArea();
        try{
          listaAreas = daoCliente.consultar(null);
        }catch(Exception e){
                e.printStackTrace();
        }
        Area area = null;
        for(int i=0;i<listaAreas.size();i++){
            if(listaAreas.size()>i){
            area = listaAreas.get(i);
            Vista.getComboArea().addItem((area.getCodigo() +" : "+area.getNombre()));
            
            }
        }
    }
    
    private void buscarClasificaciones(vistaAgregarProducto Vista) {
        String condicion;
        int codigoArea;
        StringTokenizer st = new StringTokenizer(String.valueOf(Vista.getComboArea().getSelectedItem()));
        codigoArea = (Integer.valueOf( st.nextToken()));
        condicion = " codigoArea = " + String.valueOf(codigoArea);
        ArrayList<Clasificacion> listaClasificaciones = new ArrayList<Clasificacion>();
        DAOClasificacion daoClasificacion = new DAOClasificacion();
        try{
          listaClasificaciones = daoClasificacion.consultar(condicion);
        }catch(Exception e){
                e.printStackTrace();
        }
        Clasificacion clasificacion = null;
        for(int i=0;i<listaClasificaciones.size();i++){
            if(listaClasificaciones.size()>i){
            clasificacion = listaClasificaciones.get(i);
            Vista.getComboClasificacion().addItem((clasificacion.getCodigo() +" : "+clasificacion.getNombre()));
            }
        }
    }
    
    private int toIntegerArea() throws NumberFormatException {
        StringTokenizer st = new StringTokenizer(String.valueOf(this.vistaAgregarProductos.getComboArea().getModel().getSelectedItem()));
        return Integer.valueOf(st.nextToken());
        
    }
    private int toIntegerClasificacion() throws NumberFormatException{
        StringTokenizer st = new StringTokenizer(String.valueOf(this.vistaAgregarProductos.getComboClasificacion().getModel().getSelectedItem()));
        return Integer.valueOf(st.nextToken());
    }
}
