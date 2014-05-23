/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import m2105_ihm.Controleur;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.Evenement;

/**
 *
 * @author IUT2
 */
public class PlanningUI extends javax.swing.JPanel {
    /**
     * Creates new form CarnetUI
     */
    
    private JPanel Info,Participants, PButtons, PEvent, RC;
    private JList<Evenement> ListEvent;
    private DefaultListModel LMEvent;
    private JList<Contact> ListParti;
    private JLabel Nom, Date;
    private JButton Modifier, Ajouter, Retirer, plus, moins;
    private Controleur controleur;    

    public PlanningUI(Controleur controleur) {
        super();
         
        initComponents();
    }

    private void initComponents() {
        this.add(new javax.swing.JLabel("Evenements"));
        this.controleur = controleur;
        Info = new JPanel();
        Info.setLayout(new BorderLayout());
        Info.setBorder(BorderFactory.createTitledBorder("Info :"));
        
        Participants = new JPanel();
        Participants.setLayout(new BorderLayout());
        Participants.setBorder(BorderFactory.createTitledBorder("Participants :"));
        
        PEvent = new JPanel();
        PEvent.setLayout(new BorderLayout());
        
        RC = new JPanel();
        RC.setLayout(new BorderLayout());
        
        Nom = new JLabel("El Bobo!");
        Date = new JLabel("El Bobo! 2403");
        Modifier = new JButton("Modifier");
        Info.add(Nom, BorderLayout.NORTH);
        Info.add(Date, BorderLayout.CENTER);
        Info.add(Modifier, BorderLayout.EAST);
        
        LMEvent = new DefaultListModel();
        ListParti = new JList(LMEvent);
        Ajouter = new JButton("Ajouter");
        Retirer = new JButton("Retirer");
        
        Participants.add(ListParti);
        Participants.add(Ajouter);
        Participants.add(Retirer);
        
        ListEvent = new JList<Evenement>();
        plus = new JButton("+");
        moins = new JButton("-");
        
        PEvent.add(ListEvent, BorderLayout.NORTH);
        PEvent.add(plus, BorderLayout.EAST);
        PEvent.add(moins, BorderLayout.WEST);
        
        
        RC.add(Info, BorderLayout.NORTH);
        RC.add(Participants, BorderLayout.SOUTH);
        
        this.add(PEvent, BorderLayout.WEST);
        this.add(RC, BorderLayout.EAST);
       
    }
    
    /**
     * Ajoute une entrée dans la liste de événements
     * @param Evenement événement à ajouter
     */
    public boolean ajouterEvt(Evenement evt) {
        if(evt != null){
        LMEvent.addElement(evt);
        return true;}
        else{
        return false;
        }
    }
    
    /**
     * Retire un événement de la liste
     * @param Evenement événement à retirer
     */    
    public boolean retirerEvt(Evenement evt) {
        if(LMEvent.contains(evt)){
        LMEvent.removeElement(evt);
        return true;}
        else{ 
            return false;
        }
    }    

    /*
     * Retourne l'événement sélectionné
     */
    public Evenement getSelectedEvt() {
        return ListEvent.getSelectedValue();
    }    
        
    /**
     * Usage pour les classes anonymes
     */
    private Controleur getControleur() {
        return controleur;
    }    
}
