import javax.swing.JWindow;

public class Test implements Runnable{
	int n;
	int tempstotal;
	double delta;
	double g;
	double[] masse;
	double[][] position;
	double[][] position2;
	double[][] vitesse;
	double[][] vitesse2;
	double[][] acceleration;
	double[][] acceleration2;
	ConcurrentQueue<double[][]> queue;
	
	Test(int n, int tempstotal, double g, double delta, double[] masse, double[][] position, double[][] vitesse, ConcurrentQueue queue){
		this.n = n;
		this.tempstotal = tempstotal;
		this.delta = delta;
		this.g = g; 
		this.masse = masse;
		this.position = position;
		this.vitesse = vitesse ;
		this.queue = queue;
		this.position2=new double[n][2];
		this.vitesse2=new double[n][2];
		this.acceleration=new double[n][2];
		this.acceleration2=new double[n][2];
		for (int i=0; i<n; i++) {
			position2[i][0] = 0;
			position2[i][1] = 0;
			vitesse2[i][0] = 0;
			vitesse2[i][0] = 0;
			acceleration[i][0] = 0;
			acceleration[i][1] = 0;
			acceleration2[i][0] = 0;
			acceleration2[i][1] = 0;
		}
	}
	
	public void defaultTest() throws Exception{

		Thread[] tableau = new Thread[n];/*Tableau des Threads*/
		
		for (int t = 0; t < tempstotal; t++) {
			for (int i = 0; i < n; i++) {
					Cinematic c = new Cinematic(n,masse,position,position2,vitesse,vitesse2, acceleration, acceleration2,i,delta,g);
					tableau[i] = new Thread(new Calcul(c));
					tableau[i].start();	
			}
			
			for (int i = 0; i < n; i++) {
					try {
						tableau[i].join();	
					} catch (InterruptedException e) {
					e.printStackTrace();
					}
					
			}
			
			/*on pose effectue position = position2 sans créer d'alias*/
			for (int i = 0; i<n;i++) {
				for (int j = 0; j<2;j++) {
					position[i][j] = position2[i][j];
					vitesse[i][j] = vitesse2[i][j];
					acceleration[i][j] = acceleration2[i][j];
					acceleration2[i][j] = 0;
				}
			}
			queue.put(position);
			
		}
		Thread.sleep(10000);
		System.out.println("fini");
		
	}
	

	
	public static void main(String[] args) throws Exception {
		int n = 2;
		double[] masse = {1,100};
		double[] positionun = {300,400};
		double[] positiondeux = {400,400};
		double[][] position = {positionun,positiondeux};
		double[] vitesseun = {0,50};
		double[] vitessedeux = {0,0};
		double[][] vitesse = {vitesseun,vitessedeux};
		double[][] position2 = new double[n][2];
		double[][] vitesse2 = new double[n][2];
		double[][] acceleration = new double[n][2];
		double[][]acceleration2 = new double[n][2];
		double g = 10;
		double delta = 0.01;
		int tempstotal = 20000;/*temps de la simulation*/
		ConcurrentQueue queue = new ConcurrentQueue(10000);

		
		
		Test test =new Test(n,tempstotal,g,delta,masse,position,vitesse,queue);
		test.defaultTest();
	}
	
	@Override
	public void run() {
		
		try {
			defaultTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
