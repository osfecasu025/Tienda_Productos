package ACA2;
/**
 * Enumeración que representa todos los distintos tipos de ivas aplicables a los productos.
 */
public enum TipodeIva{
    //ELEMENTOS--------------------------------------------------------------------------------------------------------------------------------------
    IvaPapeleria_16("Papeleria: 16%", 0.16f),
    IvaSupermercado_4("Super mercado: 4%", 0.04f),
    IvaDrogueria_12("Drogería: 12%", 0.12f);

    //ATRIBUTOS--------------------------------------------------------------------------------------------------------------------------------------
    private String descripcion;
    private float porcentajeIva;

    //CONSTRUCTOR------------------------------------------------------------------------------------------------------------------------------------
    private TipodeIva(String pDescripcion, float pPorcentajeIva) {
        this.descripcion = pDescripcion;
        this.porcentajeIva = pPorcentajeIva;
    }

    //GETTERS----------------------------------------------------------------------------------------------------------------------------------------
    public String getDescripcion() {
        return descripcion;
    }

    public float getPorcentajeIva() {
        return porcentajeIva;
    }
}
