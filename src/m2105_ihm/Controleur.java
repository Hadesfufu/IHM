/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import m2105_ihm.nf.CarnetPlanning;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.Evenement;
import m2105_ihm.nf.GroupeContacts;
import m2105_ihm.ui.AjoutParticipants;
import m2105_ihm.ui.CreationEvent;
import m2105_ihm.ui.CarnetUI;
import m2105_ihm.ui.CreationEvent;
import m2105_ihm.ui.FenetreUI;
import m2105_ihm.ui.ModificationEvent;
import m2105_ihm.ui.PlanningUI;
import m2105_ihm.ui.SuppressionEvent;
import m2105_ihm.ui.SuppressionParticipants;

/**
 *
 * @author IUT2
 */
public class Controleur {
    
    /*
     * Noyau Fonctionnel
     */    
    CarnetPlanning nf;
            
    /*
     * Composants
     */
    private FenetreUI fenetre;
    
    private CarnetUI carnetUI;
    private PlanningUI planningUI;

    /**
     * Constructeur de la fenêtre principale
     */
    public Controleur() {
        nf = new CarnetPlanning();
        
        initUI();
        initContent();
    }
    
    /**
     * Création des composants constituant la fenêtre principale
     */
    private void initUI() {
        fenetre = new FenetreUI(this);
        carnetUI = new CarnetUI(this);
        planningUI = new PlanningUI(this);
                               
        fenetre.addTab(carnetUI, "Carnet");
        fenetre.addTab(planningUI, "Planning");
        
        fenetre.afficher();
    }
    
    /**
     * Action créer un nouveau contact
     */
     public void creerContact() {
       Contact buff = new Contact();
       nf.ajouterContact(buff);
       carnetUI.ajouterContact(buff.getNom() + buff.getPrenom() , buff );
    }

    /**
     * Action supprimer contact
     */
    public void supprimerContact() {
    Contact buff = carnetUI.getSelectedContact();
    if(this.afficherConfirmation(buff))
    {
    nf.supprimerContact(buff);
    carnetUI.retirerContact(buff);
    }
    }
    
    /**
     * Action créer un groupe de contacts
     */
    public void creerGroupe() {
        GroupeContacts buff = new GroupeContacts();
        nf.ajouterGroupe(buff);
        carnetUI.ajouterGroupe(buff.getNom(), buff);
    }

    /**
     * Action supprimer un groupe de contacts
     */
    public void supprimerGroupe() {
    GroupeContacts buff = carnetUI.getSelectedGroupe();
    if(this.afficherConfirmation(buff))
    {
    nf.supprimerGroupe(buff);
    carnetUI.retirerGroupe(buff);
    }
    }
    
    /**
     * Ajouter un contact à un groupe
     */
    public void ajouterContactGroupe() {
        nf.ajouterContactGroupe(this.afficherChoixMembreContact("Ajouter Groupe",  nf.listeGroupes()),  carnetUI.getSelectedContact());
    }

    /**
     * Retirer un contact d'un groupe
     */
    public void retirerContactGroupe() {
        nf.retirerContactGroupe(this.afficherChoixMembreContact("Retirer Groupe", nf.listerGroupesContact(carnetUI.getSelectedContact())), carnetUI.getSelectedContact());
    }

    /**
     * 
     */
    public void creerEvenement() {
        Evenement buff = new Evenement();
        CreationEvent ce = new CreationEvent(buff, fenetre);
        ce.setVisible(true);
        if(!ce.isCancel())
        {
         this.planningUI.ajouterEvt(buff);
         this.nf.ajouterEvenement(buff);
        }
    }

    /**
     * 
     */
    
    public void supprimerEvenement() {
        Evenement buff = planningUI.getSelectedEvt();
        if(buff != null){
        SuppressionEvent se = new SuppressionEvent(buff.getIntitule(), fenetre);
        se.setVisible(true);
        if(se.confirmer()){
            nf.retirerEvenement(this.planningUI.getSelectedEvt());
            this.planningUI.retirerEvt(this.planningUI.getSelectedEvt());            
            }  
        }
    }
    
    /**
     * 
     */
    
    public void editerEvenement(){
        Evenement e = this.planningUI.getSelectedEvt();
        ModificationEvent me = new ModificationEvent(e, fenetre);
        me.setVisible(true);
    }
    
    /**
     * 
     */
    public void ajouterParticipantEvenement() {
      Evenement e = this.planningUI.getSelectedEvt();
      if(e != null){
      List<Contact> TotalContact = new LinkedList<Contact>(Arrays.asList(this.nf.listeContacts()));
      List<Contact> Ajout;
      List<Contact> Participants = new LinkedList<Contact>(Arrays.asList(this.getListeContacts(this.planningUI.getSelectedEvt())));
      TotalContact.removeAll(Participants);
      AjoutParticipants ap = new AjoutParticipants(TotalContact, fenetre);
      ap.setVisible(true);
      if(!ap.isCancel()){
      for(Contact c : ap.getResult()){
          this.nf.ajouterParticipantEvenement(e, c);}
      }
      }
 }

    /**
     * 
     */
    public void retirerParticipantEvenement() {
        Evenement e = this.planningUI.getSelectedEvt();
        if(e != null){
            List<Contact> Participants = new LinkedList<Contact>(Arrays.asList(this.getListeContacts(this.planningUI.getSelectedEvt())));
            SuppressionParticipants sp = new SuppressionParticipants(Participants, fenetre);
            sp.setVisible(true);
            if(!sp.isCancel()){
                for(Contact c : sp.getResult()){
                     this.nf.retirerParticipantEvenement(e, c);
                }
            }
        }
    }

    /**
     * Boîte de dialogue pour confirmer la suppression d'un contact
     * @param c un contact
     * @return vrai si confirmé
     */
    protected boolean afficherConfirmation(Contact c) {
        boolean res = false;

        if (c != null) {
            String [] choix = new String[] { "Supprimer", "Annuler" }; 
            
            Object selectedValue = JOptionPane.showOptionDialog(fenetre,
                  "Voulez-vous vraiment supprimer le contact : " 
                  + c.getPrenom() + " " + c.getNom() + " ?", 
                  "Suppression d'un contact",
                  JOptionPane.DEFAULT_OPTION,
                  JOptionPane.QUESTION_MESSAGE, 
                  null,
                  choix,
                  choix[1]);
            res = (((Integer) selectedValue) == 0);
        }
        
        return res;
    }

    /**
     * Boîte de dialogue pour confirmer la suppression d'un groupe de contacts
     * @param g un groupe de contacts
     * @return vrai si confirmé
     */    
    protected boolean afficherConfirmation(GroupeContacts g) {
        boolean res = false;

        if (g != null) {
            String [] choix = new String[] { "Supprimer", "Annuler" }; 
            
            Object selectedValue = JOptionPane.showOptionDialog(fenetre,
                  "Voulez-vous vraiment supprimer le groupe de contacts : " 
                  + g.getNom() + " ?", 
                  "Suppression d'un groupe contacts",
                  JOptionPane.DEFAULT_OPTION,
                  JOptionPane.QUESTION_MESSAGE, 
                  null,
                  choix,
                  choix[1]);

            res = (((Integer) selectedValue) == 0);
        }
        
        return res;
    }

    /**
     * Boîte de dialogue pour confirmer la suppression d'un événement
     * @param e un événement
     * @return vrai si confirmé
     */    
    protected boolean afficherConfirmation(Evenement e) {
        boolean res = false;

        if (e != null) {
            String [] choix = new String[] { "Supprimer", "Annuler" }; 
            
            Object selectedValue = JOptionPane.showOptionDialog(fenetre,
                  "Voulez-vous vraiment supprimer cet \u00E9v\u00E9nement : " 
                  + e.getIntitule() + " ?", 
                  "Suppression d'un \u00E9v\u00E9nement",
                  JOptionPane.DEFAULT_OPTION,
                  JOptionPane.QUESTION_MESSAGE, 
                  null,
                  choix,
                  choix[1]);

            res = (((Integer) selectedValue) == 0);
        }
        
        return res;
    }
    
    /**
     * Boîte de dialogue choisir un participant à un événement
     * @param titre titre de la fenêtre
     * @param contacts liste des contacts possibles
     * @return Contact choisi sinon valeur null
     */
    protected Contact afficherChoixParticipant(String titre, Contact [] contacts) {
        Contact res = null;
        if (titre == null) { titre = ""; }
        
        if ((contacts != null) && (contacts.length > 0)) {
            Object selectedValue = JOptionPane.showInputDialog(fenetre,
                  "Choisissez un participant : ", 
                  titre,
                  JOptionPane.INFORMATION_MESSAGE, 
                  null,
                  contacts,
                  contacts[0]);
            
            res = (Contact) selectedValue; 
        }
        
        return res;
    }
    

    /**
     * Boîte de dialogue choisir un groupe auquel ajouter un contact
     * @param titre titre de la fenêtre
     * @param groupes liste des groupes existants
     * @return groupe choisi sinon valeur null
     */    
    protected GroupeContacts afficherChoixMembreContact(String titre, GroupeContacts [] groupes) {
        GroupeContacts res = null;
        if (titre == null) { titre = ""; }
        
        if ((groupes != null) && (groupes.length > 0)) {
            Object selectedValue = JOptionPane.showInputDialog(fenetre,
                  "Choisissez un groupe : ", 
                  titre,
                  JOptionPane.INFORMATION_MESSAGE, 
                  null,
                  groupes,
                  groupes[0]);
            
            res = (GroupeContacts) selectedValue;          
        }
        
        return res;
    }

    /**
    * Indique si un contact ou un groupe est sélectionné
    * @param selected vrai si un contact est sélectionné
    */
    public void setContactSelected(boolean selected) {
        this.contactSelected = selected;
        tabChanged(0);
    }

    /**
    * Indique si un événement est sélectionné
    * @param selected vrai si un événement est sélectionné
    */
    public void setEvtSelected(boolean selected) {
        this.evtSelected = selected;
        tabChanged(1);
    }
    
    /**
     * Met à jour la base de données
     */
    public void enregistrer() {
        nf.updateDB();
    }    
        
    /**
     * Quitter l'application sans enregistrer les modifications
     */
    public void quitter() {
        System.exit(0);
    }

    /**
     * Alimente la liste avec la liste des contacts existants
     */
    private void initContent() {
        for(Contact c : nf.listeContacts()) {
            carnetUI.ajouterContact(c.getNom() + " " + c.getPrenom(), c);
        }
        
        for(GroupeContacts g : nf.listeGroupes()) {
            carnetUI.ajouterGroupe(g.getNom(), g);
        }
        
        for(Evenement e : nf.listeEvenements()) {
            planningUI.ajouterEvt(e);
        }
        
        carnetUI.showAll();
        carnetUI.selectFirstContact();
        tabChanged(0);        
    }
       
    /*
     * Commutation dans les panneaux
     */
    public void tabChanged(int index) {
        switch(index) {
            case 0:
                 fenetre.setEnabled(fenetre.MENU_CONTACTS, 0, true);
                 fenetre.setEnabled(fenetre.MENU_CONTACTS, 1, true);
                 fenetre.setEnabled(fenetre.MENU_CONTACTS, 2,   contactSelected);
                 fenetre.setEnabled(fenetre.MENU_CONTACTS, 3, ! contactSelected);
                 fenetre.setEnabled(fenetre.MENU_CONTACTS, 4,   contactSelected);
                 fenetre.setEnabled(fenetre.MENU_CONTACTS, 5,   contactSelected);                 

                 fenetre.setEnabled(fenetre.MENU_EVENEMENTS, 0, false);
                 fenetre.setEnabled(fenetre.MENU_EVENEMENTS, 1, false);
                 fenetre.setEnabled(fenetre.MENU_EVENEMENTS, 2, false);
                 fenetre.setEnabled(fenetre.MENU_EVENEMENTS, 3, false);
                 break;
            case 1:
                 fenetre.setEnabled(fenetre.MENU_CONTACTS, 0, false);
                 fenetre.setEnabled(fenetre.MENU_CONTACTS, 1, false);
                 fenetre.setEnabled(fenetre.MENU_CONTACTS, 2, false);
                 fenetre.setEnabled(fenetre.MENU_CONTACTS, 3, false);
                 fenetre.setEnabled(fenetre.MENU_CONTACTS, 4, false);
                 fenetre.setEnabled(fenetre.MENU_CONTACTS, 5, false);

                 fenetre.setEnabled(fenetre.MENU_EVENEMENTS, 0, true);
                 fenetre.setEnabled(fenetre.MENU_EVENEMENTS, 1, evtSelected);
                 fenetre.setEnabled(fenetre.MENU_EVENEMENTS, 2, evtSelected);
                 fenetre.setEnabled(fenetre.MENU_EVENEMENTS, 3, evtSelected);            
                 break;                
        }
    }   
        
        
    /**
     * Retourne la liste des contacts associés à un groupe
     */
    public Contact [] getListeContacts(GroupeContacts g) {
        return nf.listerContactsGroupe(g);
    }

    /**
     * Retourne la liste des contacts associés à un groupe
     */
    public Contact [] getListeContacts(Evenement e) {
        return nf.listerParticipantsEvenement(e);
    }
    
    private boolean contactSelected = true;
    private boolean evtSelected = false;
}
