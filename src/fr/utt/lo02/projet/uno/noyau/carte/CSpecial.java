package fr.utt.lo02.projet.uno.noyau.carte;

/** 
 *  CSpecial hérite de Carte. CSpecial repésente l'ensemble des cartes spéciales.
 */
public class CSpecial extends Carte {
	/* {author=Victor Le Deuff Gaël Mahfoudi}*/


	private ESpecial special;

	public CSpecial(ESpecial special, ECouleur couleur){
		this.couleur=couleur;
		this.special=special;
	}

	public ESpecial getSpecial() {
		return null;
	}

	@Override
	public String toString() {
		return "CSpecial [special=" + special + ", couleur=" + couleur + "]";
	}

}