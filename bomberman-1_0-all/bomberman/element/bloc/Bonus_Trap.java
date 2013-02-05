package element.bloc;

import java.awt.*;
import java.util.*;
import element.Bloc;
import element.Joueur;
import general.Information;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Bonus_Trap.java<br>
 	Bloc Bonus "Trap"<br>
 	Effet: aléatoire parmi les effets négatifs
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Bonus_Trap extends Bloc {
	
	/** Bonus_Trap.java */
	public Bonus_Trap(int case_x, int case_y, Bloc[] autres_blocs) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+11, autres_blocs);
	}//Bonus_Trap()

	/** Surcharge: Bonus_Trap.java */
	public Bonus_Trap(int case_x, int case_y, Bloc autre_bloc) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+11, autre_bloc);
	}//Bonus_Trap()
	
	/** Effet: aléatoire parmi les effets négatifs */
	public void action(Graphics g, Joueur joueur) {
		Random alea=new Random((new Date()).getTime());
		int resultat=(Math.abs(alea.nextInt()))%6;
		int retranche=((Math.abs(alea.nextInt()))%3)+1;
		switch (resultat) {
			case 0:
				joueur.addMaxNbBombes(-retranche);
				break;
			case 1:
				joueur.setPousser(false);
				break;
			case 2:
				joueur.addLongueurExlosion(-retranche);
				break;
			case 3:
				joueur.setDetonateur(false);
				break;
			case 4:
				joueur.addInvincibilite(-200);
				break;
			case 5:
				Information.addVies(g, joueur, -1);
				break;
		}//switch
		detruire(g);
	}//action()

};//classe Bonus_Trap