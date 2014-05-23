/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import m2105_ihm.Controleur;
import m2105_ihm.nf.Evenement;

/**
 *
 * @author IUT2
 */
public class PlanningUI extends javax.swing.JPanel {
    /**
     * Creates new form CarnetUI
     */
    
    private Controleur controleur;    

    public PlanningUI(Controleur controleur) {
        super();
        
        this.controleur = controleur;
                
        initComponents();
    }

    private void initComponents() {
        this.add(new javax.swing.JLabel("Evenements"));
    }
    
    /**
     * Ajoute une entrée dans la liste de événements
     * @param Evenement événement à ajouter
     */
    public boolean ajouterEvt(Evenement evt) {
    }
    
    /**
     * Retire un événement de la liste
     * @param Evenement événement à retirer
     */    
    public boolean retirerEvt(Evenement evt) {
    }    

    /*
     * Retourne l'événement sélectionné
     */
    public Evenement getSelectedEvt() {        
    }    
        
    /**
     * Usage pour les classes anonymes
     */
    private Controleur getControleur() {
        return controleur;
    }    
}
