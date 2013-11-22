package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import fr.utt.lo02.projet.uno.noyau.carte.*;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.*;

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
		pseudo = "jean";
		uno = false;
	}
	
	public int calculerScore() {
		return main.scoreMain();
	}

	public int getNombreCarte() {
		return main.getNombreCarte();
	}

	public abstract ESpecial jouer();

	public void piocherCarte(Pioche pioche) {
		main.ajouterCarte(pioche.enleverCarte());
	}

	public abstract void direBluff();

	public ECouleur choisirCouleur() {
		return null;
	}
	
	public void afficherMain(){
		main.afficherMain();
	}
	
	public void afficherPseudo(){
		System.out.println(this.pseudo);
	}
	
	public abstract void direUno();

	public abstract boolean direContreUno(Joueur j);
}
