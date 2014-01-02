package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.utt.lo02.projet.uno.noyau.carte.Carte;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;


//Amelioration a prevoir: gliser les cartes pour les ranger dans un autre ordre?


public class MainJoueurPan extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8769487842894870766L;
	public static final int hCarte=175;
	public static final int lCarte=125;
	private int choix;
	private Joueur joueur;
	private ArrayList<ImageCarte> main; 
	private JPanel mainPane;
	private JScrollPane scroll;
	

	public MainJoueurPan()
	{
		super();

		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setOpaque(false);
		this.setLayout(new GridLayout(1,1));
		mainPane = new JPanel();
		mainPane.setOpaque(false);
		scroll = new JScrollPane(mainPane); 
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Pas de barre verticale
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); //Barre horizontale
		scroll.setOpaque(false);
		scroll.setBorder(null);
		scroll.getViewport().setOpaque(false);
		
		   
		joueur = null;
		choix = -1;

		for(int i=0; i<5; i++)
		{
			mainPane.add(new ImageCarte());

		}
		this.add(scroll);
		this.setOpaque(false);
	}

	public void refresh(Joueur joueur) {
		
		 mainPane.removeAll();
		 mainPane.setOpaque(false);
		 main = new ArrayList<ImageCarte>();
		 choix = -1;
		 this.joueur = joueur;
		 
		 
		 for (int i=0; i<joueur.getNombreCarte(); i++)
		 {
             ImageCarte c = new ImageCarte(joueur.getMain().getMain().get(i)); 
             main.add(c);
             mainPane.add(c);
		 }
	}

	
	public void vider() {
		mainPane.removeAll();
		for(int i=0; i<5; i++)
		{
			mainPane.add(new ImageCarte());
		}
	}
	
	
	public int getChoix()
	{
		for(int i=0; i<main.size(); i++)
		{
			ImageCarte c = (ImageCarte) main.get(i);
			this.addListeners(c);
		}
		
		return choix;
	}


	
	private void addListeners(ImageCarte c) {
			c.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		ImageCarte carteG = (ImageCarte)e.getSource();
		Carte carte = carteG.getCarte();
		for (int i=0; i<joueur.getNombreCarte(); i++)
		{
			if( carte.equals(joueur.getMain().getMain().get(i)) )
			{

				choix = i;
			}
		}
	}
}
