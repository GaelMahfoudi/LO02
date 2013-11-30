package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import java.util.Scanner;

import fr.utt.lo02.projet.uno.noyau.carte.ESpecial;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Pioche;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
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
			Talon.getInstance().ajouterCarte(main.enleverCarte(i));
		}
		else if(i == main.getNombreCarte()+1)
		{
			main.ajouterCarte(Pioche.getInstance().enleverCarte());
			System.out.println(pseudo + " a pioch�");
		}
		else
		{
			this.direContreUno(partie);
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

	public void direContreUno(Partie partie) {
		for(int i=0; i<partie.getNbreJoueur(); i++)
		{
			if(partie.getJoueur(i).getNombreCarte()==0)
			{
				direContreUno(partie.getJoueur(i));
				return;
			}
		}
	}

	@Override
	public void direContreUno(Joueur j) {

		j.piocherCarte(Pioche.getInstance());
		j.piocherCarte(Pioche.getInstance());
	}

	public void choisirCouleur()
	{
		//TODO
	}


}
