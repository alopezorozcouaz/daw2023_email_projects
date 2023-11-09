/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.data;

import murach.business.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Permite realizar las operaciones CRUD en la entidad user de la base de datos
 * murach
 *
 * @author alopezorozco
 */
public class UserDB {

    /**
     * Inserta un nuevo usuario en la bd
     *
     * @param user
     * @return
     */
    public static int insert(User user) {
        // Obtiene una instancia del ConnectionPool para gestionar las conexiones a la base de datos
        ConnectionPool pool = ConnectionPool.getInstance();

        // Obtiene una conexión de la pool
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        // Consulta SQL para insertar un nuevo usuario en la tabla 'user'
        String query
                = "INSERT INTO user (Email, FirstName, LastName) "
                + "VALUES (?, ?, ?)";
        try {
            // Prepara la declaración SQL
            ps = connection.prepareStatement(query);

            // Establece los valores en la consulta preparada a partir del objeto User proporcionado
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());

            // Ejecuta la actualización de la base de datos y retorna el número de filas afectadas
            return ps.executeUpdate();
        } catch (SQLException e) {
            // Si ocurre una excepción SQLException, captura el error
            System.out.println(e);
            Error.descripcion = e.getMessage();  // Asigna el mensaje de error a un objeto Error          
            return 0; // Retorna 0 para indicar fallo en la inserción
        } finally {
            // Asegura el cierre de la PreparedStatement y la liberación de la conexión en el bloque finally
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }//fin del método

    /**
     * Retorna la lista de usuarios dados de alta en la bd
     *
     * @return
     */
    public static List<User> getAllUsers() {
        // Obtiene una instancia del ConnectionPool para gestionar las conexiones a la base de datos
        ConnectionPool pool = ConnectionPool.getInstance();

        // obtiene una conexión del pool
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        //consulta para obtener todos los usuarios
        String query = "SELECT * FROM user";

        try {
            //prepara la declaración SQL
            ps = connection.prepareStatement(query);

            //ejecuta la consulta
            rs = ps.executeQuery();

            //Colección para almacenar la lista de usuarios
            ArrayList<User> users = new ArrayList<>();

            //creamos un objeto de tipo user
            User user = null;

            //se recorre la lista de elementos encontrados en la bd
            while (rs.next()) {
                user = new User();
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));

                users.add(user); //se agrega el elemento a la colección.
            }
            
            return users;
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
}
