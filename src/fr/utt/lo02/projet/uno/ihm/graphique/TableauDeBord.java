package fr.utt.lo02.projet.uno.ihm.graphique;

import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public class TableauDeBord extends JPanel{

	private JTextField info;
	private int manche;
	private String joueur;
	
	public TableauDeBord()
	{
		this.manche = 1;
		this.joueur = " ";
		this.refresh();
		this.add(info);
	}
	private void personnaliser() {
		// TODO gerer la police, la taille &co
		
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
		this.removeAll();
		this.add(info);
		
		this.repaint();
	}
}
