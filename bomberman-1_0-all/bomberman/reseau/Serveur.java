package reseau;

import java.io.*;
import java.net.*;
import java.util.*;
import reseau.*;

/**
 	BOMBERMAN<br>
 	classe: Serveur.java<br>
 	Serveur réseau
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Serveur extends Observable implements Runnable {

	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------
	
	/*------------*/
	/* NON STATIC */
	/*------------*/
			
		/*--------*/
		/* PUBLIC */
		/*--------*/

	/** le message venant du client */
	public String reponse;

		/*---------*/
		/* PRIVATE */
		/*---------*/

	//le parent du processus client
	private Connexion parent;
  
  	//la socket de connexion du serveur en attente
  	private ServerSocket serveur_socket;

  	//la socket sur laquelle le client sera traite
  	private Socket socket;
  
  	//numéro de port à utiliser
  	private int num_port;
  
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
  	
	/** Construction du serveur */
  	public Serveur (Connexion parent, int num_port) {
  		this.parent=parent;
  		this.num_port=num_port;
  	}//Server()



	//------------------------------------------------------------
	//			METHODES PUBLIQUES
	//------------------------------------------------------------
	
	/*------------*/
	/* NON STATIC */
	/*------------*/
  	  	
	/** Processus Serveur */
  	public void run() {
  		//démarrage du serveur
		try {
			parent.addInfo("Démarrage en cours...");
			serveur_socket = new ServerSocket(num_port);
			int num_port=serveur_socket.getLocalPort();
			String nom_serveur=serveur_socket.getInetAddress().getHostName();
			parent.addInfo("SERVEUR ON\nserveur: "+nom_serveur);
			parent.setPort(num_port);
			
		}//try
		catch (Exception a) {
			parent.addInfo("ERREUR\n!!! Impossible de démarrer le serveur\nVeuillez réessayer");
			parent.eteindreServeur();
			return;
		}//catch
		
		//attente jusqu'à connexion d'un client
		try {
			parent.addInfo("Attente d'un client...");
        		socket=serveur_socket.accept();
        		parent.setLiaison(true);
        		parent.addInfo("CLIENT OK\nliaison: "+socket);
        	}//try
        	catch (Exception b) {
        		parent.addInfo("ERREUR\n!!! Erreur connexion cliente\nVeuillez redémarrer le serveur");
        		parent.eteindreServeur();
        		return;
        	}//catch
        	
      		//les flux d'entrees/sorties
      		try {
      			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));      
      		}//try
      		catch (Exception d) {
      			System.out.println("Une erreur s'est produite; flux i/o: run() Serveur");
        		d.printStackTrace();
      		}//catch
      		
      		//attente d'un message client
      		try {
      			for (;;) {
				reponse=in.readLine();
				if (reponse==null) {
					parent.addInfo("CLIENT DECONNECTE\nLe client s'est déconnecté ou liaison rompue");
	    				eteindre();
	    				parent.eteindreServeur();
	    			}
				
				else {
					//ajout de la provenance du paquet 1:client
					//toujours=1 car pour le moment le serveur ne gère qu'un client
					reponse=reponse.concat("#1");
					setChanged();
					notifyObservers(reponse);
					//System.out.println("Le serveur reçoit: "+reponse);
				}
			}//for
	    	}//try
	    	catch (Exception c) {
	    		System.out.println("Infos: socket fermé -->"+socket);
	    		parent.addInfo("CLIENT DECONNECTE\nLe client s'est déconnecté ou liaison rompue");
	    		eteindre();
	    		parent.eteindreServeur();
        	}//catch
        }//run()

	/** Envoyer un message au client */
	public void envoyer(String message) {
		//envoi
		out.println(message);
      		//par vider le buffer
      		out.flush();
      		//System.out.println("Le serveur envoie: " +message);
      	}//envoyer()

  	/** Fermeture du serveur */
  	public void eteindre() {
  		try {
      			socket.close();
      		}//try
      		catch(Exception e) {}
    		try {
      			serveur_socket.close();
      		}//try
      		catch(Exception f) {}
      		parent.setLiaison(false);
      		parent.addInfo("SERVEUR OFF");
    	}//eteindre()
    	

    	
};//classe Serveur
