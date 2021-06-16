package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import gui.okvir;
import logika.Logika;

public class infoPanel extends JPanel {
	Logika logika;
	prozor gui1;
	okvir gui;
	static JLabel score;
	static JLabel brojPoteza;
	
	public infoPanel (){
		build();
	}
	/**
	 * dodavanje 2 JLabela: score i preostali broj poteza, i dugme za restartovanje igre 
	 */
	public void build() {
		TitledBorder ram = new TitledBorder("Podaci:");
		setBorder(ram);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		score = new JLabel("Score: 0");
		brojPoteza = new JLabel ("Broj poteza: 13");
		ActionListener restart = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				gui.brisi();
			}
		};
		JButton nova = new JButton("Nova igra?");
		nova.addActionListener(restart);
		c.gridx=0;
		c.gridy=0;
		add(score,c);
		c.gridx=0;
		c.gridy=1;
		add(brojPoteza,c);
		c.gridx =3;
		c.gridy =3;
		add(nova,c);
		JPanel teg = new JPanel();
		c.gridx = 1;
		c.gridy = 2;
		c.weightx=1;
		c.weighty=1;
		add(teg,c);
	}
	/**
	 * povezivanje prethodno instanciranih objekata klasa: logika, okvir,prozor, sa ovom klasom: infoPanel
	 * @param logika
	 * @param gui
	 * @param gui1
	 */
	public void povezi(Logika logika,okvir gui, prozor gui1) {
		this.logika = logika;
		this.gui = gui;
		this.gui1 = gui1;	
	}
	
	/**
	 * azurira broj preostalih poteza i osvojenih bodova iz logike i ispisuje ih
	 */
	public void osvjezi() {
		brojPoteza.setText(String.valueOf("Broj poteza: "+(logika.getBrojPoteza())));
		score.setText(String.valueOf("Score: "+(gui1.score)));
	}
	
	/**
	 * ispisuje inicijalne vrijednosti
	 */
	public void resetuj() {
		score.setText("Score: 0");
		brojPoteza.setText("Broj poteza: 13");
	}
	
}
