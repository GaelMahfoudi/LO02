package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.utt.lo02.projet.uno.ihm.observer.UnoController;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public class ParametrerPartie  extends JFrame implements ActionListener{

	private boolean nbreJoueurOk;
	private JComboBox<?> comboIA;
	private JComboBox<?> comboReel;
	private Bouton nouvellePartie;
	private int nbreJoueurReel;
	private int nbreIA;
	

	public ParametrerPartie()
	{
		nbreJoueurReel = 0;
		nbreIA =0;
		nbreJoueurOk = false;

		this.setSize(300, 300);
		
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

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	 
	
	
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
	
	public String demanderPseudo() {
	
	
	String nom = null;

	while(nom == null)
	{
		nom = JOptionPane.showInputDialog(null, "Donnez le nom du joueur", "Nom", JOptionPane.QUESTION_MESSAGE);
	}
	return nom;
}

	
	
}
