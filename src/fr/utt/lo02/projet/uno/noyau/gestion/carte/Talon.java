package fr.utt.lo02.projet.uno.noyau.gestion.carte;

import java.util.*;

import fr.utt.lo02.projet.uno.noyau.carte.*;

/**
 *  La classe Talon permet de g�rer le talon d'une partie de Uno. Cette classe suit le design pattern Singleton.
 *  Elle n'est instanci�e qu'une seule fois.
 */

public class Talon {

	
	/**
	 * L'attribut listeCarte est la liste des cartes du talon.
	 */
	private Queue<Carte> listeCarte;
	
	/**
	 * L'attribut talon est l'unique instance de la classe Talon.
	 * l'attibut est priv� est accessible uniquement via la m�thode getInstance
	 * @see Talon#getInstance()
	 */
	private static Talon talon = null;
	
	/**
	 * Constructeur de la classe Talon. Il instancie l'attribue listeCarte
	 * @see Talon#listeCarte
	 */
	public Talon()
	{
		listeCarte = new LinkedList<Carte>();
	}

	/**
	 * Cette m�thode permet de vider toutes les cartes du talon dans la pioche lorsque celle ci est vide.
	 */
	public void viderTalon() {
		while(listeCarte.size() != 1)
		{
			Pioche.getInstance().ajouterCarte(listeCarte.poll());
		}
	}
	
	/**
	 * Cette m�thode retire la derni�re carte du talon et la renvoie.
	 * @return
	 * 		Renvoie la carte retir�e
	 */
	public Carte enleverDerniereCarte()
	{
		return listeCarte.remove();
	}

	/**
	 * Cette m�thode permet de modifier la couleur de la derni�re carte.
	 * @param couleur
	 * 		La nouvelle couleur
	 */
	public void setCouleurDerniereCarte(ECouleur couleur) {
		((LinkedList<Carte>) listeCarte).peekLast().setCouleur(couleur);
	}

	/**
	 * Cette m�thode permet d'ajouter une carte au talon.
	 * @param carte
	 * 		La nouvelle carte
	 */
	public void ajouterCarte(Carte carte) {
		listeCarte.add(carte);
	}

	/**
	 * Cette m�thode permet de voire la derni�re carte sans la retirer
	 * @return
	 * 		Renvoie la derni�re carte
	 */
	public Carte getDerniereCarte() {
		return ((LinkedList<Carte>) listeCarte).peekLast();
	}
	
	/**
	 * Cette carte permet de voire l'avant derni�re carte du paquet sans la retirer.
	 * @return
	 * 		Renvoie l'avant derni�re carte
	 */
	public Carte getAvantDerniereCarte() {
		return ((LinkedList<Carte>) listeCarte).get(this.getNombreCarte()-1); //TODO verifier qu'il s'agit bien de l'avant derniere
	}

	/**
	 * Cette m�thode permet de r�cup�rer l'unique instance de la classe Talon.
	 * @return
	 * 		Renvoie l'unique instance de la classe Talon
	 */
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

	/**
	 * Cette m�thode permet de retirer la derni�re carte du talon.
	 * @return
	 * 		Renvoie la carte retir�e
	 */
	public Carte enleverCarte() {
		return listeCarte.poll();
	}

	/**
	 * Cette m�thode permet de r�cup�rer le nombre de carte du talon.
	 * @return
	 * 		Renvoie un entier �tant le nombre de carte du talon
	 */
	public int getNombreCarte() {
		return listeCarte.size();
	}
	
	
	/**
	 * Cette m�thode permet de r�initialiser le talon.
	 */
	public static void reinitialiserTalon()
	{
		talon = null;
		talon = new Talon();
	}

}
