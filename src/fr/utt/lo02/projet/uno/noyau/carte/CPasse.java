package fr.utt.lo02.projet.uno.noyau.carte;

import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;


/**
 * La classe CPasse représente une carte passe dans un jeu de Uno
 * @author Victor & Gaël
 */
public class CPasse extends CSpecial {
	
	/**
	 * Constructeur de la classe CPasse
	 * @param couleur
	 * 		La couleur de la carte
	 */
	public CPasse(ECouleur couleur)
	{
		super(ESpecial.PASSE, couleur);
	}
	
	/**
	 * Implémentation de la méthode apliquerRegle de la classe Carte
	 * @see Carte#appliquerRegle(Partie)
	 */
	public void appliquerRegle(Partie partie) {
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
		return "CPasse [special=" + special + ", couleur=" + couleur + "]";
	}
	
}
