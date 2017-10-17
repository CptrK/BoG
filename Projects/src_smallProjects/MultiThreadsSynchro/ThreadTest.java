package MultiThreadsSynchro;

public class ThreadTest {
	
	public static void main (String [] args) throws InterruptedException {
		System.out.println("Main - Start");
		
		Buero b = new Buero(0);
		Einkauf e = new Einkauf(b);
		Verkaufsschalter v = new Verkaufsschalter(b, e);
		
		e.start();
		v.start();
		
		e.join();
		v.join();
		
		System.out.println("Main - Ende");
	}

}
