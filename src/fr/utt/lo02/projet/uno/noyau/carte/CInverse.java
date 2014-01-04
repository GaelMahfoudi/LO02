package fr.utt.lo02.projet.uno.noyau.carte;

import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 * La classe CInverse repr�sente une carte inverse dans un jeu de Uno
 * @author Victor & Ga�l
 */
public class CInverse extends CSpecial{

	/**
	 * Constructeur de la classe CInvers
	 * @param couleur
	 * 		La couleur de la carte
	 */
	public CInverse(ECouleur couleur)
	{
		super(ESpecial.INVERSE, couleur);
	}

	/**
	 * Impl�mentation de la m�thode apliquerRegle de la classe Carte
	 * @see Carte#appliquerRegle(Partie)
	 */
	public void appliquerRegle(Partie partie) {
		partie.setSens();
		if(partie.getNbreJoueur() == 2)
		{
			partie.nextJoueur();
		}
		
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
		return "CInverse [special=" + special + ", couleur=" + couleur + "]";
	}
}
