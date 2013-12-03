package fr.utt.lo02.projet.uno.noyau.gestion.partie;

import java.util.ArrayList;
import java.util.Scanner;

import fr.utt.lo02.projet.uno.noyau.carte.Carte;
import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.carte.ESpecial;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Pioche;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.*;

/**
 *  Partie est la classe gï¿½rant une partie de Uno. Elle gï¿½re les joueurs, les paquets de cartes et les tours de jeu.
 */
public class Partie {
	/* {author=Victor Le Deuff Gaï¿½l Mahfoudi}*/

	private int manche;

	private int sens;

	private int nbreJoueur;

	private int joueurActuel;

	private Pioche pioche;

	private Talon talon;

	private ArrayList<Joueur> listeJoueur;


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
		for(int i=0;i<nbJoueur;i++)
		{
			System.out.println("Joueur " + (i+1) + ":");
			listeJoueur.add(new JoueurNormal());
		}

		//Distribution des cartes
		this.distribuerCarte();
	}
	
	public Partie(int nbJoueur, int nbIA)
	{
		sens=1;
		manche = 1;
		joueurActuel=0;
		pioche = Pioche.getInstance();
		talon = Talon.getInstance();
		listeJoueur = new ArrayList<Joueur>();
		this.nbreJoueur = nbJoueur + nbIA;

		//generation des joueurs normaux
		for(int i=0;i<nbJoueur;i++)
		{
			System.out.println("Joueur " + (i+1) + ":");
			listeJoueur.add(new JoueurNormal());
		}

		//Generation des IAS
		for(int i=0; i<nbIA; i++)
		{
			System.out.println("Joueur " + (i+1+nbJoueur) + ":");
			listeJoueur.add(new IA());
		}

		//Distribution des cartes
		this.distribuerCarte();

	}

	public void distribuerCarte() {
		
		
		for(int i=0;i<listeJoueur.size();i++)
		{
			//Pour éviter que la pioche soit vide au moment de poser une carte sur le talon
			if(pioche.getNombreCarte()<=10)
			{
				talon.viderTalon(pioche);
			}
			listeJoueur.get(i).piocherCarte(pioche, 10);
		}

		Carte cartePioche; 

		cartePioche = pioche.enleverCarte();
		
		if(cartePioche.getSpecial() == ESpecial.JOKER || cartePioche.getSpecial() == ESpecial.PLUS_QUATRE)
		{
			talon.ajouterCarte(cartePioche);
			talon.setCouleurDerniereCarte(ECouleur.ROUGE);
		}
		else
		{
			talon.ajouterCarte(cartePioche);
		}
		
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
		String joueurGagnant = new String (" ");
		boolean cinqCent = false;
		//Lancement de la partie

		while(!cinqCent) //tant qu'aucun joueur n'a atteint 500 point
		{	


			//Lancement d'une manche
			System.out.println("\n\nDebut de la manche " + manche + ":");
			joueurActuel = 0;

			while( !verifierGagnant() ) //tant que personne n'a gagnÃ©
			{
				System.out.println(talon.getDerniereCarte() + " " + pioche.getNombreCarte());
				listeJoueur.get(joueurActuel).jouer(this); //il joue
				joueurActuel = Math.abs((joueurActuel+sens)%nbreJoueur);
			}
			
			//Si la manche c'est finie car il y a eu un gagnant
			if(pioche.getNombreCarte()>0)
			{
				System.out.println( listeJoueur.get(Math.abs((joueurActuel+sens)%nbreJoueur)).afficherPseudo() +" remporte la manche "+ manche);
				//Comptage des points du gagnant
				this.listeJoueur.get(Math.abs((joueurActuel+sens)%nbreJoueur)).calculerScore(this);

				for(int i=0; i<this.nbreJoueur; i++)
				{
					System.out.println(this.listeJoueur.get(i).afficherPseudo() + " a "+this.listeJoueur.get(i).getScore()+" points.");
					if (this.listeJoueur.get(i).getScore() >= 500)
					{
						System.out.println(this.listeJoueur.get(i).afficherPseudo()+" remporte la partie");
						joueurGagnant = (String)this.listeJoueur.get(i).afficherPseudo();
						cinqCent = true;

					}
				}
			}
			else
			{
				//Si la manche se termine carte la pioche est vide
				System.out.println("La pioche est vide, la manche est terminée");
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

		//Un joueur a depassÃ© les 500 points, fin du jeu

		System.out.println( joueurGagnant + " remporte la partie, le jeu est terminée, il y a eu " + manche + " manches");
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
		Partie p= new Partie(0, 2);
	
		p.deroulerPartie();
	}

}
