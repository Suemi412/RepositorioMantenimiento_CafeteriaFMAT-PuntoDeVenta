/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import DAO.DAOCliente;
import DAO.DAOReporteCliente;
import DAO.ReporteCliente;
import Modelo.Usuario.Cliente;
import Modelo.Usuario.Cuenta;
import Vista.vistaActualizarSaldo;
import Vista.vistaClientes;

/**
 *
 * @author jhont
 */
public class ControlActualizarSaldo implements ActionListener {
  private Cliente modeloCliente;
  private Cuenta modeloCuenta;
  private vistaActualizarSaldo vistaActualizarSaldos;
  private vistaClientes v;

  public ControlActualizarSaldo(Cliente modeloCliente, vistaActualizarSaldo vistaActualizarSaldo, vistaClientes v) {
    this.modeloCliente = modeloCliente;
    this.v = v;
    this.vistaActualizarSaldos = vistaActualizarSaldo;
    this.vistaActualizarSaldos.requestFocus();
    this.vistaActualizarSaldos.setAlwaysOnTop(true);
    this.vistaActualizarSaldos.getBotonAgregarSaldo().addActionListener(this);
    this.vistaActualizarSaldos.getBotonRetirarSaldo().addActionListener(this);
    this.vistaActualizarSaldos.getBotonVaciarCuenta().addActionListener(this);
    this.vistaActualizarSaldos.getBotonRegresar().addActionListener(this);
    this.vistaActualizarSaldos.getTextoSaldoActual().setText(String.valueOf(modeloCliente.getCuenta().getSaldo()));
    this.vistaActualizarSaldos.getTextoSaldoActual().setEditable(false);
  }

  @Override
  public void actionPerformed(ActionEvent evento) {
    // Actualizar Saldo
    if (vistaActualizarSaldos.getBotonAgregarSaldo() == evento.getSource()) {
      double nuevoSaldo;
      String condicion;
      nuevoSaldo = Double.valueOf(vistaActualizarSaldos.getTextoSaldoActual().getText());

      modeloCliente.getCuenta().setSaldo(nuevoSaldo);
      condicion = " matricula = " + String.valueOf(modeloCliente.getMatricula());
      DAOCliente daoCliente = new DAOCliente();
      try {
        daoCliente.modificar(modeloCliente, condicion);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    // AgregarrSaldo
    if (vistaActualizarSaldos.getBotonAgregarSaldo() == evento.getSource()) {
      double saldoActual;
      double saldoAgregado;
      String condicion;

      saldoActual = modeloCliente.getCuenta().getSaldo();
      saldoAgregado = Double.valueOf(vistaActualizarSaldos.getTextoModificacion().getText());
      condicion = " matricula = " + String.valueOf(modeloCliente.getMatricula());
      modeloCliente.getCuenta().setSaldo(saldoActual + saldoAgregado);

      DAOCliente daoCliente = new DAOCliente();
      DAOReporteCliente daoReporteCliente = new DAOReporteCliente();

      try {
        daoCliente.modificar(modeloCliente, condicion);
        daoReporteCliente.agregar(new ReporteCliente(modeloCliente.getMatricula(),
            ControlAbrirCorte.getModeloDia().getDia(), ControlAbrirCorte.getModeloDia().getMes(),
            ControlAbrirCorte.getModeloDia().getAnio(), "Depósito", saldoAgregado, saldoActual + saldoAgregado));
      } catch (Exception e) {
        e.printStackTrace();
      }
      vistaActualizarSaldos.getTextoSaldoActual().setText(String.valueOf(modeloCliente.getCuenta().getSaldo()));
      JOptionPane.showMessageDialog(this.vistaActualizarSaldos, "Saldo Modificado");
    }

    // retirarSaldo
    if (vistaActualizarSaldos.getBotonRetirarSaldo() == evento.getSource()) {
      double saldoActual;
      double saldoRetirado;
      String condicion;

      saldoActual = modeloCliente.getCuenta().getSaldo();
      saldoRetirado = Double.valueOf(vistaActualizarSaldos.getTextoModificacion().getText());
      if (saldoActual < saldoRetirado) {
        JOptionPane.showMessageDialog(this.vistaActualizarSaldos, "Fondos Insuficientes", "Error",
            JOptionPane.ERROR_MESSAGE);
      } else {
        condicion = " matricula = " + String.valueOf(modeloCliente.getMatricula());
        modeloCliente.getCuenta().setSaldo(saldoActual - saldoRetirado);

        DAOCliente daoCliente = new DAOCliente();
        DAOReporteCliente daoReporteCliente = new DAOReporteCliente();
        try {
          daoCliente.modificar(modeloCliente, condicion);
          daoReporteCliente.agregar(new ReporteCliente(modeloCliente.getMatricula(),
              ControlAbrirCorte.getModeloDia().getDia(), ControlAbrirCorte.getModeloDia().getMes(),
              ControlAbrirCorte.getModeloDia().getAnio(), "Retiro", (-1) * saldoRetirado, saldoActual - saldoRetirado));
        } catch (Exception e) {
          e.printStackTrace();
        }
        vistaActualizarSaldos.getTextoSaldoActual().setText(String.valueOf(modeloCliente.getCuenta().getSaldo()));
        JOptionPane.showMessageDialog(this.vistaActualizarSaldos,
            "Saldo Modificado, Entregar " + saldoRetirado + " al cliente.");
      }
    }
    // VaciarCuenta
    if (vistaActualizarSaldos.getBotonVaciarCuenta() == evento.getSource()) {
      String condicion;
      double saldoPorDevolver;
      condicion = " matricula = " + String.valueOf(modeloCliente.getMatricula());
      saldoPorDevolver = modeloCliente.getCuenta().getSaldo();
      modeloCliente.getCuenta().setSaldo(0.0);

      DAOCliente daoCliente = new DAOCliente();
      DAOReporteCliente daoReporteCliente = new DAOReporteCliente();
      try {
        daoCliente.modificar(modeloCliente, condicion);
        daoReporteCliente.agregar(new ReporteCliente(modeloCliente.getMatricula(),
            ControlAbrirCorte.getModeloDia().getDia(), ControlAbrirCorte.getModeloDia().getMes(),
            ControlAbrirCorte.getModeloDia().getAnio(), "Retiro", (-1) * saldoPorDevolver, 0.0));
      } catch (Exception e) {
        e.printStackTrace();
      }
      vistaActualizarSaldos.getTextoSaldoActual().setText(String.valueOf(modeloCliente.getCuenta().getSaldo()));
      JOptionPane.showMessageDialog(this.vistaActualizarSaldos, "Entregar " + saldoPorDevolver + " al cliente.");
    }

    if (vistaActualizarSaldos.getBotonRegresar() == evento.getSource()) {
      this.v.setEnabled(true);
      vistaActualizarSaldos.dispose();
    }
  }
}
