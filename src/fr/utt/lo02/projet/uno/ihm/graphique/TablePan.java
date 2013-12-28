package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.utt.lo02.projet.uno.noyau.carte.Carte;
import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;

public class TablePan extends JPanel implements ActionListener{
	
	private JPanel pioche;
	private JPanel talon;
	private boolean choixPioche;
	private ImageCarte carteTalon;
	private ImageCarte cartePioche;
	private JLabel labelPioche;
	private JLabel labelTalon;
	
	public TablePan()
	{
		setChoixPioche(false);
		this.setLayout(new FlowLayout());
		
		pioche = new JPanel();
		pioche.setLayout(new GridLayout(2,1));
		labelPioche = new JLabel("Piocher");
		cartePioche = new ImageCarte();
		this.pioche.add(cartePioche);
		this.pioche.add(labelPioche);
		this.add(pioche);
		
		talon = new JPanel();
		talon.setLayout(new GridLayout(2,1));
		labelTalon = new JLabel("Carte du Talon");
		carteTalon = new ImageCarte();
		this.add(talon);
		
		
	}
	
	public void refresh()
	{
		setChoixPioche(false);
		
		carteTalon = new ImageCarte(Talon.getInstance().getDerniereCarte());
		talon.removeAll();
		this.talon.add(carteTalon);
		this.talon.add(labelTalon);
		
	}
	public boolean isChoixPioche() {
		
		cartePioche.addActionListener(this);
		return choixPioche;
	}

	public void setChoixPioche(boolean choixPioche) {
		this.choixPioche = choixPioche;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(cartePioche))
		{
			this.choixPioche = true;
		}
	}

	
	
}
