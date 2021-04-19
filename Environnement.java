public class Environnement
{	private int nbLignes;
	private int nbColonnes;
	private Thon[][] tabT;
	private Requin[][] tabR;
	private int[][] courbe;
	
	public Environnement(int a, int b, int crt, int crr, int cf, int nbt, int nbr)
	{	// nbt et nbr correspondent au nb donné de poissons vivants au départ
		
		nbLignes = a;
		nbColonnes = b;
		
		// Déclaration des tableaux et allocation des espaces, 
		//  les tableaux sont remplis de poissons morts
		tabT = new Thon[a][b];
		tabR = new Requin[a][b];
		for (int i=0; i<a; i++)
		{	for (int j=0; j<b; j++)
			{	tabT[i][j] = new Thon(crt);
				tabR[i][j] = new Requin(crr, cf);
			}
		}
		//Tableau de la courbe courbe[0][] -> thons / courbe[1][] -> requins
		//Indices -> 1 ms et 1 px
		courbe = new int[2][630];
		for (int i=0; i<2; i++)
		{	for (int j=0; j<630; j++)
			{	courbe[i][j] = 0;
			}
		}
		// 
		int cpt = 0, l, c;
		while (cpt < nbt)
		{	l = (int)Math.floor(Math.random()*nbLignes);
			c = (int)Math.floor(Math.random()*nbColonnes);
			// Teste si un poisson est vivant dans la case et sinon créé un thon ou un poisson vivant
			while (tabT[l][c].getVivant() || tabR[l][c].getVivant())
			{	l = (int)Math.floor(Math.random()*nbLignes);
				c = (int)Math.floor(Math.random()*nbColonnes);
			}
			tabT[l][c].setVivant(true);
			cpt++;
		}
		cpt = 0;
		while (cpt < nbr)
		{	l = (int)Math.floor(Math.random()*nbLignes);
			c = (int)Math.floor(Math.random()*nbColonnes);
			// Teste si un poisson est vivant dans la case et sinon créé un thon ou un poisson vivant
			while (tabT[l][c].getVivant() || tabR[l][c].getVivant())
			{	l = (int)Math.floor(Math.random()*nbLignes);
				c = (int)Math.floor(Math.random()*nbColonnes);
			}
			tabR[l][c].setVivant(true);
			cpt++;
		}
	}

	public int getNbLignes()
	{	return nbLignes;
	}
	
	public int getNbColonnes()
	{	return nbColonnes;
	}
	
	public Thon getTabT(int x, int y)
	{	return tabT[x][y];
	}
	
	public Requin getTabR(int x, int y)
	{	return tabR[x][y];
	}
	
	public int getNbCases()
	{	return nbLignes*nbColonnes;
	}
	
	public int getNbThons()
	{	int cpt = 0;
		for (int i=0; i<nbLignes; i++)
		{	for (int j=0; j<nbColonnes; j++)
			{	if (tabT[i][j].getVivant())
				{	cpt++;
				}
			}
		}
		return cpt;
	}
	
	public int getNbRequins()
	{	int cpt = 0;
		for (int i=0; i<nbLignes; i++)
		{	for (int j=0; j<nbColonnes; j++)
			{	if (tabR[i][j].getVivant())
				{	cpt++;
				}
			}
		}
		return cpt;
	}
	
	public void evoluer(int a, int b)
	{	//Déplacement aléatoire : nb aléatoire entre 1 et 8
		int dir = (int)Math.ceil(Math.random()*8);
		if (tabT[a][b].getVivant())
		{	// Le thon vieillit d'un tour
			tabT[a][b].tour();
			
			if (dir == 1)
			{	if ((b>0) && (tabT[a][b-1].getVivant() == false) && (tabR[a][b-1].getVivant() == false))
				{	tabT[a][b-1].recopier(tabT[a][b]);
					tabT[a][b].reproduire();
				}
			}
			else if (dir == 2)
			{	if ((a>0) && (b>0) && (tabT[a-1][b-1].getVivant() == false) && (tabR[a-1][b-1].getVivant() == false))
				{	tabT[a-1][b-1].recopier(tabT[a][b]);
					tabT[a][b].reproduire();
				}
			}
			else if (dir == 3)
			{	if ((a>0) && (tabT[a-1][b].getVivant() == false) && (tabR[a-1][b].getVivant() == false))
				{	tabT[a-1][b].recopier(tabT[a][b]);
					tabT[a][b].reproduire();
				}
			}
			else if (dir == 4)
			{	if ((a>0) && (b<nbColonnes-1) && (tabT[a-1][b+1].getVivant() == false) && (tabR[a-1][b+1].getVivant() == false))
				{	tabT[a-1][b+1].recopier(tabT[a][b]);
					tabT[a][b].reproduire();
				}
			}
			else if (dir == 5)
			{	if ((b<nbColonnes-1) && (tabT[a][b+1].getVivant() == false) && (tabR[a][b+1].getVivant() == false))
				{	tabT[a][b+1].recopier(tabT[a][b]);
					tabT[a][b].reproduire();
				}
			}
			else if (dir == 6)
			{	if ((a<nbLignes-1) && (b<nbColonnes-1) && (tabT[a+1][b+1].getVivant() == false) && (tabR[a+1][b+1].getVivant() == false))
				{	tabT[a+1][b+1].recopier(tabT[a][b]);
					tabT[a][b].reproduire();
				}
			}
			else if (dir == 7)
			{	if ((a<nbLignes-1) && (tabT[a+1][b].getVivant() == false) && (tabR[a+1][b].getVivant() == false))
				{	tabT[a+1][b].recopier(tabT[a][b]);
					tabT[a][b].reproduire();
				}
			}
			else if (dir == 8)
			{	if ((a<nbLignes-1) && (b>0) && (tabT[a+1][b-1].getVivant() == false) && (tabR[a+1][b-1].getVivant() == false))
				{	tabT[a+1][b-1].recopier(tabT[a][b]);
					tabT[a][b].reproduire();
				}
			}
		}
		//Partie requins
		else if (tabR[a][b].getVivant())
		{	tabR[a][b].tour();
			if (dir == 1)
			{	if ((b>0) && (tabR[a][b-1].getVivant() == false))
				{	// Si la case contient un thon, le requin le mange
					if (tabT[a][b-1].getVivant())
					{	tabT[a][b-1].setVivant(false);
						tabR[a][b].setNbToursAvantFamine(tabR[a][b].getCycleDeFamine());
					}
					tabR[a][b-1].recopier(tabR[a][b]);
					tabR[a][b].reproduire();
				}
			}
			else if (dir == 2)
			{	if ((a>0) && (b>0) && (tabR[a-1][b-1].getVivant() == false))
				{	if (tabT[a-1][b-1].getVivant())
					{	tabT[a-1][b-1].setVivant(false);
						tabR[a][b].setNbToursAvantFamine(tabR[a][b].getCycleDeFamine());
					}
					tabR[a-1][b-1].recopier(tabR[a][b]);
					tabR[a][b].reproduire();
				}
			}
			else if (dir == 3)
			{	if ((a>0) && (tabR[a-1][b].getVivant() == false))
				{	if (tabT[a-1][b].getVivant())
					{	tabT[a-1][b].setVivant(false);
						tabR[a][b].setNbToursAvantFamine(tabR[a][b].getCycleDeFamine());
					}
					tabR[a-1][b].recopier(tabR[a][b]);
					tabR[a][b].reproduire();
				}
			}
			else if (dir == 4)
			{	if ((a>0) && (b<nbColonnes-1) && (tabR[a-1][b+1].getVivant() == false))
				{	if (tabT[a-1][b+1].getVivant())
					{	tabT[a-1][b+1].setVivant(false);
						tabR[a][b].setNbToursAvantFamine(tabR[a][b].getCycleDeFamine());
					}
					tabR[a-1][b+1].recopier(tabR[a][b]);
					tabR[a][b].reproduire();
				}
			}
			else if (dir == 5)
			{	if ((b<nbColonnes-1) && (tabR[a][b+1].getVivant() == false))
				{	if (tabT[a][b+1].getVivant())
					{	tabT[a][b+1].setVivant(false);
						tabR[a][b].setNbToursAvantFamine(tabR[a][b].getCycleDeFamine());
					}
					tabR[a][b+1].recopier(tabR[a][b]);
					tabR[a][b].reproduire();
				}
			}
			else if (dir == 6)
			{	if ((a<nbLignes-1) && (b<nbColonnes-1) && (tabR[a+1][b+1].getVivant() == false))
				{	if (tabT[a+1][b+1].getVivant())
					{	tabT[a+1][b+1].setVivant(false);
						tabR[a][b].setNbToursAvantFamine(tabR[a][b].getCycleDeFamine());
					}
					tabR[a+1][b+1].recopier(tabR[a][b]);
					tabR[a][b].reproduire();
				}
			}
			else if (dir == 7)
			{	if ((a<nbLignes-1) && (tabR[a+1][b].getVivant() == false))
				{	if (tabT[a+1][b].getVivant())
					{	tabT[a+1][b].setVivant(false);
						tabR[a][b].setNbToursAvantFamine(tabR[a][b].getCycleDeFamine());
					}
					tabR[a+1][b].recopier(tabR[a][b]);
					tabR[a][b].reproduire();
				}
			}
			else if (dir == 8)
			{	if ((a<nbLignes-1) && (b>0) && (tabR[a+1][b-1].getVivant() == false))
				{	if (tabT[a+1][b-1].getVivant())
					{	tabT[a+1][b-1].setVivant(false);
						tabR[a][b].setNbToursAvantFamine(tabR[a][b].getCycleDeFamine());
					}
					tabR[a+1][b-1].recopier(tabR[a][b]);
					tabR[a][b].reproduire();
				}
			}
		}
	}
	
	public void calculPoint(int temps)
	{	if (temps > 630)
		{	//Décalage du tableau
			for (int i=0; i<2; i++)
			{	for (int j=0; j<629; j++)
				{	courbe[i][j] = courbe[i][j+1];	
				}
			}
			courbe[0][629] = this.getNbThons();
			courbe[1][629] = this.getNbRequins();
		}
		else
		{	courbe[0][temps-1] = this.getNbThons();
			courbe[1][temps-1] = this.getNbRequins();
		}
	}
	
	//Accesseurs courbe
	public int getCourbeThons(int temps)
	{	return courbe[0][temps];
	}
	
	public int getCourbeRequins(int temps)
	{	return courbe[1][temps];
	}
}