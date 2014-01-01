package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public class RapportDActivite extends JPanel{

	
	private JTextArea nbreCarte;
	private int nbreJoueur;
	private String[] joueurCarte;
	private JTextArea score;
	private String[] scoreJoueurs;
	public RapportDActivite(Partie partie)
	{

		this.nbreJoueur = partie.getNbreJoueur();
		joueurCarte = new String[nbreJoueur];
		scoreJoueurs = new String[nbreJoueur];
		 nbreCarte = new JTextArea();
		 score = new JTextArea();
			
		nbreCarte.setLineWrap(false); //Interdit les retours a la ligne automatique
		score.setLineWrap(false);
		for(int i= 0; i< partie.getNbreJoueur(); i++)
        {
        	joueurCarte[i] = partie.getJoueur(i).afficherPseudo()+ ": 5 restantes" ;
        	scoreJoueurs[i] = partie.getJoueur(i).getScore() + " points";
        }
		this.refresh();
	}
	public void refresh()
	{
		this.removeAll();
		//Refresh de nbreCarte
		this.setLayout(new GridLayout(3,1));
        nbreCarte = new JTextArea("Nombre de cartes restantes:");
        score =  new JTextArea("Score:");
        
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
