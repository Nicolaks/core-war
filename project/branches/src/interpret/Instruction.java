package interpret;

public class Instruction {

    private String operateur;
    private char modificateurDepart;
    private Integer adresseDepart;
    private char modificateurArrive;
    private Integer adresseArrive;


    /**
     *
     * @param operateur qui est l'opérateur de base de Redcode par exemple "MOV".
     * @param adresseDepart qui est l'adresse de la case de départ.
     * @param adresseArrive qui est l'adresse de la case d'arrivée.
     * @param modificateurDepart qui spécifie si l'adresse est direct ou indirect.
     * @param modificateurArrive et qui spécifie si l'adresse est direct ou indirect.
     */
    public Instruction(String operateur,char modificateurDepart, Integer adresseDepart, char modificateurArrive, Integer adresseArrive){
        this.operateur = operateur;
        this.modificateurDepart = modificateurDepart;
        this.adresseDepart = adresseDepart;
        this.modificateurArrive = modificateurArrive;
        this.adresseArrive = adresseArrive;
    }

    public Instruction(String operateur,char modificateurDepart, Integer adresseDepart){
        this.operateur = operateur;
        this.modificateurDepart = modificateurDepart;
        this.adresseDepart = adresseDepart;
    }

    public Instruction(String operateur){
        this.operateur = operateur;
    }


    /**
     *
     * @return l'opérateur du fichier Redcode.
     */
    public String getOperateur() {
        return operateur;
    }

    /**
     *
     * @return l'adresse de départ.
     */
    public Integer getAdresseDepart() {
        return adresseDepart;
    }

    public char getModificateurDepart(){
      return modificateurDepart;
    }

    public char getModificateurArrive(){
      return modificateurArrive;
    }

    public void setAdresseDepart(int a){
        this.adresseDepart=a;
    }
    public void setAdresseArrive(int a){
        this.adresseArrive=a;
    }

    /**
     *
     * @return l'adresse d'arrivée
     */
    public Integer getAdresseArrive() {
        return adresseArrive;
    }

    /**
     *
     * @return Une String qui permet d'avoir ce qu'il nous faut
     * pour tout mettre dans une liste d'instructions.
     */
    public String getListInstruction(){
        return "Instruction{" + operateur + " , " + modificateurDepart + " , "  + adresseDepart + " , " + modificateurArrive + " , " + adresseArrive;
    }

    public String instruction3Word(){
      return "Instruction{" + operateur + " , " + modificateurDepart + " , "  + adresseDepart + " , " + modificateurArrive + " , " + adresseArrive;
    }

    /**
     *
     * @return Une string qui nous permet de mettre dans une
     * LinkedList d'objets 'Instruction'.
     */
    @Override
    public String toString() {
        return "Instruction{" + "operateur= " + operateur + ", modificateurDepart= " + modificateurDepart + ", adresseDepart= " + adresseDepart + ", modificateurArrive= " + modificateurArrive + ", adresseArrive= " + adresseArrive + '}';
    }
}
