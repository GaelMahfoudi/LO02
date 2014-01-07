package fr.utt.lo02.projet.uno.noyau.gestion.joueur;


import fr.utt.lo02.projet.uno.ihm.observer.*;
import fr.utt.lo02.projet.uno.noyau.carte.Carte;
import fr.utt.lo02.projet.uno.noyau.carte.ESpecial;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 *  JoueurNormal h�rite de joueur. Elle g�re un joueur humain.
 */
public class JoueurNormal extends Joueur {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/
	
	/**
	 * Constructeur d'un JoueurNormal. Il prend en param�tre l'obsevateur du joueur.
	 * @param obs
	 * 		L'observateur du joueur
	 * @see Joueur
	 * @see Observateur
	 */
	public JoueurNormal(Observateur obs) {
		super(obs);
		this.pseudo = obs.askPseudo();	
	}

	/**
	 * Impl�mentation de la m�thode abstraite jouer de joueur. Cette m�thode
	 * d�finit la fa�on dont le joueur joue. Elle prend en param�tre la partie de Uno
	 * ou se trouve le joueur
	 * @param partie
	 * 		La partie de uno
	 * @see Joueur#jouer(Partie)
	 */
	public void jouer(Partie partie) 
	{
		main.trierMain();
		
		if(this.uno && this.getNombreCarte() != 0)
			this.uno=false;
		
		int choix;
		
		do
		{	
			choix = obs.askCarte(this);
			
			if(choix >= 0 && choix < this.main.getNombreCarte()) //s'il a choisit une carte
			{
				Carte carteChoisie = this.main.enleverCarte(choix); //Recupere la carte a poser

				if(carteChoisie.estPosable())
				{
					Talon.getInstance().ajouterCarte(carteChoisie);
					carteChoisie.appliquerRegle(partie);
					if(carteChoisie.getSpecial() == ESpecial.JOKER && carteChoisie.getSpecial() == ESpecial.PLUS_QUATRE)
					{
						obs.askCouleur();
					}
					else if(Talon.getInstance().getDerniereCarte().getSpecial() == ESpecial.PASSE)
					{
						obs.notifyPasse(partie.getJoueur(partie.getJoueurActuel()));
					}
						
					obs.notifyTour(this, choix);
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
				int choix2 = obs.askPoserCarte(this);
				Carte cartePiochee = this.main.enleverCarte(this.main.getNombreCarte()-1);
				
				if(choix2 == 1)
				{
					if( cartePiochee.estPosable() )
					{
						Talon.getInstance().ajouterCarte(cartePiochee);
						cartePiochee.appliquerRegle(partie);
						if(cartePiochee.getSpecial() == ESpecial.JOKER && cartePiochee.getSpecial() == ESpecial.PLUS_QUATRE)
							obs.askCouleur();
						obs.notifyTour(this, choix-1);
						this.direUno();
						return;
					}
					else
					{
						obs.notifyError();
					}
						
				}
				//Si la carte ne peut etre posée ou le joueur ne veut pas
				this.main.ajouterCarte(cartePiochee);
				this.direUno();
				obs.notifyTour(this, this.getNombreCarte()+1);
				return;
			}
			else if ( choix == this.main.getNombreCarte()+1 ) //ContreUno
			{ 
				this.direContreUno(partie);
				//TODO notify contre uno
			}

				
		} while (true);
		
	}
	
	
	

	

	/**
	 * Impl�mentation de la m�thode direBluff de la classe Joueur. Cette m�thode
	 * d�finit la fa�on dont le joueur d�clare un bluf. Elle prend en param�tre la partie de Uno
	 * et le joueur � qui l'on d�clare le bluff.
	 * @param joueur
	 * 		Le joueur accus� de bluff
	 * @param partie 
	 * 		La partie de Uno
	 * @see Joueur#direBluff(Joueur, Partie)
	 */
	public void direBluff(Joueur joueur, Partie partie) 
	{		
		Carte carteAComparer = Talon.getInstance().getAvantDerniereCarte();
		int choix = this.obs.askBluff(partie);
		if (choix == 0)
		{
			this.piocherCarte(4);
		}
		else
		{
			boolean bluff = false;
			for(int i=0; i<joueur.getNombreCarte(); i++)
			{
				Carte carte = joueur.main.enleverCarte(i);
				joueur.main.ajouterCarte(carte);
				if(carte.getCouleur() == carteAComparer.getCouleur()  || carte.getSpecial() == ESpecial.JOKER || carte.getValeur()== carteAComparer.getValeur()) 
				{
					bluff = true; //Il bluff
				}
			}
			if (bluff)
			{
				joueur.piocherCarte(4);
				joueur.main.ajouterCarte(Talon.getInstance().enleverDerniereCarte());
				obs.notifyPasse(this);
				
			}
			else
			{
				this.piocherCarte(6);
			}
		}
	}

	/**
	 * Impl�mentation de la m�thode direUno de la classe Joueur.
	 * Cette m�thode d�finit la fa�on dont un joueur dit Uno.
	 * @see Joueur#direUno()
	 */
	public void direUno() {
		
		int choix = obs.askUno();
		if(choix == 1)
		{
			if( this.getNombreCarte() == 1)
				this.uno=true;
			else
				this.uno=false;
		}
	}

	/**
	 * Impl�mentation de la m�thode direContreUno de la classe Joueur.
	 * Cette m�thode prend en param�tre la partie de Uno.
	 * @param partie
	 * 		La partie de Uno
	 * @see Joueur#direContreUno(Partie)
	 */
	public void direContreUno(Partie partie) 
	{
		
		int nJoueur = obs.askContreUno(partie)-1;
		if(nJoueur < partie.getNbreJoueur() && this != partie.getJoueur(nJoueur) )
		{
			direContreUno(partie.getJoueur(nJoueur));
		}
	}

	/**
	 * Impl�mentation de la m�thode direContreUno de la classe Joueur.
	 * Cette m�thode prend en param�tre le joueur accus�.
	 * @param j
	 * 		Le joueur accus�
	 * @see Joueur#direContreUno(Joueur)
	 */
	public void direContreUno(Joueur j) {
		
		if( j.getNombreCarte() != 1 && j.getUno() == true) 
		{
			j.piocherCarte(2);
		}
		else
		{
			this.piocherCarte(2);
		}
	}

	/**
	 * Cette m�thode demande au joueur de choisir la nouvelle couleur.
	 * @see Joueur#choisirCouleur()
	 */
	public void choisirCouleur() 
	{
		Talon.getInstance().setCouleurDerniereCarte(obs.askCouleur());
	}

}
