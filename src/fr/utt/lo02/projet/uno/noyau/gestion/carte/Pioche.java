package fr.utt.lo02.projet.uno.noyau.gestion.carte;

import java.util.*;

import fr.utt.lo02.projet.uno.noyau.carte.*;

/**
 *  Pioche hérite de PaquetDeCarte. Elle représente la pioche. Elle n'est instanciée qu'une fois.
 */
public class Pioche {
	/* {author=Victor Le Deuff Gaël Mahfoudi}*/
	
	private Queue<Carte> listeCarte;
	
	private static Pioche pioche = null;
	
	private Pioche(){
		int i = 0;
		
		listeCarte = new LinkedList<Carte>();
		listeCarte.add(new CClassique(ECouleur.ROUGE, 0));
	
		
		for(i=1;i<10;i++)
		{
			listeCarte.add(new CClassique(ECouleur.ROUGE, i));
			listeCarte.add(new CClassique(ECouleur.ROUGE, i));
		}
		
		listeCarte.add(new CClassique(ECouleur.BLEU, 0));
		
		for(i=1;i<10;i++)
		{
			listeCarte.add(new CClassique(ECouleur.BLEU, i));
			listeCarte.add(new CClassique(ECouleur.BLEU, i));
		}
		
		listeCarte.add(new CClassique(ECouleur.JAUNE, 0));
		
		for(i=1;i<10;i++)
		{
			listeCarte.add(new CClassique(ECouleur.JAUNE, i));
			listeCarte.add(new CClassique(ECouleur.JAUNE, i));
		}
		
		listeCarte.add(new CClassique(ECouleur.VERT, 0));
		
		for(i=1;i<10;i++)
		{
			listeCarte.add(new CClassique(ECouleur.VERT, i));
			listeCarte.add(new CClassique(ECouleur.VERT, i));
		}
		
		for(i=0;i<4;i++)
		{
			switch(i){
				case 0:
					listeCarte.add(new CSpecial(ESpecial.PLUS_DEUX, ECouleur.BLEU));
					listeCarte.add(new CSpecial(ESpecial.PLUS_DEUX, ECouleur.BLEU));
					break;
				case 1:
					listeCarte.add(new CSpecial(ESpecial.PLUS_DEUX, ECouleur.JAUNE));
					listeCarte.add(new CSpecial(ESpecial.PLUS_DEUX, ECouleur.JAUNE));
					break;
				case 2:
					listeCarte.add(new CSpecial(ESpecial.PLUS_DEUX, ECouleur.ROUGE));
					listeCarte.add(new CSpecial(ESpecial.PLUS_DEUX, ECouleur.ROUGE));
					break;
				case 3:
					listeCarte.add(new CSpecial(ESpecial.PLUS_DEUX, ECouleur.VERT));
					listeCarte.add(new CSpecial(ESpecial.PLUS_DEUX, ECouleur.VERT));
					break;
			}
		}
		
		for(i=0;i<4;i++)
		{
			switch(i){
				case 0:
					listeCarte.add(new CSpecial(ESpecial.INVERSE, ECouleur.BLEU));
					listeCarte.add(new CSpecial(ESpecial.INVERSE, ECouleur.BLEU));
					break;
				case 1:
					listeCarte.add(new CSpecial(ESpecial.INVERSE, ECouleur.JAUNE));
					listeCarte.add(new CSpecial(ESpecial.INVERSE, ECouleur.JAUNE));
					break;
				case 2:
					listeCarte.add(new CSpecial(ESpecial.INVERSE, ECouleur.ROUGE));
					listeCarte.add(new CSpecial(ESpecial.INVERSE, ECouleur.ROUGE));
					break;
				case 3:
					listeCarte.add(new CSpecial(ESpecial.INVERSE, ECouleur.VERT));
					listeCarte.add(new CSpecial(ESpecial.INVERSE, ECouleur.VERT));
					break;
			}
		}
		
		for(i=0;i<4;i++)
		{
			switch(i){
				case 0:
					listeCarte.add(new CSpecial(ESpecial.PASSE, ECouleur.BLEU));
					listeCarte.add(new CSpecial(ESpecial.PASSE, ECouleur.BLEU));
					break;
				case 1:
					listeCarte.add(new CSpecial(ESpecial.PASSE, ECouleur.JAUNE));
					listeCarte.add(new CSpecial(ESpecial.PASSE, ECouleur.JAUNE));
					break;
				case 2:
					listeCarte.add(new CSpecial(ESpecial.PASSE, ECouleur.ROUGE));
					listeCarte.add(new CSpecial(ESpecial.PASSE, ECouleur.ROUGE));
					break;
				case 3:
					listeCarte.add(new CSpecial(ESpecial.PASSE, ECouleur.VERT));
					listeCarte.add(new CSpecial(ESpecial.PASSE, ECouleur.VERT));
					break;
			}
		}
		
		for(i=0;i<4;i++)
		{
			listeCarte.add(new CSpecial(ESpecial.JOKER, ECouleur.INCOLORE));
		}
		
		for(i=0;i<4;i++)
		{
			listeCarte.add(new CSpecial(ESpecial.PLUS_QUATRE, ECouleur.INCOLORE));
		}
		
		
		Collections.shuffle((List<Carte>) listeCarte);
	}

	public void ajouterCarte(Carte carte) {
		listeCarte.add(carte);
	}

	public int getNombreCarte() {
		return listeCarte.size();
	}

	public Carte enleverCarte() {
		return listeCarte.poll();
	}

	public static Pioche getInstance() {
		if(Pioche.pioche == null)
		{
			pioche = new Pioche();
			return pioche;
		}
		else
		{
			return pioche;
		}
	}
	
	public void afficherPioche()
	{
		for (Iterator<Carte> i = listeCarte.iterator(); i.hasNext();) {
	      Carte carte = (Carte) i.next();
	      System.out.println(carte);
		}
	}

}
