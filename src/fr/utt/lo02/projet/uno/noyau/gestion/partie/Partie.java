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
 *  Partie est la classe gï¿½rant une partie de Uno. Elle gï¿½re les joueurs, les paquets de cartes et les tours de jeu.
 */
public class Partie implements Observer{
	/* {author=Victor Le Deuff Gaï¿½l Mahfoudi}*/

	/**
	 * Valeur de la manche actuel
	 * @see Partie#getManche()
	 */
	private int manche;

	/**
	 * Entier gérant le sens de rotation d'une partie
	 * @see Partie#getSens()
	 * @see Partie#setSens()
	 */
	private int sens;

	/**
	 * Entier représentant le nombre de joueur total
	 * @see Partie#getNbreJoueur()
	 * @see Partie#setNbreJoueur(int)
	 */
	private int nbreJoueur;
	
	/**
	 * Entier représentant le nombre de joueur réel
	 * @see Partie#getNbreJoueurReel()
	 */
	private int nbreJoueurReel;
	
	/**
	 * Entier représentant le nombre de joueur virtuel
	 */
	private int nbreJoueurIA;

	/**
	 * Entier repésentant l'indice du joueur actuel
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
	 * Il prend en paramètre le nombre de joueurs réels, Le nombre de 
	 * joueurs virtuels ainsi que le UnoController de la partie.
	 * @param nbJoueur
	 * 			C'est le nombre de joueur réel
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
		this.addObservateur(obs);//ajoute l'observeur dÃ©s le debut
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
	 * Cette méthode distribue les cartes au différents joueurs de la partie.
	 * Elle permet aussi de poser la première carte sur le talon en prenant garde de ne pas
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
	 * Cette méthode vérifie si il y a un gagnant dans la manche actuel. Elle vérifie
	 * si un joueur possède 0 carte.
	 * 
	 * @return
	 * 		La méthode renvoie un boolean valant true en cas de gagnant et false sinon
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
	 * Cette méthode vérifie si il y a un gagnant à la partie actuel.
	 * Elle vérifie si un joueur a plus de 500 points.
	 * 
	 * @return
	 * 		La méthode renvoie true si il y a un gagnant et false sinon
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
	 * Cette méthode déroule une manche tant qu'aucun joueur n'ai posé toute ses cartes.
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
		
		
		while( !verifierGagnantManche() ) //tant que personne n'a gagnÃ©
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
	 * Cette méthode déroule une partie tant qu'aucun joueur n'ai plus de 500 points
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
	 * Cette méthode calcule et renvoie le prochain joueur qui doit jouer
	 */
	public void nextJoueur() {
		joueurActuel = Math.abs((joueurActuel+sens)%nbreJoueur);
	}


	/**
	 * Cette méthode renvoie un entier représentant le sens de rotation de la partie
	 * @return
	 * 		Renvoie 1 pour le sens horraire et -1 pour le sens anti-horraire
	 */
	public int getSens() {
		return sens;
	}

	
	/**
	 * Cette méthode change le sens de rotation d'une partie.
	 */
	public  void setSens() {
		this.sens *= -1;
	}

	/**
	 * Cette méthode actualise le joueur qui doit joueur
	 * @param joueurActuel
	 * 		Le nouveau joueur devant jouer
	 */
	public void setJoueurActuel(int joueurActuel) {
		this.joueurActuel = joueurActuel;
	}


	/**
	 * Cette méthode récupère le joueur à l'index index dans la liste des joueurs
	 * @param index
	 * 		L'index du joueur que l'on veut récupérer
	 * @return
	 * 		Renvoie le joueur voulu
	 */
	public Joueur getJoueur(int index){
		return listeJoueur.get(index);
	}

	/**
	 * Cette méthode renvoie le joueur actuellement en train de jouer
	 * @return
	 * 		Renvoie l'indice du joueur actuel
	 */
	public int getJoueurActuel()
	{
		return this.joueurActuel;
	}

	
	/**
	 * Cette méthode renvoie le nombre de joueur de la partie actuelle
	 * @return
	 * 		Renvoie un entier représentant le nombre de joueur actuel
	 */
	public int getNbreJoueur()
	{
		return this.nbreJoueur;
	}
	
	/**
	 * Cette méthode permet de régler le nombre de joueur de la partie
	 * @param nbreJoueur
	 * 		Le nouveau nombre de joueur
	 */
	public void setNbreJoueur(int nbreJoueur)
	{
		this.nbreJoueur = nbreJoueur;
	}
	
	/**
	 * Cette méthode renvoie la liste des joueurs de la partie
	 * @return
	 * 		Renvoie un ArrayList comprennant les joueurs de la partie
	 */
	public ArrayList<Joueur> getListeJoueur()
	{
		return listeJoueur;
	}

	public static void main(String[] args)
	{
		//ModeConsole c = new ModeConsole();
		ModeGraphique g = new ModeGraphique();
		UnoController controller = new UnoController(g);
		
		controller.initialiserPartie();
		
	}
	
    /**
     * Cette méthode renvoie la manche actuelle
     * @return
     * 			Renvoie un entier représentant la manche actuelle
     */
	public int getManche() {
		return manche;
	}

	/**
	 * Cette méthode permet de rajouter un observateur à la partie.
	 * Cette observateur peut être un UnoControleur.
	 * 
	 * @see Observateur
	 * @see UnoController
	 */
	public void addObservateur(Observateur obs) {
		 this.obs = obs;
	}
	
	/**
	 * Cette méthode permet de renvoyer l'observateur de la partie.
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
	 * Cette méthode retourne le nombre de joueur réel
	 * @return
	 * 		Renvoie un entier étant le nombre de joueur réel
	 */
	public int getNbreJoueurReel() {
		// TODO Auto-generated method stub
		return nbreJoueurReel;
	}
}




