package fr.utt.lo02.projet.uno.ihm.observer;

import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;


public class UnoController implements Observateur{

	private View v;
	private Partie partie;
	
	public UnoController(View v)
	{
		this.v = v;
		v.setController(this);
	}
	
	public void initialiserPartie()
	{
		this.partie = v.demarrerPartie(partie, this);
		partie.deroulerPartie();
	}
	
	public void notifyManche(Partie partie) {
		v.afficherFinManche(partie);
	}


	public void notifyPartie(Partie partie) {
		v.afficherFinPartie(partie);
	}


	public void notifyTour(Joueur joueur, int choix) {
		v.afficherTour(joueur, choix);
	}


	public int askCarte(Joueur joueur) {
		
		return v.demanderCarteAJouer(joueur);
		
	}


	public int askPoserCarte(Joueur joueur) {
		
		return v.poserCartePioche(joueur);
	}


	public void notifyPioche(Joueur joueur) {
		v.afficherCartePioche(joueur);
	}


	public int askBluff(Partie partie) {
		
		return v.demanderBluff(partie);
		
	}


	public void notifyError() {
		
		v.afficherMauvaisChoix();
		
	}


	public ECouleur askCouleur() {
		
		return v.demanderCouleur();
		
	}

	@Override
	public void declarerUno() {
		Joueur joueur = v.quiDemandeUno(partie);
		if(joueur != null)
			joueur.direUno();
	}

	public int askUno() {
		return 1;
	}


	public void declarerContreUno()
	{
		Joueur joueur = v.quiDemandeContreUno(partie);
		if(!joueur.equals(null))
			joueur.direContreUno(partie);
		
	}
	
	public int askContreUno(Partie partie) {
		return v.demanderContreUno(partie);
	}


	public String askPseudo() {
		return v.demanderPseudo();
	}

	

	public int askNombre(int min, int max) {
		
		return v.demanderChoix(min, max);
	}

	
	public void notifyPasse(Joueur joueur) {
		v.afficherPasse(joueur);
	}

	



	

}
