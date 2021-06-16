package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import konzola.KendiKras;
import logika.Logika;

public class okvir extends JFrame{
	Logika logika;
	prozor gui1;
	infoPanel gui2;

	public okvir(){
		build();
		setVisible(true);
	}
	/**
	 * ubacivanje ploce za igranje i info panela u okvir
	 */
	private void ubaciPanele() {
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx=0;
		c.gridy=0;
		add(KendiKras.gui1,c);
		c.gridx = 1;
		c.gridy = 0;
		add(KendiKras.gui2,c);
	}
	/**
	 * kreiranje rama u koji smijestamo plocu za igranje i info panel
	 */
	public void build() {
		setLayout(new GridBagLayout());
		setTitle("Super Kendi Kras Saga Elite Deluxe Extreme Edition");
		ubaciPanele();
		pack();
		setLocationRelativeTo(null);
	}
	
	/**
	 * brisanje ploce za igranje umjesto koje dodajemo dugme na koje kada kliknemo pokrecemo novu igru
	 */
	public void brisi() {
		ActionListener nova = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				GridBagConstraints c = new GridBagConstraints();
				c.fill = GridBagConstraints.BOTH;
				c.weightx = 1;
				c.weighty = 1;
				c.gridx=0;
				c.gridy=0;
				remove((JButton)e.getSource());
				KendiKras.gui1 = new prozor(logika);
				add(KendiKras.gui1,c);
				KendiKras.ponovoPostavi();	
				gui2.resetuj();
				gui1.score = 0;
				logika.reset();
				revalidate();
			}
		};
		remove(KendiKras.gui1);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx=0;
		c.gridy=0;
		JButton ponovo = new JButton("Zelite li poceti ponovo?");
		ponovo.addActionListener(nova);
		add(ponovo,c);
		revalidate();
	}
	/**
	 * povezivanje prethodno instanciranih objekata klasa: logika, prozor, infoPanel sa ovom klasom: okvir
	 * @param logika
	 * @param gui1
	 * @param gui2
	 */
	public void povezi(Logika logika, prozor gui1, infoPanel gui2) {
		this.logika = logika;
		this.gui1 = gui1;
		this.gui2 = gui2;
	}


}
