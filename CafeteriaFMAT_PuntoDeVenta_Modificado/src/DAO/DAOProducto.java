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

import Modelo.Inventario.Producto;

/**
 *
 * @author jhont
 */
public class DAOProducto extends DAOGeneral<Producto> {

    @Override
    public int agregar(Producto e) throws SQLException {
        int numFilas = 0;
        Connection con = getConeccion();

        String orden = "INSERT INTO productos (codigo, nombre, precioCompra, precioVenta, existencia, codigoArea, codigoClasificacion, descripcion)"
                +
                "VALUES (" + e.getCodigo() + ",'" + e.getNombre() + "'," + e.getPrecioDeCompra() + ","
                + e.getPrecioDeVenta() +
                "," + e.getExistencia() + "," + e.getCodigoArea() + "," + e.getCodigoClasificacion() + ", \""
                + e.getDescripcion() + "\")";
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

        String orden = "DELETE FROM productos WHERE " + condicion;

        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }

    @Override
    public int modificar(Producto e, String condicion) throws SQLException {
        int numFilas = 0;
        Connection con = getConeccion();

        String orden = "UPDATE puntodeventa.productos SET " +
                " nombre='" + e.getNombre() + "'," +
                " precioCompra=" + e.getPrecioDeCompra() + "," +
                " precioVenta=" + e.getPrecioDeVenta() + "," +
                " existencia=" + e.getExistencia() + "," +
                " descripcion='" + e.getDescripcion() + "'" +
                " WHERE " + condicion;
        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }

    @Override
    public ArrayList<Producto> consultar(String condicion) throws SQLException {
        ArrayList<Producto> lista = new ArrayList<Producto>();
        Producto e;
        Connection con = getConeccion();
        String orden = "SELECT * FROM productos " +
                (condicion == null || condicion.length() == 0 ? "" : "WHERE " + condicion);
        Statement sentencia = con.createStatement();
        ResultSet rs = sentencia.executeQuery(orden);
        while (rs.next()) {
            e = new Producto(rs.getInt("codigo"), rs.getString("nombre"),
                    rs.getDouble("precioCompra"), rs.getDouble("precioVenta"), rs.getInt("existencia"),
                    rs.getInt("codigoClasificacion"), rs.getInt("codigoArea"), rs.getString("descripcion"));

            lista.add(e);
        }
        sentencia.close();
        cerrarConeccion(con);
        return lista;
    }

}
