package niveau.level;

import base.*;
import niveau.Niveau;
import element.Bloc;
import element.bloc.*;

/**
 	BOMBERMAN<br>
 	classe: Level_00.java<br>
 	Titre: Dans l'arène
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */
 
public class Level_00 extends Niveau {

	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------

	/** Constructeur */
	public Level_00() {
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
		titre="Dans l'arène";
		
		/*==================*/
		/* # TYPE DE REBOND */
		/*	       NULL */
		/*	       IDEM */
		/*	       DIF  */
		/*==================*/
		type_rebond=IDEM;
		
		/*===================*/
		/* # DELAI EXPLOSION */
		/*   Conseillé: 1-5  */
		/*===================*/
		delai_explosion=2;	
	
		
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
		def_invincibilite=30;
		
		/*========================*/
		/* # LONGUEUR D'EXPLOSION */
		/*   Conseillé: 1-25      */
		/*========================*/
		def_longueurexplosion=3;

	// 2. Les valeurs maximales
	
		/*==============================*/
		/* # DUREE D'INVINCIBILITE MAXI */
		/*   Conseillé: 30-1000	        */
		/*==============================*/
		max_invincibilite=300;
		
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
		max_maxnbbombes=4;

	// 4. Les positions des joueurs
	
		/*========================================*/
		/* # POSITIONS DES JOUEURS SUR LE PLATEAU */
		/*   En général 1<=case_x<=11		  */
		/*		1<=case_y<=11		  */
		/*========================================*/
		creerJoueurs(6,5,6,7);
		
		
	//++++++//
	// BLOC //
	//++++++//

		/*===============*/
		/* $ BLOCS TIMER */
		/*===============*/
		for(int i=1; i<12; i++) {
			blocs_timer[200+i]=new Bloc[2];
			blocs_timer[200+i][0]=new Brique_Wall(i, 1, (Bloc)null);
			blocs_timer[200+i][1]=new Brique_Wall(12-i, 11, (Bloc)null);
		}
		for(int i=2; i<11; i++) {
			blocs_timer[400+i]=new Bloc[2];
			blocs_timer[400+i][0]=new Brique_Wall(1, i, (Bloc)null);
			blocs_timer[400+i][1]=new Brique_Wall(11, 12-i, (Bloc)null);
		}

		blocs_timer[500]=new Bloc[4];
		
		Bloc bloc;
		
		int[] alea={KICKBOMB, REMOTECONTROL};
		
		bloc=aleaBonus(alea,6,4,new Pave_Hex(6,4));
		bloc=new Brique_Hexaextra(6,4, bloc);
		blocs_timer[500][0]=bloc;
		
		bloc=aleaBonus(alea,6,8,new Pave_Hex(6,8));
		bloc=new Brique_Hexaextra(6,8, bloc);
		blocs_timer[500][1]=bloc;
		
		bloc=aleaBonus(alea,4,6,new Pave_Hex(4,6));
		bloc=new Brique_Hexaextra(4,6, bloc);
		blocs_timer[500][2]=bloc;
		
		bloc=aleaBonus(alea,8,6,new Pave_Hex(8,6));
		bloc=new Brique_Hexaextra(8,6, bloc);
		blocs_timer[500][3]=bloc;
		

		/*=========*/
		/* # BLOCS */
		/*=========*/
		
		//pavé
		for(int i=1; i<12; i++) {
			for(int j=1; j<12; j++) {
				bloc=new Pave_Hex(j, i);
				bloc.visible();
			}//for
		}//for
		
		//contour
		for(int i=0; i<13; i++) {
			bloc=new Brique_Wall(i, 0, (Bloc)null);
			bloc.visible();
			bloc=new Brique_Wall(i, 12, (Bloc)null);
			bloc.visible();
			bloc=new Brique_Wall(0, i, (Bloc)null);
			bloc.visible();
			bloc=new Brique_Wall(12, i, (Bloc)null);
			bloc.visible();
		}		
		
		int[] alea2={BOMB, KICKBOMB, REMOTECONTROL, RANGE};
		
		bloc=aleaBonus(alea2,6,4,new Pave_Hex(6,4));
		bloc=new Brique_Hexaextra(6,4, bloc);
		bloc.visible();
		
		bloc=aleaBonus(alea2,6,8,new Pave_Hex(6,8));
		bloc=new Brique_Hexaextra(6,8, bloc);
		bloc.visible();
		
		bloc=aleaBonus(alea2,4,6,new Pave_Hex(4,6));
		bloc=new Brique_Hexaextra(4,6, bloc);
		bloc.visible();
		
		bloc=aleaBonus(alea2,8,6,new Pave_Hex(8,6));
		bloc=new Brique_Hexaextra(8,6, bloc);
		bloc.visible();
		
		bloc=new Brique_Jizz(5,5,new Pave_Hex(5,5));
		bloc=new Brique_Hexawall(5,5,bloc);
		bloc.visible();
		bloc=new Brique_Jizz(7,5,new Pave_Hex(7,5));
		bloc=new Brique_Hexawall(7,5,bloc);
		bloc.visible();
		bloc=new Brique_Jizz(5,7,new Pave_Hex(5,7));
		bloc=new Brique_Hexawall(5,7,bloc);
		bloc.visible();
		bloc=new Brique_Jizz(7,7,new Pave_Hex(7,7));
		bloc=new Brique_Hexawall(7,7,bloc);
		bloc.visible();
		
		bloc=new Brique_Dark(3,3,(Bloc) null);
		bloc.visible();
		bloc=new Brique_Dark(3,9,(Bloc) null);
		bloc.visible();
		bloc=new Brique_Dark(9,3,(Bloc) null);
		bloc.visible();
		bloc=new Brique_Dark(9,9,(Bloc) null);
		bloc.visible();
		
		bloc=null;
	}//charger();



};//classe Level_00