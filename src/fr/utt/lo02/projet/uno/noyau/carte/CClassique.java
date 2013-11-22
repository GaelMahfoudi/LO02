package fr.utt.lo02.projet.uno.noyau.carte;


/**
 *  CClassique h�rite de Carte. CClassique rep�sente l'ensemble des cartes classiques.
 */
public class CClassique extends Carte{
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/


	private int valeur;

	public CClassique(ECouleur couleur, int valeur){
		this.couleur=couleur;
		this.valeur=valeur;
	}

	public int getValeur() {
		return valeur;
	}

	public ESpecial getSpecial() {
		return null;
	}

	@Override
	public String toString() {
		return "CClassique [valeur=" + valeur + ", couleur=" + couleur + "]";
	}
}
