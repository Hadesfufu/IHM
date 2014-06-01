/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    
    private JPanel Info,Participants, PEvent, PInfoParti, PEventB;
    private JList<Evenement> ListEvent;
    private DefaultListModel LMEvent, LMParti;
    private JList<Contact> ListParti;
    private JLabel Nom, Date;
    private JButton Modifier, Ajouter, Retirer, plus, moins;
    private Controleur controleur;    

    public PlanningUI(Controleur controleur) {
        super();
        this.controleur = controleur;
        initComponents();
        initActionListener();
    }

    private void initComponents() {
        //Panel Info
        Info = new JPanel();
        Info.setLayout(new GridLayout(0,2));
        Info.setBorder(BorderFactory.createTitledBorder("Info :"));
        
        //Panel Participants
        Participants = new JPanel();
        Participants.setLayout(new BorderLayout());
        Participants.setBorder(BorderFactory.createTitledBorder("Participants :"));
        
        //Panel Event ! 
        PEvent = new JPanel();
        PEvent.setLayout(new BorderLayout());
        PEvent.setBorder(BorderFactory.createTitledBorder("Evenements :"));
        
        //Panel regroupant Informations et Participants
        PInfoParti = new JPanel();
        PInfoParti.setLayout(new BorderLayout());
        
        //Panel pour les boutons
        PEventB = new JPanel();
        PEventB.setLayout(new BorderLayout());
                  
        //Elements Info
        Nom = new JLabel("Nom evenement");
        Date = new JLabel("Date evenement");
        Modifier = new JButton("Modifier");
        Info.add(new JLabel("Nom : "));
        Info.add(Nom);
        Info.add(new JLabel("Date : "));
        Info.add(Date);
        Info.add(Modifier);
        
        //Element Participants
        LMParti = new DefaultListModel<Contact>();
        ListParti = new JList(LMParti);
        Ajouter = new JButton("Ajouter");
        Retirer = new JButton("Retirer");
        
        Participants.add(ListParti, BorderLayout.NORTH);
        Participants.add(Ajouter, BorderLayout.WEST);
        Participants.add(Retirer, BorderLayout.EAST);
        
        
        LMEvent = new DefaultListModel<Evenement>();
        ListEvent = new JList(LMEvent);
        plus = new JButton("+");
        moins = new JButton("-");
        
        PEventB.add(plus, BorderLayout.EAST);
        PEventB.add(moins, BorderLayout.WEST);
        
        PEvent.add(ListEvent, BorderLayout.NORTH);
        PEvent.add(PEventB, BorderLayout.SOUTH);
                
        PInfoParti.add(Info, BorderLayout.NORTH);
        PInfoParti.add(Participants, BorderLayout.SOUTH);
        
        this.setLayout(new GridLayout(0,2));
        this.add(PEvent);  
        this.add(PInfoParti);
        
       
    }
    
    private void initActionListener(){
     plus.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.creerEvenement();
                }
          });
     moins.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.supprimerEvenement();
                }
          });
    ListEvent.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent evt) {
                miseAJourInfos();
                }
          });
    Modifier.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(getSelectedEvt() != null){
                    controleur.editerEvenement();
                    }
                    miseAJourInfos();
                    ListEvent.repaint();
                }
          });
    Ajouter.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.ajouterParticipantEvenement();
                    miseAJourInfos();
                }
          });
    Retirer.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.retirerParticipantEvenement();
                    miseAJourInfos();
                }
          });
    }
    
    /**
     * Ajoute une entrée dans la liste de événements
     * @param Evenement événement à ajouter
     */
    public void ajouterEvt(Evenement evt) {
       // if(evt != null){
        LMEvent.addElement(evt);
        //}
    }
    
    /**
     * Retire un événement de la liste
     * @param Evenement événement à retirer
     */    
    public void retirerEvt(Evenement evt) {
        if(LMEvent.contains(evt)){
        LMEvent.removeElement(evt);
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
    private void afficherParticipants(Evenement evt){
        
        this.LMParti.clear();
        for(Contact c : controleur.getListeContacts(evt)){
        LMParti.addElement(c);
        }
    }
    private void miseAJourInfos(){
        Evenement event = getSelectedEvt();
        this.Nom.setText(event.getIntitule());
        this.Date.setText(event.getDate()[2] + "/" + event.getDate()[1] + "/" + event.getDate()[0]);
        afficherParticipants(event);
    }

}
