import javax.swing.*;
import java.awt.*;

public class Panneau extends JPanel
{	private boolean affiche;
	private Environnement e1;

	public Panneau()
	{	affiche = false;
	}
	
	public void init(Environnement e2)
	{	affiche = true;
		e1 = e2;
	}
	
	public void stop()
	{	affiche = false;
	}
	
	public void paintComponent(Graphics g)
	{	//Rectangles de fond
		g.setColor(Color.BLACK);
		g.fillRect(10,10,480,360);
		
		g.setColor(Color.GRAY);
		g.fillRect(10,385,630,181);
		g.fillRect(650,385,140,181);
		
		//Carrés légende
		g.setColor(Color.BLUE);
		g.fillRect(660,450,10,10);
		g.setColor(Color.RED);
		g.fillRect(660,480,10,10);
		g.setColor(Color.BLACK);
		g.fillRect(660,420,10,10);
		
		//Contours
		g.drawRect(9,384,631,182);
		g.drawRect(649,384,141,182);
		g.setColor(Color.WHITE);
		g.drawRect(660,450,10,10);
		g.drawRect(660,480,10,10);
		g.drawRect(660,420,10,10);
		
		
		if (affiche)
		{	//Obtenir la dimension par calcul simple
			int dim = (480/e1.getNbLignes());
			//Représentation simulation
			for (int i=0;i<e1.getNbLignes();i++) 
			{	for (int j=0;j<e1.getNbColonnes();j++)
				{	if (e1.getTabT(i,j).getVivant()) //i et j sont inversés (nbL et nbC dans Environnement)
					{	g.setColor(Color.BLUE);
						g.fillRect((10+dim*i),(10+dim*j),dim,dim);
					}
					else if (e1.getTabR(i,j).getVivant())
					{	g.setColor(Color.RED);
						g.fillRect((10+dim*i),(10+dim*j),dim,dim);
					}
				}
			}
			//Courbe
			for (int i=0; i<630; i++)
			{	g.setColor(Color.BLUE);
				//nbpoisson*180/maxpoissons -> maxpoissons = (360/dim)*(480/dim)
				g.fillRect((10+i),(566-((e1.getCourbeThons(i)*180)/((360/dim)*(480/dim)))),1,1);
				g.setColor(Color.RED);
				g.fillRect((10+i),(566-((e1.getCourbeRequins(i)*180)/((360/dim)*(480/dim)))),1,1);
				//Contour
				g.setColor(Color.BLACK);
				g.drawRect(9,384,631,182);
			}
		}
	}
}