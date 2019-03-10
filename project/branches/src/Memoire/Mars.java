package Memoire;

import java.util.ArrayList;
import java.util.List;

import interpret.*;
import Graphique.*;

public class Mars{
	private Instruction[] memoire;
	public String[] affiche;
	private int taille;
	private fenetre affichage;

	/** Constructeur de la classe qui initialise les attributs
	 * @param n la taille de la mémoire
	 */
	public Mars(int n){
		this.memoire = new Instruction[n*n];
		this.affiche = new String[n*n];
		this.taille=n*n;
		this.affichage = new fenetre(n);
	}

	/** Méthode pour initialiser la mémoire/grille/tableau
	 */
	public void initMars(){
		for(Integer i = 0; i<memoire.length; i++){
			memoire[i] = new Instruction("DAT",'$',1,'$',0);
		}
	}
	
	/** Méthode pour obtenir l'instruction dans une case x
	 * @param l'adresse de la case dont on veux le contenu
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
	 * @param y le symbole
	 */
	public void modifyAffiche(int x, String y,int color){
		this.affiche[x]=y;
		this.affichage.changeColor(x,color);
		this.affichage.repaint();
	}

	/** Méthode pour afficher l'état du jeu
	 */
	public void drawMars(){ // pour console
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
}
