package gui;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;

import konzola.KendiKras;
import logika.Logika;

public class prozor extends JPanel{
	Logika logika;
	okvir gui;
	infoPanel gui2;
	int x1,x2,y1,y2;
	int score;
	JButton dugme1 = null;
	JButton dugme2 = null;
	int buttonWidth = 41;
	int buttonHeight = 26;
	int dimenzijax;
	int dimenzijay;
	JButton[][] plocaDugmadi;
	
	/**
	 * poziva istoimenu funkciju iz logike
	 */
	private void provjeriPlocuDugmadi() {
		score =logika.provjeriPlocuDugmadi(score);
		uporediPloce();
		osvjezi();
		oboji();
		if (getBrojPoteza()==0) {
			krajIgre();
		}
	}
	private int getDimenzijay() {
		return logika.getDimenzijay();
	}
	private int getDimenzijax() {
		return logika.getDimenzijax();
	}
	private void osvjezi() {
		gui2.osvjezi();
	}
	private int getBrojPoteza() {
		return logika.getBrojPoteza();
		
	}
	/**
	 * popunjava PlocuDugmadi sa vrijednostima iz ploca
	 */
	private void uporediPloce() {
		for (int i=0; i<dimenzijax;i++) {
			for (int j=0; j<dimenzijay; j++) {
				if (!(plocaDugmadi[i][j].getText().equals(String.valueOf(getPloca(i,j))))) {
					plocaDugmadi[i][j].setText(String.valueOf(getPloca(i,j)));
				}
			}
		}
	}
	
	private int getPloca(int i, int j) {
		return logika.getPloca(i,j);
	}
	/**
	 * konstruktor koji se povezuje sa logikom i popunjava matricu ploca nasumicnim vrijednostima
	 * @param logika
	 */
	public prozor(Logika logika) {
		this.logika = logika;
		dimenzijax = getDimenzijax();
		dimenzijay = getDimenzijay();
		plocaDugmadi = new JButton [dimenzijax][dimenzijay];
		logika.popuni();
		score = 0;
		build();
		
	}

	private void build() {
		setLayout(new GridBagLayout());
		ubaciDugmad();
		setVisible(true);
	}
	
	/**
	 * 
	 * Klikom na polje provjeravamo da li je klik prvi, ukoliko jeste spasavamo informacije o tom polju u dugme1,
	 *kada drugi put kliknemo, info o drugom polju spasavamo u dugme 2, te pozivamo 3 funkcije: isAdjacent,zamijeniDugmad,
	 *provjeriPlocuDugmadi
	 *
	 */
	ActionListener buttonClick = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{	
			if (dugme1 != null) {
				dugme2 = (JButton)e.getSource();
				x2 = dugme2.getX() / buttonWidth;
				y2 = dugme2.getY() / buttonHeight;
				if (logika.isAdjacent(x1,y1,x2,y2)) {
					zamijeniDugmad(x1,y1,x2,y2);
					provjeriPlocuDugmadi();	
				}
				dugme1 = null;
				dugme2 = null;
			}
			else {
			dugme1 = (JButton)e.getSource();
			x1 = dugme1.getX() / buttonWidth;
			y1 = dugme1.getY() / buttonHeight;
			}
			oboji();
		}
	};

	/**
	 * prolazi kroz matricu ploca, njene provjerene vrijednosti smijesta u matricu
	 * JButton-a, i dodijeljuje im ActionListener buttonClick, i odredjenu boju
	 */
	private void ubaciDugmad() {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		for (int i = 0; i < dimenzijax; i++) {
			for (int j = 0; j < dimenzijay; j++) {
				c.gridx = j;
				c.gridy = i;
				c.gridwidth=1;
				c.gridheight=1;
				JButton dugme = new JButton(String.valueOf(getPloca(i,j)));
				dugme.addActionListener(buttonClick);
				add(dugme,c);
				plocaDugmadi[i][j] = dugme;
				obojiJednu(i,j);
			}
		}
	}
	/**
	 *prolazi kroz petlje i poziva obojiJednu(i,j)
	 */
	private void oboji() {
		for(int i=0;i<dimenzijax;i++) {
			for(int j=0;j<dimenzijay;j++) {
				obojiJednu(i,j);
			}
		}
	}
	
	/**
	 * prima koordinate polja i na osnovu vrijednosti na polju dodijeljuje odredjenu boju
	 * @param i x koordinata
	 * @param j y koordinata
	 */
	private void obojiJednu(int i,int j) {
				if (plocaDugmadi[i][j].getText().equals("0")) {
					plocaDugmadi[i][j].setBackground(Color.red);
				}
				if (plocaDugmadi[i][j].getText().equals("1")) {
					plocaDugmadi[i][j].setBackground(Color.blue);
				}
				if (plocaDugmadi[i][j].getText().equals("2")) {
					plocaDugmadi[i][j].setBackground(Color.green);
				}
				if (plocaDugmadi[i][j].getText().equals("3")) {
					plocaDugmadi[i][j].setBackground(Color.orange);
				}
	}
	/**
	 * prima koordinate dva dugmeta i salje logici koja mijenja njihove vrijednosti
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	private void zamijeniDugmad(int x1, int y1, int x2, int y2) {
		logika.primiDugmadZaZamjenu(x1,y1,x2,y2);
	}

	/**
	 * povezivanje prethodno instanciranih objekata klasa: logika, okvir, infoPanel sa ovom klasom prozor
	 * @param logika
	 * @param gui
	 * @param gui2
	 */
	public void povezi(okvir gui, infoPanel gui2) {
		this.gui = gui;
		this.gui2 = gui2;
	}
	/**
	 * brisanje ploce za igru
	 */
	private void krajIgre() {
		gui.remove(KendiKras.gui1);
	}

}
