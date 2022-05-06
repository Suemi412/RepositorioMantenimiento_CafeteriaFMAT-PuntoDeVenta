/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import javax.swing.JButton;
import javax.swing.JMenuItem;

/**
 *
 * @author jhont
 */
public class pantallaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form pantallaPrincipal
     */
    public pantallaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem16 = new javax.swing.JMenuItem();
        BotonCerrarCorte = new javax.swing.JButton();
        BotonNuevaVenta = new javax.swing.JButton();
        BotonBuscarProducto = new javax.swing.JButton();
        BotonBusquedaCliente = new javax.swing.JButton();
        BotonReporteRapido = new javax.swing.JButton();
        BotonNuevoCliente = new javax.swing.JButton();
        BotonAbrirCorte = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuBuscarProductoF = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        MenuAgregarArea = new javax.swing.JMenuItem();
        MenuAgregarClasificacion = new javax.swing.JMenuItem();
        MenuAgregarProducto = new javax.swing.JMenuItem();
        MenuEliminarProducto = new javax.swing.JMenuItem();
        MenuModificarExistencia = new javax.swing.JMenuItem();
        MenuReporteDeExistencias = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuBuscarCliente = new javax.swing.JMenuItem();
        MenuAgregarCliente = new javax.swing.JMenuItem();
        MenuReporteCliente = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        MenuAbrirCorte = new javax.swing.JMenuItem();
        MenuNuevaVenta = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        MenuReportePorDia = new javax.swing.JMenuItem();
        MenuReportePorCliente = new javax.swing.JMenuItem();
        MenuReporteGeneral = new javax.swing.JMenuItem();
        MenuCerrarCorte = new javax.swing.JMenuItem();

        jMenuItem16.setText("jMenuItem16");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Punto_De_Venta");

        BotonCerrarCorte.setText("Cerrar corte del día");
        BotonCerrarCorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCerrarCorteActionPerformed(evt);
            }
        });

        BotonNuevaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/AgregarACarrito.png"))); // NOI18N
        BotonNuevaVenta.setText("Nueva venta");
        BotonNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonNuevaVentaActionPerformed(evt);
            }
        });

        BotonBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Busqueda.png"))); // NOI18N
        BotonBuscarProducto.setText("Busqueda de productos");
        BotonBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBuscarProductoActionPerformed(evt);
            }
        });

        BotonBusquedaCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Busqueda.png"))); // NOI18N
        BotonBusquedaCliente.setText("Busqueda de clientes");

        BotonReporteRapido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Reportes.png"))); // NOI18N
        BotonReporteRapido.setText("Reporte rápido");

        BotonNuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/AgregarPersona.png"))); // NOI18N
        BotonNuevoCliente.setText("  Nuevo cliente");

        BotonAbrirCorte.setText("Abrir corte");

        jMenu1.setText("Productos");

        MenuBuscarProductoF.setAccelerator(
                javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        MenuBuscarProductoF.setText("Buscar producto");
        jMenu1.add(MenuBuscarProductoF);

        jMenu5.setText("Agregar");

        MenuAgregarArea.setAccelerator(
                javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK));
        MenuAgregarArea.setText("Agregar área");
        jMenu5.add(MenuAgregarArea);

        MenuAgregarClasificacion.setAccelerator(
                javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK));
        MenuAgregarClasificacion.setText("Agregar clasificación");
        MenuAgregarClasificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAgregarClasificacionActionPerformed(evt);
            }
        });
        jMenu5.add(MenuAgregarClasificacion);

        MenuAgregarProducto.setAccelerator(
                javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK));
        MenuAgregarProducto.setText("Agregar producto");
        jMenu5.add(MenuAgregarProducto);

        jMenu1.add(jMenu5);

        MenuEliminarProducto.setText("Eliminar producto");
        jMenu1.add(MenuEliminarProducto);

        MenuModificarExistencia.setAccelerator(
                javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.SHIFT_MASK));
        MenuModificarExistencia.setText("Modificar existencias");
        jMenu1.add(MenuModificarExistencia);

        MenuReporteDeExistencias.setAccelerator(
                javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        MenuReporteDeExistencias.setText("Reporte de existencias");
        jMenu1.add(MenuReporteDeExistencias);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Clientes");

        MenuBuscarCliente.setText("Buscar cliente");
        jMenu2.add(MenuBuscarCliente);

        MenuAgregarCliente.setText("Agregar cliente");
        MenuAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAgregarClienteActionPerformed(evt);
            }
        });
        jMenu2.add(MenuAgregarCliente);

        MenuReporteCliente.setText("Reporte de clientes");
        MenuReporteCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuReporteClienteActionPerformed(evt);
            }
        });
        jMenu2.add(MenuReporteCliente);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Ventas");

        MenuAbrirCorte.setText("Abrir corte del día");
        jMenu3.add(MenuAbrirCorte);

        MenuNuevaVenta.setText("Nueva venta");
        jMenu3.add(MenuNuevaVenta);

        jMenu7.setText("Reporte de ventas");

        MenuReportePorDia.setText("Por día");
        MenuReportePorDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuReportePorDiaActionPerformed(evt);
            }
        });
        jMenu7.add(MenuReportePorDia);

        MenuReportePorCliente.setText("Por clientes");
        MenuReportePorCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuReportePorClienteActionPerformed(evt);
            }
        });
        jMenu7.add(MenuReportePorCliente);

        MenuReporteGeneral.setText("General");
        MenuReporteGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuReporteGeneralActionPerformed(evt);
            }
        });
        jMenu7.add(MenuReporteGeneral);

        jMenu3.add(jMenu7);

        MenuCerrarCorte.setText("Cerrar corte del día");
        MenuCerrarCorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCerrarCorteActionPerformed(evt);
            }
        });
        jMenu3.add(MenuCerrarCorte);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(BotonAbrirCorte)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(113, 113, 113)
                                                .addComponent(BotonNuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(221, 221, 221))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                .createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(BotonBuscarProducto)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(BotonReporteRapido,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(BotonNuevoCliente,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 162,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(BotonBusquedaCliente,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)))
                                                .addGap(118, 118, 118))))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BotonCerrarCorte)
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(BotonAbrirCorte)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(BotonNuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 86,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(BotonReporteRapido)
                                        .addComponent(BotonBuscarProducto))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(BotonBusquedaCliente)
                                        .addComponent(BotonNuevoCliente))
                                .addGap(19, 19, 19)
                                .addComponent(BotonCerrarCorte)
                                .addContainerGap()));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuAgregarClasificacionActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_MenuAgregarClasificacionActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_MenuAgregarClasificacionActionPerformed

    private void MenuAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_MenuAgregarClienteActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_MenuAgregarClienteActionPerformed

    private void MenuReporteClienteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_MenuReporteClienteActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_MenuReporteClienteActionPerformed

    private void MenuReportePorDiaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_MenuReportePorDiaActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_MenuReportePorDiaActionPerformed

    private void BotonCerrarCorteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BotonCerrarCorteActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_BotonCerrarCorteActionPerformed

    private void BotonBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BotonBuscarProductoActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_BotonBuscarProductoActionPerformed

    private void BotonNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BotonNuevaVentaActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_BotonNuevaVentaActionPerformed

    private void MenuCerrarCorteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_MenuCerrarCorteActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_MenuCerrarCorteActionPerformed

    private void MenuReporteGeneralActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_MenuReporteGeneralActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_MenuReporteGeneralActionPerformed

    private void MenuReportePorClienteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_MenuReportePorClienteActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_MenuReportePorClienteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(pantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pantallaPrincipal().setVisible(true);
            }
        });
    }

    public JButton getBotonAbrirCorte() {
        return BotonAbrirCorte;
    }

    public void setBotonAbrirCorte(JButton BotonAbrirCorte) {
        this.BotonAbrirCorte = BotonAbrirCorte;
    }

    public JButton getBotonBuscarProducto() {
        return BotonBuscarProducto;
    }

    public void setBotonBuscarProducto(JButton BotonBuscarProducto) {
        this.BotonBuscarProducto = BotonBuscarProducto;
    }

    public JButton getBotonBusquedaCliente() {
        return BotonBusquedaCliente;
    }

    public void setBotonBusquedaCliente(JButton BotonBusquedaCliente) {
        this.BotonBusquedaCliente = BotonBusquedaCliente;
    }

    public JButton getBotonCerrarCorte() {
        return BotonCerrarCorte;
    }

    public void setBotonCerrarCorte(JButton BotonCerrarCorte) {
        this.BotonCerrarCorte = BotonCerrarCorte;
    }

    public JButton getBotonNuevaVenta() {
        return BotonNuevaVenta;
    }

    public void setBotonNuevaVenta(JButton BotonNuevaVenta) {
        this.BotonNuevaVenta = BotonNuevaVenta;
    }

    public JButton getBotonReporteRapido() {
        return BotonReporteRapido;
    }

    public void setBotonReporteRapido(JButton BotonReporteRapido) {
        this.BotonReporteRapido = BotonReporteRapido;
    }

    public JButton getBotonNuevoCliente() {
        return BotonNuevoCliente;
    }

    public void setBotonNuevoCliente(JButton BotonNuevoCliente) {
        this.BotonNuevoCliente = BotonNuevoCliente;
    }

    public JMenuItem getMenuReporteGeneral() {
        return MenuReporteGeneral;
    }

    public void setMenuReporteGeneral(JMenuItem MenuReporteGeneral) {
        this.MenuReporteGeneral = MenuReporteGeneral;
    }

    public JMenuItem getMenuReportePorCliente() {
        return MenuReportePorCliente;
    }

    public void setMenuReportePorCliente(JMenuItem MenuReportePorCliente) {
        this.MenuReportePorCliente = MenuReportePorCliente;
    }

    public JMenuItem getMenuAgregarArea() {
        return MenuAgregarArea;
    }

    public void setMenuAgregarArea(JMenuItem MenuAgregarArea) {
        this.MenuAgregarArea = MenuAgregarArea;
    }

    public JMenuItem getMenuAgregarClasificacion() {
        return MenuAgregarClasificacion;
    }

    public void setMenuAgregarClasificacion(JMenuItem MenuAgregarClasificacion) {
        this.MenuAgregarClasificacion = MenuAgregarClasificacion;
    }

    public JMenuItem getMenuAgregarProducto() {
        return MenuAgregarProducto;
    }

    public void setMenuAgregarProducto(JMenuItem MenuAgregarProducto) {
        this.MenuAgregarProducto = MenuAgregarProducto;
    }

    public JMenuItem getMenuBuscarProductoF() {
        return MenuBuscarProductoF;
    }

    public void setMenuBuscarProductoF(JMenuItem MenuBuscarProductoF) {
        this.MenuBuscarProductoF = MenuBuscarProductoF;
    }

    public JMenuItem getMenuModificarExistencia() {
        return MenuModificarExistencia;
    }

    public void setMenuModificarExistencia(JMenuItem MenuModificarExistencia) {
        this.MenuModificarExistencia = MenuModificarExistencia;
    }

    public JMenuItem getMenuReporteDeExistencias() {
        return MenuReporteDeExistencias;
    }

    public void setMenuReporteDeExistencias(JMenuItem MenuReporteDeExistencias) {
        this.MenuReporteDeExistencias = MenuReporteDeExistencias;
    }

    public JMenuItem getMenuAgregarCliente() {
        return MenuAgregarCliente;
    }

    public void setMenuAgregarCliente(JMenuItem MenuAgregarCliente) {
        this.MenuAgregarCliente = MenuAgregarCliente;
    }

    public JMenuItem getMenuBuscarCliente() {
        return MenuBuscarCliente;
    }

    public void setMenuBuscarCliente(JMenuItem MenuBuscarCliente) {
        this.MenuBuscarCliente = MenuBuscarCliente;
    }

    public JMenuItem getMenuReporteCliente() {
        return MenuReporteCliente;
    }

    public void setMenuReporteCliente(JMenuItem MenuReporteCliente) {
        this.MenuReporteCliente = MenuReporteCliente;
    }

    public JMenuItem getMenuAbrirCorte() {
        return MenuAbrirCorte;
    }

    public void setMenuAbrirCorte(JMenuItem MenuAbrirCorte) {
        this.MenuAbrirCorte = MenuAbrirCorte;
    }

    public JMenuItem getMenuEliminarProducto() {
        return MenuEliminarProducto;
    }

    public void setMenuEliminarProducto(JMenuItem MenuEliminarProducto) {
        this.MenuEliminarProducto = MenuEliminarProducto;
    }

    public JMenuItem getMenuNuevaVenta() {
        return MenuNuevaVenta;
    }

    public void setMenuNuevaVenta(JMenuItem MenuNuevaVenta) {
        this.MenuNuevaVenta = MenuNuevaVenta;
    }

    public JMenuItem getMenuCerrarCorte() {
        return MenuCerrarCorte;
    }

    public void setMenuCerrarCorte(JMenuItem MenuCerrarCorte) {
        this.MenuCerrarCorte = MenuCerrarCorte;
    }

    public JMenuItem getMenuReportePorDia() {
        return MenuReportePorDia;
    }

    public void setMenuReportePorDia(JMenuItem MenuReportePorDia) {
        this.MenuReportePorDia = MenuReportePorDia;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAbrirCorte;
    private javax.swing.JButton BotonBuscarProducto;
    private javax.swing.JButton BotonBusquedaCliente;
    private javax.swing.JButton BotonCerrarCorte;
    private javax.swing.JButton BotonNuevaVenta;
    private javax.swing.JButton BotonNuevoCliente;
    private javax.swing.JButton BotonReporteRapido;
    private javax.swing.JMenuItem MenuAbrirCorte;
    private javax.swing.JMenuItem MenuAgregarArea;
    private javax.swing.JMenuItem MenuAgregarClasificacion;
    private javax.swing.JMenuItem MenuAgregarCliente;
    private javax.swing.JMenuItem MenuAgregarProducto;
    private javax.swing.JMenuItem MenuBuscarCliente;
    private javax.swing.JMenuItem MenuBuscarProductoF;
    private javax.swing.JMenuItem MenuCerrarCorte;
    private javax.swing.JMenuItem MenuEliminarProducto;
    private javax.swing.JMenuItem MenuModificarExistencia;
    private javax.swing.JMenuItem MenuNuevaVenta;
    private javax.swing.JMenuItem MenuReporteCliente;
    private javax.swing.JMenuItem MenuReporteDeExistencias;
    private javax.swing.JMenuItem MenuReporteGeneral;
    private javax.swing.JMenuItem MenuReportePorCliente;
    private javax.swing.JMenuItem MenuReportePorDia;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem16;
    // End of variables declaration//GEN-END:variables

}
