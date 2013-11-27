package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import java.util.Scanner;

import fr.utt.lo02.projet.uno.noyau.carte.ESpecial;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 *  IA h�rite de joueur. Elle g�re un joueur non-humain
 */
public class IA extends Joueur {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/


	private StyleIA styleJeu;
	private Scanner sc = new Scanner(System.in);
	
	public IA(int style)
	{
		super();
		System.out.println("Saisissez le nom du joueur:");
		this.pseudo = sc.nextLine();
		switch(style)
		{
		case 1:
			styleJeu = new IANormal();
			break;
		case 2:
			styleJeu = new IAOffensive();
		}
	}
	public void jouer(Partie partie) {
		int i = 0;
		
		i = styleJeu.jouerCarte(partie, this);
		
		if(i< main.getNombreCarte())
		{
			System.out.println(pseudo + " a jou� " + main.getMain().get(i));
			main.getMain().get(i).appliquerRegle(partie);
			partie.getTalon().ajouterCarte(main.enleverCarte(i));
		}
		else
		{
			main.ajouterCarte(partie.getPioche().enleverCarte());
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
	public boolean direContreUno(Joueur j) {
		// TODO Auto-generated method stub
		return false;
	}



}
