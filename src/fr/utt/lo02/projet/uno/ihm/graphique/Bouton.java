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


@SuppressWarnings("serial")
public class Bouton extends JButton implements MouseListener
{
	
	private Image img;
	private String name;
	
	
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

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		try {
		      img = ImageIO.read(new File(ImageCarte.pathImage+ "/theme/boutonEntered.png"));
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
			this.repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		try {
		      img = ImageIO.read(new File(ImageCarte.pathImage+ "/theme/bouton.png"));
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		this.repaint();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		try {
		      img = ImageIO.read(new File(ImageCarte.pathImage+ "/theme/boutonPressed.png"));
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
			this.repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.mouseExited(arg0);
	}
}