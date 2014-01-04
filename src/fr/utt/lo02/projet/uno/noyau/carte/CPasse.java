package fr.utt.lo02.projet.uno.noyau.carte;

import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;


/**
 * La classe CPasse repr�sente une carte passe dans un jeu de Uno
 * @author Victor & Ga�l
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
	 * Impl�mentation de la m�thode apliquerRegle de la classe Carte
	 * @see Carte#appliquerRegle(Partie)
	 */
	public void appliquerRegle(Partie partie) {
		partie.nextJoueur();
	}
	
	/**
	 * Impl�mentation de la m�thode estPosable de la classe Carte
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
