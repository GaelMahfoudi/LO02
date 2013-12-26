package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
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
