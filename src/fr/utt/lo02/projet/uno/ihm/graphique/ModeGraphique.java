package fr.utt.lo02.projet.uno.ihm.graphique;


import java.awt.BorderLayout;
import java.awt.Color;
<<<<<<< HEAD
=======
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
>>>>>>> 01636b73004822c0cba186de720974f9fd67744c
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.utt.lo02.projet.uno.ihm.observer.Observateur;
import fr.utt.lo02.projet.uno.ihm.observer.UnoController;
import fr.utt.lo02.projet.uno.ihm.observer.View;
import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.JoueurNormal;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public class ModeGraphique extends JFrame implements View{

	public final static int hFenetre = 725;
	public final static int lFenetre = 800;
	private ParametrerPartie param;
	private MainJoueurPan main;
	private TablePan table;
	private TableauDeBord tableauDeBord;
	public RapportDActivite rapport;
	private Partie partie;
	private OptionJeu option;
	private Observateur obs;
	public Joueur joueurActuel;
	
	//Pour la barre de menu
	private JMenuBar menuBar = new JMenuBar();
	  private JMenu fichier = new JMenu("Fichier");
	  private JMenuItem chargerPartie = new JMenu("Charger une partie");
	  private JMenuItem nouvellePartie = new JMenu("Nouvelle partie");
	  private JMenuItem aProposItem = new JMenu("a propos");
	  private JMenu aPropos = new JMenu("A propos");
	  private JMenuItem quitter = new JMenuItem("Quitter");

	 
	  
	  
	  
	public ModeGraphique()
	{
		
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
		
		//Pour la barre de menu
		
		//On initialise nos menus 
		this.fichier.add(nouvellePartie);
	    this.fichier.add(chargerPartie);
	    //Ajout d'un séparateur
	    this.fichier.addSeparator();
	    this.fichier.add(quitter);
	    quitter.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	        System.exit(0);
	      }        
	    });
	    this.aPropos.add(aProposItem);
	    this.menuBar.add(fichier);
	    this.menuBar.add(aPropos);
	    this.setJMenuBar(menuBar);
	}
	

	public void paintComponent(Graphics g){
        super.paintComponents(g);
        this.setBackground(new Color(200,200,200));
 
    }
	
	
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
	
	public String demanderPseudo() 
	{
			return param.demanderPseudo();
	}
		
	
	public int demanderCarteAJouer(Joueur joueur) 
	{
		this.joueurActuel=joueur;
		this.refresh();
		return this.demanderChoix(0, joueur.getNombreCarte());
	}
	

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



	public void cacherJeu()
	{
		main.vider();
		this.repaint();
		this.setVisible(true);
		
		
	}
	
	public void afficherFinManche(Partie p) {

		this.cacherJeu();
		rapport.refreshscore(p);
		
		JOptionPane.showMessageDialog(null, "La manche est finie, tenez vous pr�t !", "Fin de la manche", JOptionPane.WARNING_MESSAGE);
		
		rapport.refresh();
		tableauDeBord.setManche(p.getManche());
		this.repaint();
	}

	

	@Override
	public void afficherTour(Joueur joueur, int choix) {
		
		rapport.refreshJoueur(joueur);
		
		
		//TODO maj machin a posé truc
		
		this.cacherJeu();
		if(partie.getJoueur(Math.abs((partie.getJoueurActuel()+partie.getSens())%partie.getNbreJoueur())) instanceof JoueurNormal)
			JOptionPane.showMessageDialog(null, " Appuyez sur ok lorsque vous serez pret", ("Au tour de "+ (String)partie.getJoueur(Math.abs((partie.getJoueurActuel()+partie.getSens())%partie.getNbreJoueur())).afficherPseudo()), JOptionPane.WARNING_MESSAGE);
		
	
	}

	
	@Override
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

	public void afficherMauvaisChoix() {
		JOptionPane.showMessageDialog(null, "Cette carte ne peut etre posée", "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	@Override
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


	public int demanderBluff(Partie partie) {
		// TODO Auto-generated method stub
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


	public void declarerUno() {
		
		obs.declarerUno();
	}
	
	public void afficherCartePioche(Joueur joueur) {
		//Inclut dans poser carte pioche, ici non met a jour le nombree du carte du joueur
		
		rapport.refreshJoueur(joueur);
		

	}

	
	public void setController(Observateur obs)
	{
		this.obs=obs;
	}
	
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
	
	@Override
	public int demanderUno() {
		//Ne sert a rien en mode graphique
		return 0;
	}
	
	//Lance l'action
	public void contreUno() {
		obs.declarerContreUno();
	}
	
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
	
	//Demande a qui on fait un contre uno
	public int demanderContreUno(Partie partie) {
		// TODO Auto-generated method stub
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

	
	
	
	@Override
	public void afficherFinPartie(Partie p) {
		// TODO Auto-generated method stub

	}
	


	
	@Override
	public void afficherPasse(Joueur joueur) {
		
		if(joueur instanceof JoueurNormal)
		{
			JOptionPane.showMessageDialog(null, "Une carte Passe � �t� jou�e, vous passez votre tour " + joueur.afficherPseudo() + " !", "Carte Passe", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
}
