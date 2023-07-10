package ACA2;
/**
 * Enumeración que representa todos los distintos tipos de ivas aplicables a los productos.
 */
public enum TipodeIva {
    //ELEMENTOS--------------------------------------------------------------------------------------------------------------------------------------
    IvaPapeleria_16(0.16f),
    IvaSupermercado_4(0.04f),
    IvaDrogueria_12(0.12f);

    //ATRIBUTOS--------------------------------------------------------------------------------------------------------------------------------------
    private float porcentajeIva;

    //CONSTRUCTOR------------------------------------------------------------------------------------------------------------------------------------
    private TipodeIva(float pPorcentajeIva) {
        this.porcentajeIva = pPorcentajeIva;
    }

    //GETTERS----------------------------------------------------------------------------------------------------------------------------------------
    public float getPorcentajeIva() {
        return porcentajeIva;
    }
}
