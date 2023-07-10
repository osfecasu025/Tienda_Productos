package DDBB;
/**
 * Clase que se encarga de realizar todas las consultas a la base de datos
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Consultador {
    //ATRIBUTOS--------------------------------------------------------------------------------------------------------------------------------------
    public Conexion conexionDB;

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
    
}
