package fr.utt.lo02.projet.uno.noyau.carte;

import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/** 
 *  La classe Carte d�finit les caract�ristique d'une carte au sens large.
 */
public abstract class Carte {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/


	/**
	 * L'attribut couleur est la couleur de la crte.
	 * @see ECouleur
	 * @see Carte#getCouleur()
	 * @see Carte#setCouleur(ECouleur)
	 */
	protected ECouleur couleur;

	/**
	 * Cette m�thode dit si une carte peut �tre pos�e ou non.
	 * @return
	 * 		Renvoie un boolean valant true si la carte peut �tre pos�e et false sinon
	 */
	public abstract boolean estPosable();
	
	/**
	 * Cette m�thode renvoie la valeur de la carte.
	 * @return
	 * 		Renvoie un entier �tant la valeur de la carte
	 */
	public abstract int getValeur();
	
	/**
	 * Cette m�thode renvoie la sp�cialit� de la carte.
	 * @return
	 * 		Renvoie un ESpical �tant la sp�cialit� de la carte
	 * 
	 * @see ESpecial
	 */
	public abstract ESpecial getSpecial();
	
	/**
	 * Cette m�thode permet d'appliquer la r�gle de la carte � la partie en cours.
	 * @param partie
	 * 		La partie de Uno en cours
	 */
	public abstract void appliquerRegle(Partie partie);
	
	/**
	 * Cette m�thode renvoie la couleur de la carte.
	 * @return
	 * 		Renvoie un ECouleur �tant la couleur de la carte
	 * 
	 * @see ECouleur
	 */
	public ECouleur getCouleur() {
		return couleur;
	}
	
	/**
	 * Cette m�thode permet de changer la couleur de la carte. Elle est utilis�e pour les cartes
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