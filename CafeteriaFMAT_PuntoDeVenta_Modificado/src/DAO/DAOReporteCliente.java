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
 * @author 52999
 */
public class DAOReporteCliente extends DAOGeneral<ReporteCliente> {

    @Override
    public int agregar(ReporteCliente e) throws SQLException {
        int numFilas = 0;
        Connection con = getConeccion();
        String fecha;
        fecha = e.getDia() + "-" + e.getMes() + "-" + e.getAnio();

        String orden = "INSERT INTO reportecliente (matricula, fecha, accion, actualizacion, saldo)" +
                "VALUES (" + e.getMatricula() + ",\"" + fecha + "\",\"" + e.getAccion() + "\"," + e.getActualizacion()
                + "," + e.getSaldo() + ")";
        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }

    @Override
    public int eliminar(String condicion) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

    @Override
    public int modificar(ReporteCliente e, String condicion) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

    @Override
    public ArrayList<ReporteCliente> consultar(String condicion) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

}
