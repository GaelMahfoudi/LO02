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
	
	public TablePan()
	{
		setChoixPioche(false);
		this.setLayout(new FlowLayout());
		
		pioche = new JPanel();
		pioche.setLayout(new GridLayout(2,1));
		JLabel labelPioche = new JLabel("Piocher");
		
		talon = new JPanel();
		talon.setLayout(new GridLayout(2,1));
		JLabel labelTalon = new JLabel("Carte du Talon");
		cartePioche = new ImageCarte();
		this.pioche.add(cartePioche);
		this.pioche.add(labelPioche);
		carteTalon = new ImageCarte(Talon.getInstance().getDerniereCarte());
		this.talon.add(carteTalon);
		this.talon.add(labelTalon);
		
		
		this.add(pioche);
		this.add(talon);
		
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
