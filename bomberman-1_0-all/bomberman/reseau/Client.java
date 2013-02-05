package reseau;

import java.io.*;
import java.net.*;
import java.util.*;
import reseau.*;

/**
 	BOMBERMAN<br>
 	classe: Client.java<br>
 	Client réseau
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Client extends Observable implements Runnable {

	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------
	
	/*------------*/
	/* NON STATIC */
	/*------------*/
			
		/*--------*/
		/* PUBLIC */
		/*--------*/
	
	/** le message venant du serveur */
	public String reponse;
	
		/*---------*/
		/* PRIVATE */
		/*---------*/

	//le parent du processus client
	private Connexion parent;
	
	//adresse serveur distant
	private String adresse;
	
	//port sur lequel le client doit se connecter
	private int port;

  	//la socket de connexion du serveur
  	private Socket socket;
  
  	//les entrees
  	private BufferedReader in;

  	//les sorties
  	private PrintWriter out;



	//------------------------------------------------------------
	//			CONSTRUCTEUR
	//------------------------------------------------------------
	
	/*------------*/
	/* NON STATIC */
	/*------------*/
  	
	/** Construction du client */
  	public Client (Connexion parent, String adresse, int port) {
  		this.parent=parent;
  		this.adresse=adresse;
  		this.port=port;
  	}//Client()
  	
  	
  	
	//------------------------------------------------------------
	//			METHODES PUBLIQUES
	//------------------------------------------------------------
	
	/*------------*/
	/* NON STATIC */
	/*------------*/
	
	/** Processus Client */
  	public void run() {
  		//connexion du client
		try {
			parent.addInfo("Connexion en cours...");
  			InetAddress addr=InetAddress.getByName(adresse);
  			socket=new Socket(addr, port);
  			parent.setLiaison(true);
  			parent.addInfo("CLIENT ON\nserveur: "+addr);
		}//try
		catch (Exception a) {
			parent.addInfo("ERREUR\n!!! Impossible de se connecter\nVeuillez réessayer");
			parent.deconnexionClient();
			return;
		}//catch
		
		//les flux d'entrees/sorties
		try {
      			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      
      			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
      			
      		}//try
      		catch (Exception d) {
      			System.out.println("Une erreur s'est produite; flux i/o: run() Client");
        		d.printStackTrace();
      		}//catch
      
      		//attente d'un message serveur
      		try {
      			for (;;) {	
				reponse=in.readLine ();
				if (reponse==null) {
					parent.addInfo("SERVEUR ETEINT\nLe serveur s'est éteint ou liaison rompue");
	    				deconnexion();
	    				parent.deconnexionClient();
	    			}
				
				else {
					setChanged();
					notifyObservers(reponse);
					//System.out.println("Le client reçoit: "+reponse);
				}
			}//for
	    	}//try
	    	catch (Exception b) {
	    		System.out.println("Infos: socket fermé -->"+socket);
	    		parent.addInfo("SERVEUR ETEINT\nLe serveur s'est éteint ou liaison rompue");
	    		deconnexion();
	    		parent.deconnexionClient();
        	}//catch    			
        }//run()

	/** Envoyer un message au serveur */
	public void envoyer(String message) {
		//envoi
		out.println(message);      
      		//vide le buffer
      		out.flush ();
      		//System.out.println("Le client envoie: " +message);
      	}//envoyer()

  	/** Déconnexion client */
  	public void deconnexion() {
  		try {
      			socket.close();
    		}//try
    		catch(Exception e) {}
    		parent.setLiaison(false);
    		parent.addInfo("CLIENT OFF");
    	}//deconnexion()



};//classe Client
