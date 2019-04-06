package genetics;

import genetics.population.*;
import genetics.evaluation.*;
import genetics.mutation.*;
import genetics.newGeneration.*;
import interpret.*;
import memoire.*;

import java.util.ArrayList;


public class Generation{

	/**Classe éxécutable qui permet de lancer l'algorithme génétique
	 * @param args la liste des arguments
	 */
	public static void main(String[] args){
		if (args.length != 4) {
			throw new RuntimeException("Usage : <nombre de Warriors> <nombre de warriors à prendre> <nombre de secondes > <nombre de combats>");
		}
		Mutation mutatron2000 = new Mutation(0.0100F,0.0100F,0.0100F,0.0100F);
		Selection select = new Selection(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
		Ecriture ecrit;
		ArrayList<Player> afterMatation=new ArrayList<>();
		ArrayList<Player> meilleur = new ArrayList<>();
		int nombreGeneration = 15;
		for(int i=0; i<nombreGeneration;i++){
			select.lancement();
			//sauvegarde des meilleurs
			meilleur = select.getwinner();
			reproduction repro = new reproduction(meilleur,Integer.parseInt(args[1]));
			//resultat de la reproduction
			ArrayList<Player> list = repro.nouvellegen();
			//resultat de la mutation
			afterMatation = mutatron2000.mutationMultiple(list);
			//remplacement de la population
			select.setlist(afterMatation);

			System.out.println("génération "+(i+1));
			if(i<(nombreGeneration-1)){
				retranscription(afterMatation,i);
			}
		}
		System.out.println("Fin de lancement");
		int compt=1;
		for(Player winner : afterMatation){
			ecrit = new Ecriture(winner.getCoups(),"Generation final");
			if(compt==1){
				ecrit.CreerDossier();
			}
			ecrit.creerfichier("generation "+compt);
			compt+=1;
		}
	}

	/**Méthode qui ecrit les liste d'instruction des players qu'on lui donne
	 * @param afterMatation la liste des players
	 * @param i le numéro de génération
	 */
	//méthode pour visualiser chaque génération
	public static void retranscription(ArrayList<Player> afterMatation,int i){
		Ecriture ecrit;
		int compt=1;
		for(Player winner : afterMatation){
			ecrit = new Ecriture(winner.getCoups(),"Generation "+(i+1));
			if(compt==1){
				ecrit.CreerDossier();
			}
			ecrit.creerfichier("generation "+compt);
			compt+=1;
		}
	}


}
