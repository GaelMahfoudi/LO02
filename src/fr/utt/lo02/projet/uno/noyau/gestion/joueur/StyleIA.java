package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/** 
 *  L'interface StyleIA d�finit le comportement d'une IA, elle poss�de deux m�thodes.
 *  jouerCarte d�finit la mani�re dont l'IA jouera et direBluff d�finira la mani�re
 *  dont l'IA dira bluff
 */
public interface StyleIA {
  /* {author=Victor Le Deuff Ga�l Mahfoudi}*/


  /**
   * Cette m�thode devra d�finir la fa�on dont l'IA joue. Elle prend en param�tre la partie
   * ainsi qu'un joueur qui n'est autre que l'IA elle m�me.
   * @param partie
   * 		La partie de Uno
   * @param joueur
   * 		L'IA qui doit jouer
   * @return
   * 		Renvoie un entier �tant le choix de l'IA
   */
  public int jouerCarte(Partie partie, Joueur joueur);
  
  /**
   * Cette m�thode devra d�finir la fa�on dont l'IA d�clare un bluff. Elle prend
   * en param�tre un joueur qui n'est autre que l'IA elle m�me.
   * @param j
   * 		L'IA qui doit d�clarer un bluff
   * @return
   * 		Renvoie un entier �tant le choix de l'IA
   */
  public boolean direBluff(Joueur j);

}