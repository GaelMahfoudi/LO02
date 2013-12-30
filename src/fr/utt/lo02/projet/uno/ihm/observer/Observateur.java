package fr.utt.lo02.projet.uno.ihm.observer;

import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;
//Notons que l'observateur doit instancier lui meme partie dans son constructeur
public interface Observateur
{
	public void notifyManche(Partie partie);
	public void notifyPartie(Partie partie);
	public void notifyPasse(Joueur joueur);
	public void notifyTour(Joueur joueur, int choix);
	public int askCarte(Joueur joueur);
	public int askPoserCarte(Joueur joueur);
	public void notifyPioche(Joueur joueur);
	public int askBluff(Partie partie);
	public void notifyError();
	public ECouleur askCouleur();
	public int askUno();
	public void declarerUno();
	public int askContreUno(Partie partie);
	public String askPseudo();
	public int askNombre(int min, int max);
	public void declarerContreUno();

}