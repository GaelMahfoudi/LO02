package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/** 
 *  Interface StyleIA, elle poss�de une m�thode jouerCarte qui d�finira le comportement de l'IA
 */
public interface StyleIA {
  /* {author=Victor Le Deuff Ga�l Mahfoudi}*/


  
  public int jouerCarte(Partie partie, Joueur joueur);

}