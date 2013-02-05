package niveau.level;

import base.*;
import niveau.Niveau;
import element.Bloc;
import element.bloc.*;

/**
 	BOMBERMAN<br>
 	classe: Level_01.java<br>
 	Titre: Escargot
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */
 
public class Level_01 extends Niveau {

	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------

	/** Constructeur */
	public Level_01() {
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
		titre="Escargot";
		
		/*==================*/
		/* # TYPE DE REBOND */
		/*	       NULL */
		/*	       IDEM */
		/*	       DIF  */
		/*==================*/
		type_rebond=DIF;
		
		/*===================*/
		/* # DELAI EXPLOSION */
		/*   Conseillé: 1-5  */
		/*===================*/
		delai_explosion=3;	
	
		
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
		def_detonateur=true;
		
		/*===================================*/
		/* # POSSIBILITE DE POUSSER LA BOMBE */
		/*			       true  */
		/*			       false */
		/*===================================*/
		def_pousser=true;
		
		/*=========================*/
		/* # DUREE D'INVINCIBILITE */
		/*   Conseillé: 30-500     */
		/*=========================*/
		def_invincibilite=50;
		
		/*========================*/
		/* # LONGUEUR D'EXPLOSION */
		/*   Conseillé: 1-25      */
		/*========================*/
		def_longueurexplosion=5;

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
		max_longueurexplosion=8;
		
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
		min_maxnbbombes=2;
		max_maxnbbombes=3;

	// 4. Les positions des joueurs
	
		/*========================================*/
		/* # POSITIONS DES JOUEURS SUR LE PLATEAU */
		/*   En général 1<=case_x<=11		  */
		/*		1<=case_y<=11		  */
		/*========================================*/
		creerJoueurs(1,6,11,6);
		
		
	//++++++//
	// BLOC //
	//++++++//

		/*===============*/
		/* $ BLOCS TIMER */
		/*===============*/
		
		blocs_timer[300]=new Bloc[3];
		blocs_timer[300][0]=new Brique_Uparrow(4, 9, (Bloc)null);
		blocs_timer[300][1]=new Brique_Uparrow(2, 11, (Bloc)null);
		blocs_timer[300][2]=new Pave_Chess(3,10);

		/*=========*/
		/* # BLOCS */
		/*=========*/
		
		Bloc bloc;
		
		//pavé
		for(int i=1; i<12; i++) {
			for(int j=1; j<12; j++) {
				bloc=new Pave_Chess(j, i);
				bloc.visible();
			}//for
		}//for
		
		//contour
		for(int i=0; i<13; i++) {
			bloc=new Brique_Dark(i, 0, (Bloc)null);
			bloc.visible();
			bloc=new Brique_Dark(i, 12, (Bloc)null);
			bloc.visible();
			bloc=new Brique_Dark(0, i, (Bloc)null);
			bloc.visible();
			bloc=new Brique_Dark(12, i, (Bloc)null);
			bloc.visible();
		}
		
		for(int i=2; i<=10; i++) {
			bloc=new Brique_Dark_l(i, 2, (Bloc)null);
			bloc.visible();
			bloc=new Brique_Dark_l(i, 10, (Bloc)null);
			bloc.visible();
		}
		for(int i=2; i<=10; i++) {
			bloc=new Brique_Uparrow(2, i, (Bloc)null);
			bloc.visible();
			bloc=new Brique_Downarrow(10, i, (Bloc)null);
			bloc.visible();
		}
		
		
		bloc=new Pave_Chess(2, 6);
		bloc=bonus(INVINCIBLE, 2, 6, bloc);
		bloc=new Brique_Extra_d(2, 6, bloc);
		bloc=new Brique_Extra(2, 6, bloc);
		bloc.visible();
		
		bloc=new Pave_Chess(6, 2);
		bloc=bonus(INVINCIBLE, 6, 2, bloc);
		bloc=new Brique_Extra_d(6, 2, bloc);
		bloc=new Brique_Extra(6, 2, bloc);
		bloc.visible();
		
		bloc=new Pave_Chess(10, 6);
		bloc=bonus(INVINCIBLE, 10, 6, bloc);
		bloc=new Brique_Extra_d(10, 6, bloc);
		bloc=new Brique_Extra(10, 6, bloc);
		bloc.visible();
		
		for(int i=4; i<=8; i++) {
			bloc=new Brique_Dark_l(i, 4, (Bloc)null);
			bloc.visible();
			bloc=new Brique_Dark_l(i, 8, (Bloc)null);
			bloc.visible();
		}
		for(int i=4; i<=8; i++) {
			bloc=new Brique_Uparrow(4, i, (Bloc)null);
			bloc.visible();
			bloc=new Brique_Downarrow(8, i, (Bloc)null);
			bloc.visible();
		}
		
		bloc=new Pave_Chess(5, 8);
		bloc.visible();
		
		Bloc[] blocs=new Bloc[5];
		
		blocs[0]=new Pave_Chess(6, 6);
		
		bloc=new Pave_Chess(5,6);
		blocs[1]=bonus(SLOW,5,6,bloc);
		
		bloc=new Pave_Chess(6,5);
		blocs[2]=bonus(SLOW,6,5,bloc);
		
		bloc=new Pave_Chess(7,6);
		blocs[3]=bonus(SLOW,7,6,bloc);
		
		bloc=new Pave_Chess(6,7);
		blocs[4]=bonus(SLOW,6,7,bloc);
		
		bloc=bonus(LIFE, 6, 6, blocs);
		
		blocs_timer[400]=new Bloc[1];
		blocs_timer[400][0]=bloc;
		blocs_timer[600]=new Bloc[1];
		blocs_timer[600][0]=bloc;
		blocs_timer[800]=new Bloc[1];
		blocs_timer[800][0]=bloc;
		
		bloc=bonus(LIFE, 6, 6, bloc);
		bloc=bonus(LIFE, 6, 6, bloc);
		bloc.visible();
		
		bloc=null;
		blocs=null;
	}//charger();



};//classe Level_01