package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import fr.utt.lo02.projet.uno.ihm.observer.*;
import fr.utt.lo02.projet.uno.noyau.carte.Carte;
import fr.utt.lo02.projet.uno.noyau.carte.ESpecial;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.*;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 *  La classe Joueur est abstraite. Elle possède les attributs et méthodes communes aux joueurs et aux IA.
 */
public abstract class Joueur{

	
	/**
	 * Entier représentant le score du joueur
	 * @see Joueur#getScore()
	 * @see Joueur#calculerScore(Partie)
	 */
	protected int score;

	/**
	 * Chaine de caractère représentant le nom du joueur
	 * @see Joueur#afficherPseudo()
	 */
	protected String pseudo;

	/**
	 * MainJoueur représentant la main du joueur
	 * @see Joueur#getMain()
	 * @see Joueur#reinitialiserMain()
	 * @see Joueur#setMain(MainJoueur)
	 * @see MainJoueur
	 */
	protected MainJoueur main;

	/**
	 * Boolean pour déterminer si le joueur a di uno ou non
	 * @see Joueur#direContreUno(Joueur)
	 * @see Joueur#direContreUno(Partie)
	 * @see Joueur#direUno()
	 * @see Joueur#getUno()
	 */
	protected boolean uno;

	/**
	 * Observateur du joueur
	 * @see Joueur#addObservateur(Observateur)
	 * @see Observateur
	 */
	public Observateur obs;


	/**
	 * Constructeur d'un joueur de uno
	 * @param obs
	 * 		L'Observateur du joueur
	 * @see Observateur
	 */
	public Joueur(Observateur obs) {
		this.addObservateur(obs);
		main = new MainJoueur();
		score = 0;
		uno = false;
		pseudo = " ";
	}

	/**
	 * Cette méthode calcule le score du joueur. Elle prend en paramètre la partie de Uno
	 * afin de calculer le score des mains de tous les joueurs pour l'ajouter au score
	 * du joueur.
	 * @param partie
	 * 		La partie de Uno
	 */
	public void calculerScore(Partie partie) {

		for(int i=0; i < partie.getNbreJoueur(); i++)
		{
			this.score += partie.getJoueur(i).main.scoreMain();
		}
	}

	/**
	 * Cette méthode renvoie le score du joueur
	 * @return
	 * 		Renvoie un entier étant le score du joueur
	 */
	public int getScore()
	{
		return this.score;
	}

	/**
	 * Cette méthode renvoie le nombre de carte du joueur
	 * @return
	 * 		Renvoie un entier étant le nombre de carte du joueur
	 */
	public int getNombreCarte() {
		return main.getNombreCarte();
	}

	/**
	 * Cette méthode renvoie la vaeur du boolean uno
	 * @return
	 * 		Renvoie le boolean uno
	 */
	public boolean getUno()
	{
		return uno;
	}


	/**
	 * Cette méthode permet au joueur de piocher une carte
	 */
	public void piocherCarte()
	{
		if(Pioche.getInstance().getNombreCarte()==0)
		{
			Talon.getInstance().viderTalon();
		}

		Carte c = Pioche.getInstance().enleverCarte();

		if((c.getSpecial()==ESpecial.JOKER || c.getSpecial() == ESpecial.PLUS_QUATRE) && c.getCouleur()!=null)
		{
			c.setCouleur(null);
		}

		main.ajouterCarte(c);
		obs.notifyPioche(this);
	}
	
	/**
	 * Cette méthode permet au joueur de piocher une carte lors de son premier tour
	 * (elle ne notifie pas l'observateur)
	 */
	public void piocherCarteFirstTour()
	{
		if(Pioche.getInstance().getNombreCarte()==0)
		{
			Talon.getInstance().viderTalon();
		}

		Carte c = Pioche.getInstance().enleverCarte();

		if((c.getSpecial()==ESpecial.JOKER || c.getSpecial() == ESpecial.PLUS_QUATRE) && c.getCouleur()!=null)
		{
			c.setCouleur(null);
		}

		main.ajouterCarte(c);
	}

	/**
	 * Cette méthode permet au joueur de piocher n cartes au premier tour
	 * (elle ne notifie pas l'observateur)
	 * @param nb
	 * 		Nombre de carte à piocher
	 */
	public void piocherCarteFirstTour(int nb)
	{
		int i = 0;

		for(i=0;i<nb;i++)
		{
			this.piocherCarteFirstTour();
		}
	}

	
	/**
	 * Cette méthode permet au joueur de piocher n cartes
	 * @param nb
	 * 		Nombre de carte à piocher
	 */
	public void piocherCarte(int nb) {
		int i = 0;

		for(i=0;i<nb;i++)
		{
			this.piocherCarte();
		}
	}

	/**
	 * Cette méthode doit définir la façon dont le joueur joue. Elle prendra
	 * une partie en paramètre afin que le joueur joue en fonction de la partie
	 * @param partie
	 * 		La partie de Uno
	 */
	public abstract void jouer (Partie partie);
	
	/**
	 * Cette méthode réinitialise la main du joueur
	 */
	public void reinitialiserMain()
	{
		this.main.reinitialiserMain();
		this.uno=false;

	}



	/**
	 * Cette méthode renvoie le pseudo du joueur
	 * @return
	 * 		Renvoie une chaine de caractère étant le pseudo du joueur
	 */
	public String afficherPseudo(){
		return this.pseudo;
	}


	/**
	 * Cette méthode renvoie la main du joueur
	 * @return
	 * 		Renvoie la main du joueur
	 * @see MainJoueur
	 */
	public MainJoueur getMain() {
		return main;
	}



	/**
	 * Cette méthode permet de changer la main du joueur
	 * @param main
	 * 		La nouvelle main 
	 * @see MainJoueur
	 */
	public void setMain(MainJoueur main) {
		this.main = main;
	}

	/**
	 * Cette méthode devra définir la façon dont le joueur dit uno
	 */
	public abstract void direUno();

	/**
	 * Cette méthode devrat définir la façon dont le joueur choisit une couleur
	 */
	public abstract void choisirCouleur();

	/**
	 * Cette méthode devra définir la façon dont le joueur dit contre uno.
	 * Elle prendra en paramètre le joueur j à qui l'on dit contre uno.
	 * @param j
	 * 		Le joueur à qui l'on dit contre uno
	 */
	public abstract void direContreUno(Joueur j);

	/**
	 * Cette méthode devra définir la façon dont le joueur dit contre uno.
	 * Elle prendra en paramètre la partie ou se trouve le joueur
	 * @param partie
	 * 		La partie ou se trouve le joueur
	 */
	public abstract void direContreUno(Partie partie);
	
	/**
	 * Cette méthode devra définir la façon dont le joueur dit bluff.
	 * Elle prend en paramètre la partie de uno et le joueur à qui l'on di bluff
	 * @param joueur
	 * 		Le joueur accusé de bluff
	 * @param partie
	 * 		La partie de uno
	 */
	public abstract void direBluff(Joueur joueur, Partie partie);

	/**
	 * Cette méthode permet de régler l'observateur de ce joueur
	 * @param obs
	 * 		L'observateur de ce joueur
	 * @see Observateur
	 * @see UnoController
	 */
	public void addObservateur(Observateur obs) {
		this.obs= obs;

	}



}
