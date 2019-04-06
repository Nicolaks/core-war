package genetics.population;
import interpret.*;
import java.io.*;
import java.util.*;


public class Popinit{

    private String chemin;

	/**Constructeur de la classe
	 * @param chemin nom du répertoire dans lequel chercher notre population
	 */
    public Popinit(String chemin){
        this.chemin = chemin;
    }

	/**Méthode qui prends tout les nom de fichiers dans le répertoire
	 * @return la liste des noms de fichiers
	 */
    public ArrayList<String> populace(){
        File repertoire = new File(this.chemin);
        String liste[] = repertoire.list();
        ArrayList<String> list = new ArrayList<>();
        if (liste != null) {
            for (int i = 0; i < liste.length; i++) {
                list.add("../fichiers/"+liste[i]);
            }
        } else {
            System.err.println("Nom de repertoire invalide");
        }
        return list;
    }

	/**Méthode qui prends tout les nom de fichiers dans le répertoire
	 * @return la liste des noms de fichiers
	 */
    public ArrayList<Parser> population(){
        ArrayList<String> inter = new ArrayList<String>();
        inter = populace();
        Interpreteur interpre = new Interpreteur(inter);
        return interpre.joueurs();
    }

	/**Méthode qui change la liste de parser en liste de liste d'instruction
	 * @return la liste de liste d'instruction
	 */
    public ArrayList<ArrayList> parserToInstruction(){
	  Interpreteur interp = new Interpreteur(populace());
      ArrayList<ArrayList> arrayArrayList = new ArrayList<ArrayList>();

      for(int i = 0; i<interp.joueurs().size(); i++){
		arrayArrayList.add(interp.joueurs().get(i).traducteur());
      }
      return arrayArrayList;
    }

	/**Méthode qui modifie le chemin
	 * @param chemin nouveau chemin
	 */
    public void setChemin(String chemin){
      this.chemin = chemin;
    }


}
