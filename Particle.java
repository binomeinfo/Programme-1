import java.util.concurrent.locks.*;

public class Particle implements Runnable {
	// Chaque thread est associé à une particule

	int i;
	double delta;/* échelle de temps */
	int n; // nombre de particules
	double[][] position;
	double[][] vitesse;
	double[][] acceleration;
	double[] masse;
	Barrier[] barriers1;
	Barrier[] barriers2;

	public Particle(Barrier[] barriers1, Barrier[] barriers2, int n, double[] masse, double[][] position,
			double[][] vitesse, double[][] acceleration, int i, double delta) {
		this.i = i;
		this.n = n;
		this.delta = delta;
		this.masse = masse;
		this.position = position;
		this.vitesse = vitesse;
		this.acceleration = acceleration;
		this.barriers1=barriers1;
		this.barriers2=barriers2;
	}

	/* recalcul de la position */
	public void calculposition() {
		position[i][0] = position[i][0] + delta * vitesse[i][0];
		position[i][1] = position[i][1] + delta * vitesse[i][1];

	}

	/* recalcul de la vitesse */
	public void calculVitesse() {
		vitesse[i][0] = vitesse[i][0] + delta * acceleration[i][0];
		vitesse[i][1] = vitesse[i][1] + delta * acceleration[i][1];
	}

	/* calcul de la contribution de la particule j à l'accélération de i */
	public void calculAccel() {
		acceleration[i][0]=0;
		acceleration[i][1]=0;
		for (int j = 0; j < n; j++) {
			if (i != j) {
				double dx = position[i][0] - position[j][0];
				double dy = position[i][1] - position[j][1];
				double[] acc = Simulation.interaction(i, j, dx, dy, masse);
				acceleration[i][0] = acceleration[i][0] + acc[0];
				acceleration[i][1] = acceleration[i][1] + acc[1];
			}
		}
	}

	public void run() {
		for (int i=0; i<barriers1.length;i=i+1) {
			this.calculAccel();
			try {
				barriers1[i].waitForRest();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.calculVitesse();
			this.calculposition();
			try {
				barriers2[i].waitForRest();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
