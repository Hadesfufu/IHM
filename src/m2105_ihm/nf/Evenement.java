/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.nf;

import java.util.ArrayList;

/**
 *
 * @author IUT2
 */
/**
 * 
 * @class Evenement
 * Nature d'un evenement
 */
public class Evenement implements java.io.Serializable {

    /**
     * Attributs d'un évènement
     */
    private String intitule;
    private int [] date;
    private ArrayList<Contact> participants;    

    /**
     * Constructeur
     */
    public Evenement() {
        this.intitule = "Nouvel \u00E9v\u00E8nement";
        this.date = new int[] { 1970, 1, 1 };        
        this.participants = new ArrayList<Contact>();        
    }
    
    /**
     * Retourne l'intitulé de l'évènement
     * @return 
     */
    public String getIntitule() { return intitule; }

    /**
     * Affecte l'intitulé de l'évènement
     * @param objet non nul
     * @return 
     */
    public boolean setIntitule(String intitule) {       
        boolean res = false;
        
        if (intitule != null) { 
            this.intitule = intitule;
            res = true;
        }
        
        return res;
    }
    
    /**
     * Retourne le date de l'évènement
     * @return 
     */
    public int[] getDate() { return date; }

    /**
     * Affecte la date de l'évènement
     * @param objet non nul
     * @return 
     */
    public boolean setDate(int annee, int mois, int jour) {        
        this.date[0] = annee;
        this.date[1] = mois;
        this.date[2] = jour;

        return true;
    }

    /**
     * Retourne la liste des participants
     * @return tableau contenant les ID de chaque participant
     */
    public boolean isParticipant(Contact c) {
        return ((c != null) && participants.contains(c));
    }
    
    /**
     * Ajoute un participant à l'évènement
     * @param c classe identifiant un contact
     * @return True si le contact est objet non null et pas encore dans la liste
     */
    protected boolean addParticipant(Contact c) {
        boolean res = false;
        
        if (c != null) {
           if (participants.indexOf(c) == -1) {
               participants.add(c);
               res = true;
           }
        }
        
        return res;        
    }
    
    /**
     * Retire un participant à l'évènement
     * @param c classe identifiant un contact
     * @return True si le contact est dans la liste
     */
    protected boolean removeParticipant(Contact c) {
        boolean res = false;
        
        if (c != null) {
           int index = participants.indexOf(c);
           
           if (index >= 0) {
               participants.remove(index);
               res = true;
           }
        }
        
        return res;        
    }
    
    /**
     * Retourne la liste des participants
     * @return tableau contenant les ID de chaque participant
     */
    protected Contact [] getParticipants() { 
        return participants.toArray(new Contact[0]); 
    }    
    
    /**
     * Retourne une représentation textuelle d'un évènement
     * @return chaîne de caractères contenant la date et l'intitulé
     */
    public String toString() {
        return date[2] + "/" + date[1] + "/" + date[0] + " " + intitule;
    }
}