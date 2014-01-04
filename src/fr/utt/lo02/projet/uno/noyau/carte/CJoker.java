package fr.utt.lo02.projet.uno.noyau.carte;


import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 * La classe CJoker repr�sente une carte Joker dans un jeu de Uno
 * @author Victor & Ga�l
 */
public class CJoker extends CSpecial{
	
	/**
	 * Constructeur de la classe CJoker.
	 */
	public CJoker()
	{
		super(ESpecial.JOKER, null);
		
	}
	
	/**
	 * Impl�mentation de la m�thode apliquerRegle de la classe Carte
	 * @see Carte#appliquerRegle(Partie)
	 */
	public void appliquerRegle(Partie partie)
	{
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
		return "CJoker [special=" + special + ", couleur=" + couleur + "]";
	}
}
