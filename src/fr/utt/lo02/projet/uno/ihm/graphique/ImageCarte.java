package fr.utt.lo02.projet.uno.ihm.graphique;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import fr.utt.lo02.projet.uno.noyau.carte.Carte;
import fr.utt.lo02.projet.uno.noyau.carte.ESpecial;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;



public class ImageCarte extends JButton {

	
	public static final int hCarte=175;
    public static final int lCarte=108;
    private Carte carte;
    private final String pathImage ="./src/fr/utt/lo02/projet/uno/ihm/uno_images";
    private JLabel image;
    
    public ImageCarte()
    {
         this.carte = null;
    	 this.setPreferredSize(new Dimension(lCarte, hCarte));
         this.image=new JLabel();
         this.image.setPreferredSize(new Dimension(ImageCarte.lCarte, ImageCarte.hCarte));
         this.add(image);
         // refesh
         this.refresh();
    }
    
    
	public ImageCarte(Carte carte)
	{
		 this.carte= carte;

         // dimension de la carte
         this.setPreferredSize(new Dimension(lCarte, hCarte));
         
         this.image=new JLabel();
         this.image.setPreferredSize(new Dimension(ImageCarte.lCarte, ImageCarte.hCarte));
         this.add(image);
         // refesh
         this.refresh();
	}


	private void refresh() 
	{
		if (carte == null)
		{
			ImageIcon img=new ImageIcon(this.pathImage + "/special/back.jpg");
            Image i=img.getImage().getScaledInstance(ImageCarte.lCarte, ImageCarte.hCarte, Image.SCALE_DEFAULT);
            img.setImage(i);
            this.setIcon(img);
            

		}
		else
		{
			if (carte.getValeur() >= 0) //Si la carte n'est pas speciale
			{
				String couleur=ImageCarte.getCouleur(carte);
				String val = ImageCarte.getValeur(carte);
	          
	
	            ImageIcon img=new ImageIcon(this.pathImage + "/" + couleur + "/" + val +".jpg");
	            Image i=img.getImage().getScaledInstance(ImageCarte.lCarte, ImageCarte.hCarte, Image.SCALE_DEFAULT);
	            img.setImage(i);
	
	            this.setIcon(img);
	
			}
			else //si elle est speciale
			{
				String couleur=ImageCarte.getCouleur(carte);
				String special = ImageCarte.getSpecial(carte);
	          
	
	            ImageIcon img=new ImageIcon(this.pathImage + "/" + couleur + "/" + special +".jpg");
	            Image i=img.getImage().getScaledInstance(ImageCarte.lCarte, ImageCarte.hCarte, Image.SCALE_DEFAULT);
	            img.setImage(i);
	
	
	            this.setIcon(img);
	
			}
		}
		this.setVisible(true);
		
		
		
	}

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


	public Carte getCarte() {
		
		return carte;
	}
	

	
}