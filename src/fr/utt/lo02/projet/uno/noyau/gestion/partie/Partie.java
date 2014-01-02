package fr.utt.lo02.projet.uno.noyau.gestion.partie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.utt.lo02.projet.uno.ihm.observer.*;
import fr.utt.lo02.projet.uno.ihm.graphique.ModeGraphique;
import fr.utt.lo02.projet.uno.noyau.carte.Carte;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Pioche;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.*;

/**
 *  Partie est la classe g�rant une partie de Uno. Elle g�re les joueurs, les paquets de cartes et les tours de jeu.
 */
public class Partie implements Observer{
	/* {author=Victor Le Deuff Ga�l Mahfoudi}*/

	/**
	 * Valeur de la manche actuel
	 * @see Partie#getManche()
	 */
	private int manche;

	/**
	 * Entier g�rant le sens de rotation d'une partie
	 * @see Partie#getSens()
	 * @see Partie#setSens()
	 */
	private int sens;

	/**
	 * Entier repr�sentant le nombre de joueur total
	 * @see Partie#getNbreJoueur()
	 * @see Partie#setNbreJoueur(int)
	 */
	private int nbreJoueur;
	
	/**
	 * Entier repr�sentant le nombre de joueur r�el
	 * @see Partie#getNbreJoueurReel()
	 */
	private int nbreJoueurReel;
	
	/**
	 * Entier repr�sentant le nombre de joueur virtuel
	 */
	@SuppressWarnings("unused")
	private int nbreJoueurIA;

	/**
	 * Entier rep�sentant l'indice du joueur actuel
	 * @see Partie#getJoueurActuel()
	 * @see Partie#setJoueurActuel(int)
	 */
	private int joueurActuel;

	/**
	 * La liste des joueurs de la partie
	 * @see Partie#getListeJoueur()
	 */
	private ArrayList<Joueur> listeJoueur;

	/**
	 * L'observateur de la partie
	 * @see Partie#addObservateur(Observateur)
	 * @see Partie#getObservateur()
	 */
	private Observateur obs;



	
	/**
	 * Constructeur d'une partie de uno.
	 * Il prend en param�tre le nombre de joueurs r�els, Le nombre de 
	 * joueurs virtuels ainsi que le UnoController de la partie.
	 * @param nbJoueur
	 * 			C'est le nombre de joueur r�el
	 * @param nbIA
	 * 			C'est le nombre de joueur virtuel
	 * @param obs
	 * 			C'est le UnoController qui observe la partie
	 * 
	 * @see UnoController
	 */
	public Partie(int nbJoueur, int nbIA, Observateur obs)
	{
		sens=1;
		manche = 0;
		joueurActuel=0;
		listeJoueur = new ArrayList<Joueur>();
		this.nbreJoueur = nbJoueur + nbIA;
		this.nbreJoueurIA = nbIA;
		this.nbreJoueurReel = nbJoueur;
		this.addObservateur(obs);//ajoute l'observeur dés le debut
		//generation des joueurs normaux
		for(int i=0;i<nbJoueur;i++)
		{
			listeJoueur.add(new JoueurNormal(obs));
		}

		//Generation des IAS
		for(int i=0; i<nbIA; i++)
		{
			listeJoueur.add(new IA(obs));
		}
		//On melange les joueurs
		Collections.shuffle((List<Joueur>) listeJoueur);
	}
	
	
	/**
	 * Cette m�thode distribue les cartes au diff�rents joueurs de la partie.
	 * Elle permet aussi de poser la premi�re carte sur le talon en prenant garde de ne pas
	 * mettre une carte Plus Quatre ou Joker
	 */
	public void distribuerCarte() {
		
		for(int i=0;i<listeJoueur.size();i++)
		{
			listeJoueur.get(i).piocherCarteFirstTour(5);
		}

		Carte cartePioche; 
		
		do
		{
			
			if(Pioche.getInstance().getNombreCarte()==0)
			{
				Talon.getInstance().viderTalon();
			}
			cartePioche = Pioche.getInstance().enleverCarte();
			Talon.getInstance().ajouterCarte(cartePioche);
		}while(cartePioche.getSpecial()!=null);
		
	}
	
	
	/**
	 * Cette m�thode v�rifie si il y a un gagnant dans la manche actuel. Elle v�rifie
	 * si un joueur poss�de 0 carte.
	 * 
	 * @return
	 * 		La m�thode renvoie un boolean valant true en cas de gagnant et false sinon
	 */
	public boolean verifierGagnantManche() {

		for(int i=0;i<this.nbreJoueur;i++)
		{
			if(listeJoueur.get(i).getNombreCarte() == 0)
			{
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Cette m�thode v�rifie si il y a un gagnant � la partie actuel.
	 * Elle v�rifie si un joueur a plus de 500 points.
	 * 
	 * @return
	 * 		La m�thode renvoie true si il y a un gagnant et false sinon
	 */
	public boolean verifierGagnantPartie()
	{
		for(int i=0;i<this.nbreJoueur;i++)
		{
			if(listeJoueur.get(i).getScore() >= 500)
			{
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Cette m�thode d�roule une manche tant qu'aucun joueur n'ai pos� toute ses cartes.
	 */
	public void deroulerManche()
	{
		//Initialisation de la manche
		Pioche.reinitialiserPioche();
		Talon.reinitialiserTalon();
		for(int i=0; i<this.nbreJoueur; i++)
		{
			this.listeJoueur.get(i).reinitialiserMain();
		}

		this.distribuerCarte(); //Distribution des cartes.
		joueurActuel = -1;
		manche++;
		
		
		while( !verifierGagnantManche() ) //tant que personne n'a gagné
		{
			this.nextJoueur();
			listeJoueur.get(joueurActuel).jouer(this);
		}
		//MAJ points du gagnant de la manche
		for (int i=0; i<this.getNbreJoueur(); i++)
		{
			if(this.listeJoueur.get(i).getNombreCarte() == 0)
				this.listeJoueur.get(i).calculerScore(this);
		}
		
		obs.notifyManche(this);

	}
	
	
	
	/**
	 * Cette m�thode d�roule une partie tant qu'aucun joueur n'ai plus de 500 points
	 */
	public void deroulerPartie() 
	{
		//Lancement de la partie
		
		while(!verifierGagnantPartie()) //tant qu'aucun joueur n'a atteint 500 point
		{	
			this.deroulerManche();
		}
		obs.notifyPartie(this); // on notifie les observateurs
		
	}


	/**
	 * Cette m�thode calcule et renvoie le prochain joueur qui doit jouer
	 */
	public void nextJoueur() {
		joueurActuel = Math.abs((joueurActuel+sens)%nbreJoueur);
	}


	/**
	 * Cette m�thode renvoie un entier repr�sentant le sens de rotation de la partie
	 * @return
	 * 		Renvoie 1 pour le sens horraire et -1 pour le sens anti-horraire
	 */
	public int getSens() {
		return sens;
	}

	
	/**
	 * Cette m�thode change le sens de rotation d'une partie.
	 */
	public  void setSens() {
		this.sens *= -1;
	}

	/**
	 * Cette m�thode actualise le joueur qui doit joueur
	 * @param joueurActuel
	 * 		Le nouveau joueur devant jouer
	 */
	public void setJoueurActuel(int joueurActuel) {
		this.joueurActuel = joueurActuel;
	}


	/**
	 * Cette m�thode r�cup�re le joueur � l'index index dans la liste des joueurs
	 * @param index
	 * 		L'index du joueur que l'on veut r�cup�rer
	 * @return
	 * 		Renvoie le joueur voulu
	 */
	public Joueur getJoueur(int index){
		return listeJoueur.get(index);
	}

	/**
	 * Cette m�thode renvoie le joueur actuellement en train de jouer
	 * @return
	 * 		Renvoie l'indice du joueur actuel
	 */
	public int getJoueurActuel()
	{
		return this.joueurActuel;
	}

	
	/**
	 * Cette m�thode renvoie le nombre de joueur de la partie actuelle
	 * @return
	 * 		Renvoie un entier repr�sentant le nombre de joueur actuel
	 */
	public int getNbreJoueur()
	{
		return this.nbreJoueur;
	}
	
	/**
	 * Cette m�thode permet de r�gler le nombre de joueur de la partie
	 * @param nbreJoueur
	 * 		Le nouveau nombre de joueur
	 */
	public void setNbreJoueur(int nbreJoueur)
	{
		this.nbreJoueur = nbreJoueur;
	}
	
	/**
	 * Cette m�thode renvoie la liste des joueurs de la partie
	 * @return
	 * 		Renvoie un ArrayList comprennant les joueurs de la partie
	 */
	public ArrayList<Joueur> getListeJoueur()
	{
		return listeJoueur;
	}

	public static void main(String[] args)
	{
		ModeGraphique g = new ModeGraphique();
		UnoController controller = new UnoController(g);
		
		controller.initialiserPartie();
		
	}
	
    /**
     * Cette m�thode renvoie la manche actuelle
     * @return
     * 			Renvoie un entier repr�sentant la manche actuelle
     */
	public int getManche() {
		return manche;
	}

	/**
	 * Cette m�thode permet de rajouter un observateur � la partie.
	 * Cette observateur peut �tre un UnoControleur.
	 * 
	 * @see Observateur
	 * @see UnoController
	 */
	public void addObservateur(Observateur obs) {
		 this.obs = obs;
	}
	
	/**
	 * Cette m�thode permet de renvoyer l'observateur de la partie.
	 * @return
	 * 		Renvoie l'observateur de la partie
	 * 
	 * @see Observateur
	 * @see UnoController
	 */
	public Observateur getObservateur()
	{
		return obs;
	}

	
	/**
	 * Cette m�thode retourne le nombre de joueur r�el
	 * @return
	 * 		Renvoie un entier �tant le nombre de joueur r�el
	 */
	public int getNbreJoueurReel() {
		// TODO Auto-generated method stub
		return nbreJoueurReel;
	}
}




