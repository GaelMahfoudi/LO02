package lo02.projet.uno.noyau.carte;


import java.util.Scanner;

import lo02.projet.uno.noyau.gestion.carte.Pioche;
import lo02.projet.uno.noyau.gestion.carte.Talon;
import lo02.projet.uno.noyau.gestion.joueur.Joueur;
import lo02.projet.uno.noyau.gestion.partie.Partie;

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
				
				Joueur joueurNext = partie.getJoueur((partie.getJoueurActuel()+partie.getSens())%partie.getNbreJoueur());
				joueurNext.piocherCarte(Pioche.getInstance());
				joueurNext.piocherCarte(Pioche.getInstance());
				
				break;
			case PLUS_QUATRE:
				break;
			case JOKER:		
				System.out.println("Choisissez la nouvelle couleur [B|R|J|V]:");
				switch(sc.nextLine().charAt(0))
				{
					case 'B':
						Talon.getInstance().setCouleurDerniereCarte(ECouleur.BLEU);
					break;
					case 'R':
						Talon.getInstance().setCouleurDerniereCarte(ECouleur.ROUGE);
					break;
					case 'J':
						Talon.getInstance().setCouleurDerniereCarte(ECouleur.JAUNE);
					break;
					case 'V':
						Talon.getInstance().setCouleurDerniereCarte(ECouleur.VERT);
					break;
				}
				break;
		}
			
	}
	
	
	@Override
	public String toString() {
		return "CSpecial [special=" + this.special + ", couleur=" + this.couleur + "]";
	}
	
	public boolean estPosable() 
	  {
		  if(couleur == Talon.getInstance().getDerniereCarte().getCouleur() || couleur == null)
			  return true;
		  else
			  return false;
	  }
	
	public int getValeur()
	{
		return -1; //Chiffre impossible pour les comparaisons
	}
	
}