package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.utt.lo02.projet.uno.noyau.gestion.carte.Talon;

/**
 * Classe TablePan heritant de JPanel
 * Cette classe est destin a afficher la carte du Talon et la pioche
 * @author Victor & Gael
 *
 */
public class TablePan extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8312818601075105050L;
	/**
	 * JPanel qui va contenr une image de Carte factice (face cachée) qui permet de piocher i on clique dessus
	 * @see ImageCarte
	 */
	private JPanel pioche;
	/**
	 * JPanel qui affiche la dernier carte du Talon
	 */
	private JPanel talon;
	/**
	 * Boolean qui correspond au choix de l'utilisateur (s'il veut pioher ou non)
	 */
	private boolean choixPioche;
	/**
	 * Representation de la derniere carte du talon
	 */
	private ImageCarte carteTalon;
	/**
	 * Image factice (face cachée, associé a aucune Carte
	 */
	private ImageCarte cartePioche;
	/**
	 * afficher "Piocher" a coté de cartePioche
	 */
	private JLabel labelPioche;
	/**
	 * afficher "Talon" a coter de carteTalon
	 */
	private JLabel labelTalon;
	
	
	/**
	 * Constructeur de la classe
	 * Met en place la position des composants  en attributs de la classe
	 */
	public TablePan()
	{
		setChoixPioche(false);
		this.setLayout(new FlowLayout());
		this.setOpaque(false);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		
		
	
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
	
	/**
	 * Personnalise les JLabel de la classe: transparence, couleur et police du texte
	 * @param c
	 * 			Le JLabel a personnaliser
	 */
	public void personnaliser (JLabel c)
	{
		c.setOpaque(false);
		c.setForeground(Color.white);
		c.setFont(new Font("Arial", Font.BOLD, 20));
		c.setHorizontalAlignment(JTextField.CENTER);
	}
	
	/**
	 * rafraichit le panneau selon la derniere carte du talon
	 */
	public void refresh()
	{
		setChoixPioche(false);
		
		carteTalon = new ImageCarte(Talon.getInstance().getDerniereCarte());
		talon.removeAll();
		this.talon.add(carteTalon);
		this.talon.add(labelTalon);
		
	}
	
	
	/**
	 * Renvoie true si le joueur a choisis de piocher, false sinon (et par defaut)
	 * @return
	 *  Renvoie un boolean �tant le choix du joueur
	 */
	public boolean isChoixPioche() {
		
		cartePioche.addActionListener(this);
		return choixPioche;
	}

	/**
	 * setter de choixPioche
	 * @see TablePan#choixPioche
	 * @param choixPioche
	 */
	public void setChoixPioche(boolean choixPioche) {
		this.choixPioche = choixPioche;
	}

	/**
	 * @see ActionListener#actionPerformed(ActionEvent)
	 * si l'on clique sur la pioche, on met choixPioche a true
	 */
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(cartePioche))
		{
			this.setChoixPioche(true);
		}
	}

	
	
}
