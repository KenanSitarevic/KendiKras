package konzola;


import java.util.Scanner;

import logika.Logika;

public class konzola {
	static Logika logika;
	int x1,x2,y1,y2;
	int score;
	int dimenzijax;
	int dimenzijay;
	
	/**
	 * povezuje se sa logikom, popunjava matricu nasumicnim brojevima koju ispisuje u konzolu, zatim ceka unos
	 * koordinata dva polja od korisnika, mijenja njihova mjesta ukoliko je to moguce, azuriranu matricu ponovo
	 * ispisuje i te radnje ponavlja dok brojPoteza ne bude jednak 0
	 */
	public konzola() {
		logika = new Logika();
		dimenzijax = getDimenzijax();
		dimenzijay = getDimenzijay();
		logika.popuni();
		System.out.println("Score: "+score+" "+"Broj Poteza: "+getBrojPoteza());
		logika.ispisi();
		while (Integer.parseInt(getBrojPoteza())>0) {
			pokreni();
			if (logika.isAdjacent(x1,y1,x2,y2)) {
				logika.primiDugmadZaZamjenu(x1,y1,x2,y2);
				score = logika.provjeriPlocuDugmadi(score);
			}
			else {
				System.out.println("Unesena polja nisu susjedna");
			}
			System.out.println("Score: "+score+" "+"Broj Poteza: "+getBrojPoteza());
			logika.ispisi();
		}
		System.out.println("Kraj igre");
	}
	
	private int getDimenzijay() {
		return logika.getDimenzijay();
	}

	private int getDimenzijax() {
		return logika.getDimenzijax();
	}

	private String getBrojPoteza() {
		return String.valueOf(logika.getBrojPoteza());
	}

	private void pokreni() {
		int brojac = 1;
		while(brojac<4) {
		try {
			if (brojac ==1) {
				System.out.println("Unesite x koordinatu 1. polja");
				x1 = unos(x1);
				brojac = brojac+1;
			}
			if (brojac ==2) {
				System.out.println("Unesite y koordinatu 1. polja");
				y1 = unos(y1);
				brojac = brojac+1;
			}
			if (brojac ==3) {
				System.out.println("Unesite x koordinatu 2. polja");
				x2 = unos(x2);
				brojac = brojac+1;
			}
			if (brojac ==4) {
				System.out.println("Unesite y koordinatu 2. polja");
				y2 = unos(y2);
				brojac = brojac+1;
			}
			}
		catch (Exception e) {
			System.out.println("Nepravilan unos!");
		}
		}
	}
	
	private int unos(int x) {
		Scanner s = new Scanner(System.in);
		x = Integer.parseInt((s.nextLine()));
		while (x<0 || x>7) {
			System.out.println("Unos nije ispravan! Ponovo unesite.");
			x = Integer.parseInt((s.nextLine()));
		}
		return x;
	}
	
}
