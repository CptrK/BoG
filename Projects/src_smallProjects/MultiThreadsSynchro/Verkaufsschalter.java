package MultiThreadsSynchro;

	import java.util.Random;

	public class Verkaufsschalter extends Thread{
		
		private Random generator;
		private Buero buero;
		private Einkauf einkauf;
		private boolean start = true;
		
		Verkaufsschalter(Buero buero, Einkauf einkauf) {
			this.buero = buero;
			this.einkauf = einkauf;
			generator = new Random();
		}
		
		public void run() {
			if (start == true) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				start = false;
			}
			int millisec = generator.nextInt(1000)+1000;
			int verkaufteKarten=0;
			while (buero.kartenKontingent > 0) {
				buero.karteVerkauft(1);
				verkaufteKarten++;
				if (buero.kartenKontingent == 0) {
					try {
						einkauf.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					sleep(millisec);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("An dem Verkaufschalter wurden " + verkaufteKarten + " Karten verkauft!");
		}
}
