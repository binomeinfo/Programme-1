/*
 * Cette classe est la classe de base du programme
 * Elle contient l'ensemble des informations de la trajectoire
 * Il y a deux tableaux position, vitesse, et acceleration pour en remettre un à jour et utiliser l'autre simultanément
 */
public class Particles {
	int n;/* le nombre de particules*/
	double[] masse;/*tableau des masses*/
	double[][] vitesse;/*tableau des vitesses au temps t*/
	double[][] vitesse2;/*tableau des vitesses au temps t+dt*/
	double[][] position;/*tableau des positions au temps t*/
	double[][] position2;/*tableau des positions au temps t+dt*/
	double[][] acceleration;/*tableau de l'accélération au temps t*/
	double[][] acceleration2;/*tableau de l'accélération au temps t+dt*/
	
	public Particles (int n, double[] masse, double[][] position, double[][] position2, double[][] vitesse, double[][] vitesse2, double[][] acceleration, double[][] acceleration2){
		this.n = n; 
		this.masse = masse;
		this.vitesse = vitesse;
		this.position = position;
		this.acceleration = acceleration;
		this.position2 = position2;
		this.vitesse2 = vitesse2;
		this.acceleration2 = acceleration2;
	}
}
