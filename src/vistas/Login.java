package vistas;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Login.class.getName());
    private Registro registro;
    private Gestor gestor;
    private Configuracion config;
    
    public Login() {
        initComponents();
        this.setLocationRelativeTo(this);
        registro = new Registro();
        gestor = new Gestor();
    }
    
    public void limpiar(){
        txtCorreo.setText("");
        txtPassword.setText("");
    }
    
    private boolean verificarExistenciaCuenta() {
        String ruta = "usuarios.txt";
        String password = new String(txtPassword.getPassword());
        boolean acceso = false;
        
        try (FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr)) {

            String linea;

            while ((linea = br.readLine()) != null) {
                // Ejemplo de línea: Nombre: Henry Correo: henry@gmail.com Contraseña: henry123
                String[] partes = linea.split("\\|");
                /*partes[0] = "Henry" partes[1] = "henry@gmail.com"
                  partes[2] = "henry123"*/
                
                if (partes[1].equals(txtCorreo.getText()) && partes[2].equals(password)) {
                    acceso = true;
                    gestor.DatosUsuario(partes[0], partes[1]);
                }
                
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo, usuarios.txt no encontrado");
        }

        return acceso;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitle2 = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblGuion = new javax.swing.JLabel();
        lblTitle3 = new javax.swing.JLabel();
        lblSharkTask1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnlLogin = new javax.swing.JPanel();
        lblBienvenido = new javax.swing.JLabel();
        lblSubBienvenido = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        btnInicioSesion = new javax.swing.JButton();
        lblPregunta = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setSize(new java.awt.Dimension(1366, 768));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle2.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        lblTitle2.setText("Entrega Sin Estrés.");
        jPanel1.add(lblTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, -1, -1));

        lblTitle.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        lblTitle.setText("Organiza Mejor. Trabaja Más Rapido.");
        jPanel1.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, -1, -1));

        lblGuion.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        lblGuion.setForeground(new java.awt.Color(255, 102, 0));
        lblGuion.setText("-----");
        jPanel1.add(lblGuion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, -1, -1));

        lblTitle3.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        lblTitle3.setText("Gestiona tus tareas, colabora con tu equipo y mantén todo bajo control en un solo lugar.");
        jPanel1.add(lblTitle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, -1, -1));

        lblSharkTask1.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        lblSharkTask1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/diamante24.png"))); // NOI18N
        lblSharkTask1.setText("SharkTask");
        jPanel1.add(lblSharkTask1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabel2.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/blackGithub32px.png"))); // NOI18N
        jLabel2.setText("@ByHenry99");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 650, -1, -1));

        pnlLogin.setBackground(new java.awt.Color(255, 255, 255));

        lblBienvenido.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        lblBienvenido.setText("Bienvenido de nuevo");

        lblSubBienvenido.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        lblSubBienvenido.setText("Inicia sesion para empezar a gestionar tus tareas.");

        lblCorreo.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        lblCorreo.setText("Correo:");

        txtCorreo.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        txtCorreo.addActionListener(this::txtCorreoActionPerformed);

        lblPassword.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        lblPassword.setText("Contraseña:");

        btnInicioSesion.setBackground(new java.awt.Color(255, 102, 0));
        btnInicioSesion.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        btnInicioSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnInicioSesion.setText("Iniciar sesión");
        btnInicioSesion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 0));
        btnInicioSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInicioSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInicioSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInicioSesionMouseExited(evt);
            }
        });
        btnInicioSesion.addActionListener(this::btnInicioSesionActionPerformed);

        lblPregunta.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        lblPregunta.setForeground(new java.awt.Color(102, 102, 102));
        lblPregunta.setText("¿No tienes una cuenta?");

        btnRegistrar.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 102, 0));
        btnRegistrar.setText("Registrar");
        btnRegistrar.setBorder(null);
        btnRegistrar.setContentAreaFilled(false);
        btnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseExited(evt);
            }
        });
        btnRegistrar.addActionListener(this::btnRegistrarActionPerformed);

        txtPassword.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/z2.png"))); // NOI18N
        jLabel1.setAutoscrolls(true);

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPregunta)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(39, 39, 39))
                        .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblPassword)
                            .addComponent(lblCorreo)
                            .addComponent(txtCorreo)
                            .addComponent(btnInicioSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createSequentialGroup()
                                .addComponent(lblBienvenido)
                                .addGap(70, 70, 70))
                            .addComponent(lblSubBienvenido, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBienvenido)
                .addGap(18, 18, 18)
                .addComponent(lblSubBienvenido)
                .addGap(24, 24, 24)
                .addComponent(lblCorreo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnInicioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPregunta)
                    .addComponent(btnRegistrar))
                .addContainerGap(111, Short.MAX_VALUE))
        );

        jPanel1.add(pnlLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 20, 490, 630));

        lblFondo.setForeground(new java.awt.Color(255, 255, 255));
        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/fondo3.png"))); // NOI18N
        jPanel1.add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-100, 0, 1470, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1366, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void btnInicioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioSesionActionPerformed
        if (verificarExistenciaCuenta() == true) {
            limpiar();
            JOptionPane.showMessageDialog(this, "Cargando perfil...");
            gestor.setLogin(this);
            gestor.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "La cuenta no existe o la contraseña es incorrecta", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnInicioSesionActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        registro.setLogin(this);
        registro.setVisible(true);
        this.setVisible(false); 
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnRegistrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseEntered
        btnRegistrar.setForeground(new Color(204,102,0));
    }//GEN-LAST:event_btnRegistrarMouseEntered

    private void btnRegistrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseExited
        btnRegistrar.setForeground(new Color(255,102,0));
    }//GEN-LAST:event_btnRegistrarMouseExited

    private void btnInicioSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioSesionMouseEntered
        btnInicioSesion.setBackground(new Color(204,102,0));
    }//GEN-LAST:event_btnInicioSesionMouseEntered

    private void btnInicioSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioSesionMouseExited
        btnInicioSesion.setBackground(new Color(255,102,0));
    }//GEN-LAST:event_btnInicioSesionMouseExited

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInicioSesion;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBienvenido;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblGuion;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPregunta;
    private javax.swing.JLabel lblSharkTask1;
    private javax.swing.JLabel lblSubBienvenido;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitle2;
    private javax.swing.JLabel lblTitle3;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }    

    public void setConfig(Configuracion config) {
        this.config = config;
    }
    
}
