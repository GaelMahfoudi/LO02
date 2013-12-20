package fr.utt.lo02.projet.uno.noyau.carte;

import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public class CPasse extends CSpecial {
	
	public CPasse(ECouleur couleur)
	{
		super(ESpecial.PASSE, couleur);
	}

	@Override
	public void appliquerRegle(Partie partie) {
		partie.nextJoueur();
	}

	@Override
	public boolean estPosable() {
		
		if(this.couleur==Talon.getInstance().getDerniereCarte().getCouleur() || this.special == Talon.getInstance().getDerniereCarte().getSpecial())
		{
			return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "CPasse [special=" + special + ", couleur=" + couleur + "]";
	}
	
}
