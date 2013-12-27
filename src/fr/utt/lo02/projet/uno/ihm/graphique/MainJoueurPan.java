package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

import fr.utt.lo02.projet.uno.noyau.carte.Carte;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;

public class MainJoueurPan extends JPanel implements ActionListener{

	public static final int hCarte=175;
	public static final int lCarte=125;
	private int choix;
	private Joueur joueur;
	private ArrayList<ImageCarte> main; 

	public MainJoueurPan()
	{
		
		joueur = null;
		choix = -1;

		this.setLayout(new FlowLayout());
		for(int i=0; i<5; i++)
		{
			this.add(new ImageCarte());
		}
		
	}

	public MainJoueurPan(Joueur joueur) {
		 main = new ArrayList<ImageCarte>();
		main.clear();
		 choix = -1;
		 this.joueur = joueur;
		 this.setLayout(new FlowLayout());
		 for (int i=0; i<joueur.getNombreCarte(); i++)
		 {
             
             ImageCarte c = new ImageCarte(joueur.getMain().getMain().get(i)); 
             main.add(c);
             this.add(c);
		 }

	}

	
	
	public int getChoix()
	{
		while(choix == -1)
		{
			for(int i=0; i<main.size(); i++)
			{
				main.get(i).addActionListener(this);
			}
		}
		System.out.println(choix);
		this.removeAll();
		return choix;
	}

	@Override
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
