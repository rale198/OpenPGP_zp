package etf.openpgp.sr170398dsl170423d.impl;

public class main {

	public static void main(String[] args) {
		Backend b = new Backend();
		
		if(b.generateKeyPair("tanasko", "tanacko@yahoo.com", 2048, "sv3t4") == true)
		{
			System.out.println("KeyPair generated successfully!");
		}
		else
			System.out.println(":((((");
	}

}
