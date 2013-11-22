package lo02.projet.uno.noyau.carte;

import lo02.projet.uno.noyau.gestion.carte.Talon;
import lo02.projet.uno.noyau.gestion.partie.Partie;


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

	public void appliquerRegle(Partie partie)
	{
		return;
	}
	
	public int getValeur() {
		return this.valeur;
	}

	public ESpecial getSpecial() {
		return null;
	}

	
	public boolean estPosable()
	{
		if (couleur == Talon.getInstance().getDerniereCarte().getCouleur() || valeur == Talon.getInstance().getDerniereCarte().getValeur()  )
			return true;
		else
			return false;
		
	}
		
	public void setCouleur(ECouleur couleur)
	{
		return; //On ne peut modifier la couleur d'une carte classique
	}
	@Override
	public String toString() {
		return "CClassique [valeur=" + valeur + ", couleur=" + couleur + "]";
	}
}
