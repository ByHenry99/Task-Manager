package vistas;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Configuracion extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Configuracion.class.getName());
    private Gestor gestor;
    private Historial historial;
    private Completada completadas;
    private Login login;
    String correoUsuario;
    
    public Configuracion() {
        initComponents();
        this.setLocationRelativeTo(this);
    }
    
    public void DatosUsuario(String usuario, String correo){
        lblUsuario.setText(usuario);
        lblCorreo.setText(correo);
        this.correoUsuario  = correo;
    }
    
    public void mostrarInformacion() {
        String info = """
        SharkTask - Gestor de Tareas

        SharkTask es una aplicación de escritorio desarrollada en Java
        que permite gestionar tareas de manera sencilla y organizada.

        Funciones principales:
        - Crear tareas
        - Marcar tareas como completadas
        - Eliminar tareas
        - Historial de tareas
        - Gestión de cuenta
        - Cambio de contraseña

        Desarrollado por: [Henry Zuñiga]
        Versión: 1.0
        Año: 2026

        Gracias por usar SharkTask.
        """;

        JOptionPane.showMessageDialog(this, info, "Acerca de SharkTask", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void eliminarCuenta() {
        String ruta = "usuarios.txt";
        ArrayList<String> lineas = new ArrayList<>();
        
        String correoUsuario = this.correoUsuario;
        
        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que deseas eliminar tu cuenta?",
                "Eliminar cuenta",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");

                // datos[0] nombre
                // datos[1] correo
                // datos[2] contraseña

                if (!datos[1].trim().equals(correoUsuario)) {
                    lineas.add(linea);
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer archivo");
        }

        // Reescribir archivo sin el usuario eliminado
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            for (String l : lineas) {
                pw.println(l);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al escribir archivo");
        }

        JOptionPane.showMessageDialog(this, "Cuenta eliminada correctamente");

        // Volver al login
        login.setVisible(true);
        this.dispose();
    }
    
    public void cambiarPassword() {
        String ruta = "usuarios.txt";
        String password = JOptionPane.showInputDialog(this, "Ingrese su contraseña actual");

        if (password == null) return;
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede estar vacío");
            return;
        }

        ArrayList<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            String linea;
            boolean cambiada = false;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");

                if (password.equals(partes[2])) {

                    String nuevaPassword = JOptionPane.showInputDialog(this, "Ingrese nueva contraseña");
                    
                    if (nuevaPassword == null || nuevaPassword.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "No puede estar vacía");
                        return;
                    }

                    partes[2] = nuevaPassword;
                    linea = String.join("|", partes);
                    cambiada = true;
                }

                lineas.add(linea);
            }

            // Reescribir archivo
            PrintWriter pw = new PrintWriter(new FileWriter(ruta));
            for (String l : lineas) {
                pw.println(l);
            }
            pw.close();

            if (cambiada) {
                JOptionPane.showMessageDialog(this, "Contraseña cambiada correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Contraseña incorrecta");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        panelLuisAmigo = new javax.swing.JPanel();
        lblLuisAmigo = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        btnInicio = new javax.swing.JButton();
        btnHistorial = new javax.swing.JButton();
        btnCompletadas = new javax.swing.JButton();
        tbnConfiguracion = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        panelDecorativoSuperior = new javax.swing.JPanel();
        panelDecorativoInferior = new javax.swing.JPanel();
        panelDecorativoIzquierda = new javax.swing.JPanel();
        panelDecorativoDerecha = new javax.swing.JPanel();
        lblFotoUsuario = new javax.swing.JLabel();
        panelDecorativoSuperior1 = new javax.swing.JPanel();
        lblTitulo1 = new javax.swing.JLabel();
        panelInferior = new javax.swing.JPanel();
        btnInformacion = new javax.swing.JButton();
        btnEliminarCuenta = new javax.swing.JButton();
        btnCambiarPassword = new javax.swing.JButton();
        lblP2 = new javax.swing.JLabel();
        lblP1 = new javax.swing.JLabel();
        lblI2 = new javax.swing.JLabel();
        lblI1 = new javax.swing.JLabel();
        lblE2 = new javax.swing.JLabel();
        lblE1 = new javax.swing.JLabel();
        protegerIcon = new javax.swing.JLabel();
        lnlInfo = new javax.swing.JLabel();
        lblTrash = new javax.swing.JLabel();
        panelPerfil = new javax.swing.JPanel();
        btnCerrarSesion = new javax.swing.JButton();
        lblBienvenido = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelLuisAmigo.setBackground(new java.awt.Color(255, 255, 255));

        lblLuisAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/LuisAmigo2.png"))); // NOI18N

        javax.swing.GroupLayout panelLuisAmigoLayout = new javax.swing.GroupLayout(panelLuisAmigo);
        panelLuisAmigo.setLayout(panelLuisAmigoLayout);
        panelLuisAmigoLayout.setHorizontalGroup(
            panelLuisAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
            .addGroup(panelLuisAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelLuisAmigoLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblLuisAmigo)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelLuisAmigoLayout.setVerticalGroup(
            panelLuisAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
            .addGroup(panelLuisAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelLuisAmigoLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblLuisAmigo)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel2.add(panelLuisAmigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 290, 140));

        panelBotones.setBackground(new java.awt.Color(0, 153, 255));
        panelBotones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnInicio.setBackground(new java.awt.Color(102, 153, 255));
        btnInicio.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(255, 255, 255));
        btnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/whiteHouse24px.png"))); // NOI18N
        btnInicio.setText("Inicio");
        btnInicio.setBorder(null);
        btnInicio.setBorderPainted(false);
        btnInicio.setContentAreaFilled(false);
        btnInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInicioMouseExited(evt);
            }
        });
        btnInicio.addActionListener(this::btnInicioActionPerformed);
        panelBotones.add(btnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 290, 40));

        btnHistorial.setBackground(new java.awt.Color(102, 153, 255));
        btnHistorial.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        btnHistorial.setForeground(new java.awt.Color(255, 255, 255));
        btnHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/whiteTask24px.png"))); // NOI18N
        btnHistorial.setText("Historial");
        btnHistorial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255), 0));
        btnHistorial.setBorderPainted(false);
        btnHistorial.setContentAreaFilled(false);
        btnHistorial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHistorialMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHistorialMouseExited(evt);
            }
        });
        btnHistorial.addActionListener(this::btnHistorialActionPerformed);
        panelBotones.add(btnHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 290, 40));

        btnCompletadas.setBackground(new java.awt.Color(102, 153, 255));
        btnCompletadas.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        btnCompletadas.setForeground(new java.awt.Color(255, 255, 255));
        btnCompletadas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/whiteCompleted24px.png"))); // NOI18N
        btnCompletadas.setText("Completadas");
        btnCompletadas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255), 0));
        btnCompletadas.setBorderPainted(false);
        btnCompletadas.setContentAreaFilled(false);
        btnCompletadas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCompletadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCompletadasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCompletadasMouseExited(evt);
            }
        });
        btnCompletadas.addActionListener(this::btnCompletadasActionPerformed);
        panelBotones.add(btnCompletadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 290, 40));

        tbnConfiguracion.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        tbnConfiguracion.setForeground(new java.awt.Color(102, 153, 255));
        tbnConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/blueConfig24px.png"))); // NOI18N
        tbnConfiguracion.setText("Configuracion");
        tbnConfiguracion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255), 0));
        tbnConfiguracion.setBorderPainted(false);
        tbnConfiguracion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tbnConfiguracion.addActionListener(this::tbnConfiguracionActionPerformed);
        panelBotones.add(tbnConfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 280, 290, 40));

        btnHome.setBackground(new java.awt.Color(204, 204, 204));
        btnHome.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/backWhiteHome24px.png"))); // NOI18N
        btnHome.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 2, true));
        btnHome.setContentAreaFilled(false);
        btnHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHome.addActionListener(this::btnHomeActionPerformed);
        panelBotones.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel2.add(panelBotones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 780));

        javax.swing.GroupLayout panelDecorativoSuperiorLayout = new javax.swing.GroupLayout(panelDecorativoSuperior);
        panelDecorativoSuperior.setLayout(panelDecorativoSuperiorLayout);
        panelDecorativoSuperiorLayout.setHorizontalGroup(
            panelDecorativoSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
        );
        panelDecorativoSuperiorLayout.setVerticalGroup(
            panelDecorativoSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel2.add(panelDecorativoSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 1080, 60));

        javax.swing.GroupLayout panelDecorativoInferiorLayout = new javax.swing.GroupLayout(panelDecorativoInferior);
        panelDecorativoInferior.setLayout(panelDecorativoInferiorLayout);
        panelDecorativoInferiorLayout.setHorizontalGroup(
            panelDecorativoInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
        );
        panelDecorativoInferiorLayout.setVerticalGroup(
            panelDecorativoInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel2.add(panelDecorativoInferior, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 720, -1, -1));

        javax.swing.GroupLayout panelDecorativoIzquierdaLayout = new javax.swing.GroupLayout(panelDecorativoIzquierda);
        panelDecorativoIzquierda.setLayout(panelDecorativoIzquierdaLayout);
        panelDecorativoIzquierdaLayout.setHorizontalGroup(
            panelDecorativoIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );
        panelDecorativoIzquierdaLayout.setVerticalGroup(
            panelDecorativoIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );

        jPanel2.add(panelDecorativoIzquierda, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 80, -1));

        javax.swing.GroupLayout panelDecorativoDerechaLayout = new javax.swing.GroupLayout(panelDecorativoDerecha);
        panelDecorativoDerecha.setLayout(panelDecorativoDerechaLayout);
        panelDecorativoDerechaLayout.setHorizontalGroup(
            panelDecorativoDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );
        panelDecorativoDerechaLayout.setVerticalGroup(
            panelDecorativoDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );

        jPanel2.add(panelDecorativoDerecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 60, -1, -1));

        lblFotoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/usuario256.png"))); // NOI18N
        jPanel2.add(lblFotoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, -1, -1));

        panelDecorativoSuperior1.setBackground(new java.awt.Color(0, 153, 255));
        panelDecorativoSuperior1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo1.setFont(new java.awt.Font("Inter", 1, 48)); // NOI18N
        lblTitulo1.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo1.setText("Configuración");
        panelDecorativoSuperior1.add(lblTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 9, 440, 60));

        jPanel2.add(panelDecorativoSuperior1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 55, 920, 70));

        panelInferior.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnInformacion.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        btnInformacion.setForeground(new java.awt.Color(0, 43, 84));
        btnInformacion.setText("Informacion");
        btnInformacion.setContentAreaFilled(false);
        btnInformacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInformacion.addActionListener(this::btnInformacionActionPerformed);
        panelInferior.add(btnInformacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 170, 40));

        btnEliminarCuenta.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        btnEliminarCuenta.setForeground(new java.awt.Color(255, 102, 0));
        btnEliminarCuenta.setText("Eliminar cuenta");
        btnEliminarCuenta.setContentAreaFilled(false);
        btnEliminarCuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarCuenta.addActionListener(this::btnEliminarCuentaActionPerformed);
        panelInferior.add(btnEliminarCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, 220, 40));

        btnCambiarPassword.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        btnCambiarPassword.setForeground(new java.awt.Color(0, 43, 84));
        btnCambiarPassword.setText("Cambiar contraseña");
        btnCambiarPassword.setContentAreaFilled(false);
        btnCambiarPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCambiarPassword.addActionListener(this::btnCambiarPasswordActionPerformed);
        panelInferior.add(btnCambiarPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 270, 40));

        lblP2.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        lblP2.setForeground(new java.awt.Color(102, 102, 102));
        lblP2.setText("contraseña de acceso");
        panelInferior.add(lblP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, -1, -1));

        lblP1.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        lblP1.setForeground(new java.awt.Color(102, 102, 102));
        lblP1.setText("Modifica tu ");
        panelInferior.add(lblP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        lblI2.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        lblI2.setForeground(new java.awt.Color(102, 102, 102));
        lblI2.setText("la aplicación");
        panelInferior.add(lblI2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, -1, -1));

        lblI1.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        lblI1.setForeground(new java.awt.Color(102, 102, 102));
        lblI1.setText("Datos sobre");
        panelInferior.add(lblI1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, -1, -1));

        lblE2.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        lblE2.setForeground(new java.awt.Color(102, 102, 102));
        lblE2.setText("todos tus datos");
        panelInferior.add(lblE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 80, -1, -1));

        lblE1.setFont(new java.awt.Font("Liberation Sans", 0, 20)); // NOI18N
        lblE1.setForeground(new java.awt.Color(102, 102, 102));
        lblE1.setText("Borra tu cuenta y");
        panelInferior.add(lblE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 160, -1));

        protegerIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/proteger64PX.png"))); // NOI18N
        panelInferior.add(protegerIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        lnlInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Information64px.png"))); // NOI18N
        panelInferior.add(lnlInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, -1));

        lblTrash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/orangeTrash64px.png"))); // NOI18N
        panelInferior.add(lblTrash, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, -1, -1));

        jPanel2.add(panelInferior, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 550, 880, 150));

        btnCerrarSesion.setBackground(new java.awt.Color(255, 102, 0));
        btnCerrarSesion.setFont(new java.awt.Font("Inter", 0, 48)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Apagar32px.png"))); // NOI18N
        btnCerrarSesion.setText(" Cerrar Sesión");
        btnCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrarSesion.addActionListener(this::btnCerrarSesionActionPerformed);

        lblBienvenido.setFont(new java.awt.Font("Inter", 1, 48)); // NOI18N
        lblBienvenido.setForeground(new java.awt.Color(0, 43, 84));
        lblBienvenido.setText("¡Bienvenido!");

        lblUsuario.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(0, 43, 84));
        lblUsuario.setText("Usuario");
        lblUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblCorreo.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        lblCorreo.setForeground(new java.awt.Color(102, 102, 102));
        lblCorreo.setText("Correo");
        lblCorreo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelPerfilLayout = new javax.swing.GroupLayout(panelPerfil);
        panelPerfil.setLayout(panelPerfilLayout);
        panelPerfilLayout.setHorizontalGroup(
            panelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPerfilLayout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(btnCerrarSesion)
                .addContainerGap(249, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPerfilLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86))
        );
        panelPerfilLayout.setVerticalGroup(
            panelPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPerfilLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblBienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion)
                .addGap(24, 24, 24))
        );

        jPanel2.add(panelPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 880, 380));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1366, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        gestor.setConfig(this);
        gestor.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        historial.setVisible(true);
        historial.actualizarTabla();
        this.setVisible(false);
    }//GEN-LAST:event_btnHistorialActionPerformed

    private void btnCompletadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompletadasActionPerformed
        completadas.setVisible(true);
        completadas.mostrarCompletadas();
        this.setVisible(false);
    }//GEN-LAST:event_btnCompletadasActionPerformed

    private void tbnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnConfiguracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbnConfiguracionActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        int op = JOptionPane.showConfirmDialog(this, "¿Quieres finalizar la sesion y volver al inicio?", "Cerrar Sesion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        
        if (op == JOptionPane.YES_OPTION) {
            login.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        gestor.setConfig(this);
        gestor.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformacionActionPerformed
        mostrarInformacion();
    }//GEN-LAST:event_btnInformacionActionPerformed

    private void btnEliminarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCuentaActionPerformed
        eliminarCuenta();
    }//GEN-LAST:event_btnEliminarCuentaActionPerformed

    private void btnCambiarPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarPasswordActionPerformed
        cambiarPassword();
    }//GEN-LAST:event_btnCambiarPasswordActionPerformed

    private void btnInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseEntered
        btnInicio.setBackground(Color.white);
        btnInicio.setContentAreaFilled(true);
        btnInicio.setBorderPainted(false);
        btnInicio.setForeground(new Color(102,153,255)); 
    }//GEN-LAST:event_btnInicioMouseEntered

    private void btnInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseExited
        btnInicio.setBackground(new Color(102,153,255));
        btnInicio.setContentAreaFilled(false);
        btnInicio.setForeground(Color.white);
    }//GEN-LAST:event_btnInicioMouseExited

    private void btnHistorialMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistorialMouseEntered
        btnHistorial.setBackground(Color.white);
        btnHistorial.setContentAreaFilled(true);
        btnHistorial.setBorderPainted(false);
        btnHistorial.setForeground(new Color(102,153,255)); 
    }//GEN-LAST:event_btnHistorialMouseEntered

    private void btnCompletadasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCompletadasMouseEntered
        btnCompletadas.setBackground(Color.white);
        btnCompletadas.setContentAreaFilled(true);
        btnCompletadas.setBorderPainted(false);
        btnCompletadas.setForeground(new Color(102,153,255)); 
    }//GEN-LAST:event_btnCompletadasMouseEntered

    private void btnHistorialMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistorialMouseExited
        btnHistorial.setBackground(new Color(102,153,255));
        btnHistorial.setContentAreaFilled(false);
        btnHistorial.setForeground(Color.white);
    }//GEN-LAST:event_btnHistorialMouseExited

    private void btnCompletadasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCompletadasMouseExited
        btnCompletadas.setBackground(new Color(102,153,255));
        btnCompletadas.setContentAreaFilled(false);
        btnCompletadas.setForeground(Color.white);
    }//GEN-LAST:event_btnCompletadasMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiarPassword;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCompletadas;
    private javax.swing.JButton btnEliminarCuenta;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnInformacion;
    private javax.swing.JButton btnInicio;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblBienvenido;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblE1;
    private javax.swing.JLabel lblE2;
    private javax.swing.JLabel lblFotoUsuario;
    private javax.swing.JLabel lblI1;
    private javax.swing.JLabel lblI2;
    private javax.swing.JLabel lblLuisAmigo;
    private javax.swing.JLabel lblP1;
    private javax.swing.JLabel lblP2;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JLabel lblTrash;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lnlInfo;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelDecorativoDerecha;
    private javax.swing.JPanel panelDecorativoInferior;
    private javax.swing.JPanel panelDecorativoIzquierda;
    private javax.swing.JPanel panelDecorativoSuperior;
    private javax.swing.JPanel panelDecorativoSuperior1;
    private javax.swing.JPanel panelInferior;
    private javax.swing.JPanel panelLuisAmigo;
    private javax.swing.JPanel panelPerfil;
    private javax.swing.JLabel protegerIcon;
    private javax.swing.JButton tbnConfiguracion;
    // End of variables declaration//GEN-END:variables

    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }

    public void setHistorial(Historial historial) {
        this.historial = historial;
    }

    public void setCompletadas(Completada completadas) {
        this.completadas = completadas;
    }

    public void setLogin(Login login) {
        this.login = login;
    }    
    
}
