package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 * Classe Rapport d'activité héritant de JPanel
 * @see JPanel
 * Donne au joueur les informations sur less autres: nombre de cartes restantes, scores de chacuns
 * @author Gael et Victor
 *
 */
public class RapportDActivite extends JPanel{


	
	private static final long serialVersionUID = -947703756535873129L;
	/**
	 * @see JTextArea
	 * COntient les informations sur le nombre de carte de chacuns
	 */
	private JTextArea nbreCarte;
	/**
	 * Entier correspondant au nombre de joueur de la partie
	 */
	private int nbreJoueur;
	/**
	 * Tableau contenant dans chaue case le nom et le nombre de carte du joueur
	 */
	private String[] joueurCarte;
	
	/**
	 * JTetArea contenant les scores de chacuns
	 */
	private JTextArea score;
	/**
	 * Tableau de String contenant dans chaque case le nom et le score de chaues joueurs
	 */
	private String[] scoreJoueurs;
	
	
	/**
	 * Constructeur de la partie
	 * initialise les attributs en debuts de partie (score a 0, cartes a 5
	 * @param partie
	 */
	public RapportDActivite(Partie partie)
	{
		this.setOpaque(false);
		this.nbreJoueur = partie.getNbreJoueur();
		joueurCarte = new String[nbreJoueur];
		scoreJoueurs = new String[nbreJoueur];
		nbreCarte = new JTextArea();
		personnaliser(nbreCarte);
		score = new JTextArea();
		personnaliser(score);

		nbreCarte.setLineWrap(false); //Interdit les retours a la ligne automatique
		score.setLineWrap(false);
		for(int i= 0; i< partie.getNbreJoueur(); i++)
		{
			joueurCarte[i] = partie.getJoueur(i).afficherPseudo()+ ": 5 restantes" ;
			scoreJoueurs[i] = partie.getJoueur(i).afficherPseudo() + ": "+ partie.getJoueur(i).getScore() + " points";
		}

		this.refresh();
	}
	
	
	/**
	 * Meme personnalisation pour les deux JTextArea:
	 * transparent, avec bordure, police et ouleur d'ecriture
	 * @param c
	 * 		Le JTextArea a personnaliser
	 */
	private void personnaliser(JTextArea c) {

		c.setOpaque(false);
		c.setForeground(Color.white);
		c.setFont(new Font("Comic", Font.BOLD, 18));
		c.setEditable(false);
		c.setBorder(BorderFactory.createLineBorder(Color.white));

	}
	
	/**
	 * @see JPanel#paintComponents(Graphics)
	 * Créé l'image de fond de la classe
	 */
	public void paintComponent(Graphics g) {
		
		Image img = null;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/theme/FondScore.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Graphics2D g2d = (Graphics2D)g;
	    GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
	    g2d.setPaint(gp);
	    g2d.drawImage(img, 0, 0, this.getWidth(),  this.getHeight(), this);
	    
	    }
	
	
	/**
	 * Rafraichit les informations affichées
	 * 
	 */
	public void refresh()
	{
		this.removeAll();
		//Refresh de nbreCarte
		this.setLayout(new GridLayout(2,1));
		nbreCarte = new JTextArea("Nombre de cartes:");
		personnaliser(nbreCarte);
		score =  new JTextArea("Score:");
		personnaliser(score);


		for(int i=0; i< this.nbreJoueur; i++)
		{
			nbreCarte.append("\n");
			nbreCarte.append(joueurCarte[i]);
			score.append("\n");
			score.append(scoreJoueurs[i]);
		}
		this.add(nbreCarte);
		this.add(score);		
	}


	/** 
	 * Raffraichit les informations du joueur en parametre
	 * @param joueur
	 */
	public void refreshJoueur(Joueur joueur)
	{
		for(int i=0; i<this.nbreJoueur; i++)
		{

			if(joueurCarte[i].contains(joueur.afficherPseudo()))
			{
				if( joueur.getUno() )
				{
					joueurCarte[i] = joueur.afficherPseudo()+ " : " +joueur.getNombreCarte()+" restantes (uno)" ;
				}
				else
					joueurCarte[i] = joueur.afficherPseudo()+ " : " +joueur.getNombreCarte()+" restantes" ;


			}
		}
		this.refresh();
	}

	/**
	 * Rafraichi les scores de tout les joueurs en fin de manche
	 * @param partie
	 */

	public void refreshscore(Partie partie)
	{
		for(int i=0; i<partie.getNbreJoueur(); i++)
		{
			scoreJoueurs[i] = partie.getJoueur(i).afficherPseudo() + " : " + partie.getJoueur(i).getScore()+" points" ;
		}
		this.refresh();
	}
}
