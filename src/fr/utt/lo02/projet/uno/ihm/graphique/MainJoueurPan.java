package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.utt.lo02.projet.uno.noyau.carte.Carte;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;


/**
 * CLasse MainJoueur heritant de JPanel
 * Composée d'ImageCarte, cette classe regroupe et affiche les cartes dans la main du joueur courant
 * @author Victor, Gael
 * @see ImageCarte
 *
 */

public class MainJoueurPan extends JPanel implements ActionListener{

	
	private static final long serialVersionUID = 8769487842894870766L;
	/**
	 * Il s'agit de la position (entier naturel) dans la main de la carte que souhaite poser le joueur
	 */
	private int choix;
	/**
	 * Le joueur dont la main est affichée a l'ecran
	 */
	private Joueur joueur;
	/**
	 * La main du joueur (Liste de ImageCarte)
	 * @see ImageCarte
	 */
	private ArrayList<ImageCarte> main; 
	/**
	 * JPanel, interieur du JScrollPane
	 * @see MainJoueurPan#scroll
	 */
	private JPanel mainPane;
	/**
	 * S'il y a trop de ImageCarte pour l'ecran, permet d'etendre le panel
	 */
	private JScrollPane scroll;
	

	/**
	 * Constructeur de la classe
	 * met en place le panneau, en y incluant 5 cartes factices face cachées
	 */
	public MainJoueurPan()
	{
		super();

		this.setBorder(BorderFactory.createLineBorder(Color.white));
		this.setOpaque(false);
		this.setLayout(new GridLayout(1,1));
		mainPane = new JPanel();
		mainPane.setOpaque(false);
		scroll = new JScrollPane(mainPane); 
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Pas de barre verticale
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); //Barre horizontale
		scroll.setOpaque(false);
		scroll.setBorder(null);
		scroll.getViewport().setOpaque(false);
		
		   
		joueur = null;
		choix = -1;

		for(int i=0; i<5; i++)
		{
			mainPane.add(new ImageCarte());

		}
		this.add(scroll);
		this.setOpaque(false);
	}

	
	/**
	 * Change les cartes affichés par celle de joueur, le nouveau joueur courant
	 * @param joueur
	 */
	public void refresh(Joueur joueur) {
		
		 mainPane.removeAll();
		 mainPane.setOpaque(false);
		 main = new ArrayList<ImageCarte>();
		 choix = -1;
		 this.joueur = joueur;
		 
		 
		 for (int i=0; i<joueur.getNombreCarte(); i++)
		 {
             ImageCarte c = new ImageCarte(joueur.getMain().getMain().get(i)); 
             main.add(c);
             mainPane.add(c);
		 }
	}

	/**
	 * Cache le jeu du joueur precedent en le remplacant par 5 cartes factices (face cachée)
	 */
	public void vider() {
		mainPane.removeAll();
		for(int i=0; i<5; i++)
		{
			mainPane.add(new ImageCarte());
		}
	}
	
	/**
	 * Lit les listenners des cartes jusqu'a ce que l'entier choix prenne une valeur plausible et la renvoie
	 * @return choix
	 */
	public int getChoix()
	{
		for(int i=0; i<main.size(); i++)
		{
			ImageCarte c = (ImageCarte) main.get(i);
			c.addActionListener(this);
		}
		
		return choix;
	}



	/**
	 * Recupere la position de la carte choisie et le stocke dans choix
	 * @see ActionListener#actionPerformed(ActionEvent)
	 * @see MainJoueurPan#choix
	 */
	public void actionPerformed(ActionEvent e) {

		ImageCarte carteG = (ImageCarte)e.getSource();
		Carte carte = carteG.getCarte();
		for (int i=0; i<joueur.getNombreCarte(); i++)
		{
			if( carte.equals(joueur.getMain().getMain().get(i)) )
			{

				choix = i;
			}
		}
	}
}
