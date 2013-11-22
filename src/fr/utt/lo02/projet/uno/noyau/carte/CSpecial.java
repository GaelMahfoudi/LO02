package lo02.projet.uno.noyau.carte;

import lo02.projet.uno.noyau.gestion.carte.Talon;

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
	
	public boolean estPosable() 
	  {
		  if(this.couleur == Talon.getInstance().getDerniereCarte().getCouleur())
			  return true;
		  else
			  return false;
	  }
	
	public int getValeur()
	{
		return -1; //Chiffre impossible pour les comparaisons
	}
	
}