/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import m2105_ihm.Controleur;
        
/**
 *
 * @author IUT2
 */
public class FenetreUI extends JFrame {    
    /*
     * Composants
     */
    private JMenuItem [] menuFichier;
    private JMenuItem [] menuContacts;
    private JMenuItem [] menuEvenements;
    
    private JMenuBar barreMenu;
    
    private JTabbedPane tabs;    
    private Controleur controleur;

    public static final int MENU_FICHIER    = 0;
    public static final int MENU_CONTACTS   = 1;
    public static final int MENU_EVENEMENTS = 2;
    
    /**
     * Constructeur de la fenêtre principale
     */
    public FenetreUI(Controleur controleur) {
        super("Gestion de contacts et d'\u00E9v\u00E8nements");
        
        this.controleur = controleur;
        
        menuFichier = new JMenuItem[2];
        menuContacts = new JMenuItem[6];
        menuEvenements = new JMenuItem[4];
                
        initMenus();        
        initUIComponents();
    }
    
    /**
     * 
     */
    private void initMenus() {
       barreMenu = new JMenuBar();
       barreMenu.add(initMenuFichier());
       barreMenu.add(initMenuContacts());
       this.setJMenuBar(barreMenu);
    }

    private JMenu initMenuFichier() {
        JMenu menu;
                
        menu = new JMenu("Fichier");
        
        /* Enregistrer */        
        menuFichier[0] = new JMenuItem("Enregistrer");
        menuFichier[0].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.enregistrer();
                }
            }
        );
        menu.add(menuFichier[0]);
        
        /* Quitter */
        menuFichier[1] = new JMenuItem("Quitter");
        menuFichier[1].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.quitter();
                }
            }
        );
        menu.add(menuFichier[1]);
        
        return menu;
    }
    
    /**
     * Crée un menu pour la gestion des contacts et groupes de contacts
     * @return 
     */
    private JMenu initMenuContacts() {
        
        JMenu m = new JMenu("Contacts");
        
        menuContacts[0] = new JMenuItem("Create new Contact");
        menuContacts[0].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.creerContact();
                }
          });
        m.add(menuContacts[0]);
        
        menuContacts[1] = new JMenuItem("Create New Group");
        menuContacts[1].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.creerGroupe();
                }
          });
        m.add(menuContacts[1]);
        
        
        menuContacts[2] = new JMenuItem("Delete Contact");
        menuContacts[2].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.supprimerContact();
                }
          });
        m.add(menuContacts[2]);
        
        
        menuContacts[3] = new JMenuItem("Delete Group");
        menuContacts[3].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.supprimerGroupe();
                }
          });
        m.add(menuContacts[3]);
        
        menuContacts[4] = new JMenuItem("Add Group/Contact");
        menuContacts[4].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.ajouterContactGroupe();
                }
          });
        m.add(menuContacts[4]);
        
        menuContacts[5] = new JMenuItem("Remove Group/Contact");
        menuContacts[5].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.retirerContactGroupe();
                }
          });
        m.add(menuContacts[5]);
        
        return m;
    }
    
    /**
     * Crée un menu pour la gestion des évènements
     */
    private JMenu initMenuEvenements() {
        /* A COMPLETER TP5 */
        return null;
    }    
    
    /**
     * Création des composants constituant la fenêtre principale
     */
    private void initUIComponents() {
        /*
         * Contenu avec des onglets
         */
        tabs = new JTabbedPane();
        tabs.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            controleur.tabChanged(tabs.getSelectedIndex());
          }
        });
        
        this.setLayout(new BorderLayout());        
        this.add(tabs, BorderLayout.CENTER);
    }
    
    /**
     * Active des entrees des menus
     */
    public void setEnabled(int menu, int index, boolean enabled) {
        switch(menu) {
            case MENU_FICHIER:
                if ((index >= 0) && (index < menuFichier.length)) {
                    if (menuFichier[index] != null) {
                        menuFichier[index].setEnabled(enabled);
                    }
                }
                break;
                
            case MENU_CONTACTS:
                if ((index >= 0) && (index < menuContacts.length)) {
                    if (menuContacts[index] != null) {
                        menuContacts[index].setEnabled(enabled);
                    }
                }
                break;
                
            case MENU_EVENEMENTS:
                if ((index >= 0) && (index < menuEvenements.length)) {
                    if (menuEvenements[index] != null) {
                        menuEvenements[index].setEnabled(enabled);
                    }
                }
                break;
        }
    }
    
    /*
     * Rend visible la fenetre
     */
    public void afficher() {
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 700);
        this.setVisible(true);                        
    }
    
    /**
     * Ajoute un onglet au contenu de la fentre
     * @param onglet un panel a ajouter
     * @param titre titre de l'onglet
     */
    public void addTab(JPanel onglet, String titre) {
        tabs.addTab(titre, onglet);
    }    
}
