package fr.utt.lo02.projet.uno.noyau.gestion.partie;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;

import fr.utt.lo02.projet.uno.noyau.carte.Carte;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Pioche;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.*;

/**
 *  Partie est la classe g�rant une partie de Uno. Elle g�re les joueurs, les paquets de cartes et les tours de jeu.
 */
public class Partie extends Observable{
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/

	private int manche;

	private int sens;

	private int nbreJoueur;

	private int joueurActuel;

	private ArrayList<Joueur> listeJoueur;
	


	public Partie(int nbJoueur)
	{
		sens=1;
		manche = 1;
		joueurActuel=0;
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
			listeJoueur.get(i).piocherCarte(5);
		}

		Carte cartePioche; 
		
		do
		{
			
			if(Pioche.getInstance().getNombreCarte()==0)
			{
				Talon.getInstance().viderTalon();
			}
			cartePioche = Pioche.getInstance().enleverCarte();
			Talon.getInstance().ajouterCarte(cartePioche);
		}while(cartePioche.getSpecial()!=null);
		
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

			while( !verifierGagnant() ) //tant que personne n'a gagné
			{
				System.out.println(Talon.getInstance().getDerniereCarte() + " " + "il y a  " + Talon.getInstance().getNombreCarte()+ " dans le talon");
				listeJoueur.get(joueurActuel).jouer(this); //il joue
				joueurActuel = Math.abs((joueurActuel+sens)%nbreJoueur);
			}
			
			//Si la manche s'est finie car il y a eu un gagnant
			if(Pioche.getInstance().getNombreCarte()>0)
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
				System.out.println("La pioche est vide, la manche est termin�e");
			}
			

			

			//Reinitialisation des cartes pour une nouvelle manche
			Pioche.reinitialiserPioche();
			Talon.reinitialiserTalon();
			for(int i=0; i<this.nbreJoueur; i++)
			{
				this.listeJoueur.get(i).reinitialiserMain();
			}

			this.distribuerCarte();
			//Nouvelle manche
			manche++;
		}

		//Un joueur a depassé les 500 points, fin du jeu

		System.out.println( joueurGagnant + " remporte la partie, le jeu est termin�e, il y a eu " + manche + " manches");
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
		Partie p= new Partie(2, 0);
	
		p.deroulerPartie();
	}
	
    public static int getNombre(int min, int max) {
		
		Scanner sc = new Scanner(System.in);
		int i = -1;
		
		try{
			i  = sc.nextInt();
			}
		catch(InputMismatchException e)
			{
				System.out.println("Erreur: entrez un entier entre "+ min + " et " + max + ": ");
				i = getNombre(min, max);
			}
		finally
		{
			if ( i < min || i > max)
			{
				System.out.println("Erreur: entrez un entier entre "+ min + " et " + max + ": ");
				i=getNombre(min, max);
			}
		}
		
		return i;
	}

    
    
    
	

}




