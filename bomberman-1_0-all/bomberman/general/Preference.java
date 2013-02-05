package general;

import java.awt.*;
import java.awt.event.*;

/**
 	BOMBERMAN<br>
 	classe: Preference.java<br>
 	Fenêtre des préférences du jeu<br>
 	Cette classe devra être modifiée légèrement si on augmente le nombre
 	de joueurs car contrairement aux autres, elle n'est pas "autoévolutive":
 	2 joueurs uniquement; raison invoquée: difficile de faire autrement
  	due au positionnement des éléments dans la fenêtre<br>
 	Classe appelée par différents processus et objets, d'où son aspect "general"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public final class Preference extends Frame implements ActionListener, ItemListener, AdjustmentListener {

	//------------------------------------------------------------
	//			DECLARATIONS
	//------------------------------------------------------------

	/*--------*/
	/* STATIC */
	/*--------*/
	
		/*--------*/
		/* PUBLIC */
		/*--------*/

	/** couleur des joueurs */
	public static final int ROUGE=0, BLEU=1;
	
	/** couleurs courantes des joueurs */
	public static int[] couleurs;
	
	/** noms des joueurs */
	public static String[] noms;
	
	/** nombre de vies */
	public static int nb_vies;
	
	/** nombre de victoires */
	public static int nb_victoires;
	
	/** niveaux aléatoires */
	public static boolean aleatoire;
	
	/** taux de rafraichissement (fps) */
	public static int fps;


	/*------------*/
	/* NON STATIC */
	/*------------*/

		/*---------*/
		/* PRIVATE */
		/*---------*/
		
		/*------------------------------*/
		/* Les composants de la fenêtre */
		/*------------------------------*/

	//label "Joueurs:"
	private Label lbl_joueurs;
	
	//menu choix type de couleur
	private Choice choix_couleur0, choix_couleur1;
	
	//noms des joueurs
	private TextField txt_joueur0, txt_joueur1;
	
	//nombre de vies
	private CheckboxGroup cbg_vies;
	
	//label nombre de victoires
	private Label lbl_victoires;
	
	//nombre de victoires
	private Scrollbar sb_victoires;
	
	//niveaux aléatoires
	private Checkbox cb_aleatoire;
	
	//label frames par seconde
	private Label lbl_fps;
	
	//taux de rafraîchissement;
	private Scrollbar sb_fps;
	
	//bouton ok
	private Button button_ok;
	
	//image de fond
	private Image fond;
	
	//mediatracker pour surveiller le chargement de l'image
	private MediaTracker tracker;

	//dimensions de la fenêtre	
	private static final int FENETRE_LARGEUR=500;
  	private static final int FENETRE_HAUTEUR=470;


	
	//------------------------------------------------------------
	//		CONSTRUCTEUR / INITIALISATIONS
	//------------------------------------------------------------
	
	/*------------*/
	/* NON STATIC */
	/*------------*/

	/** Constructeur de la fenêtre */	
	public Preference() {
		noms=new String[2];
		couleurs=new int[2];
		
		Image icone;
		icone=getToolkit().getImage("./images/icone/bombe_ico.gif");
		setIconImage(icone);
		
		initDefault();
		initPosition();
		initConteneur();
		
		addWindowListener(new WindowAdapter() {
         		public void windowClosing(WindowEvent e) {
         			fermer();
               		}
		});
		
		setSize(FENETRE_LARGEUR, FENETRE_HAUTEUR);
		setBackground(Color.white);
		setResizable(false);
		setTitle("Préférences");
    		setVisible(false);
	}//Preference()



        //------------------------------------------------------------
	//			METHODES PUBLIQUES
	//------------------------------------------------------------
	
	/*------------*/
	/* NON STATIC */
	/*------------*/
	
	/** Capture des évènements des menus choix */
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(choix_couleur0)) {
			if (e.getItem()=="rouge") {
				choix_couleur0.setBackground(Color.red);
				choix_couleur1.setBackground(Color.blue);
				choix_couleur1.select("bleu");
				couleurs[0]=ROUGE;
				couleurs[1]=BLEU;
			}
			if (e.getItem()=="bleu") {
				choix_couleur0.setBackground(Color.blue);
				choix_couleur1.setBackground(Color.red);
				choix_couleur1.select("rouge");
				couleurs[0]=BLEU;
				couleurs[1]=ROUGE;
			}
		}
		if (e.getSource().equals(choix_couleur1)) {
			if (e.getItem()=="rouge") {
				choix_couleur1.setBackground(Color.red);
				choix_couleur0.setBackground(Color.blue);
				choix_couleur0.select("bleu");
				couleurs[1]=ROUGE;
				couleurs[0]=BLEU;
				
			}
			if (e.getItem()=="bleu") {
				choix_couleur1.setBackground(Color.blue);
				choix_couleur0.setBackground(Color.red);
				choix_couleur0.select("rouge");
				couleurs[1]=BLEU;
				couleurs[0]=ROUGE;
			}
		}
	}//itemStateChanged()
	
	/** Capture des évènements bouton */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand()=="Ok") {
			fermer();
		}
	}//actionPerformed()
	
	/** Capture des évènements des scrollbars */
	public void adjustmentValueChanged(AdjustmentEvent e) {
		if (e.getSource().equals(sb_victoires)) {
			int valeur=sb_victoires.getValue();
			lbl_victoires.setText("Nombre De Victoires: "+valeur);
			nb_victoires=valeur;
		}
		else {
			int valeur=sb_fps.getValue();
			lbl_fps.setText("FPS: "+valeur);
			fps=valeur;
		}
	}//adjustmentValueChanged()

	/** Récupère les preférences manquants et ferme la fenêtre */
	public void fermer() {
		//noms des joueurs
		String nom=txt_joueur0.getText();
		if (nom.compareTo("")==0) noms[0]="J1";
		else noms[0]=nom;
		nom=txt_joueur1.getText();
		if (nom.compareTo("")==0) noms[1]="J2";
		else noms[1]=nom;
		
		//nombre de vies
		nb_vies=new Integer(cbg_vies.getSelectedCheckbox().getLabel()).intValue();
		aleatoire=cb_aleatoire.getState();
		setVisible(false);
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
	
	/** Définit les valeurs par défaut */
	private void initDefault() {
		couleurs[0]=ROUGE;
		couleurs[1]=BLEU;
		noms[0]="J1";
		noms[1]="J2";
		nb_vies=3;
		nb_victoires=5;
		aleatoire=false;
		fps=30;
	}//initDefault()

	/** Positionnement de la fenêtre au milieu */
	private void initPosition() {
      		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
      		int xPos=(tailleEcran.width-FENETRE_LARGEUR)/2;
      		int yPos=(tailleEcran.height-FENETRE_HAUTEUR)/2;
      		setLocation(xPos, yPos);
      	}//initPosition()

	/** Création de l'intérieur de la fenêtre */
	private void initConteneur() {
		//le fond
		fond=getToolkit().getImage("./images/preference/bomber_fond.gif");
		tracker=new MediaTracker(this);
		tracker.addImage(fond, 0);
		try{
      			tracker.waitForID(0);
      		} catch  (Exception a) {
    			System.out.println("Erreur lors chargement de l'image: initConteneur() Preference");
        		a.printStackTrace();
        	}
		
		//le layout manager
		GridBagLayout layout=new GridBagLayout();
		setLayout(layout);
		GridBagConstraints c= new GridBagConstraints();
		c.weightx=10;
		c.weighty=10;
		c.ipadx=10;
		c.ipady=10;
		c.fill = GridBagConstraints.BOTH;
		
		//label "Joueurs:"
		Label lbl_joueurs=new Label("Joueurs:");
		lbl_joueurs.setAlignment(Label.CENTER);
		lbl_joueurs.setFont(new Font("SansSerif", Font.BOLD, 16));
		lbl_joueurs.setBackground(Color.black);
		lbl_joueurs.setForeground(Color.red);
		c.gridx=0; c.gridy=0;
		c.gridwidth=6; c.gridheight=1;
		c.insets=new Insets(20, 10, 10, 10);
		c.anchor=GridBagConstraints.SOUTH;
		layout.setConstraints(lbl_joueurs, c);
		add(lbl_joueurs);

		//choix de couleur du joueur ayant l'identifiant 0
		choix_couleur0=new Choice();
		choix_couleur0.addItem("rouge");
		choix_couleur0.addItem("bleu");
		choix_couleur0.addItemListener(this);
		choix_couleur0.setBackground(Color.red);
		choix_couleur0.setForeground(Color.black);
		c.gridx=0; c.gridy=1;
		c.gridwidth=2; c.gridheight=1;
		c.insets=new Insets(10, 10, 5, 20);
		c.anchor=GridBagConstraints.EAST;
		layout.setConstraints(choix_couleur0, c);
		add(choix_couleur0);
		
		//nom du joueur ayant l'identifiant 0
		txt_joueur0=new TextField(noms[0]);
		c.gridx=2; c.gridy=1;
		c.gridwidth=4; c.gridheight=1;
		c.insets=new Insets(10, 10, 5, 20);
		c.anchor=GridBagConstraints.WEST;
		layout.setConstraints(txt_joueur0, c);
		add(txt_joueur0);
		
		//choix de couleur du joueur ayant l'identifiant 1
		choix_couleur1=new Choice();
		choix_couleur1.addItem("bleu");
		choix_couleur1.addItem("rouge");
		choix_couleur1.addItemListener(this);
		choix_couleur1.setBackground(Color.blue);
		choix_couleur1.setForeground(Color.black);
		c.gridx=0; c.gridy=2;
		c.gridwidth=2; c.gridheight=1;
		c.insets=new Insets(10, 10, 20, 20);
		c.anchor=GridBagConstraints.EAST;
		layout.setConstraints(choix_couleur1, c);
		add(choix_couleur1);
		
		//nom du joueur ayant l'identifiant 1
		txt_joueur1=new TextField(noms[1]);
		c.gridx=2; c.gridy=2;
		c.gridwidth=4; c.gridheight=1;
		c.insets=new Insets(10, 10, 30, 20);
		c.anchor=GridBagConstraints.WEST;
		layout.setConstraints(txt_joueur1, c);
		add(txt_joueur1);
		
		//label "Nombre De Vies:"
		Label lbl_nbvies=new Label("Nombre De Vies:");
		lbl_nbvies.setAlignment(Label.CENTER);
		lbl_nbvies.setFont(new Font("SansSerif", Font.BOLD, 14));
		lbl_nbvies.setBackground(Color.black);
		lbl_nbvies.setForeground(Color.yellow);
		c.gridx=0; c.gridy=4;
		c.gridwidth=1; c.gridheight=1;
		c.insets=new Insets(20, 10, 5, 10);
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(lbl_nbvies, c);
		add(lbl_nbvies);
		//un choix possible
		cbg_vies=new CheckboxGroup();
		Checkbox cb;
		for (int i=1; i<=5; i++) {
			if (i==nb_vies) cb=new Checkbox(""+i, cbg_vies, true);
			else cb=new Checkbox(""+i, cbg_vies, false);
			c.gridx=i; c.gridy=4;
			c.gridwidth=1; c.gridheight=1;
			c.insets=new Insets(20, 10, 5, 10);
			c.anchor=GridBagConstraints.CENTER;
			layout.setConstraints(cb, c);
			add(cb);
		}//for
		
		//label "Nombre	De Victoires:"
		lbl_victoires=new Label();
		lbl_victoires.setAlignment(Label.CENTER);
		lbl_victoires.setFont(new Font("SansSerif", Font.BOLD, 14));
		lbl_victoires.setText("Nombre De Victoires: "+nb_victoires);
		lbl_victoires.setBackground(Color.black);
		lbl_victoires.setForeground(Color.green);
		c.gridx=0; c.gridy=6;
		c.gridwidth=1; c.gridheight=1;
		c.insets=new Insets(20, 10, 5, 10);
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(lbl_victoires, c);
		add(lbl_victoires);
		
		//choix du nombre de victoires entre un 1 et 9
		sb_victoires=new Scrollbar(Scrollbar.HORIZONTAL, 1, 1, 1, 10);
		sb_victoires.setUnitIncrement(1);
		sb_victoires.setBlockIncrement(2);
		sb_victoires.addAdjustmentListener(this);
		sb_victoires.setValue(nb_victoires);
		c.gridx=1; c.gridy=6;
		c.gridwidth=5; c.gridheight=1;
		c.insets=new Insets(20, 10, 5, 10);
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(sb_victoires, c);
		add(sb_victoires);
		
		//tirage aléatoire des niveaux
		cb_aleatoire=new Checkbox("Niveaux Aléatoires", aleatoire);
		cb_aleatoire.setFont(new Font("SansSerif", Font.BOLD, 14));
		cb_aleatoire.setBackground(Color.black);
		cb_aleatoire.setForeground(Color.magenta);
		c.gridx=0; c.gridy=8;
		c.gridwidth=1; c.gridheight=1;
		c.insets=new Insets(20, 12, 5, 10);
		c.anchor=GridBagConstraints.EAST;
		layout.setConstraints(cb_aleatoire, c);
		add(cb_aleatoire);
		
		//label "FPS:" nombre de frames par seconde
		lbl_fps=new Label();
		lbl_fps.setAlignment(Label.CENTER);
		lbl_fps.setFont(new Font("SansSerif", Font.BOLD, 14));
		lbl_fps.setText("FPS: "+fps);
		lbl_fps.setBackground(Color.black);
		lbl_fps.setForeground(Color.orange);
		c.gridx=0; c.gridy=10;
		c.gridwidth=1; c.gridheight=1;
		c.insets=new Insets(20, 10, 5, 10);
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(lbl_fps, c);
		add(lbl_fps);
		
		//choix du nombre de frames par seconde
		sb_fps=new Scrollbar(Scrollbar.HORIZONTAL, 1, 1, 1, 51);
		sb_fps.setUnitIncrement(1);
		sb_fps.setBlockIncrement(10);
		sb_fps.addAdjustmentListener(this);
		sb_fps.setValue(fps);
		c.gridx=1; c.gridy=10;
		c.gridwidth=5; c.gridheight=1;
		c.insets=new Insets(20, 10, 5, 10);
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(sb_fps, c);
		add(sb_fps);
		
		//bouton de confirmation
		button_ok=new Button("Ok");
		c.gridx=0; c.gridy=12;
		c.gridwidth=6; c.gridheight=1;
		c.insets=new Insets(20, 5, 20, 5);
		c.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(button_ok, c);
		add(button_ok);
		button_ok.addActionListener(this);
		
	}//initConteneur()



};//classe Préférences