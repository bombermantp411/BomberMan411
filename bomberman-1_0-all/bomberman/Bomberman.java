import java.awt.*;
import java.awt.event.*;
import java.util.*;
import base.*;
import reseau.*;
import zone.zonejeu.*;

/**
 	BOMBERMAN<br>
 	fichier: Bomberman.java<br>
 	Classe principale (éxécution du programme)
 	@author MAC WING Michel
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Bomberman extends Frame implements Observer, Constantes {

	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/
	
		/*--------*/
		/* PUBLIC */
		/*--------*/
	
	/** pilotage de la connexion */
	public static Connexion connexion;
	
		/*---------*/
		/* PRIVATE */
		/*---------*/
		
	//dimensions de la fenêtre	
	private static final int FENETRE_LARGEUR=840;
  	private static final int FENETRE_HAUTEUR=745;
  	
	//les destinataires possibles
	private static final int JOUER_CLIENT=-1;
	private static final int CLIENT_OK=-2;
  	private static final int MESSAGE=0;
  	private static final int MESSAGE_TEXTE=1;
  	
	//les zones de jeu
	private ZoneJeu_Serveur zonejeu_serveur;
	private ZoneJeu_Client zonejeu_client;
	
	//la zone de jeu serveur est un processus
	Thread th_zonejeu_serveur;
	
	
	
	//------------------------------------------------------------
	//			CONSTRUCTEUR
	//------------------------------------------------------------
	
	/*------------*/
	/* NON STATIC */
	/*------------*/
	
	/** Constuction du jeu Bomberman */
	public Bomberman() {
		
		Image icone;
		icone=getToolkit().getImage("./images/icone/bombe_ico.gif");
		setIconImage(icone);
		
		connexion=new Connexion(this);
		
		//bouton fermer fenêtre
      		addWindowListener(new WindowAdapter() {
         		public void windowClosing(WindowEvent e) {
         			System.out.println("Infos: quitte la partie");
         			//fermer les processus client ou serveur
         			connexion.fermer();
               			setVisible(false);
               			System.gc();
               			System.runFinalization();
               		}
		});
		
		//dimension de la fenêtre
		setSize(FENETRE_LARGEUR, FENETRE_HAUTEUR);
		
		//layout manager
		setLayout(new BorderLayout());
		
		//fond noir
      		setBackground(Color.black);
      		
      		//non redimensionnable
      		setResizable(false);
      		
      		//le titre
      		setTitle("Bomberman 1.0 / Auteur: MAC WING Michel");
      		
      		//positionnement de la fenêtre au centre de l'écran
      		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
      		int xPos=(tailleEcran.width-FENETRE_LARGEUR)/2;
      		int yPos=0;
      		setLocation(xPos, yPos);
	}//Bomberman()
	
	
	
	//------------------------------------------------------------
	//			METHODES PUBLIQUES
	//------------------------------------------------------------

	/*------------*/
	/* NON STATIC */
	/*------------*/
	
	/** L'objet courant joue le role de routeur des paquets de données entrants
	    provenant soit du serveur, soit du client selon le mode de connexion
	 */
	public void update(Observable obs , Object reponse) {
		String message=(String)reponse;
		//détermination du destinataire
		int fin=message.indexOf("#", 0);
	 	String str=message.substring(0, fin);
	 	int destinataire=(new Integer(str)).intValue();
	 	
	 	//envoi au destinataire
		if (obs==connexion.client) {
			switch (destinataire) {
	 			case MESSAGE:
	 				zonejeu_client.message(message, fin);
	 				break;
	 			case JOUER_CLIENT:
	 				jouerClient();
	 				break;
	 			case MESSAGE_TEXTE:
	 				zonejeu_client.message_texte(message, fin);
	 				break;
	 			default:
	 		}//switch
		}//obs==client	

	 	//envoi au destinataire
		else if (obs==connexion.serveur) {
			switch (destinataire) {
				case MESSAGE:
	 				zonejeu_serveur.message(message, fin);
	 				break;
				case CLIENT_OK:
					zonejeu_serveur.client_ok=true;
					break;
	 			default:
	 		}//switch
		}//obs==serveur
	}//update()
				
	/** Exécution du jeu en tant que serveur */
	public void jouerServeur() {
		zonejeu_serveur=new ZoneJeu_Serveur(this);
		zonejeu_serveur.initImages();
		th_zonejeu_serveur=new Thread(zonejeu_serveur);
		th_zonejeu_serveur.start();
		add(zonejeu_serveur);
		setVisible(true);
   	}//JouerServeur()
	
	/** Exécution du jeu en tant que client */
	public void jouerClient() {
	 	zonejeu_client=new ZoneJeu_Client(this);
      		zonejeu_client.initImages();
		add(zonejeu_client);
		setVisible(true);
   	}//JouerClient()
	
	/** Stoppe toute partie en cours */		
	public void stopperJouer() {
		if (zonejeu_serveur!=null) {
			th_zonejeu_serveur.stop();
			zonejeu_serveur.fermer();
			setVisible(false);
			remove(zonejeu_serveur);
			zonejeu_serveur=null;
		}
		if (zonejeu_client!=null) {
			zonejeu_client.fermer();
			setVisible(false);
			remove(zonejeu_client);
			zonejeu_client=null;
		}
	}//stopperJouer()





        //------------------------------------------------------------
	//			METHODE PRINCIPALE
	//------------------------------------------------------------

   	public static void main(String[] args) {
   		//Démarrage	
   		Bomberman Jeu=new Bomberman();
      	}//main()



};//classe principale Bomberman
