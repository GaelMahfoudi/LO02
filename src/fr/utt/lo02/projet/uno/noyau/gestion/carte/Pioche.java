package fr.utt.lo02.projet.uno.noyau.gestion.carte;

import java.util.*;

import fr.utt.lo02.projet.uno.noyau.carte.*;

/**
 *  La classe Pioche permet de gérer la pioche du jeu de Uno. Cette classe suit le design pattern Singleton.
 *  Elle n'est instanciée qu'une fois.
 */
public class Pioche {
	
	
	/**
	 * L'attribut listeCarte est la liste des cartes de la pioches.
	 */
	private Queue<Carte> listeCarte;
	
	/**
	 * L'attribut pioche est l'unique instance de la classe pioche.
	 * l'attibut est privé est accessible uniquement via la méthode getInstance
	 * @see Pioche#getInstance()
	 */
	private static Pioche pioche = null;
	
	/**
	 * Constructeur de la classe pioche. Il construit le jeu de carte du Uno.
	 */
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
					listeCarte.add(new CInverse(ECouleur.BLEU));
					listeCarte.add(new CInverse(ECouleur.BLEU));
					break;
				case 1:
					listeCarte.add(new CInverse(ECouleur.JAUNE));
					listeCarte.add(new CInverse(ECouleur.JAUNE));
					break;
				case 2:
					listeCarte.add(new CInverse(ECouleur.ROUGE));
					listeCarte.add(new CInverse(ECouleur.ROUGE));
					break;
				case 3:
					listeCarte.add(new CInverse(ECouleur.VERT));
					listeCarte.add(new CInverse(ECouleur.VERT));
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

	/**
	 * Cette méthode permet d'ajouter une carte à la pioche.
	 * @param carte
	 * 		La carte à ajouter.
	 */
	public void ajouterCarte(Carte carte) {
		listeCarte.add(carte);
	}

	/**
	 * Cette méthode renvoie le nombre de carte de la pioche.
	 * @return
	 * 		Renvoie un entier étant le nombre de carte
	 */
	public int getNombreCarte() {
		return listeCarte.size();
	}

	/**
	 * Cette méthode permet de retirer une carte de la pioche.
	 * @return
	 * 		Renvoie la carte retirée du paquet
	 */
	public Carte enleverCarte() {
		
		if(Pioche.getInstance().getNombreCarte() == 0 )
		{
			Talon.getInstance().viderTalon();
		}
		return listeCarte.poll();
	}

	/**
	 * Cette méthode permet d récupérer l'unique instance de la classe Pioche.
	 * @return
	 * 		Renvoie l'unique instance de la classe Pioche
	 */
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
	
	
	/**
	 * Cette méthode permet de réinitialiser l'instance de la classe Pioche.
	 */
	public static void reinitialiserPioche()
	{
		pioche = null;
		pioche = new Pioche();
	}
}
