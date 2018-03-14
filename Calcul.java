

class Calcul implements Runnable{
	/*Calcul effectue les opérations de Cinematic*/
	/*Il prend en compte les indices (i,j) pour ne pas effectuer plusieurs fois l'opération de calcul de position ou de vitesse au temps t*/

	Cinematic c;
	
	public Calcul(Cinematic c) {
		this.c=c;
	}
	public void Test() throws InterruptedException{
		/*On recalcule l'accélération, la vitesse et la position de i*/
		
		c.calculaccel();
		c.calculvitesse();
		c.calculposition();

	}
		
	
	
	@Override
	public void run() {
		try {
			Test();
		} catch (InterruptedException e) {};
		// TODO Auto-generated method stub
		
	}
}
