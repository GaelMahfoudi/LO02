

package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/** 
 *  La classe IANormal est une implémentation de StyleIA. Elle permet de faire adopter un comportement
 *  neutre à une IA
 */
public class IANormal implements StyleIA {

	/**
	 * Implémentation de la méthode jouerCarte de StyleIA.
	 * Elle cherche d'abord une carte normale à poser, puis n'importe quelle carte
	 * et enfin elle pioche si elle ne peut rien faire.
	 * @see StyleIA#jouerCarte(Partie, Joueur)
	 */
	public int jouerCarte(Partie partie, Joueur joueur) {
		
		int i = 0;
		

		//Il cherche la premiï¿½re carte normale si il y en a une et retourne son indice.
		//Il cherche la premiï¿½re carte normale de la bonne couleur si il y en a une et retourne son indice.
		
		for(i=0;i<joueur.getNombreCarte();i++)
		{
			if(joueur.getMain().getMain().get(i).estPosable() && joueur.getMain().getMain().get(i).getSpecial() == null)
			{
				return i;
			}
		}
		
		//Il cherche la premiÃ¨re carte de la bonne couleur et retourne son indice.
		
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
	 * L'IA neutre ne déclare pas de bluff.
	 * @see StyleIA#direBluff(Joueur)
	 */
	public boolean direBluff(Joueur j) {
		
		return false;
	}

  /* {author=Victor Le Deuff Gaï¿½l Mahfoudi}*/

}