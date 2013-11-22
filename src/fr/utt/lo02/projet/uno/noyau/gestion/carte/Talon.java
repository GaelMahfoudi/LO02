package lo02.projet.uno.noyau.gestion.carte;

import java.util.*;

import lo02.projet.uno.noyau.carte.*;

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

	public void viderTalon(Pioche pioche) {
		while(listeCarte.isEmpty()!=true)
		{
			pioche.ajouterCarte(listeCarte.poll());
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
