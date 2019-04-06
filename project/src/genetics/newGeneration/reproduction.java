package genetics.newGeneration;
import java.util.ArrayList;
import java.util.Random;
import memoire.*;
import interpret.*;
public class reproduction{

    private ArrayList<Player> liste;
    private int limite;

  /**Constructeur de la classe
   * @param liste2 la liste des players a reproduire
   * @param limite notre limite de reproduction
   */
    public reproduction(ArrayList<Player> liste2,int limite){
        this.liste=liste2;
        this.limite = limite;
    }

  /**Méthode pour selectionner un player au hasard
   * @param k nombre de player
   * @return le player a cette position
   */
    public Player selectionreproduction(int k){
        int n = (int)(Math.random()*k);
        return(this.liste.get(n));
    }

  /**Méthode pour reproduire 2 players choisi au hasard
   * @return la reproduction des 2 players
   */
    public Player reprod(){
        Player j1 = selectionreproduction(50-this.limite);
        Player j2 = selectionreproduction(50-this.limite);
        int testnewenfant = (int)(Math.random()*3);
        //System.out.println(j1);
        //System.out.println(j2);
        //System.out.println(testnewenfant);
        ArrayList<Instruction> bis = new ArrayList<Instruction>();
        if(testnewenfant == 0 || testnewenfant == 1){
            int mitad = j1.getCoups().size()/2;
            int mitad2 = j2.getCoups().size()/2;
            if(mitad == 0){
                bis.add(j1.getCoups().get(0));
            }
            for (int i=0;i<mitad;i++){
                bis.add(j1.getCoups().get(i));
            }
            if(mitad2 == 0){
                bis.add(j2.getCoups().get(0));
            }
            for (int j=0;j<mitad2;j++){
                bis.add(j2.getCoups().get(j+mitad2));
            }
            Player ki = new Player(bis);
            return(ki);
        }else{
            int mitad3 = j1.getCoups().size()/4*3;
            int mitad4 = j2.getCoups().size()/4;
            if(mitad3 == 0){
                bis.add(j1.getCoups().get(0));
            }
            for(int k=0;k<mitad3;k++){
                bis.add(j1.getCoups().get(k));
            }
            if(mitad4 != 0){
                for(int f=0;f<mitad4;f++){
                    bis.add(j2.getCoups().get(f+mitad4-1));
                }
            }else{
                bis.add(j2.getCoups().get(0));
            }
            Player ki = new Player(bis);
            return(ki);
        }
    }

  /**Méthode pour faire la reproduction intégrale
   * @return la liste des reproductions
   */
    public ArrayList<Player> nouvellegen(){
        ArrayList<Player> liste = new ArrayList<Player>();
        for (int i=0;i<1;i++){
            liste.add(this.reprod());
        }
        for (int j=0;j<this.limite;j++){
            liste.add(this.liste.get(j));
        }
        return liste;
    }



}
