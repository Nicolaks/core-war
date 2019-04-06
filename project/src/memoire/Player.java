package memoire;

import java.util.*;

import interpret.*;

public class Player{
	private String nom;
	private ArrayList<Instruction> coups;
	private ArrayList<Integer> pointeurs = new ArrayList(Arrays.asList(1));
	private Interpreteur inter;
	private Random r = new Random();
	private int pointeurActuel = 0;
	private String symbole;
	private int color[] = {255,255,255};
	private int score = 1;
	private boolean alreadyPlay = false;

	/** Constructeur du joueur avec initialisation des attributs
	 * @param fichier le nom du ficher
	 * @param nom nom du joueur
	 * @param X son symbol
	 */
	public Player(String fichier,String nom,String X){

		String aConvertir = "../fichiers/"+fichier;
		ArrayList<String> intruct = new ArrayList<String>();
        intruct.add(aConvertir);
        inter = new Interpreteur(intruct);

		this.nom = fichier;
		this.coups = inter.joueurs().get(0).traducteur();
		this.symbole = X;
		chargementcouleur();
	}

	/** Constructeur du joueur avec initialisation des attributs
	 * @param coups liste d'instruction du joueur
	 * @param alreadyPlay si il a jouer
	 */
	public Player(ArrayList<Instruction> coups, boolean alreadyPlay){
		this.coups = coups;
	 	this.nom = ""+this.hashCode();
		this.symbole = this.nom;
		chargementcouleur();
	}

	/** Constructeur du joueur avec initialisation des attributs
	 * @param coups liste d'instruction du joueur
	 */
	public Player(ArrayList<Instruction> coups){
		this.coups = coups;
	 	this.nom = ""+this.hashCode();
		this.symbole = this.nom;
		chargementcouleur();
	}

	/**
	 * Méthode de changement de couleur
	 */
	public void chargementcouleur(){
		this.color[0] = r.nextInt(255);
		this.color[1] = r.nextInt(255);
		this.color[2] = r.nextInt(255);
	}

	/**
	 * Méthode de réinitialisation de notre liste de pointeur
	 */
	public void restartpointeur(){
		this.pointeurs = new ArrayList(Arrays.asList(1));
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
		memoire.modifyAffiche(block,symbole,color);
		switch(action){
			case "MOV":
				Interpreteurs.getInstance().Mov(memoire,block,cases,this);
				break;
			case "ADD":
				Interpreteurs.getInstance().Add(memoire,block,cases);
				break;
			case "DAT":
				Interpreteurs.getInstance().Dat(memoire,block,controle,this);
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
			if(!action.equals("JMP")){
				setPointeur(1,memoire.taille());
			}
		}
		prochainPointeur();
	}

	/**Méthode pour obtenir le nom du player
	 * @return le nom du player
	 */
	public String getName(){
		return this.nom;
	}

	/**Méthode pour obtenir le symbol du joueur
	 * @return le nom du player
	 */
	public String getSymbol(){
		return this.symbole;
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
	 * @return le pointeur a cette emplacement
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

	/**Méthode d'ajout d'un nouveau pointeur (limite par nous du nombre de pointeur)
	 * @param i la valeur à ajouter dans la liste des pointeurs
	 */
	public void createPointeur(int i){
		if(this.pointeurs.size()<100000){
			this.pointeurs.add(i);
		}
	}

	/** Méthode pour obtenir la liste des pointeurs
	 * @return la liste des pointeurs
	 */
	public ArrayList<Integer> getPointeurs(){
		return this.pointeurs;
	}

	/** Méthode pour obtenir la liste d'instruction du joueur
	 * @return la liste des instruction du joueur
	 */
	public ArrayList<Instruction> getCoups(){
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

	/**Méthode pour incrémenter le score
	 * @param score le score à ajouter
	 */
	public void setScore(int score){
		this.score += score;
	}

	/**Méthode pour recommencer à 1 le score
	 */
	public void resetScore(){
		this.score = 1;
	}

	/**Méthode obtenir le score
	 * @return le score
	 */
	public int getScore(){
		return this.score;
	}

	/**Méthode pour vidé la liste des pointeur et remettre avec un seul pointeur
	 */
	public void resetpointeur(){
			this.pointeurs.clear();
			this.pointeurs = new ArrayList(Arrays.asList(1));
			this.pointeurActuel = 0;
	}

	/**Méthode pour modifié si le joueur a déjà jouer (algorithme génétique)
	 * @param bool la valeur a mettre a alreadyPlay
	 */
	public void setAlreadyPlay(boolean bool){
		this.alreadyPlay = bool;
	}

	/**Méthode pour obtenir si le joueur a déjà jouer (algorithme génétique)
	 * @return si le joueur a déjà jouer
	 */
	public boolean getAlreadyPlay(){
		return this.alreadyPlay;
	}

	/**Méthode pour obtenir la couleur du player pour l'interface
	 * @return la couleur (RGB)
	 */
	public int[] getColor(){
		return this.color;
	}

	/**Méthode pour modifié l'affichage en cas d'appel a la place de l'adressage de la classe
	 * @return retourne la liste d'instruction
	 */
	public String toString(){
		return ""+this.coups;
	}
}
