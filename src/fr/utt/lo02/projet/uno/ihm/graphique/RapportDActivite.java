package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public class RapportDActivite extends JPanel{


	/**
	 * 
	 */
	private static final long serialVersionUID = -947703756535873129L;
	private JTextArea nbreCarte;
	private int nbreJoueur;
	private String[] joueurCarte;
	private JTextArea score;
	private String[] scoreJoueurs;
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
	
	private void personnaliser(JTextArea c) {

		c.setOpaque(false);
		c.setForeground(Color.white);
		c.setFont(new Font("Comic", Font.BOLD, 18));
		c.setEditable(false);
		c.setBorder(BorderFactory.createLineBorder(Color.white));

	}
	
	public void paintComponent(Graphics g) {
		Image img = new ImageIcon(ImageCarte.pathImage+ "/theme/FondScore.png").getImage();
		Graphics2D g2d = (Graphics2D)g;
	    GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
	    g2d.setPaint(gp);
	    g2d.drawImage(img, 0, 0, this.getWidth(),  this.getHeight(), this);
	    
	    }
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

	/*public void refreshscore(Joueur joueur)
	{
		for(int i=0; i<this.nbreJoueur; i++)
		{

			if(scoreJoueurs[i].contains(joueur.afficherPseudo()))
			{
					scoreJoueurs[i] = joueur.afficherPseudo()+ " : " +joueur.getScore()+" points" ;

			}
		}
		this.refresh();
	}*/

	public void refreshscore(Partie partie)
	{
		for(int i=0; i<partie.getNbreJoueur(); i++)
		{
			scoreJoueurs[i] = partie.getJoueur(i).afficherPseudo() + " : " + partie.getJoueur(i).getScore()+" points" ;
		}
		this.refresh();
	}
}
