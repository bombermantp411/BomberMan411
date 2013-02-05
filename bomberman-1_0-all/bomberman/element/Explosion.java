package element;

import java.util.*;
import element.*;

/**
 	BOMBERMAN<br>
 	classe: Explosion.java<br>
 	Explosion des bombes: cr�ation de flammes
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Explosion {
	
	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/

		/*---------*/
		/* PRIVATE */
		/*---------*/
	
	// vecteur pour acc�der aux explosions
	private static Vector vect_explosions;
	
	//d�lai entre chaque cr�ation de flammes
	private static final int DELAI=1;

	//directions des flammes
	private static final int[] GAUCHE ={-1,0};
	private static final int[] DROITE ={1,0};
	private static final int[] HAUT   ={0,-1};
	private static final int[] BAS    ={0,1};


	/*------------*/
	/* NON STATIC */
	/*------------*/

		/*---------*/
		/* PRIVATE */
		/*---------*/
	
	//position de l'explosion en cases
	private int case_x, case_y;
	
	//nombre de cr�ations de flammes
	private int ncreations;
	
	//distance � parcourir par chacune des flammes
	private int distance;
	
	//d�lai courant
	private int delai;



	//------------------------------------------------------------
	//		CONSTRUCTEUR / INITIALISATIONS
	//------------------------------------------------------------
	
	/*--------*/
	/* STATIC */
	/*--------*/

	/** Initialisation des attributs static */
	public static void initStatic() {
		vect_explosions=new Vector(50);
	}//initStatic()


	/*------------*/
	/* NON STATIC */
	/*------------*/

	/** Cr�ation d'une explosion avec les propri�t�s sp�cifi�es */
	public Explosion(int case_x, int case_y, int ncreations, int distance) {
		this.case_x=case_x;
		this.case_y=case_y;
		this.ncreations=ncreations;
		this.distance=distance;
		this.delai=DELAI;
		vect_explosions.addElement(this);
	}//Explosion()



        //------------------------------------------------------------
	//			METHODES PUBLIQUES
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/

	/** Retourne d�lai entre chaque cr�ation de flammes */
	public static int getDelai() {
		return(DELAI);
	}//getDelai()

	/** Cr�er les flammes de toutes les explosions */
	public static void CreerOll() {
		Vector copie_explosions=(Vector)vect_explosions.clone();
		Enumeration e;
    		e = copie_explosions.elements();
		while (e.hasMoreElements()) {
			((Explosion)e.nextElement()).creerExplosion();
     		}//while
	}//CreerOll()


	/*------------*/
	/* NON STATIC */
	/*------------*/
	
	/** Creer les flammes d'une explosion */
	public void creerExplosion() {
		delai--;
		if (delai<=0) {
			ncreations--;
			delai=DELAI;
			//sp�cifie si ce sont les derni�res flammes de l'explosion courante
			boolean derniere=false;
			if (ncreations<=0 || !Bloc.tabl_blocs[case_x][case_y].isPave()) {
				vect_explosions.removeElement(this);
				derniere=true;
			}//if
			new Flamme(case_x, case_y, distance, GAUCHE, derniere);
			new Flamme(case_x, case_y, distance, DROITE, derniere);
			new Flamme(case_x, case_y, distance, HAUT, derniere);
			new Flamme(case_x, case_y, distance, BAS, derniere);
		}
	}//creerExplosion()



};//Classe Explosion