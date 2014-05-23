/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import m2105_ihm.nf.*;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
/**
 *
 * @author IUT2
 */
public class FicheContactUI extends JPanel {

    private JPanel           Identite, Preferences, Buttons;
    private JTextField       champPrenom;
    private JTextField       champNom;
    private JComboBox        DateNaissance1;
    private JComboBox        DateNaissance2;
    private JComboBox        DateNaissance3;
    private JPanel           DispoHobPanel;
    private ButtonGroup      DispoGroup;
    private JComboBox        Origine;
    private JCheckBox        Hobbi;
    private JList            Musiques;
    private JPanel           MusicPanel;
    private JTextField       HobbyAutre;
    private JTextField       Email;
    private JTextField       Telephone;
    
    public FicheContactUI() {
        initUIComponents();
    }
    
    private void initUIComponents() {
        this.setLayout(new BorderLayout());
        Identite = new JPanel();
        Identite.setLayout(new GridLayout(0,2));
        Identite.setBorder(BorderFactory.createTitledBorder("Identité"));
        Preferences = new JPanel();
        Preferences.setLayout(new BorderLayout());
        Identite.setBorder(BorderFactory.createTitledBorder("Préférences"));
        Buttons = new JPanel();
        Buttons.setLayout(new BorderLayout());
        this.add(Identite, BorderLayout.NORTH);
        this.add(Preferences, BorderLayout.CENTER);
        this.add(Buttons, BorderLayout.SOUTH);
        
        DispoHobPanel = new JPanel();
        DispoHobPanel.setLayout(new GridLayout(0,5));
        DispoHobPanel.setBorder(BorderFactory.createTitledBorder("Disponibilités/Hobby"));
        MusicPanel = new JPanel();
        MusicPanel.setLayout(new BorderLayout());
        MusicPanel.setBorder(BorderFactory.createTitledBorder("Musiques"));
        Preferences.add(DispoHobPanel, BorderLayout.NORTH);
        Preferences.add(MusicPanel, BorderLayout.WEST);
        
        
        Identite.add(new JLabel("Nom :"));  
        champPrenom = new JTextField(30);
        Identite.add(champPrenom);
        
        Identite.add(new JLabel("Prenom :"));  
        champNom = new JTextField(30);
        Identite.add(champNom);
        
        Integer[] buffer = new Integer[31];
        for(int i = 0; i < buffer.length; i++){buffer[i] = i+1;}
        Identite.add(new JLabel("Jour:"));  
        DateNaissance1 = new JComboBox(buffer);
        Identite.add(DateNaissance1);
        
        buffer = new Integer[12];
        for(int i = 0; i < buffer.length; i++){buffer[i] = i+1;}
        Identite.add(new JLabel("Mois"));  
        DateNaissance2 = new JComboBox(buffer);
        Identite.add(DateNaissance2);
        
        buffer = new Integer[200];
        for(int i = 0; i < buffer.length; i++){buffer[i] = i+1914;}
        Identite.add(new JLabel("Année"));  
        DateNaissance3 = new JComboBox(buffer);
        Identite.add(DateNaissance3);
        
        Identite.add(new JLabel("Email :"));  
        Email = new JTextField(30);
        Identite.add(Email);
        
        Identite.add(new JLabel("Telephone :"));  
        Telephone = new JTextField(30);
        Identite.add(Telephone);
        
        String[] RegionS = new String[Region.values().length];
        int  n = 0;
        for(Region rm: Region.values()){RegionS[n] = rm.getLabel(); n++;}
        Identite.add(new JLabel("Region d'Origine:"));  
        Origine = new JComboBox(RegionS);
        Identite.add(Origine);
        
        for(int i = 0; i < 4; i++){
            Hobbi = new JCheckBox(Hobby.values()[i].getLabel());
             DispoHobPanel.add(Hobbi);
        }
        Hobbi = new JCheckBox("Autre");
        HobbyAutre = new JTextField();
        DispoHobPanel.add(Hobbi);
        DispoHobPanel.add(HobbyAutre);
        
        DispoGroup = new ButtonGroup();
        for(int i = 0; i < 3; i++){
        JRadioButton Dispo = new JRadioButton(DispoSortie.values()[i].getLabel());
        DispoGroup.add(Dispo);
        DispoHobPanel.add(Dispo);
        }
        
        
        
        String[] MusicS = new String[StyleMusical.values().length];
        n = 0;
        for(StyleMusical rm: StyleMusical.values()){MusicS[n] = rm.getLabel(); n++;}
        Musiques = new JList(MusicS);
        MusicPanel.add(Musiques, BorderLayout.CENTER);
        
        Buttons.add(new JButton("Cancel"), BorderLayout.WEST);
        Buttons.add(new JButton("Accept"), BorderLayout.EAST);
        
      
        
    }
    
    
    public boolean setValues(Contact contact) {
        boolean success = false;
        
        if (contact != null) {
            champNom.setText(contact.getNom());
            champPrenom.setText(contact.getPrenom());
           
            DateNaissance3.setSelectedItem(contact.getDateNaissance()[0]);
            DateNaissance2.setSelectedItem(contact.getDateNaissance()[1]);
            DateNaissance1.setSelectedItem(contact.getDateNaissance()[2]);
               
            Origine.setSelectedIndex(contact.getRegion().ordinal());
            for(int i = 0; i < DispoSortie.values().length; i++){
            BWAWAWAW
            }
            
            int Stylys[] = new int[contact.getGoutsMusicaux().length];
            for(int i = 0; i < Stylys.length ; i++){
                Stylys[i] = contact.getGoutsMusicaux()[i].ordinal();
            }
            this.Musiques.setSelectedIndices(Stylys);
            
            for(int i = 0; i < contact.getHobbies().length ; i++){
                JCheckBox buff = (JCheckBox)this.DispoHobPanel.getComponent(contact.getHobbies()[i].ordinal());
                buff.setSelected(true);
            }
             
          
            HobbyAutre.setText(contact.getHobbyAutre());
            Email.setText(contact.getEmail());
            Telephone.setText(contact.getNumeroTelephone());
            success = true;
        }
        
        return success;
    }
    
    /**
     * Retourne les valeurs associées au formulaire de fiche contact
     * @return
     */
    public boolean getValues(Contact contact) {        

        contact.setNom(champNom.getText());
        contact.setDateNaissance(DateNaissance3.getSelectedIndex()+1914, DateNaissance2.getSelectedIndex()+1, DateNaissance1.getSelectedIndex()+1);
        contact.setHobbyAutre(HobbyAutre.getText());
        contact.setPrenom(champPrenom.getText());
        contact.setNumeroTelephone(Telephone.getText());
        contact.setRegion(Region.values()[Origine.getSelectedIndex()]);
        contact.setEmail(Email.getText());      
        return true;
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
