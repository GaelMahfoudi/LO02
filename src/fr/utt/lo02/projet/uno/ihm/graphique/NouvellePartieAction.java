package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.utt.lo02.projet.uno.ihm.console.ModeConsole;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.IA;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.JoueurNormal;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

public class NouvellePartieAction extends AbstractAction {

	private ModeGraphique fenetre;
	public NouvellePartieAction(ModeGraphique fenetre, Partie p, String name)
	{
		super(name);
		
		this.fenetre = fenetre;
	}
	
	public void actionPerformed(ActionEvent event) {
		
	}

}
