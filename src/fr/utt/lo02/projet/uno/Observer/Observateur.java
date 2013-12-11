package Observer;

import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public interface Observateur {
	  public void updateManche(Partie partie);
	  public void updatePartie(Partie partie);
	  public void updateJoueur(Joueur joueur, int choix);
	  public int choisirCarte(Joueur joueur);
	  public int upgradePasse(Joueur joueur);
	  public int upgradeBluff();
	  public void notifyError();
	  public ECouleur choisirCouleur();
	  public int choisirUno();
}