package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OptionJeu extends JPanel implements ActionListener{

	private Bouton direUno = new Bouton("Dire Uno");
	private Bouton direContreUno = new Bouton("Dire contre-uno");
	private Bouton pause = new Bouton("Mettre le jeu en pause");
	private ModeGraphique mg;
	
	public OptionJeu(ModeGraphique mg)
	{
		this.mg=mg;
		this.setLayout(new GridLayout(3, 1));
		this.add(pause);
		this.add(direUno);
		this.add(direContreUno);
		pause.addActionListener(this);
		direUno.addActionListener(this);
		direContreUno.addActionListener(this);
	}

	

	@Override
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
