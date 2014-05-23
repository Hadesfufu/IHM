/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.GroupeContacts;

/**
 *
 * @author IUT2
 */
public class FicheGroupeUI extends javax.swing.JPanel implements ActionListener{   
    /**
     * Creates new form CarnetUI
     */
    private ZoneDessinUI     zoneDessin;
    private JTextField       nomGroupe;
    private JTable           Contacts;
    private JPanel           Buttons;
    
    public FicheGroupeUI() {
       initUIComponents();
    }

    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */    
    private void initUIComponents() {
        this.add(new javax.swing.JLabel("Fiche groupe"));
        Buttons = new JPanel();
        Buttons.add(new JButton("Cancel"));
        Buttons.add(new JButton("Accept"));
        this.add(Buttons);
        JButton buff = (JButton) Buttons.getComponent(1);
        buff.addActionListener(this);
        nomGroupe = new JTextField();
        this.add(nomGroupe);
        Contacts = new JTable();
        this.add(Contacts.getTableHeader());
        this.add(Contacts);
        zoneDessin = new ZoneDessinUI();
        this.add(zoneDessin);
    }

    /**
     * Affecte des valeurs au formulaire de fiche groupe de contacts
     * @param groupe groupe de contacts
     * @return
     */    
    public boolean setValues(GroupeContacts groupe, Contact [] contacts) {
        boolean success = false;
        DefaultTableModel d = new DefaultTableModel(new String[0][0], new String[]{"nom", "prenom"});
        if (groupe != null) {
            for(Contact i : contacts){
            d.addRow(new String[]{i.getNom(), i.getPrenom()});
            }
            Contacts.setModel(d);
            success = true;
        }
        nomGroupe.setText(groupe.getNom());
        zoneDessin.setPoints(groupe.getPoints());
        return success;
    }
    
    public boolean getValues(GroupeContacts groupe) {
        if (groupe == null) { return false; }
        
        groupe.setNom(nomGroupe.getText());
        groupe.setPoints(zoneDessin.getPoints());
        
        
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        zoneDessin.effacer();
    }
    
    public void setAnnulerListener(ActionListener a){
        JButton buff = (JButton) Buttons.getComponent(0);
        buff.addActionListener(a);
    }
    
    public void setModifierListener(ActionListener b){
         JButton buff = (JButton) Buttons.getComponent(1);
        buff.addActionListener(b);
    }

}