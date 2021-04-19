import java.io.*;

public class CreationThon
{	public static void main (String args[])
	{	Thon nouveauThon;
		nouveauThon = new Thon(5);
		
		/*for (int i=0; i<5; i++)
		{	nouveauThon.tour();
			nouveauThon.afficher();
		}
		nouveauThon.afficher();
		nouveauThon.reproduire();
		nouveauThon.setVivant(true);
		nouveauThon.afficher(); */
		
		Thon nouveauThon2;
		nouveauThon2 = new Thon(0);
		nouveauThon2.afficher();
		nouveauThon2.recopier(nouveauThon);
		nouveauThon.afficher();
		nouveauThon2.afficher();
		nouveauThon.setCycleDeReproduction(6);
		nouveauThon.afficher();
		nouveauThon2.afficher();
		
		
	}
}