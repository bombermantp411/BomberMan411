package base;

/**
	BOMBERMAN<br>
	classe: Constantes.java<br>
 	Sont regroupées ici les constantes communes à de nombreuses classes
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 	
 */
 
public interface Constantes {

	/** dimension du plateau de jeu */
  	static final int PLATEAU_LARGEUR=832;
  	
  	/** dimension du plateau de jeu */
  	static final int PLATEAU_HAUTEUR=624;
  	
  	/** dimension d'une case */
  	static final int CASE_LARGEUR=64;
  	
  	/** dimension d'une case */
  	static final int CASE_HAUTEUR=48;
  	
  	/** dimension du joueur */
  	static final int JOUEUR_LARGEUR=64;
  	
  	/** dimension du joueur */
  	static final int JOUEUR_HAUTEUR=72;
  	
  	/** nombre de cases par ligne */
  	static final int NB_CASES_LIGNE		=PLATEAU_LARGEUR/CASE_LARGEUR;
  	
  	/** nombre de cases par colonne */
  	static final int NB_CASES_COLONNE	=PLATEAU_HAUTEUR/CASE_HAUTEUR;



};//interface Constantes