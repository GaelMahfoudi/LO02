package fr.utt.lo02.projet.uno.ihm.graphique;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import fr.utt.lo02.projet.uno.noyau.carte.Carte;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;


/**
 * 
 * @author Gael, Victor
 * Classe ImageCarte, Il s'agit d'un JButton personnalisé: son image est une carte du Uno et est rattaché a un objet de la classe Carte
 * @see Carte
 *
 */
public class ImageCarte extends JButton {

	
	private static final long serialVersionUID = -6038129419355178442L;
	/**
	 *Entier correspondant a la hauteur de l'image d'une carte
	 */
	public static final int hCarte=175;
	/**
	 *Entier correspondant a la largeur de l'image d'une carte
	 */
	public static final int lCarte=108;
	/**
	 * objet de la classe Carte
	 * @see Carte
	 */
    private Carte carte;
    
    /**
     * L'image associée a la carte
     */
    private Image img = null;
    
    
    
    /**
     * Constructeur de la classe,
     * Sans parametre, il s'agira de la face cachée de la carte (cet objet n'est pas rattaché a une Carte)
     */
    public ImageCarte()
    {
		
         this.carte = null;
         this.setOpaque(false);
    	 this.setPreferredSize(new Dimension(lCarte, hCarte));
         
         // refesh
         this.refresh();
    }
    
    /**
     * Constructeur de la classe, associe le bouton a une Carte
     * @see Carte
     * @param carte
     * 			carte associée au bouton
     */
	public ImageCarte(Carte carte)
	{
		this.setOpaque(false);
		 this.carte= carte;

         // dimension de la carte
         this.setPreferredSize(new Dimension(lCarte, hCarte));
         
        
         // refesh
         this.refresh();
	}

/**
 * rafraichit le bouton en lui associant une image correspondant a sa Carte associée
 */
	private void refresh() 
	{
		if (carte == null)
		{
			
			try {
			      img = ImageIO.read(getClass().getResourceAsStream("/special/back.jpg"));
			    } catch (IOException e) {
			      e.printStackTrace();
			    }

		}
		else
		{
			if (carte.getValeur() >= 0) //Si la carte n'est pas speciale
			{
				String couleur=ImageCarte.getCouleur(carte);
				String val = ImageCarte.getValeur(carte);
	          
	
				try {
				      img = ImageIO.read(getClass().getResourceAsStream("/" + couleur + "/" + val +".jpg"));
				    } catch (IOException e) {
				      e.printStackTrace();
				    }
	
			}
			else //si elle est speciale
			{
				String couleur=ImageCarte.getCouleur(carte);
				String special = ImageCarte.getSpecial(carte);
				
				try {
				      img = ImageIO.read(getClass().getResourceAsStream("/" + couleur + "/" + special +".jpg"));
				    } catch (IOException e) {
				      e.printStackTrace();
				    }
			}
		}
		this.setVisible(true);
		
		
		
	}

	/**
	 * converti Especial en String
	 * @param carte
	 * @return String
	 */
	public static String getSpecial(Carte carte) {
		
		if(carte.getSpecial() == null)
			return null;
		switch(carte.getSpecial())
		{
		case INVERSE:
			return "inverse";
		case JOKER:
			return "joker";
		case PASSE:
			return "passe";
		case PLUS_DEUX:
			return "plus_deux";
		case PLUS_QUATRE:
			return "plus_quatre";
		default:
			return null;
		
		}
	}

	/**
	 * Convertit ECouleur en String
	 * @param carte
	 * @return String
	 */
	public static String getCouleur(Carte carte) {
			
		if(carte.getCouleur() == null)
			return "special";
		switch (carte.getCouleur())
		{
		case VERT:
			return "green";
		case BLEU:
			return "blue";
		case JAUNE:
			return "yellow";
		case ROUGE:
			return "red";
		default:
			return "special";
		}
		
		
	}
	
	/**
	 * Convertit le int lié a la valeur en String
	 * @param carte
	 * @return String
	 */
	public static String getValeur(Carte carte) {
		  switch (carte.getValeur())
        {
        case 0:
        	return "zero";
        case 1:
      	 return "un";
        case 2:
      	  return "deux";
        case 3:
      	  return "trois";
        case 4:
      	  return "quatre";
        case 5:
      	  return "cinq";
        case 6:
      	  return "six";
        case 7:
      	  return "sept";
        case 8:
      	  return "huit";
        case 9:
      	  return "neuf";
       default:
      	 return null;
        
      	 
        
        }
	}


	/**
	 * getter de la carte associé au bouton
	 * @return Carte
	 */
	public Carte getCarte() {
		
		return carte;
	}
	
	
	public void paintComponent(Graphics g){

	 	super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D)g;
	    GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
	    g2d.setPaint(gp);
	    g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    g2d.setColor(Color.black);
	    g2d.drawString(" ", this.getWidth() / 2 - (this.getWidth() / 2 /4), (this.getHeight() / 2) + 5);

		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
 }

	
}