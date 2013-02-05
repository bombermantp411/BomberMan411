package zone;

import java.awt.*;
import base.*;
import general.Information;

/**
 	BOMBERMAN<br>
 	classe: ZoneJeu.java<br>
 	La zone de jeu<br>
 	Cette classe est étendue: spécialisation suivant serveur ou client
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public abstract class ZoneJeu extends Panel implements Constantes {

	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------
	
	/*--------*/
	/* STATIC */
	/*--------*/
	
		/*--------*/
		/* PUBLIC */
		/*--------*/
	
	/** dimension de la zone de jeu */
	public static final int ZONEJEU_LARGEUR=PLATEAU_LARGEUR;
	/** dimension de la zone de jeu */
	public static final int ZONEJEU_HAUTEUR=PLATEAU_HAUTEUR+Information.HAUTEUR;
	
	//les indices images de début de chaque élément type
	/** indices images: blocs pavés 0-49 */
	public static final int I_BLOCS_PAVES=0;
	
	/** indices images: blocs briques 50-99 */
	public static final int I_BLOCS_BRIQUES=50;
	
	/** indices images: blocs bonus 100-149 */
	public static final int I_BLOCS_BONUS=100;
	
	/** indices images: bombes 150-199 */
	public static final int I_BOMBES=150;
	
	/** indices images: flammes 200-249 */
	public static final int I_FLAMMES=200;
	
	/** indices images: joueurs 250-399 */
	public static final int I_JOUEURS=250;
	
	//indice images des informations générales
	/** indices images: infos 400-449 */
	public static final int I_INFORMATION=400;
	
	/** images de début */
	public static final int I_PRESENTATION=450;
	
	/** images du milieu */
	public static final int I_MILIEU=460;
	
	/** images de fin */
	public static final int I_FIN=480;

		/*---------*/
		/* PRIVATE */
		/*---------*/
		
	//nombre d'images de la zone de jeu au total
	private static final int NB_IMAGES_TOTAL=500;


	/*------------*/
	/* NON STATIC */
	/*------------*/
		
		/*--------*/
		/* PUBLIC */
		/*--------*/
		
	/** les images de la zone de jeu */
	public Image images[];
	
		/*---------*/
		/* PRIVATE */
		/*---------*/
		
	//le mediatracker pour surveiller le chargement complet des images
	private MediaTracker tracker;

	

	//------------------------------------------------------------
	//		CONSTRUCTEUR / INITIALISATIONS
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/
	
	/** Charge toutes les images nécessaires à la zone de jeu */
	public void initImages() {
		images=new Image[NB_IMAGES_TOTAL];
		Toolkit tk=getToolkit();
		
		//blocs pavés 0-49
		images[I_BLOCS_PAVES+0]=tk.getImage("./images/bloc/pave/button.gif");
		images[I_BLOCS_PAVES+1]=tk.getImage("./images/bloc/pave/check.gif");
		images[I_BLOCS_PAVES+2]=tk.getImage("./images/bloc/pave/chess.gif");
		images[I_BLOCS_PAVES+3]=tk.getImage("./images/bloc/pave/cityfree.gif");
		images[I_BLOCS_PAVES+4]=tk.getImage("./images/bloc/pave/controlalpha.gif");
		images[I_BLOCS_PAVES+5]=tk.getImage("./images/bloc/pave/controlnum.gif");
		images[I_BLOCS_PAVES+6]=tk.getImage("./images/bloc/pave/darkway.gif");
		images[I_BLOCS_PAVES+7]=tk.getImage("./images/bloc/pave/ghost.gif");
		images[I_BLOCS_PAVES+8]=tk.getImage("./images/bloc/pave/hex.gif");
		images[I_BLOCS_PAVES+9]=tk.getImage("./images/bloc/pave/hole.gif");
		images[I_BLOCS_PAVES+10]=tk.getImage("./images/bloc/pave/iron.gif");
		images[I_BLOCS_PAVES+11]=tk.getImage("./images/bloc/pave/karodark.gif");
		images[I_BLOCS_PAVES+12]=tk.getImage("./images/bloc/pave/karolight.gif");
		images[I_BLOCS_PAVES+13]=tk.getImage("./images/bloc/pave/lego.gif");
		images[I_BLOCS_PAVES+14]=tk.getImage("./images/bloc/pave/mrbeam.gif");
		images[I_BLOCS_PAVES+15]=tk.getImage("./images/bloc/pave/rock.gif");
		images[I_BLOCS_PAVES+16]=tk.getImage("./images/bloc/pave/spherehalf.gif");
		
		//blocs briques 50-99
		images[I_BLOCS_BRIQUES+0]=tk.getImage("./images/bloc/brique/bookshelf.gif");
		images[I_BLOCS_BRIQUES+1]=tk.getImage("./images/bloc/brique/box.gif");
		images[I_BLOCS_BRIQUES+2]=tk.getImage("./images/bloc/brique/brick.gif");
		images[I_BLOCS_BRIQUES+3]=tk.getImage("./images/bloc/brique/bricks.gif");
		images[I_BLOCS_BRIQUES+4]=tk.getImage("./images/bloc/brique/chesssphere.gif");
		images[I_BLOCS_BRIQUES+5]=tk.getImage("./images/bloc/brique/chesssphere_d.gif");
		images[I_BLOCS_BRIQUES+6]=tk.getImage("./images/bloc/brique/chest.gif");
		images[I_BLOCS_BRIQUES+7]=tk.getImage("./images/bloc/brique/chest_d.gif");
		images[I_BLOCS_BRIQUES+8]=tk.getImage("./images/bloc/brique/dark.gif");
		images[I_BLOCS_BRIQUES+9]=tk.getImage("./images/bloc/brique/dark_l.gif");
		images[I_BLOCS_BRIQUES+10]=tk.getImage("./images/bloc/brique/darkhouse.gif");
		images[I_BLOCS_BRIQUES+11]=tk.getImage("./images/bloc/brique/downarrow.gif");
		images[I_BLOCS_BRIQUES+12]=tk.getImage("./images/bloc/brique/extra.gif");
		images[I_BLOCS_BRIQUES+13]=tk.getImage("./images/bloc/brique/extra_d.gif");
		images[I_BLOCS_BRIQUES+14]=tk.getImage("./images/bloc/brique/ghostcircle.gif");
		images[I_BLOCS_BRIQUES+15]=tk.getImage("./images/bloc/brique/ghostsquare.gif");
		images[I_BLOCS_BRIQUES+16]=tk.getImage("./images/bloc/brique/hexaextra.gif");
		images[I_BLOCS_BRIQUES+17]=tk.getImage("./images/bloc/brique/hexawall.gif");
		images[I_BLOCS_BRIQUES+18]=tk.getImage("./images/bloc/brique/jizz.gif");
		images[I_BLOCS_BRIQUES+19]=tk.getImage("./images/bloc/brique/legoblack.gif");
		images[I_BLOCS_BRIQUES+20]=tk.getImage("./images/bloc/brique/legoblack_d.gif");
		images[I_BLOCS_BRIQUES+21]=tk.getImage("./images/bloc/brique/legowhite.gif");
		images[I_BLOCS_BRIQUES+22]=tk.getImage("./images/bloc/brique/mrbeambear.gif");
		images[I_BLOCS_BRIQUES+23]=tk.getImage("./images/bloc/brique/mrbeamtv.gif");
		images[I_BLOCS_BRIQUES+24]=tk.getImage("./images/bloc/brique/pumpkin.gif");
		images[I_BLOCS_BRIQUES+25]=tk.getImage("./images/bloc/brique/pyramid.gif");
		images[I_BLOCS_BRIQUES+26]=tk.getImage("./images/bloc/brique/pyramid_l.gif");
		images[I_BLOCS_BRIQUES+27]=tk.getImage("./images/bloc/brique/spheredark.gif");
		images[I_BLOCS_BRIQUES+28]=tk.getImage("./images/bloc/brique/spherelight.gif");
		images[I_BLOCS_BRIQUES+29]=tk.getImage("./images/bloc/brique/spherelight_d.gif");
		images[I_BLOCS_BRIQUES+30]=tk.getImage("./images/bloc/brique/temple.gif");
		images[I_BLOCS_BRIQUES+31]=tk.getImage("./images/bloc/brique/uparrow.gif");
		images[I_BLOCS_BRIQUES+32]=tk.getImage("./images/bloc/brique/wall.gif");
		images[I_BLOCS_BRIQUES+33]=tk.getImage("./images/bloc/brique/wall_l.gif");
		images[I_BLOCS_BRIQUES+34]=tk.getImage("./images/bloc/brique/weight.gif");
		images[I_BLOCS_BRIQUES+35]=tk.getImage("./images/bloc/brique/weight_l.gif");
				
		//blocs bonus 100-149
		images[I_BLOCS_BONUS+0]=tk.getImage("./images/bloc/bonus/bomb.gif");
		images[I_BLOCS_BONUS+1]=tk.getImage("./images/bloc/bonus/kickbomb.gif");
		images[I_BLOCS_BONUS+2]=tk.getImage("./images/bloc/bonus/range.gif");
		images[I_BLOCS_BONUS+3]=tk.getImage("./images/bloc/bonus/remotecontrol.gif");
		images[I_BLOCS_BONUS+4]=tk.getImage("./images/bloc/bonus/ignite.gif");
		images[I_BLOCS_BONUS+5]=tk.getImage("./images/bloc/bonus/slow.gif");
		images[I_BLOCS_BONUS+6]=tk.getImage("./images/bloc/bonus/speed.gif");
		images[I_BLOCS_BONUS+7]=tk.getImage("./images/bloc/bonus/invincible.gif");
		images[I_BLOCS_BONUS+8]=tk.getImage("./images/bloc/bonus/life.gif");
		images[I_BLOCS_BONUS+9]=tk.getImage("./images/bloc/bonus/poison.gif");
		images[I_BLOCS_BONUS+10]=tk.getImage("./images/bloc/bonus/multiple.gif");
		images[I_BLOCS_BONUS+11]=tk.getImage("./images/bloc/bonus/trap.gif");
				
		//bombes 150-199
		for (int i=0; i<16; i++) {
			images[I_BOMBES+i]=tk.getImage("./images/bombe/bombe"+i+".gif");
		}//for
		
		//flammes 200-249
		images[I_FLAMMES+0]=tk.getImage("./images/flamme/0_zero.gif");
		images[I_FLAMMES+1]=tk.getImage("./images/flamme/1_boutdroite.gif");
		images[I_FLAMMES+2]=tk.getImage("./images/flamme/2_boutgauche.gif");
		images[I_FLAMMES+3]=tk.getImage("./images/flamme/3_horiz.gif");
		images[I_FLAMMES+4]=tk.getImage("./images/flamme/4_boutbas.gif");
		images[I_FLAMMES+5]=tk.getImage("./images/flamme/5_droitehaut.gif");
		images[I_FLAMMES+6]=tk.getImage("./images/flamme/6_gauchehaut.gif");
		images[I_FLAMMES+7]=tk.getImage("./images/flamme/7_horizhaut.gif");
		images[I_FLAMMES+8]=tk.getImage("./images/flamme/8_bouthaut.gif");
		images[I_FLAMMES+9]=tk.getImage("./images/flamme/9_droitebas.gif");
		images[I_FLAMMES+10]=tk.getImage("./images/flamme/10_gauchebas.gif");
		images[I_FLAMMES+11]=tk.getImage("./images/flamme/11_horizbas.gif");
		images[I_FLAMMES+12]=tk.getImage("./images/flamme/12_vertic.gif");
		images[I_FLAMMES+13]=tk.getImage("./images/flamme/13_verticgauche.gif");
		images[I_FLAMMES+14]=tk.getImage("./images/flamme/14_verticdroite.gif");
		images[I_FLAMMES+15]=tk.getImage("./images/flamme/15_croix.gif");
		
		//joueurs 250-399
		//squelettes
		//4 images
    		//Avancer
		images[I_JOUEURS+0]=tk.getImage("./images/joueur/skeldroite.gif");
		//Reculer
		images[I_JOUEURS+1]=tk.getImage("./images/joueur/skelgauche.gif");
		//Monter
		images[I_JOUEURS+2]=tk.getImage("./images/joueur/skelhaut.gif");			
		//Descendre
		images[I_JOUEURS+3]=tk.getImage("./images/joueur/skelbas.gif");
		
		//joueur rouge
		for (int i=0; i<4; i++) {
			//4 images par animation
    			//animation Avancer
			images[I_JOUEURS+4+i]=tk.getImage("./images/joueur/joueur_rouge/rdroite"+(i+1)+".gif");
			//animation Reculer
			images[I_JOUEURS+4+4+i]=tk.getImage("./images/joueur/joueur_rouge/rgauche"+(i+1)+".gif");
			//animation Monter
			images[I_JOUEURS+4+2*4+i]=tk.getImage("./images/joueur/joueur_rouge/rhaut"+(i+1)+".gif");			
			//animation Descendre
			images[I_JOUEURS+4+3*4+i]=tk.getImage("./images/joueur/joueur_rouge/rbas"+(i+1)+".gif");
		}//for
		
		//joueur bleu
		for (int i=0; i<4; i++) {
			//4 images par animation
    			//animation Avancer
			images[I_JOUEURS+4+16+i]=tk.getImage("./images/joueur/joueur_bleu/bdroite"+(i+1)+".gif");
			//animation Reculer
			images[I_JOUEURS+4+16+4+i]=tk.getImage("./images/joueur/joueur_bleu/bgauche"+(i+1)+".gif");
			//animation Monter
			images[I_JOUEURS+4+16+2*4+i]=tk.getImage("./images/joueur/joueur_bleu/bhaut"+(i+1)+".gif");			
			//animation Descendre
			images[I_JOUEURS+4+16+3*4+i]=tk.getImage("./images/joueur/joueur_bleu/bbas"+(i+1)+".gif");
		}//for
		
		//infos d'ordre général 400-449
		images[I_INFORMATION+0]=tk.getImage("./images/information/information.gif");
		images[I_INFORMATION+1]=tk.getImage("./images/information/led_off.gif");
		for(int i=0; i<10; i++) {
			images[I_INFORMATION+2+i]=tk.getImage("./images/information/"+i+".gif");
		}//for
		images[I_INFORMATION+12]=tk.getImage("./images/information/mini_bombe.gif");
		
		//divers 450-499
		//image de présentation
		images[I_PRESENTATION+0]=tk.getImage("./images/presentation/presentation.gif");
		
		//image du milieu
		for (int i=0; i<13; i++) {
			images[I_MILIEU+i]=tk.getImage("./images/milieu/milieu"+i+".gif");
		}
		
		//images de fin
		images[I_FIN+0]=tk.getImage("./images/fin/rougegagnant.gif");
		images[I_FIN+1]=tk.getImage("./images/fin/bleugagnant.gif");
		
		
		tracker=new MediaTracker(this);
		for (int i=0; i<NB_IMAGES_TOTAL; i++) {
			if (images[i]!=null) {
				tracker.addImage(images[i], 0);
			}
		}//for
		
		//chargement des images
		try {
      			tracker.waitForID(0);
      			//System.out.println("Images de la zone de jeu chargées");
      		} catch  (Exception e) {
    			System.out.println("Erreur lors chargement des images: init() ZoneJeu");
        		e.printStackTrace();
        	}//catch
	}//initImages()

	
	/*------------*/
	/* NON STATIC */
	/*------------*/
	
	/** Constructeur vide */
	public ZoneJeu() {
	}//ZoneJeu



        //------------------------------------------------------------
	//			METHODES PUBLIQUES
	//------------------------------------------------------------

	/*------------*/
	/* NON STATIC */
	/*------------*/

	/** Méthode qui doit être redéfinie dans la sous classe spécialisée si nécessaire */
	public void affichage(String affichage) {
		//à définir
	}//affichage()

	/** Méthode qui doit être redéfinie dans la sous classe spécialisée si nécessaire */
	public void texte(String texte) {
		//à définir
	}//texte()

	/** Libère les ressources occupées par les images */
	protected void flush() {
		for (int i=0; i<NB_IMAGES_TOTAL; i++) {
			if (images[i]!=null) {
				images[i].flush();
				images[i]=null;
			}
		}//for
	}//flush()
	
};//classe ZoneJeu