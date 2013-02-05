package base;

/**
  	BOMBERMAN<br>
  	classe: Sprite.java<br>
  	Les �l�ments Bloc, Joueur et Bombe �tendent cette classe dans le but
  	de simplifier la manipulation de propri�t�s de base.
  	@author MAC WING Michel<br>
  	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
  	@version 1.0 du 01/01/2000
  */

public abstract class Sprite {

	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------
		
	/** position de l'�l�ment en pixels */
	protected int x;
	/** position de l'�l�ment en pixels */
	protected int y;

	/** v�locit� de l'�l�ment en pixels */
	protected int vel_x;
	/** v�locit� de l'�l�ment en pixels */
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
		/* M�thodes pour le mouvement */
		/*----------------------------*/
	
	/** translation avec param�tres valeurs de la translation */
	public void translate(int trans_x, int trans_y) {
		x += trans_x;
		y += trans_y;
	}

	/** translation suivant les valeurs de la v�locit� #surcharge */
	public void translate() {
		x += vel_x;
		y += vel_y;
	}
	
	/** d�placement � une position donn�e */
	public void moveXY(int x, int y) {
		this.x = x;
		this.y = y;
	}


		/*----------------------------------------*/
		/*  M�thodes d'acc�s et de modifications  */
		/*----------------------------------------*/

	/** donne position (haut gauche du rectangle) de l'�l�ment */
	public int getX() { return x; }
	/** donne position (haut gauche du rectangle) de l'�l�ment */
	public int getY() { return y; }
	
	/** donne v�locit� */
	public int getVelocityX() { return vel_x; }
	/** donne v�locit� */
	public int getVelocityY() { return vel_y; }

	/** modification v�locit� */
	public void setVelocityX(int vel_x) { this.vel_x = vel_x; }
	/** modification v�locit� */
	public void setVelocityY(int vel_y) { this.vel_y = vel_y; }

	/** modification v�locit� #surcharge */
	public void setVelocity(int vel_x, int vel_y) {
		this.vel_x = vel_x;
		this.vel_y = vel_y;
	}



};//classe Sprite