package fr.utt.lo02.projet.uno.noyau.carte;


import java.util.Scanner;

import fr.utt.lo02.projet.uno.noyau.gestion.carte.Pioche;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

/** 
 *  CSpecial h�rite de Carte. CSpecial rep�sente l'ensemble des cartes sp�ciales.
 */
public abstract class CSpecial extends Carte {
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/


	protected ESpecial special;

	public CSpecial(ESpecial special, ECouleur couleur)
	{
		this.couleur=couleur;
		this.special=special;
	}

	public ESpecial getSpecial() {
		return this.special;
	}

	public abstract void appliquerRegle(Partie partie);

	public abstract boolean estPosable();

	public int getValeur()
	{
		return -1; //Chiffre impossible pour les comparaisons
	}

}