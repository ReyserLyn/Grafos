/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Frames.Vista;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 */

public class Vertice extends javax.swing.JPanel implements MouseListener, MouseMotionListener {

    private int vertice = -1;

    public Vertice() {
        initComponents();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        setVisible(true);
    }

    public void dibuja(Graphics g) {
        g.drawOval(0, 0, 30, 30);
        g.setColor(Color.black);
        g.setFont(new Font("Roboto", Font.BOLD, 16));
        g.drawString(Vista.nombreVertices[Vista.numVertices], 10, 20);
        vertice = Vista.numVertices;
        Vista.numVertices++;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(45, 45));
        setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );
    }// </editor-fold>                        
    // Variables declaration - do not modify                     
    // End of variables declaration                   
   
    @Override
    public void mouseClicked(MouseEvent evt) {   
        Point posMouse;
        
        if (Vista.numAristas != Vista.aristasMax){
            if (Vista.aristaActivado ){
                if (Vista.primerVerticeSeleccionado) {              
                    posMouse = this.getLocation();
                    Vista.x1 = posMouse.x;
                    Vista.y1 = posMouse.y;
                    Vista.partida = this.vertice;
                    Vista.primerVerticeSeleccionado = false;
                    setName("E");
                } else {
                    posMouse = this.getLocation();
                    Vista.x2 = posMouse.x;
                    Vista.y2 = posMouse.y;
                    Vista.llegada = this.vertice;

                    Vista.crearArista(Vista.x1, Vista.y1, Vista.x2, Vista.y2);

                    Vista.MAdyacencia[Vista.partida][Vista.llegada] = true;
                    Vista.MAdyacencia[Vista.llegada][Vista.partida] = true;

                    Vista.MIncidencia[Vista.partida][Vista.arista] = true;
                    Vista.MIncidencia[Vista.llegada][Vista.arista] = true;
                    Vista.arista++;

                    Vista.primerVerticeSeleccionado = true;
                }
            }
        } else
            JOptionPane.showMessageDialog(null, "Colocaste el maximo de aristas...", 
                            "ARISTAS MAXIMAS", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}

