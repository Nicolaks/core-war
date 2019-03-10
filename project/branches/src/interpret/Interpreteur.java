package interpret;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;
import java.io.*;

public class Interpreteur{

    private ArrayList<String> fichiers;

    public Interpreteur(ArrayList<String> fichiers){
        this.fichiers = fichiers;
    }

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