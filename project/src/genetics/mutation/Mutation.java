package genetics.mutation;


import java.util.ArrayList;
import java.util.List;

import interpret.*;
import memoire.*;
import java.util.Random;
import java.util.*;

// Methode mutation pour appliquer des mutations à une population entière
// Methode mutationSolo pour appliquer des mutations à un seul joueur ; Paramètre supplementaire : probabilite de mutation initiale (probabiliteJoueur)

public class Mutation{

	private int probabiliteOp;
	private int probabiliteModif;
	private int probabiliteAdresse; 
	private int probabiliteJoueur;
	private String operateurs[] = {"DAT","MOV","ADD","SUB","MUL","DIV","MOD","JMP","JMZ","JMN","DJN","DJZ","SPL","CMP","SEQ","SNE","SLT"};
	private char modificateurs[] = {'$','#','@','>','<'};

   /**
   * Constructeur de la classe qui met en place les probabilités
   * @param probabiliteJoueur probabilité de muter le joueur
   * @param probabiliteOp probabilité de muter l'opérateur
   * @param probabiliteModif probabilité de muter les modificateurs
   * @param probabiliteAdresse probabilité de muter les adresses
   */
	public Mutation(float probabiliteJoueur, float probabiliteOp, float probabiliteModif, float probabiliteAdresse){
		this.probabiliteAdresse=(int) probabiliteAdresse*10000;
		this.probabiliteModif=(int) probabiliteModif*10000;
		this.probabiliteOp=(int) probabiliteOp*10000;
		this.probabiliteJoueur=(int) probabiliteJoueur*10000;
	}

   /**
   * Méthode qui fait muter un seul joueur
   * @param  joueur le joueur à faire muter
   * @param probabiliteMutation probabilité de mutation
   * @return le player muter
   */
	public Player mutationSolo(Player joueur, float probabiliteMutation ){

		Random random = new Random();
		int first = random.nextInt(10000);
		int probabiliteMutationINT=(int) probabiliteMutation*10000;
		
		if(first<probabiliteMutationINT){
			return mutation(joueur);
		}else{
			return joueur;
		}
	}

   /**
   * Méthode de mutation (adressages,modificateurs et opérandes des instructions)
   * @param joueur le joueur a faire muter
   * @return le joueur muter
   */
	public Player mutation(Player joueur){
		Random random = new Random();
		ArrayList<Instruction> inst = new ArrayList(joueur.getCoups());
		ArrayList<Instruction> newInsts = new ArrayList<Instruction>();

		for(int j=0;j<inst.size();j++){
			Instruction instActuel = inst.get(j);
			int second = random.nextInt(10000);
			if(second<this.probabiliteModif){
				int arrDep = random.nextInt(1);
				int b = random.nextInt(4);
				if(arrDep==0){
					instActuel.setModificateurDepart(modificateurs[b]);
				}else{
					instActuel.setModificateurArrive(modificateurs[b]);
				}
			}
			int third = random.nextInt(10000);
			if(third<probabiliteAdresse){
				int arrDep2 = random.nextInt(1);
				if(arrDep2==0){
					int arrDep2_1 = random.nextInt(1);
					if(arrDep2_1==0){
						mutationAdressageDepart(instActuel,-1);
					}else{
						mutationAdressageDepart(instActuel,+1);
					}
				}

				if(arrDep2==1){
					int arrDep2_2 = random.nextInt(1);
					if(arrDep2_2 ==0){
						mutationAdressageArrive(instActuel,-1);
					}else{
						mutationAdressageArrive(instActuel,+1);
					}
				}	
			}

			int fourth = random.nextInt(10000);

			if(fourth<this.probabiliteOp){

				int a = random.nextInt(16);
				instActuel.setOperateur(operateurs[a]);
			}

			newInsts.add(instActuel);
		}

		Player joueur2 = new Player(newInsts);

		return joueur2;
	}


	/**
   * Méthode de mutation de l'adressage de départ
   * @param instActuel l'instruction a muter
   * @param a la valeur de modification
   */
	public void mutationAdressageDepart(Instruction instActuel,int a){
		instActuel.setAdresseDepart(instActuel.getAdresseDepart() + a);
		if(instActuel.getAdresseDepart()==0 && instActuel.getModificateurDepart() != '$' && instActuel.getModificateurDepart() != '#'){
			instActuel.setAdresseDepart(instActuel.getAdresseDepart() - a);
		}
	}

	/**
   * Méthode de mutation de l'adressage de d'arrive
   * @param instActuel l'instruction a muter
   * @param a la valeur de modification
   */
	public void mutationAdressageArrive(Instruction instActuel,int a){
		instActuel.setAdresseArrive(instActuel.getAdresseArrive() + a);
		if(instActuel.getAdresseArrive()==0 && instActuel.getModificateurArrive() != '$' && instActuel.getModificateurDepart() != '#'){
			instActuel.setAdresseArrive(instActuel.getAdresseArrive() - a);
		}
	}

	/**
   * Méthode de mutation d'une liste de player
   * @param population la liste des player a muter
   * @return retourne la liste des joueurs muter
   */
	public ArrayList<Player> mutationMultiple(ArrayList<Player> population){
		Random random = new Random();
		ArrayList<Player> newpopulation = new ArrayList<Player>();
		
		for(int i=0;i<population.size();i++){
			int first = random.nextInt(10000);
			Player joueurActuel = population.get(i);
			if(first<this.probabiliteJoueur){
				newpopulation.add(mutation(joueurActuel));
			}else{
				newpopulation.add(joueurActuel);
			}
		}
		return newpopulation;
	}
}
