package graphique;

import java.awt.*;
import javax.swing.*;

public class Case extends JPanel{
	private int color1;
	private int color2;
	private int color3;

	/**
	 * Constructeur de la classe Case
	 * @param color les couleurs en RGB
	 */
	public Case(int[] color){
		this.color1 = color[0];
		this.color2 = color[1];
		this.color3 = color[2];
	}

	/**
	 * Méthode de changement de couleur
	 * @param color les couleurs en RGB
	 */
	public void change(int[] color){
		this.color1 = color[0];
		this.color2 = color[1];
		this.color3 = color[2];
	}

	/**
	 * Méthode qui dessine la case
	 * @param g le graphics de notre JPanel
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.black);
		g2.fillRect(0,0,this.getWidth(),this.getHeight());
		g2.setPaint(new Color(this.color1,this.color2,this.color3));
		g2.fillOval(0,0,this.getWidth(),this.getHeight());
		this.repaint();
	}

}
