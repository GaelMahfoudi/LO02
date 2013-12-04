package fr.utt.lo02.projet.uno.noyau.carte;

import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public class CInverse extends CSpecial{

	public CInverse(ECouleur couleur)
	{
		super(ESpecial.INVERSE, couleur);
	}

	@Override
	public void appliquerRegle(Partie partie) {
		partie.setSens();
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
		return "CInverse [special=" + special + ", couleur=" + couleur + "]";
	}
}
