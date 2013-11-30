package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import fr.utt.lo02.projet.uno.noyau.carte.ESpecial;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 *  Classe g�rant l'IA de type Bluff
 */
public class IABluff implements StyleIA {

	@Override
	public int jouerCarte(Partie partie, Joueur joueur) {
		
		for(int i=0; i<partie.getNbreJoueur(); i++) //Contre Uno direct
		{
			if(partie.getJoueur(i).getNombreCarte() == 1 && !partie.getJoueur(i).uno)
			{
				return joueur.getNombreCarte()+2;
			}
		}
		for (int i=0; i<joueur.getNombreCarte(); i++)//Plus 4 si possible
		{ù
			if(joueur.getMain().getMain().get(i).getSpecial() == ESpecial.PLUS_QUATRE)
				return i;
		}
		
		//S'il ne peut pas jouer
		return joueur.getNombreCarte()+1;
		
	}

}
