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
 * @class Contact
 * Fiche d'un contact du carnet d'adresse
 */
public class Contact implements java.io.Serializable {
    /**
     * Attributs définissant un contact
     */
    private String nom;
    private DispoSortie disponibilite;
    private ArrayList<Hobby> hobbies;    
    private Region region;
    private String email;
    private String hobbyAutre;
    private ArrayList<StyleMusical> goutsMusicaux;
    private int [] dateNaissance; // 0 : annee, 1 : mois, 2 : jour   
    private String numeroTelephone;
    private String prenom;
        
    /**
     * Constructeur
     */
    public Contact() {
        this.nom = "Nouveau contact";
        this.disponibilite = DispoSortie.SOIR;
        this.hobbies = new ArrayList<Hobby>();
        this.region = Region.ALSACE;
        this.email = "";
        this.hobbyAutre = "";
        this.goutsMusicaux = new ArrayList<StyleMusical>();
        this.dateNaissance = new int[] { 1970, 1, 1 };        
        this.numeroTelephone = "";
        this.prenom = "Pr\u00E9nom";
    }
    
    /**
     * Retourne le nom du contact
     * @return 
     */
    public String getNom() { return nom; }

    /**
     * Affecte le nom du contact
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
     * Retourne la disponibilité pour des sorties
     * @return 
     */
    public DispoSortie getDisponibilite() { return disponibilite; }

    /**
     * Affecte la disponibilité pour des sorties
     * @param objet non nul
     * @return 
     */
    public boolean setDisponibilite(DispoSortie disponibilite) {       
        boolean res = false;
        
        if (disponibilite != null) { 
            this.disponibilite = disponibilite;
            res = true;
        }
        
        return res;
    }

    /**
     * Retourne la liste des hobby
     * @return tableau contenant la liste des hobby
     */
    public Hobby [] getHobbies() { 
        return hobbies.toArray(new Hobby[0]); 
    }
    
    /**
     * Ajoute un Hobby dans la liste
     * @param h un hobby
     * @return True si le hobby n'est pas encore dans la liste
     */
    public boolean addHobby(Hobby h) {
        boolean res = false;
        
        if (h != null) {
           if (hobbies.indexOf(h) == -1) {
               hobbies.add(h);
               res = true;
           }
        }
        
        return res;        
    }
    
    /**
     * Retire un hobby de la liste
     * @param h un hobby
     * @return True si le hobby est dans la liste
     */
    public boolean removeHobby(Hobby h) {
        boolean res = false;
        
        if (h != null) {
           int index = hobbies.indexOf(h);
           
           if (index >= 0) {
               hobbies.remove(index);
               res = true;
           }
        }
        
        return res;        
    }    
    
    /**
     * Retourne la région du contact
     * @return 
     */
    public Region getRegion() { return region; }

    /**
     * Affecte la région du contact
     * @param objet non nul
     * @return 
     */
    public boolean setRegion(Region region) {       
        boolean res = false;
        
        if (region != null) { 
            this.region = region;
            res = true;
        }
        
        return res;
    }
    
    /**
     * Retourne le mél du contact
     * @return 
     */
    public String getEmail() { return email; }

    /**
     * Affecte le mél du contact
     * @param objet non nul
     * @return 
     */
    public boolean setEmail(String email) {       
        boolean res = false;
        
        if (email != null) { 
            this.email = email;
            res = true;
        }
        
        return res;
    }

    /**
     * Retourne un autre type de hobby
     * @return 
     */
    public String getHobbyAutre() { return hobbyAutre; }

    /**
     * Precise un autre type de hobby
     * @return 
     */
    public boolean setHobbyAutre(String h) {        
        this.hobbyAutre = h;
        
        return true;
    }

    /**
     * Retourne la liste des gouts musicaux
     * @return tableau contenant la liste des styles
     */
    public StyleMusical [] getGoutsMusicaux() { 
        return goutsMusicaux.toArray(new StyleMusical[0]); 
    }
    
    /**
     * Ajoute un style musical dans la liste
     * @param s un style musical
     * @return True si le style n'est pas encore dans la liste
     */
    public boolean addStyle(StyleMusical s) {
        boolean res = false;
        
        if (s != null) {
           if (goutsMusicaux.indexOf(s) == -1) {
               goutsMusicaux.add(s);
               res = true;
           }
        }
        
        return res;        
    }
    
    /**
     * Retire un style musical de la liste
     * @param h un style
     * @return True si le style est dans la liste
     */
    public boolean removeStyle(StyleMusical s) {
        boolean res = false;
        
        if (s != null) {
           int index = goutsMusicaux.indexOf(s);
           
           if (index >= 0) {
               goutsMusicaux.remove(index);
               res = true;
           }
        }
        
        return res;        
    }    
    
    /**
     * Retourne le date de naissance du contact
     * @return 
     */
    public int[] getDateNaissance() { return this.dateNaissance; }

    /**
     * Affecte la date de naissance du contact
     * @param objet non nul
     * @return 
     */
    public boolean setDateNaissance(int annee, int mois, int jour) {        
        this.dateNaissance[0] = annee;
        this.dateNaissance[1] = mois;
        this.dateNaissance[2] = jour;
        return true;
    }
    
    /**
     * Retourne le numéro de téléphone du contact
     * @return 
     */
    public String getNumeroTelephone() { return numeroTelephone; }

    /**
     * Affecte le numéro de téléphone
     * @param objet non nul
     * @return 
     */
    public boolean setNumeroTelephone(String numeroTelephone) {       
        boolean res = false;
        
        if (numeroTelephone != null) { 
            this.numeroTelephone = numeroTelephone;
            res = true;
        }
        
        return res;
    }

    /**
     * Retourne le prénom du contact
     * @return 
     */
    public String getPrenom() { return prenom; }

    /**
     * Affecte le prénom du contact
     * @param objet non nul
     * @return 
     */
    public boolean setPrenom(String prenom) {
        boolean res = false;
        
        if (prenom != null) { 
            this.prenom = prenom;
            res = true;
        }
        
        return res;
    }
    
    /**
     * Représentation textuelle d'un contact
     * @return chaîne de caractère contenant le nom et prénom du contact
     */
    public String toString() {
        return nom + " " + prenom;
    }
}
