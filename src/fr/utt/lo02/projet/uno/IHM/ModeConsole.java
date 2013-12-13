


package IHM;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.JoueurNormal;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;
import Observer.Observateur;


/**
 * Observateur pour les classes Partie et Joueur,
 * Traduit les actions du noyau de Uno en Console.
 * Utilisation de Scanner et System.out
 */

public class ModeConsole implements Observateur
{	
	
	public Partie partie;
	public Scanner sc = new Scanner(System.in);
	
	
	/**
		 * Constructeur de la classe
		 * Permet de parametrer le moteur du jeu et lancer la partie
		 * @see Partie
		 * 
		 */
	
	public ModeConsole() 
	{

		
		System.out.println("/////* Jeu de Uno *\\\\\\\\\\");
		System.out.println("Saisissez le nombre de joueurs réels: "); 
		int nbreJoueurReel = this.askNombre(0,10);
		System.out.println("Saisissez le nombre de joueurs virtuels: "); 
		int nbreJoueurVirtuel = this.askNombre(0,10);
		
		partie = new Partie(nbreJoueurReel, nbreJoueurVirtuel, this);
		System.out.println("\n\nDebut de la premiere manche:");
		partie.deroulerPartie();

	}
	
	
	
	/**
		 * @param Partie partie
		 * Fait le bilan de la manche et annonce la prochaine s'il y en a une
		 */
	public void notifyManche(Partie partie) //Change de manche
	{
		
		boolean fin = false;
		System.out.println( partie.getJoueur(partie.getJoueurActuel()).afficherPseudo() +" remporte la manche "+ partie.getManche());
		for (int i = 0; i< partie.getNbreJoueur(); i++)
		{ 
			System.out.println(partie.getJoueur(i).afficherPseudo() + " a " + partie.getJoueur(i).getScore() +" points.");
			if (partie.getJoueur(i).getScore() >= 500 )
				fin = true;
		}
		if (!fin)
			System.out.println("\n\nDebut de la manche " + (partie.getManche()+1) + ":");
		
		
	}
	
	
	
	
	/**
		 * @param Partie partie
		 * 
		 * Notifie la fin de la partie
		 */
	public void notifyPartie(Partie partie) //Fin de partie
	{
		
		System.out.println( partie.getJoueur(partie.getJoueurActuel()).afficherPseudo() + " remporte la partie, le jeu est terminé, il y a eu " + partie.getManche() + " manches");	
	}
	
	
	/**
	 * Notifie une erreur, lorsqu'une carte ne peut etre posée
	 */
	public void notifyError() 
	{
		System.out.println("Cette carte ne peut etre posée...");
	}
	
	
	
	/**
	 * @param Joueur joueur, int choix
	 * Fait le bilan d'un tour: ce que le joueur a posé, l'effet de la carte, s'il a pioché...
	 */
	@SuppressWarnings("incomplete-switch")
	public void notifyTour(Joueur joueur, int choix) 
	{
		
	
		if(choix < joueur.getMain().getNombreCarte())
		{
			System.out.println(joueur.afficherPseudo() +" a posé un " + Talon.getInstance().getDerniereCarte().toString());
			if(Talon.getInstance().getDerniereCarte().getSpecial() != null)
			{
				switch( Talon.getInstance().getDerniereCarte().getSpecial() )
				{
				case JOKER:
					System.out.println(joueur.afficherPseudo() +" a choisi la couleur " + Talon.getInstance().getDerniereCarte().getCouleur());
					break;
				case INVERSE:
					System.out.println("Le jeu change de sens.");
					break;

				}
			}
		}
		else if (choix >= joueur.getMain().getNombreCarte()+1)
		{
			System.out.println(joueur.afficherPseudo() +" passe.");
		}
		
	}
	
	
	/**
		 * Permet a l'utilisateur d'effectuer une action lorsque c'est son tour:
		 * -Poser une carte
		 * -Piocher
		 * -Dire un contre Uno
		 * 
		 * @param Joueur joueur
		 * @return int choix
		 */
	public int askCarte(Joueur joueur) //Permet au joueur de choisir une carte
	{
		
		int choix = 0;
		System.out.println("\n\n ----------------------------------------------------------------------");
		System.out.println("Au tour de " + joueur.afficherPseudo() +":");
		System.out.println("Carte du talon: " + Talon.getInstance().getDerniereCarte().toString());
		System.out.println("Votre Main: ");
		for (int i = 0; i< joueur.getMain().getNombreCarte(); i++)
		{
			System.out.println((i+1) +": " +joueur.getMain().getMain().get(i).toString());
		}
		System.out.println((joueur.getMain().getNombreCarte()+1) + ": Piocher");

		System.out.println((joueur.getMain().getNombreCarte()+2) + ": Declarer un Contre Uno");
		System.out.println("Choisissez une carte [1.." + (joueur.getMain().getNombreCarte()+2) + "] : ");
		choix= this.askNombre(0, joueur.getMain().getNombreCarte()+2);
	
		//on retourne le choix
		return choix-1;
	}

	
	/** 
		  * Permet au joueur a qui l'on pose un plus quatre de se defendre en declarant un bluff
		  * @return int choix
		  */
	 public int askBluff() 
	  {
		 
		 System.out.println(partie.getJoueur(partie.getJoueurActuel()).afficherPseudo() + " A posé un PLUS_QUATRE,");
		 Joueur joueur = partie.getJoueur(Math.abs((partie.getJoueurActuel()+partie.getSens())%partie.getNbreJoueur()));
		 if( joueur instanceof JoueurNormal)
		 {
			 System.out.println(joueur.afficherPseudo()+ ", voulez vous declarer un bluff? [1|0] ");
			 return this.askNombre(0,1);
		 }
		 else
		 {
			 joueur.direBluff(partie.getJoueur(partie.getJoueurActuel()));
			 return 1;
		 }
	  }
	 
	 
	 
	  /**
		  * @param Joueur joueur
		  * Notifie au joueur la carte qu'il vient de piocher
		  */
	 public void notifyPioche(Joueur joueur) //Losrque le joueur pioche
	 {
		 
		 if( this.partie.getManche() != 0)
			 System.out.println("Vous avez pioché un " + joueur.getMain().getMain().get(joueur.getNombreCarte()-1));
		 
	 }
	 
	 
	 
	 /**
		  * @param Joueur joueur
		  * @return int Choix
		  * Demande au joueur s'il veut poser la carte precedemment piochée
		  */
	 public int askPoserCarte(Joueur joueur) 
	 {
		 
		 System.out.println("Voulez vous la poser? [1|0]");
		 return this.askNombre(0,1);
		
	 }
	 
	 
	
	 /**
		  * @return ECcouleur
		  * Demande au joueur la couleur pour la suite du jeu après un +4 ou joker
		  */ 
	 public ECouleur askCouleur() //Choisir couleur
	 {
		 
			System.out.println("Choisissez la nouvelle couleur [B|R|J|V]:");
			try
			{
				String couleur = sc.nextLine();
				switch( couleur.charAt(0) )
				{
					case 'B':
						return ECouleur.BLEU;
					case 'R':
						return ECouleur.ROUGE;
					case 'J':
						return ECouleur.JAUNE;
					case 'V':
						return ECouleur.VERT;
					default:
						System.out.println("Erreur: veuillez recommencer");
						return this.askCouleur();
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("Erreur: Veuillez recommencer");
				return this.askCouleur();
			}
			
		
	 }
	 
	 /** 
		  * @return int Choix
		  * Demande a l'utilisateur contree qui il souhaite declarer un contre uno
		  */
	 public int askContreUno() 
	 {
		 
		System.out.println("A qui dites vous contre Uno? [1.."+(partie.getNbreJoueur())+"]");
		for(int i = 0; i<partie.getNbreJoueur(); i++)
		{
			System.out.println((i+1) + ":" + partie.getJoueur(i).afficherPseudo() );
		}
		System.out.println((partie.getNbreJoueur()) + ": Annuler");
		return this.askNombre(0, partie.getNbreJoueur());
	 }
	 
	 
	 /**
		  * Demander si le joueur souhaite declarer un uno
		  */
	 public int askUno() 
	 {
		 
		 System.out.println("voulez vous déclarer un UNO? [1|0]");
		 return this.askNombre(0, 1);

	 }
	

	 /**
			 * @param int min, int max
			 * @return int choix
			 * Algorithme de saisie au clavier
			 */
		public int askNombre(int min, int max) {
		
			
		int i = -1;
		
		try{
			i  = sc.nextInt();
			}
		catch(InputMismatchException e)
			{
				System.out.println("Erreur: entrez un entier entre "+ min + " et " + max + ": ");
				i = askNombre(min, max);
			}
		finally
		{
			if ( i < min || i > max)
			{
				System.out.println("Erreur: entrez un entier entre "+ min + " et " + max + ": ");
				i=askNombre(min, max);
			}
		}
		
		return i;
}
	
	/**
	 * @return String pseudo
	 * 
	 * Demande a l'utilisateur son pseudo pour la partie
	 */
	public String askPseudo()
	{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisissez le nom du joueur:");
		String s = null;
		try
		{
			s = sc.nextLine();
		}
		catch (NullPointerException e)
		{
			System.out.println("Erreur: reessayez");
		
			s = askPseudo();
		}
		finally
		{
			if(s == null || s == " ")
			{
				System.out.println("Erreur; reessayez");
				s = askPseudo();
			}
		}
		
			return s;
		
		
	}
	
}
