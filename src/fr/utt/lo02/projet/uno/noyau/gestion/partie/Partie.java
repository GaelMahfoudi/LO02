package fr.utt.lo02.projet.uno.noyau.gestion.partie;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import IHM.ModeConsole;
import Observer.Observateur;
import Observer.Observer;
import fr.utt.lo02.projet.uno.noyau.carte.Carte;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Pioche;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.*;

/**
 *  Partie est la classe g�rant une partie de Uno. Elle g�re les joueurs, les paquets de cartes et les tours de jeu.
 */
public class Partie implements Observer{
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/

	private int manche;

	private int sens;

	private int nbreJoueur;

	private int joueurActuel;

	private ArrayList<Joueur> listeJoueur;

	private Observateur obs;



	public Partie(int nbJoueur)
	{
		sens=1;
		manche = 0;
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
		manche = 0;
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

	public boolean verifierGagnantManche() {

		for(int i=0;i<this.nbreJoueur;i++)
		{
			if(listeJoueur.get(i).getNombreCarte() == 0)
			{
				return true;
			}
		}
		return false;
	}

	public boolean verifierGagnantPartie()
	{
		for(int i=0;i<this.nbreJoueur;i++)
		{
			if(listeJoueur.get(i).getScore() >= 500)
			{
				return true;
			}
		}
		return false;
	}
	
	
	public void deroulerManche()
	{
		//Initialisation de la manche
		Pioche.reinitialiserPioche();
		Talon.reinitialiserTalon();
		for(int i=0; i<this.nbreJoueur; i++)
		{
			this.listeJoueur.get(i).reinitialiserMain();
		}

		this.distribuerCarte(); //Distribution des cartes.
		joueurActuel = -1;
		manche++;
		while( !verifierGagnantManche() ) //tant que personne n'a gagné
		{
			joueurActuel = Math.abs((joueurActuel+sens)%nbreJoueur);
			
			listeJoueur.get(joueurActuel).jouer(this);
			
			
		}
		//MAJ points du gagnant de la manche
		this.listeJoueur.get(this.joueurActuel).calculerScore(this); //TODO erreur a joueur < 3
		this.updateOManche();

	}
	
	
	public void deroulerPartie() 
	{
		//Lancement de la partie

		while(!verifierGagnantPartie()) //tant qu'aucun joueur n'a atteint 500 point
		{	
			this.deroulerManche();
		}
		this.updateOPartie(); // on notifie les observateurs
		
	}


	public void nextJoueur() {
		this.joueurActuel++;
	}



	public int getSens() {
		return sens;
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
		ModeConsole c = new ModeConsole();
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

    
    
    


	public int getManche() {
		return manche;
	}

	public void addObservateur(Observateur obs) {
		 this.obs = obs;
		
	}


	public void updateOManche() {
	      this.obs.updateManche(this);
		
	}

	public void updateOPartie() {
	
	      this.obs.updatePartie(this);
		
	}
	
	public void updateOJoueur(Joueur joueur, int i)
	{ 
		this.obs.updateJoueur(joueur, i);
	}
	
	


}




