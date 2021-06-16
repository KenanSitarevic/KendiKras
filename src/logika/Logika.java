package logika;
import java.util.Random;

import javax.swing.JButton;

import konzola.KendiKras;


public class Logika {

	int scr;
	int brojProlazenja = 0;
	int x1,y1,x2,y2;
	int dimenzijax = 8;
	int dimenzijay = 8;
	int brojPoteza = 13;
	int[][] ploca = new int[dimenzijax][dimenzijay];

	/**
	 * prima koordinate dva polja i mijenja njihove vrijednosti u matrici ploca
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void primiDugmadZaZamjenu(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		int v = ploca[y1][x1];
		ploca[y1][x1] = ploca[y2][x2];
		ploca[y2][x2] = v;
		
	}
	/**
	 * spusta polja u matrici ploca, koja se nalaze u koloni iznad, dodaje nove nasumicne brojeve kada spusti postojece
	 * @param brojac
	 * @param j x koordinata posljednjeg polja koje treba da se zamijeni poljem iznad
	 * @param i y koordinata posljednjeg polja koje treba da se zamijeni poljem iznad
	 */
	public void spustiDugmadVertikal(int brojac, int j, int i) {
		Random r = new Random();
		if(j+1==dimenzijay-1 && ploca[j][i]==ploca[j+1][i]){
			j=7;
		}
		for (int o=j-(brojac+1); o>=0; o--) {
			ploca[o+(brojac+1)][i]=ploca[o][i];
		}
		for (int n = 0;n<=brojac; n++) {
			ploca[n][i]=r.nextInt(4);
			}
	}
	/**
	 * spusta polja u matrici ploca, koja se nalaze u redu iznad, dodaje nove nasumicne brojeve kada spusti postojece
	 * @param brojac broj istih uzastopnih polja u jednom redu
	 * @param j x koordinata posljednjeg polja koje treba da se zamijeni poljem iznad 
	 * @param i y koordinata posljednjeg polja koje treba da se zamijeni poljem iznad 
	 */
	public void spustiDugmadHorizontal(int brojac, int j, int i) {
		Random r = new Random();
		if (j+1==dimenzijay-1 && ploca[i][j]==ploca[i][j+1]){
			j=7;
		}
		for (int o = i;o>=0;o--) {
			for(int k = j-brojac; k<=j;k++) {
				if (o==0) {
					ploca[o][k]=r.nextInt(4);
			}
				else {
					ploca[o][k]=ploca[o-1][k];	
			}		
			}
		}
	}
	/**
	 * prolazi kroz matricu ploca, i provjerava  ima li 3 ili vise istih uzastopnih polja,
	 * ukoliko ima poziva spustiDugmadHorizontal() i/ili spustiDugmadVertikal()
	 * postavljamo jeLiMijenjano na true, da bi se opet pozvala ova funkciju, vraca osvojene bodove: score
	 * @param score
	 * @return 
	 */
	public int provjeriPlocuDugmadi(int score) {
		boolean jeLiMijenjano = false;
		int brojac=0;
		for (int i = 0; i < dimenzijax; i++) {
			for (int j = 0; j < dimenzijay-1; j++) {
				if (ploca[i][j]==ploca[i][j+1]) {
					brojac++;
				}
				if (!(ploca[i][j]==ploca[i][j+1]) || j+1==dimenzijay-1) 
				{
					if (brojac>1) 
					{
						score=score+((brojac+1)*100);
						scr = score;
						jeLiMijenjano = true;
						spustiDugmadHorizontal(brojac,j,i);
					}
					brojac=0;
				}	
			}
		}
			brojac = 0;
		for (int i = 0; i < dimenzijax; i++) {
			for (int j = 0; j < dimenzijay-1; j++) {
				if (ploca[j][i]==ploca[j+1][i]) {
					brojac++;	
				}
				if (!(ploca[j][i]==ploca[j+1][i])  || j+1==dimenzijay-1)
				{
					if (brojac>1) 
					{
						score=score+((brojac+1)*100);
						scr = score;
						jeLiMijenjano = true;
						spustiDugmadVertikal(brojac,j,i);	
					}
					brojac=0;
				}
					}	
		}
		brojProlazenja+=1;	
		if(jeLiMijenjano) {
			return provjeriPlocuDugmadi(score);
		}
		if (brojProlazenja == 1 && jeLiMijenjano == false ) {
			brojProlazenja = 0;
			primiDugmadZaZamjenu(x1,y1,x2,y2);
			return scr;
		}
		else {
			brojProlazenja = 0;
		}
		brojPoteza = brojPoteza-1;
		return scr;
	}
	/**
	 * ispisuje matricu ploca u konzolu
	 */
	public void ispisi() {
		System.out.println("|-------------------------------|");
		for (int i =0; i<ploca.length;i++) {
			for (int j=0; j<ploca[0].length;j++) {
				if (j == 0) {
					System.out.print("| "+ploca[i][j]+" | ");
				}
				else {
					System.out.print(ploca[i][j]+" | ");
				}	
			}
			System.out.println();
			System.out.print("|-------------------------------|");
			System.out.println();
		}
	}
	/**
	 * postavlja scr (osvojene bodove) i broj preostalih poteza (brojPoteza) na 0 i 13 respektivno
	 */
	public void reset() {
		scr = 0;
		brojPoteza = 13;
		
		
	}
	/**
	 *provjerava da li je potez validan tj. da li su odabrana polja jedno do drugog
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public boolean isAdjacent(int x1,int y1,int x2,int y2) {
		if (x1==x2-1 && y1==y2 ||
			x1==x2+1 && y1==y2 ||
			y1==y2-1 && x1==x2 ||
			y1==y2+1 && x1==x2 ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * popunjava matricu ploca nasumicnim brojevima
	 */
	public void popuni() {
		Random r = new Random();
		for (int i = 0; i < ploca.length; i++) {
			for (int j = 0; j < ploca[0].length; j++) {
				ploca[i][j]=r.nextInt(4);
		}
	}
		provjeriPlocu();
}
	
	/**
	 * provjerava da li se u matrici nalaze 3 ili vise istih uzastopnih brojeva, zatim ih mijenja novim uzastopnim brojevima
	 */
	private void provjeriPlocu() {
		Random r = new Random();
		boolean jeLiMijenjano = false;
		int brojac=0;
		for (int i = 0; i < dimenzijax; i++) {
			for (int j = 0; j < dimenzijay-1; j++) {
				if (ploca[i][j]==ploca[i][j+1]) {
					brojac++;
				}
				if (ploca[i][j]!=ploca[i][j+1] || j+1==dimenzijay-1) {
					if (brojac>1) 
					{
						jeLiMijenjano = true;
						for(int k = j-brojac; k<=j;k++) {
							ploca[i][k]=r.nextInt(4);
						}
					}
					brojac=0;
					}
		}
			brojac = 0;
	}
		for (int i = 0; i < dimenzijax; i++) {
			brojac = 0;
			for (int j = 0; j < dimenzijay-1; j++) {
				if (ploca[j][i]==ploca[j+1][i]) {
					brojac++;
				}
				if (ploca[j][i]!=ploca[j+1][i] || j+1==dimenzijay-1) {
					if (brojac>1) 
					{
						jeLiMijenjano = true;
						for(int k = j-brojac; k<=j;k++) {
							ploca[k][i]=r.nextInt(4);
						}
					}
					brojac=0;
					}				
		}	
	}
		if(jeLiMijenjano) {
			provjeriPlocu();
		}
	}
	/**
	 * vraca broj iz matrice ploca na poziciji [i][j]
	 * @param i
	 * @param j
	 * @return
	 */
	public int getPloca(int i, int j) {
		return ploca[i][j];
	}
	
	/**
	 * vraca brojPoteza
	 * @return
	 */
	public int getBrojPoteza() {
		return brojPoteza;
	}
	
	/**
	 * vraca visinu matrice ploca
	 * @return
	 */
	public int getDimenzijay() {
		return dimenzijay;
	}

	/**
	 * vraca sirinu matrice ploca
	 * @return
	 */
	public int getDimenzijax() {
		return dimenzijax;
	}
}
