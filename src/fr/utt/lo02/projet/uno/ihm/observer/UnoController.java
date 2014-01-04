package fr.utt.lo02.projet.uno.ihm.observer;

import fr.utt.lo02.projet.uno.ihm.graphique.ModeGraphique;
import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 * La classe UnoController permet de s�parer la vue du mod�le qui est la partie. Elle impl�mente
 * l'interface Observateur car elle observe une Partie. Lorsque la partie notify un �v�nement ou demande une information,
 * le UnoController transmet le message � la vue et retourne le r�sultat � la partie si besoin est.
 * @author Victor & Ga�l
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
	 * Cette m�thode initialise la partie du UnoController
	 */
	public void initialiserPartie()
	{
		this.partie = v.demarrerPartie(partie, this);
		partie.deroulerPartie();
	}
	
	/**
	 * Demande � la vue de notifier la fin d'une manche
	 * @see Observateur#notifyManche(Partie)
	 */
	public void notifyManche(Partie partie) {
		v.afficherFinManche(partie);
	}


	/**
	 * Demande � la vue de notifier la fin d'une partie
	 * @see Observateur#notifyPartie(Partie)
	 */
	public void notifyPartie(Partie partie) {
		v.afficherFinPartie(partie);
	}

	/**
	 * Demande � la vue de notifier un tour
	 * @see Observateur#notifyTour(Joueur, int)
	 */
	public void notifyTour(Joueur joueur, int choix) {
		v.afficherTour(joueur, choix);
	}


	/**
	 * Demande � la vue de demander une carte
	 * @see Observateur#askCarte(Joueur)
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int askCarte(Joueur joueur) {
		
		return v.demanderCarteAJouer(joueur);
		
	}


	/**
	 * Demande � la vue de demander si le joueur pose la carte pioch�e
	 * @see Observateur#askPoserCarte(Joueur)
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int askPoserCarte(Joueur joueur) {
		
		return v.poserCartePioche(joueur);
	}


	/**
	 * Demande � la vue de notifier la carte pioch�e
	 * @see Observateur#notifyPioche(Joueur)
	 */
	public void notifyPioche(Joueur joueur) {
		v.afficherCartePioche(joueur);
	}


	/**
	 * Demande � la vue de demander si le joueur d�clare bluff
	 * @see Observateur#askBluff(Partie)
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int askBluff(Partie partie) {
		
		return v.demanderBluff(partie);
		
	}

	/**
	 * Demande � la vue de notifier un mauvais choix
	 * @see Observateur#notifyError()
	 */
	public void notifyError() {
		
		v.afficherMauvaisChoix();
		
	}


	/**
	 * Demande � la vue de demander la nouvelle couleur
	 * @see Observateur#askCouleur()
	 * @see ECouleur
	 * @return 
	 * 		Renvoie la couleur choisie
	 */
	public ECouleur askCouleur() {
		
		return v.demanderCouleur();
		
	}

	/**
	 * G�re le processus de d�claration d'un Uno. Demande � la vue qui demande Uno et transmet l'information
	 * � la partie
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
	 * Demande � la vue de demander un contre Uno
	 * @see Observateur#declarerContreUno()
	 */
	public void declarerContreUno()
	{
		Joueur joueur = v.quiDemandeContreUno(partie);
		if(!joueur.equals(null))
			joueur.direContreUno(partie);
		
	}
	
	/**
	 * Demande � la vue de d�clarer contre Uno
	 * @see Observateur#askContreUno(Partie)
	 * @return
	 * 		Renvoie le choix du joueur
	 */
	public int askContreUno(Partie partie) {
		return v.demanderContreUno(partie);
	}


	/**
	 * Demande � la vue de demander le pseudo
	 * @see Observateur#askPseudo()
	 * @return
	 * 		Renvoie le pseudo
	 */
	public String askPseudo() {
		return v.demanderPseudo();
	}

	

	/**
	 * Demande � la vue de demander le choix du joueur
	 * @see Observateur#askNombre(int, int)
	 * @return 
	 * 		Renvoie le choix du joueur
	 */
	public int askNombre(int min, int max) {
		
		return v.demanderChoix(min, max);
	}

	/**
	 * Demande � la vue de notifier que le joueur passe
	 * @see Observateur#notifyPasse(Joueur)
	 */
	public void notifyPasse(Joueur joueur) {
		v.afficherPasse(joueur);
	}

	



	

}
