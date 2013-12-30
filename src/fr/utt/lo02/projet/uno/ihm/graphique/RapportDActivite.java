package fr.utt.lo02.projet.uno.ihm.graphique;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public class RapportDActivite extends JPanel{

	
	private JTextArea nbreCarte;
	private int nbreJoueur;
	private String[] joueurCarte;
	public RapportDActivite(Partie partie)
	{

		this.nbreJoueur = partie.getNbreJoueur();
		joueurCarte = new String[nbreJoueur];
		 nbreCarte = new JTextArea();
			
		nbreCarte.setLineWrap(false); //Interdit les retours a la ligne automatique
		for(int i= 0; i< partie.getNbreJoueur(); i++)
        {
        	joueurCarte[i] = partie.getJoueur(i).afficherPseudo()+ ": 5 restantes" ;
        }
		this.refresh();
	}
	public void refresh()
	{
		this.removeAll();
		//Refresh de nbreCarte
        nbreCarte = new JTextArea("Nombre de cartes restantes:");
		for(int i=0; i< this.nbreJoueur; i++)
		{
			nbreCarte.append("\n");
			nbreCarte.append(joueurCarte[i]);
		}
		this.add(nbreCarte);
		
	}

	
	public void refreshJoueur(Joueur joueur)
	{
		for(int i=0; i<this.nbreJoueur; i++)
		{
				
			if(joueurCarte[i].contains(joueur.afficherPseudo()))
			{
				if(joueur.getUno())
					joueurCarte[i] = joueur.afficherPseudo()+ ": " +joueur.getNombreCarte()+" restantes (uno)" ;
				else
					joueurCarte[i] = joueur.afficherPseudo()+ ": " +joueur.getNombreCarte()+" restantes" ;
					
				
			}
		}
	}
}
