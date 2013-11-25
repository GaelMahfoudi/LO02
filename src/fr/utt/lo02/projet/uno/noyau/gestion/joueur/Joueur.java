package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import fr.utt.lo02.projet.uno.noyau.carte.*;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.*;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 *  La classe Joueur est abstraite. Elle poss�de les attributs et m�thodes communes aux joueurs et aux IA.
 */
public abstract class Joueur {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/


	protected int score;

	protected String pseudo;

	protected MainJoueur main;

	protected boolean uno;
	
	
	public Joueur() {
		main = new MainJoueur();
		score = 0;
		uno = false;
	}
	
	public void calculerScore(Partie partie) {
		
		for(int i=0; i<partie.getNbreJoueur(); i++)
		{
			if(this != partie.getJoueur(i))
				this.score += partie.getJoueur(i).main.scoreMain();
		}
	}
	
	public int getScore()
	{
		return this.score;
	}

	public int getNombreCarte() {
		return main.getNombreCarte();
	}

	public abstract void jouer(Partie partie);

	public void piocherCarte(Pioche pioche) {
		main.ajouterCarte(pioche.enleverCarte());
	}

	public abstract boolean direBluff(Joueur joueur);
	
	public void reinitialiserMain()
	{
		this.main.reinitialiserMain();
		this.uno=false;
		
	}

	public ECouleur choisirCouleur() {
		return null;
	}
	
	public void afficherMain(){
		main.afficherMain();
	}
	
	public String afficherPseudo(){
		return this.pseudo;
	}
	
	public abstract void direUno();

	public abstract boolean direContreUno(Joueur j);
}
