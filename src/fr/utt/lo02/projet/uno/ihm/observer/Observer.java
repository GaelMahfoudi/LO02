package fr.utt.lo02.projet.uno.ihm.observer;

/**
 * Interface Observer
 * Correspond au moteur du jeu qui va etre "observé" par les autres classes 
 * @see Observateur
 * @see View
 * Voir Design Pattern MVC
 * @author Victor
 *
 */
public interface Observer {
	
	/**
	 * Ajoute a l'observé son observateur obs
	 * @param obs
	 * 		@see Observateur
	*/
	  public void addObservateur(Observateur obs);
}