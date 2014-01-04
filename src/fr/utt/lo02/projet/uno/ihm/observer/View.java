package fr.utt.lo02.projet.uno.ihm.observer;

import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 * L'interface View d�finit les m�thode que doit avoir une vue de Uno.
 * @author Victor & Ga�l
 */
public interface View {
	
	/**
	 * Cette m�thode devra afficher la fin d'une manche
	 * @param p
	 * 		La partie de Uno
	 */
	public void afficherFinManche(Partie p);
	
	/**
	 * Cette m�thode devra afficher la fin d'une partie
	 * @param p
	 * 		La partie de Uno
	 */
	public void afficherFinPartie(Partie p);
	
	/**
	 * Cette m�thode devrat afficher qu'un joueur passe son tour
	 * @param joueur
	 * 		Le joueur
	 */
	public void afficherPasse(Joueur joueur);
	
	/**
	 * Cette m�thode devra afficher la carte que le joueur a affich�
	 * @param joueur
	 * 		Le joueur
	 * @param choix
	 * 		La carte choisie
	 */
	public void afficherTour(Joueur joueur, int choix);
	
	/**
	 * Cette m�thode devra afficher un message pour demander la carte au joueur
	 * @param joueur
	 * 		Le joueur
	 * @return
	 * 		Renvoie un entier �tant le choix du joueur
	 */
	public int demanderCarteAJouer(Joueur joueur);
	
	/**
	 * Cette m�thode devra afficher un message pour demander si le joueur veut poser la carte qu'il vient de piocher
	 * @param joueur
	 * 		Le joueur
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int poserCartePioche(Joueur joueur);
	
	/**
	 * Cette m�thode devra afficher la carte pioch�e par le joueur
	 * @param joueur
	 * 		 Le joueur
	 */
	public void afficherCartePioche(Joueur joueur);
	
	/**
	 * Cette m�thode devra afficher un message pour demander si le joueur d�clare un bluff
	 * @param partie
	 * 		La partie de Uno
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int demanderBluff(Partie partie);
	
	/**
	 * Cette m�thode devra afficher un message d'erreur lorsque le joueur fait un mauvais choix
	 */
	public void afficherMauvaisChoix();
	
	/**
	 * Cette m�thode devra afficher un message pour demander au joueur la couleur qu'il choisi
	 * @return
	 * 		Renvoie la couleur choisie par le joueur
	 */
	public ECouleur demanderCouleur();
	
	/**
	 * Cette m�thode devra afficher un message pour demander Uno au joueur
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int demanderUno();
	
	/**
	 * Cette m�thode devra demander un message pour demander Contre Uno au joueur
	 * @param partie
	 * 		La partie de Uno
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int demanderContreUno(Partie partie);
	
	/**
	 * Cette m�thode devra demander le pseudo au joueur
	 * @return
	 * 		Renvoie le pseudo du joueur
	 */
	public String demanderPseudo();
	
	/**
	 * Cette m�thode devra demander un choix au joueur
	 * @param min
	 * 		Le minimum
	 * @param max
	 * 		Le maximum
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int demanderChoix(int min, int max);
	
	/**
	 * Cette m�thode devra afficher un menu pour d�marrer une partie
	 * @param partie
	 * 		La partie � d�marrer 
	 * @param controller
	 * 		Le controleur de la partie
	 * @return
	 * 		Renvoie la partie d�marr�e
	 * 
	 * @see UnoController
	 */
	public Partie demarrerPartie(Partie partie, UnoController controller);
	
	/**
	 * Cette m�thode devra permettre de d�clarer Uno
	 */
	public void declarerUno();
	
	/**
	 * Cette m�thode devra permetre de changer le controleur de la vue
	 * @param obs
	 * 		Le controleur
	 * 
	 * @see UnoController
	 */
	public void setController(Observateur obs);
	
	/**
	 * Cette m�thode devra demander qui demande Uno
	 * @param partie
	 * 		La partie de Uno
	 * @return
	 * 		Renvoie le joueur 
	 */
	public Joueur quiDemandeUno(Partie partie);
	
	/**
	 * Cette m�thode devra demander qui demande Contre Uno
	 * @param partie
	 * 		La partie de Uno
	 * @return
	 * 		Renvoie le joueur
	 */
	public Joueur quiDemandeContreUno(Partie partie);
	
	/**
	 * Cette m�thode devra mettre � jour la vue
	 */
	public void refresh();
}

