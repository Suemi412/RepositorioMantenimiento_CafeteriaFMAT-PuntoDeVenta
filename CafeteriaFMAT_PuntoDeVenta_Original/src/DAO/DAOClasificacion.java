/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Inventario.Clasificacion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jhont
 */
public class DAOClasificacion extends DAOGeneral<Clasificacion>{

    @Override
    public int agregar(Clasificacion e) throws SQLException {
        int numFilas = 0;
        Connection con = getConeccion();

        String orden = "INSERT INTO clasificaciones (codigo, nombre,codigoArea)"+
        "VALUES ("+ e.getCodigo()+ ",\"" + e.getNombre()+ "\","+e.getCodigoArea()+")";
        Statement sentencia = con.createStatement();
        numFilas = sentencia.executeUpdate(orden);
        sentencia.close();
        cerrarConeccion(con);
        return numFilas;
    }


    @Override
    public ArrayList<Clasificacion> consultar(String condicion) throws SQLException {
        ArrayList<Clasificacion> lista = new ArrayList<Clasificacion>();
        Clasificacion e;
        Connection con = getConeccion();
        String orden = "SELECT * FROM clasificaciones " +
                (condicion==null || condicion.length()==0 ? "":"WHERE " + condicion);
        Statement sentencia = con.createStatement();
        ResultSet rs = sentencia.executeQuery( orden );
        while (rs.next()) {
            e = new Clasificacion(rs.getInt("codigo"), rs.getString("nombre"),rs.getInt("codigoArea"));
            lista.add( e );
        }
        sentencia.close();
        cerrarConeccion(con);
        return lista;
    }

    @Override
    public int eliminar(String condicion) throws SQLException {
        return 0;
    }

    @Override
    public int modificar(Clasificacion e, String condicion) throws SQLException {
        return 0;
    }

}
