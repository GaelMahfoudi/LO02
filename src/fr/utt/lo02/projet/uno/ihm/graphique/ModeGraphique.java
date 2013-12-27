package fr.utt.lo02.projet.uno.ihm.graphique;


import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.utt.lo02.projet.uno.ihm.observer.UnoController;
import fr.utt.lo02.projet.uno.ihm.observer.View;
import fr.utt.lo02.projet.uno.noyau.carte.ECouleur;
import fr.utt.lo02.projet.uno.noyau.gestion.joueur.Joueur;
import fr.utt.lo02.projet.uno.noyau.gestion.partie.Partie;

<<<<<<< HEAD
public class ModeGraphique extends JFrame implements View{

	private ParametrerPartie param;
	private MainJoueurPan main;
=======
public class ModeGraphique extends JFrame implements View, ActionListener, ItemListener{

	private JComboBox comboReel;
	private JComboBox comboIA;
	private JLabel nomJoueur;
	private int nbreJoueurReel = 0;
	private int nbreIA = 0;;
	private int nbreJoueurTot = 0;
	private JTextField nomJoueur_text;
	private JButton nouvellePartie;
	private JButton nomOk;
	private boolean ok = false;
	private boolean nbreJoueurOk;
>>>>>>> 0ea241e56f65f3c0bb959f3371b4c18746247f4e
	
	public ModeGraphique()
	{
		param = new ParametrerPartie();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
<<<<<<< HEAD
		
		this.setLayout(new BorderLayout());
		
=======
>>>>>>> 0ea241e56f65f3c0bb959f3371b4c18746247f4e
	}

	
	
	public Partie demarrerPartie(Partie partie, UnoController controller) 
	{
		partie = param.recupererPartie(partie, controller);
		this.setVisible(true);
<<<<<<< HEAD
=======
		ok = false;
		while(nbreJoueurTot == 0 || nbreJoueurTot > 10)
		{
			//On attend
			//TODO une autree solution d'attente
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Got here");
		partie = new Partie(nbreJoueurReel, nbreIA, controller);
>>>>>>> 0ea241e56f65f3c0bb959f3371b4c18746247f4e
		return partie;
	}
	
	public String demanderPseudo() 
	{
			return param.demanderPseudo();
	}
		
	
	public int demanderCarteAJouer(Joueur joueur) 
	{

		main = new MainJoueurPan();
		this.getContentPane().add(main, BorderLayout.SOUTH);
		this.setVisible(true);
		
		JOptionPane jop2 = new JOptionPane();
		jop2.showMessageDialog(null, " Appuyez sur ok lorsque vous serez pret", ("Au tour de "+ (String)joueur.afficherPseudo()), JOptionPane.WARNING_MESSAGE);
		main.removeAll();
		main = new MainJoueurPan(joueur);
		this.getContentPane().add(main, BorderLayout.SOUTH);
		this.setVisible(true);
		return main.getChoix();
		
<<<<<<< HEAD
=======
		if(arg0.getSource().equals(nouvellePartie))
		{
			System.out.println(nbreJoueurReel + " " + nbreIA + " " + nbreJoueurTot);
			nbreJoueurTot = nbreJoueurReel+nbreIA;		
			System.out.println(nbreJoueurTot);
			if( nbreJoueurTot == 0 || nbreJoueurTot > 10)
			{
				/*JFrame fen = new JFrame();
				fen.setSize(300, 300);
				fen.setLocationRelativeTo(null);
				fen.setResizable(false);
				JPanel container = new JPanel();
				JLabel label = new JLabel("Erreur, veuillez revoir les parametres entrés (max 10 joueurs)");
				
				Font police = new Font("Tahoma", Font.BOLD, 16);  
				label.setFont(police);  
				label.setForeground(Color.blue);  
				label.setHorizontalAlignment(JLabel.CENTER);
				container.add(label, BorderLayout.NORTH);
				fen.setContentPane(container);
				fen.setVisible(true);*/
				
				
				JOptionPane o = new JOptionPane();
				o.showMessageDialog(null, "Choisissez un nombre de joueur entre 0 et 10", "Erreur Joueur", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				ok = true;
			}
		}
>>>>>>> 0ea241e56f65f3c0bb959f3371b4c18746247f4e
	}
	
	



	public void afficherFinManche(Partie p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afficherFinPartie(Partie p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afficherTour(Joueur joueur, int choix) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public int poserCartePioche(Joueur joueur) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void afficherCartePioche(Joueur joueur) {
		// TODO Auto-generated method stub

	}

	@Override
	public int demanderBluff(Partie partie) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void afficherMauvaisChoix() {
		JOptionPane jop2 = new JOptionPane();
		jop2.showMessageDialog(null, "Cette carte ne peut etre posée", "Erreur", JOptionPane.ERROR_MESSAGE);
		
	}

	@Override
	public ECouleur demanderCouleur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int demanderUno() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int demanderContreUno() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	

	@Override
	public int demanderChoix(int min, int max) {
		int choix = min-1;

		while(choix<min || choix > max)
		{
			JOptionPane o = new JOptionPane();

			choix = Integer.parseInt(o.showInputDialog(null, "Donnez votre choix entre " + min + " et "+ max, "Choix", JOptionPane.QUESTION_MESSAGE));
		}
		return choix;
	}

	

	
	
	

}
