package fr.utt.lo02.projet.uno.ihm.graphique;


import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.utt.lo02.projet.uno.ihm.observer.UnoController;
import fr.utt.lo02.projet.uno.ihm.observer.View;
import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public class ModeGraphique extends JFrame implements View{

	private ParametrerPartie param;
	private MainJoueurPan main;
	
	public ModeGraphique()
	{
		param = new ParametrerPartie();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.setLayout(new BorderLayout());
		
	}

	
	
	public Partie demarrerPartie(Partie partie, UnoController controller) 
	{
		partie = param.recupererPartie(partie, controller);
		this.setVisible(true);
		return partie;
	}
	
	public String demanderPseudo() 
	{
			return param.demanderPseudo();
	}
		
	
	public int demanderCarteAJouer(Joueur joueur) 
	{

		main = new MainJoueurPan();
		this.getContentPane().add(main, BorderLayout.SOUTH);
		this.setVisible(true);
		
		JOptionPane jop2 = new JOptionPane();
		jop2.showMessageDialog(null, " Appuyez sur ok lorsque vous serez pret", ("Au tour de "+ (String)joueur.afficherPseudo()), JOptionPane.WARNING_MESSAGE);
		main.removeAll();
		main = new MainJoueurPan(joueur);
		this.getContentPane().add(main, BorderLayout.SOUTH);
		this.setVisible(true);
		return main.getChoix();
		
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
		// TODO Auto-generated method stub

	}

	
	@Override
	public int poserCartePioche(Joueur joueur) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void afficherCartePioche(Joueur joueur) {
		// TODO Auto-generated method stub

	}

	@Override
	public int demanderBluff(Partie partie) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void afficherMauvaisChoix() {
		JOptionPane jop2 = new JOptionPane();
		jop2.showMessageDialog(null, "Cette carte ne peut etre posée", "Erreur", JOptionPane.ERROR_MESSAGE);
		
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

	
	

	@Override
	public int demanderChoix(int min, int max) {
		int choix = min-1;

		while(choix<min || choix > max)
		{
			JOptionPane o = new JOptionPane();

			choix = Integer.parseInt(o.showInputDialog(null, "Donnez votre choix entre " + min + " et "+ max, "Choix", JOptionPane.QUESTION_MESSAGE));
		}
		return choix;
	}

	

	
	
	

}
