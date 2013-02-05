package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Bricks.java<br>
 	Bloc Brique "Bricks"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Bricks extends Bloc {
	/** Bloc Brique "Bricks" */
	public Brique_Bricks(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+3, autres_blocs);
	}//Brique_Bricks()
	
	/** Surcharge: Bloc Brique "Bricks" */
	public Brique_Bricks(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+3, autre_bloc);
	}//Brique_Bricks()
	
};//classe Brique_Bricks