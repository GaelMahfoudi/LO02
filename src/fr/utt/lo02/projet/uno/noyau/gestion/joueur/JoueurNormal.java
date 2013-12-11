package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import java.util.Scanner;

import fr.utt.lo02.projet.uno.noyau.carte.Carte;
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
		if(this.uno && this.getNombreCarte() != 0)
			this.uno=false;
		
		int choix;
		
		do
		{	
			choix = obs.choisirCarte(this);
			
			if(choix >= 0 && choix < this.main.getNombreCarte()) //s'il a choisit une carte
			{
				Carte carteChoisie = this.main.enleverCarte(choix); //Recupere la carte a poser

				if(carteChoisie.estPosable())
				{
					Talon.getInstance().ajouterCarte(carteChoisie);
					carteChoisie.appliquerRegle(partie);
					obs.updateJoueur(this, choix);
					this.direUno();
					return;
				}
				else
				{
					this.main.ajouterCarte(carteChoisie); //Le joueur recupere sa carte
					obs.notifyError();
				}

			}
			else if (choix == this.main.getNombreCarte()) //S'il a choisit de piocher
			{
				this.piocherCarte();
				int choix2 = obs.upgradePasse(this);
				Carte cartePiochee = this.main.enleverCarte(this.main.getNombreCarte()-1);
				
				if(choix2 == 1)
				{
					if( cartePiochee.estPosable() )
					{
						Talon.getInstance().ajouterCarte(cartePiochee);
						cartePiochee.appliquerRegle(partie);
						obs.updateJoueur(this, choix-1);
						this.direUno();
						return;
					}
					else
						obs.notifyError();
				}
				//Si la carte ne peut etre posée ou le joueur ne veut pas
				this.main.ajouterCarte(cartePiochee);
				this.direUno();
				obs.updateJoueur(this, choix+1);
				return;
			}
			else if ( choix == this.main.getNombreCarte()+2 ) //ContreUno
			{ 
				this.direContreUno(partie);
				//TODO notify contre uno
			}

				
		} while (true);
		
	}
	
	
	

	

	public void direBluff(Joueur joueur) 
	{		
		Carte carteAComparer = Talon.getInstance().getAvantDerniereCarte();
		int choix = this.obs.upgradeBluff();
		if (choix == 0)
		{
			this.piocherCarte(4);
		}
		else
		{
			boolean bluff = false;
			for(int i=0; i<this.getNombreCarte(); i++)
			{
				Carte carte = this.main.enleverCarte(i);
				this.main.ajouterCarte(carte);
				if(carte.getCouleur() == carteAComparer.getCouleur()  || carte.getCouleur() == null || carte.getValeur()== carteAComparer.getValeur()) 
				{
					bluff = true; //Il bluff
				}
			}
			if (bluff)
			{
				joueur.piocherCarte(4);
			}
			else
			{
				this.piocherCarte(6);
			}
		}
	}

	public void direUno() {
		
		int choix = obs.choisirUno();
		if(choix == 1)
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
			j.piocherCarte(2);
		}
		else
		{
			System.out.println("Le contre-Uno n'est pas valide, vous piochez 2 cartes");
			this.piocherCarte();
			this.piocherCarte();
		}
	}


	public void choisirCouleur() 
	{
		Talon.getInstance().setCouleurDerniereCarte(obs.choisirCouleur());
	}

}
