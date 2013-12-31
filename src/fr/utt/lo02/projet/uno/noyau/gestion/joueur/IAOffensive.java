package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import fr.utt.lo02.projet.uno.noyau.carte.CSpecial;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/** 
 *  La classe IAOffensive est une impl�mentation de StyleIA. Elle permet de faire adopter un comportement
 *  offensif � une IA
 */
public class IAOffensive implements StyleIA {

	/**
	 * Impl�mentation de la m�thode jouerCarte de StyleIA.
	 * L'IA offensive cherche � poser ses cartes pi�ges dans un premier temps.
	 * Ensuite lla premi�re carte qu'elle peut poser.
	 * Et enfin elle pioche si elle ne peut rien faire.
	 * @see StyleIA#jouerCarte(Partie, Joueur)
	 */
	public int jouerCarte(Partie partie, Joueur joueur) {

		int i = 0;
		
		//Il cherche la premi�re carte pi�ge et retourne son indice.
		for(i=0;i<joueur.getNombreCarte();i++)
		{
			if(joueur.getMain().getMain().get(i).estPosable() && joueur.getMain().getMain().get(i) instanceof CSpecial)
			{
				return i;
			}
		}

		//Il cherche la premi�re carte normale de la bonne couleur si il y en a une et retourne son indice.

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
	 * Impl�mentation de la m�thode direBluff de StyleIA.
	 * Elle d�clare un bluff si le nombre de carte du joueur est sup�rieur � 10
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