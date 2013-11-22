package lo02.projet.uno.noyau.carte;

import lo02.projet.uno.noyau.gestion.carte.Talon;

/** 
 *  Carte est abstraite. Elle poss�de les atrributs et m�thodes communes � tout type de carte.
 */
public abstract class Carte {
  /* {author=Victor Le Deuff Ga�l Mahfoudi}*/


  protected ECouleur couleur;

  public abstract boolean estPosable();
  public abstract int getValeur();
  public abstract ESpecial getSpecial();

  public ECouleur getCouleur() {
  return couleur;
  }

}