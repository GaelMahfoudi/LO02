package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import fr.utt.lo02.projet.uno.noyau.carte.ESpecial;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 *  La classe IABluff est une impl�mentation de StyleIA. Elle permet de faire adopter un comportement
 *  de bluff � une IA
 */
public class IABluff implements StyleIA {

	/**
	 * Impl�mentation de la m�thode jouerCarte de StyleIA.
	 * Elle cherche dans un premier temps � dire contre uno � un joueur.
	 * Puis elle cherche une carte plus quatre.
	 * @see StyleIA#jouerCarte(Partie, Joueur)
	 */
	public int jouerCarte(Partie partie, Joueur joueur) {
		
		for(int i=0; i<partie.getNbreJoueur(); i++) //Contre Uno direct
		{
			if(partie.getJoueur(i).getNombreCarte() == 1 && !partie.getJoueur(i).uno)
			{
				return joueur.getNombreCarte()+2;
			}
		}
		for (int i=0; i<joueur.getNombreCarte(); i++)//Plus 4 si possible
		{
			if(joueur.getMain().getMain().get(i).getSpecial() == ESpecial.PLUS_QUATRE)
				return i;
		}
		
		//S'il ne peut pas jouer
		return joueur.getNombreCarte()+1;
		
	}

	/**
	 * Impl�mentation de la m�thode direBluff de StyleIA.
	 * Si le joueur � plus de 7 cartes, l'IA dit bluff.
	 * @see StyleIA#direBluff(Joueur)
	 */
	public boolean direBluff(Joueur j) {
		if(j.getNombreCarte()>7)
		{
			return true;
		}
		
		return false;
	}

}
