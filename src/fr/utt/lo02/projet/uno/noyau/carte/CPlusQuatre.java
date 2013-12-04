package fr.utt.lo02.projet.uno.noyau.carte;

import java.util.Scanner;

import fr.utt.lo02.projet.uno.noyau.gestion.carte.Pioche;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public class CPlusQuatre extends CSpecial {
	
	public CPlusQuatre()
	{
		super(ESpecial.PLUS_QUATRE, null);
	}
	
	public void appliquerRegle(Partie partie)
	{
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n\n\n\n\n\n\n\n\n\nQuelqu'un veut il declarer un bluff? [1.."+partie.getNbreJoueur()+"] :");
		
		for(int i=0; i<partie.getNbreJoueur(); i++)
		{
			System.out.println((i+1)+": "+ partie.getJoueur(i).afficherPseudo());
		}
		
		System.out.println((partie.getNbreJoueur()+1)+": Personne");
		
		int bluff = Partie.getNombre(1, partie.getNbreJoueur()+1) -1;
		
		if (bluff < partie.getNbreJoueur() && bluff >=0)
		{
			if (partie.getJoueur(bluff).direBluff( partie.getJoueur( partie.getJoueurActuel() ) ))
				return;
		}


		Joueur joueurNext1 = partie.getJoueur(Math.abs((partie.getJoueurActuel()+partie.getSens())%partie.getNbreJoueur()));
		joueurNext1.piocherCarte(Pioche.getInstance(),4);
		System.out.println(joueurNext1.afficherPseudo() +" a pioché 4 nouvelles cartes" );
		partie.getJoueur(partie.getJoueurActuel()).choisirCouleur();
	}
	
	public boolean estPosable() 
	{
		return true;
	}

	@Override
	public String toString() {
		return "CPlusQuatre [special=" + special + ", couleur=" + couleur + "]";
	}
}
