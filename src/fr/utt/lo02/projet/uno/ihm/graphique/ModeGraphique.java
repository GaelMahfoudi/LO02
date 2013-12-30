package fr.utt.lo02.projet.uno.ihm.graphique;


import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.utt.lo02.projet.uno.ihm.observer.UnoController;
import fr.utt.lo02.projet.uno.ihm.observer.View;
import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public class ModeGraphique extends JFrame implements View{

	public final static int hFenetre = 700;
	public final static int lFenetre = 800;
	//private Partie partie;
	private ParametrerPartie param;
	private MainJoueurPan main;
	private TablePan table;
	private TableauDeBord tableauDeBord;
	private RapportDActivite rapport;
	private Partie partie;
	private OptionJeu option;
	
	public ModeGraphique()
	{
		param = new ParametrerPartie();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(lFenetre, hFenetre);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
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
		
		
		
		this.setVisible(true);
		JOptionPane.showMessageDialog(null, " Appuyez sur ok lorsque vous serez pret", ("Au tour de "+ (String)partie.getJoueur(partie.getJoueurActuel()).afficherPseudo()), JOptionPane.WARNING_MESSAGE);
		
		
		return partie;
	}
	
	public String demanderPseudo() 
	{
			return param.demanderPseudo();
	}
		
	
	public int demanderCarteAJouer(Joueur joueur) 
	{
		this.refresh(joueur);
		
		return this.demanderChoix(0, joueur.getNombreCarte());
	}
	

	private void refresh(Joueur joueur) {
		
		
				
				
				//Rafraichissement du tableau de bord
				tableauDeBord.setJoueur(joueur);
				tableauDeBord.refresh();
				
				//Rafraichissement de la table de jeu
				table.refresh();
				//Rafraichissement de la main du joueur
				main.refresh(joueur);
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



	public void afficherFinManche(Partie p) {

		main.vider();
		
		
		JOptionPane.showMessageDialog(null, "La manche est finie, tenez vous pr�t !", "Fin de la manche", JOptionPane.WARNING_MESSAGE);
		
		rapport.refresh();
		tableauDeBord.setManche(p.getManche());
		this.repaint();
	}

	@Override
	public void afficherFinPartie(Partie p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afficherTour(Joueur joueur, int choix) {
		
		rapport.refreshJoueur(joueur);
		
		
		//TODO maj machin a posé truc
		
		main.vider(); //Protection de la main du joueur precedent
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

	@Override
	public void afficherCartePioche(Joueur joueur) {
		//NE sert a rien en mode graphique... inclut dans poserCartePioche
		

	}

	@Override
	public int demanderBluff(Partie partie) {
		int choix = 
		return 0;
	}

	@Override
	public void afficherMauvaisChoix() {
		JOptionPane.showMessageDialog(null, "Cette carte ne peut etre posée", "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public ECouleur demanderCouleur() {
		String[] couleur = {"Bleu", "Rouge", "Jaune", "Vert"};
	    
		 int rang = JOptionPane.showOptionDialog(null, "Veuillez choisir la nouvelle couleur:", "Ghangement de couleur", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, couleur, "echec");
		switch(rang)
		{
			case 0:
				return ECouleur.BLEU;
			case 1:
				return ECouleur.JAUNE;
			case 2:
				return ECouleur.ROUGE;
			case 3:
				return ECouleur.VERT;
			default:
				return this.demanderCouleur();
		}
	}

	@Override
	public int demanderUno() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int demanderContreUno() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	



	

	
	
	

}
