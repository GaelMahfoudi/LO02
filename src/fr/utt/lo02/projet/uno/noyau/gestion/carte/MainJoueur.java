package fr.utt.lo02.projet.uno.noyau.gestion.carte;

import java.util.*;

import fr.utt.lo02.projet.uno.noyau.carte.CClassique;
import fr.utt.lo02.projet.uno.noyau.carte.CSpecial;
import fr.utt.lo02.projet.uno.noyau.carte.Carte;
import fr.utt.lo02.projet.uno.noyau.carte.ESpecial;

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
		int score = 0;
		for (int i=0; i< this.main.size(); i++)
		{
			if (this.main.get(i) instanceof CClassique )
			   score += this.main.get(i).getValeur();
			else if (this.main.get(i) instanceof CSpecial)
			{
				if(this.main.get(i).getSpecial() == ESpecial.PLUS_DEUX || this.main.get(i).getSpecial() == ESpecial.INVERSE || this.main.get(i).getSpecial() == ESpecial.PASSE)
					score+=20;
				else if (this.main.get(i).getSpecial() == ESpecial.PLUS_QUATRE || this.main.get(i).getSpecial() == ESpecial.JOKER)
					score+=50;
			}
		}
		return score;
	}

	public void ajouterCarte(Carte carte) {
		main.add(carte);
	}
	
	
	public List<Carte> getMain() {
		return main;
	}

	public void setMain(List<Carte> main) {
		this.main = main;
	}

	public void afficherMain() {
		
		int i = 0;
		
		for(i=0;i<main.size();i++)
		{
			System.out.println((i+1)+": "+ main.get(i));
		}
	}

}
