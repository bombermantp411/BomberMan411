package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Brick.java<br>
 	Bloc Brique "Brick"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Brick extends Bloc {
	/** Bloc Brique "Brick" */
	public Brique_Brick(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+2, autres_blocs);
	}//Brique_Brick()
	
	/** Surcharge: Bloc Brique "Brick" */
	public Brique_Brick(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+2, autre_bloc);
	}//Brique_Brick()
	
};//classe Brique_Brick