package vistas;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Completada extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Completada.class.getName());
    private Login login;
    private Gestor gestor;
    private Historial historial;
    private Configuracion config;
    
    DefaultTableModel modelo = new DefaultTableModel();
    
    public Completada() {
        initComponents();
        this.setLocationRelativeTo(this);
        
        String encabezados[] = {"Titulo", "Descripcion", "Fecha", "Prioridad", "Estado"};
        modelo.setColumnIdentifiers(encabezados);
        tblTareasCompletadas.setModel(modelo);
        
        txtBuscarTarea.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                buscarTarea(txtBuscarTarea.getText());
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                buscarTarea(txtBuscarTarea.getText());
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                buscarTarea(txtBuscarTarea.getText());
            }
        });
    }
    
    public void buscarTarea(String texto){
        String ruta = "tareas.txt";
        modelo.setRowCount(0);

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");

                if(texto.isEmpty() || partes[0].toLowerCase().contains(texto.toLowerCase())){ 
                    modelo.addRow(new Object[]{
                        partes[0], partes[1], partes[2], partes[3], partes[4]
                    });
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar");
        }
    }
    
    public void mostrarCompletadas(){
        String ruta = "tareas.txt";
        modelo.setRowCount(0);
        
        try (FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr)) {

            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                
                if (partes[4].equals("Completada")) {
                    modelo.addRow(new Object[]{
                        partes[0], partes[1], partes[2], partes[3], partes[4]
                    });
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo");
        }
    
    }
    public int obtenerValorPrioridad(String prioridad){
        switch(prioridad){
            case "Alta": return 1;
            case "Media": return 2;
            case "Baja": return 3;
            default: return 4;
        }
    }
    
    public String formatearFecha(String fecha){
        String[] partes = fecha.split("/");
        String dia = partes[0];
        String mes = partes[1];
        String year = partes[2];
        
        return dia + mes + year;
    }
    
    public void ordenarPorPrioridad(){
        String ruta = "tareas.txt";
        ArrayList<String> lineas = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(ruta))){
            String linea;
            while((linea = br.readLine()) != null){
                lineas.add(linea);
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "Error al leer archivo");
        }

        lineas.sort((a, b) -> {
            String prioridadA = a.split("\\|")[3];
            String prioridadB = b.split("\\|")[3];

            int valorA = obtenerValorPrioridad(prioridadA);
            int valorB = obtenerValorPrioridad(prioridadB);

            return Integer.compare(valorA, valorB);
        });

        // Reordenar tabla
        modelo.setRowCount(0);
        
        for (String linea: lineas) {
            String[] partes = linea.split("\\|");
            if (partes[4].equals("Completada")) {
                    modelo.addRow(new Object[]{
                        partes[0], partes[1], partes[2], partes[3], partes[4]
                });
            }
        }
    }
    
    public void ordenarPorFecha(){
        String ruta = "tareas.txt";
        ArrayList<String> lineas = new ArrayList<>();

        // Leer archivo
        try(BufferedReader br = new BufferedReader(new FileReader(ruta))){
            String linea;
            while((linea = br.readLine()) != null){
                lineas.add(linea);
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "Error al leer archivo");
        }

        // Ordenar por fecha
        lineas.sort((a, b) -> { //Recibo a, b y hago esto (el simbolo -> es un lambda
            String fechaA = a.split("\\|")[2];  //Lo que hay en la posicion 2 separado por  |
            String fechaB = b.split("\\|")[2];
            
            String ordenA = formatearFecha(fechaA);
            String ordenB = formatearFecha(fechaB);
            
            return ordenA.compareTo(ordenB); //Sirve para comprar texto
            
            /* 
                "ana".compareTo("juan"); // negativo → ana va antes
                "perro".compareTo("gato"); // positivo → gato va antes
                "hola".compareTo("hola"); // 0 → iguales
            */
        });
      
        // Reordenar tabla
        modelo.setRowCount(0);
        
        for (String linea: lineas) {
            String[] partes = linea.split("\\|");
            if (partes[4].equals("Completada")) {
                    modelo.addRow(new Object[]{
                        partes[0], partes[1], partes[2], partes[3], partes[4]
                });
            }
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
        btnConfiguracion = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        panelDecorativoSuperior = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTareasCompletadas = new javax.swing.JTable();
        lblTitulo1 = new javax.swing.JLabel();
        panelDecorativoInferior = new javax.swing.JPanel();
        panelDecorativoIzquierda = new javax.swing.JPanel();
        panelDecorativoDerecha1 = new javax.swing.JPanel();
        txtBuscarTarea = new javax.swing.JTextField();
        btnFecha1 = new javax.swing.JButton();
        btnPrioridad1 = new javax.swing.JButton();

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

        btnCompletadas.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        btnCompletadas.setForeground(new java.awt.Color(102, 153, 255));
        btnCompletadas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/blueCompleted24px.png"))); // NOI18N
        btnCompletadas.setText("Completadas");
        btnCompletadas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255), 0));
        btnCompletadas.setBorderPainted(false);
        btnCompletadas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCompletadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCompletadasMouseEntered(evt);
            }
        });
        btnCompletadas.addActionListener(this::btnCompletadasActionPerformed);
        panelBotones.add(btnCompletadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 290, 40));

        btnConfiguracion.setBackground(new java.awt.Color(102, 153, 255));
        btnConfiguracion.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        btnConfiguracion.setForeground(new java.awt.Color(255, 255, 255));
        btnConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/whiteConfig24px.png"))); // NOI18N
        btnConfiguracion.setText("Configuracion");
        btnConfiguracion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255), 0));
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

        tblTareasCompletadas.setBackground(new java.awt.Color(0, 153, 255));
        tblTareasCompletadas.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 255, 255)));
        tblTareasCompletadas.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        tblTareasCompletadas.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTareasCompletadas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblTareasCompletadas.setRowHeight(30);
        jScrollPane2.setViewportView(tblTareasCompletadas);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, 750, 470));

        lblTitulo1.setFont(new java.awt.Font("Inter", 1, 48)); // NOI18N
        lblTitulo1.setText("Tareas completadas");
        jPanel2.add(lblTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, -1, -1));

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
            .addGap(0, 110, Short.MAX_VALUE)
        );
        panelDecorativoIzquierdaLayout.setVerticalGroup(
            panelDecorativoIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );

        jPanel2.add(panelDecorativoIzquierda, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 110, -1));

        javax.swing.GroupLayout panelDecorativoDerecha1Layout = new javax.swing.GroupLayout(panelDecorativoDerecha1);
        panelDecorativoDerecha1.setLayout(panelDecorativoDerecha1Layout);
        panelDecorativoDerecha1Layout.setHorizontalGroup(
            panelDecorativoDerecha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        panelDecorativoDerecha1Layout.setVerticalGroup(
            panelDecorativoDerecha1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );

        jPanel2.add(panelDecorativoDerecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 60, 110, 660));

        txtBuscarTarea.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        txtBuscarTarea.setForeground(new java.awt.Color(102, 102, 102));
        txtBuscarTarea.setText("Buscar tarea...");
        txtBuscarTarea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarTareaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarTareaFocusLost(evt);
            }
        });
        txtBuscarTarea.addActionListener(this::txtBuscarTareaActionPerformed);
        jPanel2.add(txtBuscarTarea, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 180, 239, -1));

        btnFecha1.setBackground(new java.awt.Color(255, 102, 0));
        btnFecha1.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        btnFecha1.setForeground(new java.awt.Color(255, 255, 255));
        btnFecha1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/whiteDownArrow24px.png"))); // NOI18N
        btnFecha1.setText("Fecha");
        btnFecha1.setBorder(null);
        btnFecha1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFecha1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnFecha1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFecha1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFecha1MouseExited(evt);
            }
        });
        btnFecha1.addActionListener(this::btnFecha1ActionPerformed);
        jPanel2.add(btnFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 210, -1));

        btnPrioridad1.setBackground(new java.awt.Color(255, 102, 0));
        btnPrioridad1.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        btnPrioridad1.setForeground(new java.awt.Color(255, 255, 255));
        btnPrioridad1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/whiteDownArrow24px.png"))); // NOI18N
        btnPrioridad1.setText("Prioridad");
        btnPrioridad1.setBorder(null);
        btnPrioridad1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrioridad1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnPrioridad1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPrioridad1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrioridad1MouseExited(evt);
            }
        });
        btnPrioridad1.addActionListener(this::btnPrioridad1ActionPerformed);
        jPanel2.add(btnPrioridad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 180, 239, -1));

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
        gestor.setCompletada(this);
        gestor.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        historial.setVisible(true);
        historial.actualizarTabla();
        this.setVisible(false);
    }//GEN-LAST:event_btnHistorialActionPerformed

    private void btnCompletadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompletadasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCompletadasActionPerformed

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        config.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        gestor.setCompletada(this);
        gestor.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnHomeActionPerformed

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

    private void btnHistorialMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistorialMouseExited
        btnHistorial.setBackground(new Color(102,153,255));
        btnHistorial.setContentAreaFilled(false);
        btnHistorial.setForeground(Color.white);
    }//GEN-LAST:event_btnHistorialMouseExited

    private void btnCompletadasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCompletadasMouseEntered

    }//GEN-LAST:event_btnCompletadasMouseEntered

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

    private void txtBuscarTareaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarTareaFocusGained
        txtBuscarTarea.setText("");
        txtBuscarTarea.setForeground(Color.black);
    }//GEN-LAST:event_txtBuscarTareaFocusGained

    private void txtBuscarTareaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarTareaFocusLost
        if (txtBuscarTarea.getText().equals("")) {
            txtBuscarTarea.setText("Buscar tarea...");
            txtBuscarTarea.setForeground(Color.gray);
        }
    }//GEN-LAST:event_txtBuscarTareaFocusLost

    private void txtBuscarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarTareaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarTareaActionPerformed

    private void btnFecha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecha1ActionPerformed
        ordenarPorFecha();
    }//GEN-LAST:event_btnFecha1ActionPerformed

    private void btnPrioridad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrioridad1ActionPerformed
        ordenarPorPrioridad();
    }//GEN-LAST:event_btnPrioridad1ActionPerformed

    private void btnFecha1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecha1MouseEntered
        btnFecha1.setBackground(new Color(0,102,255));
    }//GEN-LAST:event_btnFecha1MouseEntered

    private void btnFecha1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecha1MouseExited
        btnFecha1.setBackground(new Color(102,153,255));
    }//GEN-LAST:event_btnFecha1MouseExited

    private void btnPrioridad1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrioridad1MouseEntered
        btnPrioridad1.setBackground(new Color(0,102,255));
    }//GEN-LAST:event_btnPrioridad1MouseEntered

    private void btnPrioridad1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrioridad1MouseExited
        btnPrioridad1.setBackground(new Color(102,153,255));
    }//GEN-LAST:event_btnPrioridad1MouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompletadas;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnFecha1;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnPrioridad1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblLuisAmigo;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelDecorativoDerecha1;
    private javax.swing.JPanel panelDecorativoInferior;
    private javax.swing.JPanel panelDecorativoIzquierda;
    private javax.swing.JPanel panelDecorativoSuperior;
    private javax.swing.JPanel panelLuisAmigo;
    private javax.swing.JTable tblTareasCompletadas;
    private javax.swing.JTextField txtBuscarTarea;
    // End of variables declaration//GEN-END:variables

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }

    public void setHistorial(Historial historial) {
        this.historial = historial;
    }

    public void setConfig(Configuracion config) {
        this.config = config;
    }
    
    
}
