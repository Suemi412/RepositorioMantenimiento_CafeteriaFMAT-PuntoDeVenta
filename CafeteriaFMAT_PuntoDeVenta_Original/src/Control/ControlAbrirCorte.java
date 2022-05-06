/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;
import Modelo.Caja.Dia;
import Vista.pantallaPrincipal;
import Vista.vistaAbrirCorte;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
/**
 *
 * @author danit
 */
public class ControlAbrirCorte implements ActionListener {
    private static  Dia modeloDia;
    private vistaAbrirCorte vistaAbrirCorte;
    private pantallaPrincipal v;
    public ControlAbrirCorte(Dia modeloDia,pantallaPrincipal v,vistaAbrirCorte vistaActual){
        this.v=v;
        this.vistaAbrirCorte = vistaActual;
        this.vistaAbrirCorte.requestFocus();
        this.vistaAbrirCorte.setAlwaysOnTop(true);
        this.modeloDia=modeloDia;
        this.vistaAbrirCorte.getBotonCancelar().addActionListener(this);
        this.vistaAbrirCorte.getBotonAbrirCorte().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(vistaAbrirCorte.getBotonCancelar()==evento.getSource()){
            this.v.setEnabled(true);
            vistaAbrirCorte.dispose();
        }
        if(vistaAbrirCorte.getBotonAbrirCorte()==evento.getSource()){
            
            try{
                int dia=Integer.valueOf(this.vistaAbrirCorte.getTextoDia().getText());
                int mes=Integer.valueOf(this.vistaAbrirCorte.getTextoMes().getText());
                int anio=Integer.valueOf(this.vistaAbrirCorte.getTextoAnio().getText());
                
                if(dia>31){
                    JOptionPane.showMessageDialog(this.vistaAbrirCorte, "Día fuera de rango", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    if(mes>12){
                        JOptionPane.showMessageDialog(this.vistaAbrirCorte, "Mes fuera de rango", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        if(anio<1000 || anio>3000){
                            JOptionPane.showMessageDialog(this.vistaAbrirCorte, "Año fuera de rango", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            modeloDia.setDia(this.vistaAbrirCorte.getTextoDia().getText());
                            modeloDia.setMes(this.vistaAbrirCorte.getTextoMes().getText());
                            modeloDia.setAnio(this.vistaAbrirCorte.getTextoAnio().getText());
                            this.v.setEnabled(true);
                            this.v.getBotonAbrirCorte().setEnabled(false);
                            this.v.getMenuAbrirCorte().setEnabled(false);
                            this.v.getBotonNuevaVenta().setEnabled(true);
                            this.v.getBotonCerrarCorte().setEnabled(true);
                            this.v.getMenuNuevaVenta().setEnabled(true);
                            this.v.getMenuCerrarCorte().setEnabled(true);
                            this.v.getBotonNuevoCliente().setEnabled(true);
                            this.v.getMenuAgregarCliente().setEnabled(true);
                            this.v.getMenuBuscarCliente().setEnabled(true);
                            this.v.getBotonBusquedaCliente().setEnabled(true);
                            this.v.getBotonReporteRapido().setEnabled(true);
                            vistaAbrirCorte.dispose();
                        }
                    }
                }
                
            }
            catch(NumberFormatException error){
                JOptionPane.showMessageDialog(this.vistaAbrirCorte, "Fecha invalida", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }

    public static Dia getModeloDia() {
        return modeloDia;
    }
    
    
}