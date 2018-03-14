
public class Simulation {
	public static void main(String[] args) throws InterruptedException {
		int n = 2;
		double[] masse = {1,100};
		double[] positionun = {350,400};
		double[] positiondeux = {500,400};
		double[][] position = {positionun,positiondeux};
		double[] vitesseun = {0,10};
		double[] vitessedeux = {0,0};
		double[][] vitesse = {vitesseun,vitessedeux};
		double[][] position2 = new double[n][2];
		double[][] vitesse2 = new double[n][2];
		double[][] acceleration = new double[n][2];
		double[][]acceleration2 = new double[n][2];
		double g = 10;
		double delta = 0.01;
		int tempstotal = 15000;
		ConcurrentQueue queue = new ConcurrentQueue(15000);
		
		
		Thread test = new Thread(new Test(n,tempstotal,g,delta,masse,position,vitesse,queue));
		Thread graphic = new Thread(new Graphicfenetre(n,tempstotal,position,queue));
		test.start();
		graphic.start();
		
	}
}
