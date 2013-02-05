package general;

import java.awt.*;
import java.awt.event.*;

/**
 	BOMBERMAN<br>
 	classe: APropos.java<br>
 	Fen�tre d'A Propos: titre du jeu, mon nom et pr�nom
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public final class APropos extends Frame {

	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/
	
		/*---------*/
		/* PRIVATE */
		/*---------*/

	//dimensions de la fen�tre	
	private static final int FENETRE_LARGEUR=300;
  	private static final int FENETRE_HAUTEUR=200;
  	
  	
  	/*------------*/
	/* NON STATIC */
	/*------------*/
	
		/*---------*/
		/* PRIVATE */
		/*---------*/
  		
	//image d'� propos
	private Image apropos;
	
	//mediatracker pour surveiller le chargement de l'image
	private MediaTracker tracker;


	
	//------------------------------------------------------------
	//		CONSTRUCTEUR / INITIALISATIONS
	//------------------------------------------------------------
	
	/*------------*/
	/* NON STATIC */
	/*------------*/
	
	/** Constructeur de la fen�tre */
	public APropos() {
		
		Image icone;
		icone=getToolkit().getImage("./images/icone/bombe_ico.gif");
		setIconImage(icone);
		
		initPosition();
		initConteneur();
		
		addWindowListener(new WindowAdapter() {
         		public void windowClosing(WindowEvent e) {
         			setVisible(false);
               		}
		});
		
		setSize(FENETRE_LARGEUR, FENETRE_HAUTEUR);
		setBackground(Color.black);
		setResizable(false);
		setTitle("A Propos");
    		setVisible(false);
	}//Preferences()
	
	
	
        //------------------------------------------------------------
	//			METHODES PUBLIQUES
	//------------------------------------------------------------

  	/*------------*/
	/* NON STATIC */
	/*------------*/
	
	/** Optimisation de l'affichage: emp�che l'effacement avant de repeindre */
	public void update(Graphics g) {
		paint(g);
	}//update()
	
	/** Dessine l'� propos */
	public void paint(Graphics g) {
		g.drawImage(apropos, 0, 20, FENETRE_LARGEUR, FENETRE_HAUTEUR, this);
   	}//paint()
	
	
	//------------------------------------------------------------
	//			METHODES PRIVEES
	//------------------------------------------------------------
	
	/** Positionnement de la fen�tre au milieu */
	private void initPosition() {
      		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
      		int xPos=(tailleEcran.width-FENETRE_LARGEUR)/2;
      		int yPos=(tailleEcran.height-FENETRE_HAUTEUR)/2;
      		setLocation(xPos, yPos);
      	}//initPosition()

	/** Cr�e l'int�rieur de la fen�tre */
	private void initConteneur() {
		//l'image
		apropos=getToolkit().getImage("./images/apropos/apropos.gif");
		tracker=new MediaTracker(this);
		tracker.addImage(apropos, 0);
		try{
      			tracker.waitForID(0);
      		} catch  (Exception e) {
    			System.out.println("Erreur lors chargement de l'image: initConteneur() APropos");
        		e.printStackTrace();
        	}
        }//initConteneur()



};//classe APropos