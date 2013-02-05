package general;

import java.awt.*;
import base.*;
import general.*;
import niveau.Niveau;
import element.Joueur;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Information.java<br>
 	Affichage d'informations d'ordre général: temps, victoires, vies, status<br>
 	Cette classe devra être modifiée légèrement si on augmente le nombre
 	de joueurs car contrairement aux autres, elle n'est pas "autoévolutive":
 	2 joueurs uniquement; raison invoquée: difficile de faire autrement
  	due au positionnement des informations dans la zone de jeu.<br>
 	Classe appelée par différents processus et objets, d'où son aspect "general"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public final class Information implements Constantes {

	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/
	
		/*--------*/
		/* PUBLIC */
		/*--------*/
	
	/** nombre de leds total */
  	public static final int NB_LEDS=52;
		
	/** dimensions de la surface nécessaire pour afficher les infos */
	public static final int LARGEUR=832, HAUTEUR=90;
	
	/** position du texte */
	public static final int TEXTE_X=265;
	/** position du texte */
	public static final int TEXTE_Y=ZoneJeu.ZONEJEU_HAUTEUR-35;
	
	/** position du texte final de victoire */
	public static final int TEXTEFINAL_X=375;
	/** position du texte final de victoire */
	public static final int TEXTEFINAL_Y=365;
	
	/** dimension des fontes */
	public static final int FONTE_SIZE=16;
			
	/** nombre de vies des joueurs */
	public static int[] tabl_vies;
	
	/** nombre de victoires des joueurs */
	public static int[] tabl_victoires;
	
	/** booléen pour savoir si fin de niveau */
	public static boolean fin_niveau;
	
	/** booléen pour savoir si fin de partie */
	public static boolean fin_partie;
	
	/** identifiant pour reconnaitre le gagnant de la partie */
	public static int id_gagnant;
	
	/** alternateur de phrases (voir paintTexteFinal()) */
	public static int alternateur;
	
		/*---------*/
		/* PRIVATE */
		/*---------*/

	//la zone de jeu
	private static ZoneJeu zonejeu;

	//temps courant en leds allumées
	private static int temps;

	//dimensions led d'affichage du temps
	private static final int LED_COTE=16;



	//------------------------------------------------------------
	//		CONSTRUCTEUR / INITIALISATIONS
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/

	/** Initialisation d'ordre général */
	public static void init(ZoneJeu zone) {
		zonejeu=zone;
		//2: à modifier pour augmenter le nombre de joueurs
		tabl_vies=new int[2];
		tabl_victoires=new int[2];
		fin_niveau=false;
		fin_partie=false;
	}//init()

	/** Initialise les informations selon les préférences */
	public static void initInformation() {
		temps=NB_LEDS;
		fin_niveau=false;
		tabl_vies[0]=tabl_vies[1]=Preference.nb_vies;
		alternateur=0;
	}//initInfos
	
	
	
        //------------------------------------------------------------
	//			METHODES PUBLIQUES
	//------------------------------------------------------------
	
	/*--------*/
	/* STATIC */
	/*--------*/

	/** Dessine les différentes informations images */
   	public static void paintTout(Graphics g) {
   		int numero_image=zonejeu.I_INFORMATION;
    		g.drawImage(zonejeu.images[numero_image], 0, PLATEAU_HAUTEUR, zonejeu);
    		zonejeu.affichage(numero_image+"#"+"0"+"#"+PLATEAU_HAUTEUR+"#");
    		
    		numero_image=zonejeu.I_INFORMATION+1;
    		int x, y;
    		y=ZoneJeu.ZONEJEU_HAUTEUR-LED_COTE;
    		for (int i=NB_LEDS; i>=temps; i--) {
    			x=i*LED_COTE;
    			g.drawImage(zonejeu.images[numero_image], i*LED_COTE, y, zonejeu);
    			zonejeu.affichage(numero_image+"#"+x+"#"+y+"#");
    		}
    		paintVies(g);
    		paintVictoires(g);
    	}//paintTout()
    	
    	/** Dessine le timer (leds) */
    	public static void paintTimer(Graphics g) {
		temps--;
		if (temps==0) fin_niveau=true;
		int numero_image=zonejeu.I_INFORMATION+1;
		int x=temps*LED_COTE;
		int y=ZoneJeu.ZONEJEU_HAUTEUR-LED_COTE;
		g.drawImage(zonejeu.images[numero_image], x, y, zonejeu);
		zonejeu.affichage(numero_image+"#"+x+"#"+y+"#");
	}//paintTimer();

	/** Dessine le nombre de vies de chaque joueur */
	public static void paintVies(Graphics g) {
		int x, y;
		int numero_image;
		y=ZoneJeu.ZONEJEU_HAUTEUR-50;
		for (int id=0; id<2; id++) {
			if (Preference.couleurs[id]==Preference.ROUGE) {
				x=80;
			}
			else {
				x=LARGEUR-80-24;
			}//if
			numero_image=ZoneJeu.I_INFORMATION+2+tabl_vies[id];
			g.drawImage(zonejeu.images[numero_image], x, y, zonejeu);
			zonejeu.affichage(numero_image+"#"+x+"#"+y+"#");
		}//for
	}//paintVies()
	
	/** Dessine le nombre de victoires de chaque joueur */
	public static void paintVictoires(Graphics g) {
		int x, y;
		int numero_image;
		int ecart;
		y=ZoneJeu.ZONEJEU_HAUTEUR-85;
		numero_image=ZoneJeu.I_INFORMATION+12;
		int x_ini;
		for (int id=0; id<2; id++) {
			if (Preference.couleurs[id]==Preference.ROUGE) {
				x_ini=0;
				ecart=32;
			}
			else {
				x_ini=800;
				ecart=-32;
			}//if
			for (int j=0; j<tabl_victoires[id]; j++) {
				x=x_ini+ecart*j;
				g.drawImage(zonejeu.images[numero_image], x, y, zonejeu);
				zonejeu.affichage(numero_image+"#"+x+"#"+y+"#");
			}//for
		}//for
	}//paintVictoires()
	
	/** Mise à jour du nombre de vie d'un joueur
	    nb_vie<0 si on veut retrancher, >0 sinon
	*/
	public static void addVies(Graphics g, Joueur joueur, int nb_vies) {
		boolean etat=false;
		int id=joueur.identifiant;
		tabl_vies[id]+=nb_vies;
		if (tabl_vies[id]>9) tabl_vies[id]=9;
		if (tabl_vies[id]<=0) {
			tabl_vies[id]=0;
			fin_niveau=true;
			etat=true;
		}//if
		paintVies(g);
		//le joueur est renseigné sur son état (mort ou vivant)
		joueur.setMort(etat);
	}//addVies()
	
	/** Dessine l'information texte */
	public static void paintTexte(Graphics g) {
		String info;
		int x=TEXTE_X;
		int y=TEXTE_Y;
		g.setFont(new Font("Serif", Font.BOLD, FONTE_SIZE));
		//on arrive à la fin du niveau
		if (fin_niveau && !fin_partie) {
			if (tabl_vies[0]==0 && tabl_vies[1]==0)
				info="Manche Nulle";
			else if (temps==0) info="Temps Ecoulé";
			else {
				if (tabl_vies[0]==0) {
					tabl_victoires[1]++;
					id_gagnant=1;
					info=Preference.noms[1]+" gagne la manche: "+tabl_victoires[1]+"/"+tabl_victoires[0];
				}
				else {
					tabl_victoires[0]++;
					id_gagnant=0;
					info=Preference.noms[0]+" gagne la manche: "+tabl_victoires[0]+"/"+tabl_victoires[1];
				}
				paintVictoires(g);
				if ( (tabl_victoires[0]==Preference.nb_victoires)
				|| (tabl_victoires[1]==Preference.nb_victoires) ) {
					info=info.concat(" et la partie");
					fin_partie=true;
				}
			}//else
			g.setColor(Color.green);
			g.drawString(info, x, y);
			zonejeu.texte("1#"+info+"#"+x+"#"+y+"#C");
		}//if fin_niveau
		//sinon on affiche le titre du niveau
		else {
			//au cas où il y aurait pas de titre
			if (Niveau.titre==null) return;
			g.setColor(Color.yellow);
			g.drawString(Niveau.titre, x, y);
			zonejeu.texte("1#"+Niveau.titre+"#"+x+"#"+y+"#D");
		}
	}//paintTexte()
	
	/** On dessine le joueur victorieux */
	public static void paintVictoireFinale(Graphics g) {
		if (fin_partie) {
			int numero_image;
			int x=CASE_LARGEUR;
			int y=CASE_HAUTEUR;
			if (Preference.couleurs[id_gagnant]==Preference.ROUGE) {
				numero_image=ZoneJeu.I_FIN;
				g.drawImage(zonejeu.images[numero_image], x, y, zonejeu);
				zonejeu.affichage(numero_image+"#"+x+"#"+y+"#");
			}
			if (Preference.couleurs[id_gagnant]==Preference.BLEU) {
				numero_image=ZoneJeu.I_FIN+1;
				g.drawImage(zonejeu.images[numero_image], x, y, zonejeu);
				zonejeu.affichage(numero_image+"#"+x+"#"+y+"#");
			}
		}
	}//paintVictoireFinale()

	/** Texte final de congratulations du vainqueur */
	public static void paintTexteFinal(Graphics g) {
		if (fin_partie) {
			g.setFont(new Font("Serif", Font.BOLD, FONTE_SIZE));
			int x=TEXTE_X;
			int y=TEXTE_Y;
			if (alternateur==0) {
				String info="Congratulations "+Preference.noms[id_gagnant];
				g.setColor(Color.orange);
				g.drawString(info, x, y);
				zonejeu.texte("1#"+info+"#"+x+"#"+y+"#F");
			}
			else {
				String info="Fermer la fenêtre pour quitter la partie";
				g.setColor(Color.magenta);
				g.drawString(info, x, y);
				zonejeu.texte("1#"+info+"#"+x+"#"+y+"#E");
			}
			alternateur=(alternateur+1)%2;
			x=TEXTEFINAL_X;
			y=TEXTEFINAL_Y;
			String victoire=Preference.noms[id_gagnant];
			g.setColor(Color.yellow);
			g.drawString(victoire, x, y);
			zonejeu.texte("1#"+victoire+"#"+x+"#"+y+"#D");
		}
	}//paintTexteFinal()



};//classe Information
