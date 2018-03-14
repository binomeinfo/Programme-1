import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Graphic extends JPanel {
	int n;
	private double[] posX;
	private double[] posY;
	
	Graphic(double[] posX,double[] posY, int n){
		this.posX = posX;
		this.posY = posY;
		this.n = n;
	}

	public void paintComponent(Graphics g){
		g.setColor(Color.red);
    for (int p = 0; p < n ; p++) {
    		g.fillOval((int) posX[p], (int) posY[p], 10, 10);
    }
  }

  public double getPosX(int i) {
    return posX[i];
  }

  public void setPosX(double posX, int i) {
    this.posX[i] = posX;
  }

  public double getPosY(int i) {
    return posY[i];
  }

  public void setPosY(double posY, int i) {
    this.posY[i] = posY;
  }        
}