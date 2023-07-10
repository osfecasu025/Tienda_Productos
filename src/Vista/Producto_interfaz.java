/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;
import ACA2.Producto;
import ACA2.TipoProducto;
import ACA2.TipodeIva;
import Conexion.Conexion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author osfec
 */
public class Producto_interfaz extends javax.swing.JFrame {
    
    Producto producto;
    ArrayList<String> tiposProducto;
    ArrayList<String> tiposIva;
    public Producto_interfaz() throws SQLException {
        producto = new Producto();
        tiposProducto = producto.getTiposProducto();
        tiposIva = producto. getTiposIva();
         
         
         
         
        initComponents();
        Agregar();
        limpiarCampos();
        Actualizar();
        Eliminar();
    } 
    
    public void ComboBox(){
        for (tipoProducto : tiposProducto){
            tipo.addItem(tipoProducto);
        }
        
        for (tipoIva : tiposIva){
            iva.addItem(tipoIva);
        }
        
    
    }
    
    
    
    
     public void Agregar() {
    guarda.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Crear un objeto Producto con los datos ingresados en la interfaz
            producto.setId(Integer.parseInt(id.getText()));
            producto.setNombre(nombre.getText());
            producto.setTipo(TipoProducto.valueOf(tipo.getSelectedItem().toString()));
            producto.setCantidadBodega(Integer.parseInt(cantidadbodega.getText()));
            producto.setCantidadMinimaP(Integer.parseInt(cantidadminima.getText()));
            producto.setPrecioUnitario(Float.parseFloat(preciounitario.getText()));
            // Obtener el tipo de IVA seleccionado del JComboBox "iva"
            // Obtener el tipo de IVA seleccionado del JComboBox "iva"
                    String tipoIvaSeleccionado = (String) iva.getSelectedItem();
                    System.out.println("Tipo de IVA seleccionado: " + tipoIvaSeleccionado);

                    // Asignar el tipo de IVA correspondiente al valor seleccionado
                    TipodeIva tipoIva;
                    if (tipoIvaSeleccionado.equals("IvaPapeleria_16")) {
                        tipoIva = TipodeIva.IvaPapeleria_16;
                    } else if (tipoIvaSeleccionado.equals("IvaSupermercado_4")) {
                        tipoIva = TipodeIva.IvaSupermercado_4;
                    } else if (tipoIvaSeleccionado.equals("IvaDrogueria_12")) {
                        tipoIva = TipodeIva.IvaDrogueria_12;
                    } else {
                        // Valor por defecto si no se selecciona ningún tipo de IVA válido
                        tipoIva = null;  // Ajusta esto a lo que necesites en tu lógica
                    }
            producto.setIva(tipoIva);
             try {
                gestorConexion.insertarProducto(producto);
                // Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(null, "Producto insertado correctamente");
                // Limpiar los campos de la interfaz
                limpiarCampos();
            } catch (SQLException ex) {
                // Mostrar mensaje de error
                JOptionPane.showMessageDialog(null, "Error al insertar el producto: " + ex.getMessage());
            }
            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(null, "Producto insertado correctamente");
            // Limpiar los campos de la interfaz
            limpiarCampos();
        }
    });
}
     
   
    
    public void Actualizar(){
    modificar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
          producto.setId(Integer.parseInt(id.getText()));
            producto.setNombre(nombre.getText());
            producto.setTipo(TipoProducto.valueOf(tipo.getSelectedItem().toString()));
            producto.setCantidadBodega(Integer.parseInt(cantidadbodega.getText()));
            producto.setCantidadMinimaP(Integer.parseInt(cantidadminima.getText()));
            producto.setPrecioUnitario(Float.parseFloat(preciounitario.getText()));

                    String tipoIvaSeleccionado = (String) iva.getSelectedItem();
                    System.out.println("Tipo de IVA seleccionado: " + tipoIvaSeleccionado);

                    // Asignar el tipo de IVA correspondiente al valor seleccionado
                    TipodeIva tipoIva;
                    if (tipoIvaSeleccionado.equals("IvaPapeleria_16")) {
                        tipoIva = TipodeIva.IvaPapeleria_16;
                    } else if (tipoIvaSeleccionado.equals("IvaSupermercado_4")) {
                        tipoIva = TipodeIva.IvaSupermercado_4;
                    } else if (tipoIvaSeleccionado.equals("IvaDrogueria_12")) {
                        tipoIva = TipodeIva.IvaDrogueria_12;
                    } else {
                        // Valor por defecto si no se selecciona ningún tipo de IVA válido
                        tipoIva = null;  // Ajusta esto a lo que necesites en tu lógica
                    }
            producto.setIva(tipoIva);
              try {
                if (producto.getIva() != null) {
                    gestorConexion.actualizarProducto(producto);
                    // Mostrar mensaje de éxito
                    JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");
                    // Limpiar los campos de la interfaz
                    limpiarCampos();
                } else {
                    // Manejar el caso cuando el tipo de IVA es nulo
                    // Puedes lanzar una excepción, mostrar un mensaje de error o realizar otra acción según tus necesidades
                    throw new IllegalArgumentException("El tipo de IVA es nulo");
                }
            } catch (SQLException ex) {
                // Mostrar mensaje de error
                JOptionPane.showMessageDialog(null, "Error al actualizar el producto: " + ex.getMessage());
            }
            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");
            // Limpiar los campos de la interfaz
            limpiarCampos();
    }
});
    }
    
    public void Eliminar(){
    eliminar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Obtener el nombre del producto a eliminar
            int idProducto = Integer.parseInt(id.getText());

            // Llamar al método eliminarProducto() de la instancia gestorProductos
             gestorConexion.eliminarProducto(idProducto);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");

            // Limpiar los campos de la interfaz
            limpiarCampos();
        } catch (SQLException ex) {
            // Mostrar mensaje de error
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + ex.getMessage());
        }
    }
});
    }

    private void limpiarCampos() {
    limpiar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
    
        id.setText("");
        nombre.setText("");
        tipo.setSelectedIndex(0);
        cantidadbodega.setText("");
        cantidadminima.setText("");
        preciounitario.setText("");
        iva.setSelectedIndex(0);
    }
});
    
  
}

     




    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        siguiente = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        nombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cantidadbodega = new javax.swing.JTextField();
        cantidadminima = new javax.swing.JTextField();
        preciounitario = new javax.swing.JTextField();
        tipo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        iva = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        guarda = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        siguiente.setText("TIENDA");
        siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingresar Datos"));

        nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setText("ID:");

        jLabel2.setText("Nombre");

        jLabel5.setText("CantidadBodega");

        jLabel6.setText("Tipo");

        jLabel7.setText("CantidadMinima");

        jLabel8.setText("PrecioUnitario");

        cantidadbodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadbodegaActionPerformed(evt);
            }
        });

        tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PAPELERIA", "SUPERMERCADO", "DROGUERIA" }));
        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });

        jLabel9.setText("Iva");

        iva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IvaPapeleria_16", "IvaSupermercado_4", "IvaDrogueria_12" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .addComponent(id)
                            .addComponent(tipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(cantidadbodega, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(preciounitario, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cantidadminima, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(iva, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cantidadbodega, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(cantidadminima, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(preciounitario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(iva, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Operaciones"));

        guarda.setText("AGREGAR");
        guarda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardaActionPerformed(evt);
            }
        });

        limpiar.setText("LIMPIAR");

        modificar.setText("MODIFICAR");
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });

        eliminar.setText("ELIMINAR");
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
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(limpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(guarda, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(guarda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(limpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(eliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(siguiente)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteActionPerformed
             // TODO add your handling code here:
    }//GEN-LAST:event_siguienteActionPerformed

    private void cantidadbodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadbodegaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadbodegaActionPerformed

    private void guardaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guardaActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modificarActionPerformed

    private void tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoActionPerformed
       getTiposProducto() 
               
               return ArrayList<Strings>()
        
    }//GEN-LAST:event_tipoActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Producto_interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Producto_interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Producto_interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Producto_interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Producto_interfaz().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Producto_interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField cantidadbodega;
    public static javax.swing.JTextField cantidadminima;
    public javax.swing.JButton eliminar;
    public javax.swing.JButton guarda;
    public static javax.swing.JTextField id;
    private javax.swing.JComboBox<String> iva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    public javax.swing.JButton limpiar;
    public javax.swing.JButton modificar;
    public static javax.swing.JTextField nombre;
    public static javax.swing.JTextField preciounitario;
    public javax.swing.JButton siguiente;
    private javax.swing.JComboBox<String> tipo;
    // End of variables declaration//GEN-END:variables
}
