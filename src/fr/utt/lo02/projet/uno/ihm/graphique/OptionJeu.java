package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Classe OptionJeu,
 * contient la possibilité de declarer un Uno, un contre Uno ou mettre le jeu en pause
 * @author Gael et Victor
 *
 */
public class OptionJeu extends JPanel implements ActionListener{

	
	private static final long serialVersionUID = -6041778679539547059L;
	/**
	 * @see Bouton
	 * bouton qui permettra de declarer un Uno
	 */
	private Bouton direUno = new Bouton("Uno");
	/**
	 * @see Bouton
	 * Bouton qui permet de declarer un Contre Uno
	 */
	private Bouton direContreUno = new Bouton("Contre-uno");
	/**
	 * @see Bouton
	 * Bouton qui permet de mettre le jeu en pause
	 */
	private Bouton pause = new Bouton("Pause");
	
	/**
	 * Lien vers ModeGraphique
	 * @see ModeGraphique
	 */
	private ModeGraphique mg;
	
	
	/**
	 * Constructeur de la classe,
	 * @param mg
	 * 		ModeGraphique instanciant cette classe
	 * 		met en place les ActionLIstener sur les bouton
	 * @see ModeGraphique
	 * @see ActionListener
	 */
	public OptionJeu(ModeGraphique mg)
	{
		this.mg=mg;
		this.setPreferredSize(new Dimension(200,150));
		this.setLayout(new GridLayout(3, 1));
	
		
		this.add(pause);
		this.add(direUno);
		this.add(direContreUno);
		pause.addActionListener(this);
		direUno.addActionListener(this);
		direContreUno.addActionListener(this);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		this.setOpaque(false);
	}

	

	/**
	 * @see ActionListener#actionPerformed(ActionEvent)
	 * Selon la source de l'evenement, on lance la methode associée de ModeGraphique
	 * @see ModeGraphique
	 */
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(direUno))
		{
			mg.declarerUno();
		}
		else if (arg0.getSource().equals(pause))
		{
			mg.cacherJeu();
			JOptionPane.showMessageDialog(null, " Appuyez sur ok lorsque vous voudez reprendre la partie", "Jeu en pause", JOptionPane.WARNING_MESSAGE);
			mg.refresh();
		}
		else if (arg0.getSource().equals(direContreUno))
		{
			mg.contreUno();
		}
	}
}
