package interpret;
import java.util.ArrayList;
import java.util.List;

public class Demo{

    public static void main(String [] args){
        String imp = "Imp.nprt";
        String lance = "lance.nprt";
        ArrayList<String> intruct = new ArrayList<String>();
        intruct.add(imp);
        intruct.add(lance);
        Interpreteur inter = new Interpreteur(intruct);
        System.out.println(inter.joueurs().get(0).traducteur());
    }
}