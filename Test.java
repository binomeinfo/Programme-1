import javax.swing.JWindow;

public class Test {
	public double[][][] defaultTest(){
		/*valeurs arbitraires pour faire le test*/
		int n = 2;
		double[] masse = {8,10};
		double[] positionun = {300,400};
		double[] positiondeux = {400,400};

		double[][] position = {positionun,positiondeux};
		double[] vitesseun = {0,20};
		double[] vitessedeux = {0,-20};
		double[][] vitesse = {vitesseun,vitessedeux};
		double[][] position2 = new double[n][2];
		double[][] vitesse2 = new double[n][2];
		double[][] acceleration = new double[n][2];
		double[][]acceleration2 = new double[n][2];
		double g = 10;
		double delta = 0.01;
		int tempstotal = 20000;/*temps de la simulation*/
		Thread[][] tableau = new Thread[n][n];/*Tableau des Threads*/
		double[][][] trajectoire = new double[tempstotal][n][2];/*tableau total de la trajectoire qui contient toutes les positions*/
		
		for (int t = 0; t < tempstotal; t++) {
			/*L'idée est qu'à chaque couple (i,j) est associé un thread qui effectue les calculs
			 *Le problème majeur est qu'il faut le redémarrer à chaque temps t, et je ne suis pas sûr
			 *que ça nous fasse économiser beaucoup en temps*/
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i!=j) {
						Cinematic c = new Cinematic(n,masse,position,position2,vitesse,vitesse2, acceleration, acceleration2,i,j,delta,g);
						tableau[i][j] = new Thread(new Calcul(j,i,c));
						tableau[i][j].start();
					}
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i!=j) {
						try {
							tableau[i][j].join();
						
						} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
					}
				}
			}
			/*on pose effectue position = position2 sans créer d'alias*/
			for (int i = 0; i<n;i++) {
				for (int j = 0; j<2;j++) {
					position[i][j] = position2[i][j];
				}
			}
			/*on pose effectue vitesse = vitesse2 sans créer d'alias*/
			for (int i = 0; i<n;i++) {
				for (int j = 0; j<2;j++) {
					vitesse[i][j] = vitesse2[i][j];
				}
			}
			/*on pose effectue accélération = accélération2 sans créer d'alias*/
			for (int i = 0; i<n;i++) {
				for (int j = 0; j<2;j++) {
					acceleration[i][j] = acceleration2[i][j];
				}
			}
			/* on réinitialise acceleration à 0 */
			for (int i = 0; i<n;i++) {
				for (int j = 0; j<2;j++) {
					acceleration2[i][j] = 0;
				}
			}
			/* on ajoute les dernière positions à trajectoire */
			for (int i = 0; i<n; i++) {
				for (int j = 0; j<2; j++) {
					trajectoire[t][i][j] = position[i][j];
				}
			}
			
		}
		return trajectoire;
	}
	public static void main(String[] args) throws InterruptedException {
		Test test = new Test();
		test.defaultTest();
	}
}
