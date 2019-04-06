import java.util.*;

import interpret.*;
import memoire.*;

import genetics.mutation.*;

public class TestMutation extends Thread{

    public static void main(String [] args){
		Player joueur1 = new Player("worm.nprt","joueur1","X");
		System.out.println(joueur1);
		ArrayList<Player> list = new ArrayList<Player>();
		list.add(joueur1);
		
		System.out.println("\n\n\n");
		
		Mutation mutatron2000 = new Mutation(1,1,1,1);

		mutatron2000.mutationSolo(joueur1,1);
		mutatron2000.mutationMultiple(list);
		System.out.println(mutatron2000.mutationSolo(joueur1,1));
		
		System.out.println("\n\n\n");
		
		System.out.println("Mutation Terminé !");
	}
}
