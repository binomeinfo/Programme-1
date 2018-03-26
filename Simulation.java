import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Simulation {
	public static void main(String[] args) throws InterruptedException {
		int n = 20;
		int tempstotal = 150000;
//		double[] masse = {1,1000};
//		double[] positionun = {200,0};
//		double[] positiondeux = {200,100};
//		double[][] position = {positionun,positiondeux};
//		double[] vitesseun = {100,0};
//		double[] vitessedeux = {0,0};
//		double[][] vitesse = {vitesseun,vitessedeux};
		
		double[][] position=new double[n][2];
		double[][] vitesse=new double[n][2];
		double[][] acceleration = new double[n][2];
		double[] masse= new double[n];
		double g = 10;
		double delta = 0.001;
		generateTest(n, position, vitesse, masse);
		
		Barrier[] barriers1=new Barrier[tempstotal];
		Barrier[] barriers2=new Barrier[tempstotal];
		ArrayBlockingQueue<double[][]> queue=new ArrayBlockingQueue<double[][]>(tempstotal+1);
		Thread affichage = new Thread(new Graphicfenetre(n,tempstotal,position,queue));
		Thread[] tableau = new Thread[n];/*Tableau des Threads*/
		for (int t=0; t<tempstotal; t=t+1) {
			barriers1[t]=new Barrier(n+1);
			barriers2[t]=new Barrier(n);
		}
		for (int i = 0; i < n; i++) {
			tableau[i] = new Thread(new Particle(barriers1, barriers2, n, masse, position, vitesse, acceleration, i, delta));
			tableau[i].start();	
		}
		affichage.start();
		for (int t=0; t<tempstotal; t=t+1) {
			queue.put(position.clone());
			barriers1[t].waitForRest();
		}
	}
	
	public static void generateTest(int n, double[][] position, double[][] vitesse, double[] masse) {
		 for (int i=0; i<n; i=i+1) {
			 position[i][0]= (Math.random()-0.5)*10+500;
			 position[i][1]= (Math.random()-0.5)*10+500;
			 vitesse[i][0]= (Math.random()-0.5)*40;
			 vitesse[i][1]= (Math.random()-0.5)*40;
			 masse[i]= Math.random()*50;
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
