/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.nf;

/**
 *
 * @author IUT2
 */
public enum DispoSortie implements java.io.Serializable {
    NUIT("La nuit"),
    WEEK_END("Le week-end"),
    SOIR("Le soir");
    
    private final String label;
    
    private DispoSortie(String label) { 
        this.label = label; 
    }
    
    public String getLabel() { return this.label; }
    public String toString() { return this.label; }
}