package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.utt.lo02.projet.uno.ihm.observer.Observateur;
import fr.utt.lo02.projet.uno.ihm.observer.UnoController;
import fr.utt.lo02.projet.uno.ihm.observer.View;
import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public class ModeGraphique extends JFrame implements View{

	private JLabel nbJoueurReel;
	private JLabel nomJoueur;
	private JLabel nbIA;
	private JTextField nbJoueurReel_text;
	private JTextField nomJoueur_text;
	private JTextField nbIA_text;
	private JButton nouvellePartie;
	private JButton nomOk;
	private JPanel pan;

	public ModeGraphique()
	{
		pan = new JPanel();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	@Override
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
	public int demanderCarteAJouer(Joueur joueur) {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub

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
	public String demanderPseudo() {
		pan.removeAll();

		String nom = null;

		while(nom == null)
		{
			JOptionPane o = new JOptionPane();

			nom = o.showInputDialog(null, "Donnez le nom du joueur", "Nom", JOptionPane.QUESTION_MESSAGE);
		}
		return nom;
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

	@Override
	public Partie demarrerPartie(Partie partie, UnoController controller) {
		
		
		return partie;
		
	}

	public JTextField getJoueurText()
	{
		return nbJoueurReel_text;
	}

	public JTextField getIAText()
	{
		return nbIA_text;
	}
	
	public void setOk(boolean b)
	{
		ok = b;
	}

}
