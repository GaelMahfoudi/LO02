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

	private Partie partie;
	
	public ModeConsole()
	{
		
		System.out.println("/////* Jeu de Uno *\\\\\\\\\\");
		System.out.println("Saisissez le nombre de joueurs réels: "); 
		int nbreJoueurReel = Partie.getNombre(0,10);
		System.out.println("Saisissez le nombre de joueurs virtuels: "); 
		int nbreJoueurVirtuel = Partie.getNombre(0,10);
		
		partie = new Partie(nbreJoueurReel, nbreJoueurVirtuel);
		partie.addObservateur(this);
		for (int i=0; i<partie.getNbreJoueur(); i++)
		{	
			partie.getJoueur(i).addObservateur(this);
		}
		
		this.deroulerPartie();
	}
	
	public void deroulerPartie()
	{
		System.out.println("\n\nDebut de la premiere manche:");
		partie.deroulerPartie();

		
	}
	
	
	public void updateManche(Partie partie) {
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
	
	public void updatePartie(Partie partie)
	{
		System.out.println( partie.getJoueur(partie.getJoueurActuel()).afficherPseudo() + " remporte la partie, le jeu est terminé, il y a eu " + partie.getManche() + " manches");	
	}
	
	public void notifyError() //Notifie si la carte pu etre jouée
	{
		System.out.println("Cette carte ne peut etre posée...");
	}
	
	@SuppressWarnings("incomplete-switch")
	public void updateJoueur(Joueur joueur, int choix)
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
				case PASSE:
					System.out.println("Le joueur suivant passe son tour.");
					break;
				case PLUS_DEUX:
					System.out.println("Le joueur suivant pioche 2 cartes.");
					break;
				case INVERSE:
					System.out.println("Le jeu change de sens.");
					break;

				}
			}
		}
		else if (choix >= joueur.getMain().getNombreCarte())
		{
			System.out.println(joueur.afficherPseudo() +" passe.");
		}
		
	}
	
	
	public int choisirCarte(Joueur joueur)
	{
		int choix = 0;
		System.out.println("Carte du talon: " + Talon.getInstance().getDerniereCarte().toString());
		System.out.println("Votre Main: ");
		for (int i = 0; i< joueur.getMain().getNombreCarte(); i++)
		{
			System.out.println((i+1) +": " +joueur.getMain().getMain().get(i).toString());
		}
		System.out.println((joueur.getMain().getNombreCarte()+1) + ": Piocher");

		System.out.println((joueur.getMain().getNombreCarte()+2) + ": Declarer un Contre Uno");
		System.out.println("Choisissez une carte [1.." + (joueur.getMain().getNombreCarte()+2) + "] : ");
		choix= Partie.getNombre(0, joueur.getMain().getNombreCarte()+2);
	
		//on retourne le choix
		return choix-1;
	}

	

	 public int upgradeBluff()
	  {
		 System.out.println(partie.getJoueur(partie.getJoueurActuel()).afficherPseudo() + " A posé un PLUS_QUATRE,");
		 Joueur joueur = partie.getJoueur(Math.abs((partie.getJoueurActuel()+partie.getSens())%partie.getNbreJoueur()));
		 if( joueur instanceof JoueurNormal)
		 {
			 System.out.println(joueur.afficherPseudo()+ ", voulez vous declarer un bluff? [1|0] ");
			 return Partie.getNombre(0,1);
		 }
		 else
		 {
			 joueur.direBluff(partie.getJoueur(partie.getJoueurActuel()));
			 return 1;
		 }
	  }
	  
	 public int upgradePasse(Joueur joueur)
	 {
		 System.out.println("Vous avez pioché un " + joueur.getMain().getMain().get(joueur.getNombreCarte()-1));
		 System.out.println("Voulez vous la poser? [1|0]");
		 return Partie.getNombre(0,1);
		
	 }
	
	 public ECouleur choisirCouleur()
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
	 
	 
	 public int choisirUno()
	 {
		 System.out.println("voulez vous déclarer un UNO? [1|0]");
		 return Partie.getNombre(0, 1);

	 }
	
}