package fr.utt.lo02.projet.uno.noyau.carte;


import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;


/**
 * La classe CPlusQuatre repr�sente une carte plus quatre dans un jeu de Uno
 * @author Victor & Ga�l
 */
public class CPlusQuatre extends CSpecial {
	
	/**
	 * Constructeur de la classe CPlusQuatre
	 */
	public CPlusQuatre()
	{
		super(ESpecial.PLUS_QUATRE, null);
	}
	
	/**
	 * Impl�mentation de la m�thode apliquerRegle de la classe Carte
	 * @see Carte#appliquerRegle(Partie)
	 */
	public void appliquerRegle(Partie partie)
	{
		Joueur joueurNext = partie.getJoueur(Math.abs((partie.getJoueurActuel()+partie.getSens())%partie.getNbreJoueur()));
		
		joueurNext.direBluff(partie.getJoueur(partie.getJoueurActuel()), partie);
		
		partie.getJoueur(partie.getJoueurActuel()).choisirCouleur();
		
	}
	
	/**
	 * Impl�mentation de la m�thode estPosable de la classe Carte
	 * @see Carte#estPosable()
	 */
	public boolean estPosable() 
	{
		return true;
	}

	/**
	 * @see Object#toString()
	 */
	public String toString() {
		return "CPlusQuatre [special=" + special + ", couleur=" + couleur + "]";
	}
}
