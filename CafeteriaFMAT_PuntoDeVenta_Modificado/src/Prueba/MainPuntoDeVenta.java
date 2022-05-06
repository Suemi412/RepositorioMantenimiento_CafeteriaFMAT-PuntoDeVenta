/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import Control.ControlPantallaPrincipal;
import Vista.pantallaPrincipal;

/**
 *
 * @author jhont
 */
public class MainPuntoDeVenta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        pantallaPrincipal v = new pantallaPrincipal();
        ControlPantallaPrincipal control = new ControlPantallaPrincipal(v);
        v.setVisible(true);
    }
}
