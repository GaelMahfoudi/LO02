package fr.utt.lo02.projet.uno.noyau.gestion.joueur;

import fr.utt.lo02.projet.uno.noyau.carte.ESpecial;

/**
 *  JoueurNormal hérite de joueur. Elle gère un joueur humain.
 */
public class JoueurNormal extends Joueur {
	/* {author=Victor Le Deuff Gaël Mahfoudi}*/

	
	public JoueurNormal() {
		super();
	}
	
	public ESpecial jouer() {
		return null;
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
