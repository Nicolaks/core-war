package interpret;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.io.PrintWriter;
import java.io.File;
import java.io.*;

public class Ecriture{

    ArrayList<Instruction> liste;
    String nomdossier;

	/**Constructeur de la classe Ecriture
	 * @param liste la liste d'instruction a écrire
	 * @param nomdossier nom du dossier de destination
	 */
    public Ecriture(ArrayList<Instruction> liste,String nomdossier){
        this.liste=liste;
        this.nomdossier = nomdossier;
    }

	/**Méthode de création de dossier
	 */
    public void CreerDossier(){
        File dir = new File("generation/"+this.nomdossier);
        dir.mkdirs();
    }

	/**Méthode de création du fichier en .nprt
	 * @param nomfichier le nom du fichier en .nprt
	 */
    public void creerfichier(String nomfichier){
        File fichier = new File("generation/"+nomdossier+"/"+nomfichier+".nprt");
        try {
            fichier.createNewFile();
            PrintWriter ecrivain =  new PrintWriter(new BufferedWriter(new FileWriter(fichier)));
            for (int i=0;i<this.liste.size();i++){
                ecrivain.print(this.liste.get(i).getOperateur());
                ecrivain.print(" ");
                ecrivain.print(this.liste.get(i).getModificateurDepart());
                ecrivain.print(this.liste.get(i).getAdresseDepart());
                ecrivain.print(" ");
                if(this.liste.get(i).getModificateurArrive() != '\u0000' ){
                    ecrivain.print(this.liste.get(i).getModificateurArrive());
                    ecrivain.print(this.liste.get(i).getAdresseArrive());
                }
                ecrivain.println();
            }
            ecrivain.close();
          } catch (IOException e) {
            System.out.println(e);
          }
    }

}
