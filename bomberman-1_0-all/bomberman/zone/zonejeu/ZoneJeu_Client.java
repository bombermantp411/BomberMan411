package zone.zonejeu;

import java.awt.*;
import java.awt.event.*;
import base.*;
import zone.ZoneJeu;
import element.*;
import general.Information;
import Bomberman;

/**
 	BOMBERMAN<br>
 	classe: ZoneJeu_Client.java<br>
 	La zone de jeu du client: se charge uniquement de dessiner la zone de jeu
 	correspondant à celui du serveur et d'envoyer les évènements claviers au serveur
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class ZoneJeu_Client extends ZoneJeu implements Constantes, KeyListener {

	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------
	
	/*------------*/
	/* NON STATIC */
	/*------------*/

		/*---------*/
		/* PRIVATE */
		/*---------*/

	//propriétaire du panel
	private Bomberman parent;

	//image et contexte graphique hors ecran: double buffering pour l'affichage
	private Image workspace;
	private Graphics offscreen, cg;


	
	//------------------------------------------------------------
	//			CONSTRUCTEUR
	//------------------------------------------------------------

	/*------------*/
	/* NON STATIC */
	/*------------*/

	/** Construction de la zone de jeu côté client */	
	public ZoneJeu_Client(Bomberman parent) {
		this.parent=parent;
		//dimensions de la zone de jeu
		setSize(new Dimension(ZONEJEU_LARGEUR, ZONEJEU_HAUTEUR));
		addKeyListener(this);
	}//ZoneJeu_Client()



        //------------------------------------------------------------
	//			METHODES PUBLIQUES
	//------------------------------------------------------------
	
	/*------------*/
	/* NON STATIC */
	/*------------*/
	
	/** Traite le message concernant l'affichage des images à réaliser 
	    provenant du serveur
	*/
	public void message(String message, int indice_dep) {
		
		if ((offscreen == null) || (cg == null)) {
			cg=getGraphics();
			return;
		}
		
		//décodage du message:
		
		//détermination du type d'affichage
		int fin=message.lastIndexOf("#");
	 	String type=message.substring(fin+1);
	 	
	 	int debut;
	 	fin=indice_dep;
	 	String str;
	 	int num_image, x, y;
	 	while (fin<(message.length()-2)) {
	 		//numero d'image
			debut=fin;
			fin=message.indexOf("#", fin+1);
			str=message.substring(debut+1,fin);
			num_image=(new Integer(str)).intValue();
			
			//position x
		 	debut=fin;
		 	fin=message.indexOf("#", fin+1);
		 	str=message.substring(debut+1,fin);
		 	x=(new Integer(str)).intValue();
		 	
		 	//position y
		 	debut=fin;
		 	fin=message.indexOf("#", fin+1);
		 	str=message.substring(debut+1,fin);
		 	y=(new Integer(str)).intValue();
		 	
		 	offscreen.drawImage(images[num_image], x, y, this);
		}//while
			//type d'affichage A
			if (type.compareTo("A")==0) {
				cg.drawImage(workspace, 0, 0, this);
			}
			//type B
			else {
				cg.setClip(0, 0, ZONEJEU_LARGEUR, ZONEJEU_HAUTEUR);
				cg.drawImage(workspace, 0, 0, this);
				cg.setClip(CASE_LARGEUR, 0, PLATEAU_LARGEUR-CASE_LARGEUR, PLATEAU_HAUTEUR-CASE_HAUTEUR);
			}
	 }//message()
	 	 
	/** Traite le texte à afficher provenant du serveur */	 
	public void message_texte(String message, int indice_dep) {
		
		if ((offscreen == null) || (cg == null)) {
			cg=getGraphics();
			return;
		}
		
		//décodage du message:
		
		//détermination du type de texte à afficher
		int fin=message.lastIndexOf("#");
		String type=message.substring(fin+1);
		
		fin=message.indexOf("#", indice_dep+1);
		String texte=message.substring(indice_dep+1, fin);
		
		//position x
		int debut=fin;
		fin=message.indexOf("#", fin+1);
		String str=message.substring(debut+1,fin);
		int x=(new Integer(str)).intValue();
			
		//position y
		debut=fin;
		fin=message.indexOf("#", fin+1);
		str=message.substring(debut+1,fin);
		int y=(new Integer(str)).intValue();
		
		offscreen.setFont(new Font("Serif", Font.BOLD, Information.FONTE_SIZE));
		if (type.compareTo("C")==0) {
			offscreen.setColor(Color.green);
		}
		else if (type.compareTo("D")==0) {
			offscreen.setColor(Color.yellow);
		}
		else if (type.compareTo("E")==0) {
			offscreen.setColor(Color.magenta);
		}
		else if (type.compareTo("F")==0) {
			offscreen.setColor(Color.orange);
		}
		
		offscreen.drawString(texte, x, y);
		cg.setClip(0, 0, ZONEJEU_LARGEUR, ZONEJEU_HAUTEUR);
		cg.drawImage(workspace, 0, 0, this);
		cg.setClip(CASE_LARGEUR, 0, PLATEAU_LARGEUR-CASE_LARGEUR, PLATEAU_HAUTEUR-CASE_HAUTEUR);
	 }//message_texte()

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
		if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
			parent.connexion.client.envoyer("0#0");
		}//VK_RIGHT
		
		if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
			parent.connexion.client.envoyer("0#1");
		}//VK_LEFT
		
		if (evt.getKeyCode() == KeyEvent.VK_UP) {
			parent.connexion.client.envoyer("0#2");
		}//VK_UP
		
		if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
			parent.connexion.client.envoyer("0#3");
		}//VK_DOWN
		
		if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
			parent.connexion.client.envoyer("0#8");
		}//VK_SPACE
		
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			parent.connexion.client.envoyer("0#9");
		}//VK_ENTER
		
		if (evt.getKeyCode() == KeyEvent.VK_P) {
			parent.connexion.client.envoyer("0#10");
		}//VK_P		
	}//keyPressed()
	
	/** Touche relevée */
	public void keyReleased(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
			parent.connexion.client.envoyer("0#4");
		}//VK_RIGHT
		
		if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
			parent.connexion.client.envoyer("0#5");
		}//VK_LEFT
		
		if (evt.getKeyCode() == KeyEvent.VK_UP) {
			parent.connexion.client.envoyer("0#6");
		}//VK_UP
		
		if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
			parent.connexion.client.envoyer("0#7");
		}//VK_DOWN
	}//keyReleased()

	public void keyTyped(KeyEvent evt) {
	}//keyTyped()
	
		/*------------------------*/
		/* Gestion de l'affichage */
		/*------------------------*/

	/** Optimisation de l'affichage: empêche l'effacement avant de repeindre */
	public void update(Graphics g) {
		paint(g);
	}//update()
	
	/** Dessine la zone de jeu */
	public void paint(Graphics g) {
		
		//création de l'image et capture du contexte graphique hors écran lors du 1er passage
		if (workspace==null) {
			//System.out.println("Infos: création de l'image et capture du contexte graphique hors écran");
			workspace=createImage(ZONEJEU_LARGEUR, ZONEJEU_HAUTEUR);
			offscreen=workspace.getGraphics();
			offscreen.drawImage(images[ZoneJeu.I_PRESENTATION],0 ,0, ZONEJEU_LARGEUR, ZONEJEU_HAUTEUR, this);
			parent.connexion.client.envoyer("-2#");
		}//if
		//dessine l'image hors écran
		
		g.setClip(0, 0, ZONEJEU_LARGEUR, ZONEJEU_HAUTEUR);
		g.drawImage(workspace, 0, 0, this);
		g.setClip(CASE_LARGEUR, 0, PLATEAU_LARGEUR-CASE_LARGEUR, PLATEAU_HAUTEUR-CASE_HAUTEUR);
   	}//paint()



};//classe ZoneJeu_Client
