package vistas;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Historial extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Historial.class.getName());
    private Gestor gestor;
    DefaultTableModel modelo = new DefaultTableModel(); 
    private Login login;
    private Completada completadas;
    private Configuracion config;
    
    public Historial() {
        initComponents();
        this.setLocationRelativeTo(this);
        String encabezados[] = {"Titulo", "Descripcion", "Fecha", "Prioridad", "Estado"};
        modelo.setColumnIdentifiers(encabezados);
        tblTareas.setModel(modelo);
        
        //Metodo documentListener
        
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
    
    public String formatearFecha(String fecha){
        String[] partes = fecha.split("/");
        String dia = partes[0];
        String mes = partes[1];
        String year = partes[2];
        
        return dia + mes + year;
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
            modelo.addRow(new Object[]{
                partes[0], partes[1], partes[2], partes[3], partes[4]
            });
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
            modelo.addRow(new Object[]{
                partes[0], partes[1], partes[2], partes[3], partes[4]
            });
        }
    }
    
    public void actualizarTabla(){
        String ruta = "tareas.txt";
        modelo.setRowCount(0);
        
        try (FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr)) {

            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                modelo.addRow(new Object[]{
                    partes[0], partes[1], partes[2], partes[3], partes[4]
            });
                
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo");
        }
    }
    
    public void eliminar(int filaSeleccionada){
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

        // Eliminar la línea seleccionada
        lineas.remove(filaSeleccionada);

        // Reescribir archivo
        try(PrintWriter pw = new PrintWriter(new FileWriter(ruta))){
            for(String l : lineas){
                pw.println(l);
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "Error al escribir archivo");
        }

        actualizarTabla();

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
        tblTareas = new javax.swing.JTable();
        lblGithub = new javax.swing.JLabel();
        lblGithubUser = new javax.swing.JLabel();
        lblHenry = new javax.swing.JLabel();
        lblCreditos = new javax.swing.JLabel();
        panelDecorativoDerecha = new javax.swing.JPanel();
        txtBuscarTarea = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnRefrescar = new javax.swing.JButton();
        lblOrdenar = new javax.swing.JLabel();
        btnFecha = new javax.swing.JButton();
        lblLinea = new javax.swing.JLabel();
        btnPrioridad = new javax.swing.JButton();
        lblTitulo1 = new javax.swing.JLabel();
        panelDecorativoInferior = new javax.swing.JPanel();
        panelDecorativoIzquierda = new javax.swing.JPanel();

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

        btnHistorial.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        btnHistorial.setForeground(new java.awt.Color(102, 153, 255));
        btnHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/blueTask24px.png"))); // NOI18N
        btnHistorial.setText("Historial");
        btnHistorial.setBorder(null);
        btnHistorial.setBorderPainted(false);
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

        tblTareas.setBackground(new java.awt.Color(0, 153, 255));
        tblTareas.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 255, 255)));
        tblTareas.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        tblTareas.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTareas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblTareas.setRowHeight(30);
        jScrollPane2.setViewportView(tblTareas);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(362, 170, 720, 520));

        lblGithub.setFont(new java.awt.Font("Inter", 1, 30)); // NOI18N
        lblGithub.setText("Github:");
        jPanel2.add(lblGithub, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 590, -1, -1));

        lblGithubUser.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        lblGithubUser.setForeground(new java.awt.Color(102, 102, 102));
        lblGithubUser.setText("@ByHenry99");
        jPanel2.add(lblGithubUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 640, -1, -1));

        lblHenry.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        lblHenry.setForeground(new java.awt.Color(102, 102, 102));
        lblHenry.setText("Henry Zuñiga");
        jPanel2.add(lblHenry, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 550, -1, -1));

        lblCreditos.setFont(new java.awt.Font("Inter", 1, 30)); // NOI18N
        lblCreditos.setText("Créditos");
        jPanel2.add(lblCreditos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 500, -1, -1));

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

        btnEliminar.setBackground(new java.awt.Color(255, 102, 0));
        btnEliminar.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/whitetrash24px.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(null);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarMouseExited(evt);
            }
        });
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnRefrescar.setBackground(new java.awt.Color(0, 153, 255));
        btnRefrescar.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        btnRefrescar.setForeground(new java.awt.Color(255, 255, 255));
        btnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/whitereload24px.png"))); // NOI18N
        btnRefrescar.setText("Refrescar");
        btnRefrescar.setBorder(null);
        btnRefrescar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefrescar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRefrescarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRefrescarMouseExited(evt);
            }
        });
        btnRefrescar.addActionListener(this::btnRefrescarActionPerformed);

        lblOrdenar.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        lblOrdenar.setText("Ordenar:");

        btnFecha.setBackground(new java.awt.Color(0, 153, 255));
        btnFecha.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        btnFecha.setForeground(new java.awt.Color(255, 255, 255));
        btnFecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/whiteDownArrow24px.png"))); // NOI18N
        btnFecha.setText("Fecha");
        btnFecha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        btnFecha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFecha.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnFecha.setOpaque(true);
        btnFecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFechaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFechaMouseExited(evt);
            }
        });
        btnFecha.addActionListener(this::btnFechaActionPerformed);

        lblLinea.setText("-------------------------------------------------------------------");

        btnPrioridad.setBackground(new java.awt.Color(0, 153, 255));
        btnPrioridad.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        btnPrioridad.setForeground(new java.awt.Color(255, 255, 255));
        btnPrioridad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/whiteDownArrow24px.png"))); // NOI18N
        btnPrioridad.setText("Prioridad");
        btnPrioridad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        btnPrioridad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrioridad.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnPrioridad.setOpaque(true);
        btnPrioridad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPrioridadMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrioridadMouseExited(evt);
            }
        });
        btnPrioridad.addActionListener(this::btnPrioridadActionPerformed);

        javax.swing.GroupLayout panelDecorativoDerechaLayout = new javax.swing.GroupLayout(panelDecorativoDerecha);
        panelDecorativoDerecha.setLayout(panelDecorativoDerechaLayout);
        panelDecorativoDerechaLayout.setHorizontalGroup(
            panelDecorativoDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDecorativoDerechaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelDecorativoDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDecorativoDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblOrdenar)
                        .addGroup(panelDecorativoDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBuscarTarea, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                            .addComponent(btnRefrescar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnPrioridad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(panelDecorativoDerechaLayout.createSequentialGroup()
                .addComponent(lblLinea)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelDecorativoDerechaLayout.setVerticalGroup(
            panelDecorativoDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDecorativoDerechaLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(txtBuscarTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnEliminar)
                .addGap(30, 30, 30)
                .addComponent(btnRefrescar)
                .addGap(50, 50, 50)
                .addComponent(lblOrdenar)
                .addGap(18, 18, 18)
                .addComponent(btnFecha)
                .addGap(18, 18, 18)
                .addComponent(btnPrioridad)
                .addGap(29, 29, 29)
                .addComponent(lblLinea)
                .addContainerGap(240, Short.MAX_VALUE))
        );

        jPanel2.add(panelDecorativoDerecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 60, 270, 660));

        lblTitulo1.setFont(new java.awt.Font("Inter", 1, 48)); // NOI18N
        lblTitulo1.setText("Historial de tareas");
        jPanel2.add(lblTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, -1, -1));

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
            .addGap(0, 50, Short.MAX_VALUE)
        );
        panelDecorativoIzquierdaLayout.setVerticalGroup(
            panelDecorativoIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );

        jPanel2.add(panelDecorativoIzquierda, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 50, -1));

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
        gestor.setHistorial(this);
        gestor.setVisible(true);
        gestor.inicio();
        this.setVisible(false);
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHistorialActionPerformed

    private void btnCompletadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompletadasActionPerformed
        completadas.mostrarCompletadas();
        completadas.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCompletadasActionPerformed

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        config.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        gestor.setHistorial(this);
        gestor.setVisible(true);
        gestor.inicio();
        this.setVisible(false);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        
        int fila = tblTareas.getSelectedRow();
        
        if(tblTareas.getSelectedRow() != -1){
            int op = JOptionPane.showConfirmDialog(this, "¿Esta seguro de que desea eliminar este registro?", "Eliminar registro", JOptionPane.WARNING_MESSAGE);
            
            if(op == JOptionPane.YES_OPTION){
                eliminar(fila);
            }else{
                JOptionPane.showMessageDialog(this, "No se ha realizado ningun cambio");
            }
        }else{
          JOptionPane.showMessageDialog(this, "Debes seleccionar un registro");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        actualizarTabla();
        JOptionPane.showMessageDialog(this, "La tabla se ha actualizado correctamente");
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void btnFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechaActionPerformed
        ordenarPorFecha();
    }//GEN-LAST:event_btnFechaActionPerformed

    private void txtBuscarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarTareaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarTareaActionPerformed

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

    private void btnPrioridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrioridadActionPerformed
        ordenarPorPrioridad();
    }//GEN-LAST:event_btnPrioridadActionPerformed

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

    }//GEN-LAST:event_btnHistorialMouseEntered

    private void btnCompletadasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCompletadasMouseEntered
        btnCompletadas.setBackground(Color.white);
        btnCompletadas.setContentAreaFilled(true);
        btnCompletadas.setBorderPainted(false);
        btnCompletadas.setForeground(new Color(102,153,255)); 
    }//GEN-LAST:event_btnCompletadasMouseEntered

    private void btnConfiguracionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfiguracionMouseEntered
        btnConfiguracion.setBackground(Color.white);
        btnConfiguracion.setContentAreaFilled(true);
        btnConfiguracion.setBorderPainted(false);
        btnConfiguracion.setForeground(new Color(102,153,255)); 
    }//GEN-LAST:event_btnConfiguracionMouseEntered

    private void btnHistorialMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistorialMouseExited

    }//GEN-LAST:event_btnHistorialMouseExited

    private void btnCompletadasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCompletadasMouseExited
        btnCompletadas.setBackground(new Color(102,153,255));
        btnCompletadas.setContentAreaFilled(false);
        btnCompletadas.setForeground(Color.white);
    }//GEN-LAST:event_btnCompletadasMouseExited

    private void btnConfiguracionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfiguracionMouseExited
        btnConfiguracion.setBackground(new Color(102,153,255));
        btnConfiguracion.setContentAreaFilled(false);
        btnConfiguracion.setForeground(Color.white);
    }//GEN-LAST:event_btnConfiguracionMouseExited

    private void btnEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseEntered
        btnEliminar.setBackground(new Color(204, 102, 0));
    }//GEN-LAST:event_btnEliminarMouseEntered

    private void btnEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseExited
        btnEliminar.setBackground(new Color(255, 102, 0));
    }//GEN-LAST:event_btnEliminarMouseExited

    private void btnRefrescarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefrescarMouseEntered
        btnRefrescar.setBackground(new Color(0,102,255)); 
    }//GEN-LAST:event_btnRefrescarMouseEntered

    private void btnRefrescarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefrescarMouseExited
        btnRefrescar.setBackground(new Color(102,153,255));
    }//GEN-LAST:event_btnRefrescarMouseExited

    private void btnFechaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFechaMouseEntered
        btnFecha.setBackground(new Color(0,102,255)); 
    }//GEN-LAST:event_btnFechaMouseEntered

    private void btnFechaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFechaMouseExited
        btnFecha.setBackground(new Color(102,153,255)); 
    }//GEN-LAST:event_btnFechaMouseExited

    private void btnPrioridadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrioridadMouseEntered
        btnPrioridad.setBackground(new Color(0,102,255)); 
    }//GEN-LAST:event_btnPrioridadMouseEntered

    private void btnPrioridadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrioridadMouseExited
        btnPrioridad.setBackground(new Color(102,153,255)); 
    }//GEN-LAST:event_btnPrioridadMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompletadas;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFecha;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnPrioridad;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCreditos;
    private javax.swing.JLabel lblGithub;
    private javax.swing.JLabel lblGithubUser;
    private javax.swing.JLabel lblHenry;
    private javax.swing.JLabel lblLinea;
    private javax.swing.JLabel lblLuisAmigo;
    private javax.swing.JLabel lblOrdenar;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelDecorativoDerecha;
    private javax.swing.JPanel panelDecorativoInferior;
    private javax.swing.JPanel panelDecorativoIzquierda;
    private javax.swing.JPanel panelDecorativoSuperior;
    private javax.swing.JPanel panelLuisAmigo;
    private javax.swing.JTable tblTareas;
    private javax.swing.JTextField txtBuscarTarea;
    // End of variables declaration//GEN-END:variables

    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setCompletadas(Completada completadas) {
        this.completadas = completadas;
    }

    public void setConfig(Configuracion config) {
        this.config = config;
    }
    
    
}
