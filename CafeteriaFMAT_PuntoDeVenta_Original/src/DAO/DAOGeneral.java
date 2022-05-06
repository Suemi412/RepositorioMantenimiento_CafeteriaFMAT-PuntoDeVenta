/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author lbojor
 */
public abstract class DAOGeneral<T> {
    private String host="localhost:3306";
    private String bd="puntodeventa";
    private String login="root";
    private String password="root";
    private boolean cargadoDriver;

    public DAOGeneral() {
        cargarDriver();
   }

    public void cargarDriver(){
        try {
            if ( !cargadoDriver ) {
                //Class.forName("org.postgresql.Driver");
                Class.forName("com.mysql.jdbc.Driver");
                cargadoDriver = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConeccion(String host, String bd, String login, String password){
        Connection conexion=null;
        String urlConexion = "jdbc:mysql://"+ host +"/" + bd +
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try {
            conexion=DriverManager.getConnection(urlConexion, login, password);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }

     public Connection getConeccion(){
        return getConeccion(host, bd, login, password );
    }

    public void cerrarConeccion(Connection con){
        try {
            if ( con != null )
                if ( !con.isClosed() )    // Si no esta cerrada, se cierra
                    con.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }


    public abstract int agregar(T e)throws SQLException;

    public abstract int eliminar(String condicion)throws SQLException;

    public abstract int modificar(T e, String condicion)throws SQLException;

    public abstract ArrayList<T> consultar(String condicion)throws SQLException;

      public String getHost() {
        return host;
    }

    public String getBd() {
        return bd;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isCargadoDriver() {
        return cargadoDriver;
    }
}    

