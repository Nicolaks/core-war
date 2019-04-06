package memoire;

import java.util.*;

import interpret.*;
import graphique.*;

public class Mars{
	private Instruction[] memoire;
	private String[] affiche;
	private int taille;
	private Fenetre affichage;
	private boolean active;

	/** Constructeur de la classe qui initialise les attributs
	 * @param n la taille de la mémoire
	 * @param activation si on active ou non le graphique
	 */
	public Mars(int n, boolean activation){
		this.memoire = new Instruction[n*n];
		this.affiche = new String[n*n];
		this.taille=n*n;
		this.active = activation;
		if( activation != false){
				this.affichage = new Fenetre(n);
				initMars();
		}

		initMars();
	}

	/** Méthode pour initialiser la mémoire/grille/tableau
	 */
	public void initMars(){
		for(Integer i = 0; i<memoire.length; i++){
			memoire[i] = new Instruction("DAT",'$',1,'$',0);
		}
	}

	public String getAffiche(int i){
		return this.affiche[i];
	}

	/** Méthode de remise à 0 de la mémoire
	 */
	public void restart(){
		initMars();
		for(int i = 0; i<this.memoire.length; i++){
			this.memoire[i]=new Instruction("DAT",'$',1,'$',0);
			this.affiche[i]=null;
		}
	}

	/** Méthode pour obtenir l'instruction dans une case x
	 * @param x l'adresse de la case dont on veux le contenu
	 * @return l'instruction de la case choisi
	 */
	public Instruction getCase(int x){
		return memoire[x];
	}

	/** Méthode pour obtenir la taille de la mémoire
	 * @return la taille du tableau
	 */
	public int taille(){
		return this.taille;
	}

	/** Méthode pour modifier un élément de la mémoire
	 * @param i l'instruction à ajouter
	 * @param x l'adresse de la case ou la mettre
	 */
	public void modifyCase(Instruction i, int x){
		this.memoire[x]=i;
	}

	/** Méthode pour ajouter le symbol de la personne qui viens de jouer pour l'affichage
	 * @param x l'adresse de la mémoire
	 * @param y le symbole du joueur qui joue
	 * @param color la couleur du joueur qui joue
	 */
	public void modifyAffiche(int x, String y,int[] color){
		this.affiche[x]=y;
		if(this.active != false){
			this.affichage.changeColor(x,color);
			this.affichage.repaint();
		}

	}

	/** Méthode pour afficher l'état du jeu
	 */
	// Méthode pour la console de début
	public void drawMars(){
		for(int i = 0; i < this.affiche.length; i++) {
			if(affiche[i]==null){
				System.out.print(" - ");
			}else{
				System.out.print(" "+affiche[i]+" ");
			}
	  		if((i+1)%32==0){
	  			System.out.println();
	  		}
  		}
  	}

  	/**
	 * Méthode d'affichage du gagnant
	 * @param mot le nom du gagnant
	 */
	public void affichefin(String mot){
		if(active==true){
			this.affichage.changetext(mot);
		}
	}
}
