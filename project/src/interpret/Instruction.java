package interpret;

public class Instruction {

    private String operateur;
    private char modificateurDepart;
    private Integer adresseDepart;
    private char modificateurArrive;
    private Integer adresseArrive;


    /**Méthode pour mettre en place l'instruction
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

    /**Méthode pour mettre en place l'instruction
     * @param operateur qui est l'opérateur de base de Redcode par exemple "MOV".
     * @param modificateurDepart qui est le modificateur de la case de départ.
     * @param adresseDepart qui est l'adresse de la case de départ.
     */
    public Instruction(String operateur,char modificateurDepart, Integer adresseDepart){
        this.operateur = operateur;
        this.modificateurDepart = modificateurDepart;
        this.adresseDepart = adresseDepart;
    }

    /**Méthode pour mettre en place l'instruction
     * @param operateur qui est l'opérateur de base de Redcode par exemple "MOV". (???)
     */
    public Instruction(String operateur){
        this.operateur = operateur;
    }


    /**Méthode pour obtenir l'opérateur
     * @return l'opérateur du fichier Redcode.
     */
    public String getOperateur() {
        return operateur;
    }

    /**Méthode pour modifié l'opérateur
     * @param a le nouvelle opérateur
     */
    public void setOperateur(String a){
        this.operateur=a;
    }

    /**Méthode pour obtenir l'adresse de départ
     * @return l'adresse de départ.
     */
    public Integer getAdresseDepart() {
        return adresseDepart;
    }

    /**Méthode pour obtenir le modificateur de départ
     * @return le modificateur de départ.
     */
    public char getModificateurDepart(){
      return modificateurDepart;
    }

    /**Méthode pour obtenir le modificateur d'arrive
     * @return le modificateur d'arrive
     */
    public char getModificateurArrive(){
      return modificateurArrive;
    }

    /**Méthode pour modifié l'adresse de départ
     * @param a la nouvelle adresse de départ
     */
    public void setAdresseDepart(int a){
        this.adresseDepart=a;
    }

    /**Méthode pour modifié l'adresse d'arrive
     * @param a la nouvelle adresse d'arrive
     */
    public void setAdresseArrive(int a){
        this.adresseArrive=a;
    }

    /**Méthode pour modifié le modificateur de départ
     * @param a le nouveau modificateur de départ
     */
    public void setModificateurDepart(char a){
        this.modificateurDepart=a;
    }

    /**Méthode pour modifié le modificateur d'arrive
     * @param a le nouveau modificateur d'arrive
     */
    public void setModificateurArrive(char a){
        this.modificateurArrive=a;
    }

    /**Méthode pour obtenir l'adresse d'arrive 
     * @return l'adresse d'arrivée
     */
    public Integer getAdresseArrive() {
        return adresseArrive;
    }

    /**Méthode pour obtenir les détails de la liste d'instruction
     *
     * @return Une String qui permet d'avoir ce qu'il nous faut
     * pour tout mettre dans une liste d'instructions.
     */
    public String getListInstruction(){
        return "Instruction{" + operateur + " , " + modificateurDepart + " , "  + adresseDepart + " , " + modificateurArrive + " , " + adresseArrive;
    }

	/**Méthode pour obtenir les détails de la liste d'instruction
     *
     * @return Une String qui permet d'avoir ce qu'il nous faut
     * pour tout mettre dans une liste d'instructions.
     */
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
