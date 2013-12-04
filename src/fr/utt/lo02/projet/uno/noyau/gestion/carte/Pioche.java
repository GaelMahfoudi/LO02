package fr.utt.lo02.projet.uno.noyau.gestion.carte;

import java.util.*;

import fr.utt.lo02.projet.uno.noyau.carte.*;

/**
 *  Pioche h�rite de PaquetDeCarte. Elle repr�sente la pioche. Elle n'est instanci�e qu'une fois.
 */
public class Pioche {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/
	
	private Queue<Carte> listeCarte;
	
	private static Pioche pioche = null;
	
	@SuppressWarnings("unchecked")
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
					listeCarte.add(new CPlusDeux(ECouleur.BLEU));
					listeCarte.add(new CPlusDeux(ECouleur.BLEU));
					break;
				case 1:
					listeCarte.add(new CPlusDeux(ECouleur.JAUNE));
					listeCarte.add(new CPlusDeux(ECouleur.JAUNE));
					break;
				case 2:
					listeCarte.add(new CPlusDeux(ECouleur.ROUGE));
					listeCarte.add(new CPlusDeux(ECouleur.ROUGE));
					break;
				case 3:
					listeCarte.add(new CPlusDeux(ECouleur.VERT));
					listeCarte.add(new CPlusDeux(ECouleur.VERT));
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
					listeCarte.add(new CPasse(ECouleur.BLEU));
					listeCarte.add(new CPasse(ECouleur.BLEU));
					break;
				case 1:
					listeCarte.add(new CPasse(ECouleur.JAUNE));
					listeCarte.add(new CPasse(ECouleur.JAUNE));
					break;
				case 2:
					listeCarte.add(new CPasse(ECouleur.ROUGE));
					listeCarte.add(new CPasse(ECouleur.ROUGE));
					break;
				case 3:
					listeCarte.add(new CPasse(ECouleur.VERT));
					listeCarte.add(new CPasse(ECouleur.VERT));
					break;
			}
		}
		
		for(i=0;i<4;i++)
		{
			listeCarte.add(new CJoker());
		}
		
		for(i=0;i<4;i++)
		{
			listeCarte.add(new CPlusQuatre());
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
		
		if(Pioche.getInstance().getNombreCarte() == 0 )
		{
			Talon.getInstance().viderTalon(pioche);
		}
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
	
	public void reinitialiserPioche()
	{
		Pioche.pioche = null;
		Pioche.pioche = Pioche.getInstance();
	}
}
