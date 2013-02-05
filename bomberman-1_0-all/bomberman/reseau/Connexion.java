package reseau;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import reseau.*;
import zone.ZoneJeu;
import general.Preference;
import general.APropos;
import Bomberman;

/**
 	BOMBERMAN<br>
 	classe: Connexion.java<br>
 	Fenêtre de connexion
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public final class Connexion extends Frame implements ActionListener, ItemListener{

	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------
	
	/*------------*/
	/* NON STATIC */
	/*------------*/
	
		/*------------------*/
		/* Serveur / Client */
		/*------------------*/
		
		/*--------*/
		/* PUBLIC */
		/*--------*/
		
	/** le serveur */
	public Serveur serveur;
	
	/** le client */
	public Client client;
	
		/*---------*/
		/* PRIVATE */
		/*---------*/
		
	//le processus serveur, le processus client
	private Thread thread_serveur, thread_client;
	
	//mode de connexion (client ou serveur)
	private static final int SERVEUR=0, CLIENT=1;
	
	//mode de connexion courant
	private int mode;
	
	//spécifie si client ou serveur lancé
	private boolean etat;
	
	//spécifie si liaison en cours
	private boolean liaison;

		/*----------------*/
		/* Infos diverses */
		/*----------------*/
	
	//propriétaire de la fenêtre
	private Bomberman parent;
		
	//dimensions de la fenêtre	
	private static final int FENETRE_LARGEUR=400;
  	private static final int FENETRE_HAUTEUR=500;
  	
		/*----------*/
		/*  Le menu */
		/*----------*/

	//barre de menu
	private MenuBar barre_menu;
	
	//menu de commandes
	private Menu menu_commandes;
	
	//commandes
	private MenuItem mitem_jouer;
	private MenuItem mitem_quitter;
	
	//pour lancer ou fermer le processus serveur ou client	
	private MenuItem mitem_start;
	private MenuItem mitem_stop;
			
	//menu des divers
	private Menu menu_divers;
	private MenuItem mitem_preference;
	private MenuItem mitem_apropos;
	
		/*------------------------------*/
		/* Les composants de la fenêtre */
		/*------------------------------*/

	//menu choix type de connexion
	private Choice choix_typeconnexion;
	
	//label "Serveur distant:" (mode client)
	private Label lbl_poste;
	
	//champ texte: adresse ou nom du poste distant (mode client)
	private TextField txt_poste;
	
	//nom du poste courant (mode client et serveur)
	private Label lbl_postecourant;
	
	//ip du poste courant (mode client et serveur)
	private Label lbl_ippostecourant;
	
	//label "N° Port:" (mode client et serveur)
	private Label lbl_port;
	
	//champ texte: n° port poste distant (mode client) ou n° port (mode serveur)
	private TextField txt_port;
	
	//label "Etat:" (mode client et serveur)
	private Label lbl_etat;
	
	//champ état de connexion (mode client et serveur)
	private TextArea txt_etat;
			
	//image de fond
	private Image fond;
	
	//mediatracker pour surveiller le chargement de l'image
	private MediaTracker tracker;

	//fenêtre des préférences
	private Preference preference;
	
	//fenêtre d'à propos
	private APropos apropos;


	
	//------------------------------------------------------------
	//		CONSTRUCTEUR / INITIALISATIONS
	//------------------------------------------------------------
	
	/*------------*/
	/* NON STATIC */
	/*------------*/
	
	/** Constructeur de la fenêtre */
	public Connexion(Bomberman parent) {
		
		this.mode=CLIENT;
		this.etat=false;
		this.liaison=false;
		this.parent=parent;
		
		Image icone;
		icone=getToolkit().getImage("./images/icone/bombe_ico.gif");
		setIconImage(icone);
		
		initMenu();		
		initPosition();
		initConteneur();
		
		preference=new Preference();
		apropos=new APropos();
		
		addWindowListener(new WindowAdapter() {
         		public void windowClosing(WindowEvent e) {
         			System.out.println("Infos: termine le programme");
         			//ferme ce qui a à fermer
         			fermer();
         			//on libère la mémoire avant de quitter
         			System.runFinalizersOnExit(true);
         			System.gc();
               			System.runFinalization();
         			System.exit(0);
         			
               		}
		});
		
		setBackground(Color.white);
		setResizable(false);
		setTitle("Connexion réseau");
		setSize(FENETRE_LARGEUR, FENETRE_HAUTEUR);
		setVisible(true);
		
	}//Connexion()



       //------------------------------------------------------------
	//			METHODES PUBLIQUES
	//------------------------------------------------------------
	
	/*------------*/
	/* NON STATIC */
	/*------------*/

		/*---------------------------------*/
		/* Commandes de controle du réseau */
		/*---------------------------------*/

	/** Démarre le serveur */
	public void demarrerServeur() {
		//par précaution
		if (etat==true || mode==CLIENT) return;
		//récupération du n° port
		int num_port;
		if (txt_port.getText().compareTo("")!=0) {
			try{
				num_port=(new Integer(txt_port.getText()).intValue());
      			} catch  (Exception e) {
    				addInfo("!!! Numéro de port invalide\nDémarrage du serveur impossible");
    				return;
        		}
        	}
        	else num_port=0;
        	
        	if (num_port==0) {
        		addInfo("Le serveur va tenter de démarrer sur un port libre");
        	}
        	else addInfo("Le serveur va tenter de se démarrer sur le port "+num_port);
        	
		serveur=new Serveur(this, num_port);
		thread_serveur=new Thread(serveur);
      		//le thread est un demon
      		thread_serveur.setDaemon(true);
      		//on démarre le processus serveur
      		thread_serveur.start();
      		
      		serveur.addObserver(parent);
      		setEtat(true);
	}//demarrerServeur()

	/** Eteint le serveur */
	public void eteindreServeur() {
		//par précaution
		if (etat==false || mode==CLIENT) return;
		setEtat(false);
		serveur.deleteObservers();
    		thread_serveur.stop();
    		serveur.eteindre();
      		serveur=null;
      		thread_serveur=null;
	}//eteindreServeur()

	/** Connecte le client */
	public void connexionClient() {
		//par précaution
		if (etat==true || mode==SERVEUR) return;
		
		//récupération de l'adresse du poste distant
		String poste_distant=txt_poste.getText();
		if (poste_distant.compareTo("")==0) {
			addInfo("!!! Adresse ou nom du serveur distant manquant\nConnexion du client impossible");
			return;
		}
		//récupération du n° port
		String port=txt_port.getText();
		if (port.compareTo("")==0) {
			addInfo("!!! Numéro de port manquant\nConnexion du client impossible");
    			return;
		}
		int num_port;
		try{
			num_port=(new Integer(port).intValue());
      		} catch  (Exception e) {
    			addInfo("!!! Numéro de port invalide\nConnexion du client impossible");
    			return;
        	}//catch
        	
        	addInfo("Le client va tenter de se connecter sur "+poste_distant+" "+num_port);
        	
        	client=new Client(this, poste_distant, num_port);
		thread_client=new Thread(client);
      		//le thread est un demon
      		thread_client.setDaemon(true);
      		thread_client.start();
      		
      		client.addObserver(parent);
      		setEtat(true);
	}//connexionClient()

	/** Déconnecte le client */
	public void deconnexionClient() {
		//par précaution
		if (etat==false || mode==SERVEUR) return;
		setEtat(false);
		client.deleteObservers();
      		thread_client.stop();
      		client.deconnexion();
      		client=null;
      		thread_client=null;
	}//deconnexionClient()
	
		/*------------*/
		/* Evènements */
		/*------------*/

	/** Capture des évènements du menu */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand()=="Jouer") {
			parent.jouerServeur();
			serveur.envoyer("-1#");
			mitem_jouer.setEnabled(false);
			preference.fermer();
			preference.setVisible(false);
			mitem_preference.setEnabled(false);
		}
		if (e.getActionCommand()=="Démarrer") {
			demarrerServeur();
		}
		if (e.getActionCommand()=="Eteindre") {
			eteindreServeur();
		}
		
		if (e.getActionCommand()=="Connexion") {
			connexionClient();
		}
		if (e.getActionCommand()=="Déconnexion") {
			deconnexionClient();
		}
		if (e.getActionCommand()=="Quitter") {
			fermer();
			System.exit(0);
		}
		if (e.getActionCommand()=="Préférences") {
			preference.setVisible(true);
		}
		if (e.getActionCommand()=="A Propos") {
			apropos.setVisible(true);
		}	
	}//actionPerformed()

	/** Capture des évènements du menu choix */
	public void itemStateChanged(ItemEvent e) {
		if (e.getItem()=="Serveur") {
			mode=SERVEUR;
			setMenuClient(false);
			setMenuServeur(true);
			lbl_poste.setVisible(false);
			txt_poste.setVisible(false);
		}
		if (e.getItem()=="Client") {
			mode=CLIENT;
			setMenuServeur(false);
			setMenuClient(true);
			lbl_poste.setVisible(true);
			txt_poste.setVisible(true);
			
		}
	}//itemStateChanged()

		/*--------------------------------------*/
		/* Méthodes d'accès et de modifications */
		/*--------------------------------------*/
	
	/** Spécifie le numéro de port utilisé */
	public void setPort(int num_port) {
		txt_port.setText(""+num_port);
	}//setPort()
	
	/** Spécifie qu'une liaison est établie */
	public void setLiaison(boolean liaison) {
		this.liaison=liaison;
		mitem_jouer.setEnabled(liaison);
		mitem_preference.setEnabled(!liaison);
		if (!liaison) parent.stopperJouer();
	}//setLiaison()

	/** Ajoute une donnée d'état à la fenêtre d'état */
	public void addInfo(String info) {
		if (liaison) txt_etat.setForeground(Color.green);
		else txt_etat.setForeground(Color.red);
		txt_etat.append(info+"\n");
	}//addInfo()
	
		/*-------------------*/
		/* Méthodes diverses */
		/*-------------------*/
	
	/** Ferme tout processus client ou serveur démarré */
	public void fermer() {
		if (mode==SERVEUR) eteindreServeur();
		if (mode==CLIENT) deconnexionClient();
	}//fermer()	

	/** Optimisation de l'affichage: empêche l'effacement avant de repeindre */
	public void update(Graphics g) {
		paint(g);
	}//update()
	
	/** Dessine le fond */
	public void paint(Graphics g) {
		g.drawImage(fond, 0, 20, FENETRE_LARGEUR, FENETRE_HAUTEUR, this);
   	}//paint()


	
        //------------------------------------------------------------
	//			METHODES PRIVEES
	//------------------------------------------------------------
	
	/*------------*/
	/* NON STATIC */
	/*------------*/

	/** Création du menu */
	private void initMenu() {
		barre_menu=new MenuBar();
		menu_commandes=new Menu("Commandes");
		
		mitem_jouer=new MenuItem("Jouer");
		mitem_jouer.addActionListener(this);
		mitem_jouer.setEnabled(false);
				
		mitem_quitter=new MenuItem("Quitter");
		mitem_quitter.addActionListener(this);
		
		//en mode client au départ
		mitem_start=new MenuItem("Connexion");
		mitem_start.addActionListener(this);
		
		mitem_stop=new MenuItem("Deconnexion");
		mitem_stop.addActionListener(this);
		mitem_stop.setEnabled(false);
		
		menu_divers=new Menu("Divers");
		mitem_preference=new MenuItem("Préférences");
		mitem_preference.addActionListener(this);
		
		mitem_apropos=new MenuItem("A Propos");
		mitem_apropos.addActionListener(this);
		
		setMenuServeur(false);
		setMenuClient(true);
		
		barre_menu.add(menu_commandes);
		barre_menu.add(menu_divers);
		
		setMenuBar(barre_menu);
	}//initMenu()

	/** Positionnement de la fenêtre en haut à droite de l'écran */
	private void initPosition() {
      		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
      		int xPos=(tailleEcran.width-FENETRE_LARGEUR);
      		int yPos=0;
      		setLocation(xPos, yPos);
      	}//initPosition()

	/** Création de l'intérieur de la fenêtre */
	private void initConteneur() {
		//le fond
		fond=getToolkit().getImage("./images/connexion/bomby_fond.gif");
		tracker=new MediaTracker(this);
		tracker.addImage(fond, 0);
		try{
      			tracker.waitForID(0);
      		} catch  (Exception a) {
    			System.out.println("Erreur lors chargement de l'image: initConteneur() Connexion");
        		a.printStackTrace();
        	}
		
		//utilisation d'un layout manager
		GridBagLayout layout=new GridBagLayout();
		setLayout(layout);
		GridBagConstraints c= new GridBagConstraints();
		c.weightx=10;
		c.weighty=10;
		c.ipadx=10;
		c.ipady=10;
		c.fill = GridBagConstraints.BOTH;
		
		//label nom du poste courant
		String poste_courant;
		try {
			poste_courant=InetAddress.getLocalHost().getHostName().toUpperCase();
		}
		catch (Exception b) {
			poste_courant="??????????";
		}
		lbl_postecourant=new Label(poste_courant);
		lbl_postecourant.setAlignment(Label.CENTER);
		lbl_postecourant.setBackground(Color.black);
		lbl_postecourant.setForeground(Color.cyan);
		lbl_postecourant.setFont(new Font("Monospaced", Font.BOLD, 17));
		c.gridx=0; c.gridy=0;
		c.gridwidth=1; c.gridheight=1;
		c.insets=new Insets(25, 10, 3, 10);
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(lbl_postecourant, c);
		add(lbl_postecourant);

		//label ip du poste courant
		String ip_courant;
		try {
			ip_courant=InetAddress.getLocalHost().getHostAddress();
		}
		catch (Exception d) {
			ip_courant   ="???.???.???.???";
		}
		lbl_ippostecourant=new Label(ip_courant);
		lbl_ippostecourant.setAlignment(Label.CENTER);
		lbl_ippostecourant.setBackground(Color.black);
		lbl_ippostecourant.setForeground(Color.red);
		lbl_ippostecourant.setFont(new Font("Monospaced", Font.PLAIN, 14));
		c.gridx=0; c.gridy=1;
		c.gridwidth=1; c.gridheight=1;
		c.insets=new Insets(3, 10, 5, 10);
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(lbl_ippostecourant, c);
		add(lbl_ippostecourant);
		
		//le menu choix
		choix_typeconnexion=new Choice();
		choix_typeconnexion.setBackground(Color.yellow);
		choix_typeconnexion.setForeground(Color.blue);
		choix_typeconnexion.setFont(new Font("Serif", Font.BOLD, 15));
		choix_typeconnexion.addItem("Client");
		choix_typeconnexion.addItem("Serveur");
		choix_typeconnexion.addItemListener(this);
		c.gridx=0; c.gridy=2;
		c.gridwidth=1; c.gridheight=1;
		c.insets=new Insets(20, 10, 5, 10);
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(choix_typeconnexion, c);
		add(choix_typeconnexion);
		
		//label poste distant (mode client)
		lbl_poste=new Label("Serveur Distant:");
		lbl_poste.setAlignment(Label.CENTER);
		lbl_poste.setBackground(Color.black);
		lbl_poste.setForeground(Color.green);
		lbl_poste.setFont(new Font("SansSerif", Font.ITALIC, 12));
		c.gridx=0; c.gridy=3;
		c.gridwidth=1; c.gridheight=1;
		c.insets=new Insets(15, 100, 5, 100);
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(lbl_poste, c);
		add(lbl_poste);
		
		//le champ texte nom poste distant (mode client)
		txt_poste=new TextField();
		txt_poste.setBackground(Color.black);
		txt_poste.setForeground(Color.red);
		txt_poste.setFont(new Font("Serif", Font.BOLD, 18));
		c.gridx=0; c.gridy=4;
		c.gridwidth=1; c.gridheight=1;
		c.insets=new Insets(5, 50, 15, 50);
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(txt_poste, c);
		add(txt_poste);

		//label port
		lbl_port=new Label("Port:");
		lbl_port.setAlignment(Label.CENTER);
		lbl_port.setBackground(Color.black);
		lbl_port.setForeground(Color.green);
		lbl_port.setFont(new Font("SansSerif", Font.ITALIC, 12));
		c.gridx=0; c.gridy=5;
		c.gridwidth=1; c.gridheight=1;
		c.insets=new Insets(5, 140, 5, 140);
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(lbl_port, c);
		add(lbl_port);

		//le champ texte n° port
		txt_port=new TextField();
		txt_port.setBackground(Color.black);
		txt_port.setForeground(Color.pink);
		txt_port.setFont(new Font("Serif", Font.BOLD, 20));
		txt_port.setColumns(8);
		c.gridx=0; c.gridy=6;
		c.gridwidth=1; c.gridheight=1;
		c.insets=new Insets(5, 155, 15, 155);
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(txt_port, c);
		add(txt_port);
		
		//label état
		lbl_etat=new Label("Etat:");
		lbl_etat.setAlignment(Label.CENTER);
		lbl_etat.setBackground(Color.black);
		lbl_etat.setForeground(Color.yellow);
		lbl_etat.setFont(new Font("SansSerif", Font.ITALIC, 14));
		c.gridx=0; c.gridy=7;
		c.gridwidth=1; c.gridheight=1;
		c.insets=new Insets(5, 10, 5, 10);
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(lbl_etat, c);
		add(lbl_etat);
		
		//le champ état de connexion
		txt_etat=new TextArea(5, 40);
		txt_etat.setEditable(false);
		c.gridx=0; c.gridy=8;
		c.gridwidth=1; c.gridheight=1;
		c.insets=new Insets(5, 10, 10, 10);
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(txt_etat, c);
		add(txt_etat);		
	}//initConteneur()

		/*----------------------------------*/
		/* Mise en adéquation avec contexte */
		/*----------------------------------*/

	//spécifie la configuration du menu	true  <-> menu serveur
	//					false <-> supprime menu serveur
	private void setMenuServeur(boolean afficher) {
		if (afficher) {
			mitem_start.setLabel("Démarrer");
			menu_commandes.add(mitem_start);
			mitem_stop.setLabel("Eteindre");
			menu_commandes.add(mitem_stop);
			menu_commandes.add(mitem_jouer);
			menu_commandes.addSeparator();
			menu_commandes.add(mitem_quitter);
			menu_divers.add(mitem_preference);
			menu_divers.addSeparator();
			menu_divers.add(mitem_apropos);
		}
		else {	menu_commandes.removeAll();
			menu_divers.removeAll();
		}
	}//setMenuServeur()
	
	//spécifie la configuration du menu	true  <-> menu client
	//					false <-> supprime menu client
	private void setMenuClient(boolean afficher) {
		if (afficher) {
			mitem_start.setLabel("Connexion");
			menu_commandes.add(mitem_start);
			mitem_stop.setLabel("Déconnexion");
			menu_commandes.add(mitem_stop);
			menu_commandes.addSeparator();
			menu_commandes.add(mitem_quitter);
			menu_divers.add(mitem_apropos);
		}
		else { 	menu_commandes.removeAll();
			menu_divers.removeAll();
		}
	}//setMenuClient()
	
	//état:  true  <-> serveur démarré ou client connecté
	//	 false <-> serveur éteint ou client déconnecté
	private void setEtat(boolean etat) {
		this.etat=etat;
		mitem_start.setEnabled(!etat);
		mitem_stop.setEnabled(etat);
		choix_typeconnexion.setEnabled(!etat);
		txt_poste.setEditable(!etat);
		txt_port.setEditable(!etat);
	}//setEtat()
	


};//classe Connexion