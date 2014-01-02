package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;

public class TableauDeBord extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -937445359070340197L;
	private JTextField info;
	private int manche;
	private String joueur;
	
	public TableauDeBord()
	{
		this.manche = 1;
		this.joueur = " ";
		this.refresh();
		this.add(info);
		this.personnaliser();
	}
	private void personnaliser() {
		// TODO gerer la police, la taille &co
		
		info.setOpaque(false);
		this.setOpaque(false);
		info.setEditable(false);
		info.setHorizontalAlignment(JTextField.CENTER);
		info.setFont(new Font("Arial", Font.BOLD, 30));
		info.setForeground(Color.white);
		info.setBorder(BorderFactory.createLineBorder(Color.black));

		
		
	}
	public void setManche(int manche) 
	{
		this.manche = manche;
		
	}
	public void setJoueur(Joueur joueur) 
	{
		this.joueur = joueur.afficherPseudo();
	}

	public void refresh()
	{
		info = new JTextField("Manche "+ manche+": \n Au tour de " + joueur);
		this.personnaliser();
		this.removeAll();
		this.setLayout(new GridLayout(1,1));
		this.add(info);
		
		this.repaint();
	}
}
