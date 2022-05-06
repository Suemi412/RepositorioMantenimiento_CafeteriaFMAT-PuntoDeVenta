/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.DAOReporte;
import Modelo.Caja.*;
import Modelo.Inventario.*;
import Modelo.Usuario.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jhont
 */
public class ControlPantallaPrincipal implements ActionListener{
    private pantallaPrincipal vistaPrincipal;
    private Dia mD=new Dia();
    public ControlPantallaPrincipal(pantallaPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        this.vistaPrincipal.getBotonAbrirCorte().addActionListener(this);
        this.vistaPrincipal.getBotonBuscarProducto().addActionListener(this);
        this.vistaPrincipal.getBotonBusquedaCliente().addActionListener(this);
        this.vistaPrincipal.getBotonCerrarCorte().addActionListener(this);
        this.vistaPrincipal.getBotonNuevaVenta().addActionListener(this);
        this.vistaPrincipal.getBotonReporteRapido().addActionListener(this);
        this.vistaPrincipal.getBotonNuevoCliente().addActionListener(this);
        this.vistaPrincipal.getMenuAgregarArea().addActionListener(this);
        this.vistaPrincipal.getMenuCerrarCorte().addActionListener(this);
        this.vistaPrincipal.getMenuAgregarClasificacion().addActionListener(this);
        this.vistaPrincipal.getMenuAgregarCliente().addActionListener(this);
        this.vistaPrincipal.getMenuAgregarProducto().addActionListener(this);
        this.vistaPrincipal.getMenuBuscarCliente().addActionListener(this);
        this.vistaPrincipal.getMenuBuscarProductoF().addActionListener(this);
        this.vistaPrincipal.getMenuNuevaVenta().addActionListener(this);
        this.vistaPrincipal.getMenuAbrirCorte().addActionListener(this);
        this.vistaPrincipal.getMenuEliminarProducto().addActionListener(this);
        this.vistaPrincipal.getMenuModificarExistencia().addActionListener(this);
        this.vistaPrincipal.getMenuReporteCliente().addActionListener(this);
        this.vistaPrincipal.getMenuReporteDeExistencias().addActionListener(this);
        this.vistaPrincipal.getMenuReporteGeneral().addActionListener(this);
        this.vistaPrincipal.getMenuReportePorDia().addActionListener(this);
        this.vistaPrincipal.getMenuReportePorCliente().addActionListener(this);
        this.vistaPrincipal.getBotonNuevaVenta().setEnabled(false);
        this.vistaPrincipal.getBotonCerrarCorte().setEnabled(false);
        this.vistaPrincipal.getMenuNuevaVenta().setEnabled(false);
        this.vistaPrincipal.getMenuCerrarCorte().setEnabled(false);
        this.vistaPrincipal.getBotonNuevoCliente().setEnabled(false);
        this.vistaPrincipal.getMenuAgregarCliente().setEnabled(false);
        this.vistaPrincipal.getMenuBuscarCliente().setEnabled(false);
        this.vistaPrincipal.getBotonBusquedaCliente().setEnabled(false);
        this.vistaPrincipal.getBotonReporteRapido().setEnabled(false);
        
        this.vistaPrincipal.setEnabled(false);
        vistaAbrirCorte vAC=new vistaAbrirCorte();
        ControlAbrirCorte v =new ControlAbrirCorte(mD,this.vistaPrincipal,vAC);
        vAC.setVisible(true);
        
    } 

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vistaPrincipal.getMenuAgregarCliente() == evento.getSource()|| 
                vistaPrincipal.getBotonNuevoCliente() == evento.getSource()||
                vistaPrincipal.getBotonBusquedaCliente()==evento.getSource()||
                vistaPrincipal.getMenuBuscarCliente()==evento.getSource()|| 
                vistaPrincipal.getMenuReporteCliente()==evento.getSource()){
            this.vistaPrincipal.setEnabled(false);
            vistaClientes v = new vistaClientes();
            Cliente client = new Cliente();
            ControlCliente c = new ControlCliente(client,v,this.vistaPrincipal);
            v.setVisible(true);
        }
        
        if(vistaPrincipal.getMenuAgregarArea() == evento.getSource()){
            this.vistaPrincipal.setEnabled(false);
            vistaAgregarArea v = new vistaAgregarArea();
            Area a = new Area(0,null);
            ControlAgregarArea c = new ControlAgregarArea(a,v,this.vistaPrincipal);
            v.setVisible(true);
        }
        
        if(vistaPrincipal.getMenuAgregarClasificacion() == evento.getSource()){
            this.vistaPrincipal.setEnabled(false);
            vistaAgregarClasificacion v = new vistaAgregarClasificacion();
            Clasificacion clas = new Clasificacion(0,null,0);
            Area a = new Area(0,null);
            ControlAgregarClasificacion c = new ControlAgregarClasificacion(a,clas,v,this.vistaPrincipal);
            v.setVisible(true);
        }
        
        if(vistaPrincipal.getMenuReporteGeneral()== evento.getSource()){
            try {
                DAOReporte report = new DAOReporte();
                Connection conn=report.getConeccion();
                
                JasperReport reporte= null;
                String path="src\\Reportes\\VentasGeneral2.jasper";
                
                reporte=(JasperReport) JRLoader.loadObjectFromFile(path);
                
                JasperPrint jprint = JasperFillManager.fillReport(reporte, null, conn);
                
                JasperViewer view= new JasperViewer(jprint, false);
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
                view.setVisible(true);
                
            } catch (JRException ex) {
                Logger.getLogger(ControlPantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(vistaPrincipal.getMenuReportePorDia()== evento.getSource()){
            try {
                DAOReporte report = new DAOReporte();
                Connection conn=report.getConeccion();
                
                JasperReport reporte= null;
                String path="src\\Reportes\\VentasPorFecha.jasper";
                
                Map parametro= new HashMap();
                String fecha=JOptionPane.showInputDialog("Ingrese la fecha que desea revisar");
                parametro.put("Fecha", fecha);
                
                reporte=(JasperReport) JRLoader.loadObjectFromFile(path);
                
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, conn);
                
                JasperViewer view= new JasperViewer(jprint, false);
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
                view.setVisible(true);
                
            } catch (JRException ex) {
                Logger.getLogger(ControlPantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(vistaPrincipal.getBotonReporteRapido()==evento.getSource()){
            try {
                DAOReporte report = new DAOReporte();
                Connection conn=report.getConeccion();
                
                JasperReport reporte= null;
                String path="src\\Reportes\\VentasPorFecha.jasper";
                
                Map parametro= new HashMap();
                String fecha=this.mD.getDia()+"-"+this.mD.getMes()+"-"+this.mD.getAnio();
                System.out.println(fecha);
                parametro.put("Fecha", fecha);
                
                reporte=(JasperReport) JRLoader.loadObjectFromFile(path);
                
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, conn);
                
                JasperViewer view= new JasperViewer(jprint, false);
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
                view.setVisible(true);
                
            } catch (JRException ex) {
                Logger.getLogger(ControlPantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(vistaPrincipal.getMenuReportePorCliente()== evento.getSource()){
             try {
                DAOReporte report = new DAOReporte();
                Connection conn=report.getConeccion();
                
                JasperReport reporte= null;
                String path="src\\Reportes\\VentasPorCuenta2.jasper";
                
                Map parametro= new HashMap();
                int cuenta=Integer.valueOf(JOptionPane.showInputDialog("Ingrese la cuenta que desea revisar"));
                parametro.put("Cuenta", cuenta);
                
                reporte=(JasperReport) JRLoader.loadObjectFromFile(path);
                
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, conn);
                
                JasperViewer view= new JasperViewer(jprint, false);
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
                view.setVisible(true);
                
            } catch (JRException ex) {
                Logger.getLogger(ControlPantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(vistaPrincipal.getMenuReporteDeExistencias()==evento.getSource()){
           try {
                DAOReporte report = new DAOReporte();
                Connection conn=report.getConeccion();
                
                JasperReport reporte= null;
                String path="src\\Reportes\\ReporteInventario.jasper";
                
                reporte=(JasperReport) JRLoader.loadObjectFromFile(path);
                
                JasperPrint jprint = JasperFillManager.fillReport(reporte, null, conn);
                
                JasperViewer view= new JasperViewer(jprint, false);
                view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
                view.setVisible(true);
                
            } catch (JRException ex) {
                Logger.getLogger(ControlPantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
        if(vistaPrincipal.getBotonNuevaVenta()== evento.getSource()|| vistaPrincipal.getMenuNuevaVenta()==evento.getSource()){
            this.vistaPrincipal.setEnabled(false);
            Carrito mC= new Carrito();
            vistaCarrito vC = new vistaCarrito();
            ControlCarrito v= new ControlCarrito(mC,vC,this.vistaPrincipal);
            vC.setVisible(true);
        }
        
        if(vistaPrincipal.getBotonAbrirCorte()==evento.getSource()||vistaPrincipal.getMenuAbrirCorte()==evento.getSource()){
            this.vistaPrincipal.setEnabled(false);
            vistaAbrirCorte vAC=new vistaAbrirCorte();
            ControlAbrirCorte v =new ControlAbrirCorte(mD,this.vistaPrincipal,vAC);
            vAC.setVisible(true);
        }
        
        if(vistaPrincipal.getBotonBuscarProducto() == evento.getSource()||
               vistaPrincipal.getMenuBuscarProductoF()== evento.getSource()||
               vistaPrincipal.getMenuEliminarProducto()==evento.getSource()||
               vistaPrincipal.getMenuModificarExistencia()==evento.getSource()){
            this.vistaPrincipal.setEnabled(false);
            vistaBuscadorInventario v = new vistaBuscadorInventario();
            Producto mP=new Producto(0,null,0.0,0.0,0,0,0);
            ControlBuscarProducto c = new ControlBuscarProducto(mP,this.vistaPrincipal,v);
            v.setVisible(true);
        }
        if(vistaPrincipal.getBotonCerrarCorte()==evento.getSource()|| vistaPrincipal.getMenuCerrarCorte()==evento.getSource()){
            mD.setDia(null);
            mD.setMes(null);
            mD.setAnio(null);
            vistaPrincipal.dispose();
        }
        
        if(vistaPrincipal.getMenuAgregarProducto()==evento.getSource()){
            this.vistaPrincipal.setEnabled(false);
            vistaAgregarProducto v = new vistaAgregarProducto();
            Area a = new Area(0,null);
            Clasificacion clas=new Clasificacion(0,null,0);
            Producto p = new Producto(0,null,0.0,0.0,0,0,0);
            ControlAgregarProducto c = new ControlAgregarProducto(a,clas,p,this.vistaPrincipal,v);
            v.setVisible(true);
            
        }
    }

}