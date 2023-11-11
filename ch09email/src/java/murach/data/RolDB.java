/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import murach.business.Rol;

/**
 *
 * @author alopezorozco
 */
public class RolDB {
    /**
     * Retorna la lista de roles dados de alta en la bd
     *
     * @return
     */
    public static List<Rol> getAllRols() {
        // Obtiene una instancia del ConnectionPool para gestionar las conexiones a la base de datos
        ConnectionPool pool = ConnectionPool.getInstance();

        // obtiene una conexión del pool
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        //consulta para obtener todos los usuarios
        String query = "SELECT * FROM rol";

        try {
            //prepara la declaración SQL
            ps = connection.prepareStatement(query);

            //ejecuta la consulta
            rs = ps.executeQuery();

            //Colección para almacenar la lista de usuarios
            ArrayList<Rol> roles = new ArrayList<>();

            //creamos un objeto de tipo user
            Rol rol = null;

            //se recorre la lista de elementos encontrados en la bd
            while (rs.next()) {
                rol = new Rol();
                rol.setId(rs.getInt("Id"));
                rol.setNombre(rs.getString("Nombre"));
                rol.setDescripcion(rs.getString("Descripcion"));

                roles.add(rol); //se agrega el elemento a la colección.
            }
            
            return roles;
        } catch (SQLException e) {
            System.out.println(e);
            Error.descripcion = e.getMessage();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }//fin del método getAllUsers
    
    /**
     * retorna los datos de un rol que 
     * concuerde con el id especificado
     * @param id // id del rol
     * @return 
     */
    public static Rol findRolById(int id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM rol "
                + "WHERE Id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Rol rol = null;
            if (rs.next()) {
                rol = new Rol();
                
                rol.setId(rs.getInt("Id"));
                rol.setNombre(rs.getString("Nombre"));
                rol.setDescripcion(rs.getString("Descripcion"));
            }
            return rol;
        } catch (SQLException e) {
            System.out.println(e);
            Error.descripcion = e.getMessage();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
