package genetics.evaluation;
import genetics.population.*;
import java.util.Collections.*;
import java.util.*;
import memoire.*;
import interpret.*;
import java.lang.Thread.*;


public class Evaluation {

  private ArrayList<ArrayList> listPopulation;
  private Mars memoire;
  private ArrayList<Player> listJoueur;
  private int secondes;

  /**
   * Constructeur de la classe
   * @param secondes le temps maximal d'un combat
   */
  public Evaluation(int secondes){
     Popinit pop = new Popinit("fichiers");
     listPopulation = pop.parserToInstruction();
     memoire = new Mars(90, false);
     listJoueur = instructionToPlayer();
     this.secondes = secondes;
  }

  /**
   * Méthode choix aléatoire d'un player
   * @param listPlayer la liste ou il faut selectionner le player aléatoirement 
   * @return retourne le joueur choisi
   */
  public Player choosePlayer(ArrayList<Player> listPlayer){
    Random r2 = new Random();
    return listPlayer.get(r2.nextInt(listPlayer.size()));
  }

  /**
   * Méthode qui transforme la liste de liste d'instruction en liste de players
   * @return liste de player qui on appris leurs liste d'instruction
   */
  public ArrayList<Player> instructionToPlayer(){
    ArrayList<Player> listJoueur = new ArrayList<Player>();
    for(int i = 0; i < listPopulation.size(); i++){
      listJoueur.add(new Player(this.listPopulation.get(i), false));
    }
    return listJoueur;
  }

  /**
  * Méthode qui joue l'évaluation
  * @param k qui sera le nombre de joueurs contre qui il va jouer.
  * @param joueur1 qui est le joueur contre qui on veut faire se battre les autres.
  * @param listJoueur qui est l'ArrayList des joueurs contre qui le joueur1 doit se battre.
  */
  public void play(int k, Player joueur1, ArrayList<Player> listJoueur){
    Collections.shuffle(listJoueur);
    long t = 0;
    long timeEnd = 0;
    for(int z = 0; z < k; z++){
      ArrayList<Player> combat = new ArrayList<>();
      memoire.restart();
      combat.add(joueur1);
      combat.add(listJoueur.get(z));
      Controleur controle = new Controleur(memoire, combat); // faire un restart (moins fort)
      t = System.currentTimeMillis();
      timeEnd = t+ this.secondes*1000;//Permet de mettre un temps max de 20 secondes et mettre en paramètre le temps
      while(System.currentTimeMillis() < timeEnd && controle.isterminal() == false){
        controle.getPlayer().play(memoire,controle);
        controle.changementjoueur();
      }
      /*Mise à jour des scores*/
      if(controle.isterminal() == true){
        if(joueur1.getName() == combat.get(0).getName()){
          joueur1.setScore(memoire.taille());
          listJoueur.get(z).setScore(0);

        }
        else{
          listJoueur.get(z).setScore(memoire.taille());
          joueur1.setScore(0);
        }
        joueur1.restartpointeur();
        listJoueur.get(z).restartpointeur();
      }
      /*Donne à chaque joueurs le pourcentage de cases dans la mémoire qu'il a.*/
      else{
        int score1 = 0;
        int score2 = 0;
        for(int i = 0; i < memoire.taille(); i++){
          if (memoire.getAffiche(i) == joueur1.getSymbol() ) {
            score1 += 1;
          }
          else{
            score2 += 1;
          }
        }
        listJoueur.get(z).setScore(score1);
        joueur1.setScore(score2);
      }
    }
  }

  /**
  * Méthode qui renvoie la liste des score au lieu de l'adresse de la classe
  * @return la liste des scores
  */
  @Override
  public String toString(){
    String res = " ";
    String esp = " | ";
    for(int i = 0; i < listJoueur.size(); i++){
       res += listJoueur.get(i).getScore() + esp ;
    }
    return res;
  }

  /**
  * Méthode qui renvoie la liste des joueurs
  * @return la liste des joueurs
  */
  public ArrayList<Player> getList(){
    return listJoueur;
  }



}
