package genetics.newGeneration;

import java.util.ArrayList;
import memoire.*;
import genetics.population.*;
import genetics.mutation.*;

public class Demo{
    
    public static void main(String [] agrs){

        ArrayList<Player> ls = new ArrayList<Player>();
        Popinit pop = new Popinit("fichiers");
        ArrayList<ArrayList> listpop = pop.parserToInstruction();
		float x = (float)0.5;
        Mutation mutatron2000 = new Mutation(x,x,x,x);
    }
}
