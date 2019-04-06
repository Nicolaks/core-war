package memoire;

import interpret.*;

public class Interpreteurs{

	private static Interpreteurs self = new Interpreteurs();

	/** Constructeur du Singleton
	 */
	private Interpreteurs(){
	}

	/** Méthode qui retourne notre interpreteur
	 * @return retourne l'interpreteur
	 */
	public static Interpreteurs getInstance(){
		return self;
	}

	/** Méthode pour jouer un MOV
	 * @param partie la machine de mars
	 * @param pointeur le pointeur du joueur
	 * @param i l'instruction indiquée par le pointeur
	 * @param player le joueur
	 */
	public void Mov(Mars partie,int pointeur,Instruction i,Player player){
		int debut = emplacement(partie,i.getAdresseDepart(),i.getModificateurDepart(),pointeur);
		Instruction A = partie.getCase(debut);
		int dest = emplacement(partie,i.getAdresseArrive(),i.getModificateurArrive(),pointeur);
		partie.modifyAffiche(dest,player.getSymbol(),player.getColor());
		partie.modifyCase(A,dest);
	}

	/** Méthode pour jouer un JMP
	 * @param partie la machine de mars
	 * @param pointeur le pointeur du joueur
	 * @param i l'instruction indiquée par le pointeur
	 * @param player le joueur
	 */
	public void Jmp(Mars partie,int pointeur,Instruction i,Player player){
		int distance = emplacement(partie,i.getAdresseDepart(),i.getModificateurDepart(),pointeur);
		player.clearPointeur(distance,partie.taille());
	}

	/** Méthode en cas de DAT
	 * @param partie la machine de mars
	 * @param pointeur lieu de jeu
	 * @param controle le controleur de la partie
	 * @param player le joueur
	 */
	public void Dat(Mars partie,int pointeur,Controleur controle, Player player){
		boolean vivant = player.deletePointeur();
		if(vivant == false){
			controle.deleteJoueur();
		}else{
			player.setPointeur(-1,partie.taille());
		}
	}

	/** Méthode pour jouer un ADD
	 * @param partie la machine de mars
	 * @param pointeur le pointeur du joueur
	 * @param i l'instruction indiquée par le pointeur
	 */
	public void Add(Mars partie,int pointeur,Instruction i){
		int dest = emplacement(partie,i.getAdresseArrive(),i.getModificateurArrive(),pointeur);
		int val=0;
		if(i.getModificateurDepart() == '#' || i.getModificateurDepart()== '$'){
			val = i.getAdresseDepart();
		}else{
			int taille = partie.taille();
			val = valeurCase(partie,i,pointeur,0);
		}
		int valDest = partie.getCase(dest).getAdresseDepart();
		partie.getCase(dest).setAdresseDepart(InGrid(valDest+val,partie.taille()));

	}

	/** Méthode pour jouer un SUB
	 * @param partie la machine de mars
	 * @param pointeur le pointeur du joueur
	 * @param i l'instruction indiquée par le pointeur
	 */
	public void Sub(Mars partie,int pointeur,Instruction i){
		int dest = emplacement(partie,i.getAdresseArrive(),i.getModificateurArrive(),pointeur);
		int val=0;
		if(i.getModificateurDepart() == '#' || i.getModificateurDepart()== '$'){
			val = i.getAdresseDepart();
		}else{
			int taille = partie.taille();
			val = valeurCase(partie,i,pointeur,0);
		}
		int valDest = partie.getCase(dest).getAdresseDepart();
		partie.getCase(dest).setAdresseDepart(InGrid(valDest-val,partie.taille()));
	}

	/** Méthode pour jouer un MUL
	 * @param partie la machine de mars
	 * @param pointeur le pointeur du joueur
	 * @param i l'instruction indiquée par le pointeur
	 */
	public void Mul(Mars partie,int pointeur,Instruction i){
		int dest = emplacement(partie,i.getAdresseArrive(),i.getModificateurArrive(),pointeur);
		int val=0;
		if(i.getModificateurDepart() == '#' || i.getModificateurDepart()== '$'){
			val = i.getAdresseDepart();
		}else{
			int taille = partie.taille();
			val = valeurCase(partie,i,pointeur,0);
		}
		int valDest = partie.getCase(dest).getAdresseDepart();
		partie.getCase(dest).setAdresseDepart(InGrid(valDest*val,partie.taille()));
	}

	/** Méthode pour jouer un DIV
	 * @param partie la machine de mars
	 * @param pointeur le pointeur du joueur
	 * @param i l'instruction indiquée par le pointeur
	 */
	public void Div(Mars partie,int pointeur,Instruction i){
		int dest = emplacement(partie,i.getAdresseArrive(),i.getModificateurArrive(),pointeur);
		int val=0;
		if(i.getModificateurDepart() == '#' || i.getModificateurDepart()== '$'){
			val = i.getAdresseDepart();
		}else{
			int taille = partie.taille();
			val = valeurCase(partie,i,pointeur,0);
		}
		int valDest = partie.getCase(dest).getAdresseDepart();
		partie.getCase(dest).setAdresseDepart(InGrid(valDest/val,partie.taille()));
	}

	/** Méthode pour jouer un MOD
	 * @param partie la machine de mars
	 * @param pointeur le pointeur du joueur
	 * @param i l'instruction indiquée par le pointeur
	 */
	public void Mod(Mars partie,int pointeur,Instruction i){
	        int dest = emplacement(partie,i.getAdresseArrive(),i.getModificateurArrive(),pointeur);
	        int val=0;
	        if(i.getModificateurDepart() == '#' || i.getModificateurDepart()== '$'){
	            val = i.getAdresseDepart();
	        }else{
	            int taille = partie.taille();
				val = valeurCase(partie,i,pointeur,0);
	        }
	        int valDest = partie.getCase(dest).getAdresseDepart();
	        partie.getCase(dest).setAdresseDepart(InGrid(valDest%val,partie.taille()));
	    }

	/** Méthode pour jouer un JMZ
	 * @param partie la machine de mars
	 * @param pointeur le pointeur du joueur
	 * @param i l'instruction indiquée par le pointeur
	 * @param player le joueur
	 */
	public void Jmz(Mars partie,int pointeur,Instruction i,Player player){

		if (valeurCaseArrive(partie,i,pointeur,0)==0){
			int distance = emplacement(partie,i.getAdresseDepart(),i.getModificateurDepart(),pointeur);
			player.clearPointeur(distance-1,partie.taille());
		}
	}

	/** Méthode pour jouer un JMN
	 * @param partie la machine de mars
	 * @param pointeur le pointeur du joueur
	 * @param i l'instruction indiquée par le pointeur
	 * @param player le joueur
	 */
	public void Jmn(Mars partie,int pointeur,Instruction i,Player player){

		if (valeurCaseArrive(partie,i,pointeur,0)!=0){
			int distance = emplacement(partie,i.getAdresseDepart(),i.getModificateurDepart(),pointeur);
			player.clearPointeur(distance-1,partie.taille());
		}
	}

	/** Méthode pour jouer un DJN
	 * @param partie la machine de mars
	 * @param pointeur le pointeur du joueur
	 * @param i l'instruction indiquée par le pointeur
	 * @param player le joueur
	 */
	public void Djn(Mars partie,int pointeur,Instruction i,Player player){
		i.setAdresseArrive(i.getAdresseArrive()-1);
		if (valeurCase(partie,i,pointeur,0)!=0){
			int distance = emplacement(partie,i.getAdresseArrive(),i.getModificateurArrive(),pointeur);
			player.clearPointeur(distance-1,partie.taille());
		}
	}

	/** Méthode pour jouer un DJZ
	 * @param partie la machine de mars
	 * @param pointeur le pointeur du joueur
	 * @param i l'instruction indiquée par le pointeur
	 * @param player le joueur
	 */
	public void Djz(Mars partie,int pointeur,Instruction i,Player player){
		i.setAdresseDepart(i.getAdresseDepart()-1);

		if (valeurCase(partie,i,pointeur,0)==0){
			int distance = emplacement(partie,i.getAdresseArrive(),i.getModificateurArrive(),pointeur);
			player.clearPointeur(distance-1,partie.taille());
		}
	}

	/** Méthode pour jouer un SPL
	 * @param partie la machine de mars
	 * @param pointeur le pointeur du joueur
	 * @param i l'instruction indiquée par le pointeur
	 * @param player le joueur
	 */
	public void Spl(Mars partie,int pointeur, Instruction i, Player player){
		int dest = emplacement(partie,i.getAdresseDepart(),i.getModificateurDepart(),pointeur);
		player.createPointeur(dest);
	}

	/** Méthode pour jouer un SEQ/CMP
	 * @param partie la machine de mars
	 * @param pointeur le pointeur du joueur
	 * @param i l'instruction indiquée par le pointeur
	 * @param player le joueur
	 */
	public void Seq(Mars partie,int pointeur,Instruction i,Player player){
		int A = emplacement(partie,i.getAdresseDepart(),i.getModificateurDepart(),pointeur);
		int B = emplacement(partie,i.getAdresseArrive(),i.getModificateurArrive(),pointeur);
		if (A==B){
			player.setPointeur(1,partie.taille());
		}
	}

	/** Méthode pour jouer un SNE
	 * @param partie la machine de mars
	 * @param pointeur le pointeur du joueur
	 * @param i l'instruction indiquée par le pointeur
	 * @param player le joueur
	 */
	public void Sne(Mars partie,int pointeur,Instruction i,Player player){
		int A = emplacement(partie,i.getAdresseDepart(),i.getModificateurDepart(),pointeur);
		int B = emplacement(partie,i.getAdresseArrive(),i.getModificateurArrive(),pointeur);
		if (A!=B){
			player.setPointeur(1,partie.taille());
		}
	}

	/** Méthode pour jouer un SNE
	 * @param partie la machine de mars
	 * @param pointeur le pointeur du joueur
	 * @param i l'instruction indiquée par le pointeur
	 * @param player le joueur
	 */
	public void Slt(Mars partie,int pointeur,Instruction i,Player player){
		int A = emplacement(partie,i.getAdresseDepart(),i.getModificateurDepart(),pointeur);
		int B = emplacement(partie,i.getAdresseArrive(),i.getModificateurArrive(),pointeur);
		if (A<B){
			player.setPointeur(1,partie.taille());
		}
	}

	/** Méthode obtenir le numéro de case ou aller
	 * @param partie la machine de mars
	 * @param valeur la valeur du coup
	 * @param type le type de coup
	 * @param pointeur le pointeur du joueur
	 * @return l'adresse de la case demandée
	 */
	public int emplacement(Mars partie,int valeur, char type, int pointeur){
		int taille = partie.taille();
		int pos = InGrid(pointeur+valeur,taille);
		int x = 0;
		switch(type){
			case '#':
				x = InGrid(valeur,taille);
				break;
			case '@':
				Instruction fille = partie.getCase(pos);
				int place = valeurCase(partie,fille,pointeur,0)+pointeur;
				x = InGrid(place,taille);
				break;
			case '>':
				Instruction fille2 = partie.getCase(pos);
				int places = valeurCase(partie,fille2,pointeur,0)+pointeur;
				x = InGrid(places,taille);
				break;
			case '<':
				Instruction fille3 = partie.getCase(pos);
				int places2 = valeurCase(partie,fille3,pointeur,0)+pointeur;
				x = InGrid(places2,taille);
				break;
			case '$':
				x = InGrid(pos,taille);
				break;
		}
		return x;
	}

	/** Méthode pour obtenir la valeur de depart d'une case avec un type différent de @
	 * @param partie la machine de mars
	 * @param i l'instruction indiquée par le pointeur
	 * @param pointeur le pointeur du joueur
	 * @param compte pour limité le nombre de vérification (en cas de @0 ou saut infini)
	 * @return la valeur de la dernière case
	 */
	public int valeurCase(Mars partie,Instruction i, int pointeur, int compte){
		if(i.getModificateurDepart()!='@' && i.getModificateurDepart()!='>' && i.getModificateurDepart()!='<'){
			return i.getAdresseDepart();
		}else{
			if(compte==100){
				return i.getAdresseDepart();
			}
			int pos = InGrid(pointeur+i.getAdresseDepart(),partie.taille());
			Instruction fille = partie.getCase(pos);
			return valeurCase(partie,fille,pos,compte+1);
		}
	}

	/** Méthode pour obtenir la valeur d'arrivé d'une case avec un type différent de @
	 * @param partie la machine de mars
	 * @param i l'instruction indiquée par le pointeur
	 * @param pointeur le pointeur du joueur
	 * @param compte pour limité le nombre de vérification (en cas de @0 ou saut infini)
	 * @return la valeur de la dernière case
	 */
	public int valeurCaseArrive(Mars partie,Instruction i, int pointeur,int compte){
		if(i.getModificateurArrive()!='@' && i.getModificateurArrive()!='>' && i.getModificateurArrive()!='<'){
			return i.getAdresseArrive();
		}else{
			if(compte==100){
				return i.getAdresseArrive();
			}
			int pos = InGrid(pointeur+i.getAdresseDepart(),partie.taille());
			Instruction fille = partie.getCase(pos);
			return valeurCase(partie,fille,pos,compte+1);
		}
	}

	/** Méthode pour ne pas sortir du tableau
	 * @param x la valeur à vérifier
	 * @param taille la taille de la mémoire
	 * @return une bonne valeur
	 */
	public int InGrid(int x, int taille){
		while(x>=taille){
			x = x-taille;
		}
		while(x<0){
			x = x+taille;
		}
		return x;
	}
}
