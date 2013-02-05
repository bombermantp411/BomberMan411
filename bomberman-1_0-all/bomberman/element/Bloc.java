package element;

import java.awt.*;
import base.*;
import element.*;
import general.Information;
import niveau.Niveau;
import zone.ZoneJeu;
import zone.zonejeu.ZoneJeu_Serveur;

/**
 	BOMBERMAN<br>
 	classe: Bloc.java<br>
 	Bloc: pave (indestructible), bonus ou brique (destructible ou non)<br>
 	Cette classe est �tendue: sp�cialisation de chaque bloc
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public abstract class Bloc extends Sprite implements Constantes {
	
	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/
	
		/*--------*/
		/* PUBLIC */
		/*--------*/
	
	/** un tableau pour obtenir la disposition des blocs affich�s */
	public static Bloc[][] tabl_blocs;
		
	/** d�lai (entre chaque tranche) pour faire apparaitre les blocs timers �ventuels
	    (blocs qui apparait en cours de jeu)
	 */
	public static final int DELAI=(Information.NB_LEDS*ZoneJeu_Serveur.DELAI)/Niveau.DIMENSION_BLOCSTIMER;

		/*---------*/
		/* PRIVATE */
		/*---------*/
	
	//la zone de jeu
	private static ZoneJeu zonejeu;

	//d�lai courant
	private static int delai;
	
	//tranche courante (blocs timer)
	private static int tranche;


	/*------------*/
	/* NON STATIC */
	/*------------*/
	
		/*---------*/
		/* PRIVATE */
		/*---------*/

	//position du bloc en cases
	private int case_x, case_y;

   	//num�ro de l'image du bloc
   	private int numero_image;

	//le bloc est un pav�
	private boolean est_pave;

   	//le bloc est un bonus
   	private boolean est_bonus;
   	   	
	//les blocs qui doivent prendre place lorsque le bloc courant est d�truit
	private Bloc[] autres_blocs;
	
	//le bloc qui doit prendre place lorsque le bloc courant est d�truit
	private Bloc autre_bloc;
	
        

	//------------------------------------------------------------
	//		CONSTRUCTEUR / INITIALISATIONS
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/

	/** Initialisation des attributs static */
	public static void initStatic(ZoneJeu zone) {
		zonejeu=zone;
		tabl_blocs=new Bloc[NB_CASES_LIGNE][NB_CASES_COLONNE];
		delai=DELAI;
		tranche=-1;
	}//initStatic()


	/*------------*/
	/* NON STATIC */
	/*------------*/

	/** Constructeur */
	public Bloc() {
	}//Bloc()

	/** La construction d'un bloc est r�alis� par appel aux m�thodes suivantes
	    de sp�cialisation
	 */
	/** Le bloc est un pav� */
	protected void initPave(int case_x, int case_y, int numero_image) {
		this.case_x=case_x;
		this.case_y=case_y;
		this.numero_image=numero_image;
		this.est_pave=true;
		//un pav� n'est pas un bonus
		this.est_bonus=false;
		//pas d'autres blocs
		this.autres_blocs=null;
		this.autre_bloc=null;
		//Sprite
		this.x=Utils.convertXcp(case_x);
		this.y=Utils.convertYcp(case_y);
		setVelocity(0, 0);
	}//initPave()
	
	/** Le bloc est une brique		
	    Le param�tre autres_blocs et autre_bloc peuvent �tre null ou non, si non null cela 
	    permet de faire apparaitre d'autres blocs lorsque le bloc courant est d�truit
	    (pour faire apparaitre un bonus par exemple). On peut ainsi 'chainer' plusieurs blocs.
	    Remarques: -la position des blocs en param�tre est ind�pendant de la position
	    du bloc courant
	    	       -une brique ou bonus destructible doit poss�der un pav� en fin de cha�ne
	    	       -2 constructeurs possibles pour simplifier la cr�ation des niveaux
	 */
	protected void initBrique(int case_x, int case_y, int numero_image, Bloc[] autres_blocs) {
		this.case_x=case_x;
		this.case_y=case_y;
		this.numero_image=numero_image;
		this.est_pave=false;
		this.est_bonus=false;
		this.autres_blocs=autres_blocs;
		this.autre_bloc=null;
		//Sprite
		this.x=Utils.convertXcp(case_x);
		this.y=Utils.convertYcp(case_y);
		setVelocity(0, 0);
	}//initBrique()
	
	/** Surcharge: un seul bloc � faire appara�tre */
	protected void initBrique(int case_x, int case_y, int numero_image, Bloc autre_bloc) {
		this.case_x=case_x;
		this.case_y=case_y;
		this.numero_image=numero_image;
		this.est_pave=false;
		this.est_bonus=false;
		this.autres_blocs=null;
		this.autre_bloc=autre_bloc;
		//Sprite
		this.x=Utils.convertXcp(case_x);
		this.y=Utils.convertYcp(case_y);
		setVelocity(0, 0);
	}//initBrique()
	
	/** Le bloc est un bonus */
	protected void initBonus(int case_x, int case_y, int numero_image, Bloc[] autres_blocs) {
		this.case_x=case_x;
		this.case_y=case_y;
		this.numero_image=numero_image;
		this.est_pave=false;
		this.est_bonus=true;
		this.autres_blocs=autres_blocs;
		this.autre_bloc=null;
		//Sprite
		this.x=Utils.convertXcp(case_x);
		this.y=Utils.convertYcp(case_y);
		setVelocity(0, 0);
	}//initBonus()
	
	/** Surcharge: un seul bloc � faire appara�tre */
	protected void initBonus(int case_x, int case_y, int numero_image, Bloc autre_bloc) {
		this.case_x=case_x;
		this.case_y=case_y;
		this.numero_image=numero_image;
		this.est_pave=false;
		this.est_bonus=true;
		this.autres_blocs=null;
		this.autre_bloc=autre_bloc;
		//Sprite
		this.x=Utils.convertXcp(case_x);
		this.y=Utils.convertYcp(case_y);
		setVelocity(0, 0);
	}//initBonus()



        //------------------------------------------------------------
	//			METHODES PUBLIQUES
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/

	/** Dessine tous les blocs */
	public static void paintOll(Graphics g) {
		for(int i=0; i<NB_CASES_LIGNE; i++) {
			for(int j=0; j<NB_CASES_COLONNE; j++) {
				paintBloc(g, j, i);
			}//for
		}//for
	}//paintOll()
	
	/** Dessine les blocs d'une colonne */
	public static void paintColonne(Graphics g, int colonne) {
		for(int i=0; i<NB_CASES_COLONNE; i++) {
			paintBloc(g, colonne, i);
		}//for
	}//paintColonne()
	
	/** Dessine les blocs d'une ligne */
	public static void paintLigne(Graphics g, int ligne) {
		for(int i=0; i<NB_CASES_LIGNE; i++) {
			paintBloc(g, i, ligne);
		}//for
	}//paintLigne()
	
	/** Dessine un bloc � une position donn�e */
	public static void paintBloc(Graphics g, int case_x, int case_y) {
		if (tabl_blocs[case_x][case_y]==null) return;
		else tabl_blocs[case_x][case_y].paint(g);
	}//paintBloc();

	/** Fait apparaitre les blocs timer �ventuels */
	public static void paintBlocsTimer(Graphics g) {
		if (delai<=1) {
			//le d�lai est pass�, on dessine la nouvelle tranche
			delai=DELAI;
			tranche++;
			//par s�curit�
			if (tranche>=Niveau.DIMENSION_BLOCSTIMER) return;
			//System.out.println("tranche="+tranche);
			if (Niveau.blocs_timer[tranche]==null) return;
			else {
				//System.out.println("Blocs Timer on");
				Bloc[] blocs=Niveau.blocs_timer[tranche];
				for(int i=0; i<blocs.length; i++) {
					blocs[i].visible();
					paintBloc(g, blocs[i].case_x, blocs[i].case_y);
					//repaint la bombe �ventuelle qui aurait �t� effac�
					Bombe.paintBombe(g, blocs[i].case_x, blocs[i].case_y);
				}//for
			}//else
		}
		else delai--;
	}//paintBlocsTimer();


	/*------------*/
	/* NON STATIC */
	/*------------*/

	/** Dessine un bloc */
	public void paint(Graphics g) {
		g.drawImage(zonejeu.images[numero_image], x, y, zonejeu);
		zonejeu.affichage(numero_image+"#"+x+"#"+y+"#");
	}//paint()

	/** Rend le bloc visible: ajout du bloc au tableau */
	public void visible() {
		tabl_blocs[case_x][case_y]=this;
		//supprime la bombe qui se trouverait l� si le bloc est une brique
		if (!isPave() && !isBonus()) {
			Bombe.supprimerBombe(case_x, case_y);
		}
	}//visible()

        /** Proc�dure de destruction d'un bloc */
        public void detruire(Graphics g) {
        	//si autres_blocs!=null, faire appara�tre les autres blocs
		if (autres_blocs!=null) {
			for(int i=0; i<autres_blocs.length; i++) {
				autres_blocs[i].visible();
				paintBloc(g, autres_blocs[i].case_x, autres_blocs[i].case_y);
			}
		}//if
		//si un autre bloc, le faire appara�tre
		if (autre_bloc!=null) {
			autre_bloc.visible();
			paintBloc(g, autre_bloc.case_x, autre_bloc.case_y);
		}//if
	}//detruire()
	
   	//M�thode � red�finir dans la sous-classe dans le cas d'un bloc de type bonus
   	//D�finit les cons�quences du bonus sur le joueur
   	/** Cons�quence du bloc sur le joueur lors de sa destruction */
   	public void action(Graphics g, Joueur joueur) {
   	}//action()

		/*--------------------------------------*/
		/* M�thodes d'acc�s et de modifications */
		/*--------------------------------------*/
	
	/** Retourne si type de bloc: pave  	      <-> true
				      bonus ou brique <-> false
	 */
	public boolean isPave() {
		return est_pave;
	}//isPave()

	/** Retourne si type de bloc: bonus	     <-> true
				      brique ou pave <-> false
	 */
	public boolean isBonus() {
		return est_bonus;
	}//isBonus()


    	
};//classe Bloc