package etf.openpgp.sr170398dsl170423d.impl;

public class main {

	public static void main(String[] args) {
		Backend b = new Backend();
		
		if(b.importKey("C:\\Users\\Sonja\\Desktop\\Rales123.asc") == true)
		{
			System.out.println("Key improted successfully!");
		}
		else
			System.out.println(":((((");
	}

}
