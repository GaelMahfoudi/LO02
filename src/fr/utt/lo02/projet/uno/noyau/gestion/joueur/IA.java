package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import java.util.Scanner;

import fr.utt.lo02.projet.uno.noyau.carte.ESpecial;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 *  IA hï¿½rite de joueur. Elle gï¿½re un joueur non-humain
 */
public class IA extends Joueur {
	/* {author=Victor Le Deuff Gaï¿½l Mahfoudi}*/


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
			System.out.println(pseudo + " a joué " + main.getMain().get(i));
			main.getMain().get(i).appliquerRegle(partie);
			partie.getTalon().ajouterCarte(main.enleverCarte(i));
		}
		else
		{
			main.ajouterCarte(partie.getPioche().enleverCarte());
			System.out.println(pseudo + " a pioché");
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
