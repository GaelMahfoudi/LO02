package fr.utt.lo02.projet.uno.ihm.graphique;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.utt.lo02.projet.uno.ihm.observer.Observateur;
import fr.utt.lo02.projet.uno.ihm.observer.UnoController;
import fr.utt.lo02.projet.uno.ihm.observer.View;
import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.carte.ESpecial;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.JoueurNormal;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;


/**
 * Classe ModeGraphique implementant View
 * @see View
 * @author Victor & Gael
 * 
 * Cette classe est la vue de l'application (voir design pattern MVC)
 * Elle fait le lien entre l'utilisateur et le moteur du jeu (@see Observer)
 * Cette classe utilise java.swing et java.awt afin de créé son interface graphique
 *
 */
public class ModeGraphique extends JFrame implements View{

	private static final long serialVersionUID = 5061274056292434902L;
	/**
	 * hauteur de la fenetre (entier naturel)
	 */
	public final static int hFenetre = 725;
	/**
	 * Largeur de la fentre (entier naturel)
	 */
	public final static int lFenetre = 800;
	/**
	 * @see ParametrerPartie
	 * Permet de parametrer la partie et de l'instancier
	 * cet attribut ne sert don qu'au debut de la partie
	 */
	private ParametrerPartie param;
	/**
	 * @see MainJoueurPan
	 */
	private MainJoueurPan main;
	/**
	 * @see TablePan
	 */
	private TablePan table;
	/**
	 * @see TableauDeBord
	 */
	private TableauDeBord tableauDeBord;
	/**
	 * @see RapportDActivite
	 */
	public RapportDActivite rapport;
	/**
	 * @see OptionJeu
	 */
	private OptionJeu option;
	/**
	 * @see Partie
	 * correspond a la partie en cours
	 */
	private Partie partie;
	/**
	 * @see Observateur
	 * c'est l'observateur qui fait le lien entre le moteur et la vue
	 */
	private Observateur obs;
	/**
	 * @see JoueurNormal
	 * Correspond au joueur actuel (joueur Reel)
	 */
	public Joueur joueurActuel;
	
	
	  
	  /**
	   * Constructeur de la classe
	   * Met en place le parametrage de la parie et lui meme
	   */
	@SuppressWarnings("serial")
	public ModeGraphique()
	{
		
		this.setTitle("Partie en cours");
		
		param = new ParametrerPartie();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(lFenetre, hFenetre);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.setContentPane(new JPanel()
		{
			private Image img = new ImageIcon(ImageCarte.pathImage+ "/theme/FondUno.png").getImage();
				public void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
			    GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
			    g2d.setPaint(gp);
			    g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			    
			    }
		});
		
		this.getContentPane().setLayout(new BorderLayout());
		
	
		
	}
	

	
/**
 * @see JFrame#paintComponents(Graphics)
 * met en place l'image de fond
 * @param g
 *
 */
	public void paintComponent(Graphics g){
        super.paintComponents(g);
        this.setBackground(new Color(200,200,200));
 
    }
	
	
	/**
	 * Afin de demarer la partie, on la recupere de ParameterPartie
	 * @see ParametrerPartie
	 * ENfin, on met en place les diferents panneaux de la fenetre
	 */
	public Partie demarrerPartie(Partie partie, UnoController controller) 
	{
		//Creation de la partie
		partie = param.recupererPartie(partie, controller);
		this.partie = partie;
		
		//mise en place des differents panneaux
		//Le centre: table de jeu
		table = new TablePan();
		this.getContentPane().add(table, BorderLayout.CENTER);
		
		//Le sud: la main du joueur actuel
		main = new MainJoueurPan();
		this.getContentPane().add(main, BorderLayout.SOUTH);
		
		//Le nord: indique le nom et la manche
		tableauDeBord = new TableauDeBord();
		this.getContentPane().add(tableauDeBord, BorderLayout.NORTH);
		
		//L'ouest: indique le nombre de cartes de chaques joueurs, TODO
		rapport = new RapportDActivite(partie);
		this.getContentPane().add(rapport, BorderLayout.WEST);
		
		//L'est: les options en cours de jeu
		option = new OptionJeu(this);
		this.getContentPane().add(option, BorderLayout.EAST);
		
		this.setVisible(true);
		if(partie.getJoueur(partie.getJoueurActuel()) instanceof JoueurNormal)
			JOptionPane.showMessageDialog(null, " Appuyez sur ok lorsque vous serez pret", ("Au tour de "+ (String)partie.getJoueur(partie.getJoueurActuel()).afficherPseudo()), JOptionPane.WARNING_MESSAGE);
		
		
		return partie;
	}
	
	/**
	 * @see ParametrerPartie#demanderPseudo()
	 * Retourne le pseudo du joueur apres l'avoir demandé a @see ModeGraphique#param
	 * @return String pseudo
	 */
	public String demanderPseudo() 
	{
			return param.demanderPseudo();
	}
		
	
	/**
	 * @param joueur
	 * 		Le joueur actuel
	 * 
	 * renvoie la position de la carte que le joueur veut poser
	 */
	public int demanderCarteAJouer(Joueur joueur) 
	{
		this.joueurActuel=joueur;
		this.refresh();
		return this.demanderChoix(0, joueur.getNombreCarte());
	}
	

	/**
	 * Rafraichit la fenetre et ses JPanel
	 */
	public void refresh() {
				//Rafraichissement du tableau de bord
				tableauDeBord.setJoueur(joueurActuel);
				tableauDeBord.refresh();
				
				//Rafraichissement de la table de jeu
				table.refresh();
				//Rafraichissement de la main du joueur
				main.refresh(joueurActuel);
				//Rafraichissement du rapport d'activité
				rapport.refresh();
				
				this.repaint();
				this.setVisible(true);
	}


	/**
	 * 
	 * retourne un entier entre min et max, choisi par l'utilisateur
	 * @param min
	 * 		Le choix minimum
	 * @param max
	 * 		Le choix maximum
	 * @return 
	 * 		Renvoie le choix du joueur
	 */
	public int demanderChoix(int min, int max) {
		boolean choixPioche = false;
		int choixCarte = -1;
		while(!choixPioche && choixCarte == -1)
		{
			choixCarte = main.getChoix();
			choixPioche = table.isChoixPioche();
		}
		
		if(choixPioche)
		{
			return max;
		}
		else
		{
			return choixCarte;
		}
	}



	/**
	 * Affin de proteger l'intimité d'un joueur, on cache son jeu avant de changer de tour
	 */
	public void cacherJeu()
	{
		main.vider();
		this.repaint();
		this.setVisible(true);
	}
	
	/**
	 * Notifie la fin de la manche et met a jour la fenetre
	 * @param p
	 * 		La partie
	 */
	public void afficherFinManche(Partie p) {

		this.cacherJeu();
		rapport.refreshscore(p);
		
		JOptionPane.showMessageDialog(null, "La manche est finie, tenez vous pret !", "Fin de la manche", JOptionPane.WARNING_MESSAGE);
		
		rapport.refresh();
		tableauDeBord.setManche(p.getManche());
		this.repaint();
	}

	

	/**
	 * Notifie la fin du tour et rafraichit la fentre
	 * @param joueur
	 * @param choix
	 */
	public void afficherTour(Joueur joueur, int choix) {
		
		rapport.refreshJoueur(joueur);
		
		int joueurSuivant = Math.abs((partie.getJoueurActuel()+partie.getSens())%partie.getNbreJoueur());
		this.cacherJeu();
		if(partie.getJoueur(Math.abs((partie.getJoueurActuel()+partie.getSens())%partie.getNbreJoueur())) instanceof JoueurNormal)
		{
			if(Talon.getInstance().getDerniereCarte().getSpecial() == ESpecial.PASSE || Talon.getInstance().getDerniereCarte().getSpecial() == ESpecial.PLUS_DEUX)
			{
				JOptionPane.showMessageDialog(null, " Vous passez votre tour", ("Au tour de "+ (String)partie.getJoueur(joueurSuivant).afficherPseudo()), JOptionPane.WARNING_MESSAGE);
				JOptionPane.showMessageDialog(null, " Appuyez sur ok lorsque vous serez pret", ("Au tour de "+ (String)partie.getJoueur(Math.abs((joueurSuivant+partie.getSens())%partie.getNbreJoueur())).afficherPseudo()), JOptionPane.WARNING_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null, " Appuyez sur ok lorsque vous serez pret", ("Au tour de "+ (String)partie.getJoueur(joueurSuivant).afficherPseudo()), JOptionPane.WARNING_MESSAGE);
		}
	
	}

	
	/**
	 * Demande a l'utilisateur s'il veut poser ou non la carte piochée
	 * @param joueur
	 * 		Le joueur
	 * @return 
	 * 		Renvoie un entier �tant le choix du joueur
	 */
	public int poserCartePioche(Joueur joueur) {
			
		ImageIcon icon;
		if( joueur.getMain().getMain().get(joueur.getNombreCarte()-1).getSpecial() != null)
		{
			icon = new ImageIcon("./src/fr/utt/lo02/projet/uno/ihm/uno_images/" + ImageCarte.getCouleur(joueur.getMain().getMain().get(joueur.getNombreCarte()-1)) + "/" + ImageCarte.getSpecial(joueur.getMain().getMain().get(joueur.getNombreCarte()-1)) +".jpg");
			icon = new ImageIcon(icon.getImage().getScaledInstance(ImageCarte.lCarte, ImageCarte.hCarte, Image.SCALE_DEFAULT));
		}
		else
		{
			icon = new ImageIcon("./src/fr/utt/lo02/projet/uno/ihm/uno_images/" + ImageCarte.getCouleur(joueur.getMain().getMain().get(joueur.getNombreCarte()-1)) + "/" + ImageCarte.getValeur(joueur.getMain().getMain().get(joueur.getNombreCarte()-1)) +".jpg");
			icon = new ImageIcon(icon.getImage().getScaledInstance(ImageCarte.lCarte, ImageCarte.hCarte, Image.SCALE_DEFAULT));
			
		}
        
		int option = JOptionPane.showConfirmDialog(null, "Voulez vous poser cette carte?", "Vous avez pioché", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE , icon );
	
		if(option == JOptionPane.OK_OPTION)
		{
			
			return 1;
		}
		else
		{
			return 0;
		}
		
	}

	/**
	 * notifie l'utilisateur que la carte selectionnée precedement ne peut etre posée
	 */
	public void afficherMauvaisChoix() {
		JOptionPane.showMessageDialog(null, "Cette carte ne peut etre posée", "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Demande a l'utilisateur quelle couleur il veut imposer au jeu
	 * @return 
	 * 		Renvoie la couleur choisie par le joueur
	 */
	public ECouleur demanderCouleur() {
		String[] couleur = {"Bleu", "Rouge", "Jaune", "Vert"};
	    
		 int rang = JOptionPane.showOptionDialog(null, "Veuillez choisir la nouvelle couleur:", "Changement de couleur", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, couleur, "echec");
		switch(rang)
		{
			case 0:
				return ECouleur.BLEU;
			case 1:
				return ECouleur.ROUGE;
			case 2:
				return ECouleur.JAUNE;
			case 3:
				return ECouleur.VERT;
			default:
				return this.demanderCouleur();
		}
	}

	/**
	 * Demande a l'utilisateur s'il pense que le joueur actuel bluff
	 */
	public int demanderBluff(Partie partie) {
		Joueur joueur = partie.getJoueur(Math.abs((partie.getJoueurActuel()+partie.getSens())%partie.getNbreJoueur()));
		ImageIcon icon = new ImageIcon("./src/fr/utt/lo02/projet/uno/ihm/uno_images/special/plus_quatre.jpg");
		icon = new ImageIcon(icon.getImage().getScaledInstance(ImageCarte.lCarte, ImageCarte.hCarte, Image.SCALE_DEFAULT));
	
		int option = JOptionPane.showConfirmDialog(null,  joueur.afficherPseudo()+ ", voulez vous declarer un bluff?", partie.getJoueur(partie.getJoueurActuel()).afficherPseudo() +" a posé un Plus quatre:", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE , icon );
		
		
			if(option == JOptionPane.OK_OPTION)
			{
				
				return 1;
			}
			else
			{
				return 0;
			}
		
	}

	/**
	 * action lancée si un utilisateur souhaite declarer un uno
	 */
	public void declarerUno() {
		
		obs.declarerUno();
	}
	
	/**
	 * rafraichit les infos sur le joueur dans rapport
	 * @see ModeGraphique#rapport
	 */
	public void afficherCartePioche(Joueur joueur) {
		//Inclut dans poser carte pioche, ici on met a jour le nombre du carte du joueur
		rapport.refreshJoueur(joueur);
	}

	/**
	 * @see View#setController(Observateur)
	 */
	public void setController(Observateur obs)
	{
		this.obs=obs;
	}
	
	/**
	 * @see View#quiDemandeUno(Partie)
	 */
	public Joueur quiDemandeUno(Partie partie) {
		int rang = -1;
	    String[] nomJoueur = new String[partie.getNbreJoueur()+1];
	    for(int i=0; i<partie.getNbreJoueur(); i++)
	    {
	    	nomJoueur[i]=partie.getJoueur(i).afficherPseudo();
	    }
	    nomJoueur[partie.getNbreJoueur()] = "annuler";
		while(rang == -1)
			rang = JOptionPane.showOptionDialog(null, "Qui souhaite declarer un UNO?", "UNO", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, nomJoueur, "echec");

		if(rang < partie.getNbreJoueur())
			return partie.getJoueur(rang);
		else
			return null;
		 
	}
	
	/** 
	 * @see View#demanderUno()
	 */
	public int demanderUno() {
		//Ne sert a rien en mode graphique
		return 0;
	}
	
	/**
	 * @see UnoController#declarerContreUno()
	 */
	//Lance l'action
	public void contreUno() {
		obs.declarerContreUno();
	}
	
	/**
	 * @see View#quiDemandeContreUno(Partie)
	 */
	//Demande  qui declare contreUno
	public Joueur quiDemandeContreUno(Partie partie) {
		int rang = -1;
		String[] nomJoueur = new String[partie.getNbreJoueurReel()+1];
	    int j=0;
		for(int i=0; i<partie.getNbreJoueur(); i++)
	    {
	    	if(partie.getJoueur(i) instanceof JoueurNormal)
	    	{
	    		nomJoueur[j]=partie.getJoueur(i).afficherPseudo();
	    		j++;
	    	}
	    }
	    nomJoueur[partie.getNbreJoueurReel()] = "annuler";
		while(rang == -1)
			rang = JOptionPane.showOptionDialog(null, "Qui souhaite declarer un contre-UNO?", "Contre-UNO", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, nomJoueur, "echec");

		
		if(rang < partie.getNbreJoueurReel())
		{
			for(int i=0; i<partie.getNbreJoueur(); i++)
			{
				if(partie.getJoueur(i).afficherPseudo().equals(nomJoueur[rang]))
					return partie.getJoueur(i);
			}
			return null; //Ne peut arriver
		}
			
		else
			return null;
	}
	
	/**
	 * @see View#demanderContreUno(Partie)
	 */
	//Demande a qui on fait un contre uno
	public int demanderContreUno(Partie partie) {
		int rang = -1;
	    String[] nomJoueur = new String[partie.getNbreJoueur()+1];
	    for(int i=0; i<partie.getNbreJoueur(); i++)
	    {
	    	nomJoueur[i]=partie.getJoueur(i).afficherPseudo();
	    }
	    nomJoueur[partie.getNbreJoueur()] = "annuler";
		while(rang == -1)
			rang = JOptionPane.showOptionDialog(null, "A qui souhaitez vous dire contre-UNO?", "Contre-UNO", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, nomJoueur, "echec");

		return rang;
	}

	
	
	/**
	 * @see View#afficherFinPartie(Partie)
	 */
	public void afficherFinPartie(Partie p) {
		int choix = -1;
		while(choix == -1)
		{
			choix = JOptionPane.showConfirmDialog(null, partie.getJoueur(partie.getJoueurActuel()).afficherPseudo()+" a remporté la partie! Nouvelle partie ?", "Fin de la partie!", JOptionPane.YES_NO_OPTION);
		}
		if(choix == JOptionPane.NO_OPTION)
			System.exit(0);
		else
			this.recommencerPartie();
	}
	

	/**
	 * Action qui remet la partie a zero
	 */
	private void recommencerPartie() {
		this.dispose();
		Partie.main(null);
	}


	/**
	 * 
	 * @see fr.utt.lo02.projet.uno.ihm.observer.View#afficherPasse(fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur)
	 */
	public void afficherPasse(Joueur joueur) {
		
		//Ici c'est pour notifier qu'un joueur passe, pas forcement qu'une carte passe a été posée
		
	}


}
