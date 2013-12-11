package fr.utt.lo02.projet.uno.noyau.carte;


import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public class CJoker extends CSpecial{
	
	public CJoker()
	{
		super(ESpecial.JOKER, null);
	}
	
	public void appliquerRegle(Partie partie)
	{
		partie.getJoueur(partie.getJoueurActuel()).choisirCouleur();
	}

	public boolean estPosable() 
	{
		return true;
	}
	
	@Override
	public String toString() {
		return "CJoker [special=" + special + ", couleur=" + couleur + "]";
	}
}
