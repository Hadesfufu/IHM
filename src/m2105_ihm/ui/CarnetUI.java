/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.DefaultMutableTreeNode;  
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import m2105_ihm.Controleur;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.GroupeContacts;

/**
 *
 * @author IUT2
 */
public class CarnetUI extends javax.swing.JPanel 
                      implements TreeSelectionListener {
    
    /*
     * Composants de l'interface
     */
    private FicheGroupeUI          ficheGroupe;    
    private FicheContactUI         ficheContact;
    
    private JPanel                 cardPanel;
    private JTree                  listeContacts;
    private JScrollPane            listeDefilante;
    private CardLayout             fiches;

    protected Controleur             controleur;

    /**
     * Panel pour le carnet de contacts
     */
    public CarnetUI(Controleur controleur) {
        super();
        
        this.controleur = controleur;
        
        initListe();
        initUIComponents();
        
        ficheContact.setAnnulerListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Contact c = getSelectedContact();
                    ficheContact.setValues(c);
                }
            }
        );

        ficheContact.setModifierListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Contact c = getSelectedContact();
                    ficheContact.getValues(c);     
                    updateEntry(c);
                }
            }
        );

        ficheGroupe.setAnnulerListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    GroupeContacts g = getSelectedGroupe();
                    ficheGroupe.setValues(g, getControleur().getListeContacts(g));
                }
            }
        );

        ficheGroupe.setModifierListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    GroupeContacts g = getSelectedGroupe();
                    ficheGroupe.getValues(g);
                    updateEntry(g);
                }
            }
        );        
    }
    
    /**
     * Usage pour les classes anonymes
     */
    private Controleur getControleur() {
        return controleur;
    }
    
    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */
    private void initUIComponents() {
        this.setLayout(new BorderLayout());

        /* 
         * Ajout de la liste de contact dans une zone défilante 
         */
        listeContacts = new JTree(listeRacine);
        listeContacts.addTreeSelectionListener(this);

        /* Astuce pour ne pas pouvoir fermer un "dossier" de l'arbre */
        BasicTreeUI treeUI = (BasicTreeUI) listeContacts.getUI();
        treeUI.setCollapsedIcon(null);
        treeUI.setExpandedIcon(null);

        listeDefilante = new JScrollPane();
	listeDefilante.getViewport().add(listeContacts);
        this.add(listeDefilante, BorderLayout.WEST);
        
        /* 
         * Ajout des fiches avec commutation grace au layout
         */
        ficheGroupe = new FicheGroupeUI();
        ficheContact = new FicheContactUI();
        
        JPanel vide = new JPanel();        
        fiches = new CardLayout();
        fiches.addLayoutComponent(ficheContact, "contact");
        fiches.addLayoutComponent(vide, "vide");
        fiches.addLayoutComponent(ficheGroupe, "groupe");
        
        cardPanel = new JPanel();
        cardPanel.setLayout(fiches);
        cardPanel.add(ficheContact, "contact");
        cardPanel.add(vide, "vide");
        cardPanel.add(ficheGroupe, "groupe");
        this.add(cardPanel, BorderLayout.CENTER);
    }

    /**
     * Ajoute une entrée dans l'arbre pour les contacts
     * @param title texte affiché dans la liste pour un contact
     * @param Contact objet contact associé
     */
    public boolean ajouterContact(String title, Contact contact) {
        boolean res;
        
        res = ajouter(listeNoeudContacts, title, contact);
        if (res) { selectFirstContact(); }
        
        return res;
    }
    
    /**
     * Retire une entrée dans l'arbre pour les contacts
     * @param Contact contact à retirer
     */    
    public boolean retirerContact(Contact contact) {
        boolean res;
        
        res = retirer(listeNoeudContacts, contact);
        if (res) { selectFirstContact(); }
        
        return res;
    }

    /**
     * Ajoute une entrée dans l'arbre pour les groupes
     * @param title texte affiché dans l'arbre pour un groupe
     * @param GroupeContacts groupe de contacts associé
     */
    public boolean ajouterGroupe(String title, GroupeContacts groupe) {
        boolean res;
        
        res = ajouter(listeNoeudGroupes, title, groupe);
        if (res) { selectFirstGroupe(); }
        
        return res;
    }
    
    /**
     * Retire une entrée dans l'arbre pour les groupe
     * @param GroupeContacts groupe que l'on veut retirer
     */    
    public boolean retirerGroupe(GroupeContacts groupe) {
        boolean res;
        
        res = retirer(listeNoeudGroupes, groupe);
        if (res) { selectFirstGroupe(); }
        
        return res;
    }
        
    /**
     * Retourne le contact sélectionné
     */
    public Contact getSelectedContact() {
        NodeData data;
        Contact c = null; 
        DefaultMutableTreeNode node;
        
        node = (DefaultMutableTreeNode) selected.getLastPathComponent();
        data = (NodeData) node.getUserObject();
        if (data.id instanceof Contact) {
            c = (Contact) data.id;
        }

        return c;
    }

    /**
     * Retourne le groupe de contacts sélectionné
     */
    public GroupeContacts getSelectedGroupe() {
        NodeData data;
        GroupeContacts g = null;
        DefaultMutableTreeNode node;
        
        node = (DefaultMutableTreeNode) selected.getLastPathComponent();
        data = (NodeData) node.getUserObject();

        if (data.id instanceof GroupeContacts) {
            g = (GroupeContacts) data.id;
        }
        
        return (GroupeContacts) g;
    }

    /**
     * Affiche toutes les feuilles de l'arbre du composant listeContacts
     */
    public void showAll() {        
        for(int i = listeContacts.getRowCount(); i >= 0; i--) {
          listeContacts.expandRow(i);
        }
    }
    
    /**
     * Sélectionne le premier contact de la liste des contacts
     */
    public void selectFirstContact() {
        NodeData data;        
        DefaultMutableTreeNode node;
        
        if (! listeNoeudContacts.isLeaf()) {
           node = (DefaultMutableTreeNode) listeNoeudContacts.getChildAt(0);
           data = (NodeData) node.getUserObject();        
           setFicheContact((Contact) data.id);
           highlightSelected(node);
        } else {
            fiches.show(cardPanel,"vide");
        }
    }

    /**
     * Sélectionne le premier groupe de contacts de la liste des groupes
     */    
    public void selectFirstGroupe() {
        NodeData data;        
        DefaultMutableTreeNode node;
        
        if (! listeNoeudGroupes.isLeaf()) {
           node = (DefaultMutableTreeNode) listeNoeudGroupes.getChildAt(0);
           data = (NodeData) node.getUserObject();        
           setFicheGroupe((GroupeContacts) data.id);        
           highlightSelected(node);           
        } else {
            fiches.show(cardPanel,"vide");
        }
    }
    
    /**
     * Rend visible la sélection
     */
    private void highlightSelected(DefaultMutableTreeNode node) {
        DefaultTreeModel model;
        
        model = (DefaultTreeModel)listeContacts.getModel();
        selected = new TreePath(model.getPathToRoot(node));
        listeContacts.setSelectionPath(selected);
    }

    /**
     * Traite les évènements liés à une sélection dans la liste de contacts
     */
    public void valueChanged(TreeSelectionEvent e) {
        Object node, data;
        TreePath newSelected = listeContacts.getSelectionPath();
        
        /* Vérifie qu'il y a une sélection */
        if (newSelected == null) { return; }
        
        /* Récupère la sélection */
        node = newSelected.getLastPathComponent();
        data = ((DefaultMutableTreeNode) node).getUserObject();
        
        /* Vérifie s'il s'agit d'un contact ou un groupe de contact */
        if (data instanceof NodeData) {
            selected = newSelected;
            if (((NodeData) data).id instanceof Contact) {                
                /* Affiche le contact sélectionné */
                setFicheContact((Contact) ((NodeData) data).id);
            } else {
                /* Affiche le groupe de contacts sélectionné */
                setFicheGroupe((GroupeContacts) ((NodeData) data).id);
            }
        } else {
            listeContacts.setSelectionPath(selected);
        }
    }
    
    /**
     * Affiche les informations sur le contact sélectionné
     */
    private void setFicheContact(Contact contact) {
        controleur.setContactSelected(true);
        fiches.first(cardPanel); // Affiche la fiche contact
        ficheContact.setValues(contact); // affiche les données du contact 
    }

    /**
     * Affiche les informations sur le groupe de contacts sélectionné
     */
    private void setFicheGroupe(GroupeContacts groupe) {
        controleur.setContactSelected(false);
        fiches.last(cardPanel); // Affiche la fiche contact
        ficheGroupe.setValues(groupe, controleur.getListeContacts(groupe)); // affiche les données du contact        
    }
        
    /**
     * Ajoute une entrée dans l'arbre pour un noeud
     * @param title texte affiché dans l'arbre pour un contact
     * @param itemData données associées à l'entrée
     */
    private boolean ajouter(DefaultMutableTreeNode root, String title, Object itemData) {
        boolean success = false;
        
        if ((title != null) && (itemData != null)) {
            DefaultMutableTreeNode node;
        
            node = new DefaultMutableTreeNode(new NodeData(title, itemData));
            // XXX S'occuper du tri par ordre alphabétique
            //root.add(node);
            root.insert(node, 0);
            
            /* redraw content */
            DefaultTreeModel model = (DefaultTreeModel)listeContacts.getModel();
            model.reload(listeRacine);
            showAll();            
            
            success = true;
        }
        
        return success;
    }
    
    /**
     * Retire une entrée dans l'arbre pour un noeud
     * @param itemData données associées à l'entrée
     */    
    private boolean retirer(DefaultMutableTreeNode root, Object itemData) {
        boolean success = false;
        
        if (itemData != null) {
            NodeData data;
            DefaultMutableTreeNode node;
            int count = root.getChildCount();
            
            for(int i = count - 1; i >= 0; i--) {
                node = (DefaultMutableTreeNode) root.getChildAt(i);
                data = (NodeData) node.getUserObject();
                
                if (itemData.equals(data.id)) {
                    root.remove(i);
                }
            }
            
            /* redraw content */
            DefaultTreeModel model = (DefaultTreeModel)listeContacts.getModel();
            model.reload(listeRacine);
            showAll();

            success = true;
        }
        
        return success;
    }

    private void updateEntry(Object c) {
        NodeData data;
        Enumeration e;
        DefaultMutableTreeNode node;
        
        if (c == null) { return; }
        
        if (c instanceof Contact) {
           e = listeNoeudContacts.children();
        } else if (c instanceof GroupeContacts)  {
           e = listeNoeudGroupes.children(); 
        } else {
           return;
        }
        
        while(e.hasMoreElements()) {
            node = (DefaultMutableTreeNode) e.nextElement();
            data = (NodeData) node.getUserObject();
            if (c == data.id ) {
                DefaultTreeModel model;
                if (c instanceof Contact) {
                    data.title = ((Contact) c).getNom() + " " + ((Contact) c).getPrenom();
                } else {
                    data.title = ((GroupeContacts) c).getNom();                    
                }
                
                model = (DefaultTreeModel)listeContacts.getModel();
                model.reload(listeRacine);
                showAll();
                selected = new TreePath(model.getPathToRoot(node));
                listeContacts.setSelectionPath(selected);
                break;
            }
        }
        
    }
    
    /**
     * Crée et initialise la structure arborescente pour le contenu du carnet
     */
    private void initListe() {        
        /* Noeuds élémentaires de l'arbre de contacts */
        listeRacine = new DefaultMutableTreeNode("Contacts et groupes de contacts");
        listeNoeudGroupes = new DefaultMutableTreeNode("Groupes");
        listeNoeudContacts = new DefaultMutableTreeNode("Contacts");
        listeRacine.add(listeNoeudContacts);
        listeRacine.add(listeNoeudGroupes);        
    }
        
    /*
     * Principaux élements de l'arbre
     */
    private TreePath               selected;    
    private DefaultMutableTreeNode listeRacine;
    private DefaultMutableTreeNode listeNoeudGroupes;
    private DefaultMutableTreeNode listeNoeudContacts;
        
    /*
     * Classe de données spécifiques pour les entrées dans l'arbre
     */
    private class NodeData {
        public String title;
        public Object id;
        
        public NodeData(String title, Object id) {
            this.title = title;
            this.id = id;
        }
        
        public String toString() {
            return this.title;
        }
    }    
}
