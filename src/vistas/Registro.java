package vistas;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Registro extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Registro.class.getName());
    private Login login;
    
    public Registro() {
        initComponents();
        this.setLocationRelativeTo(this);
    }
    
    public boolean verificarExistenciaCorreo(){
        String ruta = "usuarios.txt";
        String correo = txtCorreo.getText();
        
        try (FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr)) {

            String linea;

            while ((linea = br.readLine()) != null) {
                // Ejemplo de línea: Nombre: Henry Correo: henry@gmail.com Contraseña: henry123
                String[] partes = linea.split("\\|");
                /*partes[0] = "Henry" partes[1] = "henry@gmail.com"
                  partes[2] = "henry123"*/
                
                if (correo.equals(partes[1])){
                    return true;
                }             
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo");
        }
        return false;
    }
    
    public boolean verificar(){
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmarPassword.getPassword());
        
        boolean permiso = true;
        
        if (txtNombre.getText().trim().isEmpty() || txtCorreo.getText().trim().isEmpty() ||
            password.trim().isEmpty() || confirmPassword.trim().isEmpty()){
            
            permiso = false;
            
            JOptionPane.showMessageDialog(this, "Por favor llena todos los campos");
        }
        
        if (!password.equals(confirmPassword) && permiso) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden");
            permiso = false;
        }
        
        if (verificarExistenciaCorreo() == true) {
            JOptionPane.showMessageDialog(this, "Este correo ya pertenece a una cuenta\nCambie la contraseña o utilice un nuevo correo");
            permiso = false;
        }
        
        return permiso;
    }
    
    public void guardarUsuarios(String nombre, String correo, String password){
        String ruta = "usuarios.txt";
        
        try(FileWriter fw = new FileWriter(ruta, true); //se crea el documento con true o lo cargo si ya esta creado
            BufferedWriter bw = new BufferedWriter(fw); //lo guardo o lo tengo ahi en memoria
            PrintWriter pw = new PrintWriter(bw)){   //Con esto ya hago la escritura sobre el documento
            
            pw.println(nombre + "|" + correo + "|" + password); //Aqui yo voy a colocar lo que yo quiera almancenar, tal como lo quiera almacenar
        }catch(IOException e){ //Voy a manejar una excepcion y me voy a referir a ella como e
            JOptionPane.showConfirmDialog(this, "error exception filenotfoundexception is never thrown in body "
                    + "of corresponding try statement");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblGuion = new javax.swing.JLabel();
        lblTitle3 = new javax.swing.JLabel();
        lblTitle2 = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblSharkTask = new javax.swing.JLabel();
        pnlLogin = new javax.swing.JPanel();
        lblCrearCuenta = new javax.swing.JLabel();
        lblSubCrearCuenta = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        btnCrearCuenta = new javax.swing.JButton();
        lblPregunta = new javax.swing.JLabel();
        btnInicioSesion = new javax.swing.JButton();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        lblConfirmarPassword = new javax.swing.JLabel();
        txtConfirmarPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setSize(new java.awt.Dimension(1366, 768));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/blackGithub32px.png"))); // NOI18N
        jLabel2.setText("@ByHenry99");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 650, -1, -1));

        lblGuion.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        lblGuion.setForeground(new java.awt.Color(255, 102, 0));
        lblGuion.setText("-----");
        jPanel1.add(lblGuion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, -1, -1));

        lblTitle3.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        lblTitle3.setText("Gestiona tus tareas, colabora con tu equipo y mantén todo bajo control en un solo lugar.");
        jPanel1.add(lblTitle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, -1, -1));

        lblTitle2.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        lblTitle2.setText("Entrega Sin Estrés.");
        jPanel1.add(lblTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, -1, -1));

        lblTitle.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        lblTitle.setText("Organiza Mejor. Trabaja Más Rapido.");
        jPanel1.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, -1, -1));

        lblSharkTask.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        lblSharkTask.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/diamante24.png"))); // NOI18N
        lblSharkTask.setText("SharkTask");
        jPanel1.add(lblSharkTask, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        pnlLogin.setBackground(new java.awt.Color(255, 255, 255));

        lblCrearCuenta.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        lblCrearCuenta.setText("Crear cuenta");

        lblSubCrearCuenta.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        lblSubCrearCuenta.setText("Crea una cuenta para empezar a gestionar tus tareas.");

        lblNombre.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        lblNombre.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        txtNombre.addActionListener(this::txtNombreActionPerformed);

        lblCorreo.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        lblCorreo.setText("Correo:");

        txtCorreo.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        txtCorreo.addActionListener(this::txtCorreoActionPerformed);

        btnCrearCuenta.setBackground(new java.awt.Color(255, 102, 0));
        btnCrearCuenta.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        btnCrearCuenta.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearCuenta.setText("Crear cuenta");
        btnCrearCuenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 0));
        btnCrearCuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCrearCuentaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCrearCuentaMouseExited(evt);
            }
        });
        btnCrearCuenta.addActionListener(this::btnCrearCuentaActionPerformed);

        lblPregunta.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        lblPregunta.setForeground(new java.awt.Color(102, 102, 102));
        lblPregunta.setText("¿Ya tienes una cuenta?");

        btnInicioSesion.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        btnInicioSesion.setForeground(new java.awt.Color(255, 102, 0));
        btnInicioSesion.setText("Inicio de sesión");
        btnInicioSesion.setBorder(null);
        btnInicioSesion.setContentAreaFilled(false);
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

        lblPassword.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        lblPassword.setText("Contraseña:");

        txtPassword.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        txtPassword.addActionListener(this::txtPasswordActionPerformed);

        lblConfirmarPassword.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        lblConfirmarPassword.setText("Confirmar contraseña:");

        txtConfirmarPassword.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        txtConfirmarPassword.addActionListener(this::txtConfirmarPasswordActionPerformed);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/LuisAmigo.png"))); // NOI18N

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCorreo)
                            .addComponent(txtCorreo)
                            .addComponent(lblNombre)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPassword)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblConfirmarPassword)
                            .addComponent(txtConfirmarPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(lblPregunta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnInicioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblSubCrearCuenta)
                    .addComponent(lblCrearCuenta))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblCrearCuenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSubCrearCuenta)
                .addGap(18, 18, 18)
                .addComponent(lblNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCorreo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblConfirmarPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConfirmarPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnCrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPregunta)
                    .addComponent(btnInicioSesion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel1.add(pnlLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 20, 490, 630));

        lblFondo.setForeground(new java.awt.Color(255, 255, 255));
        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/fondo3.png"))); // NOI18N
        jPanel1.add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1470, -1));

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

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void btnCrearCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCuentaActionPerformed
        String nombre =  txtNombre.getText();
        String correo = txtCorreo.getText();
        String password = new String (txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmarPassword.getPassword());
        
        if (verificar()) {
            guardarUsuarios(nombre, correo, password);
            
            JOptionPane.showMessageDialog(this, "Se ha creado la cuenta exitosamente");
            
            txtNombre.setText("");
            txtCorreo.setText("");
            txtPassword.setText("");
            txtConfirmarPassword.setText("");
            
            login.limpiar();
            login.setRegistro(this);
            login.setVisible(true);
            this.setVisible(false);
        }
        
    }//GEN-LAST:event_btnCrearCuentaActionPerformed

    private void btnInicioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioSesionActionPerformed
        txtNombre.setText("");
        txtCorreo.setText("");
        txtPassword.setText("");
        txtConfirmarPassword.setText("");
        
        login.limpiar();
        login.setRegistro(this);
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnInicioSesionActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void txtConfirmarPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConfirmarPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConfirmarPasswordActionPerformed

    private void btnCrearCuentaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearCuentaMouseEntered
        btnCrearCuenta.setBackground(new Color(204,102,0));
    }//GEN-LAST:event_btnCrearCuentaMouseEntered

    private void btnCrearCuentaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearCuentaMouseExited
        btnCrearCuenta.setBackground(new Color(255,102,0));
    }//GEN-LAST:event_btnCrearCuentaMouseExited

    private void btnInicioSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioSesionMouseEntered
        btnInicioSesion.setForeground(new Color(204,102,0));
    }//GEN-LAST:event_btnInicioSesionMouseEntered

    private void btnInicioSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioSesionMouseExited
        btnInicioSesion.setForeground(new Color(255,102,0));
    }//GEN-LAST:event_btnInicioSesionMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearCuenta;
    private javax.swing.JButton btnInicioSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblConfirmarPassword;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblCrearCuenta;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblGuion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPregunta;
    private javax.swing.JLabel lblSharkTask;
    private javax.swing.JLabel lblSubCrearCuenta;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitle2;
    private javax.swing.JLabel lblTitle3;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPasswordField txtConfirmarPassword;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables

    public void setLogin(Login login) {
        this.login = login;
    }
}
