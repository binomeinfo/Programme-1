import java.awt.Dimension; 
import javax.swing.JFrame;
/*Si tu éxecutes ce code dans la situation actuelle, tu auras une surprise : un système de trois planètes avec un soleil au centre!*/
 
public class Graphicfenetre extends JFrame implements Runnable{
	
	int n;
	double[] posX;
	double[] posY;
	int tempstotal;
	ConcurrentQueue<double[][]> queue;
	private Graphic pan;
	
	public Graphicfenetre(int n, int tempstotal,double[][] position, ConcurrentQueue<double[][]> queue){
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
		for (int t = 0; t < tempstotal-1; t++){
			trajectoire = queue.take();
			/*pour chaque i, on place le cercle i à la position donnée par trajectoire*/
			for (int i = 0; i < n; i++) {
				pan.setPosX((int) trajectoire[i][0], i);
				pan.setPosY((int) trajectoire[i][1], i);
			 
			}
			/*on modifie la fenêtre avec les nouvelles positions*/
			pan.repaint();
	  	}
  	}
  
	@Override
	public void run() {
		try {
			go();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	// TODO Auto-generated method stub
	
	}
  
}