package DDBB;
/**
 * Clase que representa una fila dentro de la tabla productos de la base de datos
 */

public class ProductoDB {
    //ATRIBUTOS--------------------------------------------------------------------------------------------------------------------------------------
    private int id;
    private String nombre;
    private String tipo;
    private int CantidadBodega;
    private int CantidadMinimaP;
    private float PrecioUnitario;
    private float Iva;
    private int cantidadVendida;

    //CONSTRUCTOR------------------------------------------------------------------------------------------------------------------------------------
    public ProductoDB(int pId,String pNombre, String pTipo, int pCantidadBodega, int pCantidadMinimaP, float pPrecioUnitario, float pIva, int pCantidadVendida) {
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

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCantidadBodega() {
        return CantidadBodega;
    }

    public int getCantidadMinimaP() {
        return CantidadMinimaP;
    }

    public float getPrecioUnitario() {
        return PrecioUnitario;
    }

    public float getIva() {
        return Iva;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }
    
}
