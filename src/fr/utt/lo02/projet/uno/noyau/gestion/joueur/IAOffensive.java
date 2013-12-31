package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import fr.utt.lo02.projet.uno.noyau.carte.CSpecial;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/** 
 *  La classe IAOffensive est une implémentation de StyleIA. Elle permet de faire adopter un comportement
 *  offensif à une IA
 */
public class IAOffensive implements StyleIA {

	/**
	 * Implémentation de la méthode jouerCarte de StyleIA.
	 * L'IA offensive cherche à poser ses cartes pièges dans un premier temps.
	 * Ensuite lla première carte qu'elle peut poser.
	 * Et enfin elle pioche si elle ne peut rien faire.
	 * @see StyleIA#jouerCarte(Partie, Joueur)
	 */
	public int jouerCarte(Partie partie, Joueur joueur) {

		int i = 0;
		
		//Il cherche la première carte piège et retourne son indice.
		for(i=0;i<joueur.getNombreCarte();i++)
		{
			if(joueur.getMain().getMain().get(i).estPosable() && joueur.getMain().getMain().get(i) instanceof CSpecial)
			{
				return i;
			}
		}

		//Il cherche la premiï¿½re carte normale de la bonne couleur si il y en a une et retourne son indice.

		for(i=0;i<joueur.getNombreCarte();i++)
		{
			if(joueur.getMain().getMain().get(i).estPosable())
			{
				return i;
			}
		}

		return joueur.getNombreCarte()+1;
	}

	/**
	 * Implémentation de la méthode direBluff de StyleIA.
	 * Elle déclare un bluff si le nombre de carte du joueur est supérieur à 10
	 * @see StyleIA#direBluff(Joueur)
	 */
	public boolean direBluff(Joueur j) {
		
		if(j.getNombreCarte()>10)
		{
			return true;
		}
		
		return false;
	}
	
	

}