package graphique;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Fenetre extends JFrame implements MouseListener{
	private Case tab[];
	private int height = 0;
	private int width = 0;
	private int taille = 0;
	Bouton bouton1;
	Bouton bouton2;

	/**Constructeur de la classe Fenetre
	 * @param n la taille du carré de la fenetre java
	 */
	public Fenetre(int n){
		this.tab = new Case[n*n];
		this.setUndecorated(true);
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.height = (int) dimension.getHeight();
		this.width = (int) dimension.getWidth();
		this.taille = n;
		
		this.setSize(this.width,this.height);
		this.setTitle("CoreWar");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		
		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());
		JPanel grille = new JPanel();
		GridLayout gl = new GridLayout(n,n,2,2);
		grille.setLayout(gl);
		grille.setBackground(Color.black);
		
		cp.add(grille,BorderLayout.CENTER);
		
		GridLayout gl2 = new GridLayout(1,2,0,0);
		
		JPanel boutons = new JPanel();
		boutons.setLayout(gl2);
		bouton1 = new Bouton("Gagnant : Personne");
		bouton1.addMouseListener(this);
		bouton2 = new Bouton("Quitter ?");
		bouton2.addMouseListener(this);
		
		boutons.add(bouton1);
		boutons.add(bouton2);
		cp.add(boutons,BorderLayout.SOUTH);
		
		init(grille);
		this.pack();
		this.setVisible(true);

	}

	/**Méthode d'ajout de chaque case du corewar
	 * @param pane le JPanel qui contient le gridlayout pour organiser les JPanel de chaque case
	 */
	public void init(JPanel pane){
		int[] cool = {0,0,0};
		for(int j=0;j<this.taille*this.taille;j++){
			this.tab[j]=new Case(cool);
			pane.add(this.tab[j]);
			this.tab[j].setPreferredSize(new Dimension(this.width/this.taille,this.height/this.taille));
		}
	}

	/**Méthode de changement de couleur
	 * @param XY la position de la case a changer
	 * @param color la couleur RGB a mettre dans la case
	 */
	public void changeColor(int XY, int[] color){
		this.tab[XY].change(color);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource()==this.bouton2){
			this.dispose();
			System.exit(0);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * Méthode de changement du texte du bouton
	 * @param mot le texte du bouton
	 */
	public void changetext(String mot){
		this.bouton1.changermot("Gagnant : "+mot);
	}
	
}
