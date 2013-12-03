

package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/** 
 *  Classe gï¿½rant l'IA de type Normale
 */
public class IANormal implements StyleIA {

	@Override
	public int jouerCarte(Partie partie, Joueur joueur) {
		// TODO Auto-generated method stub
		
		int i = 0;
		

		//Il cherche la première carte normale si il y en a une et retourne son indice.
		//Il cherche la premiï¿½re carte normale de la bonne couleur si il y en a une et retourne son indice.
		
		for(i=0;i<joueur.getNombreCarte();i++)
		{
			if(joueur.getMain().getMain().get(i).estPosable() && joueur.getMain().getMain().get(i).getSpecial() == null)
			{
				return i;
			}
		}
		
		//Il cherche la premiï¿½re carte de la bonne couleur et retourne son indice.
		
		for(i=0;i<joueur.getNombreCarte();i++)
		{
			if(joueur.getMain().getMain().get(i).estPosable())
			{
				return i;
			}
		}
		
		return joueur.getNombreCarte()+1;
		
	}

  /* {author=Victor Le Deuff Gaï¿½l Mahfoudi}*/

}