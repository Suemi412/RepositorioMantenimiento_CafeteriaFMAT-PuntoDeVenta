/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.DAOCliente;
import DAO.DAOReporte;
import DAO.Reporte;
import Modelo.Usuario.Cliente;
import Vista.pantallaPrincipal;
import Vista.vistaCarrito;
import Vista.vistaSeleccionarCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jhont
 */
public class ControlSelectCliente implements ActionListener{
    private Cliente modeloCliente;
    private vistaSeleccionarCliente vSC;
    private vistaCarrito vC;
    private pantallaPrincipal vP;

    public ControlSelectCliente(Cliente modeloCliente, vistaSeleccionarCliente vistaSelectCliente, vistaCarrito vC, pantallaPrincipal vP) {
        this.modeloCliente = modeloCliente;
        this.vSC = vistaSelectCliente;
        this.vC = vC;
        this.vP=vP;
        this.vSC.setAlwaysOnTop(true);
        this.vSC.requestFocus();
        
        this.vSC.getBotonBuscar().addActionListener(this);
        this.vSC.getBotonCobrarACuenta().addActionListener(this);
        this.vSC.getBotonCobrarSinCuenta().addActionListener(this);
        this.vSC.getBotonRegresar().addActionListener(this);
        this.vSC.getTxtPorPagar().setEditable(false);
        this.vSC.getTxtNombre().setEditable(false);
        this.vSC.getTxtSaldo().setEditable(false);
        this.vSC.getTxtPorPagar().setText(vC.gettxtTotal().getText());
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(this.vSC.getBotonBuscar()==evento.getSource()){
            ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
            int  claveCliente;
            String condicion;

            claveCliente = Integer.parseInt(this.vSC.getTxtCuenta().getText());
            condicion = " matricula = " + claveCliente;
            DAOCliente daoCliente = new DAOCliente();
            try{
              listaClientes = daoCliente.consultar(condicion);
              Cliente cliente = listaClientes.get(0);
            this.vSC.getTxtNombre().setText(cliente.getNombre());
            this.vSC.getTxtSaldo().setText(String.valueOf(cliente.getCuenta().getSaldo()));
            }catch(Exception e){
                    JOptionPane.showMessageDialog(this.vSC, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(this.vSC.getBotonCobrarACuenta()==evento.getSource()){
            try{
                String fecha = ControlAbrirCorte.getModeloDia().getDia()+"-"+ControlAbrirCorte.getModeloDia().getMes()
                    +"-"+ControlAbrirCorte.getModeloDia().getAnio();
                System.out.println(fecha);
                double saldo;
                ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
                int  claveCliente;
                String condicion;
                claveCliente = Integer.parseInt(this.vSC.getTxtCuenta().getText());
                condicion = " matricula = " + claveCliente;
                DAOCliente daoCliente = new DAOCliente();
                listaClientes = daoCliente.consultar(condicion);
                modeloCliente = listaClientes.get(0);

                saldo=Double.valueOf(vSC.getTxtSaldo().getText()) - Double.valueOf(vSC.getTxtPorPagar().getText());
                if(saldo>=0){
                    double nuevoSaldo=saldo;
                    DAOReporte daoReporte = new DAOReporte();
                    //Manejo de base de datos
                    modeloCliente.getCuenta().setSaldo(nuevoSaldo);
                    daoCliente.modificar(modeloCliente, condicion);
                    int i=0;
                    while(i<vC.getTablaCarro().getRowCount()){
                        
                        //manda el producto de cada fila en los parametros faltantes
                        int codigo=Integer.parseInt(String.valueOf(this.vC.getTablaCarro().getValueAt(i, 0)));
                        String nombre=String.valueOf(this.vC.getTablaCarro().getValueAt(i, 1));
                        int cantidad=Integer.parseInt(String.valueOf(this.vC.getTablaCarro().getValueAt(i, 2)));
                        double subtotal= Double.valueOf(String.valueOf(this.vC.getTablaCarro().getValueAt(i, 3)));
                        daoReporte.agregar(new Reporte(codigo,nombre,cantidad,subtotal,ControlAbrirCorte.getModeloDia().getDia(), ControlAbrirCorte.getModeloDia().getMes(),ControlAbrirCorte.getModeloDia().getAnio(),claveCliente));
                        i++;
                    }
                    JOptionPane.showMessageDialog(this.vSC, "Venta cobrada - $"+this.vSC.getTxtPorPagar().getText()+"\nSaldo en cuenta: $"+ nuevoSaldo);
                    this.vC.setEnabled(true);
                    this.vP.setEnabled(true);
                    this.vC.dispose();
                    this.vSC.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(vSC, "Saldo insuficiente", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(vSC, "Valor de cuenta invalido", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vSC, "Error en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(this.vSC.getBotonCobrarSinCuenta()==evento.getSource()){
            JOptionPane.showMessageDialog(this.vSC, "Cobre $"+this.vSC.getTxtPorPagar().getText());
            String fecha = ControlAbrirCorte.getModeloDia().getDia()+"-"+ControlAbrirCorte.getModeloDia().getMes()
                    +"-"+ControlAbrirCorte.getModeloDia().getAnio();
            System.out.println(fecha);
            try{
                int i=0;
                DAOReporte daoReporte = new DAOReporte();
                   while(i<vC.getTablaCarro().getRowCount()){
                       
                        //manda el producto de cada fila en los parametros faltantes
                        int codigo=Integer.valueOf(String.valueOf(this.vC.getTablaCarro().getValueAt(i, 0)));
                        String nombre=(String) this.vC.getTablaCarro().getValueAt(i, 1);
                        int cantidad=Integer.parseInt(String.valueOf(this.vC.getTablaCarro().getValueAt(i, 2)));
                        double subtotal= Double.valueOf(String.valueOf(this.vC.getTablaCarro().getValueAt(i, 3)));
                        daoReporte.agregar(new Reporte(codigo,nombre,cantidad,subtotal,ControlAbrirCorte.getModeloDia().getDia(), ControlAbrirCorte.getModeloDia().getMes(),ControlAbrirCorte.getModeloDia().getAnio(),0));
                        i++;
                    }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(vSC, "Error en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
            this.vC.setEnabled(true);
            this.vP.setEnabled(true);
            this.vC.dispose();
            this.vSC.dispose();
        }
        if(this.vSC.getBotonRegresar()==evento.getSource()){
            this.vC.setEnabled(true);
            this.vSC.dispose();
        }
    }
    
}
