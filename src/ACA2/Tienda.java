package ACA2;
/**
 * Clase principal del modelo del mundo
 */
import DDBB.*;

import java.io.EOFException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumSet;
public class Tienda {

    //ATRIBUTOS--------------------------------------------------------------------------------------------------------------------------------------
    private ArrayList<Producto> productos;
    private Conexion conexionDB;
    private Consultador consultadorDB;
    
    //CONSTUCTOR-------------------------------------------------------------------------------------------------------------------------------------
    public Tienda(){
        productos = new ArrayList<>();
        conexionDB = new Conexion();
        consultadorDB = new Consultador(conexionDB);
    }

    //GETTERS----------------------------------------------------------------------------------------------------------------------------------------
    
    /**
     * Devuelve un arraylist con los nombres de los tipos de producto posibles
     * @return
     */
    public ArrayList<String> getTiposProducto(){
        ArrayList<String> retorno = new ArrayList<>();
        retorno.add(TipoProducto.DROGUERIA.getNombre());
        retorno.add(TipoProducto.PAPELERIA.getNombre());
        retorno.add(TipoProducto.SUPERMERCADO.getNombre());
        return retorno;
    }

    /**
     * Devuelve un arraylist con las descripciones de los tipos de iva posibles
     * @return
     */
    public ArrayList<String> getTiposIva(){
        ArrayList<String> retorno = new ArrayList<>();
        retorno.add(TipodeIva.IvaDrogueria_12.getDescripcion());
        retorno.add(TipodeIva.IvaPapeleria_16.getDescripcion());
        retorno.add(TipodeIva.IvaSupermercado_4.getDescripcion());
        return retorno;
    }

    //METODOS
    public void agregarProducto(String pId, String pNombre, String  pTipo, String pCantidadBodega, String pCantidadMinima, String pPrecioUnitario, String pIva) throws Exception{
        try {
            //Validar valores pasados por parametro
            int id = Integer.parseInt(pId);
            String nombre = pNombre;
            TipoProducto tipo = buscarTipoProducto(pTipo);
            int cantidadBodega = Integer.parseInt(pCantidadBodega);
            int cantidadMinima = Integer.parseInt(pCantidadMinima);
            float precioUnitario = Float.parseFloat(pPrecioUnitario);
            TipodeIva tipodeIva = buscarTipodeIva(pIva);
            if(tipo == null)
                throw new Exception("", null);
            if(tipodeIva == null)
                throw new Exception("", null);
            
            //Creo producto y lo agrego a la lista
            Producto producto = new Producto(id, nombre, tipo, cantidadBodega, cantidadMinima, precioUnitario, tipodeIva, 0);
            productos.add(producto);

            //Agrego producto a la BD (Puede mandar SQLException si no se logra conectar a la base)
            consultadorDB.insertarProducto(id, nombre, tipo.getNombre(), cantidadBodega, cantidadMinima, precioUnitario, tipodeIva.getPorcentajeIva(), 0);

        }catch(SQLException e){
            throw new Exception("No se pudo acceder a la base de datos", null);
        }catch (Exception e) {
            throw new Exception("Los datos ingresados no son válidos", null);
        }
    }

    public void eliminarProducto(String pId) throws Exception{
        boolean productException = false;
        try {
            //Verificar Id pasada por parámetro
            int id = Integer.parseInt(pId);
            Producto producto = buscarProducto(id);
            if (producto == null){
                productException = true;
                throw new Exception("", null);
            }

            //Eliminar producto de la lista
            productos.remove(producto);

            //Eliminar producto de la BD
            consultadorDB.eliminarProducto(id);

        }catch(SQLException e){
            throw new Exception("No se pudo acceder a la base de datos", null);
        }catch (Exception e) {
            if(productException)
                throw new Exception("No se encontró un producto con la id solicitada.", null);
            throw new Exception("Los datos ingresados no son válidos", null);
        }
    }

    public void actualizarProducto(String pId, String pNombre, String  pTipo, String pCantidadBodega, String pCantidadMinima, String pPrecioUnitario, String pIva, String pCantidadVendida) throws Exception{
        boolean productException = false;
        try {
            //Validar valores pasados por parametro
            int id = Integer.parseInt(pId);
            String nombre = pNombre;
            TipoProducto tipo = buscarTipoProducto(pTipo);
            int cantidadBodega = Integer.parseInt(pCantidadBodega);
            int cantidadMinima = Integer.parseInt(pCantidadMinima);
            float precioUnitario = Float.parseFloat(pPrecioUnitario);
            TipodeIva tipodeIva = buscarTipodeIva(pIva);
            int cantidadVendida =  Integer.parseInt(pCantidadVendida);
            if(tipo == null)
                throw new Exception("", null);
            if(tipodeIva == null)
                throw new Exception("", null);
            
            //Busca producto y lo modifica
            Producto producto = buscarProducto(id);
            if(producto == null){
                productException = true;
                throw new Exception("", null);
            }
            producto.setNombre(nombre);
            producto.setTipo(tipo);
            producto.setCantidadBodega(cantidadBodega);
            producto.setCantidadMinimaP(cantidadMinima);
            producto.setPrecioUnitario(precioUnitario);
            producto.setIva(tipodeIva);
            producto.setCantidadVendida(cantidadVendida);

            //Agrego producto a la BD (Puede mandar SQLException si no se logra conectar a la base)
            consultadorDB.actualizarProducto(id, nombre, tipo.getNombre(), cantidadBodega, cantidadMinima, precioUnitario, tipodeIva.getPorcentajeIva(), cantidadVendida);

        }catch(SQLException e){
            throw new Exception("No se pudo acceder a la base de datos", null);
        }catch (Exception e) {
            if(productException)
                throw new Exception("No se encontró un producto con la id solicitada.", null);
            throw new Exception("Los datos ingresados no son válidos", null);
        }
    }

    //TODO->CALCULAR INGRESOS TOTALES
    public float darGananciaTotal() {
        float gananciaTotal = 0.0f;
        for (Producto producto : productos) {
            gananciaTotal += producto.getPrecioUnitario() * producto.getCantidadVendida();
        }
        return gananciaTotal;
    }

    //TODO->CALCULAR EL PROMEDIO DE VENTAS
    public float darPromedioVenta() {
        float totalUnidadesVendidas = 0.0f;
        float totalVentas = 0.0f;
        for (Producto producto : productos) {
            totalUnidadesVendidas += producto.getCantidadVendida();
            totalVentas += producto.getPrecioUnitario() * producto.getCantidadVendida();
        }
        return totalVentas / totalUnidadesVendidas;
    }

    //TODO->CALCULAR EL PRODUCTO MÁS VENDIDO
    public Producto darProductoMasVendido() {
        Producto productoMasVendido = null;
        int maxCantidadVendida = Integer.MIN_VALUE;
        for (Producto producto : productos) {
            if (producto.getCantidadVendida() > maxCantidadVendida) {
                maxCantidadVendida = producto.getCantidadVendida();
                productoMasVendido = producto;
            }
        }
        return productoMasVendido;
    }

    //TODO->CALCULAR EL PRODUCTO MENOS VENDIDO
    public Producto darProductoMenosVendido() {
        Producto productoMenosVendido = null;
        int minCantidadVendida = Integer.MAX_VALUE;
        for (Producto producto : productos) {
            if (producto.getCantidadVendida() < minCantidadVendida) {
                minCantidadVendida = producto.getCantidadVendida();
                productoMenosVendido = producto;
            }
        }
        return productoMenosVendido;
    }

    //TODO->DEVOLVER LISTA CON TODOS LOS PRODUCTOS
    public void mostrarEstadoProductos() {
        for (Producto producto : productos) {
            System.out.println("Producto: " + producto.getNombre());
            System.out.println("Tipo: " + producto.getTipo());
            System.out.println("Cantidad en bodega: " + producto.getCantidadBodega());
            System.out.println("Cantidad mínima: " + producto.getCantidadMinimaP());
            System.out.println("Precio unitario: $" + producto.getPrecioUnitario());
            System.out.println("Cantidad vendida: " + producto.getCantidadVendida());
            System.out.println("--------------------------------------");
        }
    }

    //TODO->DEVOLVER LISTA CON LOS PRODUCTOS QUE HAY QUE HACER PEDIDO
    public void mostrarEstadoProductosConPedido() {
        for (Producto producto : productos) {
            System.out.println("Producto: " + producto.getNombre());
            System.out.println("Tipo: " + producto.getTipo());
            System.out.println("Cantidad en bodega: " + producto.getCantidadBodega());
            System.out.println("Cantidad mínima: " + producto.getCantidadMinimaP());
            System.out.println("Precio unitario: $" + producto.getPrecioUnitario());
            System.out.println("Cantidad vendida: " + producto.getCantidadVendida());
            if (producto.necesitaPedido()) {
                System.out.println("¡Es necesario hacer un pedido!");
            }
            System.out.println("--------------------------------------");
        }
    }

    //TODO->HACER PEDIDO DE PRODUCTO SEGUN ID PASADO DESDE INTERFAZ
    public void hacerPedido(int cantidad) {
        for (Producto producto : productos) {
            if (producto.necesitaPedido()) {
                producto.realizarUnPedido(cantidad);
            }
        }
    }

    //TODO->HACER PEDIDO DE TODOS LOS PRODUCTOS QUE HAY QUE HACER PEDIDO


    public void sincronizarProductosDB()throws Exception{
        try {
            ArrayList<ProductoDB> productosDB = consultadorDB.listarProducto();
            productos = new ArrayList<Producto>();
            for (ProductoDB productoDB : productosDB) {
                Producto producto = new Producto(productoDB.getId(), productoDB.getNombre(), buscarTipoProducto(productoDB.getTipo()), productoDB.getCantidadBodega(), productoDB.getCantidadMinimaP(), productoDB.getPrecioUnitario(), buscarTipodeIvaPorcentaje(productoDB.getIva()), productoDB.getCantidadVendida());
                productos.add(producto);
            }
        } catch (SQLException e) {
            throw new Exception("No se pudo acceder a la base de datos", null);
        }
    }

 
    //METODOS PRIVADOS-------------------------------------------------------------------------------------------------------------------------------
    /**
     * Busca un producto segun la id pasada por parametro. Null si no lo encuentra.
     * @param pId
     * @return
     */
    private Producto buscarProducto(int pId){
        for (Producto producto : productos) {
            if(producto.getId() == pId)
                return producto;
        }
        return null;
    }

    /**
     * Retorna el tipo de producto con el nombre pasado por parametro y si no retorna nulo
     * @param pNombre
     * @return
     */
    private TipoProducto buscarTipoProducto(String pNombre){
        TipoProducto[] lista = TipoProducto.values();
        for (TipoProducto tipoProducto : lista) {
            if (tipoProducto.getNombre().equals(pNombre)) {
                return tipoProducto;
            }
        }
        return null;
    }

    /**
     * Retnorna el tipo de iva con la descripción pasada por parametro y sino retorna nulo
     * @param pDescripcion
     * @return
     */
    private TipodeIva buscarTipodeIva(String pDescripcion){
        TipodeIva[] lista = TipodeIva.values();
        for (TipodeIva tipodeIva : lista) {
            if (tipodeIva.getDescripcion().equals(pDescripcion)) {
                return tipodeIva;
            }
        }
        return null;
    }

    private TipodeIva buscarTipodeIvaPorcentaje(Float pPorcentaje){
        TipodeIva[] lista = TipodeIva.values();
        for (TipodeIva tipodeIva : lista) {
            if (Float.compare(tipodeIva.getPorcentajeIva(), pPorcentaje) == 0) {
                return tipodeIva;
            }
        }
        return null;
    }
}
