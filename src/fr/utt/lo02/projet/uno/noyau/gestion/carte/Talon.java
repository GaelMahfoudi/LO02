package fr.utt.lo02.projet.uno.noyau.gestion.carte;

import java.util.*;

import fr.utt.lo02.projet.uno.noyau.carte.*;

/**
 *  Talon hérite de PaquetDeCarte. Elle représente le talon. Elle n'est instanciée qu'une fois.
 */

public class Talon {
	/* {author=Victor Le Deuff Gaël Mahfoudi}*/


	private Queue<Carte> listeCarte;
	
	private static Talon talon = null;
	
	public Talon()
	{
		listeCarte = new LinkedList<Carte>();
	}

	public void viderTalon(Pioche pioche) {
		while(listeCarte.isEmpty()!=true)
		{
			pioche.ajouterCarte(listeCarte.poll());
		}
	}

	public void setCouleurDerniereCarte(ECouleur couleur) {
	}

	public void ajouterCarte(Carte carte) {
		listeCarte.add(carte);
	}

	public Carte getDerniereCarte() {
		return listeCarte.peek();
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

}
