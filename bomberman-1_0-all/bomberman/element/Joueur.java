/**
 	BOMBERMAN<br>
 	classe: Joueur.java<br>
 	Bonhomme bomber à spécialiser pour définir la couleur du joueur
 	@author MAC WING Michel<br>
  	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

package element;

import java.awt.*;
import java.util.*;
import base.*;
import element.*;
import general.Information;
import niveau.Niveau;
import zone.ZoneJeu;

public abstract class Joueur extends Sprite implements Constantes {
	
	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/
	
		/*--------*/
		/* PUBLIC */
		/*--------*/

	/** tableau pour obtenir les joueurs dans l'ordre de leur création */
	public static Joueur[] tabl_joueurs;

	/** les vitesses de déplacement du joueur */
	public static final int LENT=4, NORMAL=2, VITE=1;

		/*---------*/
		/* PRIVATE */
		/*---------*/
	
	//la zone de jeu
	private static ZoneJeu zonejeu;
	
	//indice du dernier joueur dans le tableau
	private static int indice_tabl_joueurs;

	//accès aux joueurs
	private static Vector vect_joueurs;

	//nombre d'images par animation (1 pour chaque direction du joueur)
	private static final int NB_IMAGES_ANIMATION=4;
	
	//nombre d'images par joueur
	private static final int NB_IMAGES_JOUEUR=16;

	//0:avancer, 1:reculer, 2:monter, 3:descendre
	private static final int AVANCER=0, RECULER=1, MONTER=2, DESCENDRE=3;
	
	
	/*------------*/
	/* NON STATIC */
	/*------------*/
	
		/*--------*/
		/* PUBLIC */
		/*--------*/

	/** identifiant pour reconnaître le joueur <-> ième joueur créé */
	public int identifiant;
	
		/*---------*/
		/* PRIVATE */
		/*---------*/
	
	//position du joueur en cases
	private int case_x, case_y;
	
	//précédente position en pixels
	private int old_x, old_y;
	
	//indice à partir duquel on obtient les images du joueur courant
	private int indice_image;
	
	//le compteur d'image pour chaque animation
	private int cpt_image;

	//tableau de booléens pour tester si la touche concernée est enfoncée
	//indice 0:avancer, 1:reculer, 2:monter, 3:descendre
	private boolean[] tabl_touches;
	
	//conserve dernier mouvement effectué
	//0:avancer, 1:reculer, 2:monter, 3:descendre
	private int dernier_mvt;
	
	//précédent mouvement effectué
	private int old_derniermvt;
		
	//état du joueur: mort ou vivant
	private boolean mort;
	
	//la vitesse modifiée à l'aide des variables suivantes
	//en réalité: nombre de pixels entre 2 images de l'animation
	private int vitesse_x, vitesse_y;

	//possession du détonateur
	private boolean detonateur;
	
	//possibilité de pousser une bombe
	private boolean pousser;
	
	//invincibilité du joueur en frames
	private int invincibilite;
		
	//longueur des explosions des bombes du joueur
	private int longueurexplosion;

	//nombre courant de bombes posées
	private int nb_bombes;
	
	//nombre courant maximum de bombes pouvant être posées simultanément
	private int maxnbbombes;


		
	//------------------------------------------------------------
	//		CONSTRUCTEUR / INITIALISATIONS
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/
	
	/** Initialisation des attributs static */
	public static void initStatic(ZoneJeu zone) {
		zonejeu=zone;
		//8: évolutivité possible...
		tabl_joueurs=new Joueur[8];
		indice_tabl_joueurs=0;
		vect_joueurs=new Vector(2);
	}//initStatic()


	/*------------*/
	/* NON STATIC */
	/*------------*/
	
	/** Construit le joueur */
        public Joueur(int case_x, int case_y, int indice_image) {
                this.case_x=case_x;
	        this.case_y=case_y;
        	this.indice_image=indice_image;
    		//-2: pas d'animation en cours
		cpt_image=-2;
		identifiant=indice_tabl_joueurs;
		//les booléens pour les touches avancer, reculer, monter, descendre
		tabl_touches=new boolean[4];
        	dernier_mvt=3;
        	old_derniermvt=dernier_mvt;
        	//ajouter le joueur à la liste des joueurs
       		tabl_joueurs[indice_tabl_joueurs]=this;
       		indice_tabl_joueurs++;
		vect_joueurs.addElement(this);
		//Sprite
        	this.x = Utils.convertJXcp(case_x);
	  	this.y = Utils.convertJYcp(case_y);
	  	old_x=x;
	  	old_y=y;
	  	setVelocity(0, 0);  	
	  	initDefault();
	}//Joueur()



        //------------------------------------------------------------
	//			METHODES PUBLIQUES
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/

	/** Exécute les animations de tous les joueurs */
	public static void paintOll(Graphics g) {
		Enumeration e = vect_joueurs.elements();
		while (e.hasMoreElements()) {
			((Joueur)e.nextElement()).rafraichirZone(g);
     		}//while
     		//trier pour obtenir un ordre d'affichage des joueurs
     		trier();
     		e = vect_joueurs.elements();
     		while (e.hasMoreElements()) {
			((Joueur)e.nextElement()).animation(g);
     		}//while
	}//paintOll()
	
	
	/*------------*/
	/* NON STATIC */
	/*------------*/

	/** Dessine le joueur */
	public void paintJoueur(Graphics g) {
		int numero_image;
		if (mort) {
			//le squelette
			numero_image=ZoneJeu.I_JOUEURS+dernier_mvt; invincibilite=0;
		}
		else numero_image=indice_image+NB_IMAGES_ANIMATION*dernier_mvt+cpt_image;
		if (invincibilite>0) invincibilite--;
		if (invincibilite%2==0) {
			g.drawImage(zonejeu.images[numero_image], x, y, zonejeu);
			zonejeu.affichage(numero_image+"#"+x+"#"+y+"#");
		}
		if (mort) {return;}
		//on repaint les flammes en fonction de leurs positions par rapport au joueur
		//ainsi, la tête du joueur ne doit pas être recouvert par une flamme...
		case_x=Utils.convertJXpc(x);
		case_y=Utils.convertJYpc(y);
		int case_ym=case_y-1;
		if (Flamme.tabl_flammes[case_x][case_y]!=0) {
			Flamme.paintFlamme(g, case_x, case_y);
			Flamme.paintFlamme(g, case_x, case_ym);
		}
		if (!isSurCase()) {
			int case_xp=case_x+1;
			Flamme.paintFlamme(g, case_x, case_y+1);
			if (Flamme.tabl_flammes[case_xp][case_y]!=0) {
				Flamme.paintFlamme(g, case_xp, case_y);
				Flamme.paintFlamme(g, case_xp, case_ym);
			}
		}
	}//paintJoueur()

		/*-----------------------------------*/
		/* Méthodes d'éxécution du mouvement */
		/*-----------------------------------*/
	
	/** Faire avancer le joueur */
	public void avancer() {
		tabl_touches[AVANCER]=true;
		setVelocity(vitesse_x, 0);
		//si le joueur change de direction à 90°, il est placé sur la 
		//prochaine case afin de fluidifier l'affichage
		if (!isSurCase()) {
			if (dernier_mvt==MONTER)    { y=Utils.positioncaseJY(y, 0); }
			if (dernier_mvt==DESCENDRE) { y=Utils.positioncaseJY(y, 1); }
		}
		if (dernier_mvt!=AVANCER) { dernier_mvt=AVANCER; cpt_image=0; }
	}//avancer()

	/** Faire reculer le joueur */
	public void reculer() {
		tabl_touches[RECULER]=true;
		setVelocity(-vitesse_x, 0);
		//si le joueur change de direction à 90°, il est placé sur la 
		//prochaine case afin de fluidifier l'affichage
		if (!isSurCase()) {
			if (dernier_mvt==MONTER)    { y=Utils.positioncaseJY(y, 0); }
			if (dernier_mvt==DESCENDRE) { y=Utils.positioncaseJY(y, 1); }
		}
		if (dernier_mvt!=RECULER) { dernier_mvt=RECULER; cpt_image=0; }
	}//reculer()

	/** Faire monter le joueur */
	public void monter() {
		tabl_touches[MONTER]=true;
		setVelocity(0,-vitesse_y);
		//si le joueur change de direction à 90°, il est placé sur la 
		//prochaine case afin de fluidifier l'affichage
		if (!isSurCase()) {
			if (dernier_mvt==AVANCER) { x=Utils.positioncaseJX(x, 1); }
			if (dernier_mvt==RECULER) { x=Utils.positioncaseJX(x, 0); }
		}
		if (dernier_mvt!=MONTER) { dernier_mvt=MONTER; cpt_image=0; }
	}//monter()
	
	/** Faire descendre le joueur */
	public void descendre() {
		tabl_touches[DESCENDRE]=true;
		setVelocity(0, vitesse_y);
		//si le joueur change de direction à 90°, il est placé sur la 
		//prochaine case afin de fluidifier l'affichage
		if (!isSurCase()) {
			if (dernier_mvt==AVANCER) { x=Utils.positioncaseJX(x, 1); }
			if (dernier_mvt==RECULER) { x=Utils.positioncaseJX(x, 0); }
		}
		if (dernier_mvt!=DESCENDRE) { dernier_mvt=DESCENDRE; cpt_image=0; }
	}//descendre()

		/*---------------------*/
		/* Ordres particuliers */
		/*---------------------*/	
	
	/** Poser une bombe */
	public void poserBombe() {
		if (nb_bombes<maxnbbombes) {
			if (Bombe.tabl_bombes[case_x][case_y]==null) {
				nb_bombes++;
				new Bombe(case_x, case_y, detonateur, longueurexplosion, this);
			}
		}
	}//poserBombe();

	/** Faire exploser les bombes à détonateur du joueur */
	public void exploserBombes() {
		Bombe.exploserDetonateur(this);
	}//exploserBombe();

		/*-------------------------*/
		/* Méthodes pour les bonus */
		/*-------------------------*/

	/** Spécifie la possession du détonateur */
	public void setDetonateur(boolean etat) {
		detonateur=etat;
	}//setDetonateur();

	/** Spécifie si le joueur peut pousser les bombes */
	public void setPousser(boolean etat) {
		pousser=etat;
	}//setPousser();
	
	/** Augmente l'invincibilité des joueurs */
	public void addInvincibilite(int longueur) {
		invincibilite+=longueur;
		if (invincibilite>Niveau.max_invincibilite)
			invincibilite=Niveau.max_invincibilite;
		else if (invincibilite<0)
			invincibilite=0;
	}//addInvincibilite()
	
	/** Augmente la longueur d'explosion des bombes posées */
	public void addLongueurExlosion(int longueur) {
		longueurexplosion+=longueur;
		if (longueurexplosion>Niveau.max_longueurexplosion)
			longueurexplosion=Niveau.max_longueurexplosion;
		else if (longueurexplosion<Niveau.def_longueurexplosion)
			longueurexplosion=Niveau.def_longueurexplosion;
	}//addLongueurExlosion()
	
	/** Augmente le nombre de bombes pouvant être simultanément posées */
	public void addMaxNbBombes(int nombre) {
		maxnbbombes+=nombre;
		if (maxnbbombes>Niveau.max_maxnbbombes)
			maxnbbombes=Niveau.max_maxnbbombes;
		else if (maxnbbombes<Niveau.min_maxnbbombes)
			maxnbbombes=Niveau.min_maxnbbombes;
	}//addMaxNbBombes()
	
	/** Réduction du nombre de bombes posées (explosion) */
	public void reduireNbBombes() {
		nb_bombes--;
	}//reduireNbBombes()
	
	/** Reduire la vitesse de déplacement du joueur */
	public void reduireVitesseDeplacement() {
		if (vitesse_x==CASE_LARGEUR) {
			vitesse_x=CASE_LARGEUR/NORMAL;
			vitesse_y=CASE_HAUTEUR/NORMAL;
		}
		else if (vitesse_x==CASE_LARGEUR/NORMAL) {
			vitesse_x=CASE_LARGEUR/LENT;
			vitesse_y=CASE_HAUTEUR/LENT;
		}
		switch (dernier_mvt) {
			case AVANCER:
				setVelocity(vitesse_x, 0);
				break;
			case RECULER:
				setVelocity(-vitesse_x, 0);
				break;
			case MONTER:
				setVelocity(0, -vitesse_y);
				break;
			case DESCENDRE:
				setVelocity(0, vitesse_y);
				break;
		}
	}//reduireVitesseDeplacement()
	
	/** Augmenter la vitesse de déplacement du joueur */
	public void augmenterVitesseDeplacement() {
		if (vitesse_x==CASE_LARGEUR/LENT) {
			vitesse_x=CASE_LARGEUR/NORMAL;
			vitesse_y=CASE_HAUTEUR/NORMAL;
		}
		else if (vitesse_x==CASE_LARGEUR/NORMAL) {
			vitesse_x=CASE_LARGEUR;
			vitesse_y=CASE_HAUTEUR;
		}
		switch (dernier_mvt) {
			case AVANCER:
				setVelocity(vitesse_x, 0);
				break;
			case RECULER:
				setVelocity(-vitesse_x, 0);
				break;
			case MONTER:
				setVelocity(0, -vitesse_y);
				break;
			case DESCENDRE:
				setVelocity(0, vitesse_y);
				break;
		}
	}//augmenterVitesseDeplacement()

	/** Spécifie si joueur mort ou vivant */
	public void setMort(boolean etat) {
		mort=etat;
	}//setMort()
	
		/*----------------------------------------*/
		/*  Méthodes d'accès et de modifications  */
		/*----------------------------------------*/
	
	/** Spécifie touche Avancer enfoncée ou non
		enfoncée <-> true
		relevée  <-> false
	 */
	public void setAvancer(boolean valeur) {
		tabl_touches[AVANCER]=valeur;
	}//setAvancer()

	/** Spécifie touche Reculer enfoncée ou non
		enfoncée <-> true
		relevée  <-> false
	 */	
	public void setReculer(boolean valeur) {
		tabl_touches[RECULER]=valeur;
	}//setReculer()
	
	/** Spécifie touche Monter enfoncée ou non
		enfoncée <-> true
		relevée  <-> false
	 */
	public void setMonter(boolean valeur) {
		tabl_touches[MONTER]=valeur;
	}//setMonter()

	/** Spécifie touche Descendre enfoncée ou non
		enfoncée <-> true
		relevée  <-> false
	 */	
	public void setDescendre(boolean valeur) {
		tabl_touches[DESCENDRE]=valeur;
	}//setDescendre()

	

        //------------------------------------------------------------
	//			METHODES PRIVEES
	//------------------------------------------------------------
	
	/*--------*/
	/* STATIC */
	/*--------*/
	
	/** tri pour obtenir un ordre d'affichage des joueurs
	    si un joueur est au dessus d'un autre, celui ci doit-être affiché en
	    premier pour ne pas cacher la tête de l'autre
	 */
	private static void trier() {
		//un simple tri à bulles (on n'a pas 2000 joueurs)
		int taille=vect_joueurs.size();
		int i=0;
		Joueur joueur_tmp1, joueur_tmp2;
		while(i < taille-1) {
			joueur_tmp1=(Joueur)vect_joueurs.elementAt(i);
			joueur_tmp2=(Joueur)vect_joueurs.elementAt(i+1);
			if (joueur_tmp2.y <= joueur_tmp1.y) {
				vect_joueurs.setElementAt(joueur_tmp2, i);
				vect_joueurs.setElementAt(joueur_tmp1, i+1);
			}//if
			i=joueur_tmp2.y<joueur_tmp1.y?0:i+1;
		}//while
	}//tri()


	/*------------*/
	/* NON STATIC */
	/*------------*/
	
	/** Efface l'image de l'ancienne position */
	private void rafraichirZone(Graphics g) {
		int case_x=Utils.convertXpc(old_x);
		int case_y=Utils.convertYpc(old_y);
		switch(old_derniermvt) {
			case AVANCER:
			case RECULER:
				for (int i=case_y; i<=case_y+1; i++) {
					for (int j=case_x; j<=case_x+1; j++) {
						Bloc.paintBloc(g, j, i);
    						Bombe.paintBombe(g, j, i);
    						Flamme.paintFlamme(g, j, i);
					}//for
				}//for
				break;
			case MONTER:
			case DESCENDRE:
				//attention à ne pas dépasser les limites du plateau
				int lim_casey=case_y+2>NB_CASES_COLONNE?NB_CASES_COLONNE:case_y+2;
				for (int i=case_y; i<=lim_casey; i++) {
					Bloc.paintBloc(g, case_x, i);
    					Bombe.paintBombe(g, case_x, i);
    					Flamme.paintFlamme(g, case_x, i);
				}//for
				break;
		}//switch
	}//rafraichirZone()
	
	/** Animation du joueur */
	private void animation(Graphics g) {
		//joueur en mouvement qui s'arrête
    		if (!isChange(g)) {
    			cpt_image=0;
    		}
    		//joueur en mouvement
    		else {
    			translate();
    			cpt_image++;
    			cpt_image=cpt_image%NB_IMAGES_ANIMATION;
		}
		//vérifie s'il est touché par une flamme
		toucherFlamme(g);
		//vérifie s'il a été écrasé par une brique
		ecrasementBrique(g);
		//paint finalement le joueur
		paintJoueur(g);
		//conserve les coordonnées en cours pour effacer l'image courante
		//lors du prochain appel
		old_x=x;
		old_y=y;
		old_derniermvt=dernier_mvt;
	}//animation()

	/** Test si le joueur occupe une case entièrement */
	private boolean isSurCase() {
		if ( ((y+JOUEUR_HAUTEUR)%CASE_HAUTEUR==0) && (x%CASE_LARGEUR==0) ) return true;
		else return false;
	}//isSurCase()

	/** Test si le joueur rencontre un obstacle: bombe ou brique */
	private boolean isObstacle(Graphics g) {
	        case_x=Utils.convertJXpc(x);
		case_y=Utils.convertJYpc(y);
		int horiz=0, vertic=0;
		Bloc bloc;
		Bombe bombe;
		switch(dernier_mvt) {
			case AVANCER:
				//obstacle juste à droite ?
				horiz=1;
				break;
			case RECULER:
				//obstacle juste à gauche ?
				horiz=-1;
				break;
			case MONTER:
				//obstacle juste en haut ?
				vertic=-1;
				break;
			case DESCENDRE:
				//obstacle juste en bas ?
				vertic=1;
				break;
			default:
		}//switch
		bloc=Bloc.tabl_blocs[case_x+horiz][case_y+vertic];
		bloc.action(g, this);
		bombe=Bombe.tabl_bombes[case_x+horiz][case_y+vertic];
		
		if (bombe==null) {
			if (bloc.isPave() || bloc.isBonus()) return false;
		}
		else if (pousser) bombe.setVelocityCases(horiz, vertic);
		return true;
	}//isObstacle()

	/** Retourne vrai si le joueur a changé de place */
	private boolean isChange(Graphics g) {
                if ( isSurCase() && (!tabl_touches[dernier_mvt] || isObstacle(g)) ) return false;
		else return true;
	}//isChange()
	
	/** Effectue les modifications nécessaires si le joueur est touché par une flamme */
	private void toucherFlamme(Graphics g) {
		if (invincibilite>0) return;
		//le joueur est touché
		else if (isSurCase() && Flamme.tabl_flammes[case_x][case_y]>0) {
			//retranche une vie
			Information.addVies(g, this, -1);
			initDefault();
			//toutes ses bombes explosent
			Bombe.exploserJoueur(this);
		}
	}//toucherFlamme

	/** Regarde si le joueur a été écrasé par l'apparition d'une brique */
	private void ecrasementBrique(Graphics g) {
		int case_x;
		int case_y;
		if (isSurCase()) {
			case_x=Utils.convertJXpc(x);
			case_y=Utils.convertJYpc(y);
		}
		else {
			switch(dernier_mvt) {
				case AVANCER:	
					//brique droite
					case_x=Utils.convertXpc(x+JOUEUR_LARGEUR-10);
					case_y=Utils.convertYpc(y+Utils.JOUEUR_CASE_HDIFF);
					break;
				case RECULER:
					//brique gauche
					case_x=Utils.convertXpc(x+10);
					case_y=Utils.convertYpc(y+Utils.JOUEUR_CASE_HDIFF);
					break;
				case MONTER:
					//brique haut
					case_x=Utils.convertXpc(x);
					case_y=Utils.convertYpc(y+Utils.JOUEUR_CASE_HDIFF+10);		
					break;
				case DESCENDRE:
					//brique bas
					case_x=Utils.convertXpc(x);
					case_y=Utils.convertYpc(y+JOUEUR_HAUTEUR-10);
					break;
				default:
					case_x=0;
					case_y=0;
					System.out.println("Ceci ne doit pas se produire, erreur ecrasementBrique");
			}//switch
		}//else
		if ( !Bloc.tabl_blocs[case_x][case_y].isPave() &&
			!Bloc.tabl_blocs[case_x][case_y].isBonus() ) {
			//écrasement et mort du joueur
			Information.addVies(g, this, -10);
		}
	}//ecrasementBrique()
	
	/** Initialise les propriétés par défaut selon le niveau en cours */
	private void initDefault() {
		vitesse_x=CASE_LARGEUR/Niveau.def_vitessedeplacement;
		vitesse_y=CASE_HAUTEUR/Niveau.def_vitessedeplacement;
		detonateur=Niveau.def_detonateur;
		pousser=Niveau.def_pousser;
		invincibilite=Niveau.def_invincibilite;
		longueurexplosion=Niveau.def_longueurexplosion;
		maxnbbombes=Niveau.min_maxnbbombes;
	}//initDefault()



};//classe Joueur
