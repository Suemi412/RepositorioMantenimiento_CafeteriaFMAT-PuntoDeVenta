/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.Inventario.Area;

/**
 *
 * @author jhont
 */
public class DAOArea extends DAOGeneral<Area> {

    @Override
    public int agregar(Area e) throws SQLException {
        int numFilas = 0;
        Connection con = getConeccion();

        String orden = "INSERT INTO areas (codigo, nombre)" +
                "VALUES (" + e.getCodigo() + ",'" + e.getNombre() + "'" + ")";
        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }

    @Override
    public int eliminar(String condicion) throws SQLException {
        int numFilas = 0;
        Connection con = getConeccion();

        String orden = "DELETE FROM areas WHERE " + condicion;

        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }

    @Override
    public int modificar(Area e, String condicion) throws SQLException {
        int numFilas = 0;
        Connection con = getConeccion();

        String orden = "UPDATE areas SET " +
                " nombre='" + e.getNombre() + "'," +
                " WHERE " + condicion;

        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }

    @Override
    public ArrayList<Area> consultar(String condicion) throws SQLException {
        ArrayList<Area> lista = new ArrayList<Area>();
        Area e;
        Connection con = getConeccion();
        String orden = "SELECT * FROM areas " +
                (condicion == null || condicion.length() == 0 ? "" : "WHERE " + condicion);
        Statement sentencia = con.createStatement();
        ResultSet rs = sentencia.executeQuery(orden);
        while (rs.next()) {
            e = new Area(rs.getInt("codigo"), rs.getString("nombre"));
            lista.add(e);
        }
        sentencia.close();
        cerrarConeccion(con);
        return lista;
    }

}
