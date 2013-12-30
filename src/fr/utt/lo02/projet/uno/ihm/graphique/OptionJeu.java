package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OptionJeu extends JPanel implements ActionListener{

	private JButton direUno = new JButton("Dire Uno");
	private JButton direContreUno = new JButton("Dire contre-uno");
	private JButton pause = new JButton("Mettre le jeu en pause");
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
			
		}
	}
}
