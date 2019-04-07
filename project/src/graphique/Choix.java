package graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;


public class Choix extends JFrame implements ActionListener{
	private JLabel a;
	private JPanel p;
	private ArrayList<String> warrior = new ArrayList<>();
	private JButton button2;
	private boolean pret=false;

	/**
	 * Constructeur de la classe qui fait le GUI de lancement
	 */
	public Choix(){
		//réglage de la fenetre
		this.setTitle("CoreWar - Choix des Warriors");
		this.setUndecorated(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		lancement();
		this.pack();
	}
  
  /**
   * Méthode qui met tout en place dans la fenêtre
   */
	public void lancement(){
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) dimension.getHeight();
		int width = (int) dimension.getWidth();
		this.setSize(width,height);
		Container cp = this.getContentPane();cp.setLayout(new BorderLayout());
		
		for(int i=0;i<4;i++){
			if(i==0){
				//mise en place du logo
				p = new JPanel(){
				String nom_fichier_image = "src/graphique/logo.jpg";
				protected void paintComponent(Graphics g){
					super.paintComponent(g);
					ImageIcon m = new ImageIcon(nom_fichier_image);
					Image monImage = m.getImage();
					g.drawImage(monImage, 0, 0, this.getWidth(), this.getHeight(), this);
				}
				};;
				p.setPreferredSize(new Dimension(width,height/2));
				p.setBackground(Color.black);
				cp.add(p,BorderLayout.NORTH);
			}
			else if(i==2){
				//mise en place d'une séparation au milieu
				p = new JPanel();
				p.setBackground(Color.black);
				p.setPreferredSize(new Dimension(width,height/4));
				cp.add(p,BorderLayout.CENTER);
			}
			else if(i==3){
				//mise en place de la zone avec JButton et JFileChooser 
				JButton button = new JButton("Choix des warriors");
				button.addActionListener(this);
				p = new JPanel();
				p.setPreferredSize(new Dimension(width,height/4));
				p.setBackground(Color.black);
				p.add(button);
				cp.add(p,BorderLayout.SOUTH);
				JLabel b = new JLabel("(deux fichiers obligatoires pour commencer)");
				p.add(b);
				JLabel a = new JLabel("fichiers choisi : ");
				
				button2 = new JButton("Lancement");
				button2.addActionListener(this);
				button2.setPreferredSize(new Dimension(width, height/8));
				p.add(button2);
				p.add(a);
			}
		}
	}

  /**
   * Méthode pour l'action des boutons et la création de la fenetre de choix de fichier
   */
	public void actionPerformed(ActionEvent e) {
		//action du bouton
		if(e.getSource()==this.button2){
			if(warrior.size()>=2){
				this.pret = true;
			}
		//mise en place et utilisation du JFileChooser
		}else{
			try{
				JFileChooser fc = new JFileChooser("src/fichiers");
				fc.showOpenDialog(null);
				if(fc.getSelectedFile().getName().contains(".nprt")){
				JLabel ac = new JLabel(fc.getSelectedFile().getName()+" ");
				p.add(ac);
				this.warrior.add(fc.getSelectedFile().getName());
				p.revalidate();
			}
			}catch(Exception error){
			}
		}
	}

  /**
   * Méthode qui dit quand la personne veux lancer le jeu
   * @return si on lance le jeu
   */
	public boolean getPret(){
		return this.pret;
	}

  /**
   * Méthode qui retourne les fichiers choisi
   * @return les fichiers choisi
   */
	public ArrayList<String> retourJoueur(){
		return this.warrior;  
	}

  /**
   * Méthode qui permet de détruire la fenêtre
   */
	public void destruction(){
		this.dispose();  
	}
}
