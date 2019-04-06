package interpret;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;
import java.io.File;
import java.io.*;

public class Interpreteur{

    private ArrayList<String> fichiers;

	/**Constructeur de la classe Interpreteur
	 * @param fichiers la liste des fichiers à  interpreter
	 */
    public Interpreteur(ArrayList<String> fichiers){
        this.fichiers = fichiers;
    }

	/**Méthode qui parse la liste de nos fichiers
	 * @return retourne la liste des fichiers parser
	 */
    public ArrayList<Parser> joueurs(){
        ArrayList<Parser> Redcode = new ArrayList<Parser>();
        Parser parse = new Parser(null);
        for(int i =0;i<this.fichiers.size();i++){
            parse = new Parser(this.fichiers.get(i));
            Redcode.add(parse);

        }
        return(Redcode);
    }

}
