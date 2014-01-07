package fr.utt.lo02.projet.uno.noyau.gestion.carte;

import java.util.*;

import fr.utt.lo02.projet.uno.noyau.carte.CClassique;
import fr.utt.lo02.projet.uno.noyau.carte.CSpecial;
import fr.utt.lo02.projet.uno.noyau.carte.Carte;
import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.carte.ESpecial;

/**
 *  La classe MainJoueur représente la main d'un joueur de Uno.
 */
public class MainJoueur {

	/**
	 * L'attribut main est la liste des cartes du joueur.
	 * @see MainJoueur#ajouterCarte(Carte)
	 * @see MainJoueur#enleverCarte(int)
	 * @see MainJoueur#getMain()
	 * @see MainJoueur#getNombreCarte()
	 * @see MainJoueur#reinitialiserMain()
	 * @see MainJoueur#setMain(List)
	 * @see MainJoueur#scoreMain()
	 */
	private List<Carte> main;
	
	
	/**
	 * Constructeur de la classe MainJoueur. Il instancie l'attribut main.
	 */
	public MainJoueur()
	{
		main = new ArrayList<Carte>();
	}

	/**
	 * Cette méthode réinitialise la main du joueur, elle vide les cartes.
	 */
	public void reinitialiserMain()
	{
		this.main.clear();
	}
	
	/**
	 * Cette méthode renvoie le nombre de carte dans la main.
	 * @return
	 * 		Renvoie un entier étant le nombre de carte du joueur
	 */
	public int getNombreCarte() {
		return main.size();
	}

	/**
	 * Cette méthode renvoie la carte à l'indice index.
	 * @param index
	 * 		L'index de la carte choisie
	 * @return
	 * 		Renvoie la carte choisie
	 */
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

	/**
	 * Cette méthode calcule le score de la main.
	 * @return
	 * 		Renvoie un entier étant le score de la main.
	 */
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

	/**
	 * Cette méthode permet de rajouter une carte dans la main.
	 * @param carte
	 * 		La carte à ajouter
	 */
	public void ajouterCarte(Carte carte) {
		main.add(carte);
	}
	
	/**
	 * Cette méthode permet de récupérer la liste des cartes.
	 * @return
	 * 		Renvoie la liste des cartes
	 */
	public List<Carte> getMain() {
		return main;
	}

	/**
	 * Cette méthode permet de modifier la main.
	 * @param main
	 * 		La nouvelle main
	 */
	public void setMain(List<Carte> main) {
		this.main = main;
	}
	
	public void trierMain()
	{
		int i = 0;
		
		Carte tmp;
		
		for(i=0;i<main.size();i++)
		{
			if(main.get(i).getCouleur() == ECouleur.BLEU)
			{
				tmp = main.remove(i);
				main.add(0, tmp);
			}
		}
		
		for(i=0;i<main.size();i++)
		{
			if(main.get(i).getCouleur() == ECouleur.JAUNE)
			{
				tmp = main.remove(i);
				main.add(0, tmp);
			}
		}
		
		for(i=0;i<main.size();i++)
		{
			if(main.get(i).getCouleur() == ECouleur.ROUGE)
			{
				tmp = main.remove(i);
				main.add(0, tmp);
			}
		}
		
		for(i=0;i<main.size();i++)
		{
			if(main.get(i).getCouleur() == ECouleur.VERT)
			{
				tmp = main.remove(i);
				main.add(0, tmp);
			}
		}
		
		for(i=0;i<main.size();i++)
		{
			if(main.get(i).getCouleur() == null)
			{
				tmp = main.remove(i);
				main.add(0, tmp);
			}
		}
	}
}
