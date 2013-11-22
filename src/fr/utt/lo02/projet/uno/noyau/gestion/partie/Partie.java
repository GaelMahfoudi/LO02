package fr.utt.lo02.projet.uno.noyau.gestion.partie;

import java.util.ArrayList;

import fr.utt.lo02.projet.uno.noyau.carte.ESpecial;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.*;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Pioche;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;

/**
 *  Partie est la classe gérant une partie de Uno. Elle gère les joueurs, les paquets de cartes et les tours de jeu.
 */
public class Partie {
	/* {author=Victor Le Deuff Gaël Mahfoudi}*/


	private int sens;

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
		
		int i = 0;
		
		for(i=0;i<nbJoueur;i++)
		{
			listeJoueur.add(new JoueurNormal());
		}
	}

	public void distribuerCarte() {
		int i = 0;
		for(i=0;i<listeJoueur.size();i++)
		{
			listeJoueur.get(i).piocherCarte(pioche);
			listeJoueur.get(i).piocherCarte(pioche);
			listeJoueur.get(i).piocherCarte(pioche);
			listeJoueur.get(i).piocherCarte(pioche);
			listeJoueur.get(i).piocherCarte(pioche);
		}
		
		talon.ajouterCarte(pioche.enleverCarte());
	}

	public boolean veriferGagnant() {
		return false;
	}

	public void deroulerPartie() {
	}

	public void creerPaquet() {
	}

	public void setSens() {
	}

	public void nextJoueur() {
	}

	public void appliquerRegle(ESpecial special) {
	}

	public int getSens() {
		return sens;
	}

	public void setSens(int sens) {
		this.sens = sens;
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

	public static void main(String[] args){
		Partie p = new Partie();
		p.getPioche().afficherPioche();
	}
}
