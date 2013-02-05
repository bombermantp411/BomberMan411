package zone.zonejeu;

import java.awt.*;
import java.awt.event.*;
import base.*;
import zone.ZoneJeu;
import element.*;
import general.Information;
import general.Preference;
import niveau.Niveau;
import Bomberman;


/**
 	BOMBERMAN<br>
 	classe: ZoneJeu_Serveur.java<br>
 	La zone de jeu côté serveur
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class ZoneJeu_Serveur extends ZoneJeu implements Constantes, Runnable, KeyListener {

	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/
	
		/*--------*/
		/* PUBLIC */
		/*--------*/

	/** délai avant laquelle le temps relatif (leds d'"Information") diminue d'une unité */
	public static final int DELAI=40;

		/*---------*/
		/* PRIVATE */
		/*---------*/
		
	//le joueur courant (serveur) est à l'indice 0 du tableau des joueurs
	private static final int JOUEUR_COURANT=0;

	//ordres éxécutés par un joueur client
	private static final int AVANCER=0, RECULER=1, MONTER=2, DESCENDRE=3;
	private static final int STOP_AVANCER=4, STOP_RECULER=5, STOP_MONTER=6, STOP_DESCENDRE=7;
	private static final int SPACE=8, ENTER=9, PAUSE=10;


	/*------------*/
	/* NON STATIC */
	/*------------*/
	
		/*--------*/
		/* PUBLIC */
		/*--------*/
	
	/** les données de l'affichage qui sont envoyés aux clients */
	public String affichage;
	
	/** booleen pour savoir si le client est prêt */
	public boolean client_ok;
	

		/*---------*/
		/* PRIVATE */
		/*---------*/

	//propriétaire du panel
	private Bomberman parent;

	//spécifie si jeu en pause
	private boolean pause;

	//taux de rafraichissement de la zone de jeu
	private int taux;
	
	//délai courant
	private int delai;

	//image et contexte graphique hors ecran: double buffering pour l'affichage
	private Image workspace;
	private Graphics offscreen;
	
	//contexte graphique de la fenêtre
	private Graphics cg;



	//------------------------------------------------------------
	//			CONSTRUCTEUR
	//------------------------------------------------------------
	
	/*------------*/
	/* NON STATIC */
	/*------------*/
	
	/** Construction de la zone jeu côté serveur */
	public ZoneJeu_Serveur(Bomberman parent) {
		Information.init(this);
		this.affichage="0#";
		this.parent=parent;
		this.taux=1000/Preference.fps;
		this.delai=DELAI;
		//dimensions de la zone de jeu
		setSize(new Dimension(ZONEJEU_LARGEUR, ZONEJEU_HAUTEUR));
		addKeyListener(this);
		setPause(true);
	}//ZoneJeu_Serveur()



        //------------------------------------------------------------
	//			METHODES PUBLIQUES
	//------------------------------------------------------------

	/*------------*/
	/* NON STATIC */
	/*------------*/
	
	/** Traite message délivré par le parent provenant du
	    client qui donne un ordre à un joueur
	 */
	public void message(String message, int indice_dep) {
		
	 	//détermine le joueur à recevoir l'ordre
	 	int fin=message.lastIndexOf("#");
	 	String str=message.substring(fin+1);
	 	int num_joueur=(new Integer(str)).intValue();
	 	
	 	//détermine l'ordre
	 	str=message.substring(indice_dep+1, fin);
	 	int ordre=(new Integer(str)).intValue();
	 	
	 	if (pause) {
			if ( (ordre==PAUSE) || (ordre==SPACE) ) {
				setPause(false);
				if (!Information.fin_niveau) Information.paintTexte(offscreen);
			}
			return;
		}
		else if (ordre==PAUSE) {
			setPause(true);
			offscreen.setColor(Color.magenta);
			String nom=Preference.noms[num_joueur]+" fait une pause";
			int x=Information.TEXTE_X;
			int y=Information.TEXTE_Y;
			offscreen.drawString(nom, x, y);
			texte("1#"+nom+"#"+x+"#"+y+"#E");
			repaint();
			return;
		}//pause
		
	 	//si le niveau est terminé, on ignore les ordres suivants
		if (Information.fin_niveau) return;
	 		 	
	 	switch(ordre) {
	 		
	 		case AVANCER:
	 			Joueur.tabl_joueurs[num_joueur].avancer();
	 			break;
	 		case RECULER:
	 			Joueur.tabl_joueurs[num_joueur].reculer();
	 			break;
	 		case MONTER:
	 			Joueur.tabl_joueurs[num_joueur].monter();
	 			break;
	 		case DESCENDRE:
	 			Joueur.tabl_joueurs[num_joueur].descendre();
	 			break;
			case STOP_AVANCER:
	 			Joueur.tabl_joueurs[num_joueur].setAvancer(false);
	 			break;
	 		case STOP_RECULER:
	 			Joueur.tabl_joueurs[num_joueur].setReculer(false);
	 			break;
	 		case STOP_MONTER:
	 			Joueur.tabl_joueurs[num_joueur].setMonter(false);
	 			break;
	 		case STOP_DESCENDRE:
	 			Joueur.tabl_joueurs[num_joueur].setDescendre(false);
	 			break;
	 		case SPACE:
	 			Joueur.tabl_joueurs[num_joueur].poserBombe();
	 			break;
	 		case ENTER:
	 			Joueur.tabl_joueurs[num_joueur].exploserBombes();
	 			break;
	 		default:
	 			System.out.println("Erreur, ceci ne doit pas se produire: message ZoneJeu_Serveur");
	 	}//switch
	}//message()
	
	/** Récupère les données sur l'affichage à envoyer au client */
	public void affichage(String affichage) {
		this.affichage=this.affichage.concat(affichage);
	}//affichage()

	/** Récupère les données texte et envoi au client */
	public void texte(String texte) {
		parent.connexion.serveur.envoyer(texte);
	}//texte()
	
	/** A pour conséquence de figer le jeu si vrai */
	public void setPause(boolean pause) {
		this.pause=pause;
		if (client_ok) {
			Information.paintTout(offscreen);
	         	affichage("A");
			parent.connexion.serveur.envoyer(affichage);
			affichage="0#";
		}
	}//setPause()
	
		/*-------------------------------------*/
		/* Procédure d'éxécution du processus */
		/*-------------------------------------*/

	/** Le processus ... */
  	public void run() {
  		//initialisation des niveaux
  		Niveau.initLevels();
  		
  		//temporise jusqu'à ce que le client soit prêt
  		while(!client_ok || pause) {
  			//System.out.println("Pret ?="+client_ok);
  			try {
            			Thread.sleep(500);
            		} catch (InterruptedException a) {}
  		}//while
  		
  		//tant que la partie n'est pas terminée
  		while(!Information.fin_partie) {
  			//initialisation des éléments
  			Bloc.initStatic(this);
  			Bombe.initStatic(this);
			Flamme.initStatic(this);
			Joueur.initStatic(this);
			//le prochain niveau
			Niveau.next();
			Information.initInformation();
			while(pause) {
				try {
            			Thread.sleep(500);
            			} catch (InterruptedException b) {}
            		}//while
            		
            		//affichage des informations
			Information.paintTout(offscreen);
			affichage("A");
			parent.connexion.serveur.envoyer(affichage);
			affichage="0#";
			
			//fermeture du plateau de jeu
			for(int i=0; i<=NB_CASES_LIGNE/2; i++) {
				int numero_image;
				int x, y;
				
				numero_image=I_MILIEU+i;
				x=i*CASE_LARGEUR;
				y=0;
				offscreen.drawImage(images[numero_image], x, y, this);
				affichage(numero_image+"#"+x+"#"+y+"#");
				
				numero_image=I_MILIEU+NB_CASES_LIGNE-(i+1);
				x=NB_CASES_LIGNE*CASE_LARGEUR-(i+1)*CASE_LARGEUR;
				offscreen.drawImage(images[numero_image], x, y, this);
				affichage(numero_image+"#"+x+"#"+y+"#");
				
				affichage("B");
				parent.connexion.serveur.envoyer(affichage);
				affichage="0#";
				
				repaint();
				try {
            			Thread.sleep(150);
            			} catch (InterruptedException b) {}
			}//for
			
			//affiche le plateau de jeu progressivement
			//réduit également les risques de flood du réseau
			for(int i=0; i<=NB_CASES_LIGNE/2; i++) {
				Bloc.paintColonne(offscreen, NB_CASES_LIGNE/2+i);
				Bloc.paintColonne(offscreen, NB_CASES_LIGNE/2-i);
				affichage("B");
				parent.connexion.serveur.envoyer(affichage);
				affichage="0#";
				repaint();
				try {
            			Thread.sleep(100);	
            			} catch (InterruptedException c) {}
			}//for
			Information.paintTexte(offscreen);
			
			//tant que le niveau n'est pas terminé
	  		while (!Information.fin_niveau) {
	  			delai--;
	  			if (delai<=0) {
	  				delai=DELAI;
	  				paintJustWithInfo();
	  			}
				else paintJustWithoutInfo();
	            		try {
	            			Thread.sleep(taux);
	            		} catch (InterruptedException d) {}
	            		this.getToolkit().sync();
	         	}//while fin_niveau
	         	setPause(true);
			Information.paintTexte(offscreen);
	         	repaint();
         	}//while fin_partie
         	
         	//temporise
         	try {
	        	Thread.sleep(3000);
	        } catch (InterruptedException e) {}
         	setPause(true);
         	
         	//dessine la victoire finale
         	Bloc.paintLigne(offscreen, 0);
         	Information.paintVictoireFinale(offscreen);
         	affichage("B");
		parent.connexion.serveur.envoyer(affichage);
		affichage="0#";
         	
         	//tant que l'on ne ferme pas la fenêtre
         	while (true) {
         		Information.paintTout(offscreen);
         		affichage("A");
			parent.connexion.serveur.envoyer(affichage);
			affichage="0#";
			Information.paintTexteFinal(offscreen);
         		repaint();
         		try {
	        		Thread.sleep(1000);
	        	} catch (InterruptedException f) {}
       		}//while
	}//run()
	
	/** Ferme la zone de jeu proprement */
	public void fermer() {
		flush();
		removeKeyListener(this);
	}//fermer()

		/*------------------------*/
		/* Gestion des évènements */
		/*------------------------*/

	/** Touche enfoncée */			
	public void keyPressed(KeyEvent evt) {
		
		if (pause) {
			if ((evt.getKeyCode() == KeyEvent.VK_P) 
			|| (evt.getKeyCode() == KeyEvent.VK_SPACE)) {
				setPause(false);
				if (!Information.fin_niveau) Information.paintTexte(offscreen);
			}
			return;
		}
		else if (evt.getKeyCode() == KeyEvent.VK_P) {
			setPause(true);
			offscreen.setColor(Color.magenta);
			String nom=Preference.noms[JOUEUR_COURANT]+" fait une pause";
			int x=Information.TEXTE_X;
			int y=Information.TEXTE_Y;
			offscreen.drawString(nom, x, y);
			texte("1#"+nom+"#"+x+"#"+y+"#E");
			repaint();
			return;
		}//pause
		
		//si le niveau est terminé, on ignore les évènements suivants
		if (Information.fin_niveau || (Joueur.tabl_joueurs==null)) return;
		
		if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
			Joueur.tabl_joueurs[JOUEUR_COURANT].avancer();
		}//VK_RIGHT
		
		if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
			Joueur.tabl_joueurs[JOUEUR_COURANT].reculer();
		}//VK_LEFT
		
		if (evt.getKeyCode() == KeyEvent.VK_UP) {
			Joueur.tabl_joueurs[JOUEUR_COURANT].monter();
		}//VK_UP
		
		if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
			Joueur.tabl_joueurs[JOUEUR_COURANT].descendre();
		}//VK_DOWN
		
		if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
			Joueur.tabl_joueurs[JOUEUR_COURANT].poserBombe();
		}//VK_SPACE
		
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			Joueur.tabl_joueurs[JOUEUR_COURANT].exploserBombes();
		}//VK_ENTER
	}//keyPressed()
	
	/** Touche relevée */
	public void keyReleased(KeyEvent evt) {
		//si le niveau est terminé, on ignore les évènements suivants
		if (Information.fin_niveau || (Joueur.tabl_joueurs==null)) return;
		
		if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
			Joueur.tabl_joueurs[JOUEUR_COURANT].setAvancer(false);
		}//VK_RIGHT
		
		if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
			Joueur.tabl_joueurs[JOUEUR_COURANT].setReculer(false);
		}//VK_LEFT
		
		if (evt.getKeyCode() == KeyEvent.VK_UP) {
			Joueur.tabl_joueurs[JOUEUR_COURANT].setMonter(false);
		}//VK_UP
		
		if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
			Joueur.tabl_joueurs[JOUEUR_COURANT].setDescendre(false);
		}//VK_DOWN
	}//keyReleased()

	public void keyTyped(KeyEvent evt) {
	}//keyTyped()
	
		/*------------------------*/
		/* Gestion de l'affichage */
		/*------------------------*/
		
	/** Dessine uniquement les zones modifiées avec les infos */
	public void paintJustWithInfo() {
		cg=getGraphics();
		if ((offscreen == null) || (cg == null)) return;
		
		if (pause) {
			cg.drawImage(workspace, 0, 0, this);
			return;
		}
		Bloc.paintBlocsTimer(offscreen);
		Bombe.paintOll(offscreen);
		Flamme.paintOll(offscreen);
		Joueur.paintOll(offscreen);
		Information.paintTimer(offscreen);
		
		//envoi ce qui doit être affiché aux clients
		//spécifie affichage de type A: with info
		affichage("A");
		parent.connexion.serveur.envoyer(affichage);
		affichage="0#";
		
		//dessine l'image hors écran
		cg.setClip(0, 0, ZONEJEU_LARGEUR, ZONEJEU_HAUTEUR);
		cg.drawImage(workspace, 0, 0, this);
		cg.setClip(CASE_LARGEUR, 0, PLATEAU_LARGEUR-CASE_LARGEUR, PLATEAU_HAUTEUR-CASE_HAUTEUR);
	}//paintJustWithInfo();

	/** Dessine uniquement les zones modifiées sans les infos */
	public void paintJustWithoutInfo() {
		cg=getGraphics();
		if ((offscreen == null) || (cg == null)) return;
		
		if (pause) {
			cg.drawImage(workspace, 0, 0, this);
			return;
		}
		Bloc.paintBlocsTimer(offscreen);
		Bombe.paintOll(offscreen);
		Flamme.paintOll(offscreen);
		Joueur.paintOll(offscreen);
		
		//envoi ce qui doit être affiché aux clients
		//spécifie affichage de type B: without info
		affichage("B");
		parent.connexion.serveur.envoyer(affichage);
		affichage="0#";
		
		//dessine l'image hors écran
		cg.drawImage(workspace, 0, 0, this);
	}//paintJustWithoutInfo();

	/** Optimisation de l'affichage: empêche l'effacement avant de repeindre */
	public void update(Graphics g) {
		paint(g);
	}//update()
	
	/** Dessine entièrement la zone de jeu */
	public void paint(Graphics g) {
		//création de l'image et capture du contexte graphique hors écran lors du 1er passage
		if (workspace==null) {
			//System.out.println("Infos: création de l'image et capture du contexte graphique hors écran");
			workspace=createImage(ZONEJEU_LARGEUR, ZONEJEU_HAUTEUR);
			offscreen=workspace.getGraphics();
			offscreen.setFont(new Font("Serif", Font.BOLD, Information.FONTE_SIZE));
			offscreen.drawImage(images[ZoneJeu.I_PRESENTATION],0 ,0, ZONEJEU_LARGEUR, ZONEJEU_HAUTEUR, this);
		}//if
				
		//dessine l'image hors écran
		g.setClip(0, 0, ZONEJEU_LARGEUR, ZONEJEU_HAUTEUR);
		g.drawImage(workspace, 0, 0, this);
		g.setClip(CASE_LARGEUR, 0, PLATEAU_LARGEUR-CASE_LARGEUR, PLATEAU_HAUTEUR-CASE_HAUTEUR);
   	}//paint()



};//classe ZoneJeu