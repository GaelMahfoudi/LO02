package fr.utt.lo02.projet.uno.noyau.carte;

import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/** 
 *  Carte est abstraite. Elle poss�de les atrributs et m�thodes communes � tout type de carte.
 */
public abstract class Carte {
  /* {author=Victor Le Deuff Ga�l Mahfoudi}*/


  protected ECouleur couleur;

  public abstract boolean estPosable();
  public abstract int getValeur();
  public abstract ESpecial getSpecial();
  public abstract void appliquerRegle(Partie partie);
  public ECouleur getCouleur() {
  return couleur;
  }
  public void setCouleur(ECouleur couleur)
	{
		this.couleur=couleur;
	}
	

}