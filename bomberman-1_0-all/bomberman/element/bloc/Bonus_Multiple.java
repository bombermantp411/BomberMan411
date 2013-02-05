package element.bloc;

import java.awt.*;
import java.util.*;
import element.Bloc;
import element.Joueur;
import general.Information;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	classe: Bonus_Multiple.java<br>
 	Bloc Bonus "Multiple"<br>
 	Effet: aléatoire parmi les effets positifs
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Bonus_Multiple extends Bloc {

	/** Bloc Bonus "Multiple" */
	public Bonus_Multiple(int case_x, int case_y, Bloc[] autres_blocs) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+10, autres_blocs);
	}//Bonus_Multiple()

	/** Surcharge: Bloc Bonus "Multiple" */
	public Bonus_Multiple(int case_x, int case_y, Bloc autre_bloc) {
		initBonus(case_x, case_y, ZoneJeu.I_BLOCS_BONUS+10, autre_bloc);
	}//Bonus_Multiple()
	
	/** Effet: aléatoire parmi les effets positifs */
	public void action(Graphics g, Joueur joueur) {
		Random alea=new Random((new Date()).getTime());
		int resultat=(Math.abs(alea.nextInt()))%6;
		switch (resultat) {
			case 0:
				joueur.addMaxNbBombes(1);
				break;
			case 1:
				joueur.setPousser(true);
				break;
			case 2:
				joueur.addLongueurExlosion(1);
				break;
			case 3:
				joueur.setDetonateur(true);
				break;
			case 4:
				joueur.addInvincibilite(200);
				break;
			case 5:
				Information.addVies(g, joueur, 1);
				break;
		}//switch
		detruire(g);
	}//action()

};//classe Bonus_Multiple