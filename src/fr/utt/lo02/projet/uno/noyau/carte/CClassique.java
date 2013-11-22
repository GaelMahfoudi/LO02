package lo02.projet.uno.noyau.carte;

import lo02.projet.uno.noyau.gestion.carte.Talon;


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
		return this.valeur;
	}

	public ESpecial getSpecial() {
		return null;
	}

	
	public boolean estPosable()
	{
		if (this.couleur == Talon.getInstance().getDerniereCarte().getCouleur() || this.valeur == Talon.getInstance().getDerniereCarte().getValeur()  )
			return true;
		else
			return false;
		
	}
		
		
	@Override
	public String toString() {
		return "CClassique [valeur=" + valeur + ", couleur=" + couleur + "]";
	}
}
