package fr.utt.lo02.projet.uno.noyau.carte;

/** 
 *  Carte est abstraite. Elle poss�de les atrributs et m�thodes communes � tout type de carte.
 */
public abstract class Carte {
  /* {author=Victor Le Deuff Ga�l Mahfoudi}*/


  protected ECouleur couleur;

  public boolean estPosable() {
  return false;
  }

  public abstract ESpecial getSpecial();

  public ECouleur getCouleur() {
  return couleur;
  }

}