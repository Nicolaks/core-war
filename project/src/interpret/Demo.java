package interpret;
import java.util.*;

public class Demo{

    public static void main(String [] args){
        ArrayList<Instruction> lol = new ArrayList<Instruction>();
        Parser parse = new Parser("../fichiers/cancer.nprt");
        lol = parse.traducteur();
        Ecriture ecr = new Ecriture(lol,"caca");
        ecr.CreerDossier();
        ecr.creerfichier("bitecouille");
    }
}
