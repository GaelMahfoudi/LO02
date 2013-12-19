package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import java.util.Random;

import fr.utt.lo02.projet.uno.ihm.observer.*;
import fr.utt.lo02.projet.uno.noyau.carte.Carte;
import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 *  IA h�rite de joueur. Elle g�re un joueur non-humain
 */
public class IA extends Joueur {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/


	private StyleIA styleJeu;
	
	public IA(Observateur obs)
	{
		super(obs);
		pseudo = generePseudo();
	}
	public void jouer(Partie partie) {
		int i = 0;
		
		if(this.getNombreCarte()>10)
		{
			styleJeu = new IAOffensive();
		}
		else
		{
			styleJeu = new IANormal();
		}
		i = styleJeu.jouerCarte(partie, this);
		
		
		if(i <= main.getNombreCarte())
		{
			Talon.getInstance().ajouterCarte(main.enleverCarte(i));
			Talon.getInstance().getDerniereCarte().appliquerRegle(partie);
			obs.notifyTour(this, i);
		}
		else if(i == main.getNombreCarte()+1)
		{
			this.piocherCarte();
			obs.notifyTour(this, i+1);
		}
		else
		{
			this.direContreUno(partie);
			//TODO notify contre uno
		}
		
	}
	

	public String generePseudo() {
		return "Bernard";
	}
	

	public void setStyle() {
	}

	public void direBluff(Joueur joueur)
	{
		if(joueur != this)
		{
			boolean bluff = styleJeu.direBluff(joueur);
			if(bluff)
			{
				Carte carteAComparer = Talon.getInstance().getAvantDerniereCarte();
				bluff = false;
				for(int i=0; i<this.getNombreCarte(); i++)
				{
					Carte carte = this.main.enleverCarte(i);
					this.main.ajouterCarte(carte);
					if(carte.getCouleur() == carteAComparer.getCouleur()  || carte.getCouleur() == null || carte.getValeur()== carteAComparer.getValeur()) 
						bluff = true; //Il bluff
				}
				if (bluff)
					joueur.piocherCarte(4); //LE joueur bluffait
				else
					this.piocherCarte(6); //Le jouer ne bluffait pas
			}
			else
			{
				this.piocherCarte(4); //Le joueur n'a pas dementi le joueur actuel
			}
			
		this.choisirCouleur();
		}
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
	public void choisirCouleur() { //TODO
		
		Random r = new Random();
		
		int a = 0;
		
		//Tire un nombre entre 0 (inclu) et 4 (exclu)
		a = r.nextInt(4);
		
		//On choisis la bonne couleur
		switch(a)
		{
		case 0:
			Talon.getInstance().setCouleurDerniereCarte(ECouleur.BLEU);
			break;
		case 1:
			Talon.getInstance().setCouleurDerniereCarte(ECouleur.ROUGE);
			break;
		case 2:
			Talon.getInstance().setCouleurDerniereCarte(ECouleur.JAUNE);
			break;
		case 3:
			Talon.getInstance().setCouleurDerniereCarte(ECouleur.VERT);
			break;
		}


	}
	@Override
	public void direContreUno(Joueur j) {

		j.piocherCarte();
		j.piocherCarte();
	}

}
