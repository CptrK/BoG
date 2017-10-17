package computerSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.StringTokenizer;

public class Computer {
	//variables to give information about one object of this class
	private static boolean isBelegt;
	final public String name;
	public static String belegtVon;
	final private String iNummer;
	public String geraeteTyp;
	private String ipvAdresse;
	private String macAdresse;
	private String gateway;
	private String subnetMask; 
	public String aPreis;
	public String nDauer;
	//constructor method when an object is created
	Computer (String iNummer, String name, String typ, String preis, String dauer) {
		setIsBelegt(false);
		isBelegt = getIsBelegt();
		this.name = name;
		this.iNummer = iNummer;
		this.geraeteTyp = typ;
		this.aPreis = preis;
		this.nDauer = dauer;
	}
	
	public String getInventarNummer() {
		return this.iNummer;
	}
	
	public String getBelegtVon() {
		return belegtVon;
	}
	
	public void setBelegtVon(String name) {
		belegtVon = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getTyp() {
		return this.geraeteTyp;
	}
	
	public boolean getIsBelegt() {
		return isBelegt;
	}
	
	public void setIsBelegt(boolean bool)	{
		isBelegt = bool;
	}

	public void setNetworkSettings() {
		
		try {
			//IPv4-Addresse is created
			InetAddress ip = InetAddress.getLocalHost();
			ipvAdresse = ip.toString();
			
			//SubnetzMaske is created
			short temp;
			NetworkInterface networkInterface = NetworkInterface.getByInetAddress(ip);
			temp = networkInterface.getInterfaceAddresses().get(1).getNetworkPrefixLength();
			subnetMask = String.valueOf(temp);
			
			//Gateway is created
			String defaultAddresse ="";
			Process result = Runtime.getRuntime().exec("netstat -rn");
            BufferedReader output = new BufferedReader(new InputStreamReader(
                    result.getInputStream()));
            String line = output.readLine();
            while (line != null) {
                if (line.contains("0.0.0.0")) {

                    StringTokenizer stringTokenizer = new StringTokenizer(line);
                    stringTokenizer.nextElement();// first string is 0.0.0.0
                    stringTokenizer.nextElement();// second string is 0.0.0.0
                    defaultAddresse = (String) stringTokenizer.nextElement(); // this is our default address
                    break;
                }
                line = output.readLine();

            }
        	this.gateway = defaultAddresse;  	
			
			//MAC-Addresse is created
		    Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
		    while(networks.hasMoreElements()) {
		      NetworkInterface network = networks.nextElement();
		      byte[] mac = network.getHardwareAddress();

		      if(mac != null) {

		        StringBuilder sb = new StringBuilder();
		        for (int i = 0; i < mac.length; i++) {
		          sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
		        }
		        this.macAdresse = sb.toString();
		      }
		    }
		  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getIPv4Addresse() {
		return this.ipvAdresse;
	}
	
	public String getSubNetMask() {
		return this.subnetMask;
	}
	
	public String getGateway() {
		return this.gateway;
	}
	
	public String getMacAddresse() {
		return this.macAdresse;
	}
	
	public void setAPreis(String preis) {
		aPreis = preis;
	}
	
	public String getAPreis() {
		return this.aPreis;
	}
	
	public void setNDauer(String dauer) {
		nDauer = dauer;
	}
	
	public String getNDauer() {
		return this.nDauer;
	}
}
