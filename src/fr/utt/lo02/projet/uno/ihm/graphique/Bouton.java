package fr.utt.lo02.projet.uno.ihm.graphique;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * 
 * @author Gael, Victor
 * IL s'agit ici d'un JButton personnalisé par 3 images differentes selon l'attitude la souris, cette classe est utilisée pour tous les boutons du jeu
 *
 */
@SuppressWarnings("serial")
public class Bouton extends JButton implements MouseListener
{
	/**
	 * Image du bouton
	 */
	private Image img;
	/**
	 * Nom du bouton (String), qui est ecrit par dessus l'image
	 */
	private String name;
	
	/**
	 * Constructeur de la classe, il initialise le bouton, en chargeant son image et ajoute un MouseListener sur lui meme
	 * @param name
	 * 				le nom du bouton
	 * @see MouseListener
	 */
	public Bouton(String name)
	{
		super(name);

		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.name = name;
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		try {
		      img = ImageIO.read(new File(ImageCarte.pathImage+ "/theme/bouton.png"));
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		this.setOpaque(false);
		this.addMouseListener(this);
	}
	
	/**
	 * Redefinission de paintComponent de la classe JComponent.
	 * Elle permet desormais de rafraichir l'image en fonction de la position de la souris
	 * @param Graphics g
	 */
	 public void paintComponent(Graphics g){

		 	super.paintComponent(g);
		    Graphics2D g2d = (Graphics2D)g;
		    GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
		    g2d.setPaint(gp);
		    g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		    g2d.setColor(Color.black);
		    g2d.drawString(this.name, this.getWidth() / 2 - (this.getWidth() / 2 /4), (this.getHeight() / 2) + 5);

			this.setOpaque(false);
			this.setContentAreaFilled(false);
			this.setBorderPainted(false);
	 }

	
	public void mouseClicked(MouseEvent arg0) {
		// Pas d'utilité ici
		
	}

	
	/**
	 * @see MouseListener#mouseEntered(MouseEvent)
	 * change l'image associé a img. Au prochain paintComponent, le bouton changera d'image
	 */
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		try {
		      img = ImageIO.read(new File(ImageCarte.pathImage+ "/theme/boutonEntered.png"));
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
			this.repaint();
	}

	/**
	 * @see MouseListener#mouseExited(MouseEvent)
	 * change l'image associé a img. Au prochain paintComponent, le bouton changera d'image
	 */
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		try {
		      img = ImageIO.read(new File(ImageCarte.pathImage+ "/theme/bouton.png"));
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		this.repaint();
	}

	/**
	 * @see MouseListener#mousePressed(MouseEvent)
	 * change l'image associé a img. Au prochain paintComponent, le bouton changera d'image
	 */
	public void mousePressed(MouseEvent arg0) {
		try {
		      img = ImageIO.read(new File(ImageCarte.pathImage+ "/theme/boutonPressed.png"));
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
			this.repaint();
		
	}
		
	/**
	 * @see MouseListener#mouseReleased(MouseEvent)
	 * change l'image associé a img. Au prochain paintComponent, le bouton changera d'image
	 */
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.mouseExited(arg0);
	}
}