package base;

/**
 	BOMBERMAN<br>
 	classe: Utils.java<br>
 	Quelques m�thodes utiles
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 	
 */

public final class Utils implements Constantes {

	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------

	/** diff�rence entre la hauteur d'un joueur et celle d'une case */
	public static final int JOUEUR_CASE_HDIFF=JOUEUR_HAUTEUR-CASE_HAUTEUR;


	
        //------------------------------------------------------------
	//			METHODES PUBLIQUES
	//------------------------------------------------------------

	       	/*-------------------------------------*/
	       	/* Convertisseurs coordonn�es pour les */
	       	/* blocs, flammes, bombes 	       */
		/*	pixels --> cases	       */
		/*	cases  --> pixels	       */
		/*-------------------------------------*/
		
	/** Convertisseur coordonn�es blocs, flammes, bombes<br> x (pixels) --> case_x (cases) */
	public static final int convertXpc(int x) {
		return (x/CASE_LARGEUR);
	}

	/** Convertisseur coordonn�es blocs, flammes, bombes<br> y (pixels) --> case_y (cases) */
	public static final int convertYpc(int y) {
		return (y/CASE_HAUTEUR);
	}

	/** Convertisseur coordonn�es blocs, flammes, bombes<br> case_x (cases) --> x (pixels) */
	public static final int convertXcp(int case_x) {
		return (CASE_LARGEUR*case_x);
	}

	/** Convertisseur coordonn�es blocs, flammes, bombes<br> case_y (cases) --> y (pixels) */
	public static final int convertYcp(int case_y) {
		return (CASE_HAUTEUR*case_y);
	}


		/*---------------------------------------------*/
		/* Convertisseurs coordonn�es pour les joueurs */
		/*	pixels --> cases		       */
		/*	cases  --> pixels	   	       */
		/*---------------------------------------------*/
	
	/** Convertisseur coordonn�es joueurs<br> x (pixels) --> case_x (cases) */
	public static final int convertJXpc(int x) {
		return (x/CASE_LARGEUR);
	}
	
	/** Convertisseur coordonn�es joueurs<br> y (pixels) --> case_y (cases) */
	public static final int convertJYpc(int y) {
		return ((y+JOUEUR_CASE_HDIFF)/CASE_HAUTEUR);
	}
	
	/** Convertisseur coordonn�es joueurs<br> case_x (cases) --> x (pixels) */
	public static final int convertJXcp (int case_x) {
		return (case_x*CASE_LARGEUR);
	}
	
	/** Convertisseur coordonn�es joueurs<br> case_y (cases) --> y (pixels) */
	public static final int convertJYcp (int case_y) {
		return (case_y*CASE_HAUTEUR-JOUEUR_CASE_HDIFF);
	}


		/*------------------------------------------------*/
		/* D�termination de la position d'une case qui se */
		/* trouve autour ou en dessous d'un joueur.	  */
		/* D�calage donn� par "nb_cases"                  */
		/*	pixels  --> pixels			  */
		/*------------------------------------------------*/
	
	/** D�termination de la position d'une case qui se trouve autour ou en dessous d'un joueur.<br>
	    D�calage donn� par "nb_cases"<br>
	    x joueur (pixels) --> x case (pixels)
	  */
	public static final int positioncaseJX(int x, int nb_cases) {
		return convertJXcp(convertJXpc(x)+nb_cases);
	}
	
	/** D�termination de la position d'une case qui se trouve autour ou en dessous d'un joueur.<br>
	    D�calage donn� par "nb_cases"<br>
	    y joueur (pixels) --> y case (pixels)
	  */
	public static final int positioncaseJY(int y, int nb_cases) {
		return convertJYcp(convertJYpc(y)+nb_cases);
	}
	
	
	
};//classe Utils