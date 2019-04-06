package genetics.newGeneration;
import java.util.ArrayList;
import java.util.Random;
import memoire.*;
import interpret.*;
import genetics.population.*;

public class test{
    public static void main(String [] args){
        Popinit pop = new Popinit("fichiers");
        ArrayList<ArrayList> listPopulation = pop.parserToInstruction();
        ArrayList<Player> listJoueur = new ArrayList<Player>();
        for(int i = 0; i < listPopulation.size(); i++){
            listJoueur.add(new Player(listPopulation.get(i), false));
        }
        reproduction reprod = new reproduction(listJoueur,49);
        ArrayList<Instruction> cas = reprod.reprod().getCoups();
        Ecriture ecrire = new Ecriture(cas,"cas de test");
        ecrire.CreerDossier();
        ecrire.creerfichier("dossier test");

    }
}
