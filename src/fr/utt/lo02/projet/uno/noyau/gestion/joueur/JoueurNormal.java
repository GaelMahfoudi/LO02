package lo02.projet.uno.noyau.gestion.joueur;

import java.util.Scanner;

import lo02.projet.uno.noyau.carte.Carte;
import lo02.projet.uno.noyau.carte.ESpecial;
import lo02.projet.uno.noyau.gestion.carte.Pioche;
import lo02.projet.uno.noyau.gestion.carte.Talon;
import lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 *  JoueurNormal h�rite de joueur. Elle g�re un joueur humain.
 */
public class JoueurNormal extends Joueur {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/
	Scanner sc = new Scanner(System.in);
	
	public JoueurNormal() {
		super();
		System.out.println("Saisissez le nom du joueur:");
		this.pseudo = sc.nextLine();
		
	}
	
	public void jouer(Partie partie) {
		System.out.println("Au tour de " + this.pseudo);
		
		do
		{
			System.out.println("Carte du talon: " + Talon.getInstance().getDerniereCarte().toString());
			System.out.println("Votre Main: ");
			this.main.afficherMain();
			System.out.println((this.main.getNombreCarte()+1) + ": Piocher");
			System.out.println("Choisissez une carte [1.." + (this.main.getNombreCarte()+1) + "] : ");
			int choix= sc.nextInt();

			
			if(choix > 0 && choix <= this.main.getNombreCarte()) //s'il a choisit une carte
			{
				Carte carteChoisie = this.main.enleverCarte(choix-1); //Recupere la carte a poser
				
				if(carteChoisie.estPosable())
				{
					Talon.getInstance().ajouterCarte(carteChoisie);
					carteChoisie.appliquerRegle(partie);
					return;
				}
				else
				{
					System.out.println("Cette carte ne peut etre posée, choisissez en une autre ou passez...");
					this.main.ajouterCarte(carteChoisie); //Le joueur recupere sa carte
				}
				
			}
			else if (choix == this.main.getNombreCarte()+1) //S'il a choisit de piocher
			{
				this.piocherCarte(Pioche.getInstance());
				Carte cartePiochee = this.main.enleverCarte(this.main.getNombreCarte()-1);
				System.out.println("Vous avez pioché: ");
				System.out.println(cartePiochee.toString());
				System.out.println("Voulez vous poser cette carte [1|0]");
				choix = sc.nextInt();
				if(choix == 1)
				{
					if(cartePiochee.estPosable())
					{
						System.out.println("Carte posée");
						Talon.getInstance().ajouterCarte(cartePiochee);
						cartePiochee.appliquerRegle(partie);
						return;
					}
				}
				//Si la carte ne peut etre posée ou le joueur ne veut pas
				System.out.println("Vous passez.");
				this.main.ajouterCarte(cartePiochee);
				return;
			}
			
			
		} while (true);
		
	}

	public void direBluff() {
		
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
