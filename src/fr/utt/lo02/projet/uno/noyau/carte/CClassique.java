package fr.utt.lo02.projet.uno.noyau.carte;

import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;


/**
 *  La classe CClassique permet de repr�senter les cartes normales d'un jeu de Uno.
 */
public class CClassique extends Carte{
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/


	/**
	 * L'attribut valeur est la valeur de la carte
	 * @see CClassique#getValeur()
	 */
	private int valeur;

	/**
	 * Constructeur de la classe CClassique.
	 * @param couleur
	 * 		La couleur de la carte
	 * @param valeur
	 * 		La valeur de la carte
	 * 
	 * @see ECouleur
	 */
	public CClassique(ECouleur couleur, int valeur){
		this.couleur=couleur;
		this.valeur=valeur;
	}

	/**
	 * Impl�mentation de la m�thode aappliquerRegle de la classe Carte
	 * @see Carte#appliquerRegle(Partie)
	 */
	public void appliquerRegle(Partie partie)
	{
		return;
	}



	/**
	 * Impl�mentation de la m�thode getValeur de la classe Carte
	 * @see Carte#getValeur()
	 */
	public int getValeur() {
		return this.valeur;
	}

	/**
	 * Impl�mentation de la m�thode getSpecial de la classe Carte
	 * @see Carte#getSpecial()
	 */
	public ESpecial getSpecial() {
		return null;
	}

	/**
	 * Impl�mentation de la m�thode estPosable de la classe Carte
	 * @see Carte#estPosable()
	 */
	public boolean estPosable()
	{
		if (couleur == Talon.getInstance().getDerniereCarte().getCouleur() || valeur == Talon.getInstance().getDerniereCarte().getValeur()  )
			return true;
		else
			return false;
		
	}
		
	/**
	 * Impl�mentation de la m�thode setCouleur de la classe Carte
	 * @see Carte#setCouleur(ECouleur)
	 * @see ECouleur
	 */
	public void setCouleur(ECouleur couleur)
	{
		return; //On ne peut modifier la couleur d'une carte classique
	}
	
	/**
	 * @see Object#toString()
	 */
	public String toString() {
		return "CClassique [ Valeur= "+valeur + ", Couleur= " + couleur+"]";
	}
}
