import java.util.ArrayList;
import java.util.List;

import interpret.*;
import Memoire.*;

import interpret.*;
import Memoire.*;

public class Test extends Thread{

    public static void main(String [] args){
        String imp = "../fichiers/TEST.nprt";
        String lance = "../fichiers/TEST2.nprt";
        ArrayList<String> intruct = new ArrayList<String>();
        intruct.add(imp);
        intruct.add(lance);
        Interpreteur inter = new Interpreteur(intruct);
        List<Instruction> list1 = inter.joueurs().get(0).traducteur();
        List<Instruction> list2 = inter.joueurs().get(1).traducteur();
        
		Player joueur1 = new Player("joueur1",list1,1,"X",150);
		Player joueur2 = new Player("joueur2",list2,1,"0",200);
		
		List<Player> listplayer = new ArrayList<>();
		listplayer.add(joueur1);
		listplayer.add(joueur2);
		
		Mars memoire = new Mars(32);
		memoire.initMars();
		
		Controleur controle = new Controleur(memoire,listplayer);
		
		//memoire.drawMars();
		//System.out.println("");
		while(controle.isterminal()==false){
			//System.out.print("\033[H\033[2J");  
			//System.out.flush(); 
			controle.getPlayer().play(memoire,controle);
			//memoire.drawMars();
			controle.changementjoueur();
			//System.out.println("");
			try{
				Thread.sleep(40);
			}catch(InterruptedException e){
				System.out.println("erreur !");
			}
			//System.out.println(controle.isterminal());
		}
		System.out.println("fin du jeu");
	}
}
