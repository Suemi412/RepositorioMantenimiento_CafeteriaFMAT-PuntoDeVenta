/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.DAOArea;
import Modelo.Inventario.Area;
import Modelo.Inventario.Clasificacion;
import Vista.pantallaPrincipal;
import Vista.vistaAgregarArea;
import Vista.vistaAgregarClasificacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Igniter
 */
public class ControlAgregarArea implements ActionListener{
    private Area modeloArea;
    private vistaAgregarArea vistaAgregarArea;
    private pantallaPrincipal v;
 public ControlAgregarArea(Area modeloArea, vistaAgregarArea vistaActual,pantallaPrincipal v) {
    this.v = v;
    this.modeloArea = modeloArea;
    this.vistaAgregarArea = vistaActual;
    this.vistaAgregarArea.requestFocus();
    this.vistaAgregarArea.setAlwaysOnTop(true);
    this.vistaAgregarArea.getBotonAgregarClasificacion().addActionListener(this);
    this.vistaAgregarArea.getBotonCancelarOperacion().addActionListener(this);
    this.vistaAgregarArea.getBotonCrearArea().addActionListener(this);
    this.vistaAgregarArea.getBotonRegresarAPrincipal().addActionListener(this);
    this.vistaAgregarArea.getTextoCodigoArea().setEditable(false);
    DAOArea daoArea = new DAOArea();
    ArrayList<Area> listaArea = null;
    int codigo;
    try{
        listaArea = daoArea.consultar("");
    }catch(Exception e){
        e.printStackTrace();
    } 
    if(!listaArea.isEmpty()){
        codigo = listaArea.size() + 1;
    }else{
        codigo =1;
    }
    this.vistaAgregarArea.getTextoCodigoArea().setText(String.valueOf(codigo));
   
}

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vistaAgregarArea.getBotonCrearArea() == evento.getSource()) {
            DAOArea daoArea = new DAOArea();
            this.modeloArea.setNombre(vistaAgregarArea.getTextoNombreArea().getText());
            this.modeloArea.setCodigo(Integer.valueOf(vistaAgregarArea.getTextoCodigoArea().getText()));

            try{
              daoArea.agregar(modeloArea);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                this.vistaAgregarArea.getTextoCodigoArea().setText(String.valueOf(Integer.valueOf(vistaAgregarArea.getTextoCodigoArea().getText())));
            }
            this.vistaAgregarArea.getTextoCodigoArea().setText(null);
            this.vistaAgregarArea.getTextoNombreArea().setText(null);
        }  
        
        if(vistaAgregarArea.getBotonAgregarClasificacion() == evento.getSource()) {
            this.vistaAgregarArea.setEnabled(false);
            Area a = null; 
            vistaAgregarClasificacion vistaClas = new vistaAgregarClasificacion();
            Clasificacion clas = new Clasificacion(0,null,0);
            ControlAgregarClasificacion c = new ControlAgregarClasificacion(a,clas,vistaClas,this.vistaAgregarArea,this.v);
            vistaClas.setVisible(true);
        }
        
        if((vistaAgregarArea.getBotonCancelarOperacion() == evento.getSource())
                ||vistaAgregarArea.getBotonRegresarAPrincipal() == evento.getSource()) {
            this.v.setEnabled(true);
            vistaAgregarArea.dispose();
        }
    }
    

    
}