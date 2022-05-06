/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Igniter
 */
public class DAOReporte extends DAOGeneral<Reporte>{

  

    @Override
    public int agregar(Reporte e) throws SQLException {
        int numFilas = 0;
        Connection con = getConeccion();
        String fecha;
        fecha=e.getDia()+"-"+e.getMes()+"-"+e.getAnio();

        String orden = "INSERT INTO reportes (codigo, nombre, cantidad, subtotal, fecha, matricula)"+
        "VALUES ("+ e.getCodigo()+ ",'" + e.getNombre()+ "',"+e.getCantidad()+","+e.getSubtotal()+",\""+fecha+"\","+e.getMatricula()+")";
        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }

    @Override
    public int modificar(Reporte e, String condicion) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(String condicion) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Reporte> consultar(String condicion) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
