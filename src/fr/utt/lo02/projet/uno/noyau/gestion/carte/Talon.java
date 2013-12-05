package fr.utt.lo02.projet.uno.noyau.gestion.carte;

import java.util.*;

import fr.utt.lo02.projet.uno.noyau.carte.*;

/**
 *  Talon h�rite de PaquetDeCarte. Elle repr�sente le talon. Elle n'est instanci�e qu'une fois.
 */

public class Talon {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/


	private Queue<Carte> listeCarte;
	
	private static Talon talon = null;
	
	public Talon()
	{
		listeCarte = new LinkedList<Carte>();
	}

	public void viderTalon() {
		while(listeCarte.size() != 1)
		{
			Pioche.getInstance().ajouterCarte(listeCarte.poll());
		}
	}

	public void setCouleurDerniereCarte(ECouleur couleur) {
		((LinkedList<Carte>) listeCarte).peekLast().setCouleur(couleur);
	}

	public void ajouterCarte(Carte carte) {
		listeCarte.add(carte);
	}

	public Carte getDerniereCarte() {
		return ((LinkedList<Carte>) listeCarte).peekLast();
	}
	
	public Carte getAvantDerniereCarte() {
		return ((LinkedList<Carte>) listeCarte).get(this.getNombreCarte()-1); //TODO verifier qu'il s'agit bien de l'avant derniere
	}

	public static Talon getInstance() {
		if(Talon.talon == null)
		{
			talon = new Talon();
			return talon;
		}
		else
		{
			return talon;
		}
	}

	public Carte enleverCarte() {
		return listeCarte.poll();
	}

	public int getNombreCarte() {
		return listeCarte.size();
	}
	
	public void afficherTalon()
	{
		for (Iterator<Carte> i = listeCarte.iterator(); i.hasNext();) {
	      Carte carte = (Carte) i.next();
	      System.out.println(carte);
		}
	}
	
	public static void reinitialiserTalon()
	{
		talon = null;
		talon = new Talon();
	}

}
