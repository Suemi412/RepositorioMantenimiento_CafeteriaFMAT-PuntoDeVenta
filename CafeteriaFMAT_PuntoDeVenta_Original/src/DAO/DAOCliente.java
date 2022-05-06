/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Usuario.Cliente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author lbojor
 */
public class DAOCliente extends DAOGeneral<Cliente>{
    public DAOCliente() {
    }

    @Override
    public int agregar(Cliente e) throws SQLException {
        int numFilas = 0;
        Connection con = getConeccion();

        String orden = "INSERT INTO clientes (matricula, nombre,saldo)"+
        "VALUES ("+ e.getMatricula()+ ",'" + e.getNombre()+ "',"+e.getCuenta().getSaldo()+")";
        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }

    @Override
    public int eliminar(String condicion)throws SQLException{
        int numFilas = 0;
        Connection con = getConeccion();

        String orden = "DELETE FROM clientes WHERE "+condicion;

        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }

    @Override
    public int modificar(Cliente e, String condicion)throws SQLException {
        int numFilas = 0;
        Connection con = getConeccion();

        String orden = "UPDATE puntodeventa.clientes SET " +
                " saldo="+e.getCuenta().getSaldo()+ 
                " WHERE "+condicion;

        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas; 
    }

    @Override
    public ArrayList<Cliente> consultar(String condicion) throws SQLException{
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        Cliente e;
        Connection con = getConeccion();
        String orden = "SELECT * FROM clientes " +
                (condicion==null || condicion.length()==0 ? "":"WHERE " + condicion);
        Statement sentencia = con.createStatement();
        ResultSet rs = sentencia.executeQuery( orden );
        while (rs.next()) {
            e = new Cliente(rs.getInt("matricula"), rs.getString("nombre"),rs.getDouble("saldo"));
            lista.add( e );
        }
        sentencia.close();
        cerrarConeccion(con);
        return lista;
    }
}
