
public class Cinematic extends Particles{
	/*Chaque Thread est associé à un couple de (i,j)*/
	int i;
	int j;
	double delta;/*échelle de temps*/
	double g;/*constante de force de gravitation*/
	
	public Cinematic(int n,double[] masse,double[][] position, double[][] position2, double[][] vitesse, double[][] vitesse2, double[][] acceleration, double[][] acceleration2, int i, int j, double delta,double g) {
		super(n,masse, position, position2, vitesse,vitesse2, acceleration,acceleration2);
		this.i=i;
		this.j=j;
		this.delta=delta;
		this.g=g;
		// TODO Auto-generated constructor stub
	}
	/*recalcul de la position*/
	public void calculposition() {
		position2[i][0] = position[i][0] + delta*vitesse[i][0];
		position2[i][1] = position[i][1] + delta*vitesse[i][1];
		
	}
	/*recalcul de la vitesse*/
	public void calculvitesse() {
		vitesse2[i][0] = vitesse[i][0] + delta*acceleration[i][0];
		vitesse2[i][1] = vitesse[i][1] + delta*acceleration[i][1];
	}
	/*calcul de la contribution de la particule j à l'accélération de i*/
	public void calculaccel() {
		double dx = position[i][0] - position[j][0]; 
		double dy = position[i][1] - position[j][1];
		double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		acceleration2[i][0] += -g*masse[j]*dx/(Math.pow(distance,1.5));
		acceleration2[i][1] += -g*masse[j]*dy/(Math.pow(distance,1.5));	
	}

	
}
