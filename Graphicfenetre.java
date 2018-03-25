import java.awt.Dimension;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JFrame;
/*Si tu éxecutes ce code dans la situation actuelle, tu auras une surprise : un système de trois planètes avec un soleil au centre!*/
 
public class Graphicfenetre extends JFrame implements Runnable{
	
	int n;
	double[] posX;
	double[] posY;
	int tempstotal;
	ArrayBlockingQueue<double[][]> queue;
	private Graphic pan;
	
	public Graphicfenetre(int n, int tempstotal,double[][] position, ArrayBlockingQueue<double[][]> queue){
		this.n = n;
		posX = new double[n];
		posY = new double[n];
		for (int i=0; i<n;i++) {
			this.posX[i] = position[i][0];
			this.posY[i] = position[i][0];
		}
		this.tempstotal = tempstotal;
		this.queue = queue;
		this.pan = new Graphic(posX,posY,n);
		this.setTitle("Animation");
		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(pan);
		this.setVisible(true);
	}
	

	private void go() throws InterruptedException{
		double[][] trajectoire;
		System.out.println("temps total= " + tempstotal + " ");
		for (int t = 0; t < tempstotal-1; t++){
			System.out.println("On prend l'image "+ t +" ");
			trajectoire = queue.take();
			System.out.println("On l'a prise");
			/*pour chaque i, on place le cercle i à la position donnée par trajectoire*/
			for (int i = 0; i < n; i++) {
				pan.setPosX((int) trajectoire[i][0], i);
				pan.setPosY((int) trajectoire[i][1], i);
			}
			Thread.sleep(100);
			/*on modifie la fenêtre avec les nouvelles positions*/
			pan.repaint();
	  	}
  	}
  
	@Override
	public void run() {
		try {
			go();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
  
}