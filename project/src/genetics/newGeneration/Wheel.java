package genetics.newGeneration;
import java.util.*;
import genetics.population.*;
import genetics.evaluation.*;
import memoire.Player;

public class Wheel{

  /**
  * Constructeur de la classe
  */
  public Wheel(){

  }

  /**Méthode de roue biaisé
  *
  * @param listJoueur liste des players sur lequel faire notre roue biaisé
  * @param random qui est un entier aléatoire
  * @return res qui sera un res et sera la position du score à renvoyer.
  * Méthode qui permet de faire tourner la wheel et qui parcourt le tableau
  * et sort le plus souvent le double le plus important.
  *
  */
  public int spin(ArrayList<Player> listJoueur, Random random){
    int res = 0;

    for(int i = 1; i < listJoueur.size(); i++){
      if( random.nextInt() <= (listJoueur.get(i).getScore()) / listJoueur.get(0).getScore() && listJoueur.get(i).getScore() != 0 && listJoueur.get(0).getScore() != 0){
        res = i;
      }
    }
    return res;
  }

  
}
