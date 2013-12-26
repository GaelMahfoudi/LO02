package fr.utt.lo02.projet.uno.ihm.observer;

import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public interface View {
	
	public void afficherFinManche(Partie p);
	public void afficherFinPartie(Partie p);
	public void afficherTour(Joueur joueur, int choix);
	public int demanderCarteAJouer(Joueur joueur);
	public int poserCartePioche(Joueur joueur);
	public void afficherCartePioche(Joueur joueur);
	public int demanderBluff(Partie partie);
	public void afficherMauvaisChoix();
	public ECouleur demanderCouleur();
	public int demanderUno();
	public int demanderContreUno();
	public String demanderPseudo();
	public int demanderChoix(int min, int max);
	public Partie demarrerPartie(Partie partie, UnoController controller);
}
