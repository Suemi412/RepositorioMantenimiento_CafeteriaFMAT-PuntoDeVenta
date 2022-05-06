/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import DAO.DAOProducto;
import Modelo.Caja.Carrito;
import Modelo.Inventario.Producto;
import Modelo.Usuario.Cliente;
import Vista.pantallaPrincipal;
import Vista.vistaAgregarProductosACarrito;
import Vista.vistaCarrito;
import Vista.vistaSeleccionarCliente;
import jssc.SerialPortException;

/**
 *
 * @author jhont
 */
public class ControlCarrito implements ActionListener {
    private Carrito modeloCarrito;
    private vistaCarrito vistaCarrito;
    private pantallaPrincipal v;

    public ControlCarrito(Carrito modeloCarrito, vistaCarrito vistaCarrito, pantallaPrincipal v) {
        this.modeloCarrito = modeloCarrito;
        this.vistaCarrito = vistaCarrito;
        this.v = v;
        this.vistaCarrito.setAlwaysOnTop(true);
        this.vistaCarrito.requestFocus();

        this.vistaCarrito.getAgregarProducto().addActionListener(this);
        this.vistaCarrito.getQuitarProducto().addActionListener(this);
        this.vistaCarrito.getRealizarVenta().addActionListener(this);
        this.vistaCarrito.getLimiarCarrito().addActionListener(this);
        this.vistaCarrito.getCancelarCompra().addActionListener(this);
        this.vistaCarrito.gettxtTotal().setEditable(false);
        this.vistaCarrito.gettxtTotal().setText("0");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vistaCarrito.getAgregarProducto() == e.getSource()) {
            this.vistaCarrito.setEnabled(false);
            Producto producto = new Producto(0, null, 0.0, 0.0, 0, 0, 0, "");
            vistaAgregarProductosACarrito vAPC = new vistaAgregarProductosACarrito();
            vAPC.setVisible(true);
            try {
                ControlAgregaCarro controlAddCar = new ControlAgregaCarro(producto, vAPC, this.vistaCarrito);
            } catch (SerialPortException ex) {
                Logger.getLogger(ControlCarrito.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (vistaCarrito.getQuitarProducto() == e.getSource()) {
            int fila = vistaCarrito.getTablaCarro().getSelectedRow();
            if (fila >= 0) {
                quitarProducto(fila);
            } else {
                JOptionPane.showMessageDialog(this.vistaCarrito, "Favor de seleccionar fila", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if (vistaCarrito.getRealizarVenta() == e.getSource()) {
            if (vistaCarrito.getTablaCarro().getRowCount() > 0) {
                this.vistaCarrito.setEnabled(false);
                Cliente cliente = new Cliente(0, null, 0);
                vistaSeleccionarCliente vSC = new vistaSeleccionarCliente();
                vSC.setVisible(true);
                ControlSelectCliente controlSelectCliente = new ControlSelectCliente(cliente, vSC, this.vistaCarrito,
                        this.v);
            } else {
                JOptionPane.showMessageDialog(this.vistaCarrito, "Carrito vacío", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (vistaCarrito.getLimiarCarrito() == e.getSource()) {
            limiarCarrito();
        }

        if (vistaCarrito.getCancelarCompra() == e.getSource()) {
            limiarCarrito();
            this.v.setEnabled(true);
            vistaCarrito.dispose();
        }
    }

    private void limiarCarrito() {
        if (vistaCarrito.getTablaCarro().getRowCount() > 0) {
            int fila = vistaCarrito.getTablaCarro().getRowCount();
            for (int i = fila - 1; i >= 0; i--) {
                quitarProducto(i);
            }
        } else {
            JOptionPane.showMessageDialog(this.vistaCarrito, "Carrito vacío", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void quitarProducto(int i) {
        Double total = Double.parseDouble(vistaCarrito.gettxtTotal().getText())
                - Double.parseDouble(String.valueOf(vistaCarrito.modelo.getValueAt(i, 3)));
        vistaCarrito.gettxtTotal().setText(String.valueOf(total));
        try {
            String condicion;
            condicion = " codigo = " + vistaCarrito.modelo.getValueAt(i, 0);
            DAOProducto daoProducto = new DAOProducto();
            ArrayList<Producto> listaProductos = new ArrayList<Producto>();
            listaProductos = daoProducto.consultar(condicion);
            Producto modeloProducto = listaProductos.get(0);
            // modeloProducto= listaProductos.get(0);
            modeloProducto.setExistencia(Integer.valueOf(modeloProducto.getExistencia())
                    + Integer.valueOf(String.valueOf(vistaCarrito.modelo.getValueAt(i, 2))));
            daoProducto.modificar(modeloProducto, condicion);// mover
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this.vistaCarrito, "Error en la base de datos", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            vistaCarrito.modelo.removeRow(i);
        }
    }
}
