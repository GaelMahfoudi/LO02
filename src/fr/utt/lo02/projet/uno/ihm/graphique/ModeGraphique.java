package fr.utt.lo02.projet.uno.ihm.graphique;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.utt.lo02.projet.uno.ihm.observer.UnoController;
import fr.utt.lo02.projet.uno.ihm.observer.View;
import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public class ModeGraphique extends JFrame implements View, ActionListener, ItemListener{

	private JComboBox comboReel;
	private JComboBox comboIA;
	private JLabel nomJoueur;
	public int nbreJoueurReel;
	public int nbreIA;
	private JTextField nomJoueur_text;
	private JButton nouvellePartie;
	private JButton nomOk;
	private boolean ok;
	private boolean nbreJoueurOk;
	
	public ModeGraphique()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		nbreJoueurReel = 0;
		nbreIA =0;
	}

	
	
	public Partie demarrerPartie(Partie partie, UnoController controller) {
		this.nbreJoueurOk = false;
		 this.setLayout(new GridLayout(3, 1));
		 //Generation des champs 

		 
		 JPanel panReel = new JPanel();
		 JPanel panIA = new JPanel();
		 
		 JLabel labelReel = new JLabel("Nombre de joueurs reels: ");
		 JLabel labelIA = new JLabel("Nombre de joueurs Virtuels: ");
		 
		 panReel.add(labelReel);
		 panIA.add(labelIA);
		 
		 String[] tab = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		 comboReel = new JComboBox(tab);
		 comboIA = new JComboBox(tab);
		 
		 panReel.add(comboReel);
		 panIA.add(comboIA);
		 
		 this.getContentPane().add(panReel);
		 this.getContentPane().add(panIA);
		 //Ajout des listener
		 comboReel.addItemListener(this);
		 comboIA.addItemListener(this);
		 
		 //Bouton de validation
		 nouvellePartie = new JButton("Lancer la partie");
		 nouvellePartie.addActionListener(this);
		 this.getContentPane().add(nouvellePartie);
		 
		this.setVisible(true);
		int nbreJoueur = nbreJoueurReel+nbreIA;
		while(nbreJoueur == 0 || nbreJoueur > 10)
		{
			//On attend
			//TODO une autree solution d'attente
		}
		partie = new Partie(nbreJoueurReel, nbreIA, controller);
		return partie;
	}
	
	     
	
	public void itemStateChanged(ItemEvent e) 
	{

		if(e.getSource().equals(comboIA))
		{
			this.nbreIA = Integer.parseInt((String) e.getItem());
		}
		else if(e.getSource().equals(comboReel))
		{
			this.nbreJoueurReel = Integer.parseInt((String) e.getItem());
		}
	}
		   
	public void actionPerformed(ActionEvent arg0) {
		
		//Lorsque l'on clique sur le bouton, on verifie le que le nombre de joueur n'est pas superieur a 10 et different de 0
		
		
		if(arg0.getSource().equals(nouvellePartie))
		{
			int nbJoueur = nbreJoueurReel+nbreIA;			
			if( nbJoueur == 0 || nbJoueur > 10)
			{
				JFrame fen = new JFrame();
				fen.setSize(300, 300);
				fen.setLocationRelativeTo(null);
				fen.setResizable(false);
				JPanel container = new JPanel();
				JLabel label = new JLabel("Erreur, veuillez revoir les parametres entrés (max 10 joueurs)");
				
				Font police = new Font("Tahoma", Font.BOLD, 16);  
				label.setFont(police);  
				label.setForeground(Color.blue);  
				label.setHorizontalAlignment(JLabel.CENTER);
				container.add(label, BorderLayout.NORTH);
				fen.setContentPane(container);
				fen.setVisible(true);
			}
		}
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

	

	
	
	public void setOk(boolean b)
	{
		ok = b;
	}

}
