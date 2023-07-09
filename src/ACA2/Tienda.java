/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ACA2;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author osfec
 */
public class Tienda {
    
    public float Dinero;
     private List<Producto> productos;
    
     public Tienda() {
        Dinero = 0.0f;
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public float darGananciaTotal() {
        float gananciaTotal = 0.0f;
        for (Producto producto : productos) {
            gananciaTotal += producto.getPrecioUnitario() * producto.getCantidadVendida();
        }
        return gananciaTotal;
    }

    public float darPromedioVenta() {
        float totalUnidadesVendidas = 0.0f;
        float totalVentas = 0.0f;
        for (Producto producto : productos) {
            totalUnidadesVendidas += producto.getCantidadVendida();
            totalVentas += producto.getPrecioUnitario() * producto.getCantidadVendida();
        }
        return totalVentas / totalUnidadesVendidas;
    }

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

    public void hacerPedido(int cantidad) {
        for (Producto producto : productos) {
            if (producto.necesitaPedido()) {
                producto.realizarUnPedido(cantidad);
            }
        }
    }

   
}
