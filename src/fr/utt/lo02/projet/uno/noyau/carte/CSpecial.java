package fr.utt.lo02.projet.uno.noyau.carte;


import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/** 
 *  La classe CSpecial représente les cartes spéciales d'un jeu de Uno.
 */
public abstract class CSpecial extends Carte {
	/* {author=Victor Le Deuff Gaï¿½l Mahfoudi}*/


	/**
	 * L'attribut special est la spécialité de la carte.
	 * @see ESpecial
	 */
	protected ESpecial special;

	/**
	 * Constructeur de la classe CSpecial.
	 * @param special
	 * 		La spécialitée de la carte
	 * @param couleur
	 * 		la couleur de la carte
	 * 
	 * @see ESpecial
	 */
	public CSpecial(ESpecial special, ECouleur couleur)
	{
		this.couleur=couleur;
		this.special=special;
	}


	/**
	 * Implémentation de la méthode getSpecial de la classe Carte
	 * @see Carte#getSpecial()
	 */
	public ESpecial getSpecial() {
		return this.special;
	}

	/**
	 * @see Carte#appliquerRegle(Partie)
	 */
	public abstract void appliquerRegle(Partie partie);

	/**
	 * @see Carte#estPosable()
	 */
	public abstract boolean estPosable();

	/**
	 * Implémentation de la méthode getValeur de la classe Carte
	 * @see Carte#getValeur()
	 */
	public int getValeur()
	{
		return -1; //Chiffre impossible pour les comparaisons
	}

}