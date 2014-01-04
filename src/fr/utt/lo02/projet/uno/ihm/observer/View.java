package fr.utt.lo02.projet.uno.ihm.observer;

import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 * L'interface View définit les méthode que doit avoir une vue de Uno.
 * @author Victor & Gaël
 */
public interface View {
	
	/**
	 * Cette méthode devra afficher la fin d'une manche
	 * @param p
	 * 		La partie de Uno
	 */
	public void afficherFinManche(Partie p);
	
	/**
	 * Cette méthode devra afficher la fin d'une partie
	 * @param p
	 * 		La partie de Uno
	 */
	public void afficherFinPartie(Partie p);
	
	/**
	 * Cette méthode devrat afficher qu'un joueur passe son tour
	 * @param joueur
	 * 		Le joueur
	 */
	public void afficherPasse(Joueur joueur);
	
	/**
	 * Cette méthode devra afficher la carte que le joueur a affiché
	 * @param joueur
	 * 		Le joueur
	 * @param choix
	 * 		La carte choisie
	 */
	public void afficherTour(Joueur joueur, int choix);
	
	/**
	 * Cette méthode devra afficher un message pour demander la carte au joueur
	 * @param joueur
	 * 		Le joueur
	 * @return
	 * 		Renvoie un entier étant le choix du joueur
	 */
	public int demanderCarteAJouer(Joueur joueur);
	
	/**
	 * Cette méthode devra afficher un message pour demander si le joueur veut poser la carte qu'il vient de piocher
	 * @param joueur
	 * 		Le joueur
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int poserCartePioche(Joueur joueur);
	
	/**
	 * Cette méthode devra afficher la carte piochée par le joueur
	 * @param joueur
	 * 		 Le joueur
	 */
	public void afficherCartePioche(Joueur joueur);
	
	/**
	 * Cette méthode devra afficher un message pour demander si le joueur déclare un bluff
	 * @param partie
	 * 		La partie de Uno
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int demanderBluff(Partie partie);
	
	/**
	 * Cette méthode devra afficher un message d'erreur lorsque le joueur fait un mauvais choix
	 */
	public void afficherMauvaisChoix();
	
	/**
	 * Cette méthode devra afficher un message pour demander au joueur la couleur qu'il choisi
	 * @return
	 * 		Renvoie la couleur choisie par le joueur
	 */
	public ECouleur demanderCouleur();
	
	/**
	 * Cette méthode devra afficher un message pour demander Uno au joueur
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int demanderUno();
	
	/**
	 * Cette méthode devra demander un message pour demander Contre Uno au joueur
	 * @param partie
	 * 		La partie de Uno
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int demanderContreUno(Partie partie);
	
	/**
	 * Cette méthode devra demander le pseudo au joueur
	 * @return
	 * 		Renvoie le pseudo du joueur
	 */
	public String demanderPseudo();
	
	/**
	 * Cette méthode devra demander un choix au joueur
	 * @param min
	 * 		Le minimum
	 * @param max
	 * 		Le maximum
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int demanderChoix(int min, int max);
	
	/**
	 * Cette méthode devra afficher un menu pour démarrer une partie
	 * @param partie
	 * 		La partie à démarrer 
	 * @param controller
	 * 		Le controleur de la partie
	 * @return
	 * 		Renvoie la partie démarrée
	 * 
	 * @see UnoController
	 */
	public Partie demarrerPartie(Partie partie, UnoController controller);
	
	/**
	 * Cette méthode devra permettre de déclarer Uno
	 */
	public void declarerUno();
	
	/**
	 * Cette méthode devra permetre de changer le controleur de la vue
	 * @param obs
	 * 		Le controleur
	 * 
	 * @see UnoController
	 */
	public void setController(Observateur obs);
	
	/**
	 * Cette méthode devra demander qui demande Uno
	 * @param partie
	 * 		La partie de Uno
	 * @return
	 * 		Renvoie le joueur 
	 */
	public Joueur quiDemandeUno(Partie partie);
	
	/**
	 * Cette méthode devra demander qui demande Contre Uno
	 * @param partie
	 * 		La partie de Uno
	 * @return
	 * 		Renvoie le joueur
	 */
	public Joueur quiDemandeContreUno(Partie partie);
	
	/**
	 * Cette méthode devra mettre à jour la vue
	 */
	public void refresh();
}

