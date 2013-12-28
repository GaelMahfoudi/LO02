package fr.utt.lo02.projet.uno.ihm.graphique;


import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import fr.utt.lo02.projet.uno.ihm.observer.UnoController;
import fr.utt.lo02.projet.uno.ihm.observer.View;
import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public class ModeGraphique extends JFrame implements View{

	public final static int hFenetre = 700;
	public final static int lFenetre = 800;
	private Partie partie;
	private ParametrerPartie param;
	private MainJoueurPan main;
	private TablePan table;

	
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
		partie = param.recupererPartie(partie, controller);
		this.partie = partie;
		table = new TablePan();
		this.getContentPane().add(table, BorderLayout.CENTER);

		main = new MainJoueurPan();
		this.getContentPane().add(main, BorderLayout.SOUTH);
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
		//Rafraichissement de la table de jeu
		table.removeAll();
		table = new TablePan();
		this.getContentPane().add(table, BorderLayout.CENTER);
		
		//Rafraichissement de la main du joueur
		main.removeAll();
		main = new MainJoueurPan(joueur);
		
		this.getContentPane().add(main, BorderLayout.SOUTH);
		
		
		this.setVisible(true);
		return this.demanderChoix(0, joueur.getNombreCarte()+1);
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
			return (partie.getJoueur( partie.getJoueurActuel() ).getNombreCarte());
		}
		else
		{
			return choixCarte;
		}
	}



	public void afficherFinManche(Partie p) {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void afficherFinPartie(Partie p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afficherTour(Joueur joueur, int choix) {
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
			return 0;
	}

	@Override
	public void afficherCartePioche(Joueur joueur) {
		// Sans interet dans le mode graphique... fonction incluse dans poserCartePioche;
		

	}

	@Override
	public int demanderBluff(Partie partie) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void afficherMauvaisChoix() {
		JOptionPane.showMessageDialog(null, "Cette carte ne peut etre posée", "Erreur", JOptionPane.ERROR_MESSAGE);
		
	}

	@Override
	public ECouleur demanderCouleur() {
		// TODO Auto-generated method stub
		return null;
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
