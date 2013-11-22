package fr.utt.lo02.projet.uno.noyau.carte;

/** 
 *  CSpecial h�rite de Carte. CSpecial rep�sente l'ensemble des cartes sp�ciales.
 */
public class CSpecial extends Carte {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/


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