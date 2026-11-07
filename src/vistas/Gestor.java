package vistas;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

public class Gestor extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Gestor.class.getName());
    private Login login;
    private Completada completadas = new Completada();
    private Historial historial = new Historial();
    private Configuracion config = new Configuracion();
    AgregarTarea agregarTarea = new AgregarTarea();
    
    public Gestor() {
        initComponents();
        this.setLocationRelativeTo(this);
        historial.setGestor(this);
        config.setGestor(this);
        completadas.setGestor(this);
        
        historial.setCompletadas(completadas);
        historial.setConfig(config);
        completadas.setHistorial(historial);
        completadas.setConfig(config);
        config.setHistorial(historial);
        config.setCompletadas(completadas);
        config.setLogin(login);
        
        panelListaTareas.setLayout(new BoxLayout(panelListaTareas, BoxLayout.Y_AXIS));
        inicio();
    }
    
    public void DatosUsuario(String nombre, String correo){
        config.DatosUsuario(nombre, correo);
    }
    
    public void inicio(){
        String ruta = "tareas.txt";
        
        //Limpiar interfaz
        panelListaTareas.removeAll();
        btnInicio.requestFocus();
        
        try (FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr)) {

            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                agregarTarea(partes[0], partes[1], partes[2], partes[3], partes[4]);
                
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo");
        }

    }
    
    public void cambiarEstadoArchivo(String titulo, String nuevoEstado){

        String ruta = "tareas.txt";

        // Lista donde guardaremos todas las líneas del archivo
        ArrayList<String> lineas = new ArrayList<>();

        try(
            // BufferedReader → permite leer el archivo línea por línea
            BufferedReader br = new BufferedReader(new FileReader(ruta))
        ){

            String linea;

            // Recorremos todo el archivo
            while((linea = br.readLine()) != null){

                // Separar los datos usando el "|"
                String[] datos = linea.split("\\|");

                // datos[0] = titulo
                // datos[1] = descripcion
                // datos[2] = fecha
                // datos[3] = prioridad
                // datos[4] = estado

                // Si encontramos la tarea con el mismo título
                if(datos[0].equals(titulo)){
                    // Cambiamos el estado
                    datos[4] = nuevoEstado;

                    // Volvemos a construir la línea con los datos actualizados
                    linea = String.join("|", datos);
                }

                // Guardamos la línea (modificada o no)
                lineas.add(linea);
            }

        }catch(IOException e){
            e.printStackTrace();
        }

        // Ahora sobrescribimos TODO el archivo con las nuevas líneas
        try(
            PrintWriter pw = new PrintWriter(new FileWriter(ruta))
        ){
            for(String l : lineas){
                pw.println(l);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void eliminarTareaArchivo(String titulo){

        String ruta = "tareas.txt";

        ArrayList<String> lineas = new ArrayList<>();

        try(
            BufferedReader br = new BufferedReader(new FileReader(ruta))
        ){

            String linea;                                                                                                                                                                  

            while((linea = br.readLine()) != null){

                String[] datos = linea.split("\\|");

                // Solo guardamos las tareas que NO coincidan con el título
                if(!datos[0].equals(titulo)){
                    lineas.add(linea);
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }

        // Sobrescribimos el archivo sin la tarea eliminada
        try(
            PrintWriter pw = new PrintWriter(new FileWriter(ruta))
        ){
            for(String l : lineas){
                pw.println(l);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void agregarTarea(String titulo, String descripcion, String fecha, String prioridad, String estado){

        javax.swing.JPanel panelTarea = new javax.swing.JPanel();
        panelTarea.setLayout(new java.awt.BorderLayout());
        panelTarea.setPreferredSize(new java.awt.Dimension(0, 100));
        panelTarea.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 100));
        panelTarea.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.GRAY));
        panelTarea.setBackground(java.awt.Color.WHITE);

        // TITULO
        javax.swing.JLabel lblTitulo = new javax.swing.JLabel(titulo);
        lblTitulo.setFont(new java.awt.Font("Inter", java.awt.Font.BOLD, 20));

        // DESCRIPCION
        javax.swing.JLabel lblDescripcion = new javax.swing.JLabel(" "+descripcion);
        lblDescripcion.setFont(new java.awt.Font("Inter", java.awt.Font.PLAIN, 16));

        // PANEL INFERIOR
        javax.swing.JPanel panelInferior = new javax.swing.JPanel();
        panelInferior.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        panelInferior.setBackground(java.awt.Color.WHITE);
        
        //PANEL SUPERIOR ACTUALIZADO
        javax.swing.JPanel panelSuperiorActualizado = new javax.swing.JPanel();
        panelSuperiorActualizado.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        panelSuperiorActualizado.setBackground(java.awt.Color.WHITE);
        javax.swing.JLabel lblCompletada = new javax.swing.JLabel("Completada");
        lblCompletada.setFont(new java.awt.Font("Inter", java.awt.Font.PLAIN, 20));
        lblCompletada.setForeground(new Color(0,153,51));
        panelSuperiorActualizado.add(lblTitulo);

        javax.swing.JLabel lblFecha = new javax.swing.JLabel("Fecha: " + fecha);
        lblFecha.setFont(new java.awt.Font("Inter", java.awt.Font.PLAIN, 14));

        javax.swing.JLabel lblPrioridad = new javax.swing.JLabel("Prioridad: " + prioridad);
        lblPrioridad.setFont(new java.awt.Font("Inter", java.awt.Font.BOLD, 14));

        // Cambiar color según prioridad
        if(prioridad.equals("Alta")){
            lblPrioridad.setForeground(java.awt.Color.RED);
        } else if(prioridad.equals("Media")){
            lblPrioridad.setForeground(java.awt.Color.ORANGE);
        } else {
            lblPrioridad.setForeground(java.awt.Color.darkGray);
        }

        // BOTONES
        javax.swing.JButton btnCompletar = new javax.swing.JButton("Completar");
        javax.swing.JButton btnEliminar = new javax.swing.JButton("Eliminar");
        
        btnCompletar.setBackground(new Color(0,153,255));
        btnCompletar.setForeground(Color.white);
        btnCompletar.setFont(new java.awt.Font("Inter", 1, 15));
        btnCompletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setFont(new java.awt.Font("Inter", 1, 15));
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setBackground(new Color(255,102,0));
        btnEliminar.setForeground(Color.white);
        
        // BOTON COMPLETAR
        btnCompletar.addActionListener(e -> {

            // Tachar el título visualmente
            lblTitulo.setText("<html><strike>" + titulo + "</strike></html>");

            // Cambiar estado en el archivo
            cambiarEstadoArchivo(titulo, "Completada");
            
            //Añadir al panel superior
            panelSuperiorActualizado.removeAll(); //Limpiar panel superior
            panelSuperiorActualizado.add(lblTitulo);
            panelSuperiorActualizado.add(lblCompletada);
        });
        
        //Tachar si ya esta completado
        if(estado.equals("Completada")){
            panelSuperiorActualizado.removeAll(); //Limpiar panel superior
            panelSuperiorActualizado.add(lblTitulo);
            panelSuperiorActualizado.add(lblCompletada);
            lblTitulo.setText("<html><strike>" + titulo + "</strike></html>");
        }

        // BOTON ELIMINAR
        btnEliminar.addActionListener(e -> {
            
            int op = JOptionPane.showConfirmDialog(this, "¿Esta seguro de que desea eliminar este registro?", "Eliminar registro", JOptionPane.WARNING_MESSAGE);
            
            if(op == JOptionPane.YES_OPTION){
                // Elimina el panel de la interfaz
                panelListaTareas.remove(panelTarea);

                // Actualiza la pantalla
                panelListaTareas.revalidate();
                panelListaTareas.repaint();

                // Elimina del archivo
                eliminarTareaArchivo(titulo);
            }
            
        });

        // Añadir al panel inferior
        panelInferior.add(lblFecha);
        panelInferior.add(lblPrioridad);
        panelInferior.add(btnCompletar);
        panelInferior.add(btnEliminar);

        
        
        // Añadir al panel tarea
        panelTarea.add(panelSuperiorActualizado, java.awt.BorderLayout.NORTH);
        panelTarea.add(lblDescripcion, java.awt.BorderLayout.CENTER);
        panelTarea.add(panelInferior, java.awt.BorderLayout.SOUTH);

        panelListaTareas.add(panelTarea, 0);
        panelListaTareas.revalidate();
        panelListaTareas.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblLuisAmigo1 = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        btnInicio = new javax.swing.JButton();
        btnHistorial = new javax.swing.JButton();
        btnCompletadas = new javax.swing.JButton();
        btnConfiguracion = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        lblLuisAmigo = new javax.swing.JLabel();
        panelLuisAmigo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelListaTareas = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnNuevaTarea = new javax.swing.JButton();
        lblTitulo1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLuisAmigo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/LuisAmigo2.png"))); // NOI18N
        jPanel2.add(lblLuisAmigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        panelBotones.setBackground(new java.awt.Color(0, 153, 255));
        panelBotones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnInicio.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(102, 153, 255));
        btnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/blueHouse24px.png"))); // NOI18N
        btnInicio.setText("Inicio");
        btnInicio.setBorder(null);
        btnInicio.setBorderPainted(false);
        btnInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInicioMouseEntered(evt);
            }
        });
        btnInicio.addActionListener(this::btnInicioActionPerformed);
        panelBotones.add(btnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 290, 40));

        btnHistorial.setBackground(new java.awt.Color(102, 153, 255));
        btnHistorial.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        btnHistorial.setForeground(new java.awt.Color(255, 255, 255));
        btnHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/whiteTask24px.png"))); // NOI18N
        btnHistorial.setText("Historial");
        btnHistorial.setBorder(null);
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
        btnCompletadas.setBorder(null);
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

        btnConfiguracion.setBackground(new java.awt.Color(102, 153, 255));
        btnConfiguracion.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        btnConfiguracion.setForeground(new java.awt.Color(255, 255, 255));
        btnConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/whiteConfig24px.png"))); // NOI18N
        btnConfiguracion.setText("Configuracion");
        btnConfiguracion.setBorder(null);
        btnConfiguracion.setBorderPainted(false);
        btnConfiguracion.setContentAreaFilled(false);
        btnConfiguracion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfiguracion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfiguracionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfiguracionMouseExited(evt);
            }
        });
        btnConfiguracion.addActionListener(this::btnConfiguracionActionPerformed);
        panelBotones.add(btnConfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 280, 290, 40));

        btnHome.setBackground(new java.awt.Color(204, 204, 204));
        btnHome.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/backWhiteHome24px.png"))); // NOI18N
        btnHome.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 2, true));
        btnHome.setContentAreaFilled(false);
        btnHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHome.addActionListener(this::btnHomeActionPerformed);
        panelBotones.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblLuisAmigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/LuisAmigo2.png"))); // NOI18N
        panelBotones.add(lblLuisAmigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        panelLuisAmigo.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelLuisAmigoLayout = new javax.swing.GroupLayout(panelLuisAmigo);
        panelLuisAmigo.setLayout(panelLuisAmigoLayout);
        panelLuisAmigoLayout.setHorizontalGroup(
            panelLuisAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        panelLuisAmigoLayout.setVerticalGroup(
            panelLuisAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        panelBotones.add(panelLuisAmigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 290, 140));

        jPanel2.add(panelBotones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 780));

        lblTitulo.setFont(new java.awt.Font("Inter", 1, 36)); // NOI18N
        lblTitulo.setText("Tareas recientes");
        jPanel2.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, -1, -1));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(1088, 670));

        javax.swing.GroupLayout panelListaTareasLayout = new javax.swing.GroupLayout(panelListaTareas);
        panelListaTareas.setLayout(panelListaTareasLayout);
        panelListaTareasLayout.setHorizontalGroup(
            panelListaTareasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1088, Short.MAX_VALUE)
        );
        panelListaTareasLayout.setVerticalGroup(
            panelListaTareasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 701, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelListaTareas);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 1040, 580));

        btnNuevaTarea.setBackground(new java.awt.Color(0, 153, 255));
        btnNuevaTarea.setFont(new java.awt.Font("Inter", 1, 30)); // NOI18N
        btnNuevaTarea.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevaTarea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/2plus32px.png"))); // NOI18N
        btnNuevaTarea.setText("Nueva tarea");
        btnNuevaTarea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        btnNuevaTarea.addActionListener(this::btnNuevaTareaActionPerformed);

        lblTitulo1.setFont(new java.awt.Font("Inter", 1, 48)); // NOI18N
        lblTitulo1.setText("Gestor de tareas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblTitulo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 363, Short.MAX_VALUE)
                .addComponent(btnNuevaTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnNuevaTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo1)
                .addContainerGap())
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 1080, 70));

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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        historial.setGestor(this);
        historial.setVisible(true);
        historial.actualizarTabla();
        historial.setLogin(login);
        this.setVisible(false);
    }//GEN-LAST:event_btnHistorialActionPerformed

    private void btnCompletadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompletadasActionPerformed
        completadas.setGestor(this);
        completadas.setVisible(true);
        completadas.mostrarCompletadas();
        completadas.setLogin(login);
        this.setVisible(false);
    }//GEN-LAST:event_btnCompletadasActionPerformed

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        config.setGestor(this);
        config.setVisible(true);
        config.setLogin(login);
        this.setVisible(false);
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        int op = JOptionPane.showConfirmDialog(this, "¿Quieres finalizar la sesion y volver al inicio?", "Cerrar Sesion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        
        if (op == JOptionPane.YES_OPTION) {
            login.setVisible(true);
            login.setGestor(this);
            this.setVisible(false);
        }
        
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnNuevaTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaTareaActionPerformed
        agregarTarea.setVisible(true);
        agregarTarea.setGestor(this);
    }//GEN-LAST:event_btnNuevaTareaActionPerformed

    private void btnHistorialMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistorialMouseEntered
        btnHistorial.setBackground(Color.white);
        btnHistorial.setContentAreaFilled(true);
        btnHistorial.setBorderPainted(false);
        btnHistorial.setForeground(new Color(102,153,255));
    }//GEN-LAST:event_btnHistorialMouseEntered

    private void btnHistorialMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistorialMouseExited
        btnHistorial.setBackground(new Color(102,153,255));
        btnHistorial.setContentAreaFilled(false);
        btnHistorial.setForeground(Color.white);
    }//GEN-LAST:event_btnHistorialMouseExited

    private void btnCompletadasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCompletadasMouseEntered
        btnCompletadas.setBackground(Color.white);
        btnCompletadas.setContentAreaFilled(true);
        btnCompletadas.setBorderPainted(false);
        btnCompletadas.setForeground(new Color(102,153,255));                                        
    }//GEN-LAST:event_btnCompletadasMouseEntered

    private void btnCompletadasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCompletadasMouseExited
        btnCompletadas.setBackground(new Color(102,153,255));
        btnCompletadas.setContentAreaFilled(false);
        btnCompletadas.setForeground(Color.white);
    }//GEN-LAST:event_btnCompletadasMouseExited

    private void btnConfiguracionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfiguracionMouseEntered
        btnConfiguracion.setBackground(Color.white);
        btnConfiguracion.setContentAreaFilled(true);
        btnConfiguracion.setBorderPainted(false);
        btnConfiguracion.setForeground(new Color(102,153,255));     
    }//GEN-LAST:event_btnConfiguracionMouseEntered

    private void btnConfiguracionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfiguracionMouseExited
        btnConfiguracion.setBackground(new Color(102,153,255));
        btnConfiguracion.setContentAreaFilled(false);
        btnConfiguracion.setForeground(Color.white);
    }//GEN-LAST:event_btnConfiguracionMouseExited

    private void btnInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInicioMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompletadas;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnNuevaTarea;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLuisAmigo;
    private javax.swing.JLabel lblLuisAmigo1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelListaTareas;
    private javax.swing.JPanel panelLuisAmigo;
    // End of variables declaration//GEN-END:variables

    public void setLogin(Login login) {
        this.login = login;
        config.setLogin(login);
    }

    public void setAgregarTarea(AgregarTarea agregarTarea) {
        this.agregarTarea = agregarTarea;
    }

    public void setHistorial(Historial historial) {
        this.historial = historial;
    }

    public void setCompletada(Completada completadas) {
        this.completadas = completadas;
    }

    public void setConfig(Configuracion config) {
        this.config = config;
    }
}
