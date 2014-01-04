package fr.utt.lo02.projet.uno.noyau.carte;

import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;


/**
 * La classe CPlusDeux représente une carte plus deux dans un jeu de Uno
 * @author Victor & Gaël
 */
public class CPlusDeux extends CSpecial {
	
	/**
	 * Constructeur de la classe CPlusDeux
	 * @param couleur
	 * 		La couleur de la carte 
	 */
	public CPlusDeux(ECouleur couleur)
	{
		super(ESpecial.PLUS_DEUX, couleur);
	}
	
	/**
	 * Implémentation de la méthode apliquerRegle de la classe Carte
	 * @see Carte#appliquerRegle(Partie)
	 */
	public void appliquerRegle(Partie partie) {

		Joueur joueurNext = partie.getJoueur(Math.abs((partie.getJoueurActuel()+partie.getSens())%partie.getNbreJoueur()));
		joueurNext.piocherCarte(2);
		partie.nextJoueur();
		
	}
	
	/**
	 * Implémentation de la méthode estPosable de la classe Carte
	 * @see Carte#estPosable()
	 */
	public boolean estPosable() {
		
		if(this.couleur==Talon.getInstance().getDerniereCarte().getCouleur() || this.special == Talon.getInstance().getDerniereCarte().getSpecial())
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * @see Object#toString()
	 */
	public String toString() {
		return "CPlusDeux [special=" + special + ", couleur=" + couleur + "]";
	}
	
}
