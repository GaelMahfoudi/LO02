package lo02.projet.uno.noyau.gestion.carte;

import java.util.*;

import lo02.projet.uno.noyau.carte.Carte;

/**
 *  MainJoueur h�rite de PaquetDeCarte. Elle repr�sente la main du joueur.
 */
public class MainJoueur {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/


	private List<Carte> main;
	
	public MainJoueur()
	{
		main = new ArrayList<Carte>();
	}

	public void reinitialiserMain()
	{
		this.main.clear();
	}
	
	
	public int getNombreCarte() {
		return main.size();
	}

	public Carte enleverCarte(int index) {
		if(index<0 || index>main.size())
		{
			return null;
		}
		else
		{
			return main.remove(index);
		}
	}

	public int scoreMain() {
		return 0;
	}

	public void ajouterCarte(Carte carte) {
		main.add(carte);
	}
	
	public void afficherMain() {
		
		int i = 0;
		
		for(i=0;i<main.size();i++)
		{
			System.out.println((i+1)+": "+ main.get(i));
		}
	}

}
