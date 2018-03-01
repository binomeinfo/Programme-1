import java.awt.Dimension; 
import javax.swing.JFrame;
/*Si tu éxecutes ce code dans la situation actuelle, tu auras une surprise : un système de trois planètes avec un soleil au centre!*/
 
public class Graphicfenetre extends JFrame{
	int n = 2;
	
	double[] posX = {300,400};
	double[] posY = {400,400};
	private Graphic pan = new Graphic(posX,posY);
	
	public Graphicfenetre(){        
		this.setTitle("Animation");
		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(pan);
		this.setVisible(true);
		go();
  }

  private void go(){
	  Test test = new Test();
	  double[][][] trajectoire = test.defaultTest(); /*on récupère le tableau des positions calculé par Test*/
	  for(int t = 0; t < 20000; t++){
		  for (int i = 0; i<n; i++) {
			 /*pour chaque i, on place le cercle i à la position donnée par trajectoire*/
			 pan.setPosX((int) trajectoire[t][i][0], i);
			 pan.setPosY((int) trajectoire[t][i][1], i);
			 
			 try {
			     Thread.sleep(1);
			 } catch (InterruptedException e) {
			        e.printStackTrace();
			 }
			 
		  }
		  /*on modifie la fenêtre avec les nouvelles positions*/
		  pan.repaint();
	  }
  }
  
  public static void main(String[] args) {
	  Graphicfenetre fenetre = new Graphicfenetre();
  }
  
}