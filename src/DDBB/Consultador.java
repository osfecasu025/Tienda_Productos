package DDBB;
/**
 * Clase que se encarga de realizar todas las consultas a la base de datos
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
<<<<<<< Updated upstream
=======
import java.sql.*;
import java.util.ArrayList;
>>>>>>> Stashed changes


public class Consultador {
    //ATRIBUTOS--------------------------------------------------------------------------------------------------------------------------------------
    private Conexion conexionDB;

    //CONSTRUCTOR------------------------------------------------------------------------------------------------------------------------------------

    public Consultador(Conexion pConexion) {
        this.conexionDB = pConexion;
    }

    //GETTERS AND SETTERS----------------------------------------------------------------------------------------------------------------------------

    //METODOS----------------------------------------------------------------------------------------------------------------------------------------
    
    /**
     * 
     * @param pIdProducto
     * @param pNombreProducto
     * @param pTipoProducto
     * @param pCantidadProducto
     * @param pCantidadMinProducto
     * @param pPrecioUnit
     * @param pIva
     * @throws SQLException
     */
    public void insertarProducto(int pIdProducto, String pNombreProducto, String pTipoProducto, int pCantidadProducto, int pCantidadMinProducto, float pPrecioUnit, float pIva) throws SQLException {
        //Iniciar conexión
        this.conexionDB.conectar();
        Connection tempConnection = conexionDB.getConexion();

        //Preparar statement
        String sql = "INSERT INTO productos (id, nombre, tipo, cantidadBodega, cantidadMinima, precioUnitario, iva) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = tempConnection.prepareStatement(sql);

        //Pasamos los valores de los parametros
        statement.setInt(1, pIdProducto);
        statement.setString(2, pNombreProducto);
        statement.setString(3, pTipoProducto); 
        statement.setInt(4, pCantidadProducto);
        statement.setInt(5, pCantidadMinProducto);
        statement.setFloat(6, pPrecioUnit);
        statement.setFloat(7, pIva);

        //Ejecutamos el statment e informamos
        statement.executeUpdate();
        System.out.println("Producto insertado correctamente");

        //Cerrar conexión
        this.conexionDB.desconectar();
    }

    //TODO-> Actualizar producto

    //TODO->eliminarProducto

    //TODO->listarProductos... Deve devolver un arraylist con 7 columnas en el que cada fila traiga los valores necesarios para construir un producto 
    
<<<<<<< Updated upstream
=======
    public void actualizarProducto(int pIdProducto, String pNombreProducto, String pTipoProducto, int pCantidadProducto, int pCantidadMinProducto, float pPrecioUnit, float pIva, int pCantidadVendida) throws SQLException {
        //Iniciar conexión
        this.conexionDB.conectar();
        Connection tempConnection = conexionDB.getConexion();

        //Preparar statement
       String sql = "UPDATE productos SET nombre=?, tipo=?, cantidadBodega=?, cantidadMinima=?, precioUnitario=?, iva=?, cantidadVendida=? WHERE id=?";
        PreparedStatement statement = tempConnection.prepareStatement(sql);

        //Pasamos los valores de los parametros
        statement.setString(1, pNombreProducto);
        statement.setString(2, pTipoProducto); // Utilizamos el nombre del enum como valor de tipo
        statement.setInt(3, pCantidadProducto);
        statement.setInt(4, pCantidadMinProducto);
        statement.setFloat(5, pPrecioUnit);
        statement.setFloat(6, pIva); // Utilizamos el nombre del enum como valor de Iva
        statement.setInt(7, pCantidadVendida);
        statement.setInt(8, pIdProducto);

        //Ejecutamos el statment e informamos
        statement.executeUpdate();
        System.out.println("Producto Actualizado correctamente");

        //Cerrar conexión
        this.conexionDB.desconectar();
    }

    
    public void eliminarProducto(int pIdProducto) throws SQLException {
        //Iniciar conexión
        this.conexionDB.conectar();
        Connection tempConnection = conexionDB.getConexion();

        //Preparar statement
        String sql = "DELETE FROM productos WHERE id=?";
        PreparedStatement statement = tempConnection.prepareStatement(sql);

        //Pasamos los valores de los parametros
        statement.setInt(1, pIdProducto);
        

        //Ejecutamos el statment e informamos
        statement.executeUpdate();
        System.out.println("Producto Eliminado correctamente");

        //Cerrar conexión
        this.conexionDB.desconectar();
    }
    

    public ArrayList<ProductoDB> listarProducto() throws SQLException {
        //Iniciar conexión
        this.conexionDB.conectar();
        Connection tempConnection = conexionDB.getConexion();

        //Preparar statement
        String sql = "SELECT * FROM productos";
        PreparedStatement statement = tempConnection.prepareStatement(sql);
        ResultSet result = statement.executeQuery(sql);

        //Crear nuevo arraylist
        ArrayList<ProductoDB> resultados = new ArrayList<ProductoDB>();
        ProductoDB current;

        //Pasamos los valores de los parametros
        while (result.next()) {
            int id = result.getInt("id");
            String nombre = result.getString("nombre");
            String tipo = result.getString("tipo");
            int cantidadBodega = result.getInt("cantidadBodega");
            int cantidadMinimaP = result.getInt("cantidadMinima");
            float precioUnitario = result.getFloat("precioUnitario");
            float iva = result.getFloat("iva");
            int cantidadVendida = result.getInt("cantidadVendida");
            current = new ProductoDB(id, nombre, tipo, cantidadBodega, cantidadMinimaP, precioUnitario, iva, cantidadVendida);

            resultados.add(current);
        }

        //Cerrar conexión
        this.conexionDB.desconectar();

        return resultados;
    }
>>>>>>> Stashed changes
}
