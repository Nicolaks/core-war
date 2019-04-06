package genetics.newGeneration;

import java.util.*;
import genetics.evaluation.*;
import memoire.*;
import java.util.Random;

public class Selection {
	private Random random = new Random();
	private Wheel wheel = new Wheel();
	private Evaluation eval;
	private ArrayList<Player> listJoueur;
	private int nbTours;
	private int nombreCombats;
	private int longueurSelection;
	private ArrayList<Player> winner;

  /**Constructeur de la classe Selection
  * @param nbTours le nombre de Tours à faire
  * @param longueurSelection le nombre de fichier a obtenir
  * @param secondes le temps maximal d'un combat
  * @param nombreCombats le nombre de combat qu'un joueur dois faire avant de passer a un autre joueur qui dois combattre
   */
  public Selection(int nbTours, int longueurSelection, int secondes, int nombreCombats){
		this.eval = new Evaluation(secondes);
		this.nbTours = nbTours;
		this.nombreCombats = nombreCombats;
		this.listJoueur = eval.getList();
		this.longueurSelection = longueurSelection;
  }

  /**Méthode pour lancer le combat et faire la liste des winners du combat
   */
	public void lancement(){
		this.seBattre(nbTours, nombreCombats);
		//System.out.println("Score : " + eval.toString());
		winner = this.vainqueurs(this.listJoueur, longueurSelection);
	}

  /**Méthode retourner la liste des winner
   * @return la liste des player victorieux
   */
	public ArrayList<Player> getwinner(){
		return this.winner;
	}

  /**Méthode pour changer la liste des joueurs
   * @param a la liste des nouveau joueurs
   */
	public void setlist(ArrayList<Player> a){
		this.listJoueur = a;
		resetpass();
	}

  /**Méthode pour remettre tout les joueur en mode "pas encore jouer"
   */
	public void resetpass(){
		for(Player pal : this.listJoueur){
			pal.setAlreadyPlay(false);
			pal.resetScore();
		}
	}

	/**
	 * Méthode qui fait se battre un Warrior K fois contre d'autre
	 * @param  nbTours Le nombre de Warriors contre lequel il devrat se battre
	 * @param nombreCombats le nombre de combat que chaque joueurs dois faire
	 * @return un liste de joueur (Warrior) avec un getter pour avoir le score de chaque Warriro.
	 */
	 public ArrayList<Player> seBattre(int nbTours, int nombreCombats){
	 for(int i = 0; i < nbTours; i++){//50
		 Player joueur1 = eval.choosePlayer(listJoueur);
		 while(joueur1.getAlreadyPlay()==true){
			 joueur1 = eval.choosePlayer(listJoueur);
		 }
		 this.listJoueur.remove(joueur1);
		 eval.play(nombreCombats, joueur1, listJoueur);
		 joueur1.setAlreadyPlay(true);
		 this.listJoueur.add(joueur1);
	 }
	 return listJoueur;

 }

  /**Méthode pour obtenir la liste des joueurs
   * @return la liste des joueurs
   */
 public ArrayList<Player> getListPlayer(){
	 return this.listJoueur;
 }
	/**
	 * Méthode qui retourne une liste de K vainqueurs (Warriors avec le plus grand score)
	 * @param  listeDeCombat     Liste de Warrior à évaluer
	 * @param  longueurSelection Nombre de Warriors à sélectionner.
	 * @return                   Une liste composée de K Warriors.
	 */
  public ArrayList<Player> vainqueurs(ArrayList<Player> listeDeCombat, int longueurSelection){//normalement 25.
    ArrayList<Player> winner = new ArrayList<>();
    for(int j=0;j<longueurSelection;j++){
      Player win = listeDeCombat.get(wheel.spin(listeDeCombat,random));
      listeDeCombat.remove(win);
      winner.add(win);
    }
    return winner;
  }
}
