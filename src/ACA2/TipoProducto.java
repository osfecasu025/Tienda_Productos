package ACA2;
/**
 * Enumeración que representa todos los distintos productos que puede tener una tienda
 */
public enum TipoProducto {
    //ELEMENTOS--------------------------------------------------------------------------------------------------------------------------------------
    PAPELERIA("Papeleria"),
    SUPERMERCADO("Super Mercado"),
    DROGUERIA("Drogueria");

    //ATRIBUTOS--------------------------------------------------------------------------------------------------------------------------------------
    private String nombre;

    //CONSTRUCTOR------------------------------------------------------------------------------------------------------------------------------------
    private TipoProducto(String pNombre) {
        this.nombre = pNombre;
    }

    //GETTERS----------------------------------------------------------------------------------------------------------------------------------------
    public String getNombre() {
        return nombre;
    }
}
