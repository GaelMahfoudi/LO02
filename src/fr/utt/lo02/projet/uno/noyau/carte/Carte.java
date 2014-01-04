package fr.utt.lo02.projet.uno.noyau.carte;

import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/** 
 *  La classe Carte définit les caractéristique d'une carte au sens large.
 */
public abstract class Carte {
	/* {author=Victor Le Deuff Gaï¿½l Mahfoudi}*/


	/**
	 * L'attribut couleur est la couleur de la crte.
	 * @see ECouleur
	 * @see Carte#getCouleur()
	 * @see Carte#setCouleur(ECouleur)
	 */
	protected ECouleur couleur;

	/**
	 * Cette méthode dit si une carte peut être posée ou non.
	 * @return
	 * 		Renvoie un boolean valant true si la carte peut être posée et false sinon
	 */
	public abstract boolean estPosable();
	
	/**
	 * Cette méthode renvoie la valeur de la carte.
	 * @return
	 * 		Renvoie un entier étant la valeur de la carte
	 */
	public abstract int getValeur();
	
	/**
	 * Cette méthode renvoie la spécialité de la carte.
	 * @return
	 * 		Renvoie un ESpical étant la spécialité de la carte
	 * 
	 * @see ESpecial
	 */
	public abstract ESpecial getSpecial();
	
	/**
	 * Cette méthode permet d'appliquer la règle de la carte à la partie en cours.
	 * @param partie
	 * 		La partie de Uno en cours
	 */
	public abstract void appliquerRegle(Partie partie);
	
	/**
	 * Cette méthode renvoie la couleur de la carte.
	 * @return
	 * 		Renvoie un ECouleur étant la couleur de la carte
	 * 
	 * @see ECouleur
	 */
	public ECouleur getCouleur() {
		return couleur;
	}
	
	/**
	 * Cette méthode permet de changer la couleur de la carte. Elle est utilisée pour les cartes
	 * Joker et Plus Quatre
	 * @param couleur
	 * 		La nouvelle couleur de la carte
	 * 
	 * @see ECouleur
	 */
	public void setCouleur(ECouleur couleur)
	{
		this.couleur=couleur;
	}


}