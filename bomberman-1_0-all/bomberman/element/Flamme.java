package element;

import java.awt.*;
import java.util.*;
import base.*;
import element.*;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Flamme.java<br>
 	Flamme d'explosion
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Flamme implements Constantes {

	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/

		/*--------*/
		/* PUBLIC */
		/*--------*/	

	/** tableau pour obtenir la disposition des flammes à afficher */
	public static int[][] tabl_flammes;

		/*---------*/
		/* PRIVATE */
		/*---------*/
	
	//la zone de jeu
	private static ZoneJeu zonejeu;
	
	//vecteur pour accéder rapidement aux flammes
	private static Vector vect_flammes;
	
	//nombre d'images de flammes
	private static final int NB_IMAGES_FLAMMES=16;
	
	//délai entre chaque déplacement de la flamme
	private static final int DELAI=Explosion.getDelai();


	/*------------*/
	/* NON STATIC */
	/*------------*/

		/*---------*/
		/* PRIVATE */
		/*---------*/
	
	//position de la flamme en cases
	private int case_x, case_y;
	
	//distance à parcourir par la flamme
	private int distance;
	
	//direction de la flamme
	private int[] direction;
	
	//spécifie si dernière flamme de l'explosion, cela lui
	//confère la possibilité de détruire un bloc
	private boolean derniere;
		
	//délai courant
	private int delai;
	
	//le numéro d'image à afficher
	private int num_image;
	
	
	
	//------------------------------------------------------------
	//		CONSTRUCTEUR / INITIALISATIONS
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/

	/** Initialisation des attributs static */
	public static void initStatic(ZoneJeu zone) {
		//initialisation explosion: créateur de flammes
		Explosion.initStatic();		
		zonejeu=zone;
		tabl_flammes=new int[NB_CASES_LIGNE][NB_CASES_COLONNE];
		vect_flammes=new Vector(150);
	}//initStatic()


	/*------------*/
	/* NON STATIC */
	/*------------*/
	
	/** Construction d'une flamme suivant les propriétés spécifiées */
	public Flamme(int case_x, int case_y, int distance, int[] direction, boolean derniere) {
		this.case_x=case_x;
		this.case_y=case_y;
		this.distance=distance;
		this.direction=direction;
		this.derniere=derniere;
		this.delai=DELAI;
		tabl_flammes[case_x][case_y]++;
		vect_flammes.addElement(this);
	}//Flamme()



        //------------------------------------------------------------
	//			METHODES PUBLIQUES
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/

	/** Dessine toutes les flammes */
	public static void paintOll(Graphics g) {
		moveOll(g);
		Flamme flamme;
		Enumeration e;
    		e = vect_flammes.elements();
		while (e.hasMoreElements()) {
			flamme=(Flamme)e.nextElement();
			if (tabl_flammes[flamme.case_x][flamme.case_y]<100) {
				paintFlamme(g, flamme.case_x, flamme.case_y);
				//+100: cet endroit a déjà été dessiné
				tabl_flammes[flamme.case_x][flamme.case_y]+=100;
			}
     		}//while
     		e = vect_flammes.elements();
     		while (e.hasMoreElements()) {
			flamme=(Flamme)e.nextElement();
			if (tabl_flammes[flamme.case_x][flamme.case_y]>=100) {
				//-100: retour à l'état initial
				tabl_flammes[flamme.case_x][flamme.case_y]-=100;
			}
     		}//while
	}//paintOll()
	
	/** Dessine une flamme se trouvant à une position donnée */
	public static void paintFlamme(Graphics g, int case_x, int case_y) {
		if (tabl_flammes[case_x][case_y]==0) return;
		int num_image=findNumImage(case_x, case_y);
		int x=Utils.convertXcp(case_x);
		int y=Utils.convertYcp(case_y);
		int numero_image=zonejeu.I_FLAMMES+num_image;
		g.drawImage(zonejeu.images[numero_image], x, y, zonejeu);
		zonejeu.affichage(numero_image+"#"+x+"#"+y+"#");
	}//paintFlamme()


	/*------------*/
	/* NON STATIC */
	/*------------*/

	/** Déplacement d'une flamme */
	public void moveFlamme(Graphics g) {
		delai--;
		if (delai<=0) {			
			delai=DELAI;
			distance--;
			tabl_flammes[case_x][case_y]--;
			Bloc.paintBloc(g, case_x, case_y);
			//un bloc bonus ou brique apparait à la même position que la flamme
			//elle doit continuer son chemin et pouvoir detruire un bloc
			if (!Bloc.tabl_blocs[case_x][case_y].isPave()) {
				derniere=true;
				case_x+=direction[0];
				case_y+=direction[1];
				if ( (distance<=0) || (!Bloc.tabl_blocs[case_x][case_y].isPave()) ) {
					vect_flammes.removeElement(this);
				}
				else tabl_flammes[case_x][case_y]++;
			}
			else {
				case_x+=direction[0];
				case_y+=direction[1];
				if ( (distance<=0) || (!Bloc.tabl_blocs[case_x][case_y].isPave()) ) {
					vect_flammes.removeElement(this);
					if (derniere) Bloc.tabl_blocs[case_x][case_y].detruire(g);
				}
				else tabl_flammes[case_x][case_y]++;
			}
		}//if delai
	}//moveFlamme()
	


	//------------------------------------------------------------
	//			METHODES PRIVEES
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/
	
	/** Déplacement des toutes les flammes */
	private static void moveOll(Graphics g) {
		Vector copie_flammes=(Vector)vect_flammes.clone();
		Enumeration e;
     		e = copie_flammes.elements();
		while (e.hasMoreElements()) {
			((Flamme)e.nextElement()).moveFlamme(g);
     		}//while
     		Explosion.CreerOll();     		
	}//moveOll()


	/** Détermine le numéro de l'image à afficher */
	private static int findNumImage(int case_x, int case_y) {
		int num=0;
		if (tabl_flammes[case_x-1][case_y]!=0) num+=1;
		if (tabl_flammes[case_x+1][case_y]!=0) num+=2;
		if (tabl_flammes[case_x][case_y-1]!=0) num+=4;
		if (tabl_flammes[case_x][case_y+1]!=0) num+=8;
		return(num);
	}//findFlamme()
	


};//classe Flamme