package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OptionJeu extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6041778679539547059L;
	private Bouton direUno = new Bouton("Uno");
	private Bouton direContreUno = new Bouton("Contre-uno");
	private Bouton pause = new Bouton("Pause");
	private ModeGraphique mg;
	
	public OptionJeu(ModeGraphique mg)
	{
		this.mg=mg;
		this.setPreferredSize(new Dimension(200,150));
		this.setLayout(new GridLayout(3, 1));
		

		this.direContreUno.setOpaque(false);
		this.direContreUno.setContentAreaFilled(false);
		this.direContreUno.setBorderPainted(false);
		
		this.direUno.setOpaque(false);
		this.direUno.setContentAreaFilled(false);
		this.direUno.setBorderPainted(false);
		
		this.pause.setOpaque(false);
		this.pause.setContentAreaFilled(false);
		this.pause.setBorderPainted(false);
		
		this.add(pause);
		this.add(direUno);
		this.add(direContreUno);
		pause.addActionListener(this);
		direUno.addActionListener(this);
		direContreUno.addActionListener(this);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		this.setOpaque(false);
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
