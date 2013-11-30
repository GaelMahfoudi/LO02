package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import java.util.Random;
import java.util.Scanner;

import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.carte.ESpecial;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 *  IA h�rite de joueur. Elle g�re un joueur non-humain
 */
public class IA extends Joueur {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/


	private StyleIA styleJeu;
	private Scanner sc;
	
	public IA()
	{
		super();
		System.out.println("Saisissez le nom du joueur:");
		sc = new Scanner(System.in);
		this.pseudo = sc.nextLine();
	}
	public void jouer(Partie partie) {
		int i = 0;
		
		if(this.getNombreCarte()>10)
		{
			styleJeu = new IAOffensive();
			i = styleJeu.jouerCarte(partie, this);
		}
		else
		{
			styleJeu = new IANormal();
			i = styleJeu.jouerCarte(partie, this);
		}
		
		
		if(i< main.getNombreCarte())
		{
			System.out.println(pseudo + " a jou� " + main.getMain().get(i));
			partie.getTalon().ajouterCarte(main.enleverCarte(i));
			partie.getTalon().getDerniereCarte().appliquerRegle(partie);
		}
		else
		{
			this.piocherCarte(partie.getPioche());
			System.out.println(pseudo + " a pioch�");
		}
	}

	public void generePseudo() {
	}
	

	public void setStyle() {
	}

	public boolean direBluff(Joueur joueur)
	{
		return false;
	}

	public void direUno() {
	}

	public boolean direContreUno() {
		return false;
	}

	
	@Override
	public void choisirCouleur() {
		
		Random r = new Random();
		
		int a = 0;
		
		//Tire un nombre entre 0 (inclu) et 4 (exclu)
		a = r.nextInt(4);
		
		//On choisis la bonne couleur
		switch(a)
		{
		case 0:
			Talon.getInstance().setCouleurDerniereCarte(ECouleur.BLEU);
			System.out.println(this.pseudo + " a choisi la couleur bleu");
			break;
		case 1:
			Talon.getInstance().setCouleurDerniereCarte(ECouleur.ROUGE);
			System.out.println(this.pseudo + " a choisi la couleur rouge");
			break;
		case 2:
			Talon.getInstance().setCouleurDerniereCarte(ECouleur.JAUNE);
			System.out.println(this.pseudo + " a choisi la couleur jaune");
			break;
		case 3:
			Talon.getInstance().setCouleurDerniereCarte(ECouleur.VERT);
			System.out.println(this.pseudo + " a choisi la couleur vert");
			break;
		}


	}
	@Override
	public boolean direContreUno(Joueur j) {
		// TODO Auto-generated method stub
		return false;
	}



}
