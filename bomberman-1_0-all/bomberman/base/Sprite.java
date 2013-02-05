package base;

/**
  	BOMBERMAN<br>
  	classe: Sprite.java<br>
  	Les éléments Bloc, Joueur et Bombe étendent cette classe dans le but
  	de simplifier la manipulation de propriétés de base.
  	@author MAC WING Michel<br>
  	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
  	@version 1.0 du 01/01/2000
  */

public abstract class Sprite {

	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------
		
	/** position de l'élément en pixels */
	protected int x;
	/** position de l'élément en pixels */
	protected int y;

	/** vélocité de l'élément en pixels */
	protected int vel_x;
	/** vélocité de l'élément en pixels */
	protected int vel_y;



	//------------------------------------------------------------
	//			CONSTRUCTEUR
	//------------------------------------------------------------
		
        protected Sprite() {
        }//Sprite()
        
        
        
        //------------------------------------------------------------
	//			METHODES PUBLIQUES
	//------------------------------------------------------------
               
		/*----------------------------*/
		/* Méthodes pour le mouvement */
		/*----------------------------*/
	
	/** translation avec paramètres valeurs de la translation */
	public void translate(int trans_x, int trans_y) {
		x += trans_x;
		y += trans_y;
	}

	/** translation suivant les valeurs de la vélocité #surcharge */
	public void translate() {
		x += vel_x;
		y += vel_y;
	}
	
	/** déplacement à une position donnée */
	public void moveXY(int x, int y) {
		this.x = x;
		this.y = y;
	}


		/*----------------------------------------*/
		/*  Méthodes d'accès et de modifications  */
		/*----------------------------------------*/

	/** donne position (haut gauche du rectangle) de l'élément */
	public int getX() { return x; }
	/** donne position (haut gauche du rectangle) de l'élément */
	public int getY() { return y; }
	
	/** donne vélocité */
	public int getVelocityX() { return vel_x; }
	/** donne vélocité */
	public int getVelocityY() { return vel_y; }

	/** modification vélocité */
	public void setVelocityX(int vel_x) { this.vel_x = vel_x; }
	/** modification vélocité */
	public void setVelocityY(int vel_y) { this.vel_y = vel_y; }

	/** modification vélocité #surcharge */
	public void setVelocity(int vel_x, int vel_y) {
		this.vel_x = vel_x;
		this.vel_y = vel_y;
	}



};//classe Sprite