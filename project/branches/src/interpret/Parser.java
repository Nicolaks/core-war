package interpret;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;
import java.io.*;
import java.util.regex.*;

public class Parser{

    private String fichier;

    public Parser(String fichier){
        this.fichier = fichier;
    }

    /**
     * [ Méthode permettant de prendre un fichier en entré, puis retourne chaque ligne dans une liste de String.
     * @param  fichier  C'est un fichier redcode.
     * @return         Une liste de String qui sera envoyé dans les autres méthodes.
     */
    public List<String> lire(String fichier){
        File f = new File("interpret/"+fichier);
        Path lectru = Paths.get(f.getAbsolutePath());
        Charset charset = Charset.forName("ISO-8859-1");
        List<String> lines = null;
        try {
            lines = Files.readAllLines(lectru, charset);

            /*for (String line : lines) {
              System.out.println(line);
            }*/
          } catch (IOException e) {
            System.out.println(e);
          }
        if (lines.size() > 50){
            System.out.println("Redcode trop long");
            return(null);
        }
        return(lines);
    }
    /**
     * [ Méthode qui permet de séparer dans une liste de String les différents mots avec un espace]
     * @param  separe C'est une liste de String qui est le résultat de la méthode lire().
     * @return        La fonction retourne une ArrayList<ArrayList<String>> qui pourra ensuite être
     *  convertit en liste d'instructions.
     */
    public ArrayList<ArrayList<String>> separateur(){
        ArrayList<ArrayList<String>> tab = new ArrayList<ArrayList<String>>();
        if(this.lire(this.fichier) == null){
            return(null);
        }
        for (int i =0;i<this.lire(this.fichier).size();i++){
            String str = this.lire(this.fichier).get(i);
            ArrayList<String> tab2 = new ArrayList<String>();
            for (String retval: str.split(" ")){
                tab2.add(retval);
            }
            tab.add(tab2);
        }
        return(tab);
    }

    /**
     * [ Méthode qui va traduire ce que la méthode separateur() renvoie, en une ArrayList<Instruction> ]
     * @param  liste  La liste est ce que la méthode separateur() renverra.
     * @return       Une ArrayList<Instruction> avec à chaque fois une instruction contenant trois éléments.
     */
    public ArrayList<Instruction> traducteur(){
        if(this.separateur()== null){
            return(null);
        }
        Instruction instruct = new Instruction(null,'$',null,'$',null);
        ArrayList<Instruction> loup = new ArrayList<Instruction>();
        for(int i = 0;i< this.separateur().size();i++){
            String operateur = this.separateur().get(i).get(0);
            if (this.separateur().get(i).size() == 2){
                  if(this.separateur().get(i).get(1).substring(0,1).matches("[#@$<>]")){
                    instruct = new Instruction(operateur,this.separateur().get(i).get(1).charAt(0), Integer.parseInt(this.separateur().get(i).get(1).substring(1)));
                }
                else{
                    instruct = new Instruction(operateur,'$',Integer.parseInt(this.separateur().get(i).get(1)));
                }
            }
            else if(this.separateur().get(i).size() == 3){
                if (this.separateur().get(i).get(1).substring(0,1).matches("[#@$<>]") && this.separateur().get(i).get(2).substring(0,1).matches("[#@$]") ){
                    instruct = new Instruction(operateur,this.separateur().get(i).get(1).charAt(0), Integer.parseInt(this.separateur().get(i).get(1).substring(1)),this.separateur().get(i).get(2).charAt(0), Integer.parseInt(this.separateur().get(i).get(2).substring(1)));
                }
                else if(this.separateur().get(i).get(1).substring(0,1).matches("[#@$<>]")){
                    instruct = new Instruction(operateur,this.separateur().get(i).get(1).charAt(0), Integer.parseInt(this.separateur().get(i).get(1).substring(1)),'$', Integer.parseInt(this.separateur().get(i).get(2)));
                }
                else if(this.separateur().get(i).get(2).substring(0,1).matches("[#@$<>]")){
                    instruct = new Instruction(operateur,'$', Integer.parseInt(this.separateur().get(i).get(1)),this.separateur().get(i).get(2).charAt(0), Integer.parseInt(this.separateur().get(i).get(2).substring(1)));
                }
                else{
                    instruct = new Instruction(operateur,'$',Integer.parseInt(this.separateur().get(i).get(1)),'$',Integer.parseInt(this.separateur().get(i).get(2)));

                }

            }
            loup.add(instruct);
        }

        return(loup);

    }


}
