

package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/** 
 *  La classe IANormal est une impl�mentation de StyleIA. Elle permet de faire adopter un comportement
 *  neutre � une IA
 */
public class IANormal implements StyleIA {

	/**
	 * Impl�mentation de la m�thode jouerCarte de StyleIA.
	 * Elle cherche d'abord une carte normale � poser, puis n'importe quelle carte
	 * et enfin elle pioche si elle ne peut rien faire.
	 * @see StyleIA#jouerCarte(Partie, Joueur)
	 */
	public int jouerCarte(Partie partie, Joueur joueur) {
		
		int i = 0;
		

		//Il cherche la premi�re carte normale si il y en a une et retourne son indice.
		//Il cherche la premi�re carte normale de la bonne couleur si il y en a une et retourne son indice.
		
		for(i=0;i<joueur.getNombreCarte();i++)
		{
			if(joueur.getMain().getMain().get(i).estPosable() && joueur.getMain().getMain().get(i).getSpecial() == null)
			{
				return i;
			}
		}
		
		//Il cherche la première carte de la bonne couleur et retourne son indice.
		
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
	 * L'IA neutre ne d�clare pas de bluff.
	 * @see StyleIA#direBluff(Joueur)
	 */
	public boolean direBluff(Joueur j) {
		
		return false;
	}

  /* {author=Victor Le Deuff Ga�l Mahfoudi}*/

}