/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import ACA2.Producto;
import ACA2.TipoProducto;
import ACA2.TipodeIva;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
/**
 *
 * @author osfec
 */
public class Conexion  {
        public String url = "jdbc:mysql://localhost:3307/tienda";
        public String usuario = "root";
        public String contraseña = "";
        public Connection conexion;
     
       public Conexion(String url, String usuario, String contraseña) throws SQLException {
        this.url = url;
        this.usuario = usuario;
        this.contraseña = contraseña;
        conectar();
    }

    public Conexion() throws SQLException{
        conectar();
    }
       

    public void conectar() throws SQLException{
        conexion = DriverManager.getConnection(url, usuario, contraseña);
        System.out.println("Conexión establecida");
   
    }

    public void desconectar() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
            System.out.println("Conexión cerrada");
        }
    }
     
     public void insertarProducto(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos (id, nombre, tipo, cantidadBodega, cantidadMinima, precioUnitario, iva) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setInt(1, producto.getId());
        statement.setString(2, producto.getNombre());
        statement.setString(3, producto.getTipo().name()); // Utilizamos el nombre del enum como valor de tipo
        statement.setInt(4, producto.getCantidadBodega());
        statement.setInt(5, producto.getCantidadMinimaP());
        statement.setFloat(6, producto.getPrecioUnitario());
        if (producto.getIva() != null) {
        statement.setString(7, producto.getIva().name());
    } else {
        statement.setString(7, null); // O ajusta esto según tus necesidades
    }

    statement.executeUpdate();
    System.out.println("Producto insertado correctamente");
}
    

    public void actualizarProducto(Producto producto) throws SQLException {
        String sql = "UPDATE productos SET nombre=?, tipo=?, cantidadBodega=?, cantidadMinima=?, precioUnitario=?, iva=? WHERE id=?";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, producto.getNombre());
        statement.setString(2, producto.getTipo().name()); // Utilizamos el nombre del enum como valor de tipo
        statement.setInt(3, producto.getCantidadBodega());
        statement.setInt(4, producto.getCantidadMinimaP());
        statement.setFloat(5, producto.getPrecioUnitario());
        statement.setString(6, producto.getIva().name()); // Utilizamos el nombre del enum como valor de Iva
        statement.setInt(7, producto.getId());
        statement.executeUpdate();
        System.out.println("Producto actualizado correctamente");
    }

    public void eliminarProducto(int idProducto) throws SQLException {
        String sql = "DELETE FROM productos WHERE id=?";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setInt(1, idProducto);
        statement.executeUpdate();
        System.out.println("Producto eliminado correctamente");
    }

    public void listarProductos() throws SQLException {
        String sql = "SELECT * FROM productos";
        Statement statement = conexion.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("id");
            String nombre = result.getString("nombre");
            TipoProducto tipo = TipoProducto.valueOf(result.getString("tipo"));
            int cantidadBodega = result.getInt("cantidadBodega");
            int cantidadMinimaP = result.getInt("cantidadMinima");
            float precioUnitario = result.getFloat("precioUnitario");
            TipodeIva iva = TipodeIva.valueOf(result.getString("iva"));
          
            // Hacer algo con los datos obtenidos, por ejemplo imprimirlos
            System.out.println("Id: " + id);
            System.out.println("Nombre: " + nombre);
            System.out.println("Tipo: " + tipo);
            System.out.println("Cantidad en Bodega: " + cantidadBodega);
            System.out.println("Cantidad Mínima Permitida: " + cantidadMinimaP);
            System.out.println("Precio Unitario: " + precioUnitario);
            System.out.println("IVA: " + iva.name()); // Utilizamos el nombre del enum al mostrarlo

            System.out.println("--------------------");
        }
    }
  
     
}
