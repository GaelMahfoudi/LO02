package lo02.projet.uno.noyau.gestion.joueur;

import lo02.projet.uno.noyau.carte.ESpecial;
import lo02.projet.uno.noyau.gestion.partie.Partie;

/**
 *  IA h�rite de joueur. Elle g�re un joueur non-humain
 */
public class IA extends Joueur {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/


	private StyleIA styleJeu;

	public void jouer(Partie partie) {
		
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

	public boolean direContreUno() {
		return false;
	}

	@Override
	public boolean direContreUno(Joueur j) {
		// TODO Auto-generated method stub
		return false;
	}



}
