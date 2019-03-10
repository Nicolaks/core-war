package Graphique;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;


public class fenetre extends JFrame{
	private JPanel tab[];
	private int height = 0;
	private int width = 0;
	private int taille = 0;
	
	public fenetre(int n){
		this.tab = new JPanel[n*n];
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.height = (int) dimension.getHeight();
		this.width = (int) dimension.getWidth();
		this.taille = n;
		
		this.setSize(this.width,this.height);
		this.setTitle("CoreWar");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		GridLayout gl = new GridLayout(n,n,2,2);
		this.setLayout(gl);
		init();
		this.pack();
		this.setVisible(true);

	}
	
	public void init(){
 
		for(int j=0;j<this.taille*this.taille;j++){
			this.tab[j]=new JPanel();
			this.tab[j].setBackground(new Color(175,175,175));
			this.getContentPane().add(this.tab[j]);
			this.tab[j].setPreferredSize(new Dimension(this.width/this.taille,this.height/this.taille));
		}
	}
	
	public void changeColor(int XY, int color){
		this.tab[XY].setBackground(new Color(color-(color%75),200-(color%150),color/3));
	}
}
