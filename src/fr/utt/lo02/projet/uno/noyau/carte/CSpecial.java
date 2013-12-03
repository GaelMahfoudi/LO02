package fr.utt.lo02.projet.uno.noyau.carte;


import java.util.Scanner;

import fr.utt.lo02.projet.uno.noyau.gestion.carte.Pioche;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/** 
 *  CSpecial h�rite de Carte. CSpecial rep�sente l'ensemble des cartes sp�ciales.
 */
public class CSpecial extends Carte {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/


	private ESpecial special;

	public CSpecial(ESpecial special, ECouleur couleur)
	{
		this.couleur=couleur;
		this.special=special;
	}

	public ESpecial getSpecial() {
		return this.special;
	}

	public void appliquerRegle(Partie partie)
	{
		Scanner sc = new Scanner(System.in);
		switch( this.getSpecial())
		{
			case PASSE:
				partie.nextJoueur();
				break;
			case INVERSE:
				partie.setSens();
				break;
			case PLUS_DEUX:
				
				Joueur joueurNext = partie.getJoueur(Math.abs((partie.getJoueurActuel()+partie.getSens())%partie.getNbreJoueur()));
				joueurNext.piocherCarte(Pioche.getInstance(), 2);
				System.out.println(joueurNext.afficherPseudo() +" a pioché 2 nouvelles cartes"  );
				break;
			case PLUS_QUATRE:
				
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

				break;
			case JOKER:		
				partie.getJoueur(partie.getJoueurActuel()).choisirCouleur();
				break;
		}

	}
	
	
	
	@Override
	public String toString() {
		return "CSpecial [ Special= " + this.special + ", Couleur= " + this.couleur+"]";
	}
	
	public boolean estPosable() 
	{
		  if(this.getSpecial()==ESpecial.PLUS_QUATRE || this.getSpecial()==ESpecial.JOKER)
		  {
			  return true;
		  }
		  else if(this.getCouleur()==Talon.getInstance().getDerniereCarte().getCouleur())
		  {
			  return true;
		  }
		  else
		  {
			  return false;
		  }
	}
	
	public int getValeur()
	{
		return -1; //Chiffre impossible pour les comparaisons
	}
	
}