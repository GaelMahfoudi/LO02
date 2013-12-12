package IHM;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.JoueurNormal;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;
import Observer.Observateur;




public class ModeConsole implements Observateur
{	
	
	public Partie partie;
	public ModeConsole() //Constructeur
	{
		
		System.out.println("/////* Jeu de Uno *\\\\\\\\\\");
		System.out.println("Saisissez le nombre de joueurs réels: "); 
		int nbreJoueurReel = this.getNombre(0,10);
		System.out.println("Saisissez le nombre de joueurs virtuels: "); 
		int nbreJoueurVirtuel = this.getNombre(0,10);
		
		partie = new Partie(nbreJoueurReel, nbreJoueurVirtuel, this);
		this.deroulerPartie();
	}
	
	public void deroulerPartie() //lance le moteur du jeu
	{
		System.out.println("\n\nDebut de la premiere manche:");
		partie.deroulerPartie();

		
	}
	
	
	public void updateManche(Partie partie) //Change de manche
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
	
	public void updatePartie(Partie partie) //Fin de partie
	{
		System.out.println( partie.getJoueur(partie.getJoueurActuel()).afficherPseudo() + " remporte la partie, le jeu est terminé, il y a eu " + partie.getManche() + " manches");	
	}
	
	public void notifyError() //Notifie si la carte n'a pu etre jouée
	{
		System.out.println("Cette carte ne peut etre posée...");
	}
	
	@SuppressWarnings("incomplete-switch")
	public void updateJoueur(Joueur joueur, int choix) //Info sur la fin d'un tour
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
	
	
	public int choisirCarte(Joueur joueur) //Permet au joueur de choisir une carte
	{
		int choix = 0;
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
		choix= this.getNombre(0, joueur.getMain().getNombreCarte()+2);
	
		//on retourne le choix
		return choix-1;
	}

	

	 public int upgradeBluff() //Savoir si le joueur declare un bleuff
	  {
		 System.out.println(partie.getJoueur(partie.getJoueurActuel()).afficherPseudo() + " A posé un PLUS_QUATRE,");
		 Joueur joueur = partie.getJoueur(Math.abs((partie.getJoueurActuel()+partie.getSens())%partie.getNbreJoueur()));
		 if( joueur instanceof JoueurNormal)
		 {
			 System.out.println(joueur.afficherPseudo()+ ", voulez vous declarer un bluff? [1|0] ");
			 return this.getNombre(0,1);
		 }
		 else
		 {
			 joueur.direBluff(partie.getJoueur(partie.getJoueurActuel()));
			 return 1;
		 }
	  }
	  
	 public void upgradePioche(Joueur joueur) //Losrque le joueur pioche
	 {
		 if( this.partie.getManche() != 0)
			 System.out.println("Vous avez pioché un " + joueur.getMain().getMain().get(joueur.getNombreCarte()-1));
		 
	 }
	 
	 public int upgradePasse(Joueur joueur) //Demande au joueur s'il veut poser la carte piochée
	 {
		 System.out.println("Voulez vous la poser? [1|0]");
		 return this.getNombre(0,1);
		
	 }
	
	 public ECouleur choisirCouleur() //Choisir couleur
	 {
		 Scanner sc = new Scanner(System.in);
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
						return this.choisirCouleur();
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("Erreur: Veuillez recommencer");
				return this.choisirCouleur();
			}
			
		
	 }
	 
	 
	 public int updateDireContreUno() //Declarer un contru uno
	 {
		 Scanner sc = new Scanner(System.in);
		System.out.println("A qui dites vous contre Uno? [1.."+(partie.getNbreJoueur())+"]");
		for(int i = 0; i<partie.getNbreJoueur(); i++)
		{
			System.out.println((i+1) + ":" + partie.getJoueur(i).afficherPseudo() );
		}
		System.out.println((partie.getNbreJoueur()) + ": Annuler");
		return this.getNombre(0, partie.getNbreJoueur());
	 }
	 
	 public int choisirUno() //Declarer un uno
	 {
		 System.out.println("voulez vous déclarer un UNO? [1|0]");
		 return this.getNombre(0, 1);

	 }
	


		public int getNombre(int min, int max) {
		
		Scanner sc = new Scanner(System.in);
		int i = -1;
		
		try{
			i  = sc.nextInt();
			}
		catch(InputMismatchException e)
			{
				System.out.println("Erreur: entrez un entier entre "+ min + " et " + max + ": ");
				i = getNombre(min, max);
			}
		finally
		{
			if ( i < min || i > max)
			{
				System.out.println("Erreur: entrez un entier entre "+ min + " et " + max + ": ");
				i=getNombre(min, max);
			}
		}
		
		return i;
}
	
	public String updatePseudo()
	{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisissez le nom du joueur:");
		String s = " ";
		try
		{
			s = sc.nextLine();
		}
		catch (NullPointerException e)
		{
			System.out.println("Erreur: reessayez");
		
			s = updatePseudo();
		}
		
			return s;
		
		
	}
	
}
