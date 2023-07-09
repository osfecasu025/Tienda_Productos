/*
 * Clase que representa la conexión con la base de datos
 */
package DDBB;
import java.sql.*;
public class Conexion  {
    //ATRIBUTOS--------------------------------------------------------------------------------------------------------------------------------------
    private String url;
    private String usuario;
    private String contraseña;
    private Connection conexion;

    
    //CONSTRUCTORES----------------------------------------------------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia de la clase con valores por defecto.
     * @throws SQLException Cuando ocurre un error a la hora de conectar con la base de datos MySql.
     */
    public Conexion() throws SQLException {
        this.url = "jdbc:mysql://localhost:3307/tienda";
        this.usuario = "root";
        this.contraseña = "";
    }
    
    //SETTERS AND GETTERS----------------------------------------------------------------------------------------------------------------------------
    /**
     * Devuelve la conexión establecida con la base de datos.
     * @return
     */
    public Connection getConexion() {
        return conexion;
    }

    //METODOS----------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Crea una conexión con la base de datos y la asigna al atributo 'conexion' y avisa en la consola.
     * @throws SQLException Cuando ocurre un error a la hora de conectar con la base de datos MySql.
     */
    public void conectar() throws SQLException{
        conexion = DriverManager.getConnection(url, usuario, contraseña);
        System.out.println("Conexión establecida");
    }

    /**
     * Si la conexión se encuentra activa, la desconecta y avisa en la consola.
     * @throws SQLException ToDo->Averiguar cuando lanza error
     */
    public void desconectar() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
            System.out.println("Conexión cerrada");
        }
    }
}
