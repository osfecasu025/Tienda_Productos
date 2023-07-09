/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ACA2;

/**
 *
 * @author DANIEL
 */
public class Producto {
    public int id;
    public String nombre;
    public TipoProducto tipo;
    public int CantidadBodega;
    public int CantidadMinimaP;
    public float PrecioUnitario;
    public TipodeIva Iva;

  

    public Producto() {
    }

    public Producto(int id,String nombre, TipoProducto tipo, int CantidadBodega, int CantidadMinimaP, float PrecioUnitario, TipodeIva Iva) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.CantidadBodega = CantidadBodega;
        this.CantidadMinimaP = CantidadMinimaP;
        this.PrecioUnitario = PrecioUnitario;
        this.Iva =Iva;
    }
    
     
     public void calcularPrecioFinal() {
        float precioFinal = (float) (PrecioUnitario + (PrecioUnitario * (Iva.obtenerPorcentajeIva() / 100)));
        System.out.println("El precio final del producto '" + nombre + "' es: $" + precioFinal);
    }

    public void ventaDeCliente(int cantidad) {
        if (cantidad <= CantidadBodega) {
            CantidadBodega -= cantidad;
            cantidadVendida += cantidad;
            System.out.println("Venta realizada. Cantidad disponible actual: " + CantidadBodega);
        } else {
            System.out.println("No hay suficiente stock disponible para realizar la venta.");
        }
    }

    public void realizarUnPedido(int cantidad) {
        CantidadBodega += cantidad;
        System.out.println("Se ha realizado un pedido del producto '" + nombre + "'. Cantidad disponible actual: " + CantidadBodega);
    }
    
    public boolean necesitaPedido() {
        return CantidadBodega <= CantidadMinimaP;
    }

   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int cantidadVendida;
 

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
    
    
}
