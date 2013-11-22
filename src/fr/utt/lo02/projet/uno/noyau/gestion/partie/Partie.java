package lo02.projet.uno.noyau.gestion.partie;

import java.util.ArrayList;

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
		sens=0;
		joueurActuel=0;
		pioche = Pioche.getInstance();
		talon = Talon.getInstance();
		listeJoueur = new ArrayList<Joueur>();
	}
	
	public Partie(int nbJoueur)
	{
		
		sens=0;
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
		
		//Lancement de la partie
		this.deroulerPartie();
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
		
		talon.ajouterCarte(pioche.enleverCarte());
		
		
		
	
		
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
		
		while( !verifierGagnant() ) //tant que personne n'a gagné
		{
			
			for (int i=0; i<this.nbreJoueur; i++) //Pour chaque joueur,
			{
				if (!verifierGagnant()) //si le precedent n'a pas gagné,
				{
					appliquerRegle(listeJoueur.get(i).jouer()); //il joue
				}
			}
		}
	}


	public void nextJoueur() {
	}

	public void appliquerRegle(ESpecial special) {
		
		//On applique la règle pour chaque cas de figure
		
		System.out.println("test");
		//On ajoute la carte au talon
		
	}

	public int getSens() {
		return sens;
	}

	public void setSens(int sens) {
		this.sens = sens;
	}
public void setSens() {
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

	
}
