public class Requin extends Thon
{	private int cycleDeFamine;
	private int nbToursAvantFamine;
	
	public Requin(int a,int b)
	{	super(a);
		cycleDeFamine = b;
		nbToursAvantFamine = b;
	}
	
	public int getNbToursAvantFamine()
	{	return nbToursAvantFamine;
	}
	
	public int getCycleDeFamine()
	{	return cycleDeFamine;
	}
	
	public void setNbToursAvantFamine(int a)
	{	nbToursAvantFamine = a;
	}
	
	public void setCycleDeFamine(int a)
	{	cycleDeFamine = a;
	}
	
	public void tour()
	{	super.tour();
		if (nbToursAvantFamine > 0)
		{	nbToursAvantFamine--;
		}
		else
		{	this.setVivant(false);
		}
	}
	
	public void reproduire()
	{	super.reproduire();
		if (nbToursAvantFamine == 0)
		{	this.setVivant(false);
		}
		else
		{	nbToursAvantFamine = cycleDeFamine;
		}
	}
	
	public void recopier(Requin a)
	{	super.recopier(a);
		this.nbToursAvantFamine = a.nbToursAvantFamine;
	}
	
	public void afficher()
	{	System.out.println("Vivant : "+this.getVivant()+" Cycle de reproduction : "+this.getCycleDeReproduction()+" Reproduction dans : "+this.getNbToursAvantReproduction()+" Cycle de famine : "+cycleDeFamine+" Famine dans : "+nbToursAvantFamine);
	}
}