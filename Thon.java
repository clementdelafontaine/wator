public class Thon
{	//Attributs
	private boolean vivant;
	private int cycleDeReproduction;
	private int nbToursAvantReproduction;
	
	//Méthodes
	public Thon (int n)
	{	vivant = false;
		cycleDeReproduction = n;
		nbToursAvantReproduction = n;	
	}
	
	public boolean getVivant()
	{	return vivant;
	}
	
	public int getCycleDeReproduction()
	{	return cycleDeReproduction;
	}
	
	public int getNbToursAvantReproduction()
	{	return nbToursAvantReproduction;
	}
	
	public void setVivant(boolean n)
	{	vivant = n;
	}
	
	public void setCycleDeReproduction(int n)
	{	cycleDeReproduction = n;
	}
	
	public void setNbToursAvantReproduction(int n)
	{	nbToursAvantReproduction = n;
	}
	
	public void tour()
	{	if (nbToursAvantReproduction > 0)
		{	nbToursAvantReproduction--;
		}
	}
	
	public void reproduire()
	{	if (nbToursAvantReproduction == 0)
		{	nbToursAvantReproduction = cycleDeReproduction;
		}
		else
		{	vivant = false;
		}
	}
	
	public void afficher()
	{	System.out.println("Vivant : "+vivant+" Cycle de reproduction : "+cycleDeReproduction+" Reproduction dans : "+nbToursAvantReproduction);
	}
	
	public void recopier(Thon a)
	{	this.vivant = a.vivant;
		this.cycleDeReproduction = a.cycleDeReproduction;
		this.nbToursAvantReproduction = a.nbToursAvantReproduction;
		
		if (this.nbToursAvantReproduction == 0)
		{	this.nbToursAvantReproduction = this.cycleDeReproduction;
		}
	}
}