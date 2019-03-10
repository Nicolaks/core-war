package genetics;
//https://github.com/dwdyer/watchmaker/blob/master/framework/src/java/main/org/uncommons/watchmaker/framework/selection/RouletteWheelSelection.java
// https://stackoverflow.com/questions/298301/roulette-wheel-selection-algorithm
import java.util.*;


public class Wheel{

  double[] c; //Création d'un tableau de double.
  double total; //Création d'un double total.
  Random random;


  /**
  * Constructeur qui prend en paramètre un tableau de double, (mais autait pu être une liste)
  */
  public Wheel(double[] n){
      random = new Random();
      total = 0;
      // Initialise le tableau c à la longueur n+1.
      c = new double[n.length+1];
      c[0] = 0;

      // Rajoute dans c[i+1] la valeur de c[i] + la valeur de n[i].
      for( int i = 0; i < n.length; i++){
        c[i+1] = c[i] + n[i];
        total += n[i];
      }
  }


  public int run(){
    double r = random.nextDouble() * total;

    int a = 0;
    int b = c.length - 1;
    while (b - a > 1){
      int moyenne = (a + b) / 2;
      if (c[moyenne] > r) {
        b = moyenne;
      }
      else{
        a = moyenne;
      }

    }
    return a;
  }


}
