package fr.utt.lo02.projet.uno.ihm.observer;


/**
 * Interface Observateur
 * Il fait le lien entre la vue et le moteur
 * Voir Design Pattern MVC
 * @see View
 * @see Observer
 * @author Victor & Gael
 */
import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;
//Notons que l'observateur doit instancier lui meme partie dans son constructeur
public interface Observateur
{
	/**
	 * methode appelée a la fin de chaque manche
	 * @param partie
	 * 		La partie de Uno
	 */
	public void notifyManche(Partie partie);
	/**
	 * Methode appelée a la fin de la partie
	 * @param partie
	 * 		La partie de Uno
	 */
	
	public void notifyPartie(Partie partie);
	/**
	 * Methode appelée lorsqu'un jour passe ou est passé
	 * @param joueur
	 * 		Le joueur
	 */
	public void notifyPasse(Joueur joueur);
	/**
	 * Methode appelée a la fin de chaque tour
	 * @param joueur
	 * 		le joueur qui a joué
	 * @param choix
	 * 		le choix qu'il a fait
	 */
	public void notifyTour(Joueur joueur, int choix);
	/**
	 *	retourne le choix de l'utilisateur actuel (quelle carte veut il poser?)
	 * @param joueur
	 * 		Le joueur
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int askCarte(Joueur joueur);
	/**
	 * Demande a l'utilisateur s'il veut poser la carte piochée precedemment
	 * @param joueur
	 * 		Le joueur
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int askPoserCarte(Joueur joueur);
	/**
	 * Notifie l'utilisateur la carte qu'il vient de piocher
	 * @param joueur
	 * 		Le joueur
	 */
	public void notifyPioche(Joueur joueur);
	
	/**
	 * demande a l'utilisateur si le joueur precedent bluffait s'il vient de poser un +4
	 * @param partie
	 * 		La partie de Uno
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int askBluff(Partie partie);
	/**
	 * Notifie l'utilisateur que la carte selectionnée ne peut etre posée
	 */
	public void notifyError();
	/**
	 * Demande a l'utilisateur quelle couleur il veut imposer au jeu
	 * @return 
	 * 		Renvoie la couleur choisie par le joueur
	 */
	public ECouleur askCouleur();
	/**
	 * Demande a l'utilisateur s'il veut dire Uno [0,1]
	 * @return 
	 * 		Renvoie le choix du joueur
	 */
	public int askUno();
	/**
	 * Lorsque l'utilisateur veut dire Uno, View lance cette methode
	 */
	public void declarerUno();
	/**
	 * Demande a l'utilisateur a qui il veut dire contre UNO
	 * @param partie
	 * 		La partie de Uno
	 * @return 
	 * 		Renvoie le choix du joueur
	 */
	public int askContreUno(Partie partie);
	/**
	 * Demande a l'utilisateur son pseudo
	 * @return 
	 * 		Renvoie le nom du joueur
	 */
	public String askPseudo();
	/**
	 * demande a l'utilisateur un nombre entre min et max
	 * @param min
	 * 		Le minimum
	 * @param max
	 * 		Le maximum
	 * @return 
	 * 		Renvoie le choix du joueur
	 */
	public int askNombre(int min, int max);
	/**
	 * Lorsque l'utiliateur souhaite declarer un vontre Uno, view actionne cette methode
	 */
	public void declarerContreUno();

}