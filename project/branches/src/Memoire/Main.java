package Memoire;

import java.util.List;
import java.util.ArrayList;
//import java.util.Thread;

import interpret.*;

public class Main extends Thread{

	public static void main(String[] args){
		// --- mise en place des joueurs et listes d'instructions --- //
		Instruction ordre1 = new Instruction("MOV",'$',0,'$',2);
		Instruction ordre2 = new Instruction("ADD",'$',-1,'$',1);
		List<Instruction> list = new ArrayList<>();
		list.add(ordre1);
		list.add(ordre2);
		Player joueur1 = new Player("joueur1",list,1,"X",150);
		Player joueur2 = new Player("joueur2",list,1,"0",200);
		List<Player> listplayer = new ArrayList<>();
		listplayer.add(joueur1);
		listplayer.add(joueur2);
		// --- Initialisation de la machine de Mars --- //
		Mars memoire = new Mars(1024);
		memoire.initMars();
		// --- mise en route du controleur --- //
		Controleur controle = new Controleur(memoire,listplayer);
		// --- Placement des pointeurs et des instructions au hasard dans les parties de la mémoire --- //
		memoire.drawMars();
		System.out.println("");
		while(controle.isterminal()==false){
			//System.out.print("\033[H\033[2J");  
			//System.out.flush(); 
			controle.getPlayer().play(memoire,controle);
			memoire.drawMars();
			controle.changementjoueur();
			//System.out.println("");
			try{
				Thread.sleep(50);
			}catch(InterruptedException e){
				System.out.println("erreur !");
			}
			System.out.println(controle.isterminal());
		}
		System.out.println("fin du jeu");
	}

}
