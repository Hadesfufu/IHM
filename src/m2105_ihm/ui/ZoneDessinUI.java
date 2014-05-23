/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.Point;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author IUT2
 */
/**
 * 
 * @class ZoneDessinUI
 * Zone d'édition d'un logo
 */
public class ZoneDessinUI extends Canvas implements MouseListener{

    /*
     * Liste des points définissant le dessin
     */
    private Polygon polygon = new Polygon();
    
    public ZoneDessinUI() {        
        this.addMouseListener(this);
    }
    
    /**
     * Dessine le contenu du canvas, c'est-à-dire l'icone
     * @param g un contexte graphique
     */
    public void paint(Graphics g) {
        this.setBackground(Color.WHITE);
        Dimension dim = this.getSize();
        g.drawRect(0, 0, dim.width-1, dim.height-1);
        g.drawPolygon(polygon);
    }

    /**
     * Efface le dessin
     */
    public void effacer() {
        System.out.println("Effacer logo"); // A supprimer : juste pour tester
        polygon.reset();
        this.repaint();
    }
    
    /**
     * Affecte le logo avec un ensemble de points
     * @param points tableau de points
     */
    public void setPoints(Point [] points) {
        polygon.reset();
        for(Point p: points){
        polygon.addPoint(p.x, p.y);
        }
    }

    /*
     * A COMPLETER POUR LA GESTION D'EVENEMENT SOURIS
     */        
 
    /**
     * Retourne les points définissant le dessin
     * @return tableau d'entiers
     */
    public Point [] getPoints() {
        Point [] res;
        
        res = new Point[polygon.xpoints.length];
        
        for(int i = 0; i < res.length; i++) {
            res[i] = new Point(polygon.xpoints[i], polygon.ypoints[i]);
        }
        
        return res;
    }
    
    /*
     * Taille fixe
     */
    public Dimension getSize() {
        return new Dimension(150, 150);        
    }    

    @Override
    public void mouseClicked(MouseEvent e) {
        polygon.addPoint(e.getX(), e.getY());
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
