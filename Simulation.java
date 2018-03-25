import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Simulation {
	public static void main(String[] args) throws InterruptedException {
		int n = 2;
		int tempstotal = 150000;
		double[] masse = {1,1000};
		double[] positionun = {200,0};
		double[] positiondeux = {200,100};
		double[][] position = {positionun,positiondeux};
		double[] vitesseun = {100,0};
		double[] vitessedeux = {0,0};
		double[][] vitesse = {vitesseun,vitessedeux};
		double[][] acceleration = new double[n][2];
		double g = 10;
		double delta = 0.01;
		
		
		Barrier[] barriers=new Barrier[tempstotal];
		ArrayBlockingQueue<double[][]> queue=new ArrayBlockingQueue<double[][]>(tempstotal+1);
		Thread affichage = new Thread(new Graphicfenetre(n,tempstotal,position,queue));
		Thread[] tableau = new Thread[n];/*Tableau des Threads*/
		for (int t=0; t<tempstotal; t=t+1) {
			barriers[t]=new Barrier(n+1);
		}
		for (int i = 0; i < n; i++) {
			barriers[i]=new Barrier(n+1);
			tableau[i] = new Thread(new Particle(barriers, n, masse, position, vitesse, acceleration, i, delta));
			tableau[i].start();	
		}
		affichage.start();
		for (int t=0; t<tempstotal; t=t+1) {
			queue.add(position.clone());
			affiche(position);
			Thread.sleep(100);
			barriers[t].waitForRest();
		}
	}
	
	public static void affiche(double[][] position) {
		for (int i=0; i<position.length; i=i+1) {
			for (int j=0; j<position[0].length; j=j+1) {
				System.out.print(position[i][j]+" ");
			}
			System.out.println(" fin de ligne");
		}
	}
	
	public static double[] interaction(int i, int j, double dx, double dy, double[] masse) {
		double[] acc=new double[2];
		double g=9.1;
		double distance=Math.sqrt(dx*dx+dy*dy);
		acc[0] = -g*masse[j]*dx/(distance*distance);
		acc[1] = -g*masse[j]*dy/(distance*distance);	
		return acc;
	}
}
