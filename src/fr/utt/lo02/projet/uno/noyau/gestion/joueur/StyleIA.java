package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/** 
 *  L'interface StyleIA définit le comportement d'une IA, elle possède deux méthodes.
 *  jouerCarte définit la manière dont l'IA jouera et direBluff définira la manière
 *  dont l'IA dira bluff
 */
public interface StyleIA {
  /* {author=Victor Le Deuff Gaï¿½l Mahfoudi}*/


  /**
   * Cette méthode devra définir la façon dont l'IA joue. Elle prend en paramètre la partie
   * ainsi qu'un joueur qui n'est autre que l'IA elle même.
   * @param partie
   * 		La partie de Uno
   * @param joueur
   * 		L'IA qui doit jouer
   * @return
   * 		Renvoie un entier étant le choix de l'IA
   */
  public int jouerCarte(Partie partie, Joueur joueur);
  
  /**
   * Cette méthode devra définir la façon dont l'IA déclare un bluff. Elle prend
   * en paramètre un joueur qui n'est autre que l'IA elle même.
   * @param j
   * 		L'IA qui doit déclarer un bluff
   * @return
   * 		Renvoie un entier étant le choix de l'IA
   */
  public boolean direBluff(Joueur j);

}