package memoire;

import java.util.*;

import interpret.*;

public class Controleur{
	private ArrayList<Player> listplayer;
	private int joueuractuel = 0;

	/** Constructeur de la classe Controleur pour initialiser les attributs
	 * @param partie la machine de mars
	 * @param listeplayer la liste des joueurs de la partie
	 */
	public Controleur(Mars partie,ArrayList<Player> listeplayer){
		this.listplayer = listeplayer;
		iIJoueurs(partie);
	}
	
	/** Méthode pour retourner la liste de joueur vivant
	 * @return liste des joueurs vivant
	 */
	public ArrayList<Player> retourjoueurs(){
		return this.listplayer;
	}
	
	/** Méthode pour changer de joueur
	 */
	public void changementjoueur(){
		if(this.joueuractuel+1<this.listplayer.size()){
			this.joueuractuel += 1;
		}
		else{
			this.joueuractuel = 0;
		}
	}
	
	/** Méthode pour obtenir le joueur qui joue
	 * @return le joueur qui joue
	 */
	public Player getPlayer(){
		return listplayer.get(joueuractuel);
	}
	
	/** Méthode de placement des pointeurs de chaques joueurs et de la liste d'instruction dans la machine de mars
	 * @param partie la machine de mars
	 */
	public void iIJoueurs(Mars partie){
		Random r = new Random();
		int ecart = partie.taille()/(this.listplayer.size());
		int ajout = 0;
		//mise en place des listes d'instruction de chaque joueur dans la mémoire
		for(Player c : this.listplayer){
			int pos = (ecart*ajout) + r.nextInt(ecart);
			//reste dans la limite de separation
			if(pos>=(ecart*(ajout+1))-c.getCoups().size()){
				pos = pos - (c.getCoups().size());
			}
			c.resetpointeur();
			c.setPointeur(pos-1,partie.taille());
			int i = 0;
			while(i<c.getCoups().size()){
				partie.modifyCase(copyCoup(c.getCoups().get(i)),pos);
				partie.modifyAffiche(pos,c.getSymbol(),c.getColor());
				pos+=1;
				i+=1;
			}
			ajout+=1;
		}
	}
	
	/**Méthode de suppression du joueur qui joue
	 */
	public void deleteJoueur(){
		this.listplayer.remove(this.joueuractuel);
		if(this.joueuractuel==0){
			this.joueuractuel = this.listplayer.size()-1;
		}else{
			this.joueuractuel-=1;
		}
	}
	
	/** Méthode pour savoir si la partie est finie
	 * @return partie fini ou non
	 */
	public boolean isterminal(){
		if (this.listplayer.size()==1){
			return true;
		}
		return false;
	}
	
	/** Méthode pour faire une copie d'une instruction
	 * @param change l'instruction dont on veux faire la copie
	 * @return la copie de l'instruction
	 */
	public Instruction copyCoup(Instruction change){
		String op = change.getOperateur();
		char deb = change.getModificateurDepart();
		char fin = change.getModificateurArrive();
		Integer val1 = change.getAdresseDepart();
		Integer val2 = change.getAdresseArrive();
		Instruction copy =  new Instruction(op,deb,val1,fin,val2);
		return copy;
	}
}
