import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Fenetre extends JFrame implements ActionListener, Runnable
{	private Panneau p;
	private JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, nbthons, nbreq;
	private JTextField t1, t2, t3, t4, t5, t6;
	private JButton b1, b2, b3, b4;
	private Environnement ocean;
	private Thread tache1;
	private int dim, vitesse, temps;
	
	public Fenetre()
	{	this.setTitle("Wator");
		this.setSize(810,610);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p = new Panneau();
		p.setLayout(null);
		p.setBackground(Color.lightGray);
		this.setContentPane(p);
		
		vitesse = 64;
		temps = 0;
		
		//Zones de texte
		l1 = new JLabel("Dimensions :");
		l1.setBounds(500,10,150,20);
		p.add(l1);
		l13 = new JLabel("",JLabel.CENTER);
		l13.setBounds(500,40,290,20);
		p.add(l13);
		l2 = new JLabel("Cycle reprod thons :");
		l2.setBounds(500,70,150,20);
		p.add(l2);
		l3 = new JLabel("Cycle reprod requins :");
		l3.setBounds(500,100,150,20);
		p.add(l3);
		l4 = new JLabel("Cycle de famine :");
		l4.setBounds(500,130,150,20);
		p.add(l4);
		l5 = new JLabel("Nombre de thons :");
		l5.setBounds(500,160,150,20);
		p.add(l5);
		l6 = new JLabel("Nombre de requins :");
		l6.setBounds(500,190,150,20);
		p.add(l6);
		
		//Zones de saisie
		t1 = new JTextField("2");
		t1.setBounds(650,10,140,20);
		p.add(t1);		
		t2 = new JTextField("3");
		t2.setBounds(650,70,140,20);
		p.add(t2);
		t3 = new JTextField("5");
		t3.setBounds(650,100,140,20);
		p.add(t3);
		t4 = new JTextField("7");
		t4.setBounds(650,130,140,20);
		p.add(t4);
		t5 = new JTextField("20000");
		t5.setBounds(650,160,140,20);
		p.add(t5);
		t6 = new JTextField("5000");
		t6.setBounds(650,190,140,20);
		p.add(t6);
	
		//Boutons
		b1 = new JButton("Lancer la simulation");
		b1.setBounds(575,220,150,20);
		//Event b1
		b1.addActionListener(this);
		p.add(b1);
		b2 = new JButton("Accelerer");
		b2.setBounds(575,250,150,20);
		b2.addActionListener(this);
		p.add(b2);
		b3 = new JButton("Ralentir");
		b3.setBounds(575,280,150,20);
		b3.addActionListener(this);
		p.add(b3);
		b4 = new JButton("Pause");
		b4.setBounds(575,310,150,20);
		b4.addActionListener(this);
		p.add(b4);
		
		//Zones de texte d'information en bas à droite
		l7 = new JLabel("Legende",JLabel.CENTER);
		l7.setForeground(Color.WHITE);
		l7.setBounds(650,385,140,20);
		p.add(l7);
		l8 = new JLabel("Mer");
		l8.setForeground(Color.WHITE);
		l8.setBounds(680,415,100,20);
		p.add(l8);
		l9 = new JLabel("Thons");
		l9.setForeground(Color.WHITE);
		l9.setBounds(680,445,100,20);
		p.add(l9);
		l10 = new JLabel("Requins");
		l10.setForeground(Color.WHITE);
		l10.setBounds(680,475,100,20);
		p.add(l10);
		l11 = new JLabel("Nb Thons");
		l11.setForeground(Color.WHITE);
		l11.setBounds(660,505,140,20);
		p.add(l11);
		l12 = new JLabel("Nb Requins");
		l12.setForeground(Color.WHITE);
		l12.setBounds(660,535,140,20);
		p.add(l12);
		//Limite atteinte
		l14 = new JLabel("",JLabel.CENTER);
		l14.setForeground(Color.RED);
		l14.setBounds(500,340,290,20);
		p.add(l14);
		//NB Poissons
		nbthons = new JLabel("",JLabel.CENTER);
		nbthons.setForeground(Color.WHITE);
		nbthons.setBounds(690,505,140,20);
		p.add(nbthons);
		nbreq = new JLabel("",JLabel.CENTER);
		nbreq.setForeground(Color.WHITE);
		nbreq.setBounds(690,535,140,20);
		p.add(nbreq);
	}
	
	public void actionPerformed(ActionEvent ae)
	{	if (ae.getSource() == b1&&b1.getText().equals("Lancer la simulation"))
		{	//Test dimension
			if (((Integer.parseInt(t1.getText()) > 0) && (Integer.parseInt(t1.getText()) < 6) && (Integer.parseInt(t1.getText()) != 3)) || (Integer.parseInt(t1.getText()) == 10))
			{	//Affichage dimensions
				int h, l, nbt = Integer.parseInt(t5.getText()), nbr = Integer.parseInt(t6.getText());
				h = 360/Integer.parseInt(t1.getText());
				l = 480/Integer.parseInt(t1.getText());
				l13.setText(l+" x "+h+" = "+l*h+" cases");
				
				//changer valeurs thons, req poissons
				
				if (nbt > (l*h))
				{	l14.setText("Trop de thons");
				}
				else if (nbr > (l*h))
				{	l14.setText("Trop de requins");
				}
				else if ((nbt+nbr) > (l*h))
				{	l14.setText("Trop de poissons");
				}
				//Lancer la simulation
				else
				{	dim = Integer.parseInt(t1.getText());
					l14.setText("");
					b1.setText("Reinitialiser");
					ocean = new Environnement(l,h,Integer.parseInt(t2.getText()),Integer.parseInt(t3.getText()),Integer.parseInt(t4.getText()),nbt,nbr);
					p.init(ocean);
					
					nbthons.setText(ocean.getNbThons()+"");
					nbreq.setText(ocean.getNbRequins()+"");
					//Début thread
					tache1 = new Thread(this);
					tache1.start();
					p.repaint();
				}
			}
			else 
			{	l14.setText("La dimension doit etre 1, 2, 4, 5 ou 10");
			}
		}
		//Réinitialisation
		else if (ae.getSource() == b1&&b1.getText().equals("Reinitialiser"))
		{	tache1.stop();
			p.stop();
			p.repaint();
			temps = 0;
			ocean = null;
			l13.setText("");
			nbthons.setText("");
			nbreq.setText("");
			b1.setText("Lancer la simulation");
			t1.setText("2");
			t2.setText("3");
			t3.setText("5");
			t4.setText("7");
			t5.setText("20000");
			t6.setText("5000");	
			b4.setText("Pause");
			vitesse = 64;
		}
		//Autres boutons
		else if (ae.getSource() == b2)
		{	if(vitesse < 8193)
			{	vitesse = vitesse * 2;
			}
		}
		else if (ae.getSource() == b3)
		{	if(vitesse > 1)
			{	vitesse = vitesse / 2;
			}
		}	
		else if (ae.getSource() == b4)
		{	if (b4.getText().equals("Pause"))
			{	b4.setText("Reprendre");
			}
			else
			{	b4.setText("Pause");
			}
		}
		
		p.repaint();
		this.setSize(811,611);
		this.setSize(810,610);
	}
	
	public void run()
	{	long tempsDebut = System.currentTimeMillis();
		long tempsFin;
		while(tache1.isAlive())
		{	try
			{	if (b4.getText().equals("Pause"))
				{	for (int i=0;i<vitesse;i++)
					{	ocean.evoluer((int)Math.floor(Math.random()*ocean.getNbLignes()),(int)Math.floor(Math.random()*ocean.getNbColonnes()));
					}
					temps++;
					nbthons.setText(ocean.getNbThons()+" ");
					nbreq.setText(ocean.getNbRequins()+" ");
					ocean.calculPoint(temps);
					p.init(ocean);
					p.repaint();
				}
				tache1.sleep(1);
			}
			catch (InterruptedException e) {}
		}
	}
}
