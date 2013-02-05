package niveau;

import java.util.*;
import niveau.level.*;
import element.*;
import element.bloc.*;
import element.joueur.*;
import general.Preference;

/**
 	BOMBERMAN<br>
 	fichier: Niveau.java<br>
 	Gère l'ensemble des levels disponibles<br>
 	Les levels doivent étendre cette classe et en définir les propriétés
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */
 
public abstract class Niveau {

	//------------------------------------------------------------
	//		DECLARATIONS POUR LES LEVELS
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/
	
		/*--------*/
		/* PUBLIC */
		/*--------*/

	/** dimension du tableau contenant les blocs timers
	    échelle de temps=1040
	 */
	public static final int DIMENSION_BLOCSTIMER=1040;

		/*-----------*/
		/* PROTECTED */
		/*-----------*/

	//types de rebonds
	/** pas de rebonds */
	protected static final int NULL=Bombe.REBOND_NULL;
	/** rebonds avec la même orientation */
	protected static final int IDEM=Bombe.REBOND_MEME_ORIENTATION;
	/** rebonds avec orientation changeant */
	protected static final int DIF=Bombe.REBOND_DIF_ORIENTATION;
	
	/** vitesse du joueur: lent */
	protected static final int LENT=Joueur.LENT;
	/** vitesse du joueur: normal */
	protected static final int NORMAL=Joueur.NORMAL;
	/** vitesse du joueur: vite */
	protected static final int VITE=Joueur.VITE;
	
	/** bonus disponible */
	protected static final int BOMB=0;
	/** bonus disponible */
	protected static final int KICKBOMB=1;
	/** bonus disponible */
	protected static final int RANGE=2;
	/** bonus disponible */
	protected static final int REMOTECONTROL=3;
	/** bonus disponible */
	protected static final int IGNITE=4;
	/** bonus disponible */
	protected static final int SLOW=5;
	/** bonus disponible */
	protected static final int SPEED=6;
	/** bonus disponible */
	protected static final int INVINCIBLE=7;
	/** bonus disponible */
	protected static final int LIFE=8;
	/** bonus disponible */
	protected static final int POISON=9;
	/** bonus disponible */
	protected static final int MULTIPLE=10;
	/** bonus disponible */
	protected static final int TRAP=11;

		/*--------*/
		/* PUBLIC */
		/*--------*/

	////////////////////////////////////////////////////////////////////
	//////////////// PROPRIETES A DEFINIR POUR UN LEVEL ////////////////
	//////////////// # obligatoire /////////////////////////////////////
	//////////////// $ facultatif //////////////////////////////////////
	////////////////////////////////////////////////////////////////////
			
	//+++++++++++++//
	// INFORMATION //
	//+++++++++++++//
	
		/*===================*/
		/* $ TITRE DU NIVEAU */
		/*===================*/
		/** titre du niveau */
		public static String titre;


	//+++++++//
	// BOMBE //
	//+++++++//
	
		/*==================*/
		/* # TYPE DE REBOND */
		/*	       NULL */
		/*	       IDEM */
		/*	       DIF  */
		/*==================*/
		/** type de rebond */
		public static int type_rebond;
		
		/*===================*/
		/* # DELAI EXPLOSION */
		/*   Conseillé: 1-5  */
		/*===================*/
		/** délai d'explosion */
		public static int delai_explosion;


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
		/** vitesse de déplacement du joueur */
		public static int def_vitessedeplacement;

		/*============================*/
		/* # POSSESSION DU DETONATEUR */
		/*	  	        true  */
		/*		        false */
		/*============================*/
		/** possession du détonateur */
		public static boolean def_detonateur;
	
		/*===================================*/
		/* # POSSIBILITE DE POUSSER LA BOMBE */
		/*			       true  */
		/*			       false */
		/*===================================*/
		/** possibilité de pousser la bombe */
		public static boolean def_pousser;

		/*=========================*/
		/* # DUREE D'INVINCIBILITE */
		/*   Conseillé: 30-500     */
		/*=========================*/
		/** durée d'invincibilité */
		public static int def_invincibilite;

		/*========================*/
		/* # LONGUEUR D'EXPLOSION */
		/*   Conseillé: 1-25      */
		/*========================*/
		/** longueur d'explosion */
		public static int def_longueurexplosion;
	
	// 2. Les valeurs maximales
	
		/*==============================*/
		/* # DUREE D'INVINCIBILITE MAXI */
		/*   Conseillé: 30-1000	        */
		/*==============================*/
		/** invincibilité maximum */
		public static int max_invincibilite;
	
		/*=============================*/
		/* # LONGUEUR D'EXPLOSION MAXI */
		/*   Conseillé: 1-25           */
		/*=============================*/
		/** longueur d'explosion maximum */
		public static int max_longueurexplosion;
	
		/*=====================================================*/
		/* # NOMBRE DE BOMBES POSEES MAXI PAR JOUEUR (INITIAL) */
		/*   Conseillé: 1-5            			       */
		/*=====================================================*/
		/** nombre de bombes pouvant être posées */
		public static int max_nbbombes;
		
	// 3. Les intervalles de valeurs
	
		/*==========================================*/
		/* # INTERVALLE DU NOMBRE MAXIMUM DE BOMBES */
		/*   POUVANT ETRE POSEES PAR JOUEUR	    */
		/*   Conseillé: 1-5             	    */
		/*==========================================*/
		/** nombre de bombes pouvant être posées mini */
		public static int min_maxnbbombes;
		/** nombre de bombes pouvant être posées maxi */
		public static int max_maxnbbombes;
		
	// 4. Les positions des joueurs
	
		/*========================================*/
		/* # POSITIONS DES JOUEURS SUR LE PLATEAU */
		/*   En général 1<=case_x<=11		  */
		/*		1<=case_y<=11		  */
		/*========================================*/
		//Utiliser pour cela la méthode de création des joueurs
		//void <- creerJoueurs(int j1_case_x, int j1_case_y, int j2_case_x, int j2_case_y)


	//++++++//
	// BLOC //
	//++++++//

		/*===============*/
		/* $ BLOCS TIMER */
		/*===============*/
		/** Informations: les blocs timer apparaissent en cours de jeu<br>
		    la premiere dimension correspond à une échelle de temps:<br>
		    le temps imparti pour un niveau est ainsi découpé en tranches<br>
		    la deuxième dimension correspond aux blocs à afficher pour chaque tranche
		  */
		public static Bloc[][] blocs_timer;

		/*=========*/
		/* # BLOCS */
		/*=========*/
		//Libre court à l'imagination de chacun
		//Pour simplifier certaines tâches, utiliser les méthodes suivantes
		//Bloc <- aleaBonus(int tabl_bonus[], int case_x, int case_y, Bloc[] autres_blocs)
		//Bloc <- aleaBonus(int tabl_bonus[], int case_x, int case_y, Bloc autre_bloc)
		//Bloc <- bonus(int type_bonus, int case_x, int case_y, Bloc autre_bloc)
		//Bloc <- bonus(int type_bonus, int case_x, int case_y, Bloc autre_bloc)


	//------------------------------------------------------------
	//		DECLARATIONS POUR NIVEAU
	//------------------------------------------------------------
	
	/*--------*/
	/* STATIC */
	/*--------*/
	
		/*---------*/
		/* PRIVATE */
		/*---------*/
		
	//les levels
	private static Niveau[] tabl_levels;
	
	//nombre de levels disponibles
	private static final int NB_LEVELS=3;
	
	//indice du level courant si non aléatoire
	private static int indice;
	
	//chargement aléatoire des levels
	private static Random alea;



	//------------------------------------------------------------
	//		CONSTRUCTEUR / INITIALISATIONS
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/
	
		/*--------*/
		/* PUBLIC */
		/*--------*/

	/** Initialisation des levels */
	public static void initLevels() {
		tabl_levels=new Niveau[NB_LEVELS];
		tabl_levels[0]=new Level_00();
		tabl_levels[1]=new Level_01();
		tabl_levels[2]=new Level_02();
		indice=0;
		//initialisation du générateur de nombres aléatoires
		alea=new Random((new Date()).getTime());
	}//initLevels();


	/*------------*/
	/* NON STATIC */
	/*------------*/

	/** Constructeur vide */
	public Niveau() {
	}//Niveau()



	//------------------------------------------------------------
	//			METHODES PUBLIQUES
	//------------------------------------------------------------
	
	/*--------*/
	/* STATIC */
	/*--------*/

	/** Charge le level suivant */
	public static void next() {
		blocs_timer=new Bloc[DIMENSION_BLOCSTIMER][];
		if (Preference.aleatoire) {
			int resultat=(Math.abs(alea.nextInt()))%NB_LEVELS;
			tabl_levels[resultat].charger();
		}
		else {
			tabl_levels[indice].charger();
			indice=(indice+1)%NB_LEVELS;
		}
	}//next();



        //------------------------------------------------------------
	//			METHODES PROTECTED
	//------------------------------------------------------------
	
		/*--------------------------------------------*/
		/* Méthodes utiles pour la création de levels */
		/*--------------------------------------------*/

	/** Tirage aléatoire d'un bonus parmi ceux proposés */
	protected static Bloc aleaBonus(int tabl_bonus[], int case_x, int case_y, Bloc[] autres_blocs) {
		int resultat=(Math.abs(alea.nextInt()))%tabl_bonus.length;
		switch (tabl_bonus[resultat]) {
			case BOMB:
				return new Bonus_Bomb(case_x, case_y, (Bloc[])autres_blocs);
			case KICKBOMB:
				return new Bonus_KickBomb(case_x, case_y, (Bloc[])autres_blocs);
			case RANGE:
				return new Bonus_Range(case_x, case_y, (Bloc[])autres_blocs);
			case REMOTECONTROL:	
				return new Bonus_RemoteControl(case_x, case_y, (Bloc[])autres_blocs);
			case IGNITE:
				return new Bonus_Ignite(case_x, case_y, (Bloc[])autres_blocs);
			case SLOW:
				return new Bonus_Slow(case_x, case_y, (Bloc[])autres_blocs);
			case SPEED:
				return new Bonus_Speed(case_x, case_y, (Bloc[])autres_blocs);
			case INVINCIBLE:	
				return new Bonus_Invincible(case_x, case_y, (Bloc[])autres_blocs);
			case LIFE:
				return new Bonus_Life(case_x, case_y, (Bloc[])autres_blocs);
			case POISON:
				return new Bonus_Poison(case_x, case_y, (Bloc[])autres_blocs);
			case MULTIPLE:
				return new Bonus_Multiple(case_x, case_y, (Bloc[])autres_blocs);
			case TRAP:	
				return new Bonus_Trap(case_x, case_y, (Bloc[])autres_blocs);
			default:
				return null;
		}//switch
	}//aleaBonus()

	/** Surcharge: tirage aléatoire d'un bonus parmi ceux proposés */
	protected static Bloc aleaBonus(int tabl_bonus[], int case_x, int case_y, Bloc autre_bloc) {
		int resultat=(Math.abs(alea.nextInt()))%tabl_bonus.length;
		switch (tabl_bonus[resultat]) {
			case BOMB:
				return new Bonus_Bomb(case_x, case_y, (Bloc)autre_bloc);
			case KICKBOMB:
				return new Bonus_KickBomb(case_x, case_y, (Bloc)autre_bloc);
			case RANGE:
				return new Bonus_Range(case_x, case_y, (Bloc)autre_bloc);
			case REMOTECONTROL:	
				return new Bonus_RemoteControl(case_x, case_y, (Bloc)autre_bloc);
			case IGNITE:
				return new Bonus_Ignite(case_x, case_y, (Bloc)autre_bloc);
			case SLOW:
				return new Bonus_Slow(case_x, case_y, (Bloc)autre_bloc);
			case SPEED:
				return new Bonus_Speed(case_x, case_y, (Bloc)autre_bloc);
			case INVINCIBLE:	
				return new Bonus_Invincible(case_x, case_y, (Bloc)autre_bloc);
			case LIFE:
				return new Bonus_Life(case_x, case_y, (Bloc)autre_bloc);
			case POISON:
				return new Bonus_Poison(case_x, case_y, (Bloc)autre_bloc);
			case MULTIPLE:
				return new Bonus_Multiple(case_x, case_y, (Bloc)autre_bloc);
			case TRAP:	
				return new Bonus_Trap(case_x, case_y, (Bloc)autre_bloc);
			default:
				return null;
		}//switch
	}//aleaBonus()
	
	/** Retourne un bonus spécifié */
	protected static Bloc bonus(int type_bonus, int case_x, int case_y, Bloc[] autres_blocs) {
		switch (type_bonus) {
			case BOMB:
				return new Bonus_Bomb(case_x, case_y, (Bloc[])autres_blocs);
			case KICKBOMB:
				return new Bonus_KickBomb(case_x, case_y, (Bloc[])autres_blocs);
			case RANGE:
				return new Bonus_Range(case_x, case_y, (Bloc[])autres_blocs);
			case REMOTECONTROL:	
				return new Bonus_RemoteControl(case_x, case_y, (Bloc[])autres_blocs);
			case IGNITE:
				return new Bonus_Ignite(case_x, case_y, (Bloc[])autres_blocs);
			case SLOW:
				return new Bonus_Slow(case_x, case_y, (Bloc[])autres_blocs);
			case SPEED:
				return new Bonus_Speed(case_x, case_y, (Bloc[])autres_blocs);
			case INVINCIBLE:	
				return new Bonus_Invincible(case_x, case_y, (Bloc[])autres_blocs);
			case LIFE:
				return new Bonus_Life(case_x, case_y, (Bloc[])autres_blocs);
			case POISON:
				return new Bonus_Poison(case_x, case_y, (Bloc[])autres_blocs);
			case MULTIPLE:
				return new Bonus_Multiple(case_x, case_y, (Bloc[])autres_blocs);
			case TRAP:	
				return new Bonus_Trap(case_x, case_y, (Bloc[])autres_blocs);
			default:
				return null;
		}//switch
	}//bonus()

	/** Surcharge: retourne un bonus spécifié */
	protected static Bloc bonus(int type_bonus, int case_x, int case_y, Bloc autre_bloc) {	
		switch (type_bonus) {
			case BOMB:
				return new Bonus_Bomb(case_x, case_y, (Bloc)autre_bloc);
			case KICKBOMB:
				return new Bonus_KickBomb(case_x, case_y, (Bloc)autre_bloc);
			case RANGE:
				return new Bonus_Range(case_x, case_y, (Bloc)autre_bloc);
			case REMOTECONTROL:	
				return new Bonus_RemoteControl(case_x, case_y, (Bloc)autre_bloc);
			case IGNITE:
				return new Bonus_Ignite(case_x, case_y, (Bloc)autre_bloc);
			case SLOW:
				return new Bonus_Slow(case_x, case_y, (Bloc)autre_bloc);
			case SPEED:
				return new Bonus_Speed(case_x, case_y, (Bloc)autre_bloc);
			case INVINCIBLE:	
				return new Bonus_Invincible(case_x, case_y, (Bloc)autre_bloc);
			case LIFE:
				return new Bonus_Life(case_x, case_y, (Bloc)autre_bloc);
			case POISON:
				return new Bonus_Poison(case_x, case_y, (Bloc)autre_bloc);
			case MULTIPLE:
				return new Bonus_Multiple(case_x, case_y, (Bloc)autre_bloc);
			case TRAP:	
				return new Bonus_Trap(case_x, case_y, (Bloc)autre_bloc);
			default:
				return null;
		}//switch
	}//bonus()
	
	/** Création des joueurs, détermine l'ordre de création 
	    nécessaire pour correspondre aux préférences
	*/
	protected static void creerJoueurs(int j1_case_x, int j1_case_y, int j2_case_x, int j2_case_y) {
		if (Preference.couleurs[0]==Preference.ROUGE) {
			new Joueur_Rouge(j1_case_x, j1_case_y);
			new Joueur_Bleu(j2_case_x, j2_case_y);
		}
		else {
			new Joueur_Bleu(j1_case_x, j1_case_y);
			new Joueur_Rouge(j2_case_x, j2_case_y);
		}
	}//creerJoueurs()
	
	/** Méthode définit dans le level: chargement du niveau */
	protected abstract void charger();


	
};//classe Niveau