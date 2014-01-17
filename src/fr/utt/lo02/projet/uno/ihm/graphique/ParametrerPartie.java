package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.utt.lo02.projet.uno.ihm.observer.UnoController;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;


/**
 * Classe ParametrerPartie
 * Permet, avant de lancer la partie, de recuperer le nombre de Joueurs Reel (et leur noms) ainsi que le nombre de joueurs virtuels
 * 
 * @author Victor et Gael
 *
 */
public class ParametrerPartie  extends JFrame implements ActionListener{

	private static final long serialVersionUID = 123317141480211943L;
	/**
	 * Boolean indiquant si les nombres de joueurs choisis par l'utilisateur sont plausible
	 */
	private boolean nbreJoueurOk;
	/**
	 * JComboBox recuperant le nombre de joueurs virtuels
	 */
	private JComboBox<?> comboIA;
	/**
	 * JComboBox recuperant le nombre de joueurs Reels
	 */
	private JComboBox<?> comboReel;
	/**
	 * @see Bouton
	 * Bouton de validation
	 */
	private Bouton nouvellePartie;
	/**
	 * entier recuperant le nombre de joueur reels choisis dans la JComboBox associée
	 * @see ParametrerPartie#comboReel
	 */
	private int nbreJoueurReel;
	/**
	 * entier recuperant le nombre de joueur virtuels choisis dans la JComboBox associée
	 * @see ParametrerPartie#comboIA
	 */
	private int nbreIA;
	

	/**
	 * Constructeur de la classe
	 * Créé la fenetre
	 */
	@SuppressWarnings("serial")
	public ParametrerPartie()
	{
		nbreJoueurReel = 0;
		nbreIA =0;
		nbreJoueurOk = false;
		this.setTitle("Bienvenue");
		this.setSize(300, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		
		this.setContentPane(new JPanel()
		{
			private Image img = null;
				public void paintComponent(Graphics g) {
				try {
					img = ImageIO.read(getClass().getResourceAsStream("/theme/FondUno.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Graphics2D g2d = (Graphics2D)g;
			    GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
			    g2d.setPaint(gp);
			    g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			    
			    }
		});

		
		
	}
	 
	
	/**
	 * Créé la les composants de la fenetre afin de recuperer les choix de parametrage de la partie de l'utilisateur
	 * Recupere en parametre une partie vide, l'instancie selon les parametres choisis par l'utilisateur
	 * Il renvoie ensuite la partie ainsi créée
	 * @param partie
	 * @param controller
	 * @return partie
	 */
	public Partie recupererPartie(Partie partie, UnoController controller) {
		this.nbreJoueurOk = false;
		 this.setLayout(new GridLayout(4, 1));
		 //Generation des champs 
		 JPanel panReel = new JPanel();
		 JPanel panIA = new JPanel();
		 
		 JLabel labelReel = new JLabel("Nombre de joueurs reels: ");
		 JLabel labelIA = new JLabel("Nombre de joueurs Virtuels: ");
		 
		 labelReel.setFont( new Font("Arial", Font.BOLD, 14));
		 labelReel.setForeground(Color.white);
		 
		 labelIA.setFont( new Font("Arial", Font.BOLD, 14));
		 labelIA.setForeground(Color.white);
		 panReel.add(labelReel);
		 panIA.add(labelIA);
		 
		 String[] tab = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		 comboReel = new JComboBox<Object>(tab);
		 comboIA = new JComboBox<Object>(tab);
		 
		 panReel.add(comboReel);
		 panReel.setOpaque(false);
		 panIA.add(comboIA);
		 panIA.setOpaque(false);
		 this.getContentPane().add(panReel);
		 this.getContentPane().add(panIA);
		
		 //Bouton de validation
		 nouvellePartie = new Bouton("Lancer la partie");
		 this.getContentPane().add(new JLabel());
		 this.getContentPane().add(nouvellePartie);
		 
		this.setVisible(true);

		nouvellePartie.addActionListener(this);


		while(!nbreJoueurOk)
		{
			comboReel.addActionListener(this);
			comboIA.addActionListener(this);

			int nbreJoueur = nbreJoueurReel+nbreIA;
			if(nbreJoueur > 0 && nbreJoueur <= 10)
			{
				nbreJoueurOk = true;
				partie = new Partie(nbreJoueurReel, nbreIA, controller);
				
			}
		}
		this.setVisible(false);
		return partie;
	}

	/**
	 * @see ActionListener#actionPerformed(ActionEvent)
	 * Verifie si les valeurs sont correct.
	 */
	public void actionPerformed(ActionEvent arg0) {
		
		//Lorsque l'on clique sur le bouton, on verifie le que le nombre de joueur n'est pas superieur a 10 et different de 0
		
		
		if(arg0.getSource().equals(nouvellePartie))
		{
			this.nbreIA = Integer.parseInt((String) comboIA.getSelectedItem());
			this.nbreJoueurReel = Integer.parseInt((String) comboReel.getSelectedItem());
			int nbJoueur = this.nbreJoueurReel+this.nbreIA;			
			if( nbJoueur == 0 || nbJoueur > 10)
			{
				JOptionPane.showMessageDialog(null, "Le nombre de joueur total doit etre compris entre 1 et 10", "Attention", JOptionPane.WARNING_MESSAGE);
				
			}
		}
		
		
	}
	
	/**
	 * recupere le pseudo d'un joueur
	 * @return pseudo (String)
	 */
	public String demanderPseudo() {
	
	
	String nom = null;

	while(nom == null)
	{
		nom = JOptionPane.showInputDialog(null, "Donnez le nom du joueur", "Nom", JOptionPane.QUESTION_MESSAGE);
	}
	return nom;
}

	
	
}
