package niveau.level;

import base.*;
import niveau.Niveau;
import element.Bloc;
import element.bloc.*;

/**
 	BOMBERMAN<br>
 	classe: Level_02.java<br>
 	Titre: Grille en échiquier
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */
 
public class Level_02 extends Niveau {

	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------

	/** Constructeur */
	public Level_02() {
	}
	
	/** Chargement */
	public void charger() {

	////////////////////////////////////////////////////////////////////
	//////////////// PROPRIETES A DEFINIR LE LEVEL /////////////////////
	//////////////// # obligatoire /////////////////////////////////////
	//////////////// $ facultatif //////////////////////////////////////
	////////////////////////////////////////////////////////////////////
			
	//+++++++++++++//
	// INFORMATION //
	//+++++++++++++//

		/*===================*/
		/* $ TITRE DU NIVEAU */
		/*===================*/
		titre="Grille en échiquier";
		
		/*==================*/
		/* # TYPE DE REBOND */
		/*	       NULL */
		/*	       IDEM */
		/*	       DIF  */
		/*==================*/
		type_rebond=NULL;
		
		/*===================*/
		/* # DELAI EXPLOSION */
		/*   Conseillé: 1-5  */
		/*===================*/
		delai_explosion=1;	
	
		
	//++++++++//
	// JOUEUR //
	//++++++++//
	// 1. Les valeurs par défaut
	
		/*==========================*/
		/* # VITESSE DE DEPLACEMENT */
		/* 	 	     LENT   */
		/*		     NORMAL */
		/*		     VITE   */
		/*==========================*/
		def_vitessedeplacement=NORMAL;
		
		/*============================*/
		/* # POSSESSION DU DETONATEUR */
		/*	  	        true  */
		/*		        false */
		/*============================*/
		def_detonateur=false;
		
		/*===================================*/
		/* # POSSIBILITE DE POUSSER LA BOMBE */
		/*			       true  */
		/*			       false */
		/*===================================*/
		def_pousser=false;
		
		/*=========================*/
		/* # DUREE D'INVINCIBILITE */
		/*   Conseillé: 30-500     */
		/*=========================*/
		def_invincibilite=50;
		
		/*========================*/
		/* # LONGUEUR D'EXPLOSION */
		/*   Conseillé: 1-25      */
		/*========================*/
		def_longueurexplosion=4;

	// 2. Les valeurs maximales
	
		/*==============================*/
		/* # DUREE D'INVINCIBILITE MAXI */
		/*   Conseillé: 30-1000	        */
		/*==============================*/
		max_invincibilite=200;
		
		/*=============================*/
		/* # LONGUEUR D'EXPLOSION MAXI */
		/*   Conseillé: 1-25           */
		/*=============================*/
		max_longueurexplosion=6;
		
		/*=====================================================*/
		/* # NOMBRE DE BOMBES POSEES MAXI PAR JOUEUR (INITIAL) */
		/*   Conseillé: 1-5            			       */
		/*=====================================================*/
		max_nbbombes=2;
		
	// 3. Les intervalles de valeurs

		/*==========================================*/
		/* # INTERVALLE DU NOMBRE MAXIMUM DE BOMBES */
		/*   POUVANT ETRE POSEES PAR JOUEUR	    */
		/*   Conseillé: 1-5             	    */
		/*==========================================*/
		min_maxnbbombes=1;
		max_maxnbbombes=3;

	// 4. Les positions des joueurs
	
		/*========================================*/
		/* # POSITIONS DES JOUEURS SUR LE PLATEAU */
		/*   En général 1<=case_x<=11		  */
		/*		1<=case_y<=11		  */
		/*========================================*/
		creerJoueurs(1,1,11,11);
		
		
	//++++++//
	// BLOC //
	//++++++//

		/*===============*/
		/* $ BLOCS TIMER */
		/*===============*/
		
		/*=========*/
		/* # BLOCS */
		/*=========*/
		
		Bloc bloc;
		
		//pavé
		for(int i=1; i<12; i++) {
			for(int j=1; j<12; j++) {
				bloc=new Pave_Check(j, i);
				bloc.visible();
			}//for
		}//for
		
		//contour
		for(int i=0; i<13; i++) {
			bloc=new Brique_Pyramid(i, 0, (Bloc)null);
			bloc.visible();
			bloc=new Brique_Pyramid(i, 12, (Bloc)null);
			bloc.visible();
			bloc=new Brique_Pyramid(0, i, (Bloc)null);
			bloc.visible();
			bloc=new Brique_Pyramid(12, i, (Bloc)null);
			bloc.visible();
		}
		
		int[] alea={BOMB, KICKBOMB, RANGE, REMOTECONTROL, IGNITE, SLOW, SPEED, INVINCIBLE, LIFE, POISON, MULTIPLE, TRAP};
		
		for(int i=2; i<=10; i+=2) {
			for(int j=2; j<=10; j+=2) {
				bloc=new Brique_Box(j, i, aleaBonus(alea, j, i, new Pave_Iron(j, i) ) );
				bloc.visible();
			}
		}
		
		bloc=null;
		
	}//charger();



};//classe Level_02