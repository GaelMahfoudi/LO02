package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import fr.utt.lo02.projet.uno.noyau.carte.CSpecial;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/** 
 *  Classe g�rant l'IA de type Offensive
 */
public class IAOffensive implements StyleIA {

	@Override
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

	@Override
	public boolean direBluff(Joueur j) {
		
		if(j.getNombreCarte()>10)
		{
			return true;
		}
		
		return false;
	}
	
	

}