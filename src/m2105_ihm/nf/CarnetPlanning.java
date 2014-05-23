/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.nf;

import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author IUT2
 */
/**
 * 
 * @class CarnetPlanning
 * Gestion du carnet de contact et de la planification d'evenements
 */
public class CarnetPlanning {
    private static final String DB_FILE = "carnet.db";
    
    private ArrayList<Contact> contacts;
    private ArrayList<GroupeContacts> groupes;
    private ArrayList<Evenement> evenements;

    private String path = "";

    /**
     * Initialise la base du carnet
     */
    public CarnetPlanning() {
        if (! loadDB()) { newDB(); }
    }
    
    /**
     * Initialise la base du carnet
     */
    public CarnetPlanning(String path) {
        
        if (path != null) { this.path = path; }
        
        if (! loadDB()) { newDB(); }
    }
    
    /**
     * Retourne la liste des contacts
     * @return 
     */
    public Contact [] listeContacts() {
        return contacts.toArray(new Contact[0]);
    }
    
    /**
     * Retourne la liste des groupes de contacts
     * @return 
     */
    public GroupeContacts [] listeGroupes() {
        return groupes.toArray(new GroupeContacts[0]);
    }
    
    /**
     * Retourne la liste des evenements
     * @return 
     */
    public Evenement [] listeEvenements() {
        return evenements.toArray(new Evenement[0]);
    }
    
    /**
     * Ajoute un contact
     * @param c un contact
     * @return 
     */            
    public boolean ajouterContact(Contact c) {
        boolean success = false;
        
        if ((c != null) && (contacts.indexOf(c) == -1)) {
            contacts.add(c);
            success = true;
        }
        
        return success;
    }
    
    /**
     * Retire un contact
     * @param c un contact
     * @return 
     */    
    public boolean supprimerContact(Contact c) {
        boolean success = true;

        if ((c != null) && (contacts.indexOf(c) != -1)) {
            for(GroupeContacts g : groupes) {
                retirerContactGroupe(g, c);
            }

            for(Evenement e : evenements) {
                retirerParticipantEvenement(e, c);
            }

            contacts.remove(c);
            
            success = true;            
        }
        
        return success;        
    }
    
    /**
     * Ajoute un groupe
     * @param g un groupe de contacts
     * @return 
     */
    public boolean ajouterGroupe(GroupeContacts g) {
        boolean success = true;

        if ((g != null) && (groupes.indexOf(g) == -1)) {
            groupes.add(g);
            success = true;
        }
                
        return success;               
    }
    
    /**
     * Retire un contact
     * @param g un groupe de contacts
     * @return 
     */
    public boolean supprimerGroupe(GroupeContacts g) {
        boolean success = true;

        if ((g != null) && (groupes.indexOf(g) != -1)) {
            groupes.remove(g);
            success = true; 
        }
        
        return success;               
    }

    /**
     * Ajoute un contact à un groupe
     * @param g un groupe de contacts
     * @param c un contact
     * @return 
     */ 
    public boolean ajouterContactGroupe(GroupeContacts g, Contact c) {
        boolean success = true;
        
        if ((g != null) && (groupes.indexOf(g) != -1)) {
            success = g.addContact(c);
        }
        
        return success;               
    }

    /**
     * Retire un contact d'un groupe
     * @param g un groupe de contacts
     * @param c un contact
     * @return 
     */
    public boolean retirerContactGroupe(GroupeContacts g, Contact c) {
        boolean success = true;

        if ((g != null) && (groupes.indexOf(g) != -1)) {
            success = g.removeContact(c);
        }

        return success;               
    }

    /**
     * Retourne la liste des contacts associés à groupe
     * @param g un groupe
     * @return un tableau de contacts
     */
    public Contact [] listerContactsGroupe(GroupeContacts g) {
        Contact [] res;
        
        if ((g != null) && (groupes.indexOf(g) != -1)) {
            res = g.getContacts();
        } else {
            res = new Contact[0];
        }
        
        return res;
    }

    /**
     * Retourne la liste des groupes associés à un contact
     * @param g un groupe
     * @return un tableau de groupes de contacts
     */
    public GroupeContacts [] listerGroupesContact(Contact c) {
        GroupeContacts [] res;
        
        
        if (c != null) {
            ArrayList<GroupeContacts> liste;
            
            liste = new ArrayList<GroupeContacts>();
            for(GroupeContacts g : groupes) {
                if (g.isMembre(c)) { liste.add(g); }
            }
            
            res = liste.toArray(new GroupeContacts[0]);
        } else {
            res = new GroupeContacts[0];
        }
            
        return res;
    }
    
    /**
     * Ajoute un évènement à la liste
     * @param e un évènement
     * @return 
     */
    public boolean ajouterEvenement(Evenement e) {
        boolean success = true;

        if ((e != null) && (evenements.indexOf(e) == -1)) {
            evenements.add(e);
            success = true;
        }
        
        return success;               
    }
    
    /**
     * Retire un évènement de la liste
     * @param e un évènement
     * @return 
     */
    public boolean retirerEvenement(Evenement e) {
        boolean success = true;

        if ((e != null) && (evenements.indexOf(e) != -1)) {
            evenements.remove(e);
            success = true; 
        }
        
        return success;               
    }
    
    /**
     * Ajoute un contact à la liste des participants à l'évènement
     * @param e un évènement
     * @param c un contact
     * @return 
     */
    public boolean ajouterParticipantEvenement(Evenement e, Contact c) {
        boolean success = true;

        if ((e != null) && (evenements.indexOf(e) != -1)) {
            success = e.addParticipant(c);
        }
                
        return success;               
    }
    
    /**
     * Retire un contact de la liste des participants à l'évènement
     * @param e un évènement
     * @param c un contact
     * @return 
     */
    public boolean retirerParticipantEvenement(Evenement e, Contact c) {
        boolean success = true;

        if ((e != null) && (evenements.indexOf(e) != -1)) {
            success = e.removeParticipant(c);
        }
        
        return success;               
    }
    
    /**
     * Retourne la liste des participants à un évènement
     * @param e un évènement
     * @return un tableau de contacts
     */
    public Contact [] listerParticipantsEvenement(Evenement e) {
        Contact [] res;
        
        if ((e != null) && (evenements.indexOf(e) != -1)) {
            res = e.getParticipants();
        } else {
            res = new Contact[0];
        }
        
        return res;
    }
            
    /**
     * Met à jour la base de données persistante
     * @return 
     */
    public boolean updateDB() {
        return saveDB();
    }

    /**
     * Créaction d'une nouvelle base de données
     */
    private void newDB() {
        contacts = new ArrayList<Contact>();
        groupes = new ArrayList<GroupeContacts>();
        evenements = new ArrayList<Evenement>();
    }
    
   /**
    * Sauvegarde de la base d'un fichier
    * @return 
    */
    private boolean saveDB() {
        File file;
        boolean success = true;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;            
        
        file = new File(path + DB_FILE);
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            
            oos.writeObject(contacts);
            oos.writeObject(groupes);
            oos.writeObject(evenements);
            
        }
        catch (Exception e) {
            System.out.println("SAVE" + e);
            success = false;
        }
        finally {
                if (oos != null) { 
                    try { oos.close(); }
                    catch(IOException e) {}
                }
                
                if (fos != null) { 
                    try { fos.close(); }
                    catch(IOException e) {}
                }
            
        }
        
        return success;
    }
    
    /**
     * Chargement d'une base de données à partir d'un fichier
     * @return 
     */
    private boolean loadDB() {
        boolean success = true;
        File file = new File(path + DB_FILE);
        
        if (file.exists()) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;            

            try {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                
                contacts = (ArrayList<Contact>) ois.readObject();
                groupes = (ArrayList<GroupeContacts>) ois.readObject();
                evenements = (ArrayList<Evenement>) ois.readObject();
            }             
            catch(Exception e) {
                System.out.println("LOAD" + e);
                success = false;
            }
            finally {
                if (ois != null) { 
                    try { ois.close(); }
                    catch(IOException e) {}
                }
                
                if (fis != null) { 
                    try { fis.close(); }
                    catch(IOException e) {}
                }
            }
        } else { success = false; }
        
        return success;
    }
}
