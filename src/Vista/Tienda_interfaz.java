/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author osfec
 */
public class Tienda_interfaz extends javax.swing.JPanel {
    
    private JTable tabla;
    private DefaultTableModel model;
    
    /**
     * Creates new form Tienda_interfaz
     */
    public Tienda_interfaz() {
        initComponents();
        columnas();
        llenarTabla();
        Vender();
        refrescar();
        Pedido();
        mayorVendida();
        menorVendida();
        actualizarMayoryMenor();
        
    }
    
    private void columnas(){
        
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Producto");
        model.addColumn("Cantidad");
        model.addColumn("IVA");
        model.addColumn("Precio");
        model.addColumn("Pedido");
        table.setModel(model);
       
    }
    
    public void Pedido(){
    pedir.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
     
            // Mostrar un JOptionPane para seleccionar el producto a vender
            String selectedProduct = (String) JOptionPane.showInputDialog(null, "Selecciona un producto",
                    "Pedido de productos", JOptionPane.QUESTION_MESSAGE, null, getProductosDisponibles(), null);

                if (selectedProduct != null) {
                                // Mostrar otro JOptionPane para ingresar la cantidad a vender
                    String input = JOptionPane.showInputDialog(null,
                   "Ingrese la cantidad a pedir para el producto " + selectedProduct, "Venta de productos",
                   JOptionPane.QUESTION_MESSAGE);

                if (input != null) {
                    int cantidadVenta = Integer.parseInt(input);
                   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/tienda", "root", "");
                String sql = "SELECT cantidadBodega, precioUnitario, iva FROM productos WHERE nombre = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, selectedProduct);
                    ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int cantidadBodega = resultSet.getInt("cantidadBodega");
                    float precioUnitario = resultSet.getFloat("precioUnitario");
                    float iva = resultSet.getFloat("iva");

                    if (cantidadVenta >= cantidadBodega) {
                        

                        // Restar la cantidad vendida a la cantidad en bodega
                        int nuevaCantidadBodega = cantidadBodega + cantidadVenta;
                        actualizarCantidadBodega(selectedProduct, nuevaCantidadBodega, cantidadVenta);

                        // Mostrar el detalle de la venta al usuario
                        String mensaje = "Producto: " + selectedProduct + "\n" +
                                "Cantidad vendida: " + cantidadVenta + "\n";
                        JOptionPane.showMessageDialog(null, mensaje, "Venta realizada", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se puede realizar el pedido",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el producto en la base de datos",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

                resultSet.close();
                statement.close();
                    
                } else {
                    System.out.println("error");
                }
               
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    });
            }
    
    public void Vender(){
    vender1.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
     
            // Mostrar un JOptionPane para seleccionar el producto a vender
            String selectedProduct = (String) JOptionPane.showInputDialog(null, "Selecciona un producto",
                    "Venta de productos", JOptionPane.QUESTION_MESSAGE, null, getProductosDisponibles(), null);

                if (selectedProduct != null) {
                                // Mostrar otro JOptionPane para ingresar la cantidad a vender
                    String input = JOptionPane.showInputDialog(null,
                   "Ingrese la cantidad a vender para el producto " + selectedProduct, "Venta de productos",
                   JOptionPane.QUESTION_MESSAGE);

                if (input != null) {
                    int cantidadVenta = Integer.parseInt(input);
                   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/tienda", "root", "");
                String sql = "SELECT cantidadBodega, precioUnitario, iva FROM productos WHERE nombre = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, selectedProduct);
                    ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int cantidadBodega = resultSet.getInt("cantidadBodega");
                    float precioUnitario = resultSet.getFloat("precioUnitario");
                    float iva = resultSet.getFloat("iva");

                    if (cantidadVenta <= cantidadBodega) {
                        // Realizar las operaciones de venta y actualizar la base de datos
                        float precioTotalConIva = precioUnitario * (1 + (iva / 100));
                        float precioTotal = cantidadVenta * precioTotalConIva;
                        
                        ingresos.setText(String.valueOf(precioTotal));

                        // Restar la cantidad vendida a la cantidad en bodega
                        int nuevaCantidadBodega = cantidadBodega - cantidadVenta;
                        actualizarCantidadBodega(selectedProduct, nuevaCantidadBodega,cantidadVenta );
                        
                        // Mostrar el detalle de la venta al usuario
                        String mensaje = "Producto: " + selectedProduct + "\n" +
                                "Cantidad vendida: " + cantidadVenta + "\n" +
                                "Precio total sin IVA: " + precioTotal + "\n" +
                                "Precio total con IVA: " + precioTotalConIva;
                        JOptionPane.showMessageDialog(null, mensaje, "Venta realizada", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay suficiente cantidad en bodega para realizar la venta",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el producto en la base de datos",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

                resultSet.close();
                statement.close();
                    
                } else {
                    System.out.println("error");
                }
               
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    });
            }
    
    private Object[] getProductosDisponibles() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/tienda", "root", "");
        String sql = "SELECT nombre FROM productos";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(sql);

        // Obtener la cantidad de productos en el ResultSet
        resultSet.last();
        int rowCount = resultSet.getRow();
        resultSet.beforeFirst();

        // Crear un array para almacenar los nombres de los productos
        String[] productos = new String[rowCount];

        // Almacenar los nombres de los productos en el array
        int index = 0;
        while (resultSet.next()) {
            productos[index] = resultSet.getString("nombre");
            index++;
        }

        resultSet.close();
        statement.close();

        return productos;
    }
    
    private void actualizarCantidadBodega(String producto, int nuevaCantidad, int cantidadVendida) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/tienda", "root", "");
        String sql = "UPDATE productos SET cantidadBodega = ? WHERE nombre = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, nuevaCantidad);
        statement.setString(2, producto);
        statement.executeUpdate();
        
        String sqlUpdate = "UPDATE productos SET cantidadVendida = cantidadVendida + cantidadVendida ? WHERE nombre = ?";
        PreparedStatement statementUpdate = connection.prepareStatement(sqlUpdate);
        statementUpdate.setInt(1, cantidadVendida);
        statementUpdate.setString(2, producto);
        statementUpdate.executeUpdate();
        statement.close();
    }
    
    public void mayorVendida(){
        try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/tienda", "root", "");
        String sql = "SELECT * FROM productos WHERE cantidadVendida = (SELECT MAX(cantidadVendida) FROM productos) ORDER BY cantidadVendida DESC LIMIT 1";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            // Obtener los datos del producto con la mayor cantidad vendida
            String nombre = resultSet.getString("nombre");
            masvendido.setText(String.valueOf(nombre));
        }  
            } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
    }
    public void menorVendida(){
        try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/tienda", "root", "");
        String sql = "SELECT * FROM productos WHERE cantidadVendida = (SELECT MIN(cantidadVendida) FROM productos) ORDER BY cantidadVendida ASC LIMIT 1";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            // Obtener los datos del producto con la mayor cantidad vendida
            String nombre = resultSet.getString("nombre");
            menosvendido.setText(String.valueOf(nombre));
        }  
            } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
    }
      
    public void actualizarMayoryMenor(){
        actualizar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/tienda", "root", "");
        String sql = "SELECT * FROM productos WHERE cantidadVendida = (SELECT MAX(cantidadVendida) FROM productos) ORDER BY cantidadVendida DESC LIMIT 1";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            // Obtener los datos del producto con la mayor cantidad vendida
            String nombre = resultSet.getString("nombre");
            masvendido.setText(String.valueOf(nombre));
        }
        
        String sql1 = "SELECT * FROM productos WHERE cantidadVendida = (SELECT MIN(cantidadVendida) FROM productos) ORDER BY cantidadVendida ASC LIMIT 1";
        Statement statements = connection.createStatement();
        ResultSet resultSeta = statements.executeQuery(sql1);

        if (resultSeta.next()) {
            // Obtener los datos del producto con la mayor cantidad vendida
            String nombre = resultSeta.getString("nombre");
            menosvendido.setText(String.valueOf(nombre));
        }  
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    });
    }
    
    
    public void refrescar(){
        refrescar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/tienda", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM productos");
            model.setRowCount(0);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String producto = resultSet.getString("nombre");
                int cantidad = resultSet.getInt("cantidadBodega");
                int cantidadMinima = resultSet.getInt("cantidadMinima");
                String iva = resultSet.getString("iva");
                double precio = resultSet.getDouble("precioUnitario");
                

                // Realizar los cálculos necesarios para el pedido
                String pedido = cantidad <= cantidadMinima ? "No" : "Sí";
                
                model.addRow(new Object[]{id, producto, cantidad, iva, precio, pedido});
            }

            resultSet.close();
            statement.close();
            connection.close();
            
            
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    });
    }
    
    
    
    
    public void llenarTabla(){
      try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/tienda", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM productos");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String producto = resultSet.getString("nombre");
                int cantidad = resultSet.getInt("cantidadBodega");
                int cantidadMinima = resultSet.getInt("cantidadMinima");
                String iva = resultSet.getString("iva");
                double precio = resultSet.getDouble("precioUnitario");
                

                // Realizar los cálculos necesarios para el pedido
                String pedido = cantidad <= cantidadMinima ? "NO" : "SI";
                
                model.addRow(new Object[]{id, producto, cantidad, iva, precio, pedido});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        siguiente = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        refrescar = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        masvendido = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        ingresos = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cantidadbodega2 = new javax.swing.JTextField();
        menosvendido = new javax.swing.JTextField();
        actualizar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        vender1 = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        pedir = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();

        jPanel4.setToolTipText("NOSE");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 583, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );

        jPanel4.getAccessibleContext().setAccessibleName("Productos");

        siguiente.setText("PRODUCTOS");
        siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("PRODUCTOS"));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        refrescar.setText("REFRESCAR");
        refrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refrescarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(469, 469, 469)
                .addComponent(refrescar, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(refrescar)
                .addGap(0, 436, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addGap(0, 32, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("CÁLCULOS"));

        jPanel10.setName(""); // NOI18N

        masvendido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel15.setText("INGRESOS");

        jLabel16.setText("PRODUCTO MAS VENDIDO");

        jLabel17.setText("PROMEDIO");

        jLabel18.setText("PRODUCTO MENOS VENDIDO");

        cantidadbodega2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadbodega2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(243, 243, 243)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cantidadbodega2, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(masvendido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(ingresos)
                    .addComponent(menosvendido))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15))
                    .addComponent(ingresos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(masvendido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menosvendido, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(cantidadbodega2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        actualizar.setText("REFRESCAR");
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(actualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.getAccessibleContext().setAccessibleName("datos");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("OPERACIONES"));

        vender1.setText("VENDER PRODUCTO");
        vender1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vender1ActionPerformed(evt);
            }
        });

        limpiar.setText("OPCION 1");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });

        pedir.setText("PEDIR PRODUCTO");
        pedir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pedirActionPerformed(evt);
            }
        });

        eliminar.setText("OPCION 2");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pedir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vender1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(65, 65, 65)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(limpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vender1)
                    .addComponent(limpiar))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pedir)
                    .addComponent(eliminar))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 495, Short.MAX_VALUE)
                        .addComponent(siguiente))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.getAccessibleContext().setAccessibleName("Productos");
        jPanel9.getAccessibleContext().setAccessibleName("Calculos");
        jPanel9.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_siguienteActionPerformed

    private void vender1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vender1ActionPerformed
       
    }//GEN-LAST:event_vender1ActionPerformed

    private void pedirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pedirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pedirActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarActionPerformed

    private void cantidadbodega2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadbodega2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadbodega2ActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_limpiarActionPerformed

    private void refrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refrescarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refrescarActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_actualizarActionPerformed
 public static void main(String[] args) {
        // Crear una instancia de la interfaz
        Tienda_interfaz interfaz = new Tienda_interfaz();

        // Configurar la ventana principal
        JFrame frame = new JFrame("Mi Aplicación");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(interfaz);
        frame.pack();
        frame.setVisible(true);
    }







    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton actualizar;
    public static javax.swing.JTextField cantidadbodega2;
    public javax.swing.JButton eliminar;
    public static javax.swing.JTextField ingresos;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JButton limpiar;
    public static javax.swing.JTextField masvendido;
    public static javax.swing.JTextField menosvendido;
    public javax.swing.JButton pedir;
    public javax.swing.JButton refrescar;
    public javax.swing.JButton siguiente;
    private javax.swing.JTable table;
    public javax.swing.JButton vender1;
    // End of variables declaration//GEN-END:variables

    private void setDefaultCloseOperation(int EXIT_ON_CLOSE) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    }
