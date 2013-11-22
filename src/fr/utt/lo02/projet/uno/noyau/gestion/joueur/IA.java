package lo02.projet.uno.noyau.gestion.joueur;

import lo02.projet.uno.noyau.carte.ESpecial;

/**
 *  IA h�rite de joueur. Elle g�re un joueur non-humain
 */
public class IA extends Joueur {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/


	private StyleIA styleJeu;

	public ESpecial jouer() {
		return null;
	}

	public void generePseudo() {
	}

	public void setStyle() {
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
