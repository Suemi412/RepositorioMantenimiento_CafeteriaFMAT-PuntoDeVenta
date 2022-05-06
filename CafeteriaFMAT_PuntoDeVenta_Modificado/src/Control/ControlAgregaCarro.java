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

import javax.swing.JOptionPane;

import DAO.DAOProducto;
import Modelo.Inventario.Producto;
import Vista.vistaAgregarProductosACarrito;
import Vista.vistaCarrito;
import jssc.SerialPortException;

/**
 *
 * @author jhont
 */
public class ControlAgregaCarro implements ActionListener {

    private Producto modeloProducto;
    private vistaAgregarProductosACarrito vAPC;
    private vistaCarrito vC;

    public ControlAgregaCarro(Producto modeloProducto, vistaAgregarProductosACarrito vAPC, vistaCarrito vC)
            throws SerialPortException {
        this.modeloProducto = modeloProducto;
        this.vAPC = vAPC;
        this.vC = vC;
        this.vAPC.setAlwaysOnTop(true);
        this.vAPC.requestFocus();

        this.vAPC.getBotonBuscar().addActionListener(this);
        this.vAPC.getBotonAgregarACarrito().addActionListener(this);
        this.vAPC.getBotonCancelar().addActionListener(this);
        this.vAPC.getTxtPrecio().setEditable(false);
        this.vAPC.getTxtExistencia().setEditable(false);
        this.vAPC.getBoxNombreProducto().addActionListener(this);
        this.vAPC.getTextoDescripcionProducto().setEditable(false);
        buscarProductos(this.vAPC);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (this.vAPC.getBotonBuscar() == evento.getSource()) {
            try {
                int temp = Integer.valueOf(this.vAPC.getTxtCodigo().getText());
                // Manejo de base de datos
                String condicion;
                condicion = " codigo = " + temp;
                DAOProducto daoProducto = new DAOProducto();
                ArrayList<Producto> listaProductos = new ArrayList<Producto>();
                try {
                    listaProductos = daoProducto.consultar(condicion);
                    Producto producto = new Producto();
                    producto = listaProductos.get(0);

                    this.vAPC.getBoxNombreProducto()
                            .setSelectedItem(producto.getCodigo() + ": " + producto.getNombre());
                    this.vAPC.getTxtPrecio().setText(String.valueOf(producto.getPrecioDeVenta()));
                    this.vAPC.getTxtExistencia().setText(String.valueOf(producto.getExistencia()));
                    this.vAPC.getTextoDescripcionProducto().setText(producto.getDescripcion());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(this.vAPC, "Valor de codigo invalido", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if (this.vAPC.getBoxNombreProducto() == evento.getSource()) {
            try {
                if (this.vAPC.getBoxNombreProducto().getSelectedIndex() > 0) {
                    String productoSeleccionado = this.vAPC.getBoxNombreProducto().getSelectedItem().toString()
                            .split(":")[0];
                    int temp = Integer.valueOf(productoSeleccionado);
                    // Manejo de base de datos
                    String condicion;
                    condicion = " codigo = " + temp;
                    DAOProducto daoProducto = new DAOProducto();
                    ArrayList<Producto> listaProductos = new ArrayList<Producto>();
                    try {
                        listaProductos = daoProducto.consultar(condicion);
                        Producto producto = new Producto();
                        producto = listaProductos.get(0);

                        this.vAPC.getTxtCodigo().setText(String.valueOf(producto.getCodigo()));
                        this.vAPC.getTxtPrecio().setText(String.valueOf(producto.getPrecioDeVenta()));
                        this.vAPC.getTxtExistencia().setText(String.valueOf(producto.getExistencia()));
                        this.vAPC.getTextoDescripcionProducto().setText(producto.getDescripcion());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(this.vAPC, "Valor de codigo invalido", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if (this.vAPC.getBotonAgregarACarrito() == evento.getSource()) {
            if ((Integer.valueOf(this.vAPC.getTxtExistencia().getText())
                    - Integer.valueOf(this.vAPC.getTxtCantidad().getText())) >= 0) {
                try {
                    String[] info = new String[4];
                    info[0] = vAPC.getTxtCodigo().getText();
                    info[1] = vAPC.getBoxNombreProducto().getSelectedItem().toString();
                    info[2] = vAPC.getTxtCantidad().getText();
                    double precio = Double.valueOf(vAPC.getTxtPrecio().getText())
                            * Integer.valueOf(vAPC.getTxtCantidad().getText());
                    info[3] = String.valueOf(precio);
                    vC.modelo.addRow(info);
                    double total = Double.valueOf(vC.gettxtTotal().getText()) + precio;
                    vC.gettxtTotal().setText(String.valueOf(total));
                    try {
                        String condicion;
                        condicion = " codigo = " + Integer.valueOf(this.vAPC.getTxtCodigo().getText());
                        DAOProducto daoProducto = new DAOProducto();
                        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
                        listaProductos = daoProducto.consultar(condicion);
                        modeloProducto = listaProductos.get(0);
                        modeloProducto.setExistencia(Integer.valueOf(this.vAPC.getTxtExistencia().getText())
                                - Integer.valueOf(this.vAPC.getTxtCantidad().getText()));
                        daoProducto.modificar(modeloProducto, condicion);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this.vAPC, "Error en la base de datos", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    this.vC.setEnabled(true);
                    this.vAPC.dispose();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this.vAPC, "Datos incompletos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this.vAPC, "Existencia insuficiente", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
        if (this.vAPC.getBotonCancelar() == evento.getSource()) {
            this.vC.setEnabled(true);
            this.vAPC.dispose();
        }
    }

    private void buscarProductos(vistaAgregarProductosACarrito Vista) {

        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        DAOProducto daoProducto = new DAOProducto();
        try {
            listaProductos = daoProducto.consultar(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Producto producto = null;
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.size() > i) {
                producto = listaProductos.get(i);
                Vista.getBoxNombreProducto().addItem((producto.getCodigo() + ": " + producto.getNombre()));
            }
        }
    }

}
