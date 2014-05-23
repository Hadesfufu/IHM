/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.nf;

/**
 *
 * @author IUT2
 */
public enum StyleMusical {
    BLACK_METAL("Black m\u00E9tal"),
    BLUES("Blues"),
    CLASSIQUE("Classique"),
    COUNTRY("Country"),
    DANCE("Dance"),
    ELECTRO("Electronique"),
    HARD_ROCK("Hard Rock"),
    JAZZ("Jazz"),
    MONDE("Musique du monde"),
    POP("Pop"),
    RAP("Rap"),
    RNB("RnB"),
    ROCK("Rock"),
    SOUL("Soul"),
    TRADI("Traditionnelle"),    
    VARIETE_FR("Vari\u00E9t\u00E9 Fran\u00E7aise");
    
    private final String label;
    
    private StyleMusical(String label) { 
        this.label = label; 
    }
    
    public String getLabel() { return this.label; }    
    public String toString() { return this.label; }    
}
