package lo02.projet.uno.noyau.gestion.partie;

import java.util.ArrayList;
import java.util.Scanner;

import lo02.projet.uno.noyau.carte.Carte;
import lo02.projet.uno.noyau.carte.ESpecial;
import lo02.projet.uno.noyau.gestion.carte.Pioche;
import lo02.projet.uno.noyau.gestion.carte.Talon;
import lo02.projet.uno.noyau.gestion.joueur.*;

/**
 *  Partie est la classe g�rant une partie de Uno. Elle g�re les joueurs, les paquets de cartes et les tours de jeu.
 */
public class Partie {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/


	private int sens;
	
	private int nbreJoueur;

	private int joueurActuel;
	
	private Pioche pioche;
	
	private Talon talon;
	
	private ArrayList<Joueur> listeJoueur;

    public Partie()
	{
		sens=1;
		joueurActuel=0;
		pioche = Pioche.getInstance();
		talon = Talon.getInstance();
		listeJoueur = new ArrayList<Joueur>();
	}
	
	public Partie(int nbJoueur)
	{
		
		sens=1;
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
		
		//Lancement de la partie
		while(true) //tant qu'aucun joueur n'a atteint 500 point?
		{	
			Scanner sc = new Scanner(System.in);
			joueurActuel = 0;
			//Lancement d'une manche
			while( !verifierGagnant() ) //tant que personne n'a gagné
			{
				listeJoueur.get(joueurActuel).jouer(this); //il joue
				joueurActuel = Math.abs((joueurActuel+sens)%nbreJoueur);
				
			}
			
		}
		
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
	
}
