package vistas;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class AgregarTarea extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AgregarTarea.class.getName());
    Gestor gestor; 
    public AgregarTarea() {
        initComponents();
        this.setLocationRelativeTo(this);
        cbxPrioridad.setSelectedIndex(-1);
    }
    
    public void guardarTareas(String titulo, String descripcion, String fecha, String prioridad){

        // Ruta del archivo donde se guardarán las tareas
        String ruta = "tareas.txt";

        // TRY WITH RESOURCES → se encarga de cerrar el archivo automáticamente
        try(
            // FileWriter → abre el archivo (true = modo agregar, no borrar lo anterior)
            FileWriter fw = new FileWriter(ruta, true);

            // BufferedWriter → mejora el rendimiento (escribe en memoria primero)
            BufferedWriter bw = new BufferedWriter(fw);

            // PrintWriter → permite escribir líneas fácilmente con println
            PrintWriter pw = new PrintWriter(bw)
        ){

            // Estado inicial de la tarea
            String estado = "Pendiente";

            // Guardamos los datos separados por "|" para poder leerlos después fácilmente
            pw.println(titulo + "|" + descripcion + "|" + fecha + "|" + prioridad + "|" + estado);

        }catch(IOException e){
            // Mensaje si ocurre un error al guardar
            JOptionPane.showMessageDialog(this, "Error al leer el archivo, tareas.txt no encontrado");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        lblNuevaTarea = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        lblPrioridad = new javax.swing.JLabel();
        cbxPrioridad = new javax.swing.JComboBox<>();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(102, 102, 102));
        lblTitulo.setText("Titulo");
        jPanel1.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        txtTitulo.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        txtTitulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 460, 40));

        lblNuevaTarea.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        lblNuevaTarea.setText("Nueva tarea");
        jPanel1.add(lblNuevaTarea, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        lblDescripcion.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(102, 102, 102));
        lblDescripcion.setText("Descripcion");
        jPanel1.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        txtDescripcion.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        txtDescripcion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 460, 40));

        txtFecha.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(102, 102, 102));
        txtFecha.setText("Ej: 04/05/2025");
        txtFecha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtFecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFechaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFechaFocusLost(evt);
            }
        });
        txtFecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtFechaMouseEntered(evt);
            }
        });
        txtFecha.addActionListener(this::txtFechaActionPerformed);
        jPanel1.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 460, 40));

        lblFecha.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(102, 102, 102));
        lblFecha.setText("Fecha");
        jPanel1.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        lblPrioridad.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblPrioridad.setForeground(new java.awt.Color(102, 102, 102));
        lblPrioridad.setText("Prioridad");
        jPanel1.add(lblPrioridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        cbxPrioridad.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        cbxPrioridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Baja", "Media", "Alta" }));
        cbxPrioridad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        cbxPrioridad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(cbxPrioridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 460, 30));

        btnCancelar.setBackground(new java.awt.Color(242, 242, 242));
        btnCancelar.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(51, 51, 51));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 430, -1, -1));

        btnGuardar.setBackground(new java.awt.Color(255, 102, 0));
        btnGuardar.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 430, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String titulo = txtTitulo.getText();
        String descripcion = txtDescripcion.getText();
        String fecha = txtFecha.getText();
        String prioridad = (String) cbxPrioridad.getSelectedItem();
        String estado = "Pendiente";
        gestor.agregarTarea(titulo, descripcion, fecha, prioridad, estado);
        guardarTareas(titulo, descripcion, fecha, prioridad);
        
        txtTitulo.setText("");
        txtDescripcion.setText("");
        txtFecha.setText("");
        cbxPrioridad.setSelectedIndex(-1);
        gestor.setAgregarTarea(this);
        this.setVisible(false);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        txtTitulo.setText("");
        txtDescripcion.setText("");
        txtFecha.setText("");
        cbxPrioridad.setSelectedIndex(-1);
        gestor.setAgregarTarea(this);
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void txtFechaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFechaMouseEntered
        
    }//GEN-LAST:event_txtFechaMouseEntered

    private void txtFechaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaFocusGained
        txtFecha.setText("");
        txtFecha.setForeground(Color.black);
    }//GEN-LAST:event_txtFechaFocusGained

    private void txtFechaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaFocusLost
        if (txtFecha.getText().equals("")) {
            txtFecha.setText("Ej: 04/05/2025: ");
        }
    }//GEN-LAST:event_txtFechaFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbxPrioridad;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNuevaTarea;
    private javax.swing.JLabel lblPrioridad;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables

    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }
}
