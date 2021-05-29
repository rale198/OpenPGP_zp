package etf.openpgp.sr170398dsl170423d.impl;

public class main {

	public static void main(String[] args) {
		Backend b = new Backend();
		/*
		if(b.removeImportKey(7482258956263966246l) == true)
		{
			System.out.println("Key removed successfully!");
			b.showPublicKeyRingCollection();
		}
		else
		{
			System.out.println(":((");
			b.showPublicKeyRingCollection();
		}
		*/
		//System.out.println(String.format("0x%H", "[B@55b7a4e0"));
		
		//b.generateKeyPair("tomislav", "toma@yahoo.tom", 2048, "123");
		//b.showPrivateKeyRingCollection();
		/*
		if(b.removeKeyPair(7482258956263966242l, "123") == true)
		{
			System.out.println("success");
		}
		else
		{
			System.out.println(":(");
		}
		*/
		b.showPrivateKeyRingCollection();

	}

}
