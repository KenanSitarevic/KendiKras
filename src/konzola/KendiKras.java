package konzola;
import gui.infoPanel;
import gui.okvir;
import gui.prozor;
import logika.Logika;

public class KendiKras {

	static Logika logika;
	public static okvir gui;
	public static prozor gui1;
	public static infoPanel gui2;
	
	public KendiKras() {
		logika = new Logika();
		gui1 = new prozor(logika);
		gui2 = new infoPanel();
		gui = new okvir();
		
		ponovoPostavi();
	}
	/**
	 * povezivanje svih klasa
	 */
	public static void ponovoPostavi() {
		gui.povezi(logika,gui1,gui2);
		gui1.povezi(gui,gui2);
		gui2.povezi(logika,gui,gui1);
	}
}