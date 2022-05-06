/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.DAOArea;
import DAO.DAOClasificacion;
import Modelo.Inventario.*;
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
public class ControlAgregarClasificacion implements ActionListener{
    private Area modeloArea;
    private Clasificacion modeloClasificacion;
    private vistaAgregarClasificacion VistaC;
    private vistaAgregarArea v;
    private pantallaPrincipal vP;

    public ControlAgregarClasificacion(Area modeloArea, Clasificacion modeloClasificacion,
           vistaAgregarClasificacion VistaC, vistaAgregarArea v, pantallaPrincipal vP) {
        this.modeloArea = modeloArea;
        this.modeloClasificacion = modeloClasificacion;
        this.VistaC = VistaC;
        this.VistaC.requestFocus();
        this.VistaC.setAlwaysOnTop(true);
        
        this.v = v;
        this.vP = vP;
            
        this.VistaC.getBotonAgregarClasificacion().addActionListener(this);
        this.VistaC.getBotonCancelarOperacion().addActionListener(this);
        this.VistaC.getBotonIrAgregarProducto().addActionListener(this);
        this.VistaC.getBotonRegresarAPantallaPrincipal().addActionListener(this);
        this.VistaC.getTextoCodigoClasificacion().setEditable(false);
        buscarAreas(this.VistaC);
    }

    public ControlAgregarClasificacion(Area modeloArea, Clasificacion modeloClasificacion,
           vistaAgregarClasificacion VistaC, pantallaPrincipal vP) {
        this.modeloArea = modeloArea;
        this.modeloClasificacion = modeloClasificacion;
        this.VistaC = VistaC;
        this.vP = vP;
        this.v = null;
           
        this.VistaC.getBotonAgregarClasificacion().addActionListener(this);
        this.VistaC.getBotonCancelarOperacion().addActionListener(this);
        this.VistaC.getBotonIrAgregarProducto().addActionListener(this);
        this.VistaC.getBotonRegresarAPantallaPrincipal().addActionListener(this);
        buscarAreas(this.VistaC);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        if(VistaC.getBotonIrAgregarProducto() == evento.getSource()){
            this.VistaC.setEnabled(false);
            vistaAgregarProducto vistaProducto = new vistaAgregarProducto();
            Producto modeloProducto = new Producto(0,null,0.0,0.0,0,0,0);
            ControlAgregarProducto c = new ControlAgregarProducto(this.modeloArea,this.modeloClasificacion,modeloProducto,
            this.v,this.VistaC, this.vP, vistaProducto);
            vistaProducto.setVisible(true);
        }
        
        
        if(VistaC.getBotonAgregarClasificacion() == evento.getSource()){
            ArrayList<Clasificacion> listaClasificaciones = new ArrayList<Clasificacion>();
            String condicion;
            DAOClasificacion daoClasificacion = new DAOClasificacion();
            int codigoArea = 0;
             int codigoClasificacion = 0;
            try{
                codigoArea = this.toIntegerArea();
            }catch(Exception e){
                // favor de elegir un area
                JFrame frame = new JFrame("Status");
                JOptionPane.showMessageDialog(frame, e);
            }
            condicion = " codigoArea = " + codigoArea;
            try{
              listaClasificaciones = daoClasificacion.consultar(condicion);
            }catch(Exception e){
                    e.printStackTrace();
            }
            if(!listaClasificaciones.isEmpty()){
                codigoClasificacion = listaClasificaciones.size() + 1;
            }else{
                codigoClasificacion = 1;
            }
            
            this.VistaC.getTextoCodigoClasificacion().setText(String.valueOf(codigoClasificacion));
            
                    String nombreClasificacion;
                    nombreClasificacion = String.valueOf(this.VistaC.getTextoNombreClasificacion().getText());

                    this.modeloClasificacion.setNombre(nombreClasificacion);
                    this.modeloClasificacion.setCodigo(codigoClasificacion);
                    this.modeloClasificacion.setCodigoArea(codigoArea);

                    try{
                      daoClasificacion.agregar(modeloClasificacion);
                    }catch(Exception e){
                        e.printStackTrace();
            }
        }
        
        if(VistaC.getBotonCancelarOperacion() == evento.getSource()){
            if(v == null){
                this.vP.setEnabled(true);
            }else{
                this.v.setEnabled(true);
            }
            this.VistaC.dispose();
        }
        
        
        if(VistaC.getBotonRegresarAPantallaPrincipal() == evento.getSource()){
            if(v == null){
                this.vP.setEnabled(true);
            }else{
                this.v.dispose();
                this.vP.setEnabled(true);
            }
            this.VistaC.dispose();
        }
        this.VistaC.getTextoCodigoClasificacion().setText(String.valueOf(modeloClasificacion.getCodigo()));
    }
    private void buscarAreas(vistaAgregarClasificacion Vista) {
        
        ArrayList<Area> listaAreas = new ArrayList<Area>();
        DAOArea daoArea = new DAOArea();
        try{
          listaAreas = daoArea.consultar(null);
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
    private int toIntegerArea()throws NumberFormatException{
        StringTokenizer st = new StringTokenizer(String.valueOf(this.VistaC.getComboArea().getModel().getSelectedItem()));
        return Integer.valueOf(st.nextToken());
    }
}
