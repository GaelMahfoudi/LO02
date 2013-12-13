package fr.utt.lo02.projet.uno.noyau.gestion.joueur;


import Observer.Observateur;
import fr.utt.lo02.projet.uno.noyau.carte.Carte;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 *  JoueurNormal h�rite de joueur. Elle g�re un joueur humain.
 */
public class JoueurNormal extends Joueur {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/
	
	
	public JoueurNormal(Observateur obs) {
		super(obs);
		this.pseudo = obs.askPseudo();
		
		
		
	}

	public void jouer(Partie partie) 
	{
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
						obs.notifyTour(this, choix-1);
						this.direUno();
						return;
					}
					else
						obs.notifyError();
				}
				//Si la carte ne peut etre posée ou le joueur ne veut pas
				this.main.ajouterCarte(cartePiochee);
				this.direUno();
				obs.notifyTour(this, choix+1);
				return;
			}
			else if ( choix == this.main.getNombreCarte()+1 ) //ContreUno
			{ 
				this.direContreUno(partie);
				//TODO notify contre uno
			}

				
		} while (true);
		
	}
	
	
	

	

	public void direBluff(Joueur joueur) 
	{		
		Carte carteAComparer = Talon.getInstance().getAvantDerniereCarte();
		int choix = this.obs.askBluff();
		if (choix == 0)
		{
			this.piocherCarte(4);
			this.choisirCouleur();
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
				joueur.main.ajouterCarte(Talon.getInstance().enleverDerniereCarte());
				
			}
			else
			{
				this.piocherCarte(6);
				this.choisirCouleur();
			}
		}
	}

	public void direUno() {
		
		int choix = obs.askUno();
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
		
		int nJoueur = obs.askContreUno()-1;
		if(nJoueur < partie.getNbreJoueur() && this != partie.getJoueur(nJoueur) )
		{
			direContreUno(partie.getJoueur(nJoueur));
		}
	}

	public void direContreUno(Joueur j) {
		
		if( j.getNombreCarte() == 1 && !j.uno) 
		{
			j.piocherCarte(2);
		}
		else
		{
			this.piocherCarte(2);
		}
	}


	public void choisirCouleur() 
	{
		Talon.getInstance().setCouleurDerniereCarte(obs.askCouleur());
	}

}
