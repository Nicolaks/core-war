package Memoire;

import java.util.ArrayList;
import java.util.List;

import interpret.*;

public class Player{
	private String nom;
	private List<Instruction> coups;
	private List<Integer> pointeurs = new ArrayList<>();
	private int pointeurActuel;
	public String symbole;
	public int color;

	/** Constructeur du joueur avec initialisation des attributs
	 * @param nom le nom du joueur
	 * @param instru la liste des instructions du joueur
	 * @param X son symbol
	 */
	public Player(String nom,List<Instruction> instru,int pointeur,String X,int color){
		this.nom = nom;
		this.coups = instru;
		this.pointeurs.add(pointeur);
		this.pointeurActuel=0;
		this.symbole = X;
		this.color = color;
	}
	
	/** Méthode pour jouer son coup
	 * @param memoire la machine de mars
	 * @param controle le controleur du jeu
	 */
	public void play(Mars memoire,Controleur controle){
		// --- jouer le coup avec notre interpreteur --- //
		int block = returnpoint(this.pointeurActuel);
		String action = memoire.getCase(pointeurs.get(this.pointeurActuel)).getOperateur();
		Instruction cases = memoire.getCase(block);
		if(cases.getModificateurDepart()=='<'){
			cases.setAdresseDepart(cases.getAdresseDepart()-1);
		}
		if(cases.getModificateurArrive()=='<'){
			cases.setAdresseArrive(cases.getAdresseArrive()-1);
		}
		switch(action){
			case "MOV":
				Interpreteurs.getInstance().Mov(memoire,block,cases,this.symbole,this.color);
				break;
			case "ADD":
				Interpreteurs.getInstance().Add(memoire,block,cases);
				break;
			case "DAT":
				Interpreteurs.getInstance().Dat(memoire,controle,this);
				break;
			case "SUB":
				Interpreteurs.getInstance().Sub(memoire,block,cases);
				break;
			case "MUL":
				Interpreteurs.getInstance().Mul(memoire,block,cases);
				break;
			case "DIV":
				Interpreteurs.getInstance().Div(memoire,block,cases);
				break;
			case "JMP":
				Interpreteurs.getInstance().Jmp(memoire,block,cases,this);
				break;
			case "MOD":
				Interpreteurs.getInstance().Mod(memoire,block,cases);
				break;
			case "JMZ":
				Interpreteurs.getInstance().Jmz(memoire,block,cases,this);
				break;
			case "JMN":
				Interpreteurs.getInstance().Jmn(memoire,block,cases,this);
				break;
			case "DJN":
				Interpreteurs.getInstance().Djn(memoire,block,cases,this);
				break;
			case "DJZ":
				Interpreteurs.getInstance().Djz(memoire,block,cases,this);
				break;
			case "SPL":
				Interpreteurs.getInstance().Spl(memoire,block,cases,this);
				break;
			case "CMP":
				Interpreteurs.getInstance().Seq(memoire,block,cases,this);
				break;
			case "SEQ":
				Interpreteurs.getInstance().Seq(memoire,block,cases,this);
				break;
			case "SNE":
				Interpreteurs.getInstance().Sne(memoire,block,cases,this);
				break;
			case "SLT":
				Interpreteurs.getInstance().Slt(memoire,block,cases,this);
				break;
		}
		if(cases.getModificateurDepart()=='>'){
			cases.setAdresseDepart(cases.getAdresseDepart()+1);
		}
		if(cases.getModificateurArrive()=='>'){
			cases.setAdresseArrive(cases.getAdresseArrive()+1);
		}
		if(this.pointeurs.isEmpty()==false){
			setPointeur(1,memoire.taille());
		}
		prochainPointeur();
	}
	
	/** Méthode pour changer augmenter l'adresse du pointeur
	 * @param a la valeur a ajouter
	 * @param taille la taille de la mémoire
	 */
	public void setPointeur(int a,int taille){
		int point = this.pointeurs.get(this.pointeurActuel)+a;
		while(point>=taille){
			point = point-taille;
		}
		while(point<0){
			point = point+taille;
		}
		this.pointeurs.set(this.pointeurActuel,point);
	}
	
	/** Méthode pour modifier l'adresse du pointeur
	 * @param a la nouvelle valeur
	 * @param taille la taille de la mémoire
	 */
	public void clearPointeur(int a,int taille){
		int point = a;
		while(point>=taille){
			point = point-taille;
		}
		while(point<0){
			point = point+taille;
		}
		this.pointeurs.set(this.pointeurActuel,point);
	}
	
	/** Méthode pour obtenir un pointeur
	 * @param i le pointeur a récupéré
	 */
	public int returnpoint(int i){
		return pointeurs.get(i);
	}

	/** Méthode pour changer le pointeur
	 */
	public void prochainPointeur(){
		if(this.pointeurActuel+1 < pointeurs.size()){
			this.pointeurActuel ++;
		}else{
			this.pointeurActuel = 0;
		}
	}
	
	/** Je ne sais pas encore ...
	 */
	public void createPointeur(int i){
		this.pointeurs.add(i);
	}

	/** Méthode pour obtenir la liste des pointeurs
	 * @return la liste des pointeurs
	 */
	public List<Integer> getPointeurs(){
		return this.pointeurs;
	}

	/** Méthode pour obtenir la liste d'instruction du joueur
	 * @return la liste des instruction du joueur
	 */
	public List<Instruction> getCoups(){
		return this.coups;
	}
	
	/** Méthode de suppression de pointeur et pour savoir si le joueur peut encore jouer
	* @return si le joueur peut encore jouer ou si il est mort
	*/
	public boolean deletePointeur(){
		this.pointeurs.remove(this.pointeurActuel);
		if(this.pointeurs.isEmpty()==true){
			return false;
		}else{
			if(this.pointeurActuel==0){
				this.pointeurActuel = this.pointeurs.size()-1;
			}else{
				this.pointeurActuel-=1;
			}
		}
		return true;
	}

}
