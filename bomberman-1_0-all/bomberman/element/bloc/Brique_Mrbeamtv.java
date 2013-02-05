package element.bloc;

import element.Bloc;
import zone.ZoneJeu;

/**
 	BOMBERMAN<br>
 	fichier: Brique_Mrbeamtv.java<br>
 	Bloc Brique "Mrbeamtv"
 	@author MAC WING Michel<br>
 	<a href="mailto: minf14@educ.univ-reunion.fr">minf14@educ.univ-reunion.fr</a>
 	@version 1.0 du 01/01/2000
 */

public class Brique_Mrbeamtv extends Bloc {
	/** Bloc Brique "Mrbeamtv" */
	public Brique_Mrbeamtv(int case_x, int case_y, Bloc[] autres_blocs) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+23, autres_blocs);
	}//Brique_Mrbeamtv()
	
	/** Surcharge: Bloc Brique "Mrbeamtv" */
	public Brique_Mrbeamtv(int case_x, int case_y, Bloc autre_bloc) {
		initBrique(case_x, case_y, ZoneJeu.I_BLOCS_BRIQUES+23, autre_bloc);
	}//Brique_Mrbeamtv()
	
};//classe Brique_Mrbeamtv