package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import java.nio.Buffer;
import java.util.InputMismatchException;
import java.util.Scanner;

import fr.utt.lo02.projet.uno.noyau.carte.Carte;
import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.carte.ESpecial;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Pioche;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 *  JoueurNormal h�rite de joueur. Elle g�re un joueur humain.
 */
public class JoueurNormal extends Joueur {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/
	
	private Scanner sc;
	
	public JoueurNormal() {
		super();
		System.out.println("Saisissez le nom du joueur:");
		sc = new Scanner(System.in);
		this.pseudo = sc.nextLine();
	}

	public void jouer(Partie partie) 
	{
		sc = new Scanner(System.in);
		if(this.uno && this.getNombreCarte() !=0)
			this.uno=false;

		System.out.println(" \n\n\n\n\n\nAu tour de " + this.pseudo+". Appuyez sur Entrée lorsque vous serez pret");
		sc.nextLine();

		
		do
		{
			
			
			System.out.println("Carte du talon: " + Talon.getInstance().getDerniereCarte().toString());
			System.out.println("Votre Main: ");
			this.main.afficherMain();
			System.out.println((this.main.getNombreCarte()+1) + ": Piocher");

			System.out.println((this.main.getNombreCarte()+2) + ": Declarer un Contre Uno");
			System.out.println("Choisissez une carte [1.." + (this.main.getNombreCarte()+2) + "] : ");
			int choix= Partie.getNombre(0, this.main.getNombreCarte()+2);


			choix = choisirCarte();

			if(choix > 0 && choix <= this.main.getNombreCarte()) //s'il a choisit une carte
			{
				Carte carteChoisie = this.main.enleverCarte(choix-1); //Recupere la carte a poser

				if(carteChoisie.estPosable())
				{
					System.out.println("Carte posée");
					Talon.getInstance().ajouterCarte(carteChoisie);
					carteChoisie.appliquerRegle(partie);
					this.direUno();
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
				choix = Partie.getNombre(0,1);
				if(choix == 1)
				{
					if(cartePiochee.estPosable())
					{
						System.out.println("Carte posée");
						Talon.getInstance().ajouterCarte(cartePiochee);
						cartePiochee.appliquerRegle(partie);
						this.direUno();
						return;
					}
				}
				//Si la carte ne peut etre posée ou le joueur ne veut pas
				System.out.println("Vous passez.");
				this.main.ajouterCarte(cartePiochee);
				this.direUno();
				return;
			}
			if ( choix == this.main.getNombreCarte()+2 ) //ContreUno
			{ 
				this.direContreUno(partie);
			}

				
		} while (true);
		
	}
	
	private int choisirCarte()
	{
		int choix = 0;
		
		//Tant que le choix est invalide
		while(choix<=0 || choix>this.main.getNombreCarte()+2)
		{
			System.out.println("Carte du talon: " + Talon.getInstance().getDerniereCarte().toString());
			System.out.println("Votre Main: ");
			this.main.afficherMain();
			System.out.println((this.main.getNombreCarte()+1) + ": Piocher");

			System.out.println((this.main.getNombreCarte()+2) + ": Declarer un Contre Uno");
			System.out.println("Choisissez une carte [1.." + (this.main.getNombreCarte()+2) + "] : ");
			choix= sc.nextInt();
		}
		
		//on retourne le choix
		return choix;
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
				System.out.println(joueur.afficherPseudo() + " a bluffé, il pioche 4 cartes");
				joueur.piocherCarte(Pioche.getInstance(), 4);
				return true;

			}

		}

		
		System.out.println(joueur.afficherPseudo() + " ne bluffait pas, " + this.afficherPseudo() + "pioche 2 cartes.");
		this.piocherCarte(Pioche.getInstance(), 2);
		return false;
	}

	public void direUno() {
		System.out.println("voulez vous déclarer un UNO? [1|0]");

		sc = new Scanner(System.in);
		int choix = Partie.getNombre(0,1);
		if(choix == 0)
		{
			if( this.getNombreCarte() == 0)
				this.uno=true;
			else
				this.uno=false;
		}

		
		
	}

	public void direContreUno(Partie partie) 
	{
		sc = new Scanner(System.in);
		System.out.println("A qui dites vous contre Uno? [1.."+(partie.getNbreJoueur())+"]");
		for(int i = 0; i<partie.getNbreJoueur(); i++)
		{
			System.out.println((i+1) + ":" + partie.getJoueur(i).afficherPseudo() );
		}
		int nJoueur = Partie.getNombre(0, partie.getNbreJoueur())-1;
		direContreUno(partie.getJoueur(nJoueur));
	}

	public void direContreUno(Joueur j) {
		if( j.getNombreCarte() == 1 && !j.uno) 
		{
			System.out.println(j.afficherPseudo()+" pioche 2 cartes");
			j.piocherCarte(Pioche.getInstance(), 2);
		}
		else
		{
			System.out.println("Le contre-Uno n'est pas valide, vous piochez 2 cartes");
			this.piocherCarte(Pioche.getInstance());
			this.piocherCarte(Pioche.getInstance());
		}
	}


	public void choisirCouleur() 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Choisissez la nouvelle couleur [B|R|J|V]:");
		try
		{
			String couleur = sc.nextLine();
			switch(couleur.charAt(0))
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
				default:
					System.out.println("Erreur: veuillez recommencer");
					choisirCouleur();
				break;
			}
		}
		catch(InputMismatchException e)
		{
			System.out.println("Erreur: Veuillez recommencer");
			choisirCouleur();
		}
		
	
	}

}
