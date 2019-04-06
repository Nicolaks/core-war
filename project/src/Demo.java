import java.util.*;

import interpret.*;
import memoire.*;
import graphique.*;

public class Demo extends Thread{

    public static void main(String [] args){
		Choix menu = new Choix();
		//tant que l'utilisateur n'a pas fini son choix de warrior
		while(menu.getPret()==false){
			try {
                 Thread.sleep(1);
            } catch (InterruptedException e) {
            }
		}
		ArrayList<Player> listplayer = new ArrayList<>();
		ArrayList<String> joueur = menu.retourJoueur();
		//realisation de la liste de player
		for(int i=0;i<joueur.size();i++){
			int random = (int)Math.random() * (10000);
			Player joueur1 = new Player(joueur.get(i),"joueur"+i,""+random);
			listplayer.add(joueur1);
		}
		int compteur = 0;
		menu.destruction();
		Mars memoire = new Mars(90, true);
		Controleur controle = new Controleur(memoire,listplayer);
		// lancement de la boucle de jeu
		while(controle.isterminal()==false){
			controle.getPlayer().play(memoire,controle);
			controle.changementjoueur();
			try{
				Thread.sleep(1);
			}catch(InterruptedException e){
				System.out.println("erreur !");
			}
			compteur+=1;
		}
		//affichage pour connaitre quelques détails de la bataille
		System.out.println("fin du jeu");
		System.out.println("nombre de coups = "+compteur);
		System.out.println("victoire de : "+listplayer.get(0).getName());
		memoire.affichefin(listplayer.get(0).getName());
	}
}
