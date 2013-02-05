package element;

import java.awt.*;
import java.util.*;
import base.*;
import element.*;
import niveau.Niveau;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Bombe.java<br>
 	Bombe, élément indispensable pour les joueurs :)
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Bombe extends Sprite implements Constantes {

	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/

		/*--------*/
		/* PUBLIC */
		/*--------*/

	/** un tableau pour obtenir la disposition des bombes affichés */
	public static Bombe[][] tabl_bombes;

	/** un vecteur pour accéder rapidement aux bombes */
	private static Vector vect_bombes;
		
	//rebonds possibles
	/** pas de rebonds */
	public static final int REBOND_NULL=0;
	/** rebonds avec la même orientation */
	public static final int REBOND_MEME_ORIENTATION=1;
	/** rebonds avec orientation changeant */
	public static final int REBOND_DIF_ORIENTATION=2;

		/*---------*/
		/* PRIVATE */
		/*---------*/

	//la zone de jeu
	private static ZoneJeu zonejeu;

	//nombre d'images pour animation de la bombe
	private static final int NB_IMAGES_ANIMATION=16;

	
	/*------------*/
	/* NON STATIC */
	/*------------*/
	
		/*---------*/
		/* PRIVATE */
		/*---------*/

	//position de la bombe en cases
	private int case_x, case_y;

	//spécifie si une bombe est à détonateur
	private boolean detonateur;
	
	//longueur de l'explosion
	private int longueur;
	
	//le propriétaire de la bombe
	private Joueur joueur;
	
	//velocité en cases
	private int vel_casex, vel_casey;

	//le compteur d'image pour l'animation
	private int cpt_image;

	//délai courant
	private int delai;



	//------------------------------------------------------------
	//		CONSTRUCTEUR / INITIALISATIONS
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/
	
	/** Initialisation des attributs static */
	public static void initStatic(ZoneJeu zone) {
		zonejeu=zone;
		tabl_bombes=new Bombe[NB_CASES_LIGNE][NB_CASES_COLONNE];
		vect_bombes=new Vector(20);
	}//initStatic()
	

	/*------------*/
	/* NON STATIC */
	/*------------*/

	/** Constuit la bombe avec les propriétés spécifiées */
    	public Bombe(int case_x, int case_y, boolean detonateur, int longueur, Joueur joueur) {
		this.case_x=case_x;
        	this.case_y=case_y;
        	this.detonateur=detonateur;
        	this.longueur=longueur;
        	this.joueur=joueur;
        	this.vel_casex=0;
        	this.vel_casey=0;
        	this.delai=Niveau.delai_explosion;
		//début animation: 0
		cpt_image=0;
		tabl_bombes[case_x][case_y]=this;
		vect_bombes.addElement(this);
		//Sprite
		this.x=Utils.convertXcp(case_x);
		this.y=Utils.convertYcp(case_y);
		setVelocity(0, 0);
	}//Bombe()


	
        //------------------------------------------------------------
	//			METHODES PUBLIQUES
	//------------------------------------------------------------
	
	/*--------*/
	/* STATIC */
	/*--------*/

	/** Exécute l'animation de toutes les bombes */
   	public static void paintOll(Graphics g) {
    		Enumeration e;
    		e = vect_bombes.elements();
		while (e.hasMoreElements()) {
			((Bombe)e.nextElement()).rafraichirZone(g);
     		}//while
     		Vector copie_bombes=(Vector)vect_bombes.clone();
     		e = copie_bombes.elements();
     		while (e.hasMoreElements()) {
			((Bombe)e.nextElement()).animation(g);
     		}//while
    	}//paintOll()
    	
    	/** Dessine une bombe à une position donnée */
    	public static void paintBombe(Graphics g, int case_x, int case_y) {
		if ( tabl_bombes[case_x][case_y]==null ) return;
		else tabl_bombes[case_x][case_y].paint(g);
	}//paintBombe();

	/** Exploser toutes les bombes à détonateur du joueur donné */
	public static void exploserDetonateur(Joueur joueur) {
		Enumeration e;
		Vector copie_bombes=(Vector)vect_bombes.clone();
     		e = copie_bombes.elements();
     		Bombe bombe;
     		while (e.hasMoreElements()) {
     			bombe=(Bombe)e.nextElement();
			if ((bombe.joueur==joueur) && (bombe.detonateur))
				bombe.exploser();
     		}//while
	}//exploserDetonateur()

	/** Exploser toutes les bombes d'un joueur donné */
	public static void exploserJoueur(Joueur joueur) {
		Enumeration e;
		Vector copie_bombes=(Vector)vect_bombes.clone();
     		e = copie_bombes.elements();
     		Bombe bombe;
     		while (e.hasMoreElements()) {
     			bombe=(Bombe)e.nextElement();
			if (bombe.joueur==joueur)
				bombe.exploser();
     		}//while
	}//exploserJoueur()
	
	/** Exploser toutes les bombes */
	public static void exploserOll() {
		Enumeration e;
		Vector copie_bombes=(Vector)vect_bombes.clone();
     		e = copie_bombes.elements();
     		Bombe bombe;
     		while (e.hasMoreElements()) {
     			bombe=(Bombe)e.nextElement();
				bombe.exploser();
     		}//while
	}//exploserJoueur()

	/** Supprimer une bombe à une position donnée */
	public static void supprimerBombe(int case_x, int case_y) {
		if (tabl_bombes[case_x][case_y]==null) return;
		vect_bombes.removeElement(tabl_bombes[case_x][case_y]);
		tabl_bombes[case_x][case_y]=null;
	}//supprimerBombe();


	/*------------*/
	/* NON STATIC */
	/*------------*/

    	/** Dessine une bombe */
    	public void paint(Graphics g) {
    		int numero_image=zonejeu.I_BOMBES+cpt_image;
    		g.drawImage(zonejeu.images[numero_image], x, y, zonejeu);
		zonejeu.affichage(numero_image+"#"+x+"#"+y+"#");
    	}//paint()

	/** Modifie la vélocité en cases */
	public void setVelocityCases(int vel_casex, int vel_casey) {
		this.vel_casex=vel_casex;
		this.vel_casey=vel_casey;
		setVelocity(Utils.convertXcp(vel_casex), Utils.convertYcp(vel_casey));
	}//setVelocity()


    	
        //------------------------------------------------------------
	//			METHODES PRIVEES
	//------------------------------------------------------------

	/*------------*/
	/* NON STATIC */
	/*------------*/
    	
	/** Rafraîchit la zone où se trouve la bombe */
	private void rafraichirZone(Graphics g) {
		Bloc.paintBloc(g, case_x, case_y);
	}//rafraichirZone()

    	//animation de la bombe
	private void animation(Graphics g) {
		glisser();
		if (Flamme.tabl_flammes[case_x][case_y]!=0) {exploser(); return;}
		if (detonateur) {cpt_image=15;}
		else {
			if (delai==0) {
				delai=Niveau.delai_explosion;
    				if (cpt_image<NB_IMAGES_ANIMATION-1) cpt_image++;
    				else exploser();
    			}
    			else delai--;
    		}
    		paint(g);
	}//animation()

	/** Explosion de la bombe */
	private void exploser() {
		joueur.reduireNbBombes();
		vect_bombes.removeElement(this);
		tabl_bombes[case_x][case_y]=null;
		//une explosion se produit
		new Explosion(case_x, case_y, longueur, longueur);
	}//exploser()

	/** La bombe glisse ou non sur le pavé */
	private void glisser() {
		int tmp_casex=case_x+vel_casex;
		int tmp_casey=case_y+vel_casey;
		//la bombe rebondi si elle rencontre un obstacle
		if (!Bloc.tabl_blocs[tmp_casex][tmp_casey].isPave() ||
			Bombe.tabl_bombes[tmp_casex][tmp_casey]!=null ) {
			if (Niveau.type_rebond==REBOND_NULL) setVelocityCases(0, 0);
			else if (Niveau.type_rebond==REBOND_MEME_ORIENTATION)
				setVelocityCases(-vel_casex, -vel_casey);
			else {
				setVelocityCases(-vel_casey, vel_casex);
			}
		}
		if (!Bloc.tabl_blocs[case_x+vel_casex][case_y+vel_casey].isPave())
			setVelocityCases(0, 0);
		//elle n'est plus à l'ancienne position
		tabl_bombes[case_x][case_y]=null;
		case_x+=vel_casex;
		case_y+=vel_casey;
		//nouvelle position
		tabl_bombes[case_x][case_y]=this;
		translate();
	}//glisser()
	
	

};//classe Bombe