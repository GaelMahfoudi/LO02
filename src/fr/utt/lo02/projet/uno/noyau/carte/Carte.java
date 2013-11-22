package fr.utt.lo02.projet.uno.noyau.carte;

/** 
 *  Carte est abstraite. Elle possède les atrributs et méthodes communes à tout type de carte.
 */
public abstract class Carte {
  /* {author=Victor Le Deuff Gaël Mahfoudi}*/


  protected ECouleur couleur;

  public boolean estPosable() {
  return false;
  }

  public abstract ESpecial getSpecial();

  public ECouleur getCouleur() {
  return couleur;
  }

}