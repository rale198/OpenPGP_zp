package etf.openpgp.sr170398dsl170423d.impl;

public class main {

	public static void main(String[] args) {
		Backend b = new Backend();
		
		if(b.generateKeyPair("luka199", "simko@yahoo.com", 1024, "Simkelion99") == true)
		{
			System.out.println("KeyPair generated successfully!");
		}
		else
			System.out.println(":((((");
	}

}
