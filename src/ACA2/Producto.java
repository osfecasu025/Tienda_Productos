package ACA2;
/**
 * Clase que representa a un producto dentro de la tienda
 */
public class Producto {
    //ATRIBUTOS--------------------------------------------------------------------------------------------------------------------------------------
    private int id;
    private String nombre;
    private TipoProducto tipo;
    private int CantidadBodega;
    private int CantidadMinimaP;
    private float PrecioUnitario;
    private TipodeIva Iva;
    private int cantidadVendida;

    //CONSTRUCTOR------------------------------------------------------------------------------------------------------------------------------------
    public Producto(int pId,String pNombre, TipoProducto pTipo, int pCantidadBodega, int pCantidadMinimaP, float pPrecioUnitario, TipodeIva pIva, int pCantidadVendida) {
        this.id = pId;
        this.nombre = pNombre;
        this.tipo = pTipo;
        this.CantidadBodega = pCantidadBodega;
        this.CantidadMinimaP = pCantidadMinimaP;
        this.PrecioUnitario = pPrecioUnitario;
        this.Iva =pIva;
        this.cantidadVendida = pCantidadVendida;
    }
    
    //GETTERS AND SETTERS----------------------------------------------------------------------------------------------------------------------------

   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public int getCantidadBodega() {
        return CantidadBodega;
    }

    public void setCantidadBodega(int CantidadBodega) {
        this.CantidadBodega = CantidadBodega;
    }

    public int getCantidadMinimaP() {
        return CantidadMinimaP;
    }

    public void setCantidadMinimaP(int CantidadMinimaP) {
        this.CantidadMinimaP = CantidadMinimaP;
    }

    public float getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(float PrecioUnitario) {
        this.PrecioUnitario = PrecioUnitario;
    }

    public TipodeIva getIva() {
        return Iva;
    }

    public void setIva(TipodeIva Iva) {
        this.Iva = Iva;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    //METODOS----------------------------------------------------------------------------------------------------------------------------------------
    
    /**
     * Calcula el precio final del producto según su tipo de iva.
     * @return
     */
    public float calcularPrecioFinal() {
        return (float) (this.PrecioUnitario * (1 + this.Iva.getPorcentajeIva()));
    }

    /**
     * Si la cantidad pasada como argumento es mayor o igual a lo que hay en bodega le resta dicha cantidad a lo que hay en bodega y
     * se la suma a lo que ha vendido.
     * @param cantidad
     * @return True si se pudo realizar la venta, false si no.
     */
    public boolean ventaDeCliente(int cantidad) {
        if (cantidad <= CantidadBodega) {
            CantidadBodega -= cantidad;
            cantidadVendida += cantidad;
            return true;
        }
        return false;
    }

    /**
     * Suma a la cantidad que hay en bodega la cantidad que se indica como argumento.
     * @param cantidad
     */
    public void realizarUnPedido(int cantidad) {
        CantidadBodega += cantidad;
    }

    /**
     * Retorna true si la cantidad de bodega es menor o igual a la mínima necesaria para hacer un pedido.
     * @return
     */
    public boolean necesitaPedido() {
        return CantidadBodega <= CantidadMinimaP;
    }
}
