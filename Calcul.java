

class Calcul implements Runnable{
	/*Calcul effectue les opérations de Cinematic*/
	/*Il prend en compte les indices (i,j) pour ne pas effectuer plusieurs fois l'opération de calcul de position ou de vitesse au temps t*/
	int j;
	int i;
	Cinematic c;
	
	public Calcul(int j,int i, Cinematic c) {
		this.j=j;
		this.i=i;
		this.c=c;
	}
	public void Test() throws InterruptedException{
		/*On calcule la contribution de j à l'accélération de i*/
		c.calculaccel();
		/*j'ai choisi la convention si j==0 pour remettre à jour la position ou la vitesse
		 * Il ne faut le faire que pour un unique j*/
		if (j==0 || i==0 && j==1) {
			
			c.calculposition();
			c.calculvitesse();
		}
		
	}
	
	@Override
	public void run() {
		try {
			Test();
		} catch (InterruptedException e) {};
		// TODO Auto-generated method stub
		
	}
}
