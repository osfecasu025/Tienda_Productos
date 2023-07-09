/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ACA2;

/**
 *
 * @author osfec
 */
public enum TipodeIva {
    
    
    IvaPapeleria_16(16),
    IvaSupermercado_4(4),
    IvaDrogueria_12(12);

    private final double porcentajeIva;

    private TipodeIva(double porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public double obtenerPorcentajeIva() {
        return porcentajeIva;
    }

    public double calcularIva(double precio) {
        return precio * (porcentajeIva / 100);
    }
}
