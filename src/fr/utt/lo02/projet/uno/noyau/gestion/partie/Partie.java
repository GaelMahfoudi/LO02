package fr.utt.lo02.projet.uno.noyau.gestion.partie;

import java.util.ArrayList;
import java.util.Scanner;

import fr.utt.lo02.projet.uno.noyau.carte.Carte;
import fr.utt.lo02.projet.uno.noyau.carte.ESpecial;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Pioche;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.*;

/**
 *  Partie est la classe g�rant une partie de Uno. Elle g�re les joueurs, les paquets de cartes et les tours de jeu.
 */
public class Partie {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/

	private int manche;

	private int sens;

	private int nbreJoueur;

	private int joueurActuel;

	private Pioche pioche;

	private Talon talon;

	private ArrayList<Joueur> listeJoueur;

	public Partie()
	{
		sens=1;
		manche = 1;
		joueurActuel=0;
		pioche = Pioche.getInstance();
		talon = Talon.getInstance();
		listeJoueur = new ArrayList<Joueur>();
	}

	public Partie(int nbJoueur)
	{

		sens=1;
		manche = 1;
		joueurActuel=0;
		pioche = Pioche.getInstance();
		talon = Talon.getInstance();
		listeJoueur = new ArrayList<Joueur>();
		this.nbreJoueur = nbJoueur;

		//generation des joueurs
		for(int i=0;i<this.nbreJoueur;i++)
		{
			System.out.println("Joueur " + (i+1) + ":");
			listeJoueur.add(new JoueurNormal());
		}


		//Distribution des cartes
		this.distribuerCarte();


	}

	public void distribuerCarte() {
		for(int i=0;i<listeJoueur.size();i++)
		{
			listeJoueur.get(i).piocherCarte(pioche);
			listeJoueur.get(i).piocherCarte(pioche);
			listeJoueur.get(i).piocherCarte(pioche);
			listeJoueur.get(i).piocherCarte(pioche);
			listeJoueur.get(i).piocherCarte(pioche);
		}

		Carte cartePioche; 
		do
		{
			cartePioche= pioche.enleverCarte();
			talon.ajouterCarte(cartePioche);
		} while(cartePioche.getSpecial() != null);




	}

	public boolean verifierGagnant() {

		for(int i=0;i<this.nbreJoueur;i++)
		{
			if(listeJoueur.get(i).getNombreCarte() == 0)
			{
				return true;
			}
		}
		return false;
	}

	public void deroulerPartie() {
		boolean cinqCent = false;
		//Lancement de la partie
		Scanner sc = new Scanner(System.in);
		while(!cinqCent) //tant qu'aucun joueur n'a atteint 500 point?
		{	


			//Lancement d'une manche
			System.out.println("\n\nDebut de la manche" + manche + ":");
			joueurActuel = 0;


			while( !verifierGagnant() ) //tant que personne n'a gagné
			{
				listeJoueur.get(joueurActuel).jouer(this); //il joue
				joueurActuel = Math.abs((joueurActuel+sens)%nbreJoueur);

			}

			//Un joueur n'a plus de cartes, fin de la manche
			System.out.println( listeJoueur.get(Math.abs((joueurActuel+sens)%nbreJoueur)).afficherPseudo() +" remporte la manche "+ manche);
			//Comptage des points
			for(int i=0; i<this.nbreJoueur; i++)
			{
				this.listeJoueur.get(i).calculerScore();
				System.out.println(this.listeJoueur.get(i).afficherPseudo() + " a "+this.listeJoueur.get(i).getScore()+" points.");
				if (this.listeJoueur.get(i).getScore() >= 500)
				{
					System.out.println(this.listeJoueur.get(i).afficherPseudo()+" remporte la partie");
					cinqCent = true;
			
				}

			}

			//Reinitialisation des cartes pour une nouvelle manche
			Pioche.getInstance().reinitialiserPioche();
			for(int i=0; i<this.nbreJoueur; i++)
			{
				this.listeJoueur.get(i).reinitialiserMain();
			}

			this.distribuerCarte();
			//Nouvelle manche
			manche++;
		}

		//Un joueur a depassé les 500 points, fin du jeu


	}


	public void nextJoueur() {

		this.joueurActuel++;

	}



	public int getSens() {
		return sens;
	}

	public void setSens(int sens) {
		this.sens = sens;
	}
	public  void setSens() {
		this.sens *= -1;
	}

	public void setJoueurActuel(int joueurActuel) {
		this.joueurActuel = joueurActuel;
	}

	public Pioche getPioche() {
		return pioche;
	}

	public Talon getTalon() {
		return talon;
	}

	public Joueur getJoueur(int index){
		return listeJoueur.get(index);
	}

	public int getJoueurActuel()
	{
		return this.joueurActuel;
	}

	public int getNbreJoueur()
	{
		return this.nbreJoueur;
	}

	public static void main(String[] args)
	{
		Partie p= new Partie(1);
		
		p.distribuerCarte();
		p.deroulerPartie();
	}

}
