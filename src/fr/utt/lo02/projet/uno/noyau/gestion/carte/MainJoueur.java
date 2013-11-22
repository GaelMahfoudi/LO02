package fr.utt.lo02.projet.uno.noyau.gestion.carte;

import java.util.*;

import fr.utt.lo02.projet.uno.noyau.carte.Carte;

/**
 *  MainJoueur hérite de PaquetDeCarte. Elle représente la main du joueur.
 */
public class MainJoueur {
	/* {author=Victor Le Deuff Gaël Mahfoudi}*/


	private List<Carte> main;
	
	public MainJoueur()
	{
		main = new ArrayList<Carte>();
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
			System.out.println(main.get(i));
		}
	}

}
