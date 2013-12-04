package fr.utt.lo02.projet.uno.noyau.carte;

import fr.utt.lo02.projet.uno.noyau.gestion.carte.Pioche;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public class CPlusDeux extends CSpecial {
	
	public CPlusDeux(ECouleur couleur)
	{
		super(ESpecial.PLUS_DEUX, couleur);
	}

	@Override
	public void appliquerRegle(Partie partie) {

		Joueur joueurNext = partie.getJoueur(Math.abs((partie.getJoueurActuel()+partie.getSens())%partie.getNbreJoueur()));
		joueurNext.piocherCarte(Pioche.getInstance(), 2);
		System.out.println(joueurNext.afficherPseudo() +" a pioché 2 nouvelles cartes"  );
		
	}

	@Override
	public boolean estPosable() {
		
		if(this.couleur==Talon.getInstance().getDerniereCarte().getCouleur())
		{
			return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "CPlusDeux [special=" + special + ", couleur=" + couleur + "]";
	}
	
}
