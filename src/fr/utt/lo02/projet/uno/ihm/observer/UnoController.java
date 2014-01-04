package fr.utt.lo02.projet.uno.ihm.observer;

import fr.utt.lo02.projet.uno.ihm.graphique.ModeGraphique;
import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 * La classe UnoController permet de séparer la vue du modèle qui est la partie. Elle implémente
 * l'interface Observateur car elle observe une Partie. Lorsque la partie notify un événement ou demande une information,
 * le UnoController transmet le message à la vue et retourne le résultat à la partie si besoin est.
 * @author Victor & Gaël
 */

public class UnoController implements Observateur{

	/**
	 * L'attribut v est la vue du controleur
	 */
	private View v;
	
	/**
	 * L'attribut partie est la partie du controleur
	 */
	private Partie partie;
	
	
	/**
	 * Constructeur de la classe UnoControleur
	 * @param v
	 * 		La vue du controleur
	 */
	public UnoController(View v)
	{
		this.v = v;
		v.setController(this);
	}
	
	/**
	 * Cette méthode initialise la partie du UnoController
	 */
	public void initialiserPartie()
	{
		this.partie = v.demarrerPartie(partie, this);
		partie.deroulerPartie();
	}
	
	/**
	 * Demande à la vue de notifier la fin d'une manche
	 * @see Observateur#notifyManche(Partie)
	 */
	public void notifyManche(Partie partie) {
		v.afficherFinManche(partie);
	}


	/**
	 * Demande à la vue de notifier la fin d'une partie
	 * @see Observateur#notifyPartie(Partie)
	 */
	public void notifyPartie(Partie partie) {
		v.afficherFinPartie(partie);
	}

	/**
	 * Demande à la vue de notifier un tour
	 * @see Observateur#notifyTour(Joueur, int)
	 */
	public void notifyTour(Joueur joueur, int choix) {
		v.afficherTour(joueur, choix);
	}


	/**
	 * Demande à la vue de demander une carte
	 * @see Observateur#askCarte(Joueur)
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int askCarte(Joueur joueur) {
		
		return v.demanderCarteAJouer(joueur);
		
	}


	/**
	 * Demande à la vue de demander si le joueur pose la carte piochée
	 * @see Observateur#askPoserCarte(Joueur)
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int askPoserCarte(Joueur joueur) {
		
		return v.poserCartePioche(joueur);
	}


	/**
	 * Demande à la vue de notifier la carte piochée
	 * @see Observateur#notifyPioche(Joueur)
	 */
	public void notifyPioche(Joueur joueur) {
		v.afficherCartePioche(joueur);
	}


	/**
	 * Demande à la vue de demander si le joueur déclare bluff
	 * @see Observateur#askBluff(Partie)
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int askBluff(Partie partie) {
		
		return v.demanderBluff(partie);
		
	}

	/**
	 * Demande à la vue de notifier un mauvais choix
	 * @see Observateur#notifyError()
	 */
	public void notifyError() {
		
		v.afficherMauvaisChoix();
		
	}


	/**
	 * Demande à la vue de demander la nouvelle couleur
	 * @see Observateur#askCouleur()
	 * @see ECouleur
	 * @return 
	 * 		Renvoie la couleur choisie
	 */
	public ECouleur askCouleur() {
		
		return v.demanderCouleur();
		
	}

	/**
	 * Gère le processus de déclaration d'un Uno. Demande à la vue qui demande Uno et transmet l'information
	 * à la partie
	 * @see Observateur#declarerUno()
	 */
	public void declarerUno() {
		Joueur joueur = v.quiDemandeUno(partie);
		if(joueur != null)
		{
			joueur.direUno();
			((ModeGraphique)v).rapport.refreshJoueur(joueur);
			v.refresh();
		}
	}

	/**
	 * Utiliser declarerUno
	 * @deprecated
	 * @see Observateur#declarerUno()
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int askUno() {
		return 1;
	}

	/**
	 * Demande à la vue de demander un contre Uno
	 * @see Observateur#declarerContreUno()
	 */
	public void declarerContreUno()
	{
		Joueur joueur = v.quiDemandeContreUno(partie);
		if(!joueur.equals(null))
			joueur.direContreUno(partie);
		
	}
	
	/**
	 * Demande à la vue de déclarer contre Uno
	 * @see Observateur#askContreUno(Partie)
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int askContreUno(Partie partie) {
		return v.demanderContreUno(partie);
	}


	/**
	 * Demande à la vue de demander le pseudo
	 * @see Observateur#askPseudo()
	 * @return
	 * 		Renvoie le pseudo
	 */
	public String askPseudo() {
		return v.demanderPseudo();
	}

	

	/**
	 * Demande à la vue de demander le choix du joueur
	 * @see Observateur#askNombre(int, int)
	 * @return 
	 * 		Renvoie le choix du joueur
	 */
	public int askNombre(int min, int max) {
		
		return v.demanderChoix(min, max);
	}

	/**
	 * Demande à la vue de notifier que le joueur passe
	 * @see Observateur#notifyPasse(Joueur)
	 */
	public void notifyPasse(Joueur joueur) {
		v.afficherPasse(joueur);
	}

	



	

}
