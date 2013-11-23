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
		if(this.uno && this.getNombreCarte() !=0)
			this.uno=false;
			
		System.out.println("Au tour de " + this.pseudo);
		
		do
		{
			System.out.println("Carte du talon: " + Talon.getInstance().getDerniereCarte().toString());
			System.out.println("Votre Main: ");
			this.main.afficherMain();
			System.out.println((this.main.getNombreCarte()+1) + ": Piocher");

			System.out.println((this.main.getNombreCarte()+2) + ": Declarer un Contre Uno");
			System.out.println("Choisissez une carte [1.." + (this.main.getNombreCarte()+2) + "] : ");
			int choix= sc.nextInt();

			
			if(choix > 0 && choix <= this.main.getNombreCarte()) //s'il a choisit une carte
			{
				Carte carteChoisie = this.main.enleverCarte(choix-1); //Recupere la carte a poser
				
				if(carteChoisie.estPosable())
				{
					System.out.println("Carte posée");
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
			if ( choix == this.main.getNombreCarte()+2 ) //ContreUno
			{ 
				if(!this.direContreUno(partie))
				{
					System.out.println("Le contre-Uno n'est pas valide, vous piochez X cartes"); //TODO
					//Que dois je faire si je me suis trompé? combie de carte?
				}
			}
			
			
		} while (true);
		
	}

	public boolean direBluff(Joueur joueur) 
	{
		Carte carteAComparer = Talon.getInstance().getAvantDerniereCarte();
		for(int i=0; i<this.getNombreCarte(); i++)
		{
			Carte carte = this.main.enleverCarte(i);
			this.main.ajouterCarte(carte);
			if(carte.getCouleur() == carteAComparer.getCouleur()  || carte.getCouleur() == null || carte.getValeur()== carteAComparer.getValeur()) 
			{
				System.out.println(joueur.afficherPseudo() + " a bluffé");
				//TODO si le joueur a bluffé, on le punit
				return true;
				
			}
		
		}
		
		//TODO Si le joueur n'a pas bluffé, on punit le joueur qui l'a denoncé
		System.out.println(joueur.afficherPseudo() + " ne bluffait pas");
		return false;
		
		
			
		
		
	}

	public void direUno() {
		if( this.getNombreCarte() == 0)
			this.uno=true;
		else
			this.uno=false;
	}

	public boolean direContreUno(Partie partie) {
		Scanner sc = new Scanner(System.in);
		System.out.println("A qui dites vous contre Uno? [1.."+(partie.getNbreJoueur())+"]");
		for(int i = 0; i<partie.getNbreJoueur(); i++)
		{
			System.out.println((i+1) + ":" + partie.getJoueur(i).afficherPseudo() );
		}
		 int nJoueur = sc.nextInt()-1;
		return direContreUno(partie.getJoueur(nJoueur));
		
	}
	
	@Override
	public boolean direContreUno(Joueur j) {
		if( j.getNombreCarte() == 1 && !j.uno) //TODO verifier la regle
		{
			System.out.println(j.afficherPseudo()+" pioche 4 cartes");
			j.piocherCarte(Pioche.getInstance());
			j.piocherCarte(Pioche.getInstance());
			j.piocherCarte(Pioche.getInstance());
			j.piocherCarte(Pioche.getInstance());
			return true;
		}
		else
			return false;
	}

	
}
