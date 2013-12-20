package fr.utt.lo02.projet.uno.noyau.carte;


import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public class CPlusQuatre extends CSpecial {
	
	public CPlusQuatre()
	{
		super(ESpecial.PLUS_QUATRE, null);
	}
	
	public void appliquerRegle(Partie partie)
	{
		Joueur joueurNext = partie.getJoueur(Math.abs((partie.getJoueurActuel()+partie.getSens())%partie.getNbreJoueur()));
		
		joueurNext.direBluff(partie.getJoueur(partie.getJoueurActuel()));
		
		partie.getJoueur(partie.getJoueurActuel()).choisirCouleur();
		
	}
	
	public boolean estPosable() 
	{
		return true;
	}

	
	public String toString() {
		return "CPlusQuatre [special=" + special + ", couleur=" + couleur + "]";
	}
}
