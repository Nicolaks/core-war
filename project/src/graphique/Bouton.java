package graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
 
import javax.swing.JButton;
  
public class Bouton extends JButton {
	private String name;

	/**
	 * Constructeur de la classe Bouton
	 * @param str le texte du bouton
	 */
	public Bouton(String str){
		super(str);
		this.name = str;
		this.setBorderPainted(false);
		Font f=new Font("Arial", Font.BOLD, 28);
		this.setFont(f);
	}

	/**
	 * Méthode pour dessiner dans le bouton
	 * @param g le Graphics de notre object qui viens de JButton
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setPaint(Color.black);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(Color.white);
		g2d.drawString(this.name, this.getWidth() / 2 - (this.getWidth()/ 2 /4), (this.getHeight() / 2) + 5);
		this.setText(this.name);
		this.repaint();
	}
 
 	/**
	 * CMéthode de changement du texte
	 * @param mot le nouveau texte
	 */
	public void changermot(String mot){
		this.name = mot;
	}      
}
