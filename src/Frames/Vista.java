/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frames;

import Clases.Vertice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author
 */

public class Vista extends javax.swing.JFrame {
    
    public static int xMouse, yMouse;
    public static ArrayList<Vertice> panel = new ArrayList<>();
    public static int numVertices = 0;
    public static int numAristas = 0;
    public static boolean verticeActivado = false;
    public static boolean aristaActivado = false;
    public static boolean primerVerticeSeleccionado = true;
    public static Graphics grafico;
    public static String[] nombreVertices = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
        "M", "N", "O", "P", "Q", "R", "S", "T"};
    public static String[] nombreAristas = {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10",
        "A11", "A12", "A13", "A14", "A15", "A16", "A17", "A18", "A19", "A20"};
    public static int verticesMax = nombreVertices.length;
    public static int aristasMax = nombreAristas.length;
    public static int x1, x2, y1, y2;
    public static int partida = -1, llegada = -1, arista = 0;
    public static boolean MAdyacencia[][] = new boolean[verticesMax][verticesMax];
    public static boolean MIncidencia[][] = new boolean[verticesMax][aristasMax];
    private String matrizAdyacencia = "", matrizIncidencia = "", teoremaEuler = "";
    
    // Constructor
    public Vista() {
        initComponents();
        // Inicio
        grafico = this.getGraphics();
        this.setLocationRelativeTo(null);
        verticeBtn.setBackground(null);
        aristaBtn.setBackground(null);
        eulerBtn.setBackground(null);
        
        // Iniciar matrices
        inicializarMatrices();
        matrizTxt.setEditable(false);
        
        // Opciones Desactivadas al no haber nodos
        aristaBtn.setEnabled(false);
        adyacenteBtn.setEnabled(false);
        incidenciaBtn.setEnabled(false);
        eulerBtn.setEnabled(false);
    }
    
    // Inicializar matrices
    private void inicializarMatrices() {
        for (int i = 0; i < verticesMax; i++) {
            for (int j = 0; j < verticesMax; j++) {
                MAdyacencia[i][j] = false;
            }
        }
        for (int i = 0; i < verticesMax; i++) {
            for (int j = 0; j < aristasMax; j++) {
                MIncidencia[i][j] = false;
            }
        }
    }
    
    public static void crearArista(int x, int y, int w, int z) {
        grafico.setColor(Color.RED);
        
        if (x == w && y == z) {
            grafico.drawArc(x + 22, y + 30, 25, 28, 300, 300);
            x = x + 28;
            y = y + 28;
        } else {
            grafico.drawLine(x + 35, y + 70, w + 35, z + 70);
            x = ((x + 30 - w + 30) / 2) + w;
            y = ((y + 70 - z + 70) / 2) + z;
        }
        
        grafico.setColor(Color.BLACK);
        grafico.setFont(new Font("Roboto", Font.PLAIN, 12));
        grafico.drawString(nombreAristas[numAristas], x, y);
        
        numAristas++;
    }

    public void mostrarMatrizAdyacencia() {
        int cantVertices = panel.size();
        int rpta;
        String vertice;
        
        matrizAdyacencia = "  Matriz de Adyacencia\n\n  ";
                
        for (int i = 0; i < cantVertices; i++){
            if (i > 12)
                matrizAdyacencia += "   " + nombreVertices[i];
            else
                matrizAdyacencia += "   " + nombreVertices[i];
        }
        for (int i = 0; i < cantVertices; i++) {
            matrizAdyacencia += "\n";
            
            for (int j = 0; j < cantVertices; j++) {
                if (MAdyacencia[i][j])
                    rpta = 1;
                else
                    rpta = 0;
                
                if ((i == 8 || i == 11) && j == 0)
                    vertice = nombreVertices[i] + "    ";
                else if (j == 0)
                    vertice = nombreVertices[i] + "   ";
                else 
                    vertice = "";
                
                
                if (j == 12 || j == 13 || j == 14)
                    matrizAdyacencia += vertice + rpta + "    ";
                else
                    matrizAdyacencia += vertice + rpta + "   ";
            }
        }
        
        matrizAdyacencia += "\n\n\n";
        
        setMatriz(matrizIncidencia, teoremaEuler, matrizAdyacencia);
    }

    public void mostrarMatrizIncidencia() {
        int cantVertices = panel.size();
        int rpta;
        String vertice;
        
        matrizIncidencia = "  Matriz de Incidencia\n\n  ";
        
        for (int i = 0; i < numAristas; i++){
            if (i < 10 && i != 6)
                matrizIncidencia += "   " + nombreAristas[i];
            else if (i == 6)
                matrizIncidencia += "    " + nombreAristas[i];
            else 
                matrizIncidencia += "  " + nombreAristas[i];
        }
        
        for (int i = 0; i < cantVertices; i++) {
            matrizIncidencia += "\n";
            
            for (int j = 0; j < numAristas; j++) {
                if (MIncidencia[i][j])
                    rpta = 1;
                else 
                    rpta = 0;
                
                if ((i == 8 || i == 11) && j == 0)
                    vertice = nombreVertices[i] + "    ";
                else if (j == 0)
                    vertice = nombreVertices[i] + "   ";
                else 
                    vertice = "";
                
                if (j < 10)
                    matrizIncidencia += vertice + rpta + "      ";
                else
                    matrizIncidencia += vertice + rpta + "       ";
            }
        }
        
        matrizIncidencia += "\n\n\n";
        setMatriz(matrizAdyacencia, teoremaEuler, matrizIncidencia);
    }
    
    public void mostrarTeoremaEuler() {
        int cantVertices = panel.size();
        int gradoVertices[] = new int[cantVertices];
        int verticesImpar = 0;
        int verticesPar = 0;
        int verticeCero = 0;  
        
        for (int i = 0; i < cantVertices; i++){
            for (int j = 0; j < numAristas; j++){
                if (MIncidencia[i][j])
                    gradoVertices[i]++;
            }
        }
        
        for (int i = 0; i < cantVertices; i++){
            if (gradoVertices[i] == 0)
                verticeCero++;
            else if (gradoVertices[i] % 2 == 0)
                verticesPar++;
            else
                verticesImpar++;
        }
        
        teoremaEuler = "TEOREMA DE EULER\n\n";
        
        if (verticeCero == 0 && verticesImpar == 0 ){
            teoremaEuler += " Este grafo presenta CICLO EULERIANO\n\n"
                          + " Es decir, SI se puede recorrer todas las\n"
                          + " aristas de un solo trazo.\n\n"
                          + " Comenzando y terminando en el mismo\n"
                          + " vertice.";
        } else if (verticeCero == 0 && verticesImpar == 2){
            teoremaEuler += " Este grafo presenta CAMINO EULERIANO\n\n"
                          + " Es decir, SI se puede recorrer todas las\n"
                          + " aristas de un solo trazo.\n\n"
                          + " Comenzando en un vertice de grado\n"
                          + " impar y terminando en en el otro\n"
                          + " vertice de grado impar";
        } else {
            teoremaEuler += " Este grafo NO ES UN GRAFO EULERIANO\n\n"
                          + " No tiene ni camino ni ciclo euleriano.\n\n"
                          + " Es decir, NO se puede recorrer todas las\n"
                          + " aristas de un solo trazo.";
        }
            
        teoremaEuler += "\n\n\n";
        setMatriz(matrizAdyacencia, matrizIncidencia, teoremaEuler);
    }

    private void setMatriz(String str1, String str2, String str3) {
        matrizTxt.setText(str1 + str2 + str3);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        exitBtn = new javax.swing.JPanel();
        exitLabel = new javax.swing.JLabel();
        panelGeneral = new javax.swing.JPanel();
        verticeBtn = new javax.swing.JButton();
        aristaBtn = new javax.swing.JButton();
        adyacenteBtn = new javax.swing.JButton();
        incidenciaBtn = new javax.swing.JButton();
        matrices = new javax.swing.JScrollPane();
        matrizTxt = new javax.swing.JTextArea();
        estadoPrograma = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        eulerBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bg.setPreferredSize(new java.awt.Dimension(899, 553));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setPreferredSize(new java.awt.Dimension(898, 40));
        header.setRequestFocusEnabled(false);
        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });

        exitBtn.setBackground(new java.awt.Color(255, 255, 255));

        exitLabel.setBackground(new java.awt.Color(255, 255, 255));
        exitLabel.setFont(new java.awt.Font("Roboto Light", 0, 24)); // NOI18N
        exitLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitLabel.setText("X");
        exitLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitLabel.setPreferredSize(new java.awt.Dimension(40, 40));
        exitLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitLabelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout exitBtnLayout = new javax.swing.GroupLayout(exitBtn);
        exitBtn.setLayout(exitBtnLayout);
        exitBtnLayout.setHorizontalGroup(
            exitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exitBtnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        exitBtnLayout.setVerticalGroup(
            exitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exitLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addGap(0, 858, Short.MAX_VALUE)
                .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        bg.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 898, 40));

        panelGeneral.setBackground(new java.awt.Color(255, 255, 255));
        panelGeneral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102), 2));
        panelGeneral.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        panelGeneral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelGeneralMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelGeneralLayout = new javax.swing.GroupLayout(panelGeneral);
        panelGeneral.setLayout(panelGeneralLayout);
        panelGeneralLayout.setHorizontalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );
        panelGeneralLayout.setVerticalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
        );

        bg.add(panelGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 51, 570, 530));

        verticeBtn.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        verticeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/circulo.png"))); // NOI18N
        verticeBtn.setText("Vertice");
        verticeBtn.setToolTipText("");
        verticeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        verticeBtn.setFocusPainted(false);
        verticeBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        verticeBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        verticeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verticeBtnActionPerformed(evt);
            }
        });
        bg.add(verticeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 80, 80));

        aristaBtn.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        aristaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/linea.png"))); // NOI18N
        aristaBtn.setText("Arista");
        aristaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        aristaBtn.setFocusPainted(false);
        aristaBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        aristaBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        aristaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aristaBtnActionPerformed(evt);
            }
        });
        bg.add(aristaBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 100, 80, 80));

        adyacenteBtn.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        adyacenteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/matriz.png"))); // NOI18N
        adyacenteBtn.setText("Adyacencia");
        adyacenteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adyacenteBtn.setFocusPainted(false);
        adyacenteBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        adyacenteBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        adyacenteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adyacenteBtnActionPerformed(evt);
            }
        });
        bg.add(adyacenteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, -1, 70));

        incidenciaBtn.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        incidenciaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/matriz.png"))); // NOI18N
        incidenciaBtn.setText("Incidencia");
        incidenciaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        incidenciaBtn.setFocusPainted(false);
        incidenciaBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        incidenciaBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        incidenciaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incidenciaBtnActionPerformed(evt);
            }
        });
        bg.add(incidenciaBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 190, 110, 70));

        matrices.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Matrices", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 1, 14))); // NOI18N
        matrices.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        matrices.setViewportView(null);
        matrices.setWheelScrollingEnabled(false);

        matrizTxt.setColumns(20);
        matrizTxt.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        matrizTxt.setRows(5);
        matrizTxt.setAutoscrolls(false);
        matrizTxt.setBorder(null);
        matrices.setViewportView(matrizTxt);

        bg.add(matrices, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 340, 270, 240));

        estadoPrograma.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        estadoPrograma.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 1, 14))); // NOI18N
        bg.add(estadoPrograma, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 280, 270, 51));

        titulo.setFont(new java.awt.Font("Roboto", 1, 30)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("GRAFOS");
        titulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bg.add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, 300, -1));

        eulerBtn.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        eulerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/euler.png"))); // NOI18N
        eulerBtn.setText("Euler");
        eulerBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        eulerBtn.setFocusPainted(false);
        eulerBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eulerBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        eulerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eulerBtnActionPerformed(evt);
            }
        });
        bg.add(eulerBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 100, 80, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelGeneralMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelGeneralMouseClicked
        if (panelGeneral.getMousePosition() != null) {            
            xMouse = evt.getX();
            yMouse = evt.getY();
            
            if (Vista.numVertices != Vista.verticesMax) {
                if (!aristaActivado ){            
                    if (verticeActivado) {
                        // Al haber una arista en el panel, se habilitan las demas opciones
                        aristaBtn.setEnabled(true);
                        adyacenteBtn.setEnabled(true);
                        incidenciaBtn.setEnabled(true);
                        eulerBtn.setEnabled(true);

                        Vertice nuevoVertice = new Vertice();
                        nuevoVertice.setBounds(xMouse - 15, yMouse - 10, 31, 31);

                        panelGeneral.add(nuevoVertice);
                        panel.add(nuevoVertice);
                        nuevoVertice.dibuja(nuevoVertice.getGraphics());
                    }
                }
            } else
                JOptionPane.showMessageDialog(null, "Colocaste el maximo de vertices...",
                    "VERTICES MAXIMOS", JOptionPane.INFORMATION_MESSAGE);
        }       
    }//GEN-LAST:event_panelGeneralMouseClicked

    private void exitLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitLabelMouseClicked
        int valor = JOptionPane.showConfirmDialog(null, "¿Desea cerrar la aplicacion?",
            "ADVERTENCIA", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (valor == JOptionPane.YES_OPTION)
            System.exit(0);
    }//GEN-LAST:event_exitLabelMouseClicked

    private void exitLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitLabelMouseEntered
        exitBtn.setBackground(Color.red);
        exitLabel.setForeground(Color.white);
    }//GEN-LAST:event_exitLabelMouseEntered

    private void exitLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitLabelMouseExited
        exitBtn.setBackground(Color.white);
        exitLabel.setForeground(Color.black);
    }//GEN-LAST:event_exitLabelMouseExited

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_headerMouseDragged

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void incidenciaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incidenciaBtnActionPerformed
        mostrarMatrizIncidencia();
    }//GEN-LAST:event_incidenciaBtnActionPerformed

    private void adyacenteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adyacenteBtnActionPerformed
        mostrarMatrizAdyacencia();
    }//GEN-LAST:event_adyacenteBtnActionPerformed

    private void aristaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aristaBtnActionPerformed
        if (aristaActivado) {
            aristaActivado = false;
            verticeActivado = false;
            estadoPrograma.setText("Vertice y Arista Desactivado");
            verticeBtn.setBackground(null);
            aristaBtn.setBackground(null);
        } else {
            aristaActivado = true;
            verticeActivado = false;
            aristaBtn.setBackground(Color.decode("#79f966"));
            verticeBtn.setBackground(null);
            estadoPrograma.setText("Arista Activo");
        }
    }//GEN-LAST:event_aristaBtnActionPerformed

    private void verticeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verticeBtnActionPerformed
        if (verticeActivado) {
            aristaActivado = false;
            verticeActivado = false;
            estadoPrograma.setText("Vertice y Arista Desactivado");
            verticeBtn.setBackground(null);
            aristaBtn.setBackground(null);
        } else {
            aristaActivado = false;
            verticeActivado = true;
            verticeBtn.setBackground(Color.decode("#79f966"));
            aristaBtn.setBackground(null);
            estadoPrograma.setText("Vertice Activo");
        }
    }//GEN-LAST:event_verticeBtnActionPerformed

    private void eulerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eulerBtnActionPerformed
        mostrarTeoremaEuler();
    }//GEN-LAST:event_eulerBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {

        try {
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /*
            * If Nimbus (introduced in Java SE 6) is not available, stay with the
            * default look and feel. For details see
            * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
            */
            
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adyacenteBtn;
    private javax.swing.JButton aristaBtn;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel estadoPrograma;
    private javax.swing.JButton eulerBtn;
    private javax.swing.JPanel exitBtn;
    private javax.swing.JLabel exitLabel;
    private javax.swing.JPanel header;
    private javax.swing.JButton incidenciaBtn;
    private javax.swing.JScrollPane matrices;
    private javax.swing.JTextArea matrizTxt;
    public javax.swing.JPanel panelGeneral;
    private javax.swing.JLabel titulo;
    private javax.swing.JButton verticeBtn;
    // End of variables declaration//GEN-END:variables
}
