package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;

/**
 * Classe TableauDeBord heritant de JPanel
 * Affiche a l'utilisteur le numero de la mance actuelle et le nom du joueur actuel
 * @author Victor et Gael
 *
 */
public class TableauDeBord extends JPanel{

	
	private static final long serialVersionUID = -937445359070340197L;
	/**
	 * contient les informations de la classe (manche et nom du joueur actuel)
	 */
	private JTextField info;
	/**
	 * entier correspondant au numer de la manche
	 */
	private int manche;
	/**
	 * String correspondant au nom du joueur actuel
	 */
	private String joueur;
	
	
	/**
	 * Constructeur de la partie, initialise les attributss de la classe
	 */
	public TableauDeBord()
	{
		this.manche = 1;
		this.joueur = " ";
		this.refresh();
		this.add(info);
		this.personnaliser();
	}
	
	/**
	 * Personnalise info
	 * @see TableauDeBord#info
	 */
	private void personnaliser() {
		// TODO gerer la police, la taille &co
		
		info.setOpaque(false);
		this.setOpaque(false);
		info.setEditable(false);
		info.setHorizontalAlignment(JTextField.CENTER);
		info.setFont(new Font("Arial", Font.BOLD, 30));
		info.setForeground(Color.white);
		info.setBorder(BorderFactory.createLineBorder(Color.white));

		
		
	}
	
	/**
	 * setter de manche
	 * @see TableauDeBord#manche
	 * @param manche
	 */
	public void setManche(int manche) 
	{
		this.manche = manche;
		
		
	}
	
	/**
	 * Setter de joueur
	 * @see TableauDeBord#joueur
	 * @param joueur
	 */
	public void setJoueur(Joueur joueur) 
	{
		this.joueur = joueur.afficherPseudo();
	}

	/**
	 * rafraichit le panneau en fonction des nouvelles valeurs des attributs
	 */
	public void refresh()
	{
		info = new JTextField("Manche "+ this.manche+": Au tour de " + joueur);
		this.personnaliser();
		this.removeAll();
		this.setLayout(new GridLayout(1,1));
		this.add(info);
		
		this.repaint();
	}
}
