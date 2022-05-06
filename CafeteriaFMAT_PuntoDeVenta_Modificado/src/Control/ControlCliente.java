/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import DAO.DAOCliente;
import DAO.DAOReporteCliente;
import DAO.ReporteCliente;
import Modelo.Caja.Dia;
import Modelo.Usuario.Cliente;
import Modelo.Usuario.Cuenta;
import Vista.pantallaPrincipal;
import Vista.vistaActualizarSaldo;
import Vista.vistaClientes;
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
public class ControlCliente implements ActionListener {
  private Cliente modeloCliente;
  private Cuenta modeloCuenta = new Cuenta();
  private vistaClientes vistaCliente;
  private pantallaPrincipal v;
  private Dia ModeloDia;

  public ControlCliente(Cliente modeloCliente, vistaClientes vistaCliente, pantallaPrincipal v) {
    this.v = v;
    this.modeloCliente = modeloCliente;
    this.vistaCliente = vistaCliente;
    this.vistaCliente.getBotonActualizarSaldo().addActionListener(this);
    this.vistaCliente.getBotonAgregar().addActionListener(this);
    this.vistaCliente.getBotonBuscar().addActionListener(this);
    this.vistaCliente.getBotonRegresoInicio().addActionListener(this);
    this.vistaCliente.getBotonReporte().addActionListener(this);
    this.vistaCliente.getBotonEliminar().addActionListener(this);
    // this.vistaCliente.getTextoFechaIngreso().setText(ModeloDia.getFecha());
  }

  public void actionPerformed(ActionEvent evento) {
    if (vistaCliente.getBotonReporte() == evento.getSource()) {
      try {
        DAOReporteCliente reporteCliente = new DAOReporteCliente();
        Connection conn = reporteCliente.getConeccion();

        JasperReport reporte = null;
        String path = "src\\Reportes\\ReporteCliente.jasper";

        Map parametro = new HashMap();
        int cuenta = Integer.valueOf(vistaCliente.getTextoMatricula().getText());
        parametro.put("Cuenta", cuenta);

        reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

        JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, conn);

        JasperViewer view = new JasperViewer(jprint, false);
        view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        view.setVisible(true);
      } catch (JRException ex) {
        Logger.getLogger(ControlPantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    // Insertar Cliente
    if (vistaCliente.getBotonAgregar() == evento.getSource()) {
      int codigo;
      String nombreCliente;
      String fechaIngreso;
      double saldo;

      codigo = Integer.valueOf(vistaCliente.getTextoMatricula().getText());
      nombreCliente = vistaCliente.getTextoNombre().getText();
      saldo = Double.valueOf(vistaCliente.getTextoSaldo().getText());

      modeloCliente.setMatricula(codigo);
      modeloCliente.setNombre(nombreCliente);
      modeloCuenta.setSaldo(saldo);
      modeloCliente.setCuenta(modeloCuenta);

      DAOCliente daoCliente = new DAOCliente();
      DAOReporteCliente daoReporteCliente = new DAOReporteCliente();
      try {
        daoCliente.agregar(modeloCliente);
        daoReporteCliente.agregar(new ReporteCliente(codigo, ControlAbrirCorte.getModeloDia().getDia(), ControlAbrirCorte.getModeloDia().getMes(),ControlAbrirCorte.getModeloDia().getAnio(), "Depósito", saldo, saldo));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    // Eliminar Cliente
    if (vistaCliente.getBotonEliminar() == evento.getSource()) {
      int claveCliente;
      String condicion;
      claveCliente = Integer.parseInt(vistaCliente.getTextoMatricula().getText());
      condicion = " matricula = " + claveCliente;

      DAOCliente daoCliente = new DAOCliente();
      try {
        daoCliente.eliminar(condicion);
      } catch (Exception e) {
        JOptionPane.showMessageDialog(this.vistaCliente, "Cliente no eliminado", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }

    // Consultar Cliente
    if (vistaCliente.getBotonBuscar() == evento.getSource()) {
      ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
      int claveCliente;
      String condicion;

      claveCliente = Integer.parseInt(vistaCliente.getTextoMatricula().getText());
      condicion = " matricula = " + claveCliente;
      DAOCliente daoCliente = new DAOCliente();
      try {
        listaClientes = daoCliente.consultar(condicion);
        Cliente cliente = listaClientes.get(0);
        vistaCliente.getTextoMatricula().setText(String.valueOf(cliente.getMatricula()));
        vistaCliente.getTextoNombre().setText(cliente.getNombre());
        vistaCliente.getTextoSaldo().setText(String.valueOf(cliente.getCuenta().getSaldo()));
      } catch (Exception e) {
        JOptionPane.showMessageDialog(this.vistaCliente, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
      }

    }
    // Actualizar Saldo
    if (vistaCliente.getBotonActualizarSaldo() == evento.getSource()) {
      ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
      int claveCliente;
      String condicion;
      boolean validacion = true;

      claveCliente = Integer.parseInt(vistaCliente.getTextoMatricula().getText());
      condicion = " matricula = " + claveCliente;
      DAOCliente daoCliente = new DAOCliente();
      try {
        listaClientes = daoCliente.consultar(condicion);
        Cliente cliente = listaClientes.get(0);
        vistaCliente.getTextoMatricula().setText(String.valueOf(cliente.getMatricula()));
        vistaCliente.getTextoNombre().setText(cliente.getNombre());
        vistaCliente.getTextoSaldo().setText(String.valueOf(cliente.getCuenta().getSaldo()));
      } catch (Exception e) {
        validacion = false;
        JOptionPane.showMessageDialog(this.vistaCliente, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
      }
      if (validacion) {
        if (!listaClientes.isEmpty()) {
          modeloCliente = listaClientes.get(0);
          this.vistaCliente.setEnabled(false);
          vistaActualizarSaldo vistaActuSaldo = new vistaActualizarSaldo();
          ControlActualizarSaldo controlActualizacionSaldo = new ControlActualizarSaldo(modeloCliente, vistaActuSaldo,
              this.vistaCliente);
          // controlador.iniciarVista();
          vistaActuSaldo.setVisible(true);
        } else {
          JOptionPane.showMessageDialog(this.vistaCliente, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }

      }

    }

    if (vistaCliente.getBotonRegresoInicio() == evento.getSource()) {
      this.v.setEnabled(true);
      vistaCliente.dispose();
    }

  }
}