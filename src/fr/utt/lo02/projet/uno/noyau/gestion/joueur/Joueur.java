package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import Observer.Observateur;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.*;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 *  La classe Joueur est abstraite. Elle poss�de les attributs et m�thodes communes aux joueurs et aux IA.
 */
public abstract class Joueur{
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/
	

	protected int score;

	protected String pseudo;

	protected MainJoueur main;

	protected boolean uno;
	
	public Observateur obs;
	
	
	public Joueur() {

		main = new MainJoueur();
		score = 0;
		uno = false;
	}
	
	public void calculerScore(Partie partie) {
		
		for(int i=0; i < partie.getNbreJoueur(); i++)
		{
			this.score += partie.getJoueur(i).main.scoreMain();
		}
	}
	
	public int getScore()
	{
		return this.score;
	}

	public int getNombreCarte() {
		return main.getNombreCarte();
	}

	
	public void piocherCarte()
	{
		if(Pioche.getInstance().getNombreCarte()==0)
		{
			Talon.getInstance().viderTalon();
		}
		main.ajouterCarte(Pioche.getInstance().enleverCarte());
	}
	
	public void piocherCarte(int nb) {
		int i = 0;
		
		for(i=0;i<nb;i++)
		{
			if(Pioche.getInstance().getNombreCarte()==0)
			{
				Talon.getInstance().viderTalon();
			}
			main.ajouterCarte(Pioche.getInstance().enleverCarte());
		}
	}
	
	public abstract void jouer (Partie partie);
	public void reinitialiserMain()
	{
		this.main.reinitialiserMain();
		this.uno=false;
		
	}

	
	
	
	public String afficherPseudo(){
		return this.pseudo;
	}
	
	
	public MainJoueur getMain() {
		return main;
	}

	public void setMain(MainJoueur main) {
		this.main = main;
	}

	public abstract void direUno();

	public abstract void choisirCouleur();

	public abstract void direContreUno(Joueur j);
	
	public abstract void direContreUno(Partie partie);
	public abstract void direBluff(Joueur joueur);

	public void addObservateur(Observateur obs) {
		this.obs= obs;
		
	}



}
