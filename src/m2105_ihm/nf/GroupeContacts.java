/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.nf;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author IUT2
 */
/**
 * 
 * @class GroupeContacts
 * Groupe de contacts
 */
public class GroupeContacts implements java.io.Serializable {
    
    /**
     * Attributs :
     * nom : nom du groupe
     * icone : liste de coordonnées d'une forme géométrique
     */
    private String nom;
    private ArrayList<Point> points;
    private ArrayList<Contact> contacts;    
    
    /**
     * Contructeur
     * @param id identifiant unique associé à un groupe
     */
    public GroupeContacts() {
        /*
         * Initialisation des champs
         */
        this.nom = "Nouveau groupe";
        this.points = new ArrayList<Point>();
        this.contacts = new ArrayList<Contact>();
    }
    
    /**
     * Retourne le nom du groupe
     * @return 
     */
    public String getNom() { return this.nom; }

    /**
     * Affecte le nom du groupe
     * @param objet non nul
     * @return 
     */
    public boolean setNom(String nom) {
        boolean res = false;
        
        if (nom != null) { 
            this.nom = nom;
            res = true;
        }
        
        return res;
    }

    /**
     * Retourne les points définissant le dessin associé au groupe
     * @return tableau d'entiers
     */
    public Point [] getPoints() {
        return points.toArray(new Point[0]);
    }
     
    /**
     * Affecte le dessin associé au groupe
     * @param points tableau de points (x,y)
     */
    public void setPoints(Point [] p) {
        if (p != null) { 
            points.clear();
            for(int i = 0; i < p.length; i++) {
                if (p[i] != null) {
                    points.add(p[i]);
                }    
            }
        }
    }
    
    /**
     * Efface les points définissant l'icône
     */
    public void clearPoints() {
        points.clear();
    }
    
    /**
     * Indique si un contact est membre du groupe
     * @param c un contact
     * @return vrai si le contact est membre
     */
    public boolean isMembre(Contact c) {
        return ((c != null) && contacts.contains(c));
    }

    /**
     * Ajoute un contact dans le groupe
     * @param c classe identifiant un contact
     * @return vrai si le contact est objet non null et pas encore dans la liste
     */
    protected boolean addContact(Contact c) {
        boolean res = false;
        
        if (c != null) {
           if (contacts.indexOf(c) == -1) {
               contacts.add(c);
               res = true;
           }
        }
        
        return res;        
    }
    
    /**
     * Retire un contact du groupe
     * @param c classe identifiant un contact
     * @return True si le contact est dans la liste
     */
    protected boolean removeContact(Contact c) {
        boolean res = false;
        
        if (c != null) {
           int index = contacts.indexOf(c);
           
           if (index >= 0) {
               contacts.remove(index);
               res = true;
           }
        }
        
        return res;        
    }
    
    /**
     * Retourne la liste des contacts
     * @return tableau contenant les ID de chaque contact
     */
    protected Contact [] getContacts() { 
        return contacts.toArray(new Contact[0]); 
    }    
    
    /**
     * Retourne une forme textuelle d'un groupe de contacts
     * @return chaîne de caractères contenant le nom du groupe
     */
    public String toString() {
        return nom;
    }
}