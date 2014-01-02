package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		this.setOpaque(false);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
	
		pioche = new JPanel();
		pioche.setOpaque(false);
		pioche.setLayout(new FlowLayout());
		labelPioche = new JLabel("Piocher");
		personnaliser(labelPioche);
		cartePioche = new ImageCarte();
		this.pioche.add(cartePioche);
		this.pioche.add(labelPioche);
		this.add(pioche);
		
		
		talon = new JPanel();
		talon.setOpaque(false);
		talon.setLayout(new FlowLayout());
		labelTalon = new JLabel("Talon");
		personnaliser(labelTalon);
		carteTalon = new ImageCarte();
		this.add(talon);

		
		
		
	}
	public void personnaliser (JLabel c)
	{
		c.setOpaque(false);
		c.setForeground(Color.white);
		c.setFont(new Font("Arial", Font.BOLD, 20));
		c.setHorizontalAlignment(JTextField.CENTER);
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
