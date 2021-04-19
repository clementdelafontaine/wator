import java.io.*;

public class CreaRequin
{	public static void main (String args[])
	{	Requin r1;
		r1 = new Requin(5,7);
		r1.afficher();
		
		Requin r2;
		r2 = new Requin(3,7);
		r2.afficher();
		
		r2.recopier(r1);
		
		r1.afficher();
		r2.afficher();
		
		r1.setCycleDeFamine(6);
		r1.afficher();
		r2.afficher();
	}
}